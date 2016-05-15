package com.example.fangyi.download;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    int threadCount = 4;
    static String path = "http://ww4.sinaimg.cn/mw690/0067xXBmjw1f3qnn0f57vj30jg0pu40i.jpg";
    int threadFinish = 0;
    private ProgressBar pb;
    private TextView tv;

    //进度条的当前进度，初始为0
    long a = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pb = (ProgressBar) findViewById(R.id.pb);
        tv = (TextView) findViewById(R.id.tv);
    }
    //刷新 百分比下载进度文本
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            tv.setText(a * 100 / pb.getMax() + "%");
        }
    };

    public void click(View v) {
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    URL url = new URL(path);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setReadTimeout(5000);
                    conn.setConnectTimeout(5000);

                    if (conn.getResponseCode() == 200) {
                        //拿到要下载的文件的大小
                        int Length = conn.getContentLength();
                        //指定临时文件的路径和文件名
                        File file = new File("sdcard/" + getFileName(path));
                        //创建随机储存文件对象
                        RandomAccessFile raf = new RandomAccessFile(file, "rwd");
                        //设置临时文件的大小，保证跟服务器文件一模一样
                        raf.setLength(Length);

                        //给进度条的最大进度设置源文件总大小。给进度条设置进度时直接设置下载进度即可
                        pb.setMax(Length);
                        System.out.println("下载的总大小" + Length + "     @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                        //发送消息，让textView更新百分比
                        handler.sendEmptyMessage(1);

                        //计算每个线程下载的字节
                        int size = Length / threadCount;
                        for (int i = 0; i < threadCount; i++) {
                            //计算开始 三个线程下载数据的开始位置和结束位置
                            int startIndex = i * size;
                            int endIndex = (i + 1) * size - 1;
                            //如果是最后一个线程，那么结束位置做特殊处理
                            if (i == threadCount-1) {
                                endIndex = Length -1;
                            }
                            System.out.println("线程" + i + "的开始位置：" + startIndex + "-----------" + "结束位置：" + endIndex);
                            //开始线程，传入线程id和下载的开始位置、结束位置
                            new DownLoadThread(i, startIndex, endIndex).start();
                        }


                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();
    }

    public static String getFileName(String path) {
        int index = path.lastIndexOf("/");
        return path.substring(index + 1);
    }

    class DownLoadThread extends Thread {
        int threadId;
        int startIndex;
        int endIndex;


        public DownLoadThread(int threadId, int startIndex, int endIndex) {
            super();
            this.threadId = threadId;
            this.startIndex = startIndex;
            this.endIndex = endIndex;
        }

        @Override
        public void run() {
            URL url = null;
            try {
                //设置记录下载进度的临时文件的路径和文件名
                File fileProgress = new File("sdcard/" + threadId + ".txt");
                //判断保存下载进度的临时文件是否存在
                if (fileProgress.exists()) {
                    FileInputStream fis = new FileInputStream(fileProgress);
                    BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                    //拿到临时文件中所保存的新的开始位置
                    int newStartIndex = Integer.parseInt(br.readLine());

                    a += (newStartIndex - startIndex) + 1;
                    pb.setProgress((int) a);

                    startIndex = newStartIndex;
                    fis.close();
                }
                System.out.println("*******************************");
                System.out.println("线程" + threadId + "的开始位置是：" + startIndex + "-----------" + "结束位置是：" + endIndex);


                url = new URL(path);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setReadTimeout(5000);
                conn.setConnectTimeout(5000);
                //设置请求的数据的范围
                conn.setRequestProperty("Range", "bytes=" + startIndex + "-" + endIndex);

                //建立连接
                if (conn.getResponseCode() == 206) {
                    InputStream is = conn.getInputStream();
                    int len = 0;
                    byte[] b = new byte[1024];
                    //当前线程现在的总字节数
                    int total = 0;

                    //指定临时文件的路径和文件名
                    File file = new File("sdcard/" + getFileName(path));
                    //创建随机储存文件对象
                    RandomAccessFile raf = new RandomAccessFile(file, "rwd");
                    //设置线程从哪个位置开始写入数据到临时文件
                    raf.seek(startIndex);

                    //记录当前下载进度
                    int currentPosition = startIndex;


                    while ((len = is.read(b)) != -1) {
                        //把下载下来的源文件数据写入raf临时文件
                        raf.write(b, 0, len);

                        total += len;
                        RandomAccessFile rafProgress = new RandomAccessFile(fileProgress, "rwd");
                        currentPosition = startIndex + total;
                        //把下载进度写进rafProgress临时文件，下一次下载时，就以这个值作为新的startIndex
                        rafProgress.write((currentPosition + "").getBytes());
                        rafProgress.close();

//                    System.out.println("线程" + threadId + "已经下载" + total);

                        a += len;
                        pb.setProgress((int) a);
                    }

                    raf.close();

                    System.out.println("线程" + threadId + "下载完毕" + "-----------");
                    System.out.println("线程" + threadId + "下载的总字节数" + total);
                    System.out.println("XXXXXXX下载的总字节数" + a);

                    threadFinish++;
                    //如果这个条件成立，说明所有线程下载完毕，删除临时文件
                    //同语句块
                    synchronized (path) {
                        if (threadFinish == threadCount) {
                            for (int i = 0; i < threadCount; i++) {
                                File temp = new File("sdcard/" + i + ".txt");
                                temp.delete();
                            }
                            threadFinish = 0;
                        }
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}