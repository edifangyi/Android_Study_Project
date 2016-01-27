 Activity

一、本课讲解如何创建一个 Activity，如何在 Manifest 文件中对Activity进行配置，以及如何使用 startActivity 函数启动一个 Activity。


1.	在res → layout 新建文件 my_activity.xml  

	<?xml version="1.0" encoding="utf-8"?>
	<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
	    android:orientation="vertical" android:layout_width="match_parent"
	    android:layout_height="match_parent">

	    <Button
	        android:layout_width="wrap_content"
	        android:layout_height="wrap_content"
	        android:text="New Button"
	        android:id="@+id/button" />

	    <Button
	        android:layout_width="wrap_content"
	        android:layout_height="wrap_content"
	        android:text="启动另外一个activity"
	        android:id="@+id/btnStartAnotherAty" />
	</LinearLayout>


2.	在 java → com.example.fangyi.androidactivi → MainActivity 中

	public class MainActivity extends AppCompatActivity {

	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	       // setContentView(R.layout.activity_main);
	        setContentView(R.layout.my_activity);



			凭身份证								设置点击侦听器				点击监听
	        findViewById(R.id.btnStartAnotherAty).setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	                
	            }
	        });


3.	在 java → com.example.fangyi.androidactivi 中创建Acitivty模板 名字 AnotherAty


4.	在 MainActivity 中使用API 
 		startActivity(new Intent(MainActivity.this, AnotherAty.class));
 		//效果
 		//点开以后跳到另一个Activity

 		 startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://baidu.com")));
 		 //效果
 		//点开以后跳到百度首页
 		//
 
//////////////////////////////////////////////////////////////////////////////////////////////////////////
///
///
 Activity的生命周期
 //如何查看 Google 官方所提供的 Android 开发参考文档，以参考文档做为指导进行开发工作。
 //D:\Android\sdk\docs\index.html
 //点 Develop → Reference → API packages.
 //
 //
 onsta → 输出
	 @Override
	    protected void onStart() {
	        super.onStart();
	    }
 sout → 输出 System.out.println("onPause");

 //
 //
 // 
 // 
 // 在MainActivi中创建
    System.out.println("lalalalalalalall            onCreate");

 	@Override
    protected void onStart() {
        super.onStart();
        System.out.println("onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("onRestart");
    }
 
参考  Activity - Android SDK
01-20 11:31:32.151 2611-2611/? I/System.out: onCreate
01-20 11:31:32.158 2611-2611/? I/System.out: onStart
01-20 11:31:32.158 2611-2611/? I/System.out: onResume
//回到桌面
01-20 11:33:31.310 2611-2611/? I/System.out: onPause
01-20 11:33:31.325 2611-2611/? I/System.out: onStop
//点任务管理器切回apk
01-20 11:34:31.104 2611-2611/? I/System.out: onRestart
01-20 11:34:31.105 2611-2611/? I/System.out: onStart
01-20 11:34:31.105 2611-2611/? I/System.out: onResume
//点退出
01-20 11:35:50.114 2611-2611/? I/System.out: onPause
01-20 11:35:50.692 2611-2611/? I/System.out: onStop
01-20 11:35:50.692 2611-2611/? I/System.out: onDestroy


1.在两个 Activity （B不是透明的）跳转的过程当中 A先暂停，等 B全部加载完，A结束
            
2.在AndroidManifest.xml中更改

            android:theme="@style/AppTheme.NoActionBar"
    换成透明的
    		android:theme="@style/Theme.AppCompat.Dialog"

 	A执行一个 onPause  ，此时A可见，运行状态  然后是B  onCreate，onStart，onResume
 	把B关闭，B先执行onPause，然后A在执行onResume，B执行onStop， B 最后onDestroy

//////////////////////////////////////////////////////////////////////////////////////////////////////////

Alt(Option)+Enter	自动修正

//////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
///
///
本课讲解在 Activity 跳转时如何传递简单数据。

前面都正常

1.在MainActivity中创建
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnStartAnotherAty2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, AnotherAty.class);

                	额外投入
                i.putExtra("data","hello hi几个号hiI");
                startActivity(i);
            }
        });


2.在content_main.xml中添加Button
    <Button
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="启动另外一个activity2222222"
        android:id="@+id/btnStartAnotherAty2"
        android:layout_below="@+id/btnStartAnotherAty"
        android:layout_alignParentLeft="true"
        android:layout_alignParentStart="true" />




3.在AnotherAty中创建		获取数据
public class AnotherAty extends AppCompatActivity {

    private TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another_aty);

        Intent i = getIntent();

        textView2 = (TextView) findViewById(R.id.textView2);

        textView2.setText(i.getStringExtra("data"));
//////////////////////////////////////////////////////////////////////////////////////////////////////////

本课讲解在 Activity 跳转时如何传递复杂的数据。

在上个例子中
1.在MainActivity中添加

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnStartAnotherAty2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, AnotherAty.class);
                //i.putExtra("data","hello 傻逼桑");

                Bundle b = new Bundle();

                b.putString("name", "hahahahah");
                b.putInt("age", 2);
                b.putString("name1","iwen");
        方法1	// i.putExtras(b);
        方法2	i.putExtra("data",b);


                startActivity(i);
            }
        });

2.在AnotherAty中添加

public class AnotherAty extends AppCompatActivity {
    private TextView textView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another_aty);

        System.out.println("B onCreate");

        Intent i = getIntent();
方法1   //Bundle data = i.getExtras();
方法2   Bundle data = i.getBundleExtra("data");
        textView2 = (TextView) findViewById(R.id.textView2);

       // textView2.setText(i.getStringExtra("data"));																			//可以设初值
        textView2.setText(String.format("name=%s,age=%d,name1=%s", data.getString("name"),data.getInt("age"), data.getString("name1","leo")));

//////////////////////////////////////////////////////////////////////////////////////////////////////////

本课讲解在 Activity 跳转时如何传递自定义的值对象。

在 Code →  Generate 可以自动生成各种get set 析构函数


//////////////////////////////////////////////////////////////////////////////////////////////////////////

通过 setResult() 返回状态码


finish();直接把当前的 activity

startActivityForResult(i,0)

再加上
@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	super.onActivityResult(requestCode, resultCode, data)  
}

//////////////////////////////////////////////////////////////////////////////////////////////////////////Launch

默认启动模式
标准启动模式
本课讲解在 Android 中 Activity 的标准启动模式的意义。
		

		protected TextView tv;



        tv = (TextView) findViewById(R.id.tv);

        tv.setText(String.format("TaskID:%d, \n Current Activity id:%s", getTaskId(),toString()));


定义框框中文字是否全为大写
android:textAllCaps="false" 


        findViewById(R.id.btnStartSelf).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MainActivity.class));
            }
        });




关于配置 在 manifests 中的 AndroidManifest.xml

<activity
            android:name=".MainActivity"
            android:label="@string/app_name"
  啦啦啦    android:launchMode="standard"
            android:theme="@style/AppTheme.NoActionBar">
//////////////////////////////////////////////////////////////////////////////////////////////////////////

SingleTop 模式
本课讲解在 Android 中 Activity 的 SingleTop 启动模式的意义。
			
            android:launchMode="singleTop"


改名字重构   右键 Refactor → Rename 完成改名工作


在同一个堆栈 里面 Activity 的 SingleTop 启动模式的话，如果此时这个 Activity 处于栈顶 只能创建一个实例。
如果当前不属于栈顶，会创建新的实例

//////////////////////////////////////////////////////////////////////////////////////////////////////////
SingleTask 与 SingleInstance 模式

            android:launchMode="singleTask"

有一个任务栈，有 A和B  点B后 再点A  直接把B弹出，A 是最初时候的，再按后退键直接退出

            android:launchMode="singleInstance"

两个任务 A B 相互切换，，，，独立的任务栈只放了一个实例


package com.example.fangyi.launchmode;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity {
    protected TextView tv;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView) findViewById(R.id.tv);

        tv.setText(String.format("TaskID:%d, \n Current Activity id:%s", getTaskId(), toString()));

        findViewById(R.id.btnMainAty).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MainActivity.class));
            }
        });


        findViewById(R.id.btnStartBAty).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, BAty.class));
            }
        });





package com.example.fangyi.launchmode;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class BAty extends AppCompatActivity {
    private TextView tv;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baty);

        tv = (TextView) findViewById(R.id.tv);
        tv.setText(String.format("TaskID:%d, \n Current Activity id:%s", getTaskId(), toString()));

        findViewById(R.id.btnMainAty).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BAty.this, MainActivity.class));
            }
        });


        findViewById(R.id.btnStartBAty).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BAty.this, BAty.class));
            }
        }); 

    <TextView
        android:id="@+id/tv"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Hello World!" />

    <Button
        android:textAllCaps="false"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="启动 Main Activity"
        android:id="@+id/btnMainAty" />

    <Button
        android:textAllCaps="false"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="启动 BAty"
        android:id="@+id/btnStartBAty" />


//////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////
///
///
显式 Intent 

本课讲解显式 Intent 的声明与使用方式。

手动创建一个类，让他继承 Activity，给他绑定一个视图

1。在 com.example.fangyi.launchmode 中创建一个类 MyAty

	让它继承至 Activity

public class MyAty extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView 设置内容视图 (R.layout.myaty);  //可以用过这段代码把 myaty 的Activity 绑定起来  绑定一个布局
    }
}
2.在 res.layout 里面创建一个myaty
	
	orientation布局走向

3.注册 MyAty 在 AndroidManifest.xml

	在 <application 标记下添加一个子标记
        	<activity
         	   android:name=".MyAty"/>


1.显式Intent
new Intent(MainActivity.this, MyAty.class)
//////////////////////////////////////////////////////////////////////////////////////////////////////////

2.隐式Intent
注册 MyAty 在 AndroidManifest.xml

        <activity android:name=".MyAty">
            <intent-filter>
                <category android:name="android.intent.category.DEFAULT"/>
                <action android:name="com.example.fangyi.launchmode.intent.action.MyAty"/>
            </intent-filter>

被启动的 Activity - MyAty 中添加
public static final String ACTION ="com.example.fangyi.launchmode.intent.action.MyAty"


        findViewById(R.id.btnStartMyAty).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyAty.ACTION));
            }
        });


 //优势，可以启动其他应用中的 Activity
 
新建一个模块App1

在MainActivity中添加

        findViewById(R.id.btnStartMyAty).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent("com.example.fangyi.launchmode.intent.action.MyAty"));
            }
        });
启动 app 中的 Activity

//如果不想其他应用调用，只能在同一个应用内部进行访问

可以在 app 中的 AndroidManifest.xml 标签处添加   android:exported=""  是否可以调用，默认是可以导出

        <activity android:name=".MyAty" android:exported="false">
            <intent-filter>
                <category android:name="android.intent.category.DEFAULT"/>
                <action android:name="com.example.fangyi.launchmode.intent.action.MyAty"/>
            </intent-filter>

//可以在 App1 中加一个检测，处理 捕获到这个异常  不能调用app中的应用

                try {
                    startActivity(new Intent("com.example.fangyi.launchmode.intent.action.MyAty"));
                }catch (Exception e) {
                    Toast.makeText(MainActivity.this, "无法启动指定的Activity", Toast.LENGTH_SHORT).show();
                }

//////////////////////////////////////////////////////////////////////////////////////////////////////////

Intent 过滤器相关选项

在 app 中 新建一个 activity  MyAty1

什么都不用改

在 AndroidManifest.xml 中

默认方式

        <activity 							加上下面这句
            android:name=".MyAty" android:label 标签 ="MyAty">
啦啦啦这段删了            <!--android:exported="false">-->
            <intent-filter>
                <category android:name="android.intent.category.DEFAULT" />
                <action android:name="com.example.fangyi.launchmode.intent.action.MyAty" />
            </intent-filter>
        </activity>
        <activity
            android:name=".MyAty1"
            android:label="@string/title_activity_my_aty1"
            android:theme="@style/AppTheme.NoActionBar">
            <intent-filter>//添加子标签
 加这段         <category /*类别*/ android:name="android.intent.category.DEFAULT /*默认*/"/>
 加这段         <action /*行动*/ android:name="com.example.fangyi.launchmode.intent.action.MyAty" />
 															//注意格式
            </intent-filter>
        </activity>

//效果， 在app1 中 选择 会有两个选择 MAty 和 MAty1
//
582行 添加 <data android:scheme="app"/>

如果想指明 这段Activity

 		<activity
            android:name=".MyAty1"
            android:label="@string/title_activity_my_aty1"
            android:theme="@style/AppTheme.NoActionBar">
            <intent-filter>//添加子标签
 加这段         <category /*类别*/ android:name="android.intent.category.DEFAULT /*默认*/"/>
 加这段         <action /*行动*/ android:name="com.example.fangyi.launchmode.intent.action.MyAty" />
 				<data android:scheme="app"/>											//注意格式
            </intent-filter>
        </activity>

去 app1中的 MainActivity
	
重载函数																				这里→
		startActivity(new Intent("com.example.fangyi.launchmode.intent.action.MyAty", Uri.parse("app://hello")));
直接启动到指明协议的 Activity 

除此之外，所配置的 data 选项 还有许多其他可以匹配的参数

//////////////////////////////////////////////////////////////////////////////////////////////////////////


通过浏览器链接启动本地 Activity 07:22

本课讲解如何通过配置 IntentFilter 从而从浏览器启动本地的 Activity。


1.新建一个 LocalAppAty  随便写点东西在里面

2.在 AndroidManifest 中添加 
        <activity
            android:name=".LocalAppAty"
            android:label="@string/title_activity_local_app_aty"
            android:theme="@style/AppTheme.NoActionBar">
            <intent-filter>
                <category android:name="android.intent.category.BROWSABLE"/>
                <category android:name="android.intent.category.DEFAULT" />
                <action android:name="android.intent.action.VIEW" />
                <data android:scheme="LocalAppAty" />
            </intent-filter>
            </activity>



3.写一个浏览器的页面 index.html  WebStorm

<!DOCTYPE html>
<html>
<head lang="en">
	<meta charset="UTF-8"
	<title></title>

	<style type="text/css">
		a{
			font-size: 50pt;
		}
	</style>
</head>
<body>
<a href="app:/LocalAppAty">Launch My App</a>
</body>
</html>


3.
来获取启动这个Activity的Intent的对象
在LocalAppAty中添加
     Uri uri = getIntent().getData();
     System.out.println(uri);
可以获取到传来的信息的 app:/LocalAppAty


//////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////

Android 中 Context 的理解及使用
本课带你理解 Context 及 Context 的作用。



Interface to global information about an application environment. This is an abstract class whose implementation is 
provided by the Android system. It allows access to application-specific resources and classes, as well as up-calls 
for application-level operations such as launching activities, broadcasting and receiving intents, etc.


一个应用环境的全球信息接口。这是一个抽象类，它的实现是由安卓系统提供的。它允许访问应用程序特定的资源和类，以及应用
级的操作，如启动活动，电话，广播和接收的意图，等等

接口有关的应用程序环境的全局信息。这是一个抽象类，它的实现是由Android系统提供的。它允许访问特定应用程序的资源和类，
以及向上呼吁应用层的操作，如发射活动，广播和接收意图等



    protected TextView tv;

    tv = new TextView(MainActivity.this);
//方法一    tv.setText("hello android");
    tv.setText(R.string.hello_world);//方法二 重载函数，字符串资源ID,出入ID  R.string.hello_world  任意资源

//    工程在创建时候，会自动在values.strings.xml中生成资源  app_name、hello_world都是字符串资源
//    <resources>
//     <string name="app_name">LaunchMode</string>
//     <string name="hello_world">Hello world!</string>
//     <string name="action_settings">Settings</string>
//     <string name="title_activity_baty">BAty</string>
//     <string name="title_activity_my_aty1">MyAty1</string>
//     <string name="title_activity_local_app_aty">LocalAppAty</string>
//     <string name="title_activity_learn_context">LearnContext</string>
// </resources>

    setContentView(tv);//设置内容视图
    System.out.println(getResources().getText(R.string.hell_world));



//图标资源

        ImageView iv = new ImageView(this);
        iv.setImageResource(R.mipmap.ic_launcher);
        setContentView(iv);

/////////////////////////////////////////////////////////////////////////////////////////////////////////

Application 的用途

本课讲解在 Android 中 Application 的用途以及如何自定义 Application。

1.定义一个class → App

	继承自 Application

		public class App extends Application{
	    private String textData = "default";

	    public String getTextData() {
	        return textData;
	    }

	    public void setTextData(String textData) {
	        this.textData = textData;
	    }
	}

	在AndroidManifest中进行配置 
		<application
			android:name=".App" //通过这种方式，我们就自定义了application 真正的全局上下文对象

2.定义一个class → Main2
	继承自 Activity
	public class Main2 extends Activity {
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	    }
	}


在AndroidManifest中进行配置 
        <activity android:name=".Main2">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>


在<activity
            android:name=".MainActivity"
            android:label="Main1" //改成Main1



/////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////

使用 Service
本课讲解如何使用 startService 启动服务和如何使用 stopService 停止服务，以及在过程中需要注意的问题。
创建 MyService

    protected Intent intent;

            intent = new Intent(MainActivity.this, MyService.class);

        findViewById(R.id.btnStartService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(MainActivity.this, MyService.class));
            }
        });

        findViewById(R.id.btnStopService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService( intent );
            }
        });


//在后台不断输出 输出语句

在 MyService中 
添加
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        new Thread() {
            @Override
            public void run() {
                super.run();

                while (true) {
                    System.out.println("服务正在运行···");
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        return super.onStartCommand(intent, flags, startId);
    }

//    在外界执行了 startService 的时候 会执行到 onStartCommand

/////////////////////////////////////////////////////////////////////////////////////////////////////////

绑定 Service 06:26

本课讲解如何使用 bindService 绑定并启动服务，如何使用 unbindService 解除绑定服务，以及 Activity 与被绑定的服务之间的关系。


//技巧
//如果希望 所以按钮，可以这么写代码
        findViewById(R.id.btnStartService).setOnClickListener(this);
        findViewById(R.id.btnStopService).setOnClickListener(this);
        findViewById(R.id.btnBindService).setOnClickListener(this);
        findViewById(R.id.btnUnBindService).setOnClickListener(this);


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnStartService:
            //Inten i = new Intent(this, MyService.class);
            //startService(i);
                startService(intent);
                break;
            case R.id.btnStopService:
                stopService(intent);
                break;
            case R.id.btnBindService:
                bindService(intent, this, Context.BIND_AUTO_CREATE);   //第二个this会有错误，类型不匹配 需要补全
                break;
            case R.id.btnUnBindService:
                unbindService(this);
                break;
        }
    }


//补全代码
    @Override//服务被绑定成功之后 执行
    public void onServiceConnected(ComponentName name, IBinder service) {
        System.out.println("Service Connected");
    }

    @Override//服务所在进程崩溃杀掉 执行
    public void onServiceDisconnected(ComponentName name) {

    }


//替换掉在 MySerivice 下面代码
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
//为这样
    @Override
    public IBinder onBind(Intent intent) {
		return new Binder();
    }

/////////////////////////////////////////////////////////////////////////////////////////////////////////

Service生命周期 06:58

本课介绍 Service 的生命周期，并 Service 的生命周期对于开发实际项目的意义。



    @Override//启动
    public void onCreate() {
        super.onCreate();
    }

    @Override//停止
    public void onDestroy() {
        super.onDestroy();
    }

//当启动服务，绑定服务的时候，，，需要停止 和 解除 才真正意义上的关闭





package com.example.fangyi.launchmode;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class MyService extends Service {
    private boolean serviceRuning = false;
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new Binder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {



        System.out.println("哈哈哈哈哈哈·······onStattCommand");

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("服务，，，，onCreate");

        serviceRuning = true;


        new Thread() {
            @Override
            public void run() {
                super.run();

                while (serviceRuning) {
                    System.out.println("服务正在运行···");
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("服务，，，disDestroy，");
        serviceRuning = false;

    }
}


/////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////

启动 Service 并传递数据 08:59

本课时讲解如何启动一个 Service 并且向该 Service 传递数据。



外界与一个服务进行通信

1.在 content_main.xml 中添加

    <EditText
        android:layout_width="95dp"
        android:layout_height="wrap_content"
        android:text="默认信息"
        android:id="@+id/etData"
        android:layout_alignTop="@+id/btnMainAty"
        android:layout_alignParentRight="true"
        android:layout_alignParentEnd="true" />


2.在 MainActivity.java 中添加
    1.protected EditText etData;

    2.etData = (EditText) findViewById(R.id.etData);
	
	3.@Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnStartService:
                intent.putExtra("data",etData.getText().toString()); //这句话
                startService(intent);
                break;
            case R.id.btnStopService:
                stopService(intent);
                break;
            case R.id.btnBindService:
                bindService(intent, this, Context.BIND_AUTO_CREATE);
                break;
            case R.id.btnUnBindService:
                unbindService(this);
                break;
        }
    }

3. 在 MyService 中 添加


public class MyService extends Service {
    private boolean running = false;
    private String data = "这是默认信息";

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        data = intent.getStringExtra("data");	//这句话

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();	//这句话
        running = true;
        new Thread(){
            @Override
            public void run() {
                super.run();
                
                while (running) {

                    System.out.println(data);


                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();	//这句话
    }

    @Override
    public void onDestroy() {
        running = false;	//这句话
    }
}


////////////////////////////////////////////////////////////////////////////////////////////////////////////

绑定 Service 进行通信（上） 08:02

本课时讲解如何与被绑定的 Service 进行通信。

1.在以上基础上添加按钮

    <Button
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="同步数据"
        android:id="@+id/btnSyncData"
        android:layout_below="@+id/btnUnBindService"
        android:layout_alignLeft="@+id/btnUnBindService"
        android:layout_alignStart="@+id/btnUnBindService" />

2.在 MainActivity 中添加 监听器

        a. 		findViewById(R.id.btnSyncData).setOnClickListener(this);

        b.	case R.id.btnSyncData:

                break;

3.在 MyService 中 修改添加 Binder

    @Override
    public IBinder onBind(Intent intent) {
//        // TODO: Return the communication channel to the service.
//        throw new UnsupportedOperationException("Not yet implemented");
        return new Binder();
    }
    public class Binder extends android.os.Binder{
        public void setData(String data) {
            MyService.this.data = data;
        }
    }

    这样就可以把 MyService 和 MainActivity 链接起来了


4. 在 MainActivity 中添加     
	protected MyService.Binder binder = null;

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        binder = (MyService.Binder) service;
    }

5. 直接添加代码
            case R.id.btnSyncData:
                if (binder!=null){
                    binder.setData(etData.getText().toString());
                }
                break;

////////////////////////////////////////////////////////////////////////////////////////////////////////////

绑定 Service 进行通信（下） 08:26

本课时讲解如何侦听被绑定的 Service 的内部状态。

之前都是在控制台输出
现在要 把输出呈现在 MainActivity 中

1.在 MyService 中添加 数字

int i = 0;
                while (running) {
                    i++;
                    System.out.println(i+":"+data);


2.新建一个 TextView
        android:layout_width="fill_parent"
        android:layout_height="wrap_content"
        android:text="New Text"
        android:id="@+id/tvOut"
        android:layout_below="@+id/btnSyncData"
        android:layout_alignParentLeft="true"
        android:layout_alignParentStart="true" />

3.在 MainActivity 中定义一个 新的
	
    private TextView tvOut;
       
       tvOut = (TextView) findViewById(R.id.tvOut);

4.接下来就是如何让内部通知外界
	回调机制

	在 MyService 中 添加一个接口

	   a. private Callback callback = null;
    

        b.            
         int i = 0;
                while (running) {
                    i++;
                    String str = i+":"+data;
                    System.out.println(str);
                    if (callback!=null) {
                        callback.onDataChange(str);
                    }


  c,  @Override
    public void onDestroy() {
        super.onDestroy();
        running = false;
    }

    private Callback callback = null;

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public Callback getCallback() {
        return callback;
    }

    public static interface Callback {
        void onDataChange(String data);
    }



UI线程不准许辅线程调用UI线程资源

5.在 MainActivity 中添加
    public void onServiceConnected(ComponentName name, IBinder service) {
        binder = (MyService.Binder) service;
        binder.getService().setCallback(new MyService.Callback() {
            @Override
            public void onDataChange(String data) {
                Message msg = new Message();
                Bundle b = new Bundle();
                b.putString("data", data);
                msg.setData(b);
                handler.sendMessage(msg);
            }
        });
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }

    private android.os.Handler handler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            tvOut.setText(msg.getData().getString("data"));
        }
    };








