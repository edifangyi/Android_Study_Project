import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by FANGYI on 2016/5/11.
 */
public class MultiDownLoad {
    //设置线程数
    static int threadCount = 4;
    static String path = "http://ww4.sinaimg.cn/mw690/0067xXBmjw1f3qnn0f57vj30jg0pu40i.jpg";
    static int threadFinish = 0;

    public static void main(String[] args) {

        try {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setReadTimeout(5000);
            conn.setConnectTimeout(5000);

            if (conn.getResponseCode() == 200) {
                //拿到要下载的文件的大小
                int length = conn.getContentLength();

                System.out.println("文件总大小：" + length);

                //指定临时文件的路径和文件名
                File file = new File(getFileName(path));
                //创建随机储存文件对象
                RandomAccessFile raf = new RandomAccessFile(file, "rwd");
                //设置临时文件的大小，保证跟服务器文件一模一样
                raf.setLength(length);


                //计算每个线程下载的字节
                int size = length / threadCount;
                for (int i = 0; i < threadCount; i++) {
                    //计算开始 三个线程下载数据的开始位置和结束位置
                    int startIndex = i * size;
                    int endIndex = (i + 1) * size - 1;
                    //如果是最后一个线程，那么结束位置做特殊处理
                    if (i == threadCount - 1 ) {
                        endIndex = length -1;
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

    public static String getFileName(String path) {
        int index = path.lastIndexOf("/");
        return path.substring(index + 1);
    }

    static int a = 0;

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
        try {
            //设置记录下载进度的临时文件的路径和文件名
            File fileProgress = new File(threadId + ".txt");
            //判断保存下载进度的临时文件是否存在
            if (fileProgress.exists()) {
                FileInputStream fis = new FileInputStream(fileProgress);
                BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                //拿到临时文件中所保存的新的开始位置
                int newStartIndex = Integer.parseInt(br.readLine());
                startIndex = newStartIndex;
                fis.close();
            }
            System.out.println("*******************************");
            System.out.println("线程" + threadId + "的开始位置是：" + startIndex + "-----------" + "结束位置是：" + endIndex);

            URL url = new URL(MultiDownLoad.path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setReadTimeout(5000);
            conn.setConnectTimeout(5000);
            //设置请求的数据的范围
            conn.setRequestProperty("Range", "bytes=" + startIndex + "-" + endIndex);

            //建立连接,206是请求部分数据成功
            if (conn.getResponseCode() == 206) {
                InputStream is = conn.getInputStream();
                int len = 0;
                byte[] b = new byte[1024];
                //当前线程现在的总字节数
                int total = 0;

                File file = new File(MultiDownLoad.getFileName(MultiDownLoad.path));
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

                    MultiDownLoad.a += len;
                }




                raf.close();
                System.out.println("线程" + threadId + "下载完毕" + "-----------");
                System.out.println("线程" + threadId + "下载的总字节数" + total);
                System.out.println("XXXXXXX下载的总字节数" + MultiDownLoad.a);

                MultiDownLoad.threadFinish++;
                //如果这个条件成立，说明所有线程下载完毕
                //同语句块
                synchronized (MultiDownLoad.path) {
                    if (MultiDownLoad.threadFinish == MultiDownLoad.threadCount) {
                        for (int i = 0; i < MultiDownLoad.threadCount; i++) {
                            File temp = new File(i + ".txt");
                            temp.delete();
                        }
                        MultiDownLoad.threadFinish = 0;
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
