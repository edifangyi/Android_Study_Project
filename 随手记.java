Android 文字使用 sp，长度使用 dp ，不推荐使用 像素 px

 android:inputType="phone" //限制输入
 android:inputType="textPassword"//输入文字显示密文
 android:hint="请输入电话好吗..." //提示输入

 android:lines="5" //展示5行内容
 android:singleLine="ture" //默认单行
 android:ellipsize="none" //没有省略号，参数有开始省略，末尾省略，中间省略
 android:ellipsize="marquee" //跑马灯效果

android:padding="8dp"//边框

定义框 框中文字字体是否全为大写
android:textAllCaps="false" 



android:src 前景，当前控件上面内容的大小
android:background: 北京控件大小
android:scaleType="fitXY" 让前景去填充慢背景



//跑马灯效果
    <TextView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="最新的手机卫士，快来下载，下载就送大妹纸o(*≧▽≦)ツ"
        android:singleLine="true"
        android:ellipsize="marquee"
        android:focusable="true"
        android:focusableInTouchMode="true"/>

//自定义控件，在手机安全卫士中的 类
        public class FocusedTextView extends TextView{}

    <com.fangyi.mobilesafe.view.FocusedTextView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:singleLine="true"
        android:ellipsize="marquee"
        android:text="哈哈哈哈哈`(*∩_∩*)′，，，，o(*≧▽≦)ツ快来下载"/>


 android:shadowColor="#ff0000"//给文字设置阴影
 android:shadowDx="2"
 android:shadowDy="2"
 android:shadowRadius="2"


 
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


//进度条
    <ProgressBar
        android:id="@+id/pb"
        style="@android:style/Widget.ProgressBar.Horizontal"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" />
//拖动条
    <SeekBar
        android:id="@+id/sb"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"/>


快速录入

 快速打出类似于eclipse 的sysout(针对android studio 1.2.1.1)

    settings->搜索Live Templates->如图指定位置改为sysout,原来默认的是sout

    iter:
    for (Sms  : mSmsList) {
        
    }

/**

 */


清单文件中
//默认安装位置
android:installLocation="auto"|"internalOnly"|"preferExternal"//自动，内部，外部，默认安装在内部，内存不够安装外部
android:launchMode="singleInstance" //启动模式
android:excludeFromRecents="true" //是否在最近列表中显示 true 显示，false不显示
android:configChanges="screenSize|keyboardHidden|orientation"//屏幕切换不影响Activity


android:theme="@android:style/Theme.Translucent.NoTitleBar" //透明背景




            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                // 桌面上是否有图标
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>


/**

 */

android:shape="oval"

            oval    椭圆形
            line    线
            rectangle 矩形
            ring 戒指
/**

 */


文本显示组件：TextView
按钮组件：Button
编辑框：EditText
单选钮：RadioGroup
复选框：CheckBox
下拉列表框：Spinner
图片视图：ImageView
图片按钮：ImageButton
时间选择器：TimePicker
日期选择器：DatePicker
滚动视图：ScrollView
列表显示：ListView
对话框：Dialog
定制对话框和LayoutInflater
日期对话框：DatePickerDialog
时间对话框：TimePickerDialog
进度处理对话框：ProgressDialog
随笔提示文本：AutoCompleteTextView
拖动条：SeekBar
评分组件：RatingBar
信息提示框：Toast
图片切换：ImageSwitcher
文本切换：TextSwitcher
拖拉图片：Gallery
网格视图：GridView
时钟组件：AnalogClock与DigitalClock
计时器：Chronometer
标签：TabHost
菜单：Menu
选项菜单：OptionsMenu
上下文菜单：ContextMenu
子菜单：SubMenu
隐式抽屉组件：SlidingDrawer
缩放控制：ZoomControls
弹出窗口：PopupWindow
树型组件：ExpandableListView

/**
 
 */

查询电脑ip地址  ipconfig 


/**
 
 */

重构Listview

this.setSelector(R.drawable.XX);点击显示颜色
this.setCacheColorHint(R.drawable.XX);拖拽的颜色
this.setDivider(getDrawable(R.drawable.XX));每个条目的间隔的分割线


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
 * //两个组件，第一个组件是100dp+权重1:1分配的，第二个组件是50dp+权重1:1分配的
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
属性名                                     功能说明
    android:layout_centerHoriazontal        控制该子组件是否相对于父容器水平居中
    android:layout_centerVertical           控制该子组件是否相对于父容器垂直居中
    android:layout_centerInParent           控制该子组件是否相对于父容器居中

    android:layout_alignParentBottom        控制该子组件是否与父容器底端对齐
    android:layout_alignParentTop           控制该子组件是否与父容器顶端对齐
    android:layout_alignParentLeft          控制该子组件是否与父容器左端对齐
    android:layout_alignParentRight         控制该子组件是否与父容器右端对齐
    android:layout_alignParentEnd           控制该子组件是否与父容器结束对齐（右上角）
    android:layout_alignParentStart         控制该子组件是否与父容器开始对齐（左上角）

    需要注意的是，这些属相是可以同时使用的，不过如果同时使用相互冲突的属性（比如layout_alignParentLeft和layout_alignParentRight）会产生一些比较古怪的效果，所以不建议同时使用有冲突的属性。

属性名                                     功能说明
    android:layout_toRightOf                控制该子组件位于ID指定组件的右侧
    android:layout_toLeftOf                 控制该子组件位于ID指定组件的左侧
    android:layout_above                    控制该子组件位于ID指定组件的上方
    android:layout_below                    控制该子组件位于ID指定组件的下方

    android:layout_alignTop                 控制该组件顶端与ID指定组件的上边界对齐
    android:layout_alignBottom              控制该底端组件与ID指定组件的下边界对齐
    android:layout_alignLeft                控制该组件左端与ID指定组件的左边界对齐
    android:layout_alignRight               控制该组件右端与ID指定组件的右边界对齐
    android:layout_alignStart               控制该组件起始位置与ID指定组件的起始位置对齐（左上角）
    android:layout_alignEnd                 控制该组件结束位置与ID指定组件的结束位置对齐（右上角）



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


***********************************************************************************************



    tvAvailRom.setText("内存可用：" + getAvailSpace(Environment.getDataDirectory().getAbsolutePath()));
    tvAvailSdcard.setText("sd卡可用：" + getAvailSpace(Environment.getExternalStorageDirectory().getAbsolutePath()));


    /**
     * 可到某个目录的可用空间
     * @param path
     * @return
     */
    private String getAvailSpace(String path) {
        StatFs fs = new StatFs(path);
        //得到多少块可用空间
        long blocks = fs.getAvailableBlocksLong();
        //得到每块的可用大小
        long size = fs.getBlockSizeLong();

        Formatter.formatFileSize(this, blocks * size);

        return Formatter.formatFileSize(this, blocks * size);;
    }

***********************************************************************************************


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



实现多线程的方式

        new Thread() {
            @Override
            public void run() {
                super.run();
            }
        }.start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                
            }
        }).start();




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
    
    如果用10个应用同时启动一个非单例模式的Activity，那么10个应用的栈中，各子会有一个该Activity
    
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
	 ContentProvider(内容提供者)

广播：
	Android
	*系统在运行过程中，会产生很多事件：开机、拨打电话、收发短信、电量改变、屏幕解锁
	*一旦发生这种事件，系统就会发送一个广播，为了接收到这个广播，知道系统发生了什么事件
	就需要广播接受者，功能类似收音机


    广播接收者很特别，不管可以在功能清单文件注册，也可以在代码里面注册(Activity，Service，其他)



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

	//注册
        <receiver android:name=".CallReceiver">
            <intent-filter>
                <action android:name="android.intent.action.NEW_OUTGOING_CALL"/>
            </intent-filter>
        </receiver>


    /********************************************************************/

    //开启服务
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
	其实是收到了短信广播，那我们的短信拦截器，只要在短信应用拿到广播时，把短信广播拦截，那么短信应用就不会收到广播


/***************************************************************************************/
	
	<uses-permission android:name="android.permission.RECEIVE_SMS"/>

        <receiver android:name=".SmsReceiver">
            <intent-filter android:priority="1000">
                <action android:name="android.provider.Telephony.SMS_RECEIVED"/>
            </intent-filter>
        </receiver>



    /**
     * 在优先级相同的情况下  代码注册的优先级比清单文件中的高
     */
    //广播优先级 -1000~1000
	android:priority="1000"

    //代码注册优先级
    filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);//注册最高优先级


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
	        //获取收到的广播时那一条
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
/**
 
 */

服务
	*默默运行在后台的组件，可以理解为没有前台的Activity，服务的作用就是用来运行需要在后台默默运行的代码

进程优先级

http://www.android-doc.com/guide/components/processes-and-threads.html

	由难到简系统回收服务 4. 5. 在系统内存不足时候自动杀死，系统内存恢复时候也不会被启动

	1.Foreground process(前台进程):

		拥有一个可以与用户交互的Activity(Activity的onResume方法调用)的进程

		拥有一个和用户正在交互的Activity绑定的服务的进程

		拥有一个调用startForeground方法的服务

		拥有一个正在执行生命周期( onCreate()/onStart()/onDestroy() )方法的服务的进程

		拥有一个正在执行onReceiver方法的广播接收者


	2.Visible process(可见进程):

		拥有一个可见但无焦点的Activity(Activity的onPause方法被调用)的进程

		拥有一个可见和Activity绑定的Service的进程

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

服务的两种启动方式
	*startService

	*bindService (绑定服务)

		此方法启动的服务，不会使进程称为服务进程，在服务运行列表根本找不到
		绑定服务启动的服务会和启动它的Activity同生共死

绑定服务生命周期：
	create → bind
解绑服务声明周期：
	unBind → destroy

/***************************************************************************************/

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mConnection = new MyServiceConnection();
    }

	public void click9(View v) {
        Intent intent = new Intent(this, MyService.class);
        //绑定服务，其实就是跟服务建立连接
        bindService(intent, mConnection, BIND_AUTO_CREATE);

    }


    private MyServiceConnection mConnection;

    class MyServiceConnection implements ServiceConnection {

        //服务建立连接
        //当连接到服务的连接被简历，此方法调用
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

        }
        //失去连接时，此方法调用
        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }

    public void click10(View v) {
        //解绑
        unbindService(mConnection);

    }

/*******************************************************************************************/

public class MyService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

}

/**
 
 */

访问服务中的方法


/*********************************************************************************/

private MyService.ZhongJianRen zjr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //提前创建，否则怕点击按钮来不及
        Intent intent = new Intent(this, MyService.class);
        bindService(intent, new MyServiceConnection(), BIND_AUTO_CREATE);
    }

    //调用服务中的方法
	public void click9(View v) {
		zjr.qianXian();
    }

    class MyServiceConnection implements ServiceConnection {

        //service:中间人对象
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
			zjr = (MyService.ZhongJianRen) service;
        }
    }

/*******************************************************************************************/

public class MyService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
    	//把中间人对象return给Activity
        return new ZhongJianRen();
    }

    class ZhongJianRen extends Binder {
        public void qianXian() {
            banZheng();
        }
    }

    public void banZheng() {
    	//小秘给你办证
    }
}

/*******************************************************************************************/
/*******************************************************************************************/
/*******************************************************************************************/
/*******************************************************************************************/

当不希望 一些方法被调用，我们可以通过接口的形式来调用 这个类中的能调用的方法，不能调用的不可以显示

public class MyService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new ZhongJianRen();
    }

    class ZhongJianRen extends Binder implements PublicBussiness {

        @Override
        public void qianXian() {
            banZheng();
        }

        public void daMaJiang() {
            //只有领导可以调用，小秘陪打麻将
        }
    }

    public void banZheng() {

    }
}

/****************************************************************************************/

public interface PublicBussiness {
    void  qianXian();
}


/****************************************************************************************/
    
    public void click9(View v) {

        zjr.qianXian();
        // zjr.daMaJiang(); 不可能被调用，不会提示的

    }


private PublicBussiness zjr;

    class MyServiceConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            zjr = (PublicBussiness) service;
        }
    }


/**
 
 */

服务的混合开启

	start - bind - unBind - stop


/************************************************************************************/

服务运行音乐播放器

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mConnection = new MyServiceConnection();

        Intent intent = new Intent(this, MusicService.class);
        //混合调用
        //先start再bind，保证此进程成为服务进程，并且可以拿到binder对象
        startService(intent);
        bindService(intent, new MyServiceConnection(), BIND_AUTO_CREATE);
    }

    private MusicController mc;

    class MyServiceConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mc = (MusicController) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }

    public void play(View v) {
        mc.play();
    }

    public void pause(View v) {
        mc.pause();
    }

    public void continuePlay(View v) {
        mc.continuePlay();
    }
}

/*********************************************************************/

public class MusicService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MusicBinder();
    }

    class MusicBinder extends Binder implements MusicController {

        @Override
        public void play() {
            MusicService.this.play();
        }
        @Override
        public void pause() {
            MusicService.this.pause();
        }
        @Override
        public void continuePlay() {
            MusicService.this.continuePlay();
        }
    }

    public void play() {
        System.out.println("开始播放");
    }

    public void pause() {
        System.out.println("暂停不放");
    }

    public void continuePlay() {
        System.out.println("继续播放");
    }
}

/*****************************************************************************/

public interface MusicController {
    void  play();
    void pause();
    void continuePlay();
}


/**
 
 */

使用服务中用代码注册广播接收者

	*广播接受者的使用必须在清单文件中注册
	*其实也可以用代码注册
	*但是基本用清单文件注册	(用户不关，永远在运行)
	*代码注册的好处就是，可以解除注册
	*有些特殊广播接收者，只能代码注册
		*电量改变
		*屏幕解锁和锁屏

		解锁：Intent.ACTION_SCREEN_ON
		锁屏：Intent.ACTION_SCREEN_OFF
		电量改变：Intent.ACTION_BATTERY_CHANGED
		进入低电状态的一瞬间：Intent.ACTION_BATTERY_LOW
		充电过了最低电量提示：Intent.ACTION_BATTERY_OKAY
/********************************************************************************************/


public class MainActivity extends AppCompatActivity {

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

		intent = new Intent(this, RegisterService.class);

    }

    public void start(View v) {
        startService(intent);
    }

    public void stop(View v) {
        startService(intent);
    }
}


/*****************************************************************************/

public class RegisterService extends Service {
    private ScreenOnOffReceiver soorr;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //注册广播接收者
        //1.创建广播接收者对象
        soorr = new ScreenOnOffReceiver();

        //2.创建 intent-filter 来指定广播接收者接收什么广播

        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);//注册最高优先级


        //3.注册
        registerReceiver(soorr, filter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //解除注册
        unregisterReceiver(soorr);
    }
}

/***************************************************************************************/

public class ScreenOnOffReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        String action  = intent.getAction();
        if (Intent.ACTION_SCREEN_ON.equals(action)) {
            System.out.println("屏幕亮起来，就算解锁");
            Toast.makeText(context, "屏幕解锁", Toast.LENGTH_SHORT).show();
        } else if (Intent.ACTION_SCREEN_OFF.equals(action)) {
            System.out.println("锁屏");
            Toast.makeText(context, "锁屏", Toast.LENGTH_SHORT).show();
        }
    }
}

/**
 
 */

服务的种类

	*本地服务
		启动的服务跟Activity在同一个进程
	*远程服务
		启动的服务跟Activity在不同的进程

AIDL 
	*Android Interface Definition Language
	*安卓接口定义语言
	*作用：进程间通信

Linux: PIPE:管道

/************************************************************************************/

使用 AIDL (Android Studio)	

	https://www.zhihu.com/question/21581761

	1. 首先，在实现Parcelable接口的类所在的包上右键New -> AIDL
	1. 直接把	PublicBussiness.java 后缀改成 .aidl

	2. AS 会自动生成aidl文件夹以及对应的包名。接着声明所需要类。
	提示interface name must be unique时，可以随意命名，新建完成后再重命名。
	
	3. Build -> Make Project后可生成Java文件。
	
	app\build\generated\source\aidl\debug\com\example\fangyi\XXXXXXX

/************************************************************************************/

隐式启动远程服务

APP2 访问 APP1 中的方法

APP 1 //远程办证服务

        <service android:name=".BanZhengService">
            <intent-filter>
                <action android:name="aaa.bbb.ccc"/>
            </intent-filter>
        </service>

        /************************************************************************/

		public class MyService extends Service {
		    @Nullable
		    @Override
		    public IBinder onBind(Intent intent) {
		        return new ZhongJianRen();
		    }

		    class ZhongJianRen extends Binder implements PublicBussiness {

		        @Override
		        public void qianXian() {
		            banZheng();
		        }

		        public void daMaJiang() {
		            //只有领导可以调用，小秘陪打麻将
		        }
		    }

		    public void banZheng() {
		        //小莉莉帮你办外地的证
		    }
		}

		/******************************************************************************/
		
		public interface PublicBussiness {
		    void  qianXian();
		}


		/************************以上代码经过AIDL修改以后******************************/

		public class MyService extends Service {
		    @Nullable
		    @Override
		    public IBinder onBind(Intent intent) {
		        return new ZhongJianRen();
		    }
		    //直接继承Stub，stub已经继承了binder对象，可以被onBind return出去，
		    //并且stub也实现了PublicBussiness接口 
		    //是 PublicBussiness.AIDL 自动生成的
		    class ZhongJianRen extends PublicBussiness.Stub {

		        @Override
		        public void qianXian() {
		        	//PublicBussiness.this.banZheng();
		            banZheng();
		        }

		        public void daMaJiang() {
		            //只有领导可以调用，小秘陪打麻将
		        }
		    }

		    public void banZheng() {
		        //小莉莉帮你办外地的证
		    }
		}

		/********************************************************/

		interface PublicBussiness {
		    void  qianXian();
		}


/********************************************************************************/
/********************************************************************************/
/********************************************************************************/
/********************************************************************************/

要想让 APP2 访问 APP1 的服务，需要把 APP1 中的 PublicBussiness.AIDL 直接粘贴到 APP2 中
必须要保证包名相同，就会有相同的过程，，，也会在 APP2 中生成相同的 PublicBussiness.java，内容一模一样
这样就实现了两个应用进程间的通信

APP 2 //我要远程办证

    //启动远程办证服务
    public void click12(View v) {
        Intent intent = new Intent();
        intent.setAction("aaa.bbb.ccc");
        bindService(intent, new MyServiceConnection(), BIND_AUTO_CREATE);
    }

    PublicBussiness pb;
    class MyServiceConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            pb = Stub.asInterface(service);
        }
    }

    //远程办证
	public void click14(View v) {
	        try {
	            pb.qianXian();
	        } catch (RemoteException e) {
	            e.printStackTrace();
	        }
	    }
/**
 
 */
/**
 
 */

内容提供者

	ContentProvider

APP 1
/**************************************************************************************/
内容提供者在清单文件中注册

        <provider
            android:name=".sqlite.PersonProvider"
            android:authorities="aaa.bbb.ccc" //主机名
            android:exported="true">
            
        </provider>

/**************************************************************************************/

public class MyOpenHelper extends SQLiteOpenHelper {
    public MyOpenHelper(Context context) {
        super(context, "people.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table person(_id integer primary key autoincrement, name char(10), salary integer(10))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

/****************************************************************************************************************/

public class PersonProvider extends ContentProvider {

    private MyOpenHelper oh;
    private SQLiteDatabase db;

    @Override
    public boolean onCreate() {
                            //获取内容提供者所运行在的上下文
        oh = new MyOpenHelper(getContext());
        db = oh.getWritableDatabase();

        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        //返回查询结果
        Cursor cursor = db.query("person", projection, selection, selectionArgs, null, null, sortOrder, null);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }


    @Nullable
    @Override
    //url：这是其他应用在访问内容提供者时传入的主机名，告诉系统要访问哪个内容提供者
    //values：这是其他用户传入的数据
    public Uri insert(Uri uri, ContentValues values) {
        db.insert("person", null , values);
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
    	//返回删除的条数
        int i = db.delete("person", selection, selectionArgs);
        return i;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        //修改多少行
        int i = db.update("person", values, selection, selectionArgs);
        return i;
    }
}

/**************************************************************************************************************/
/**************************************************************************************************************/
/**************************************************************************************************************/
APP 2

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //插入数据按钮
    public void insert(View v) {
        //通过内容提供者把数据插入数据库
        //拿到一个内容解析器对象
        ContentResolver cr = getContentResolver();
        ContentValues values = new ContentValues();
        values.put("name", "小莉莉");
        values.put("salary", 12000);
        //传入内容提供者的主机名
        cr.insert(Uri.parse("content://aaa.bbb.ccc"), values);

        values = new ContentValues();
        values.put("name", "小胖");
        values.put("salary", 13000);
        cr.insert(Uri.parse("content://aaa.bbb.ccc"), values);

        values.clear();
        values.put("name", "笑哈哈");
        values.put("salary", 14000);
        cr.insert(Uri.parse("content://aaa.bbb.ccc"), values);
    }

    //删除按钮
    public void delete(View v) {
        ContentResolver cr = getContentResolver();
        int i = cr.delete(Uri.parse("content://aaa.bbb.ccc"), "name = ?", new String[] {"小莉莉"});
        Toast.makeText(this, "删除了" + i +  "条数据", Toast.LENGTH_SHORT).show();
    }

    //修改按钮
    public void update(View v) {
        ContentResolver cr = getContentResolver();
        ContentValues values = new ContentValues();
        values.put("name", "嘻嘻嘻");
        int i = cr.update(Uri.parse("content://aaa.bbb.ccc"), values,"name = ?", new String[] {"笑哈哈"});
        
        //修改一条数据后要clear()
        // values.clear();

        Toast.makeText(this, "修改了" + i +  "条数据", Toast.LENGTH_SHORT).show();
    }

    //查询数据
    public void query(View v) {
        ContentResolver cr = getContentResolver();
        Cursor cursor = cr.query(Uri.parse("content://aaa.bbb.ccc"), null, null, null, null);
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String salary = cursor.getString(cursor.getColumnIndex("salary"));
            Toast.makeText(this, name +";"+ salary, Toast.LENGTH_SHORT).show();
        }
    }
}

/**
 
 */

Uri匹配器

APP 1 
/*****************************************************************************/

/**
 * 添加一张新的表
 */

public class MyOpenHelper extends SQLiteOpenHelper {
    public MyOpenHelper(Context context) {
        super(context, "people.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table person(_id integer primary key autoincrement, name char(10), salary integer(10))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("create table teacher(_id integer primary key autoincrement, name char(10)");
    }
}

/******************************************************************************************************/


public class PersonProvider extends ContentProvider {

    private MyOpenHelper oh;
    private SQLiteDatabase db;
    //检测其他用户传入的uri跟预先定义好的匹配规则中哪一条uri匹配
    //跟任何都不匹配返回-1
    UriMatcher um = new UriMatcher(UriMatcher.NO_MATCH);
    {
        //添加uri汽配起的匹配规则，通过对返回码的判断，就知道其他用户传进来的uri是什么
        um.addURI("aaa.bbb.ccc", "person", 1); //content://aaa.bbb.ccc/persopn
        um.addURI("aaa.bbb.ccc", "teacher", 2);
        um.addURI("aaa.bbb.ccc", "person/#", 3);//content://aaa.bbb.ccc/persopn/(可以带数据、文本)
    }


    @Override
    public boolean onCreate() {
                            //获取内容提供者所运行在的上下文
        oh = new MyOpenHelper(getContext());
        db = oh.getWritableDatabase();

        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        //返回查询结果
        Cursor cursor = null;
        if (um.match(uri) == 1) {
            cursor = db.query("person", projection, selection, selectionArgs, null, null, sortOrder, null);
        } else if (um.match(uri) == 2) {

        } else if (um.match(uri) == 3){
            //拿到url中路径后面携带的数字
            long id = ContentUris.parseId(uri);
            //把拿到的数字作为主键的值来查询数据库
            cursor = db.query("person", projection, "_id = ?", new String[]{id +""}, null, null, sortOrder, null);
        } else {
            throw new IllegalArgumentException("uri中没有携带路径");
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        if (um.match(uri) == 1) {
            return "vnd.android.cursor.dir/";
        } else if (um.match(uri) ==3) {
            return "vnd.android.cursor.item/";
        }
        return null;
    }


    @Nullable
    @Override
    //url：这是其他应用在访问内容提供者时传入的主机名，告诉系统要访问哪个内容提供者
    //values：这是其他用户传入的数据
    public Uri insert(Uri uri, ContentValues values) {
        if (um.match(uri) == 1) {
            db.insert("person", null , values);
        } else if (um.match(uri) == 2) {
            db.insert("teacher", null , values);
        } else {
            throw new IllegalArgumentException("uri中没有携带路径");
        }
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int i;
        if (um.match(uri) == 1) {
            i = db.delete("person", selection, selectionArgs);
        } else if (um.match(uri) == 2) {
            i = db.delete("teacher", selection, selectionArgs);
        } else {
            throw new IllegalArgumentException("别瞎删");
        }
        return i;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        //修改多少行
        int i = db.update("person", values, selection, selectionArgs);
        return i;
    }
}

/***********************************************************************************************************/
/***********************************************************************************************************/
/***********************************************************************************************************/

APP 2

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void insert(View v) {
        //通过内容提供者把数据插入数据库
        //拿到一个内容解析器对象
        ContentResolver cr = getContentResolver();
        ContentValues values = new ContentValues();
        values.put("name", "小莉莉");
        values.put("salary", 12000);
        //传入内容提供者的主机名
        cr.insert(Uri.parse("content://aaa.bbb.ccc/person"), values);

        values = new ContentValues();
        values.put("name", "小胖");
        values.put("salary", 13000);
        cr.insert(Uri.parse("content://aaa.bbb.ccc/teacher"), values);

        values.clear();
        values.put("name", "笑哈哈");
        values.put("salary", 14000);
        cr.insert(Uri.parse("content://aaa.bbb.ccc"), values);
    }

    //删除按钮
    public void delete(View v) {
        ContentResolver cr = getContentResolver();
        int i = cr.delete(Uri.parse("content://aaa.bbb.ccc/teacher"), "name = ?", new String[] {"小莉莉"});
        Toast.makeText(this, "删除了" + i +  "条数据", Toast.LENGTH_SHORT).show();
    }

    //修改按钮
    public void update(View v) {
        ContentResolver cr = getContentResolver();
        ContentValues values = new ContentValues();
        values.put("name", "嘻嘻嘻");
        int i = cr.update(Uri.parse("content://aaa.bbb.ccc"), values,"name = ?", new String[] {"笑哈哈"});

        values.clear();

        Toast.makeText(this, "修改了" + i +  "条数据", Toast.LENGTH_SHORT).show();
    }

    public void query(View v) {
        ContentResolver cr = getContentResolver();
        Cursor cursor = cr.query(Uri.parse("content://aaa.bbb.ccc/person/4"), null, null, null, null);
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String salary = cursor.getString(cursor.getColumnIndex("salary"));
            Toast.makeText(this, name +";"+ salary, Toast.LENGTH_SHORT).show();
        }
    }
}

/**
 
 */
获取系统短信

	短信数据只看sms表即可

	sms表中，只看四个字段
		*address:对方号码
		*body:短信内容
		*date:发送或接收时间
		*type:短信类型，1为接收，2为发送

data/com.android.providers.teltphony/databass/mmssms.db

/**************************************************************************************************************/
	
	//权限
    <uses-permission android:name="android.permission.READ_SMS"></uses-permission>

    //获取系统短信
    public void click(View v) {
        ContentResolver cr = getContentResolver();
        Cursor cursor = cr.query(Uri.parse("content://sms"), new String[] {"address", "date", "type", "body"}, null, null, null);
        while (cursor.moveToNext()) {
            String address = cursor.getString(0);
            String date = cursor.getString(1);
            String type = cursor.getString(2);
            String body = cursor.getString(3);
            Toast.makeText(this, address + ";" + date + ";"+ type + ";" + body, Toast.LENGTH_SHORT).show();
        }
        cursor.close();
    }

/**
 
 */

备份系统短信
    <uses-permission android:name="android.permission.READ_SMS"></uses-permission>
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"></uses-permission>

/******************************************************************************************************/

public class Sms {
    private String address;
    private String body;
    private String type;
    private String data;

    public Sms(String address, String body, String type, String data) {
        this.address = address;
        this.body = body;
        this.type = type;
        this.data = data;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}

/******************************************************************************************************/

public class MainActivity extends AppCompatActivity {
    
    List<Sms> mSmsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSmsList = new ArrayList<>();
    }

    //读取系统短信
    public void click(View v) {
        ContentResolver cr = getContentResolver();
        Cursor cursor = cr.query(Uri.parse("content://sms"), new String[] {"address", "date", "type", "body"}, null, null, null);
        while (cursor.moveToNext()) {
            String address = cursor.getString(0);
            String date = cursor.getString(1);
            String type = cursor.getString(2);
            String body = cursor.getString(3);
            Sms sms = new Sms(address, body, type, date);
            mSmsList.add(sms);
        }
    }

    //备份系统短信
    public void click2(View v) {
        XmlSerializer xs = Xml.newSerializer();
        File file = new File("sdcard/sms.xml");
        FileOutputStream fos;

        try {
            //初始化xs
            fos = new FileOutputStream(file);
            xs.setOutput(fos, "utf-8");
            //开始生产xml文件
            xs.startDocument("utf-8", true);
            xs.startTag(null, "smss");
            for (Sms sms : mSmsList) {
                xs.startTag(null, "sms");

                xs.startTag(null, "address");
                xs.text(sms.getAddress());
                xs.endTag(null, "address");

                xs.startTag(null, "date");
                xs.text(sms.getData());
                xs.endTag(null, "date");

                xs.startTag(null, "body");
                xs.text(sms.getBody());
                xs.endTag(null, "body");

                xs.startTag(null, "type");
                xs.text(sms.getType());
                xs.endTag(null, "type");

                xs.endTag(null, "sms");
            }
            xs.endTag(null, "smss");
            xs.endDocument();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/**
 
 */
插入系统短信


	//把短信插入短信数据库
    public void click3(View v) {
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(8000);//延迟8秒
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //把短信插入短信数据库
                ContentResolver cr = getContentResolver();
                ContentValues values = new ContentValues();
                values.put("address", 95555);
                values.put("body", "您尾号XXX的招行储蓄卡收到转账");
                values.put("date", System.currentTimeMillis());
                values.put("type", 1);
                cr.insert(Uri.parse("content://sms"), values);
            }
        };
        t.start();
    }

/**
 
 */

联系人数据库

	*raw_contacts表
		*contact_id:联系人id 
		*拿着这个id去data表中查询属于该联系人的信息


	*data表：保存了所有联系人的信息，每条信息占一行
		*data1:保存联系人信息的相信内容
		*raw_contact_id:联系人id，表明此行信息属于哪个联系人
		*mimetype_id:表明信息的类型(是属于电话还是邮箱，或者姓名)

	*mimetypes表：通过mimetype_id在此表中找到对应的类型的名字


ContactsProvider2.java

/***************************************************************************************/

<uses-permission android:name="android.permission.READ_CONTACTS"></uses-permission>

/***************************************************************************************/

public class MainActivity extends AppCompatActivity {
    List<Person> mPersons;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSmsList = new ArrayList<>();
        mPersons = new ArrayList<>();
    }


    public void click4() {
        //访问内容提供者获取联系人数据
        ContentResolver cr = getContentResolver();
        Cursor cursor = cr.query(Uri.parse("content://com.android.contacts/raw_contacts"), new String[]{"contact_id"}, null, null, null);
        while (cursor.moveToNext()) {
            //拿到联系人id
            String contact_id = cursor.getString(0);
            //拿到联系人id再去查询data表，去除属于该联系人的信息
            Cursor cursor1 = cr.query(Uri.parse("content://com.android.contacts/data"), new String[]{"data1", "mimetype"},
                    "raw_contact_id = ?", new String[]{contact_id}, null);

            Person p = new Person();

            while (cursor1.moveToNext()) {
                String data1 = cursor1.getString(0);
                String mimetype = cursor1.getString(2);
                System.out.println(contact_id + ";" + data1 + ";" + mimetype);
                if ("vnd.android.cursor.item/email_v2".equals(mimetype)) {
                    p.setEmail(data1);
                } else if ("vnd.android.cursor.item/phone_v2".equals(mimetype)) {
                    p.setPhone(data1);
                } else if ("vnd.android.cursor.item/name".equals(mimetype)){
                    p.setName(data1);
                }
            }
            mPersons.add(p);

        }
        for (Person p : mPersons) {
            System.out.println(p.toString());
        }

    }
}

/***************************************************************************************/

public class Person {
    private String name;
    private String phone;
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

/**
 
 */

插入联系人信息

	*先插raw_contact表
		*把联系人id先插进去

	*再查data表:
		*data1
		*mimetype
		*raw_contact_id

/*************************************************************************************************/

    <uses-permission android:name="android.permission.READ_CONTACTS"></uses-permission>
    <uses-permission android:name="android.permission.WRITE_CALENDAR"></uses-permission>

/*************************************************************************************************/
    
    public void click5() {
        //插入联系人信息至联系人数据库
        ContentResolver cr = getContentResolver();
        //先查询raw_contacts表，去除最后一条联系人的主键，然后主键+1，得到新的联系人的id
        Cursor cursor = cr.query(Uri.parse("content://com.android.contacts/rwa_contacts"), new String[]{"id"}, null, null, null);
        int contactId = 0;
        if (cursor.moveToLast()) {
            int _id = cursor.getInt(cursor.getColumnIndex("_id"));
            //+1得到最新的联系人id
            contactId = ++_id;
        } else {
            contactId = 1;
        }

        ContentValues values = new ContentValues();
        values.put("contact_id", contactId);
        cr.insert(Uri.parse("content://com.android.contacts/rwa_contacts"), values);

        values.clear();
        values.put("data1", 123456);
        values.put("mimetype", "vnd.android.cursor.item/phone_v2");
        values.put("raw_contact_id", contactId);
        cr.insert(Uri.parse("content://com.android.contacts/data"), values);

        values.clear();
        values.put("data1", "二胖");
        values.put("mimetype", "vnd.android.cursor.item/name");
        values.put("raw_contact_id", contactId);
        cr.insert(Uri.parse("content://com.android.contacts/data"), values);

        values.clear();
        values.put("data1", "fangyi@gmail.com");
        values.put("mimetype", "vnd.android.cursor.item/email_v2");
        values.put("raw_contact_id", contactId);
        cr.insert(Uri.parse("content://com.android.contacts/data"), values);

    }
/**
 
 */


内容观察者
	
	contentObserver

使用内容观察者聆听短信内容提供者的叫唤

/******************************************************************************************/

自定义
一般很少去自己定义，都是使用别的人的

            //发通知给内容观察者：数据库内容改变啦
            //url：所有注册这个uri上的内容观察者都可以收到这条通知
            getContext().getContentResolver().notifyChange(uri,null);

/******************************************************************************************/

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //注册内容观察者
        ContentResolver cr = getContentResolver();
        //假设content://sms/inbox的数据改变，第二参数设置false,精确匹配，那么"content://sms"的内容观察者不会提示的
        //第二参数设置true,那么"content://sms"路径下的任何数据改变，都会被匹配
        cr.registerContentObserver(Uri.parse("content://sms"), true, new MyObserver(new Handler()));
    }

    class MyObserver extends ContentObserver {

        /**
         * Creates a content observer.
         *
         * @param handler The handler to run {@link #onChange} on, or null if none.
         */
        public MyObserver(Handler handler) {
            super(handler);
        }

        //收到内容改变时，此方法调用
        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            Toast.makeText(MainActivity.this, "短信数据库改变了", Toast.LENGTH_SHORT).show();
        }
    }
}

/**
 
 */
/**
 
 */
/**
 
 */

# 多媒体概念
* 文本、图片、音频、视频

---
# 图片的处理
* 图片的大小 = 图片的在那个像素 * 每个像素的大小

##BMP
* 单色：每个像素只能表示两种颜色，只需使用一个长度为1的二进制数字即可，那么一个像素占用1/8个字节
* 16色：每个像素只可以表示16种颜色，那么只需要16个数字，0-15， 0000-1111，长度4的二进制数字，那么每个像素占用1/2字节
* 256色：每个像素可以表示256中颜色，那么只需要256个数字，也就是0-255， 0000 0000 -1111 11111，长度为8的二进制数字，那么每个像素占用1个字节
* 24位：每个像素能表示的颜色，用一个24位的二进制数字表示，每个像素占用3个字节
    * RGB
    * R：红色，取值范围0 - 255
    * G：绿色，取值范围0 - 255
    * B：蓝色，取值范围0 - 255

* ARGB

    * A：alpha，透明度。取值范围0-255


#加载图片
* 图片像素过大，造成图片内存溢出
* 解决方案：先缩放图片，然后再加载
    * 图片总像素 7680000
    * 屏幕像素 153600
* 缩放比例
    * 图片宽度：2400*3200
    * 屏幕宽高：320*480 
    * 宽的缩放比例：24000/320 = 7
    * 高的缩放比例：3200/480 = 6
    * 缩放比例使用大的那个

#图片的特效处理
* 旋转
* 平移
* 缩放
* 镜面效果
* 倒影效果

#画画板

* 系统每次收到SD卡就绪广播时，都会去遍历sd卡的所有文件和文件夹，把遍历到的所有多媒体文件都在MediaStore数据库保存一个索引，这个索引包含多媒体文件的文件名、路径、大小
* 图库每次打开时，并不会去便利sd卡获取图片，而是通过内容提供者从MediaStore数据库中获取图片的信息，然后读取该图片

/**

 */

    //显示图片
    public void click(View v) {
        //不能直接加载，否则内存溢出
//        Bitmap bm = BitmapFactory.decodeFile("sdcard/dog.jpg");

        //加载图片时的可选项
        BitmapFactory.Options opt = new BitmapFactory.Options();
        //允许调用者去查询图片的属性，但是不为图片的像素分配内存
        opt.inJustDecodeBounds = true;
        BitmapFactory.decodeFile("sdcard/dog.jpg", opt);

        //获取图片宽高
        int imageWidth = opt.outWidth;
        int imageHeight = opt.outHeight;

        //获取屏幕宽高
        Display dp = getWindowManager().getDefaultDisplay();
        int sceenWidth = dp.getWidth();
        int sceenHeight = dp.getHeight();

        //计算缩放比例
        int scaleWidth = imageWidth / sceenWidth;
        int scaleHeight = imageHeight / sceenHeight;
        int scale = 1;

        //如果宽高缩放比例不一致，采用哪个
        if (scaleWidth >= scaleHeight && scaleWidth > 0) {
            scale = scaleWidth;
        } else if (scaleWidth < scaleHeight && scaleHeight >0) {
            scale = scaleHeight;
        }
        //设置缩放比例
        opt.inSampleSize = scale;
        opt.inJustDecodeBounds = false;
        Bitmap bm = BitmapFactory.decodeFile("sdcard/dog.jpg", opt);
        ImageView iv = (ImageView) findViewById(R.id.iv);
        iv.setImageBitmap(bm);

    }

/**
 
 */

创建图片的副本


    //创建图片的副本
    public void click2(View v) {
        //加载原图，这个对象是只读的
        Bitmap bmSrc = BitmapFactory.decodeFile("sdcard/photo3.jpg");

        //开始创建原图的拷贝
        //1.创建一个没有任何内容的bitmap对象，宽高与原图一致，比喻为一张跟原图一样大小的白纸，下一步在纸上作画，把原图画出来
        Bitmap bmCopy = Bitmap.createBitmap(bmSrc.getWidth(), bmSrc.getHeight(), bmSrc.getConfig());
        //2.创建画笔
        Paint paint = new Paint();
        //3.创建一个画板，把白纸铺在画板上
        Canvas canvas = new Canvas(bmCopy);
        //4.开始作画
        //bitmap:这是要写生的风景
        canvas.drawBitmap(bmSrc, new Matrix(), paint);

        ImageView iv_src = (ImageView) findViewById(R.id.iv_src);
        ImageView iv_copy = (ImageView) findViewById(R.id.iv_copy);
        iv_src.setImageBitmap(bmSrc);
        iv_copy.setImageBitmap(bmCopy);
    }


/**
 
 */

#图片的特效处理
* 旋转
* 平移
* 缩放
* 镜面效果
* 倒影效果

        //4.开始作画
        Matrix mt = new Matrix();

        /**
         * 旋转
         */
        //顺时针旋转45°，中心点在左上角0, 0
        mt.setRotate(45);
        //中心点在图片的右下角
        mt.setRotate(45, bmCopy.getWidth(), bmCopy.getHeight());
        //中心点是图片的中心点
        mt.setRotate(45, bmCopy.getWidth()/2 , bmCopy.getHeight()/2);

        /**
         * 平移
         */
        //往右平移10px，往下20px
        mt.setTranslate(10, 20);

        /**
         * 缩放
         */
        //X轴缩放0.5，Y轴放大2倍
        mt.setScale(0.5f, 2);

        /**
         * 镜面效果
         */
        mt.setScale(-1, 1);
        mt.postTranslate(bmCopy.getWidth(), 0);

        /**
         * 倒影效果
         */
        mt.setScale(1, -1);
        mt.postTranslate(0, bmCopy.getHeight());

        canvas.drawBitmap(bmSrc, mt, paint);

/**
 
 */

ImageView 的触摸事件

        //在图片上绘制直线
        paint.setColor(Color.RED);
        //前两个参数是起始坐标，后两个终点坐标
        canvas.drawLine(10, 10, 100, 100, paint);


/*********************************************************************************/

    //ImageView 的触摸事件
    private void click3(View v) {
        ImageView iv = (ImageView) findViewById(R.id.iv_painter);
        //设置触摸侦听
        iv.setOnTouchListener(new View.OnTouchListener() {
            //只要触摸事件产生，此方法调用
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //获取当前触摸事件的action
                int action = event.getAction();

                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        System.out.println("手指按下");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        System.out.println("手指滑动");
                        break;
                    case MotionEvent.ACTION_UP:
                        System.out.println("手指抬起");
                        break;
                }

                //如果设置为true，那么这个触摸事件由这个组件处理，如果设置为false，则此时间交给这个组件的父节点处理
                return true;
            }
        });
    }

/**

 */

画画板

    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"></uses-permission>

/*******************************************************************************************************/
    Bitmap bmCopy;
    Canvas canvas;
    Paint paint;
    ImageView iv;
    int startX;
    int startY;

    //ImageView 的触摸事件
    private void click3(View v) {

        //加载资源id的拖
        Bitmap bmSrc = BitmapFactory.decodeResource(getResources(), R.drawable.bg);

        //创建副本
        //1.创建大小与原图一模一样的白纸
        bmCopy = Bitmap.createBitmap(bmSrc.getWidth(), bmSrc.getHeight(), bmSrc.getConfig());
        //2.创建画笔
        paint = new Paint();
        //3.创建画板
        canvas = new Canvas(bmCopy);
        //4.把风景绘制到纸上
        canvas.drawBitmap(bmSrc, new Matrix(), paint);

        iv = (ImageView) findViewById(R.id.iv_painter);
        iv.setImageBitmap(bmCopy);
        //设置触摸侦听
        iv.setOnTouchListener(new View.OnTouchListener() {
            //只要触摸事件产生，此方法调用
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //获取当前触摸事件的action
                int action = event.getAction();

                switch (action) {
                    //手指按下
                    case MotionEvent.ACTION_DOWN:
                        startX = (int) event.getX();
                        startY = (int) event.getY();
                        break;
                    //手指滑动
                    case MotionEvent.ACTION_MOVE:
                        int newstartX = (int) event.getX();
                        int newstartY = (int) event.getY();
                        canvas.drawLine(startX, startY, newstartX, newstartY, paint);
                        startX = newstartX;
                        startY = newstartY;
                        //重新设置iv显示副本
                        iv.setImageBitmap(bmCopy);
                        break;
                    case MotionEvent.ACTION_UP:
                        System.out.println("手指抬起");
                        break;
                }

                //如果设置为true，那么这个触摸事件由这个组件处理，如果设置为false，则此时间交给这个组件的父节点处理
                return true;
            }
        });
    }


    public void red(View v) {
        paint.setColor(Color.RED);
    }

    public void green(View v) {
        paint.setColor(Color.GREEN);

    }
    public void brush(View v) {
        paint.setStrokeWidth(7);
    }

    public void save(View v) {
        File file = new File("sdcard/dazou.png");
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        bmCopy.compress(Bitmap.CompressFormat.PNG, 100, fos);

        //手动发送sd卡就绪广播
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_MEDIA_MOUNTED);
        intent.setData(Uri.fromFile(Environment.getExternalStorageDirectory()));
        sendBroadcast(intent);
    }

/**

 */

撕衣服应用

/**************************************************************************************************/

<ImageView
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:src="@drawable/img_11"/>

<ImageView
android:id="@+id/iv"
android:layout_width="wrap_content"
android:layout_height="wrap_content"/>


/**************************************************************************************************/

package com.example.fangyi.syifu;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    Bitmap bmSrc;
    Bitmap bmCopy;
    Paint paint;
    Canvas canvas;
    ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bmSrc = BitmapFactory.decodeResource(getResources(), R.drawable.img_12);
        bmCopy = Bitmap.createBitmap(bmSrc.getWidth(), bmSrc.getHeight(), bmSrc.getConfig());

        paint = new Paint();

        canvas = new Canvas(bmCopy);

        canvas.drawBitmap(bmSrc, new Matrix(), paint);

        iv = (ImageView) findViewById(R.id.iv);
        iv.setImageBitmap(bmCopy);
        iv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_MOVE:
                        int x = (int) event.getX();
                        int y = (int) event.getY();
                        //把指定坐标的像素设置为某个颜色
                        for (int i= -50; i<50; i++) {
                            for (int j= -50; j<50; j++) {

                                //计算某个像素是否在圆内或圆上
                                if (Math.sqrt(j*j + i*i) <= 50) {
                                    System.out.println("移动");
                                    bmCopy.setPixel(x + i, y + j, Color.TRANSPARENT);
                                }
                            }
                        }
                        iv.setImageBitmap(bmCopy);
                        break;
                }
                return true;
            }
        });
    }
}

/**
 
 */
/**
 
 */

音乐播放器的实现

/****************************************************************************************************/

<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
          package="com.example.fangyi.music">
    <uses-permission android:name="android.permission.INTERNET"></uses-permission>

    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:supportsRtl="true"
        android:theme="@style/AppTheme">
        <activity android:name=".MainActivity">
            <intent-filter>
                <action android:name="android.intent.action.MAIN"/>

                <category android:name="android.intent.category.LAUNCHER"/>
            </intent-filter>
        </activity>
        <service android:name=".MusicService"></service>
    </application>

</manifest>

/****************************************************************************************************/

<?xml version="1.0" encoding="utf-8"?>
<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:orientation="vertical"
    android:layout_height="match_parent"
    android:paddingBottom="@dimen/activity_vertical_margin"
    android:paddingLeft="@dimen/activity_horizontal_margin"
    android:paddingRight="@dimen/activity_horizontal_margin"
    android:paddingTop="@dimen/activity_vertical_margin"
    tools:context="com.example.fangyi.music.MainActivity">

    <Button
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="开始播放"
        android:onClick="play"/>

    <Button
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="暂停播放"
        android:onClick="pause"/>

    <Button
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="继续播放"
        android:onClick="continuePlay"/>

    <Button
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="停止播放"
        android:onClick="stop"/>

    <SeekBar
        android:id="@+id/sb"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"/>
</LinearLayout>

/****************************************************************************************************/

package com.example.fangyi.music;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {
    private MusicInterface mi;
    public static SeekBar sb;

    public static Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            int duration = bundle.getInt("duration");
            int currentPosition = bundle.getInt("currentPosition");

            sb.setMax(duration);
            sb.setProgress(currentPosition);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sb = (SeekBar) findViewById(R.id.sb);
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            //当手指拖动进度条圆圈，此方法调用
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }
            //当手指按下进度条圆圈，此方法调用
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            //当手指离开进度条圆圈，此方法调用
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int progress = seekBar.getProgress();
                mi.seekTo(progress);
            }
        });


        Intent intent = new Intent(this, MusicService.class);
        startService(intent);
        bindService(intent, new MyServiceConn(), BIND_AUTO_CREATE);
    }

    private class MyServiceConn implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mi = (MusicInterface) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }

    public void play(View v) {
        mi.play();
    }

    public void pause(View v) {
        mi.pause();
    }

    public void continuePlay(View v) {
        mi.continuePlay();
    }

    public void stop(View v) {
        mi.stop();
    }

}

/****************************************************************************************************/

package com.example.fangyi.music;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by FANGYI on 2016/5/21.
 */
public class MusicService extends Service {

    MediaPlayer player;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MusicBinder();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        player = new MediaPlayer();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //此方法调用，player就不能用了，所以卸载摧毁方法里，摧毁方法一调用就说明用户退出了播放器
        player.release();
        player = null;

    }

    class MusicBinder extends Binder implements MusicInterface{
        public void play() {
            MusicService.this.play();
        }

        public void pause() {
            MusicService.this.pause();
        }
        public void continuePlay() {
            MusicService.this.continuePlay();
        }
        public void stop() {
            MusicService.this.stop();
        }

        public void seekTo(int progress) {
            MusicService.this.seekTo(progress);
        }
    }

    private void play() {
        player.reset();
        try {
            player.setDataSource("http://m1.music.126.net/4Dyp0YZUPYmojzG_muvc3A==/1377688080392626.mp3");
            //同步准备
//            player.prepare();
            //异步准备
            player.prepareAsync();
            //设置准备侦听，知道子线程何时准备完毕
            player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                //准备完毕时调用
                @Override
                public void onPrepared(MediaPlayer mp) {
                    player.start();
                    addSeekBar();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
//        player.start();
    }

    private void pause() {
        player.pause();
    }

    private void continuePlay() {
        player.start();

    }

    private void stop() {
        player.stop();
    }

    /**
     * 显示进度条
     */
    private void addSeekBar() {

        //使用计时器去不断的执行获取播放进度的代码
        Timer timer = new Timer();
        //设置计时任务
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //获取当前曲目的持续时间
                //返回处理歌曲的毫秒值
                int duration = player.getDuration();
                //获取当前的播放进度
                //当前播放的位置
                int currentPosition = player.getCurrentPosition();
                Message msg = MainActivity.mHandler.obtainMessage();
                //搭载信息
                Bundle bundle = new Bundle();
                bundle.putInt("duration", duration);
                bundle.putInt("currentPosition", currentPosition);
                msg.setData(bundle);
                //发送消息让进度条更新
                MainActivity.mHandler.sendMessage(msg);
            }
            //参数，5毫秒开始执行任务，每500毫秒执行一次任务
        }, 5, 500);
    }

    /**
     * 拖拽进度条更改播放时间
     */
    private void seekTo(int progress) {
        player.seekTo(progress);
    }
}

/****************************************************************************************************/

package com.example.fangyi.music;

/**
 * Created by FANGYI on 2016/5/21.
 */
public interface MusicInterface {

    void play();
    void pause();
    void continuePlay();
    void stop();
    void seekTo(int progress);
}

/**
 
 */
/**
 
 */

#SurfaceView
* 双缓冲技术
	*内存中有两个界面，一个显示在前台，一个在后台，刷新界面时，后台界面覆盖前台

* 对于画面实时更新要求高的需求，就会使用这个组件
* SurfaceView 是一个重量级组件
* 当 SurfaceView 被用户看见时，才创建
* SurfaceView 一旦不可见，就会被销毁，一旦可见，就会创建


public class MainActivity extends AppCompatActivity {
    private MediaPlayer player;
    static int currentPosition = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SurfaceView sv = (SurfaceView) findViewById(R.id.sv);
        //拿到surfaceview的控制器
        final SurfaceHolder sh = sv.getHolder();
//
//        Thread t = new Thread() {
//            @Override
//            public void run() {
//                try {
//                    sleep(200);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//                //在主线程中运行一个Runnable
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        MediaPlayer player = new MediaPlayer();
//                        player.reset();
//                        try {
//                            player.setDataSource("http://us.sinaimg.cn/003fwLaujx071RAwwcpx05040100a1Im0k01.mp4?KID=unistore,video&ssig=6cDbuY1ETD&Expires=1463842798");
//                            //设置把视频显示在那个sufaceView上
//                            player.setDisplay(sh);
//                            player.prepare();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                        player.start();
//                    }
//                });
//            }
//        };
//        t.start();

        sh.addCallback(new SurfaceHolder.Callback() {

            //sufaceView创建时调用
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                if (player == null) {
                    player = new MediaPlayer();
                    player.reset();
                    try {
                        player.setDataSource("http://us.sinaimg.cn/003fwLaujx071RAwwcpx05040100a1Im0k01.mp4?KID=unistore,video&ssig=6cDbuY1ETD&Expires=1463842798");
                        //设置把视频显示在那个sufaceView上
                        player.setDisplay(sh);
                        player.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    player.start();
                    //把播放进度设置到上一次退出时的位置
                    player.seekTo(currentPosition);
                }

            }
            //sufaceView结构改变时调用
            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }
            //sufaceView销毁时调用
            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                if (player != null) {
                    //每次退出时，获取当前播放的时间
                    currentPosition = player.getCurrentPosition();
                    player.stop();
                    player.release();
                    player = null;
                }
            }
        });


    }
}

/**
 
 */

#Vitamio框架

* VideoView
* 检查是否兼容Vitamo引擎，和安装
    * if（!LibsChecker.checkVitamioLibs(this)）{return;}
* 清单文件中要配置
	* 用来检测的
	* <activity android:name="io.vov.vitamio.activity.InitActivity"/>

/**************************************************************************/
<io.vov.vitamio.widget.VideoView
	android:id="@+id/vv"
 	android:layout_width="match_parent"
    android:layout_height="match_parent"/>

/**************************************************************************/
final VideoView vv = (VideoView)findViewById(R.id.vv);
vv.setVideoPath("");
vv.setOnPreparedListener(new OnPreparedListener() {
	public void onPrepared(MediaPlayer mp) {
		vv.start();
	}
});
vv.setMediaVontroller(new MediaController(this));

/**
 
 */
摄像头的使用

http://www.android-doc.com/guide/topics/media/camera.html

/***************************************************************************************/
拍照
package com.example.fangyi.video;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    private MediaPlayer player;
    static int currentPosition = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void click1(View v) {
        // create Intent to take a picture and return control to the calling application
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //指定照片保存的路径和文件名
        intent.putExtra(MediaStore.EXTRA_OUTPUT, new File(Environment.getExternalStorageDirectory(), "haha.jpg")); // set the image file name

        // start the image capture Intent
        startActivityForResult(intent, 1);
    }

    public void click2(View v) {
        // create Intent to take a picture and return control to the calling application
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        //指定视频保存的路径和文件名
        intent.putExtra(MediaStore.EXTRA_OUTPUT, new File(Environment.getExternalStorageDirectory(), "haha.3gp")); // set the image file name
        //指定视频的质量
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1); // set the video image quality to high
        // start the image capture Intent
        startActivityForResult(intent, 2);
    }

    @Override
    public void onActivityReenter(int resultCode, Intent data) {
        super.onActivityReenter(resultCode, data);
        if (resultCode == 1) {
            Toast.makeText(this, "拍照成功", Toast.LENGTH_SHORT).show();
        } else if (resultCode == 2) {
            Toast.makeText(this, "摄像成功", Toast.LENGTH_SHORT).show();
        }
    }
}


	

/**
 
 */
/**
 
 */
/**
 
 */

Framgent 实现过程


/*********************************************************************************************/
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click1(View v) {
        //把Fragment显示至屏幕
        //1.创建Fragment的对象
        Framgent01 fg1 = new Framgent01();
        //2.获取Fragment管理器
        FragmentManager fm = getFragmentManager();
        //3.开启事务
        FragmentTransaction ft = fm.beginTransaction();
        //4.把Fragment显示至界面
        ft.replace(R.id.fl, fg1);
        //5.提交
        ft.commit();

    }

    public void click2(View v) {
        Framgent02 fg2 = new Framgent02();
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fl, fg2);
        ft.commit();
    }

    public void click3(View v) {
        Framgent03 fg3 = new Framgent03();
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fl, fg3);
        ft.commit();
    }
}

/*********************************************************************************************/

public class Framgent01 extends Fragment {
    //此方法返回的View对象就是该Fragment显示的内容
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = View.inflate(getActivity(), R.layout.framgent01, null);
        return v;
    }
}

/*********************************************************************************************/
activity_main

    <LinearLayout
        android:layout_width="100dp"
        android:layout_height="match_parent"
        android:orientation="vertical">
        <Button
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="界面1"
            android:onClick="click1"/>
        <Button
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="界面2"
            android:onClick="click2"/>
        <Button
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="界面3"
            android:onClick="click3"/>

    </LinearLayout>

    <FrameLayout
        android:id="@+id/fl"
        android:layout_width="match_parent"
        android:layout_height="match_parent">

    </FrameLayout>

/*********************************************************************************************/

fragment01


<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
              android:orientation="vertical"
              android:background="#EBCAFE"
              android:layout_width="match_parent"
              android:layout_height="match_parent">

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="这是紫色"
        android:textSize="30sp"/>

</LinearLayout>

/**
 
 */

传递数据



package com.example.fangyi.fragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Framgent01 fg1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fg1 = new Framgent01();
        //2.获取Fragment管理器
        FragmentManager fm = getFragmentManager();
        //3.开启事务
        FragmentTransaction ft = fm.beginTransaction();
        //4.把Fragment显示至界面
        ft.replace(R.id.fl, fg1);
        //5.提交
        ft.commit();
    }

    public void click1(View v) {
        //把Fragment显示至屏幕
        //1.创建Fragment的对象
//      Framgent01 fg1 = new Framgent01();
        //2.获取Fragment管理器
        FragmentManager fm = getFragmentManager();
        //3.开启事务
        FragmentTransaction ft = fm.beginTransaction();
        //4.把Fragment显示至界面
        ft.replace(R.id.fl, fg1);
        //5.提交
        ft.commit();
    }

    public void click2(View v) {
        //把Fragment显示至屏幕
        //1.创建Fragment的对象
        Framgent02 fg2 = new Framgent02();
        //2.获取Fragment管理器
        FragmentManager fm = getFragmentManager();
        //3.开启事务
        FragmentTransaction ft = fm.beginTransaction();
        //4.把Fragment显示至界面
        ft.replace(R.id.fl, fg2);
        //5.提交
        ft.commit();
    }

    public void click3(View v) {
        //把Fragment显示至屏幕
        //1.创建Fragment的对象
        Framgent03 fg3 = new Framgent03();
        //2.获取Fragment管理器
        FragmentManager fm = getFragmentManager();
        //3.开启事务
        FragmentTransaction ft = fm.beginTransaction();
        //4.把Fragment显示至界面
        ft.replace(R.id.fl, fg3);
        //5.提交
        ft.commit();
    }

    public void commit(View v) {
        EditText et = (EditText) findViewById(R.id.et);
        String text = et.getText().toString();
        fg1.setText(text);
    }

    public void setText(String text) {
        //此方法运行在02中
        EditText et = (EditText) findViewById(R.id.et);
        et.setText(text);
    }
}


/**************************************************************************************************/

public class Framgent01 extends Fragment {
    TextView tv;

    //此方法返回的View对象就是该Fragment显示的内容
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = View.inflate(getActivity(), R.layout.framgent01, null);
        tv = (TextView) v.findViewById(R.id.tv);
        return v;
    }

    public void setText(String text) {
        tv.setText(text);
    }
}

/**************************************************************************************************/

public class Framgent02 extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = View.inflate(getActivity(), R.layout.framgent02, null);
        final EditText et = (EditText) v.findViewById(R.id.et);
        Button bt = (Button) v.findViewById(R.id.bt);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = et.getText().toString();
                ((MainActivity)getActivity()).setText(text);
            }
        });
        return v;
    }
}

/**************************************************************************************************/

<LinearLayout
        android:layout_width="100dp"
        android:layout_height="match_parent"
        android:orientation="vertical">
        <Button
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="界面1"
            android:onClick="click1"/>
        <Button
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="界面2"
            android:onClick="click2"/>
        <Button
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="界面3"
            android:onClick="click3"/>
        <EditText
            android:id="@+id/et"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:hint="填写数据"/>

        <Button
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="提交"
            android:onClick="commit"/>

    </LinearLayout>

    <FrameLayout
        android:id="@+id/fl"
        android:layout_width="match_parent"
        android:layout_height="match_parent">

    </FrameLayout>

/**************************************************************************************************/

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="这是紫色"
        android:textSize="30sp"/>

    <TextView
        android:id="@+id/tv"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:textSize="18sp"/>

/**************************************************************************************************/
    
    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="这是绿色"
        android:textSize="30sp"/>

    <EditText
        android:id="@+id/et"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:hint="需要提交的数据"
        android:textSize="20sp"/>
    <Button
        android:id="@+id/bt"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="提交"/>

/**
 
 */
/**
 
 */


帧动画

http://www.android-doc.com/guide/topics/graphics/drawable-animation.html

/************************************************************************************************/
1.rocket_thrust.xml

android:oneshot="true" true 播放一次,false 重复播放

<animation-list xmlns:android="http://schemas.android.com/apk/res/android"
    android:oneshot="true">
    <item android:drawable="@drawable/rocket_thrust1" android:duration="200" />
    <item android:drawable="@drawable/rocket_thrust2" android:duration="200" />
    <item android:drawable="@drawable/rocket_thrust3" android:duration="200" />
</animation-list>

/************************************************************************************************/

2.

AnimationDrawable rocketAnimation;


public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.main);

  ImageView rocketImage = (ImageView) findViewById(R.id.rocket_image);
  //通过资源id设置要显示的背景图
  rocketImage.setBackgroundResource(R.drawable.rocket_thrust);
  //获取rocketImage的背景图，其实是个动画
  rocketAnimation = (AnimationDrawable) rocketImage.getBackground();
}

public boolean onTouchEvent(MotionEvent event) {
  if (event.getAction() == MotionEvent.ACTION_DOWN) {
    rocketAnimation.start();
    return true;
  }
  return super.onTouchEvent(event);
}

/**
 
 */

#补间动画


* 旧形态向新形态变形时，为了让过程过度平滑自然而生成的动画



##平移


        TranslateAnimation ta = new TranslateAnimation(10, 50, 20 , -60);

* 10：代表x的开始坐标：真实x+10
* 50：代表x的结束坐标：真实x+50
* 20：代表y的开始坐标：真实y+20
* -60：代表y的开始坐标：真实y-60


        TranslateAnimation ta = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, (float) 0, Animation.RELATIVE_TO_SELF, 2,
                Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 2);

*  Animation.RELATIVE_TO_SELF, 0：代表x开始坐标
    * 真实x + 0 * iv自身宽度
*  Animation.RELATIVE_TO_SELF, 2：代表x结束坐标
    * 真实x + 2 * iv自身宽度


##缩放

       ScaleAnimation sa = new ScaleAnimation(0.1f, 2, 3, 0.5f);

* 0.1f：代表x轴的开始比例
* 2：代表x轴的结束比例
* 3：代表y轴的开始比例
* 0.5f：代表x轴的结束比例


        ScaleAnimation sa = new ScaleAnimation(0.1f, 2, 2, 0.5f, iv.getWidth() / 2, iv.getHeight() / 2);

* iv.getWidth() / 2：代表缩放中心点的x坐标
    * 真实y + iv.getWidth() / 2
* iv.getHeight() / 2：代表缩放中心电的y坐标
    * 真实x + iv.getHeight() / 2


    ScaleAnimation sa = new ScaleAnimation(0.1f, 2, 2, 0.5f,Animation.RELATIVE_TO_SELF, 1, Animation.RELATIVE_TO_SELF, 0.5f);

*  Animation.RELATIVE_TO_SELF, 0.5f：代表x开始坐标
    * 真实x + iv自身宽度 * 1
*  Animation.RELATIVE_TO_SELF, 0.5f：代表x结束坐标
    * 真实x + iv自身高度 * 0.5

##透明

    AlphaAnimation aa = new AlphaAnimation(0, 1);


给控件用

    一是, 参数为int类型

    titleHeadIv.setAlpha(0~255);


    二是,参数为float类型

    titleHeadIv.setAlpha(0f~1f);


    三是,setImageAlpha(int),这个方法是较高api的, 不做使用


    
* 起始透明度0
* 终点透明度 1

##旋转
    RotateAnimation ra = new RotateAnimation(20, 360);
    RotateAnimation ra = new RotateAnimation(20, 360, iv.getWidth() / 2, iv.getHeight() / 2);
    RotateAnimation ra = new RotateAnimation(20, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

##集合
    public void fly(View v) {
        //动画集合
        AnimationSet as = new AnimationSet(false);
        //添加动画
        as.addAnimation(mTa);
        as.addAnimation(mAa);
        as.addAnimation(mRa);
        as.addAnimation(mSa);
        iv.startAnimation(as);
    }


            //动画播放两秒
        mTa.setDuration(2000);

/**
 
 */

属性动画

package com.example.fangyi.bujian;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageView iv;
    private ScaleAnimation mSa;
    private TranslateAnimation mTa;
    private AlphaAnimation mAa;
    private RotateAnimation mRa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv = (ImageView) findViewById(R.id.iv);
        iv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Toast.makeText(MainActivity.this, "你点不到我", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    public void translate1(View v) {
        //创建补间动画对象
//        TranslateAnimation ta = new TranslateAnimation(10, 50, 20 , 60);
        //使用相对的方式来定义位移的坐标,RELATIVE_TO_SELF自身的，后面的参数是  真实x + (自身*参数)
        mTa = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, (float) 0, Animation.RELATIVE_TO_SELF, 2,
                Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 2);
        //动画播放两秒
        mTa.setDuration(2000);
        iv.startAnimation(mTa);

    }

    public void scale1(View v) {
        mSa = new ScaleAnimation(0.1f, 2, 2, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        mSa.setDuration(2000);
        //重复播放
        mSa.setRepeatCount(1);
        //重复播放模式
        mSa.setRepeatMode(Animation.REVERSE);
        //填充结束位置，也就是结束位置把组件绘制上去
        mSa.setFillAfter(true);
        //
//        sa.setFillBefore(true);
        iv.startAnimation(mSa);
    }

    public void alpha1(View v) {
        mAa = new AlphaAnimation(0, 1);
        mAa.setDuration(2000);

        mAa.setRepeatCount(1);
        //重复播放模式
        mAa.setRepeatMode(Animation.REVERSE);
        //填充结束位置，也就是结束位置把组件绘制上去
        mAa.setFillAfter(true);

        iv.startAnimation(mAa);

    }

    public void rotato1(View v) {
        //起始20°，旋转360°
//        RotateAnimation ra = new RotateAnimation(20, 360);
//        RotateAnimation ra = new RotateAnimation(20, 360, iv.getWidth() / 2, iv.getHeight() / 2);
        mRa = new RotateAnimation(20, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

        mRa.setDuration(2000);
        iv.startAnimation(mRa);

    }

    public void fly1(View v) {
        //动画集合
        AnimationSet as = new AnimationSet(false);
        //添加动画
        as.addAnimation(mTa);
        as.addAnimation(mAa);
        as.addAnimation(mRa);
        as.addAnimation(mSa);
        iv.startAnimation(as);
    }

    public void translate2(View v) {
        //创建属性动画
        //改变的是真实值的坐标
        ObjectAnimator oa = ObjectAnimator.ofFloat(iv, "translationX", 10, -50, 20, 200);
        oa.setDuration(2000);
        oa.start();

    }

    public void scale2(View v) {
        ObjectAnimator oa = ObjectAnimator.ofFloat(iv, "scaleY", 0.1f, 2, 1, 2);
        oa.setDuration(2000);
        oa.start();
    }

    public void alpha2(View v) {
        ObjectAnimator oa = ObjectAnimator.ofFloat(iv, "alpha", 0, 0.5f, 0 , 1);
        oa.setDuration(2000);
        oa.start();

    }

    public void rotato2(View v) {
        ObjectAnimator oa = ObjectAnimator.ofFloat(iv, "rotation", 0, 180, 90, 360);
        ObjectAnimator oax = ObjectAnimator.ofFloat(iv, "rotationX", 0, 180, 90, 360);
        ObjectAnimator oay = ObjectAnimator.ofFloat(iv, "rotationY", 0, 180, 90, 360);
        oa.setDuration(2000);
        oa.start();
        oax.setDuration(2000);
        oax.start();
        oay.setDuration(2000);
        oay.start();
    }

    public void fly2(View v) {
        AnimatorSet as = new AnimatorSet();

        ObjectAnimator oa = ObjectAnimator.ofFloat(iv, "translationX", 10, -50, 20, 200);
        ObjectAnimator oa2 = ObjectAnimator.ofFloat(iv, "scaleY", 0.1f, 2, 1, 2);
        ObjectAnimator oa3 = ObjectAnimator.ofFloat(iv, "alpha", 0, 0.5f, 0 , 1);
        ObjectAnimator oa4 = ObjectAnimator.ofFloat(iv, "rotation", 0, 180, 90, 360);


        as.setDuration(2000);
        as.setTarget(iv);
        //往集合中添加动画
        //挨个飞
//        as.playSequentially(oa, oa2, oa3, oa4);
        //一起飞
        as.playTogether(oa, oa2, oa3, oa4);
        as.start();
    }
}

/**
 
 */



用xml文件定义属性动画


/**
 
 */

对话框


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    //确定取消对话框
    public void click1(View v) {
        //创建对话框创建者对象
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //用创建者对象初始化对话框的各种属性
        builder.setTitle("葵花宝典");
        builder.setMessage("欲练此功，必先自宫");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "感谢使用本软件，再见", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "若不自宫，一定不成功", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog ad = builder.create();
        ad.show();

    }

    //单选对话框
    public void click2(View v) {
        //创建对话框创建者对象
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //用创建者对象初始化对话框的各种属性
        builder.setTitle("选择你的性别");
        final String[] items = new String[] {
                "男",
                "女",
                "小胖"
        };
        //设置单选条目
        builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
            //which：用户点击的条目索引
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, items[which], Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        builder.show();
    }

    //多选对话框
    public void click3(View v) {
        //创建对话框创建者对象
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //用创建者对象初始化对话框的各种属性
        builder.setTitle("你喜欢吃什么水果");
        final String[] items = new String[] {
                "苹果",
                "鸭梨",
                "橘子",
                "香蕉",
                "黄瓜"
        };
        final boolean[] checkedItems = new boolean[] {
                true,
                true,
                false,
                false,
                false
        };

       builder.setMultiChoiceItems(items, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
           @Override
           public void onClick(DialogInterface dialog, int which, boolean isChecked) {
               //根据用户的选择改变记录条目是否被选中的boolean数组值
               checkedItems[which] = isChecked;
           }
       });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String s = "";
                for (int i = 0; i < items.length; i++) {
                    s = s + (checkedItems[i]? items[i] + "," : "");
                }
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();

            }
        });
        builder.show();
    }
}


/**
 
 */

#样式和主题
* 样式：用于普通的组件
* 主题：用于 application 和 Activity 节点

/*********************************************************************************************/

    <style name="JiangNanStyle">
        <item name="android:layout_width">wrap_content</item>
        <item name="android:layout_height">wrap_content</item>
        <item name="android:textSize">18sp</item>
        <item name="android:textColor">@android:color/holo_red_dark</item>
    </style>

    <style name="JiangBeiStyle" parent="JiangNanStyle">
        <item name="android:textSize">25sp</item>
        <item name="android:textColor">@android:color/holo_blue_bright</item>
    </style>

    <style name="JiangBeiStyle.JiangDongStyle">
        <item name="android:textSize">35sp</item>
    </style>

/*********************************************************************************************/
   
    <TextView
        style="@style/JiangNanStyle"
        android:text="`(*∩_∩*)′"/>

    <TextView
        style="@style/JiangBeiStyle"
        android:text="o(*≧▽≦)ツ"/>

    <TextView
        style="@style/JiangBeiStyle.JiangDongStyle"
        android:text="o(*≧▽≦)ツ"/>

/**
 
 */

国际化

/**
 * 字符串国际化
 */
	values-en-rGB//英国
	values-en-rUS//美国
	values-it//意大利
	values-zh//中国
	values-en//英语世界
/**
 * 图片国际化
 */
	drawable-en-rGB//英国
	drawable-en-rGB//美国
	
	drawable //需要有一个默认的

/**
 
 */

md5加密

public class MD5 {
    public static void main(String[] args) {
        //md5加密
        //1.信息摘要器
        try {
            MessageDigest digest = MessageDigest.getInstance("md5");
            String password = "12356";
            //2.变成byte数组
            byte bytes[] = digest.digest(password.getBytes());
            StringBuffer buffer = new StringBuffer();

            //3.每一个byte个和8个二进制位做与运算
            for (byte b : bytes) {
                int number = b & 0xff;//加盐0xfff
                
                //4.把int类型转换成十六进制
                String numberStr = Integer.toHexString(number);
                System.out.println();
                //5.不足的补全
                if (numberStr.length() == 1) {
                    buffer.append("0");
                }
                
                buffer.append(numberStr);
            
            }
//          buffer.toString();标准的md5加密后的结果
            System.out.println(buffer.toString());
   
        } catch (NoSuchAlgorithmException e) {
            //没有预先准备的说法异常
            e.printStackTrace();
        }
    }


/**
 



 */

自定义ProgressBar简单完成颜色渐变功能进度条

样式，有外框 两头圆角，有渐变色的


    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_centerVertical="true"
        android:layout_marginLeft="50dp"
        android:layout_marginRight="80dp"
        android:background="@drawable/shape_progressbar">

        <ProgressBar
            android:id="@+id/progressBar"
            style="@android:style/Widget.ProgressBar.Horizontal"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_centerVertical="true"
            android:layout_margin="3dp"

            android:max="100"
            android:progress="50"
            android:progressDrawable="@drawable/my_progress"/>
    </LinearLayout>


--------------------------------------------------------------------------------------------------------
my_progress.xml


<?xml version="1.0" encoding="utf-8"?>
<layer-list xmlns:android="http://schemas.android.com/apk/res/android">

    <!--<item android:id="@android:id/background">-->
    <!--<shape>-->
    <!--<corners android:radius="5dip" />-->
    <!--<gradient-->
    <!--android:startColor="#ff9d9e9d"-->
    <!--android:centerColor="#ff5a5d5a"-->
    <!--android:centerY="0.75"-->
    <!--android:endColor="#ff747674"-->
    <!--android:angle="270"-->
    <!--/>-->
    <!--</shape>-->
    <!--</item>-->

    <!--<item android:id="@android:id/secondaryProgress">-->
    <!--<clip>-->
    <!--<shape>-->
    <!--<corners android:radius="5dip" />-->
    <!--<gradient-->
    <!--android:startColor="#80ffd300"-->
    <!--android:centerColor="#80ffb600"-->
    <!--android:centerY="0.75"-->
    <!--android:endColor="#a0ffcb00"-->
    <!--android:angle="270"-->
    <!--/>-->
    <!--</shape>-->
    <!--</clip>-->
    <!--</item>-->

    <item android:id="@android:id/progress">
        <clip>
            <shape>
                <corners android:radius="360dip"/>

                <solid android:color="#49ACFD"/>

                <stroke
                    android:width="1dp"
                    android:color="#49ACFD"/>

                <gradient
                    android:angle="0"
                    android:centerY="0.75"
                    android:endColor="#49ACFD"
                    android:startColor="#8CC5EE"
                    />
            </shape>
        </clip>
    </item>

--------------------------------------------------------------------------------------------------------


shape_progressbar.xml

<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android">
    <corners android:radius="360dp"/>
    <solid android:color="#ffffff"/>
    <stroke
        android:width="1dp"
        android:color="#f55c18"/>
</shape>



/**
 
 */

1.第一个widget被创建的生命周期

onReceive   每个方法都用刀onReceive，通讯就用广播
onEnabled   当第一个widget被创建的时候执行，适合初始化资源，比如创建服务
onReceive
onUpdate    每新创建一个widget都被执行，系统默认更新时间30分，时间到了也会执行
onReceive
onAppWidgetOptionsChanged   每新创建一个widget都被执行

2.第二个widget被创建的生命周期

onReceive
onUpdate
onReceive
onAppWidgetOptionsChanged

3.第三个widget被创建的生命周期

同样


1.删除第一个widget

onReceive
onDeleted   只要删除任意一个widget

2.删除最后一个widget

onReceive
onDeleted
onReceive
onDisabled 只要删除任意一个widget就会执行，适合释放资源，比如停止服务

/**




 */

/**
 * 流量统计
 *
 * 起始点是本次开机 而不是第一次开机
 */

public class TrafficManagerActivity extends AppCompatActivity {

    static long  getMobileRxBytes()  //获取通过Mobile连接收到的字节总数，不包含WiFi  
    static long  getMobileRxPackets()  //获取Mobile连接收到的数据包总数  
    static long  getMobileTxBytes()  //Mobile发送的总字节数  
    static long  getMobileTxPackets()  //Mobile发送的总数据包数  
    static long  getTotalRxBytes()  //获取总的接受字节数，包含Mobile和WiFi等  
    static long  getTotalRxPackets()  //总的接受数据包数，包含Mobile和WiFi等  
    static long  getTotalTxBytes()  //总的发送字节数，包含Mobile和WiFi等  
    static long  getTotalTxPackets()  //发送的总数据包数，包含Mobile和WiFi等   
    static long  getUidRxBytes(int uid)  //获取某个网络UID的接受字节数  
    static long  getUidTxBytes(int uid) //获取某个网络UID的发送字节数  

    int uid;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<AppInfo> mAppInfos = new ArrayList<>();
        //包管理器
        PackageManager pm = getPackageManager();
        List<PackageInfo> packageInfos = pm.getInstalledPackages(0);

        for (PackageInfo packageInfo : packageInfos) {
            AppInfo appInfo = new AppInfo();

            String packName = packageInfo.packageName;
            appInfo.setPackName(packName);

            Drawable icon = packageInfo.applicationInfo.loadIcon(pm);
            appInfo.setIcon(icon);

            String name = packageInfo.applicationInfo.loadLabel(pm).toString();
            appInfo.setName(name);

            uid = packageInfo.applicationInfo.uid;

            mAppInfos.add(appInfo);
        }

        //2.2版本以后，就引入流量统计的接口
        //统计总流量的接口
        TrafficStats.getMobileRxBytes();//手机(2G, 2.5G, 3G, 4G )流量下载的总和
        TrafficStats.getMobileTxBytes();//手机流量上传的总和

        TrafficStats.getTotalRxBytes();//手机+wifi流量下载的总和
        TrafficStats.getTotalTxBytes();//手机+wifi流量上传的总和

        //统计某一款应用消耗的流量
        TrafficStats.getUidRxBytes(uid);//根据用户ID获取它下载了多少流量
        TrafficStats.getUidTxBytes(uid);//根据用户ID获取它上传了多少钱流量

    }
}


------------------------------------------------------------------------------------------------



1.获得当前的总接受数据，getTotalRxPackets()

2.每隔几秒再获取一次总接收的数据

3.讲最新获取的数据减去之前获取的数据并且除以间隔的秒数，就得到了每秒平均的网速b/s，最后进行单位转换为kb、Mb等等




1.得到当前网速的方法

初始时给total_data一个值：

private long total_data = TrafficStats.getTotalRxBytes();

之后每次获取的数值要减去total_data再除以间隔秒数，并且将total重新赋值为最新的。


    /**
     * 核心方法，得到当前网速
     * @return
     */
    private int getNetSpeed() {  
        long traffic_data = TrafficStats.getTotalRxBytes() - total_data;
        total_data = TrafficStats.getTotalRxBytes();
        return (int)traffic_data /count ;
    }


2.周期性的获取网速


    /**
     * 定义线程周期性地获取网速
     */
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            //定时器
            mHandler.postDelayed(mRunnable, count * 1000);
            Message msg = mHandler.obtainMessage();
            msg.what = 1;
            msg.arg1 = getNetSpeed();
            mHandler.sendMessage(msg);
        }
    };

3.单位转换

    @Override
    public void onCreate() {
        super.onCreate();
        
        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == 1) {
                    //float real_data = (float)msg.arg1;
                    if(msg.arg1  > 1024 ){
                        System.out.println(msg.arg1 / 1024 + "kb/s");                    
                    }
                    else{
                        System.out.println(msg.arg1 + "b/s");    
                    }
                }
            }
        };

    }


4.启动handler和停止handler

    // PS：服务开始就执行runnable中的方法，并且runnable中设置handler循环执行。
    // 执行runnable中run()方法时，将网速包装到message中，交给handler进行单位转换并显示
    // （这里在handler中进行单位转换是为了方便显示到ui中）

    /**
     * 启动服务时就开始启动线程获取网速
     */
    @Override
    public void onStart(Intent intent, int startId) {
        mHandler.postDelayed(mRunnable, 0);
    };

    /**
     * 在服务结束时删除消息队列
     */
    @Override
    public void onDestroy() {
        mHandler.removeCallbacks(mRunnable);
        super.onDestroy();
    };


    @Override
    public void onCreate() {
        super.onCreate();
        
        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == 1) {
                    //float real_data = (float)msg.arg1;
                    if(msg.arg1  > 1024 ){
                        System.out.println(msg.arg1 / 1024 + "kb/s");                    
                    }
                    else{
                        System.out.println(msg.arg1 + "b/s");    
                    }
                }
            }
        };

最后，全部代码

/**
 * @author:Jack Tony
 * @tips  :实时获取当前网速的service
 * @date  :2014-9-24
 */
public class Net_Service extends Service {

    private long total_data = TrafficStats.getTotalRxBytes();
    private Handler mHandler;
    //几秒刷新一次
    private final int count = 5;

    /**
     * 定义线程周期性地获取网速
     */
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            //定时器
            mHandler.postDelayed(mRunnable, count * 1000);
            Message msg = mHandler.obtainMessage();
            msg.what = 1;
            msg.arg1 = getNetSpeed();
            mHandler.sendMessage(msg);
        }
    };
    
    @Override
    public void onCreate() {
        super.onCreate();
        
        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == 1) {
                    //float real_data = (float)msg.arg1;
                    if(msg.arg1  > 1024 ){
                        System.out.println(msg.arg1 / 1024 + "kb/s");                    
                    }
                    else{
                        System.out.println(msg.arg1 + "b/s");    
                    }
                }
            }
        };
        
        

    }
    
    /**
     * 核心方法，得到当前网速
     * @return
     */
    private int getNetSpeed() {  
        long traffic_data = TrafficStats.getTotalRxBytes() - total_data;
        total_data = TrafficStats.getTotalRxBytes();
        return (int)traffic_data /count ;
    }

    /**
     * 启动服务时就开始启动线程获取网速
     */
    @Override
    public void onStart(Intent intent, int startId) {
        mHandler.postDelayed(mRunnable, 0);
    };

    /**
     * 在服务结束时删除消息队列
     */
    @Override
    public void onDestroy() {
        mHandler.removeCallbacks(mRunnable);
        super.onDestroy();
    };

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}



/**
 

 */

网络防火墙  droidwall


ListView Adapter 的实现方式


public class MainActivity extends AppCompatActivity {

    private static final String names[] = {"常用号码查询", "黑名单管理", "位置3", "大波q", "吹波q", "跨抓q", "喷他q",
            "常用号码查询", "黑名单管理", "位置3", "大波q", "吹波q", "跨抓q", "喷他q"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        assignViews();
    }

    private ListView lv;
    private ListView lv2;
    private ListView lv3;


    private void assignViews() {
        lv = (ListView) findViewById(R.id.lv);
        lv2 = (ListView) findViewById(R.id.lv2);
        lv3 = (ListView) findViewById(R.id.lv3);

        /**
         * 1.自定义适配器
         */
        lv.setAdapter(new listviewAdapter());

        /**
         * 2.数组适配器
         */
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, names);

        lv2.setAdapter(arrayAdapter);

        /**
         * item点击事件
         */
        lv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "点击事件" + "  第" + (position + 1) + "个", Toast.LENGTH_SHORT).show();
            }
        });
        /**
         * item长按事件
         */
        lv2.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "长按事件" + "  第" + (position + 1) + "个", Toast.LENGTH_SHORT).show();
                return false;
            }
        });


        /**
         * 3.HashMap
         */
        List<Map<String, Object>> data = new ArrayList<>();

        Map<String, Object> map1 = new HashMap<>();
        map1.put("image", R.drawable.icon_11);
        map1.put("title", "小明");
        map1.put("content", "138");
        map1.put("time", "2016年7月21日");
        data.add(map1);

        Map<String, Object> map2= new HashMap<>();
        map2.put("image", R.drawable.icon_12);
        map2.put("title", "小红");
        map2.put("content", "188");
        map2.put("time", "2013年6月06日");
        data.add(map2);

        Map<String, Object> map3 = new HashMap<>();
        map3.put("image", R.drawable.icon_13);
        map3.put("title", "小花");
        map3.put("content", "159");
        map3.put("time", "2016年10月22日");
        data.add(map3);

        Map<String, Object> map4 = new HashMap<>();
        map4.put("image", R.drawable.icon_11);
        map4.put("title", "小娜");
        map4.put("content", "178");
        map4.put("time", "2014年4月11日");
        data.add(map4);

        Map<String, Object> map5= new HashMap<>();
        map5.put("image", R.drawable.icon_12);
        map5.put("title", "小胖");
        map5.put("content", "120");
        map5.put("time", "2017年6月06日");
        data.add(map5);

        Map<String, Object> map6 = new HashMap<>();
        map6.put("image", R.drawable.icon_13);
        map6.put("title", "小兰");
        map6.put("content", "110");
        map6.put("time", "2015年5月22日");
        data.add(map6);

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, data, R.layout.item, new String[]{"image", "title", "content", "time"}, new int[]{R.id.img, R.id.title, R.id.content, R.id.time});

        lv3.setAdapter(simpleAdapter);


    }

    /**
     * 自定义适配器
     */
    class listviewAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return names.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view;
            ViewHolder viewHolder;
            if (convertView != null && convertView instanceof RelativeLayout) {
                view = convertView;
                viewHolder = (ViewHolder) view.getTag();
            } else {
                view = View.inflate(MainActivity.this, R.layout.item_listview, null);
                viewHolder = new ViewHolder();

                viewHolder.tv = (TextView) view.findViewById(R.id.tv);


                view.setTag(viewHolder);
            }

            viewHolder.tv.setText(names[position]);

            return view;
        }
    }

    /**
     * 容器
     */
    static class ViewHolder {
        TextView tv;
    }

}



/**
 

 */
五种适配方式

1.图片适配

2.dimens.xml适配（当前手机的像素密度属于哪个范围内，dp和px的转换关系去达到适配，values-1280*720）
    ldpi    1dp = 0.75 px    160dp = 120px      240px一半      320*240
    mdpi    1dp = 1 px       160dp = 160px      320px一半      480*320
    hdpi    1dp = 1.5 px     160dp = 240px      480px一半      320*480
    xdpi    1dp = 2 px       160dp = 320px      640px一半      320*720 360px 180dp
    xxdpi    1dp = 3 px       160dp = 480px      960px一半      320*1080 540px

3.布局适配（注意当前手机想读密码属于哪个范围内，加载不同资源布局 layout-1280*720 文件夹下，布局文件）

4.java代码配置

        /**
         * 获取手机屏幕的宽高
         */
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        /**
         * 获取手机宽高上面的像素点
         */

        int widthPixels = displayMetrics.widthPixels;
        int heightPixels = displayMetrics.heightPixels;

        //textView 显示屏幕宽高的1/2，首先指定的宽高设置在当前控件的父布局，然后再将宽高作用在当前控件上
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                //数学四舍五入操作
                (int) (widthPixels * 0.5 + 0.5), (int) (heightPixels * 0.5 + 0.5));

        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setLayoutParams(layoutParams);


5.weight 权重适配（只有在线性布局中使用）

/**
 

 */

自定义View控件
    /**
     * 一个控件View从创建到显示过程中主要的方法
     * 1.构造方法-实例化 两个参数的构造方法
     * 2.测量：onMeasure(int, int); 如果这个View是ViewGroup，有义务测量孩子的宽高
     * 3.指定View的大小和位置：onLayout(boolean, int,int,int,int);
     *   如果这个View是ViewGroup，有义务测量孩子的大小和位置
     * 4.绘制视图：onDraw(canvas)
     */
    

/**
 
 
 */
Vibrator类：实现振动服务

权限 <uses-permission android:name="android.permission.VIBRATE"/>

实例化
1、Context.getSystemService(java.lang.String)

private Vibrator mVibrator;//震动效果

mVibrator = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);


方法：
1、vibrate(long[] pattern, int repeat)
指定的时间间隔内震动并且可以设置震动持续的时间
参数：
    pattern：设置震动的时间间隔和持续时间
    repeat：设置震动重复的次数

2、cancel():取消震动


        long[] pattern = {500, 300, 500, 300};
        mVibrator.vibrate(pattern, -1);


         //震动效果
        vibrator.vibrate(1000);//震动一秒


        long[] pattern = {500, 500, 1000, 1000, 2000, 2000};//震动停止震动停止
        //-1 不重复
        //0 重复
        //2 重复55,11,22  11,22  22
        vibrator.vibrate(pattern, -1);


/**
 
 
 */

MediaPlayer类：实现音频 audio 和 视频 video 文件的播放功能

音乐播放的方式：
1、静态方法
2、构造方法
    1、static MediaPlayer create(Context class, int resid);
        根据音频文件的标识得到MediaPlayer对象

    2、构造方法
    MediaPlayer();
    方法：
        setDataSource(FileDescriptor fd, long offset, long length);
        设置音频文件资源
        参数：fd 文件描述符
        offset 初始偏移量
        length 文件的长度
        严格按照生命周期来实现

    播放
        方法：
        1、start():播放音乐


/**
 
 
 */




设置欢迎页面图片

        //延迟2秒在做后续操作
        new Handler() {
            //处理2秒后接收到的消息
        }.sendEmptyMessageDelayed(0, 2000);

导航页面



<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <android.support.v4.view.ViewPager
        android:id="@+id/view_pager"
        android:layout_width="match_parent"
        android:layout_height="match_parent"></android.support.v4.view.ViewPager>

    <Button
        android:id="@+id/button"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_alignParentBottom="true"
        android:layout_centerHorizontal="true"
        android:layout_marginBottom="30dp"
        android:paddingLeft="30dp"
        android:paddingRight="30dp"
        android:text="开始体验"
        android:textSize="20sp"
        android:visibility="gone"/>
</RelativeLayout>


/**
 * 
 */


public class GuideActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private Button button;
    private List<ImageView> arrayList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guide);

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        button = (Button) findViewById(R.id.button);

        initData();

        viewPager.setAdapter(new MyAdapter());

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //滑动过程中
            }

            @Override
            public void onPageSelected(int position) {
                //已经选中某一页
                if (position == arrayList.size()-1) {
                    button.setVisibility(View.VISIBLE);
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(GuideActivity.this, MainActivity.class));
                            finish();
                        }
                    });
                } else {
                    button.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //滑动状态发生改变的方法
            }
        });

    }

    private void initData() {
        arrayList = new ArrayList<>();
        //添加包含了图片，图片空间

        ImageView imageView1 = new ImageView(getApplicationContext());
        imageView1.setBackgroundResource(R.drawable.guide_1);

        ImageView imageView2 = new ImageView(getApplicationContext());
        imageView2.setBackgroundResource(R.drawable.guide_2);

        ImageView imageView3 = new ImageView(getApplicationContext());
        imageView3.setBackgroundResource(R.drawable.guide_3);

        arrayList.add(imageView1);
        arrayList.add(imageView2);
        arrayList.add(imageView3);
    }

    //ViewPager预加载操作
    private class MyAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        //给指定的viewpager添加一个View方法
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            //container就是viewpager对象
            container.addView(arrayList.get(position));
            return arrayList.get(position);
        }

        //给指定的viewpager移除一个View方法

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

    }
}


/**
 * 
 */

public class WelcomeActivity extends AppCompatActivity {
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("config", MODE_PRIVATE);

        //延迟2秒在做后续操作
        new Handler() {
            //处理2秒后接收到的消息

            @Override
            public void handleMessage(Message msg) {
                boolean b = sharedPreferences.getBoolean("is_first", true);

                if (b) {
                    //第一次进入导航页面
                    sharedPreferences.edit().putBoolean("is_first", false).commit();
                    //跳转到防止了ViewPager的Activity中
                    startActivity(new Intent(WelcomeActivity.this, GuideActivity.class));
                } else {
                    //第一次之外进入应用，直接进入应用程序

                }
                finish();
            }
        }.sendEmptyMessageDelayed(0, 2000);

    }
}







/**
 
 */


    /**
     * 设置webView
     */
    String url = "http://i.meituan.com/?cevent=imt%2Fguide%2Fi%2FAndroid";
    private WebView webView;

    private void webView() {
        webView = (WebView) findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                //get the newProgress and refresh progress bar
            }
        });
        webView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });

        webView.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {  //表示按返回键 时的操作
                        webView.goBack();   //后退
                        return true;    //已处理
                    }
                }
                return false;
            }
        });
    }