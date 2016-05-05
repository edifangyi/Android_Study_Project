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
    }


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













