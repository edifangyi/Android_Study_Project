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

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

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



//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

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


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

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

public class MyService extends Service {
    private boolean running = false;
    private String data = "这是默认信息";

    public MyService() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        return new Binder();
    }
    public class Binder extends android.os.Binder{
        public void setData(String data) {
            MyService.this.data = data;
        }

        public MyService getService() {
            return MyService.this;
        }
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        data = intent.getStringExtra("data");

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        running = true;
        new Thread(){
            @Override
            public void run() {
                super.run();

                int i = 0;
                while (running) {
                    i++;
                    String str = i+":"+data;
                    System.out.println(str);
                    if (callback!=null) {
                        callback.onDataChange(str);
                    }
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
}






UI线程不准许辅线程调用UI线程资源

5.在 MainActivity 中添加
    protected Intent intent;
    private EditText etData;
    private MyService.Binder binder = null;
    private TextView tvOut;
        

        intent = new Intent(MainActivity.this, MyService.class);
        etData = (EditText) findViewById(R.id.etData);
        tvOut = (TextView) findViewById(R.id.tvOut);

        findViewById(R.id.btnStartService).setOnClickListener(this);
        findViewById(R.id.btnStopService).setOnClickListener(this);
        findViewById(R.id.btnBindService).setOnClickListener(this);
        findViewById(R.id.btnUnBindService).setOnClickListener(this);
        findViewById(R.id.btnSyncData).setOnClickListener(this);


   @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnStartService:
                intent.putExtra("data",etData.getText().toString());
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
            case R.id.btnSyncData:
                if (binder!=null){
                    binder.setData(etData.getText().toString());
                }
                break;
        }
    }

    @Override
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


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
Android 中 AIDL 的理解与使用

跨应用启动 Service 10:57

本课讲解如何跨应用启动 Service，从 Android 5.0 以后只能通信显式 Intent 来启动服务

1.在 app 中创建 AppService 类 

	添加
	    @Override
	    public void onCreate() {
	        super.onCreate();
	        System.out.println("Service started");
	    }

	    @Override
	    public void onDestroy() {
	        super.onDestroy();

	        System.out.println("Service destroy");
	    }


	 在 MainActivity 中 添加 startService(new Intent(this,AppService.c;ass));

	 和 @Override
	 protected void onDestroy(){
	 	super.onDestroy();

	 	stopService(new Intent(this,AppService.class)); 
	 }

	 以上方法可以在同一个应用中启动和定制服务

	 下面通过其他App调用另一个App服务



2.再来创建一个 module - app2
    
    创建两个按钮
    
    <Button
        android:textAllCaps="false"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="启动LaunchMode服务"
        android:id="@+id/btnStartLaunchModeAppService"
        android:layout_below="@+id/btnStartMyAty"
        android:layout_alignParentLeft="true"
        android:layout_alignParentStart="true"
        android:layout_marginTop="36dp" />

    <Button
        android:textAllCaps="false"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="关闭LaunchMode服务"
        android:id="@+id/btnStopLaunchModeAppService"
        android:layout_below="@+id/btnStopLaunchModeAppService"
        android:layout_alignParentLeft="true"
        android:layout_alignParentStart="true"
        android:layout_marginTop="29dp" />

3.

    private Intent serviceIntent;
        serviceIntent = new Intent();
	    serviceIntent.setComponent(new ComponentName("com.example.fangyi.app1", "com.example.fangyi.launchmode.AppService"));
//通过setComponent来设置一个组建名字，同过名字来启动 Activity  ，参数 首先是这个app文件的包名，之后是被启动的服务的类的名字，可以写全路径
        
        findViewById(R.id.btnStartLaunchModeAppService).setOnClickListener(this);
        findViewById(R.id.btnStopLaunchModeAppService).setOnClickListener(this);

4. 

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnStartLaunchModeAppService:
                startService(serviceIntent);
                break;
            case R.id.btnStopLaunchModeAppService:
                stopService(serviceIntent);
                break;
        }
    }


4.通过 service 启动服务 还可以在 AppService 中 重新写 onStartCommand 用于接收从其他的应用传递过来的数据

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////

跨应用绑定 Service 06:14

本课讲解如何跨应用绑定 Service

在绑定的过程当中我们需要一个Bind,,指定的类里面所定义的。我们无法从一个应用里面访问另一个应用里面的Bind

接口定义语言

创建一个 AIDI 文件



1.创建：IAPPServiceRemoteBinder 

他会全自动的给我们生成相关的类

然后重新构建 在 Build → Rebuild Project


2.在 AppService 中添加代码

    @Override
    public IBinder onBind(Intent intent) {
        return new IAPPServiceRemoteBinder.Stub() {
//因为是抽象类，所以我们需要实现里面的方法
            @Override
            public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

            }
        };
    }

3.如何通过app1 来绑定 app中的服务

在app1 中添加按钮

    <Button
        android:textAllCaps="false"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="绑定LaunchMode服务"
        android:id="@+id/btnBindLaunchModeAppService"
        android:layout_below="@+id/btnStopLaunchModeAppService"
        android:layout_alignParentLeft="true"
        android:layout_alignParentStart="true" />

    <Button
        android:textAllCaps="false"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="解除LaunchMode服务"
        android:id="@+id/btnUnBindLaunchModeAppService"
        android:layout_below="@+id/btnBindLaunchModeAppService"
        android:layout_alignParentLeft="true"
        android:layout_alignParentStart="true" />

4.在 app1 中在添加两个事件监听器
	private Intent serviceIntent;
 		serviceIntent = new Intent();
        serviceIntent.setComponent(new ComponentName("com.example.fangyi.app1", "com.example.fangyi.launchmode.AppService"));

        findViewById(R.id.btnStartLaunchModeAppService).setOnClickListener(this);
        findViewById(R.id.btnStopLaunchModeAppService).setOnClickListener(this);
        findViewById(R.id.btnBindLaunchModeAppService).setOnClickListener(this);
        findViewById(R.id.btnUnBindLaunchModeAppService).setOnClickListener(this);


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnStartLaunchModeAppService:
                startService(serviceIntent);
                break;
            case R.id.btnStopLaunchModeAppService:
                stopService(serviceIntent);
                break;
            case R.id.btnBindLaunchModeAppService:
                bindService(serviceIntent, this, Context.BIND_AUTO_CREATE); //绑定代码，this，实现 onServiceConnected
                break;
            case R.id.btnUnBindLaunchModeAppService:
                unbindService((ServiceConnection) this);
                break;
        }
    }


    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        System.out.println("Bind Service");
        System.out.println(service);
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }


////////////////////////////////////////////////////////////////////////////////////////////////////////////

跨应用绑定 Service 并通信 11:28

本课讲解如何使用 AIDL 跨应用与 Service 通信

1.把app 中的 aidl文件进行修改 ，如果说你想新增加接口的话，肯定会在 aidl 文件里面进行添加修改


    void setData(String data);


然后重新构建 在 Build → Rebuild Project

2.

会在 AppService 中有一个错误

IAPPServiceRemoteBinder.Stub  需要一个接口

    @Override
    public IBinder onBind(Intent intent) {
        return new IAPPServiceRemoteBinder.Stub() {

            @Override
            public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

            }

            @Override
            public void setData(String data) throws RemoteException {

            }
        };
    }


    private String data = "默认数据";


3. 然后我们回到
	
	public void setData(String data) throws RemoteException {
       	AppService.this.data = data;//添加这句，来访问
    }


4. 进行测试

    private boolean running = false;

    然后我们回到 onCreate() 中 添加一个线程
 
    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("Service started");

        new Thread(){
            @Override
            public void run() {
                super.run();

                running = true;
                while(running){
                    System.out.println(data);
                    try {
                        Thread.sleep(1000);
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

        System.out.println("Service destroy");

        running = false;
    }


    private boolean running = false;


5. 在app1 中 放进一个输入文本和按钮



    <EditText
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:id="@+id/etInput"
        android:layout_below="@+id/btnUnBindLaunchModeAppService"
        android:layout_alignParentLeft="true"
        android:layout_alignParentStart="true"
        android:layout_marginTop="22dp"
        android:layout_alignRight="@+id/btnUnBindLaunchModeAppService"
        android:layout_alignEnd="@+id/btnUnBindLaunchModeAppService"
        android:text="这是另一个应用的数据"/>

    <Button
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="同步数据到绑定的服务中"
        android:id="@+id/btnSync"
        android:layout_below="@+id/editText"
        android:layout_alignParentLeft="true"
        android:layout_alignParentStart="true" />



	在 MainActivity 中添加事件监听器
    private EditText etInput;
        etInput = (EditText) findViewById(R.id.etInput);
        findViewById(R.id.btnSync).setOnClickListener(this);

                    case R.id.btnSync:
                break;



5.然后我们在同步的时候，该如何去使用呢

在这里我们首先需要一个bind，我们如何通过bind 很方便的去执行一个远程的函数呢，我们需要把 app 中 aidl 文件拷贝一下，而且还要保持同样的包名


首先，我们在 app1 中创建一个包

app1 → New → Folder → AIDI Folder  

会多出来一个 aidl 文件夹，在里面呢，New 一个 Package 

然后去 app 中 把 aidl 文件 拷贝到 app1 中

然后重新构建 在 Build → Rebuild Project



6. 之后我们可以在 app1 里面的 MainActivity 中 定义一个

    private IAPPServiceRemoteBinder binder = null;

    然后回到 onClick()


            case R.id.btnUnBindLaunchModeAppService:
                unbindService(this);//还要加上这句话
                binder = null;
            case R.id.btnSync:
                if (binder!=null){
                    //binder.setData(etInput.getText().toString()); 这句话会有一个异常，因为远程通信
                    //
                    try {
                        binder.setData(etInput.getText().toString());
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                break;

    当我们获取到了 Service ，我们


    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        System.out.println("Bind Service");
        System.out.println(service);
        //binder = (IAPPServiceRemoteBinder) service; //在这里需要执行一个强制转换
        //在运行的时候，上面的强制类型转换 虽然名字一样，但是内存地址不一样
        //
        //所以我们换用另一种方法
        //
        binder = IAPPServiceRemoteBinder.Stub.asInterface(service);
    }


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

Android 广播接收器

使用 BroadcastReceiver 05:20

本课讲解 BroadcastReceiver 的概念以及如何使用


1.我们先创建一个 BroadcastReceiver 

	New → Other → Broadcast Receiver → 起名 MyReceiver


public class MyReceiver extends BroadcastReceiver {
    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}


如果有其他程序朝这个接收器发送消息，他就会就收到，事实上，他也是一种通信的方式


2.
创建一个按钮
    <Button
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="发送消息"
        android:id="@+id/btnSendMsg"
        android:layout_x="1dp"
        android:layout_y="260dp" />

添加事件监听器  

        findViewById(R.id.btnSendMsg).setOnClickListener(this);

添加 	public void onClick(View v)
            

            case R.id.btnSendMsg:
                Intent i = new Intent(this,MyReceiver.class);
                i.putExtra("data","ABCD");
                sendBroadcast(i);
                break;


3.在 MyReceiver 中添加

  @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("接收到了一个消息,消息的内容是：" + intent.getStringExtra("data"));
    }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
动态注册和注销 BroadcastReceiver 05:12

在有些情况下，我们不是很希望 BroadcastReceiver 始终处于监听状态

本课讲解如何实现动态注册和注销 BroadcastReceiver

1.在 AndroidManifest 中把 
        <receiver
            android:name=".MyReceiver"
            android:enabled="true"
            android:exported="true"></receiver>
  删掉，这样我们就接收不到了



2.如何通过注册，我们可以用过程序实现

	例如，再添加两个按钮，来控制注册和注销

	    <Button
	        android:layout_width="wrap_content"
	        android:layout_height="wrap_content"
	        android:text="注册接收器"
	        android:id="@+id/btnReg"
	        android:layout_x="1dp"
	        android:layout_y="300dp" />
	    <Button
	        android:layout_width="wrap_content"
	        android:layout_height="wrap_content"
	        android:text="注销接收器"
	        android:id="@+id/btnUnReg"
	        android:layout_x="1dp"
	        android:layout_y="340dp" />

	添加事件监听器  
        findViewById(R.id.btnReg).setOnClickListener(this);
        findViewById(R.id.btnUnReg).setOnClickListener(this);

	添加 	public void onClick(View v)

            case R.id.btnReg:
                if(receiver==null){
                    receiver = new MyReceiver();
                    registerReceiver(receiver,new IntentFilter(/*这里需要第三步，创建 ACTION*/));
                }
                break;
            case R.id.btnUnReg:
                if(receiver!=null){
                    unregisterReceiver(receiver);
                    receiver = null;
                }
                break;


为了防止注册多个 Receiver 我们可以在最外面一个
    private MyReceiver receiver = null;


3.回到 MyReceiver 里面

    public static final String ACTION ="com.example.fangyi.launchmode.intent.action.MyReceiver";

    然后第二步 中 注册接收器 添加
	registerReceiver(receiver,new IntentFilter(MyReceiver.ACTION));


4.在通过注册时候，我们不能用第一阶段的方式发送，即

            case R.id.btnSendMsg:
           //不要     //Intent i = new Intent(this,MyReceiver.class);
                //即在创建Intent的时候 调用MyReceiver.ACTION的方式
                //也就是隐式Intent
                Intent i = new Intent(MyReceiver.ACTION);
                i.putExtra("data","ABCD");
                sendBroadcast(i);
                break;

5.测试

		1.此时我们点击 “发送消息” 的按钮，是没有任何反应的

		2.点击 “注册接收器” ，此时是注册成功了；然后我们点击 “发送消息”，
			有反应：接收到了一个消息,消息的内容是：ABCD

		3.点击 “注销接收器”，再点击 “发送消息”，发现不会再有反应了


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
BroadcastReceiver 的优先级 06:25

本课讲解 BroadcastReceiver 的优先级以及如何进行控制


1. 我们来给 MyReceiver 在 AndroidManifest 添加注册信息 

        <receiver
            android:name=".MyReceiver"
            android:enabled="true"
            android:exported="true">
            <intent-filter>
                <action android:name="com.example.fangyi.launchmode.intent.action.MyReceiver"/>
                //添加的 action 是 MyReceiver 我们定义的
            </intent-filter>
        </receiver>
2. 之后我们在创建一个接收器 ：MyReceiver 1
添加
        System.out.println("MyReceiver 1 接收到了消息");

        在 AndroidManifest 中添加注册信息
	        <receiver
	            android:name=".MyReceiver1"
	            android:enabled="true"
	            android:exported="true">
	            <intent-filter>
	                <action android:name="com.example.fangyi.launchmode.intent.action.MyReceiver"/>
	            </intent-filter>
	        </receiver>

当我们朝接收器发送信息，，，，理论上来讲两个接收器都会发送信息

测试表明，后注册的 MyReceiver 1 先提示信息

我们可以改变这一顺序

3.测试表明，后注册的 MyReceiver 1 先提示信息

	我们可以改变这一顺序

	在 <intent-filter> 中有一个参数 android:priority="X"

	比如在 

	 		<receiver
		            android:name=".MyReceiver1"
		            android:enabled="true"
		            android:exported="true">
		            <intent-filter android:priority="10">
		                <action android:name="com.example.fangyi.launchmode.intent.action.MyReceiver"/>
		            </intent-filter>
		        </receiver>


	        <receiver
	            android:name=".MyReceiver"
	            android:enabled="true"
	            android:exported="true">
	            <intent-filter android:priority="9">
	                <action android:name="com.example.fangyi.launchmode.intent.action.MyReceiver"/>
	                //添加的 action 是 MyReceiver 我们定义的
	            </intent-filter>
	        </receiver>

	测试表明，优先级是 10 的先接收到消息，并输出 

	如果把 MyReceiver1 优先级 改成 8， 测试：优先级 9的先接收到消息


	即：在参数中，，数字谁的高，谁的优先级就高



4.如果一个 优先级高的 MyReceiver，不想让后面的 MyReceiver 接收到消息，

我们可以在 高 优先级 的 MyReceiver 中的  public void onReceive(Context context, Intent intent) 中添加一个方法

        abortBroadcast();

从这里直接把广播给中断了

但其实，在上述代码没改变的情况下，会抛出一个异常：他尝试中断的一个非顺序的 Broadcast
也就是说，我们通过

            case R.id.btnSendMsg:
                Intent i = new Intent(MyReceiver.ACTION);
                i.putExtra("data","ABCD");
                // sendBroadcast(i);//这种方式所发送的Broadcast是不能够被中断的，所以我们需要换函数
                
                sendOrderedBroadcast(i, null);//第一个参数是intent，第二个参数是权限，我们不需要所以写null
                break;

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

Android 日志系统


使用日志 API 06:01

本课讲解在 Android 系统中如何使用日志 API 进行日志输出，内容包括 System.out、System.err、Log.v、Log.d、Log.i、Log.w、Log.e 的使用。


1.我们在 MainActivity 中 添加 

	他其实是java本身提供的输出信息，他的日志级别是 “普通日志” 
    sout →
        System.out.println("普通日志Info");
	

	此外，java还提供了 “错误日志” 输出信息
	serr →
       System.err.println("错误日志Warn");

两种信息样式不一样，I/System.out:普通日志Info	//黑色
					W/System.out:错误日志Warn	//蓝色

					Warn 比 Info 级别要高

2.Android 还提供了有非常详细的日志级别的信息

在最上面创建一个
    private static String TAG = "MainActivity";

按优先级 从高到低
        Log.e(TAG,"错误信息");	//红色的
        Log.w(TAG,"警告信息");	//蓝色
        Log.i(TAG,"普通信息");	//黑色
        Log.d(TAG,"调试信息");	//黑色
        Log.v(TAG,"无用信息");//给程序员唠叨用的//黑色

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
日志分类 05:08

本课讲解在 Android Studio 中如何对日志进行分类呈现，便于开发调试。

Edit Filter Configuration//编辑滤镜的配置信息

1.可以添加一个自定义的标签
	Name:MainActivity //只输出MainActivity的信息，所以名字这么写

	by Log Tag(regex):	MainActivity

	然后点击ok
	
	输出
        E/MainActivity：错误信息	//红色的
        W/MainActivity：警告信息	//蓝色
        I/MainActivity：普通信息	//黑色

2. 
	by Log Message (regex): 信息

	输出
		E/MainActivity：错误信息	//红色的
        W/MainActivity：警告信息	//蓝色
        I/MainActivity：普通信息	//黑色

    比如不是信息的他也会输出
    比我我们在主程序中添加一个
        Log.e("MSG","其他信息");

    输出
		E/MainActivity：错误信息	//红色的
        W/MainActivity：警告信息	//蓝色
        I/MainActivity：普通信息	//黑色
        E/MSG：其他信息	

        即只要 出现信息两个字，他就会输出

3.
	by Package Name://根据程序的包名，他会全自动的添加

4.
	by PID:	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

使用 DDMS 查看日志 04:49

本课讲解如何使用 Android Studio 中的 DDMS 以及独立的 DDMS 查看日志。

D:\Android\sdk\tools\ddms.bat


//机器人小图标  Android Device Monitor //设备监视器	DDMS

点开后，我们可以在下面看到一个 LogCat 的窗口

我们可以在那个 加号 添加分类信息

1.
	Filter Name:
	by log Tag:
	~~~




////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
Android 权限系统

请求权限实例 05:18
本课用一个实例演示如何请求使用系统内置的某一个权限。

重点是：类名千万别和包名起一样的

我们添加一个 Activity,然后加一个 WebView

1.
    private WebView wv;

        wv = (WebView) findViewById(R.id.wv);
        wv.loadUrl("http://www.baidu.com");

2.
	在AndroidManifest中进行配置 中加权限
    <uses-permission android:name="android.permission.INTERNET" />



////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


为代码添加权限检查 05:37

本课介绍如何在代码中为应用添加权限检查功能。


1.创建一个类 Hello.class


public class Hello {
    public static final String PLERISSION_SAY_HELLO="com.example.fangyi.launchmode.permission.SAY_HELLO";
    //需要检查一个权限， 定义一个静态的字符串，他的值是 我们的包名，.permission.SAY_HELLO

    public static void sayHello() {

    }
}



2.我们需要为这个类在  AndroidManifest 中进行注册一个新的权限

    <permission android:name="com.example.fangyi.launchmode.permission.SAY_HELLO" />

3.接下来我们想检查执行这个程序的代码，是否拥有这个权限
所以我们需要一个 Context context 对象，我们才可以访问到全局的属性
    public static void sayHello(Context context) {
    	int checkResult = context.checkCallingOrSelfPermission(PLERISSION_SAY_HELLO);
    	//执行这个程序的代码是否拥有这个权限
    	if(checkResult!= PackageManager.PERMISSION_GRANTED/*拒绝DENIED 和 访问GRANTED*/){
    		throw new SecurityException("执行sayHello方法需要有：com.example.fangyi.launchmode.permission.SAY_HELLO 权限");	
    		//不能访问，我们可以抛出一个安全的异常
        }
                System.out.println("Hello ABCD");//有权限，就会输出
    }


4.我们可以在 MainActivity 中 通过
	        Hello.sayHello(this);
5.输出错误信息
		
		执行sayHello方法需要有：com.example.fangyi.launchmode.permission.SAY_HELLO 权限

6.如何来填写权限请求
	AndroidManifest 中进行添加
    <uses-permission android:name="com.example.fangyi.launchmode.permission.SAY_HELLO"></uses-permission>
输出：
	Hello ABCD

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

为基本组件添加权限检查 07:19

本课讲解如何为 Android 基本控件添加权限检查功能。

1.	添加一个 新的 Activity （MyAt2）

	我们在 AndroidManifest 中进行注册 权限

	添加
	<permission android:name="com.example.fangyi.launchmode.permission.MyAty2" />

	然后给在指定的Activity权限，你想启动 Activity 必须拥有这个权限
        <activity
            android:name=".MyAty2"
            android:label="@string/title_activity_my_aty2"
            android:theme="@style/AppTheme.NoActionBar"
            android:permission="com.example.fangyi.launchmode.permission.MyAty2"></activity>

2.在 MainActivity 中添加代码
	按钮，事件监听器
        findViewById(R.id.btnStartMyAty2).setOnClickListener(this);
            

            case R.id.btnStartMyAty2:
                startActivity(new Intent(MainActivity.this,MyAty2.class));
                break;



3.我们在其他程序中启动，权限 在app1 中新建一个按钮 btnStartMyAty2

	我们通过 action 来启动 app 中的MyAt2

	在 app 中的 AndroidManifest 中我们配置
	        <activity
	            android:name=".MyAty2"
	            android:label="@string/title_activity_my_aty2"
	            android:theme="@style/AppTheme.NoActionBar"
	            android:permission="com.example.fangyi.launchmode.permission.MyAty2">
	            <intent-filter>//声明一个 category
	                <category android:name="android.intent.category.DEFAULT"/>
	                <action android:name="com.example.fangyi.launchmode.intent.action.MyAty2"/>
	            </intent-filter>
	        </activity>





	在app1 中  MainActivity 中添加 事件监听器

        findViewById(R.id.btnStartMyAty2).setOnClickListener(this);

            case R.id.btnStartMyAty2:
                startActivity(new Intent("com.example.fangyi.launchmode.intent.action.MyAty2"));
                break;

4.java.lang.SecurityException: Permission Denial: starting Intent { act=com.example.fangyi.launchmode.intent.action.MyAty2 cmp=com.example.fangyi.launchmode/.MyAty2 } from ProcessRecord{f24ac01 2998:com.example.fangyi.app1/u0a57} (pid=2998, uid=10057) requires com.example.fangyi.launchmode.permission.MyAty2

安全错误，没有权限

在 app1 中 AndroidManifest 文件里面写上一个使用说明
    <uses-permission android:name="com.example.fangyi.launchmode.permission.MyAty2"></uses-permission>



////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
使用 Fragment 11:38

本课讲解如何创建和使用 Fragment。

属于轻量级的界面切换，即同一个应用内部进行切换


1.在 fragment_main.xml 创建一个按钮

    <Button
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="呈现另一个Fragment"
        android:id="@+id/btnShowAnotherFragment"/>

2.我们在 layout 中添加一个布局
	
	New → Layout Resource File

	File name：fragment_another

	我们在里面添加一个文本框
	    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="这是另一个Fragment"
        android:id="@+id/textView" />

3.我们想呈现一个Fragment，需要一个类

		AnotherFragment
	继承 自动 	android.support.v4.app.Fragment


/**
 * Created by FANGYI on 2016/2/2.
 */
public class AnotherFragment extends android.support.v4.app.Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_another,container,false);//初始化这个布局，从外界传进来的

        return root;
    }
}


4.在 MainActivity 中添加
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .addToBackStack(null)
                    .add(R.id.fragment, new MainActivityFragment())
                    .commit();
        }





5.在 MainActivityFragment 中

public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        rootView.findViewById(R.id.btnShowAnotherFragment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.fragment, new AnotherFragment()).commit();
            }
        });

        return rootView;
    }
}


两个fragment重叠
	1、先将你的activity_main里的fragment改为FrameLayout将name那句删除。2、在MainActivity的oncreate的super下面增加
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .addToBackStack(null)
                    .add(R.id.fragment, new MainActivityFragment())
                    .commit();
        }


	。之于你那个为什么会出现我可能解释的会不是很好我的想法是你的main里就已经是添加了mainactivityfragment了所以之后再替换也就只
	是后面添加了我尝试了 一下xml不改变就只增加2的内容这时就会出现了两个mainfragment而且下面的那个fragment就可以被替换因为第二个
	的fragment是由2这个写法写的。
		http://www.cnblogs.com/mengdd/archive/2013/01/08/2851368.html
	还是看看这个吧   说明fragment的



6.添加后退            
	在fragment_another 中
	    
	    <Button
	        android:layout_width="wrap_content"
	        android:layout_height="wrap_content"
	        android:text="后退"
	        android:id="@+id/btnBack" />

	在 AnotherFragment 中添加
	
        View root = inflater.inflate(R.layout.fragment_another,container,false);
        root.findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();//这句话
            }
        });

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


带侧边栏的 Activity 13:05

本课讲解如何创建一个带侧边栏的 Activity 以及如何使用它。



1.我们新建一个Activity，
	New → Activity → Navigation Drawer Activity → SliderActivity


2.我们在 fragment_main 中添加按钮

    <Button
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="启动侧边栏的Activity"
        android:id="@+id/btnStartSliderActivity"
        android:layout_below="@+id/btnShowAnotherFragment"
        android:layout_alignParentStart="true" />

3.在 MainActivityFragment 中添加 
        
        rootView.findViewById(R.id.btnStartSliderActivity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), SliderActivity.class));
            }
        });

        //这样就有侧边栏了

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



Tabbed Activity 08:59

本课讲解如何创建一个 Tabbed Activity 并使用它。

New → Activity → Tabbed Activity → Tabs (Navigatiion Style：Action Bar Tabs (with ViewPager))


1.fragment_main.xml 写一个按钮
	    <Button
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="启动Tabbed Activity"
        android:id="@+id/btnStartTabbedActivity"
        android:layout_below="@+id/btnStartSliderActivity"
        android:layout_alignStart="@+id/btnShowAnotherFragment" />

2.在
        rootView.findViewById(R.id.btnStartTabbedActivity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Tabs.class));
            }
        });

3.fragment_tabs.xml
可以添加一个自定义的按钮，，，三个界面 都有按钮，，使用同一个布局资源

4.如何来自定义布局

在 Tabs 中  关键代码在


 public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override									//这句话重点
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override									//这句话重点
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
            }
            return null;
        }
    }


5.新建类 Image_1Fm 代码


package com.example.fangyi.learnfragment;

import android.graphics.ImageFormat;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by FANGYI on 2016/2/7.
 */
public class Image_1Fm extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ImageView iv = new ImageView(getActivity());
        iv.setImageResource(R.drawable.img3);
        return iv;
    }
}



6.接下来我们回到 Tabs ， 来到 4 中的 代码段里


        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    return new Image_1Fm();
                case 1:
                    return new Image_2Fm();
                case 2:
                    return new Image_3Fm();
            }

            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return null;
        }


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
LinearLayout 06:19

本课介绍线性布局的定义与使用，并介绍在线性布局中 weight 属性的意义与使用方式

做了一个简单的浏览器界面

1.在

	content_main 里面改 这个

		<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
   			xmlns:tools="http://schemas.and
   		
   		<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    		xmlns:tools="http://schemas.and

 
    android:orientation="vertical"		//垂直
    android:orientation="horizontal"	//水平


	andorid:background="#f00"	//红色
    android:background="#0f0"	//绿色
    android:background="#00f"	//蓝色


    android:layout_width="1"	//分割富集容器比重
    android:layout_width="1"
    android:layout_width="1"

    屏占比：1:1:1


2. 在 layout 中创建一个 browser.xml

添加
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical" android:layout_width="match_parent"
    android:layout_height="match_parent">

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="horizontal">


        <EditText
            android:layout_weight="1"////分割富集容器比重的属性
            android:layout_width="wrap_content"
            android:layout_height="wrap_content" />

        <Button//不参与分割
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Go"/>
    </LinearLayout>
        <WebView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_weight="1"></WebView>
</LinearLayout>



///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///

用代码控制子对象 07:39

本课介绍如何使用代码控制布局子对象的添加与删除。

    private LinearLayout root;
    private Button btnClickme;




        root = new LinearLayout(this);
        setContentView(root);
        btnClickme = new Button(this);
        btnClickme.setText("Click me");
        // root.addView(btnClickme);
        root.addView(btnClickme,300,200);//调整按钮大小

效果一：root.addView(btnClickme, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        //宽度铺满富集容器，


效果一：LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		root.addView(btnClickme, lp);//把布局放里面


添加多个子对象：
        root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);//调整垂直布局
        setContentView(root);

        for (int i= 0; i < 5; i++) {	
            btnClickme = new Button(this);
            btnClickme.setText("Click me");
            btnClickme.setOnClickListener(this);//跳到A：


            //设定布局参数
效果一：    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.weight = 1;//五个设定1:1:1:1:1分割


           lp.leftMargin = 200;//相对布局左边
           lp.topMargin = 500;//相对布局上边
            root.addView(btnClickme, lp);


效果2：     root.addView(btnClickme, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        }
        //五个按钮
设定比重：


A：		@Override
		public void onClick(View v) {
			root.removeView(v);
		}



///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

RelativeLayout 09:34

本课介绍如何定义与使用相对布局。

1.	创建一个 新的  Blank Activity → RelativeLayoutAty
	把它当成启动的 Activity ： Launcher Activity（勾上）

2.
	我们在 AndroidManifest 中的 MainActivity 启动 <intent-filter> 中的部分 注释掉

	此时，我们就从 RelativeLayoutAty 中启动了

在 protected void onCreate() 函数中添加 代码布局


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

FrameLayout 06:06

本课讲解 FrameLayout 的意义并以实例演示如何使用。

1.	创建一个 新的  Blank Activity → FrameLayoutAty
	把它当成启动的 Activity ： Launcher Activity（勾上）

2.
	我们在 AndroidManifest 中的 RelativeLayoutAty 启动 <intent-filter> 中的部分 注释掉

	此时，我们就从 FrameLayoutAty 中启动了


3.
	我们 可以来到 activity_relative_layout_aty.xml 中 把布局改成 FrameLayout

给主布局一个id
		android:id="@+id/root"


添加图片
	
	//先呈现一个图片，让他俩有切换的效果
	<ImageView
		android:layout_width="match_parent"
		android:layout_height="match_parent"
		android:src="@drawable/img1"
		android:id="@+id/ivA"/>

	<ImageView
		android:layout_width="match_parent"
		android:layout_height="match_parent"
		android:src="@drawable/img2"
		android:visiibility="invisible"//不可见
		android:id="@+id/ivB"/>

4.
	来到主程序 FrameLayoutAty 

    private FrameLayoutAty root;
    private ImageView ivA,ivB;

    在 onCreate() 函数中添加

    	root = (FrameLayoutAty) findViewById(R.id.root);
    	ivA = (ImageView) findViewById(R.id.ivA);
    	ivB = (ImageView) findViewById(R.id.ivB);

    	showA();

    	root.setOnClickListener(new View.OnClickListener() {
    		@Override
    		public void onClick(View v) {
    			//判断
    			if(ivA.getVisibility()==View.VISIIBLE) {
    				showB();
    			} else {
    				showA();
    			}
    		}
    	});

    private void showA() {
    	ivA.setVisibility(View.VISIIBLE);//可见
    	ivB.setVisibility(View.INVISIIBLE);//不可见
    }
    private void showB) {
    	ivA.setVisibility(View.INVISIIBLE);//不可见
    	ivB.setVisibility(View.VISIIBLE);//可见
    }


//效果，先呈现 A，然后点击一下图片，出现 B
//	在点击 B 呈现 A


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

使用RecyclerView 08:58

本课讲解如何创建RecyclerView、如何为RecyclerView适配数据源

1.如果想使用 RecyclerView 需要 

	在主工程右键 Open Module Settings
	Dependencies //依赖项

	添加一个库
		recyclerview-v7

2.
public class MainActivity extends AppCompatActivity {

    private RecyclerView rv;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        rv = new RecyclerView(this);
        setContentView(rv);//把 rv 当成 Activity 的布局

        rv.setLayoutManager(new LinearLayoutManager(this));//使用线性布局

        //填充内容
        rv.setAdapter(new RecyclerView.Adapter() {

            //使用onCreateViewHolder()，创建一个自定义类
            class ViewHolder extends RecyclerView.ViewHolder {

                private TextView tv;//绑定一个子对象的视图，用他呈现一些数据

                public ViewHolder(TextView itemView) {
                    super(itemView);

                    tv = itemView;//通过这种方式，可以和ViewHolder产生关联
                }

                public TextView getTv() {
                    return tv;
                }
            }

            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return new ViewHolder(new TextView(parent.getContext()));
            }

            @Override//第二个参数是索引，当前显式的是哪一条
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                ViewHolder vh = (ViewHolder) holder;
//                vh.getTv().setText("Item"+position);
                vh.getTv().setText(data[position]);
            }
            
            @Override//获取 RecycleView 子对象的数量，比如返回10，创建了10个子对象
            public int getItemCount() {
//                return 1000;
                return data.length;
            }
            
            private String[] data = new String[]{"Hello","fangyi1896@gmail.com","fangyi186@outlook.com"};
        });
    }
}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

使用资源文件自定义列表项 08:39

本课讲解如何创建列表项资源以及如何解析列表项资源并应用在列表中
1.
	新建 list_cell.xml 添加

	//标题
    <TextView
        android:layout_width="fill_parent"
        android:layout_height="wrap_content"
        android:textAppearance="?android:attr/textAppearanceLarge"
        android:text="Large Text"
        android:id="@+id/tvTitle"
        android:layout_gravity="center_horizontal" />
    //文本
    <TextView
        android:layout_width="fill_parent"
        android:layout_height="wrap_content"
        android:text="New Text"
        android:id="@+id/tvContent" />

2.
	我们回来程序里去 MainActivity 中的 RecyclerView.Adapter()，转移到单独的一个文件里去

					在 RecyclerView.Adapter() {
						中 右键  Refactor → Move 
					}




		public class MainActivity extends AppCompatActivity {

		    private RecyclerView rv;

		    @Override
		    protected void onCreate(Bundle savedInstanceState) {
		        super.onCreate(savedInstanceState);

		        rv = new RecyclerView(this);
		        setContentView(rv);//把 rv 当成 Activity 的布局

		        rv.setLayoutManager(new LinearLayoutManager(this));//使用线性布局

		        //填充内容
		        rv.setAdapter(new MyAdapter());
		    }

		}





	之后我们来继续移动，  在 MyAdapter 上右键 Refactor → Move 创建了一个 MyAdapter 类
		

		class MyAdapter extends RecyclerView.Adapter {

		    //使用onCreateViewHolder()，创建一个自定义类
		    class ViewHolder extends RecyclerView.ViewHolder {

		        private View root;//绑定一个子对象的视图，用他呈现一些数据
		        private TextView tvTitle, tvContent;

		        public ViewHolder(View root) {
		            super(root);

		            tvTitle = (TextView) root.findViewById(R.id.tvTitle);
		            tvContent = (TextView) root.findViewById(R.id.tvContent);
		        }

		        public TextView getTvTitle() {
		            return tvTitle;
		        }

		        public TextView getTvContent() {
		            return tvContent;
		        }
		    }

		    @Override
		    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_cell, null));//根据一个资源进行创建,布局解释器
		    }

		    @Override//第二个参数是索引，当前显式的是哪一条
		    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
		        ViewHolder vh = (ViewHolder) holder;

		        CellData cd = data[position];

		        vh.getTvTitle().setText(cd.title);
		        vh.getTvContent().setText(cd.content);
		    }

		    @Override//获取 RecycleView 子对象的数量，比如返回10，创建了10个子对象
		    public int getItemCount() {
		//                return 1000;
		        return data.length;
		    }

		    private CellData[] data = new CellData[]{new CellData("Hello","fangyi1896@gmail.com"), new CellData("大笨蛋","fangyi186@outlook.com")};
		    //放数据的地方
		}



3.我们创建 一个 类 方数据，CellData 类

public class CellData {

    public CellData(String title, String content) {
        this.title = title;
        this.content = content;
    }
    
    public String title = "title";
    public String content = "content";
}


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


RecyclerView的布局样式 03:42

本课讲解如何为列表RecyclerView适配样式，并演示适配样式后的结果



1.
	我们先把 list_cell <TextView 布局改一下
       
       android:layout_width="fill_parent" 	→ 	android:layout_width="wrap_content"

2. 
	我们来到 主程序， 把布局参数改一下

效果一：		rv.setLayoutManager(new LinearLayoutManager(this));//使用线性布局,默认 垂直排列数据

		//更改下列布局

效果二：       rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
				//使用线性布局， 水平方向排列数据，左边是第一位，不反转 false


效果三：       rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
				//使用线性布局， 水平方向排列数据，右边是第一位


3.
	表格布局

	        rv.setLayoutManager(new GridLayoutManager(this,3));
	        //第一个参数传进去this，第二个参数传进去多少列。如果水平的话，第二个参数就是多少行
	        //不能拖动是因为数据不够多

	//还可以调整布局走向
	

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

Android 常用控件


下拉列表 00:33

本课讲解如何创建下拉列表、如何为下拉列表适配数据源，以及如何侦听下拉列表的选择事件


1.
	添加一个 下拉控件 → Spinner

   <Spinner
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:id="@+id/spinner"
        android:layout_gravity="center_horizontal" />


2.
  	在主程序中添加
   
    private Spinner s;


	s = (Spinner) findViewById(R.id.spinner);

	        //如果想呈现下拉列表，我们设定一个数据源
			//第二个参数，我们传入一个列表项资源的ID,第三个参数是数据
	        s.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,new String[]{"大笨蛋","fangyi186@outlook.com","fangyi1896@gmail.com"}));

3.
	如何侦听用户的选择
	比如用户选择 "大笨蛋" ，如何能跳过去
    private Spinner s;
    private String[] dataSource = new String[]{"大笨蛋","fangyi186@outlook.com","fangyi1896@gmail.com"};


		s = (Spinner) findViewById(R.id.spinner);

		        //如果想呈现下拉列表，我们设定一个数据源
		        //第二个参数，我们传入一个列表项资源的ID,第三个参数是数据
		        s.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,dataSource));

		        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
		            @Override
		            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
		                System.out.println("用户选择的是"+dataSource[position]);
		            }

		            @Override
		            public void onNothingSelected(AdapterView<?> parent) {

		            }
		        });


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

日期选择器 04:59

本课讲解如何呈现日期选择器以及如何侦听日期选择器的选中事件


1.新建 一个 Activity → name:ChooseAData

2.在 AndroidManifest 中配置，把 MainActivity 不当做启动 Activity
        <activity
            android:name=".MainActivity"
            android:label="@string/app_name"
            android:theme="@style/AppTheme.NoActionBar">
            // <intent-filter>
            //     <action android:name="android.intent.action.MAIN" />

            //     <category android:name="android.intent.category.LAUNCHER" />
            // </intent-filter>
        </activity>

3.修改成线性布局


	在 content_choose_adata.xml 中添加按钮
	    <Button
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="0000-00-00"
        android:id="@+id/btnChooseData" />



4.
	在 ChooseAData 中添加

	private Button btnChooseData;


        btnChooseData = (Button) findViewById(R.id.btnChooseData);
        btnChooseData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //在这里呈现一个日期选择器
                new DatePickerDialog(ChooseAData.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        //月份从0快开始的                        
                        String theDate = String.format("%d-%d-%d", year, monthOfYear + 1, dayOfMonth);
						
						//把数字呈现在按钮里
                        btnChooseData.setText(theDate);
                        System.out.println(theDate);
                    }
                },2016,1,10).show();
            }
        });

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///
///
时间选择器 03:54

本课讲解如何创建并呈现时间选择器以及如何使用并侦听Android5时间选择器事件


1.新建 一个 Activity → name: btnChooseTime

2.在 AndroidManifest 中配置，把 MainActivity 不当做启动 Activity 和 ChooseAData

3.修改成线性布局

	在 content_choose_time.xml 中添加按钮
    <Button
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="00:00"
        android:id="@+id/btnChooseTime" />



4.
	在 ChooseAData 中添加

    private Button btnChooseTime;


        btnChooseTime = (Button) findViewById(R.id.btnChooseTime);
        btnChooseTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(ChooseTime.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String theTime = String.format("%d:%d",hourOfDay,minute);
                        System.out.println(theTime);
                        btnChooseTime.setText(theTime);
                    }
                },0,0,true).show();//小时：0，分钟：0，是否24小时制：是
            }
        });


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


单项选择 04:40

本课讲解如何使用单选按扭组以及单选按扭，并使用单选按扭组开发一个简单的选择题程序


1.新建 一个 Activity → name: ChooseTime

2.在 AndroidManifest 中配置，把 MainActivity 不当做启动 Activity 和 ChooseAData 和 ChooseTime

3.
	在 content_sngle_choose.xml 中添加 RadioGroup
	可以在里面存放多个 RadioButton
    
	然后我们拖进 RadioButton




<LinearLayout
  
    android:orientation="vertical"

	<TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:textAppearance="?android:attr/textAppearanceLarge"
        android:text="Large Text"
        android:id="@+id/textView" />

    <RadioGroup
        android:layout_width="wrap_content"
        android:layout_height="wrap_content">

        <RadioButton
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="New RadioButton"
            android:id="@+id/rbA"/>
        <RadioButton
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="New RadioButton"
            android:id="@+id/rbB"/>
        <RadioButton
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="New RadioButton"
            android:id="@+id/rbC"/>
        <RadioButton
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="New RadioButton"
            android:id="@+id/rbD"/>

    <Button
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="提交"
        android:id="@+id/btnSubmit" />

    </RadioGroup>

//配置这些就有了单选的效果





4.
	在 SingleChoose 中添加

    private Button btnSubmit;
    private RadioButton rbA;


        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        rbA = (RadioButton) findViewById(R.id.rbA);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rbA.isChecked()) {
                    Toast.makeText(SingleChoose.this,"所选是正确的",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(SingleChoose.this,"所选是错误的",Toast.LENGTH_SHORT).show();
                }
            }
        });


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



多项选择 05:44

本课讲解如何使用多选按扭(CheckBox)，如何侦听多选按钮选择状态改变事件

1.新建 一个 Activity → name: MulChoose

2.在 AndroidManifest 中配置，把 MainActivity 不当做启动 Activity 和 ChooseAData 和 ChooseTime 和 SingChoose

3.	在 content_mul_choose.xml 中添加 CheckBox
	
	CheckBox 不像 单选






<LinearLayout
  
    android:orientation="vertical"
    
    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:textAppearance="?android:attr/textAppearanceLarge"
        android:text="你最喜欢哪些食物（多选）"
        android:id="@+id/textView2" />

    <CheckBox
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="肉夹馍"
        android:id="@+id/cb1" />

    <CheckBox
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="苹果派"
        android:id="@+id/cb2" />

    <CheckBox
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="红烧肉"
        android:id="@+id/cb3" />

    <CheckBox
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="鸡大腿"
        android:id="@+id/cb4" />

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:textAppearance="?android:attr/textAppearanceLarge"
        android:text="Large Text"
        android:id="@+id/tvResult" />

    </RadioGroup>

//配置这些就有了单选的效果



4.
	在 MulChoose 中添加

    private CheckBox cb1,cb2,cb3,cb4;
    private TextView tvResult;

 @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mul_choose);

        tvResult = (TextView) findViewById(R.id.tvResult);

        cb1 = (CheckBox) findViewById(R.id.cb1);
        cb2 = (CheckBox) findViewById(R.id.cb2);
        cb3 = (CheckBox) findViewById(R.id.cb3);
        cb4 = (CheckBox) findViewById(R.id.cb4);

        //选择状态发生改变，用this实现接口
        cb1.setOnCheckedChangeListener(this);
        cb2.setOnCheckedChangeListener(this);
        cb3.setOnCheckedChangeListener(this);
        cb4.setOnCheckedChangeListener(this);
    }


@Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        String str = "你喜欢：";
        if (cb1.isChecked()) {
            str += cb1.getText() + ",";
        }
        if (cb2.isChecked()) {
            str += cb2.getText() + ",";
        }
        if (cb3.isChecked()) {
            str += cb3.getText() + ",";
        }
        if (cb4.isChecked()) {
            str += cb4.getText();
        }

        tvResult.setText(str);
    }



//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


Android用户界面优化-Android SlidingMenu菜单栏程序设计开发






https://www.zybuluo.com/natsumi/note/135382







3. 导入类库的过程

视频里面导入了SlidingMenu和ActionBarSherlock两个包，说是因为SlidingMenu/Readme.md里说要“set up with ActionBarSherlock” 
但是，我只导入了SlidingMenu就可以实现那些基础功能了，并不知道ActionBarSherlock有什么卵用= =

参考：https://github.com/phodal/learning-android 
下面正式开始导入的步骤：

新建空Activity工程UsingSlidingMenu
从github上下载SlidingMenu-master.zip解压
将其中的SlidingMenu-master/library目录整个拷贝到UsingSlidingMenu工程文件夹下的任意位置，我是拷贝到了UsingSlidingMenu/libraries/library，并将library改名为SlidingMenu，即UsingSlidingMenu/libraries/SlidingMenu
在AS中通过Import Module的方式导入库：File--Import Module，在弹出的框中，通过浏览的方式填入待导入库的路径AS工作空间/UsingSlidingMenu/libraries/SlidingMenu
修改SlidingMenu里的build.gradle文件（并不是太懂gradle，看着网上的各种资料以及自己试着改，下面两个注释了必须改的不该就不能成功build，其他不改好像也没什么事儿）
buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:1.1.0'//必须改
    }
}
apply plugin: 'android-library'
dependencies {
    compile 'com.android.support:support-v4:13.0.0'
}
android {
    compileSdkVersion 21//参考learning-android项目做了修改
    buildToolsVersion "21.1.2"//必须改，这里是参考app目录里的build.gradle改的
    defaultConfig {
        minSdkVersion 14//参考learning-android项目做了修改
        targetSdkVersion 21//参考learning-android项目做了修改
    }
    sourceSets {
        main {
            java.srcDirs = ['src']
            resources.srcDirs = ['src']
            aidl.srcDirs = ['src']
            renderscript.srcDirs = ['src']
            res.srcDirs = ['res']
            assets.srcDirs = ['assets']
            manifest.srcFile 'AndroidManifest.xml'
        }
    }
}
不要修改项目根目录下的build.gradle文件中的dependencies里的内容，因为注释写着“Do not place your application dependencies here; they belong in the individual module build.gradle files”
修改app目录下的build.gradle文件
dependencies {
    compile fileTree(dir: 'libs', include: ['*.jar'])
    compile 'com.android.support:appcompat-v7:21.0.3'
    compile project(':SlidingMenu')//加上这句～
}
修改了Gradle文件后代码编辑器上方会提示同步，点sync now同步
网上有些资料说要修改settings.gradle,但是用import module的方式导入的话好像AS会自动修改这个配置文件
include ':app', ':SlidingMenu'
project(':SlidingMenu').projectDir = new File('libraries/SlidingMenu')
4. 开始开发吧～

下面就可以按照视频愉快的写代码了～ 
菜单背景色是打代码的时候少打了两个9～从灰色变粉色萌萌哒～

MainActivity.java

package com.jikexueyuan.usingslidiingmenu;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
public class MainActivity extends ActionBarActivity {
    private SlidingMenu slidingMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        slidingMenu = new SlidingMenu(this);//创建对象
        slidingMenu.setMode(SlidingMenu.LEFT);//设定模式，SlidingMenu在左边
        slidingMenu.setBehindOffsetRes(R.dimen.sliding_menu_offset);//配置slidingmenu偏移出来的尺寸
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);//全屏都可以拖拽触摸，打开slidingmenu
        slidingMenu.attachToActivity(this,SlidingMenu.SLIDING_CONTENT);//附加到当前的Aty上去
        slidingMenu.setMenu(R.layout.slidingmenu);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //重写KeyDown事件，菜单键按下也能拉出slidingmenu
        switch(keyCode){
            case KeyEvent.KEYCODE_MENU:
                slidingMenu.toggle(true);
                break;
            default:
                break;
        }
        return super.onKeyDown(keyCode, event);
    }
}
下面是布局文件activity_main.xml

<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical" android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="#ff9999">
    <com.jeremyfeinstein.slidingmenu.lib.SlidingMenu
        android:layout_width="fill_parent"
        android:layout_height="fill_parent"
        android:id="@+id/slidingmenulayout">
        <Button
            android:layout_width="fill_parent"
            android:layout_height="wrap_content"
            android:text="Click me"/>
    </com.jeremyfeinstein.slidingmenu.lib.SlidingMenu>
</LinearLayout>


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////



















































































































































































