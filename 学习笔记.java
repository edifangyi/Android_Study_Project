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


finish();直接把当前的 activity 收回

Activity.finish()
Call this when your activity is done and should be closed. 
在你的activity动作完成的时候，或者Activity需要关闭的时候，调用此方法。
当你调用此方法的时候，系统只是将最上面的Activity移出了栈，并没有及时的调用onDestory（）方法，其占用的资源也没有被及时释放。
因为移出了栈，所以当你点击手机上面的“back”按键的时候，也不会找到这个Activity。





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


定义框 框中文字字体是否全为大写
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

4.java.lang.SecurityException: Permission Denial: 

starting Intent { act=com.example.fangyi.launchmode.intent.action.MyAty2 cmp=com.example.fangyi.launchmode/.MyAty2 } from 
ProcessRecord{f24ac01 2998:com.example.fangyi.app1/u0a57} (pid=2998, uid=10057) requires com.example.fangyi.launchmode.permission.MyAty2

安全错误，没有权限

在 app1 中 AndroidManifest 文件里面写上一个使用说明
    <uses-permission android:name="com.example.fangyi.launchmode.permission.MyAty2"></uses-permission>



////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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

package com.example.fangyi.theviewanimation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by FANGYI on 2016/2/12.
 */
public class AnotherFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_another,container,false);//初始化这个布局，从外界传进来的

        root.findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();//这句话
            }
        });
        
        return root;
    }
}




4.
	在 MainActivity 中添加
	   
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
                getFragmentManager().beginTransaction()
                .addToBackStack(null)//返回键设置
                .replace(R.id.fragment, new AnotherFragment())
                .commit();
            }
        });

        return rootView;
    }
}


两个fragment重叠
	1、先将你的 content_main 里的 fragment 改为 FrameLayout 将name那句删除。


			<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
			    xmlns:app="http://schemas.android.com/apk/res-auto"
			    xmlns:tools="http://schemas.android.com/tools"
			    android:id="@+id/fragment"

			    android:layout_width="match_parent"
			    android:layout_height="match_parent"
			    app:layout_behavior="@string/appbar_scrolling_view_behavior"
			    tools:layout="@layout/fragment_main" />




	2、在MainActivity的oncreate的super下面增加


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
                            et = (EditText) findViewById(R.id.er);
        show = (TextView) findViewById(R.id.show);

        findViewById(R.id.writeBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileOutputStream fos = openFileOutput(filename, Context.MODE_PRIVATE);
                    OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
                    osw.write(et.getText().toString());
                    //输出所有缓冲区的内容
                    osw.flush();
                    fos.flush();

                    //关闭顺序，fos先打开的，后打开的先关闭，逐层关闭
                    osw.close();
                    fos.close();
                    Toast.makeText(getApplicationContext(), "写入完成", Toast.LENGTH_LONG).show();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


        findViewById(R.id.readBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileInputStream fis = openFileInput(filename);
                    InputStreamReader is = new InputStreamReader(fis, "UTF-8");
                    char input[] = new char[fis.available()];//获取文件长度
                    is.read(input);

                    is.close();
                    fis.close();
                    String readed = new String(input);

                    show.setText(readed);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });.makeText(SingleChoose.this,"所选是错误的",Toast.LENGTH_SHORT).show();
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


Android 用户界面优化 - Android SlidingMenu 菜单栏程序设计开发


Android Studio导入SlidingMenu类库
https://www.zybuluo.com/natsumi/note/135382

https://github.com/jfeinstein10/SlidingMenu

1.
	
	在 MainActivity 中添加

public class MainActivity extends AppCompatActivity {


    private SlidingMenu slidingMenu;

    private PullToRefreshListView pullToRefreshListView;
    private ArrayAdapter<String> adapter;
    private String[] data = new String[]{"Hello","fangyi1896@gmail.com","fangyi186@outlook.com"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
         *Android SlidingMenu 菜单栏程序设计开发
         */
        slidingMenu = new SlidingMenu(this);//创建对象
        slidingMenu.setMode(SlidingMenu.LEFT);//设定模式，SlidingMenu在左边\
        slidingMenu.setBehindOffsetRes(R.dimen.sliding_menu_offset);//配置slidingmenu偏移出来的尺寸

        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);//全屏都可以拖拽触摸，打开slidingmenu
        slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);//附加到当前的Aty上去
        slidingMenu.setMenu(R.layout.slidingmenu);

        /*
         *那个小邮件图标
         */
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //重写KeyDown事件，菜单键按下也能拉出slidingmenu
        switch (keyCode) {
            case KeyEvent.KEYCODE_MENU:
                slidingMenu.toggle(true);
                break;
            default:
                break;
        }
        return super.onKeyDown(keyCode, event);
    }
}




2. 新建布局 slidingmenu.xml 


<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical" android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="#ff9999">	//粉色

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

Android 通用下拉刷新控件的使用


https://github.com/chrisbanes/Android-PullToRefresh


导入包 Android-PullToRefresh

	先把library库以as的形式导入。确认这个工程没有问题之后，然后在你新建的项目中添加进去


1.

	在 MainActivity 中新建 

    private PullToRefreshListView pullToRefreshListView;
    private ArrayAdapter<String> adapter;
    private String[] data = new String[]{"Hello","fangyi1896@gmail.com","fangyi186@outlook.com"};

        /*
         *Android 通用下拉刷新控件的使用
         */

        //下拉菜单以后 效果：放开以刷新
        pullToRefreshListView = (PullToRefreshListView) findViewById(R.id.pullToRefreshListViewlayout);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);
        pullToRefreshListView.setAdapter(adapter);


        //下拉菜单以后 效果：正在载入···
        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... params) {
                        //休眠3秒钟，模拟网络通信的效果
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }

                    //可以用来修改UI控件的变化
                    @Override
                    protected void onPostExecute(Void aVoid) {
                        super.onPostExecute(aVoid);

                        adapter.addAll("87649669@qq.com", "475714960@qq.com");

                        //然后我们通过 pullToRefreshListView 成功加载了上面的数据
                        pullToRefreshListView.onRefreshComplete();//其实这个地方是有问题的，我们之前是数组，现在我们改成 集合

                    }
                }.execute();
            }
        });

2.
	在 content_main.xml 中新添加

    <com.handmark.pulltorefresh.library.PullToRefreshListView
        android:id="@+id/pullToRefreshListViewlayout"
        android:layout_width="fill_parent"
        android:layout_height="fill_parent">
    </com.handmark.pulltorefresh.library.PullToRefreshListView>













//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



自定义视图属性


1.
	创建一个类 MyRect 继承自 View 


		package com.example.fangyi.customizeview;

		import android.content.Context;
		import android.content.res.TypedArray;
		import android.util.AttributeSet;
		import android.view.View;

		/**
		 * Created by FANGYI on 2016/2/12.
		 */
		public class MyRect extends View {

		    public MyRect(Context context) {
		        super(context);
		    }

		    //资源解析器来访问
		    public MyRect(Context context, AttributeSet attrs) {
		        super(context, attrs);

		        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MyView);

		        //可以获得颜色值
		        int color = ta.getColor(R.styleable.MyView_rect_color, 0xffff0000);//默认值
		        setBackgroundColor(color);

		        ta.recycle();
		    }
		}


2. 

	设定颜色的属性

	创建一个 values （值） 文件、

		New → XML → Values XML File

		<?xml version="1.0" encoding="utf-8"?>
		<resources>
		    <declare-styleable name="MyRect">
		        <attr name="rect_color" format="color"/>//格式
		    </declare-styleable>
		</resources>



3.
	在 content_main.xml 文件中定义

	mlns:jkxy="http://schemas.android.com/apk/res/com.example.fangyi.customizeview"
    



    <com.example.fangyi.customizeview.MyRect
        android:layout_width="100dp"
        android:layout_height="100dp"
        jkxy:rect_color="#ff0000ff" //可以在这里给设值，不写的话，就是默认值
        >

    </com.example.fangyi.customizeview.MyRect>

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///


自定义控件皮肤

1.
	在 res\drawable 中添加皮肤，PNG格式

2.
	添加控件

    <Button
        // android:background="@drawable/android" //皮肤
        android:background="@drawable/button_skin"
        android:text="Button"
        android:layout_width="200dp"
        android:layout_height="200dp"
        android:id="@+id/button" />


3.
	交互效果

	在 res 文件夹 下  New → Android Resource Fle 

		选择 
			File name:button_skin	//File-based resource names must start with a lowercase letter 基于文件的资源名称必须以小写字母开头
			Resource type:Drawable
			Root element:selector


<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">
	//按下前的状态
    <item android:state_pressed="false" android:drawable="@drawable/btn_1"></item>//原图绿色
    //按下后的状态
    <item android:state_pressed="true" android:drawable="@drawable/btn_2"></item>//原图蓝色
    //还有很多方案
</selector>


4.
	效果
		点击前：绿色

		点击 不弹起：蓝色

		弹起：绿色



//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

使用绘图 API 自定义视图



1. 
	创建一个类 （旋转的方块）
		

		package com.example.fangyi.customizeview;

		import android.content.Context;
		import android.graphics.Canvas;
		import android.graphics.Color;
		import android.graphics.Paint;
		import android.util.AttributeSet;
		import android.view.View;

		/**
		 * Created by FANGYI on 2016/2/12.
		 */
		public class RotatingRect extends View {

		    public RotatingRect(Context context) {
		        super(context);

		        initProperties();
		    }

		    public RotatingRect(Context context, AttributeSet attrs) {
		        super(context, attrs);

		        initProperties();
		    }

		    public RotatingRect(Context context, AttributeSet attrs, int defStyleAttr) {
		        super(context, attrs, defStyleAttr);

		        initProperties();
		    }



			private Paint p;//画笔对象，控制我们绘制图形的样式
		    private float degrees = 0;//绘制旋转角度

		    //初始化属性
		    private void initProperties() {
		        p = new Paint();
		        p.setColor(Color.RED);//给个红色
		    }

		    @Override
		    public void draw(Canvas canvas) {
		        super.draw(canvas);

		        canvas.save();//保存状态
		//        canvas.rotate(degrees); //默认绕着左上角旋转，degrees是旋转一个角度
		        canvas.translate(200,200);//调整坐标
		        canvas.rotate(degrees,50,50);//第一个是角度，第二个和第三个是旋转坐标点
		        canvas.drawRect(0,0,100,100,p);//绘图API，绘制了一个正方形
		        degrees ++;
		        canvas.restore();
		        invalidate();//使这个View无效，太耗资源，可以用延时
		        //重绘的时候查看是否是有效状态，有效，就不重绘了，重新绘制，就会重新执行draw方法
		        //Handler 延时绘制
		    }
		}


2.

    <com.example.fangyi.customizeview.RotatingRect
        android:layout_width="fill_parent"
        android:layout_height="fill_parent"
        android:layout_alignParentBottom="true"
        android:layout_alignParentRight="true"
        android:layout_alignParentEnd="true">
    </com.example.fangyi.customizeview.RotatingRect>




//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


View动画-透明动画效果

需要 运用之前的 2221 行代码处


/*
使用代码控制
 */
1.
	在 fragment_main.xml 中添加 按钮
    
    <Button
        android:textAllCaps="false"
        android:layout_width="fill_parent"
        android:layout_height="wrap_content"
        android:text="Animate Me"
        android:id="@+id/btnAnimMe"
        android:layout_below="@+id/btnShowAnotherFragment"
        android:layout_alignParentLeft="true"
        android:layout_alignParentStart="true" />

2.
	在 MainActivityFragment 中添加

        rootView.findViewById(R.id.btnAnimMe).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlphaAnimation btnAnimMe = new AlphaAnimation(0, 1);//透明度0到1的动画效果
                btnAnimMe.setDuration(1000);//设置动画时间长度
                v.startAnimation(btnAnimMe);
            }
        });



/*
使用xml文件控制
 */
1.
	在 res 右键，New →  Android Resource File
	选择 
		neme:animation_transparent
		Resource type: Animation
		Root element:alpha


	<?xml version="1.0" encoding="utf-8"?>
	<alpha xmlns:android="http://schemas.android.com/apk/res/android"
	    android:fromAlpha="0"
	    android:toAlpha="1"
	    android:duration="1000">

	</alpha>


2.
	在 MainActivityFragment 中添加

        rootView.findViewById(R.id.btnAnimMe).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.animation_transparent));//xml文件配置
            }
        });




//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


View动画-旋转动画效果

/*
使用代码控制
 */
1.
	在 fragment_main.xml 中添加 按钮
    
    <Button
        android:textAllCaps="false"
        android:layout_width="fill_parent"
        android:layout_height="wrap_content"
        android:text="Rotate Me"
        android:id="@+id/btnRotateMe"
        android:layout_below="@+id/btnAnimMe"
        android:layout_alignParentLeft="true"
        android:layout_alignParentStart="true" />

2.
	在 MainActivityFragment 中添加
    private RotateAnimation rotateAnimation;

    // 在    public View onCreateView(LayoutInflater inflater, ViewGroup container,
    //                          Bundle savedInstanceState)
    // 中添加
    // 
//        rotateAnimation = new RotateAnimation(0, 360);//默认左上角旋转，0到360度
//        rotateAnimation = new RotateAnimation(0, 360, 100, 100);//指明在哪个点旋转
        rotateAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);//相对自身的中心点
        rotateAnimation.setDuration(1000);//旋转一秒

        rootView.findViewById(R.id.btnRotateMe).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(rotateAnimation);
            }
        });



/*
使用xml文件控制
 */
1.
	在 res 右键，New →  Android Resource File
	选择 
		neme:animation_rotate
		Resource type: Animation
		Root element:translate //根节点


	<?xml version="1.0" encoding="utf-8"?>
	<alpha xmlns:android="http://schemas.android.com/apk/res/android"
    android:fromDegrees="0"
    android:toDegrees="360"
    android:duration="1000"
    android:pivotX="50%"	//写百分百，会自动解析相对自身的位置
    android:pivotY="50%">	//写数值， 会解析成像素位置

	</alpha>


2.
	在 MainActivityFragment 中添加

        /*
            View动画-旋转动画效果
         */
//        rotateAnimation = new RotateAnimation(0, 360);//默认左上角旋转，0到360度
//        rotateAnimation = new RotateAnimation(0, 360, 100, 100);//指明在哪个点旋转
//        rotateAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);//相对自身的中心点
//        rotateAnimation.setDuration(1000);//旋转一秒

        rootView.findViewById(R.id.btnRotateMe).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                v.startAnimation(rotateAnimation);
                v.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.animation_rotate));
            }
        });




//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///
///
///
View动画-移动动画效果



/*
使用代码控制
 */
1.
	在 fragment_main.xml 中添加 按钮
    
    <Button
        android:textAllCaps="false"
        android:layout_width="fill_parent"
        android:layout_height="wrap_content"
        android:text="Rotate Me"
        android:id="@+id/btnRotateMe"
        android:layout_below="@+id/btnAnimMe"
        android:layout_alignParentLeft="true"
        android:layout_alignParentStart="true" />

2.
	在 MainActivityFragment 中添加

    private TranslateAnimation translateAnimation;
        /*
            View动画-移动动画效果
         */

        translateAnimation = new TranslateAnimation(0, 200 , 0, 200);//当前位置，往右移动200，往下移动200
        translateAnimation.setDuration(1000);

        rootView.findViewById(R.id.btnTranslateMe).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(translateAnimation);
            }
        });



/*
使用xml文件控制
 */
1.
	在 res 右键，New →  Android Resource File
	选择 
		neme:animation_translate
		Resource type: Animation
		Root element:scale //根节点


			<?xml version="1.0" encoding="utf-8"?>
			<translate xmlns:android="http://schemas.android.com/apk/res/android"
			    android:fromXDelta="0"
			    android:toXDelta="200"
			    android:fromYDelta="0"
			    android:toYDelta="200"
			    android:duration="1000">

			</translate>


2.
	在 MainActivityFragment 中添加

        /*
            View动画-移动动画效果
         */

        rootView.findViewById(R.id.btnTranslateMe).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.animation_translate));//xml文件配置
            }
        });




//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///
///
///
View动画-缩放动画效果

/*
使用代码控制
 */
1.
	在 fragment_main.xml 中添加 按钮
    
    <Button
        android:textAllCaps="false"
        android:layout_width="fill_parent"
        android:layout_height="wrap_content"
        android:text="Rotate Me"
        android:id="@+id/btnRotateMe"
        android:layout_below="@+id/btnAnimMe"
        android:layout_alignParentLeft="true"
        android:layout_alignParentStart="true" />

2.
	在 MainActivityFragment 中添加

    private ScaleAnimation scaleAnimation;

        /*
            View动画-缩放动画效果
         */
//        scaleAnimation = new ScaleAnimation(0, 1, 0, 1);//默认左上角， 按比例从 0 到 1 缩放
//        scaleAnimation = new ScaleAnimation(0, 1, 0, 1, 100, 50);//后面两个是缩放中心点，从缩放点，按比例从 0 到 1 缩放
        scaleAnimation = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);//相对于自身中心点进行缩放
        scaleAnimation.setDuration(1000);

        rootView.findViewById(R.id.btnScaleMe).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(scaleAnimation);
            }
        });

/*
使用xml文件控制
 */
1.
	在 res 右键，New →  Android Resource File
	选择 
		neme:animation_scale
		Resource type: Animation
		Root element:scale //根节点


		<?xml version="1.0" encoding="utf-8"?>
		<scale xmlns:android="http://schemas.android.com/apk/res/android"
		    android:fromXScale="0"
		    android:toXScale="1"
		    android:fromYScale="0"
		    android:toYScale="1"
		    android:duration="1000"
		    android:pivotX="50%"
		    android:pivotY="50%">

		</scale>


2.
	在 MainActivityFragment 中添加

        /*
            View动画-移动动画效果
         */

        rootView.findViewById(R.id.btnTranslateMe).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.animation_scale));//xml文件配置
            }
        });



//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///
///
///
View动画-动画效果混合


/*
使用代码控制
 */
1.
	在 fragment_main.xml 中添加 按钮
    
    <Button
        android:textAllCaps="false"
        android:layout_width="fill_parent"
        android:layout_height="wrap_content"
        android:text="Two Animaion"
        android:id="@+id/btnTwoANimaion"
        android:layout_below="@+id/btnRotateMe"
        android:layout_alignParentLeft="true"
        android:layout_alignParentStart="true" />

2.
	在 MainActivityFragment 中添加

    private ScaleAnimation scaleAnimation;

        /*
            View动画-动画效果混合
         */
        animationSet = new AnimationSet(true);//是否使用动画补间
        animationSet.setDuration(1000);

        alphaAnimation = new AlphaAnimation(0, 1);//透明度0到1的动画效果
        alphaAnimation.setDuration(1000);//设置动画时间长度
        animationSet.addAnimation(alphaAnimation);

        translateAnimation = new TranslateAnimation(0, 200 , 0, 200);//当前位置，往右移动200，往下移动200
        translateAnimation.setDuration(1000);
        animationSet.addAnimation(translateAnimation);

        rootView.findViewById(R.id.btnTwoAnimaion).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(animationSet);
            }
        });


/*
使用xml文件控制
 */
1.
	在 res 右键，New →  Android Resource File
	选择 
		neme:animation
		Resource type: Animation
		Root element:set //根节点


		<?xml version="1.0" encoding="utf-8"?>
		<set xmlns:android="http://schemas.android.com/apk/res/android"
		    android:shareInterpolator="true"
		    android:duration="1000">
		    <alpha
		        android:fromAlpha="0"
		        android:toAlpha="1"></alpha>
		    <translate
		        android:fromXDelta="0"
		        android:toXDelta="200"
		        android:fromYDelta="0"
		        android:toYDelta="200"></translate>

		</set>


2.
	在 MainActivityFragment 中添加

        rootView.findViewById(R.id.btnTwoAnimaion).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.animation));//xml文件配置
            }
        });


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


View动画-动画效果侦听


        rootView.findViewById(R.id.btnTwoAnimaion).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                v.startAnimation(animationSet);
                Animation a = AnimationUtils.loadAnimation(getActivity(), R.anim.animation);

                a.setAnimationListener(new Animation.AnimationListener() {
                    @Override//动画开始
                    public void onAnimationStart(Animation animation) {
//                        Toast.makeText(getActivity(), "Animation End", Toast.LENGTH_SHORT).show();
                    }

                    @Override//动画重复
                    public void onAnimationEnd(Animation animation) {

                    }

                    @Override//动画结束
                    public void onAnimationRepeat(Animation animation) {
                        Toast.makeText(getActivity(), "Animation End", Toast.LENGTH_SHORT).show();
                    }
                });

                v.startAnimation(a);//xml文件配置
            }
        });


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


自定义动画效果


1. 我们新建一个Activity，、CustomAnimation

			package com.example.fangyi.theviewanimation;

			import android.view.animation.Animation;
			import android.view.animation.Transformation;

			/**
			 * Created by FANGYI on 2016/2/12.
			 */
			public class CustomAnimation extends Animation {
			    //需要了解目标组件的宽高
			    @Override
			    public void initialize(int width, int height, int parentWidth, int parentHeight) {
			        super.initialize(width, height, parentWidth, parentHeight);
			    }

			    @Override
			    protected void applyTransformation(float interpolatedTime, Transformation t) {
			        //float interpolatedTime ：补间时间，动画在执行完毕以后是1，在执行的过程当中applyTransformation()在不断的执行，interpolatedTime在0-1之间
			        //Transformation t ：来对目标组件状态进行修改

			//        t.setAlpha(interpolatedTime);//设置透明度0-1之间的透明变化效果
			//        t.getMatrix().setTranslate(200, 200);//直接瞬间跳到指定的位置,然后一秒回来
			//        t.getMatrix().setTranslate(200*interpolatedTime, 200);//Y轴立即跳到指定的坐标，X轴慢慢的挪过去，然后一秒回来
			//        t.getMatrix().setTranslate(200*interpolatedTime, 200*interpolatedTime);//两轴慢慢的挪到指定的坐标，然后一秒回来


			        //我们来设置一个摇头的动画效果,往X轴里面放一个周期，括弧里面第一个*是摇晃速度，外面第二个*是摇晃幅度
			        t.getMatrix().setTranslate((float) (Math.sin(interpolatedTime*10)*50), 0);

			        super.applyTransformation(interpolatedTime, t);
			    }
			}

2.

    <Button
        android:textAllCaps="false"
        android:layout_width="fill_parent"
        android:layout_height="wrap_content"
        android:text="Custom Animation"
        android:id="@+id/btnCustomAnimaion"
        android:layout_below="@+id/btnTwoAnimaion"
        android:layout_alignParentLeft="true"
        android:layout_alignParentStart="true" />



3.

    private CustomAnimation customAnimation;

        /*
            自定义动画效果
         */

        customAnimation = new CustomAnimation();
        customAnimation.setDuration(1000);
        rootView.findViewById(R.id.btnCustomAnimaion).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(customAnimation);
            }
        });




//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


 Android用户界面优化-Android创建和配置布局动画


为布局添加动画效果

1.把 fragment_main.xml 改成 
	
	<LinearLayout
    	android:orientation="vertical"


2.
    private ScaleAnimation scaleAnimation;

        /*
            根目录列表动画,效果：慢慢缩放出来
         */
        //原来       
        // View root = inflater.inflate(R.layout.fragment_another,container,false);//初始化这个布局，从外界传进来的
        //改成
        LinearLayout rootView = (LinearLayout) inflater.inflate(R.layout.fragment_main, container, false);

        scaleAnimation = new ScaleAnimation(0, 1, 0, 1);
        scaleAnimation.setDuration(1000);

        LayoutAnimationController lac = new LayoutAnimationController(scaleAnimation, 0.5f);//第二个参数是，假设为0.5f，当第一个按钮动画执行到0.5f时，第二个动画开始加载执行动画

        lac.setOrder(LayoutAnimationController.ORDER_REVERSE);//默认三个属性：ORDER_NORMA从上到下,LORDER_RANDOM随机,ORDER_REVERSE从下往上

        rootView.setLayoutAnimation(lac);


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2015/0619/3090.html

	由于 layout-animation 是对于某一组控件的操作，就需要一个基本的动画来定义单个控件的动画。另外还可以定义动画的显示顺序和延迟：
	

	<layoutAnimation xmlns:android="http://schemas.android.com/apk/res/android"
	        android:delay="30%"
	        android:animationOrder="reverse"
	        android:animation="@anim/slide_right"/>





其中

android:delay表示动画播放的延时，既可以是百分比，也可以是float小数。

android:animationOrder表示动画的播放顺序，有三个取值normal(顺序)、reverse(反序)、random(随机)。

android:animation指向了子控件所要播放的动画。

上述步骤完成之后，就可以将layout-animation应用到ViewGroup中，xml布局添加下面一行就ok：

		android:layoutAnimation="@anim/list_anim_layout"

这样在加载布局的时候就会自动播放layout-animtion。



//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

布局内容改变动画

1.
	在 content_main 中更改

	<LinearLayout
	    android:orientation="vertical"
	    android:id="@+id/rootView"
	        android:animateLayoutChanges="true"//添加动画效果

2. 
	在 res/menu/ menu_main.xml 中添加
    
    <item
        android:id="@id/action_add"
        app:showAsAction="always"					//注意这里是app 不是android
        android:title="@string/action_add"
        android:icon="@android:drawable/ic_input_add"></item>

3.

package com.example.fangyi.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private LinearLayout linearLayout;

    //方法点击退出
    private View.OnClickListener btn_onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            linearLayout.removeView(v);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayout = (LinearLayout) findViewById(R.id.rootView);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


	//添加按钮
    private void AddButton() {

        Button btn = new Button(this);
        btn.setText("Remove Me");

        linearLayout.addView(btn);
		linearLayout.setLayoutTransition(？？？？？);//加动画
        btn.setOnClickListener(btn_onClickListener);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
									//对菜单栏做选择
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            case R.id.action_add:
                AddButton();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}




//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///
偷懒了


为列表添加布局动画效果


1.
	修改，


public class MainActivity extends ListActivity {
	//布局
    private ArrayAdapter<String> adapter;
    //动画效果
    private LayoutAnimationController lac;
    private ScaleAnimation sa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        //布局
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new String[]{"Hello","fangyi1896@gmail.com","fangyi186@outlook.com"});
        setListAdapter(adapter);

        //动画效果
        sa = new ScaleAnimation(0, 1, 0, 1);
        sa.setDuration(1000);
        lac = new LayoutAnimationController(sa, 0.5f);
        getListView().setLayoutAnimation(lac);
    }



//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

使用资源文件配置布局动画,效果和前面的一样



1.

		public class MainActivity extends ListActivity {

		    private ArrayAdapter<String> adapter;
		//    private LayoutAnimationController lac;
		//    private ScaleAnimation sa;
		    @Override
		    protected void onCreate(Bundle savedInstanceState) {
		        super.onCreate(savedInstanceState);
		        setContentView(R.layout.main);//添加这句

		        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new String[]{"Hello","fangyi1896@gmail.com","fangyi186@outlook.com"});

		        setListAdapter(adapter);

		//        sa = new ScaleAnimation(0, 1, 0, 1);
		//        sa.setDuration(1000);
		//        lac = new LayoutAnimationController(sa, 0.5f);
		//        getListView().setLayoutAnimation(lac);
		    }



2.

	创建 layout/main.xnl

			<?xml version="1.0" encoding="utf-8"?>
			<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
			    android:orientation="vertical" android:layout_width="match_parent"
			    android:layout_height="match_parent">

			    <ListView
			        android:id="@android:id/list"//记住这里
			        android:layout_width="fill_parent"
			        android:layout_height="fill_parent"
			        android:layoutAnimation="@anim/listview_anim">
			    </ListView>

			</LinearLayout>





	创建	anim/listview_anim.xml


			<?xml version="1.0" encoding="utf-8"?>
			<layoutAnimation xmlns:android="http://schemas.android.com/apk/res/android"
			    android:animation="@anim/scale_0_1"
			    android:delay="0.5">

			</layoutAnimation>




	创建	anim/scale_0_1.xml


			<?xml version="1.0" encoding="utf-8"?>
			<scale xmlns:android="http://schemas.android.com/apk/res/android"
			    android:fromXScale="0"
			    android:toXScale="1"
			    android:fromYScale="0"
			    android:toYScale="1"
			    android:duration="1000">


			</scale>






//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


Android知识点-点9切图法在Android UI设计中的运用

1.

	在 AndroidManifest 中

	        android:icon="@drawable/icon_android"

	        可以改图标

2.

	在  xml 文件中

		android:background="@drawable/XXX"

		可以改背景



3，

	在 content_main 中添加

	    <ImageView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:id="@+id/imageView"
        android:src="@drawable/img_1" />//看这里



4.
 	在应用程序中修改原来的拖
        ImageView iv = (ImageView) findViewById(R.id.imageView);
        iv.setImageResource(R.drawable.img_2);  //看这里




http://blog.csdn.net/qq_24889075/article/details/47211913

不用用太大的图片 容易 oom



//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


程序引导页依然成为现今App的必需品，通过引导页可以第一时间知道此款软件是如何应用的。并且能了解到最新的动态和更新。


ViewPager实现 13:24

本课时讲解ViewPager的实现，实现左右滑动的效果，让学员掌握ViewPager的使用。


引导页



1.

	新建 Guide 类

		package com.example.fangyi.viewpagerdemo;

		import android.app.Activity;

		/**
		 * Created by FANGYI on 2016/2/15.
		 */
		public class Guide extends Activity {

		    private
		}


2.
	新建 ViewPagerAdapter 类

		package com.example.fangyi.viewpagerdemo;

		import android.content.Context;
		import android.support.v4.view.PagerAdapter;
		import android.support.v4.view.ViewPager;
		import android.view.View;
		import android.view.ViewGroup;

		import java.util.List;

		/**
		 * Created by FANGYI on 2016/2/15.
		 */
		public class ViewPagerAdapter extends PagerAdapter {
		//重写方法  直接 alt+enter


		    private List<View> views;
		    private Context context;

		    public ViewPagerAdapter(List<View> views, Context context) {
		        this.views = views;
		        this.context = context;
		    }


		    @Override//我们不可能都保存每一个views，我们可以将其销毁
		    public void destroyItem(ViewGroup container, int position, Object object) {

		        //我们使用container.removeView来进行移除，通过views来知道当前个数，position来进行索引
		//            container.removeView(views.get(position));
		        ((ViewPager)container).removeView(views.get(position));
		    }

		    @Override//我们需要一个加载views的方法，
		    public Object instantiateItem(ViewGroup container, int position) {
		        ((ViewPager)container).removeView(views.get(position));

		        return views.get(position);
		    }

		    @Override//返回当前views 的数量
		    public int getCount() {
		        return views.size();
		    }

		    @Override//来判断当前view是不是我们需要的对象
		    public boolean isViewFromObject(View view, Object object) {
		        return (view == object);
		    }
		}



3.
	补全 Guide 类
		package com.example.fangyi.viewpagerdemo;

		import android.app.Activity;
		import android.os.Bundle;
		import android.os.PersistableBundle;
		import android.support.v4.view.ViewPager;
		import android.view.LayoutInflater;
		import android.view.View;

		import java.util.ArrayList;
		import java.util.List;

		/**
		 * Created by FANGYI on 2016/2/15.
		 */
		public class Guide extends Activity {

		    private ViewPager viewPager;
		    private ViewPagerAdapter viewPagerAdapter;
		    private List<View> views;

		    @Override
		    protected void onCreate(Bundle savedInstanceState) {
		        super.onCreate(savedInstanceState);
		        setContentView(R.layout.guide);//加载好当前的视图
		        initViews();
		    }

		    //初始化的方法        首先我们要加载额外的三个view 切换的三个view
		    private void initViews() {
		        LayoutInflater inflater = LayoutInflater.from(this);//先来通过这个来加载
		        //view 是要放在集合当中的，所以我们先将view进行实例化操作
		        views = new ArrayList<View>();
		        views.add(inflater.inflate(R.layout.one, null));
		        views.add(inflater.inflate(R.layout.two, null));
		        views.add(inflater.inflate(R.layout.three, null));
		    }
		}



4.
	创建一个 guide.xml 文件

		<android.support.v4.view.ViewPager
	        android:id="@+id/viewpager"
	        android:layout_width="fill_parent"
	        android:layout_height="fill_parent"
	        android:background="#000000"></android.support.v4.view.ViewPager>



5.

	创建一个 one.xml 和 two.xml 和 three.xml 文件

	    <ImageView
	        android:layout_width="fill_parent"
	        android:layout_height="fill_parent"
	        android:background="@drawable/img_1"/>

	    <ImageView
	        android:layout_width="fill_parent"
	        android:layout_height="fill_parent"
	        android:background="@drawable/img_2"/>


5.

	去 AndroidManifest 修改启动入口

	<activity
            android:name=".MainActivity"
            android:label="@string/app_name"
            android:theme="@style/AppTheme.NoActionBar">
            //把下面这段删除
          	// <intent-filter>
           //      <action android:name="android.intent.action.MAIN" />

           //      <category android:name="android.intent.category.LAUNCHER" />
           //  </intent-filter>
    
    </activity>


    添加

        <activity android:name=".Guide">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////




添加导航点 06:48

本课主要时讲解在ViewPager上添加页面滑动的导航点，
用以表示当前ViewPager的索引。让学员掌握如何使用点来标识ViewPager当前在哪一页中。



1.
	先打开布局文件 guide.xml 添加


    <LinearLayout
        android:layout_width="fill_parent"
        android:layout_height="wrap_content"
        android:id="@+id/ll"
        android:orientation="horizontal"	//布局方向：水平布局
        android:gravity="center_horizontal"	//水平居中
		android:layout_alignParentBottom="true"	//基于空间底部
        >
        <ImageView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:id="@+id/iv1"
            android:src="@drawable/login_point_selected"/>//亮的
        <ImageView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:id="@+id/iv2"
            android:src="@drawable/login_point"/>//暗的
        <ImageView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:id="@+id/iv3"
            android:src="@drawable/login_point"/>//暗的

    </LinearLayout>


2.

	在 GuiDe 中进行操作


//点的改变需要ViewPager，所以我们需要一个监听事件，监听ViewPager
//我们要复写这三个方法
public class Guide extends Activity implements ViewPager.OnPageChangeListener {

    private ImageView[] dots;
    private int[] ids = {R.id.iv1,R.id.iv2,R.id.iv3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guide);//加载好当前的视图
        initViews();
        initDots();
    }

//在 initViews() 最后一行 添加回调	viewPager.setOnPageChangeListener(this);

        viewPager.addOnPageChangeListener(this);//回调


    //点的操作方法
    private void initDots() {
        dots = new ImageView[views.size()];

        for (int i = 0; i < views.size(); i++) {
            dots[i] = (ImageView) findViewById(ids[i]);
        }
    }

    @Override//当前新的页面被选中时候调用
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override//当页面滑动时候调用
    public void onPageSelected(int position) {
        for (int i = 0; i < ids.length; i++) {
            if (position == i) {
                dots[i].setImageResource(R.drawable.login_point_selected);
            }else {
                dots[i].setImageResource(R.drawable.login_point);
            }
        }
    }

    @Override//滑动状态改变时候进行调用
    public void onPageScrollStateChanged(int state) {

    }
}




/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

添加进入主界面按钮 04:09

本课时讲解了在ViewPager的最后一个页面添加上Button按钮用以提示用户进入程序，让学员掌握如何在最后一个页面添加上Button。


1.

	在最后一张图，也就是 three.xml 中

	<?xml version="1.0" encoding="utf-8"?>
	<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
	    android:layout_width="match_parent"
	    android:layout_height="match_parent">

	    <ImageView
	        android:layout_width="fill_parent"
	        android:layout_height="fill_parent"
	        android:background="@drawable/img_3"/>

	    <LinearLayout
	        android:layout_width="fill_parent"
	        android:layout_height="wrap_content"
	        android:gravity="center_horizontal"
	        android:layout_alignParentBottom="true"
	        android:orientation="horizontal">

	        <Button
	            android:id="@+id/start_btn"
	            android:layout_width="wrap_content"
	            android:layout_height="wrap_content"
	            android:text="进入"/>
	    </LinearLayout>

	</RelativeLayout>

2.
	事件的监听  砸 initViews(){} 中添加


	    private Button start_btn;


	    private void initViews() {

	        //直接findViewById不行，因为当前加载的View是guide，所以我们应该到views列表里找到，谁加载了这个View
	        start_btn = (Button) views.get(2).findViewById(R.id.start_btn);
	        start_btn.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	                //直接跳转到主界面就可以了，因为主界面至始至终都没有打开过，所以直接跳转打开新界面
	                Intent i = new Intent(Guide.this,MainActivity.class);
	                startActivity(i);
	                finish();//将当期没用的收回
	            }
	        });
	    }




/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



添加数据储存 09:25

本课主要时讲解了如何在首次打开程序进入引导页，之后进入主程序。让学员掌握数据存储的使用，通过使用数据储存实现效果。


1.
	我们要新建一个首界面 WelcomeActivity 类



		package com.example.fangyi.viewpagerdemo;

		import android.app.Activity;
		import android.content.Intent;
		import android.content.SharedPreferences;
		import android.os.Bundle;
		import android.os.Handler;
		import android.os.Message;

		/**
		 * Created by FANGYI on 2016/2/16.
		 */
		public class WelcomeActivity extends Activity{
		    //我们先让首界面沉睡几秒，然后再选择跳入哪一个界面，主界面还是引导面
		    private boolean isFirstIn = false;//这个值我们要储存起来
		    private static final int TIME = 2000;
		    private static final int Go_HOME = 1000;
		    private static final int GO_GUIDE =1001;

		    //我们等待的时间不能直接让他在主线程中沉睡，这是不合理的
		    //mHandler发送的消息是很关键的，所有我们需要一个boolean类型的值，判定哪个消息 我们写一个方法init()
		    private Handler mHandler = new Handler() {
		        @Override
		        public void handleMessage(Message msg) {
		            switch (msg.what) {
		                case Go_HOME:
		                    goHome();
		                    break;
		                case GO_GUIDE:
		                    goGuide();
		                    break;
		            }
		        }
		    };

		    @Override
		    protected void onCreate(Bundle savedInstanceState) {
		        super.onCreate(savedInstanceState);
		        setContentView(R.layout.welcomeactivity);
		        init();
		    }

		    private void goHome() {
		        Intent i = new Intent(WelcomeActivity.this, MainActivity.class);
		        startActivity(i);
		        finish();
		    }

		    private void goGuide() {
		        Intent i = new Intent(WelcomeActivity.this, Guide.class);
		        startActivity(i);
		        finish();
		    }

		    private void init() {
		        //SharedPreferences来进行储存，首先获取
		        SharedPreferences prePreferences = getSharedPreferences("FANGYI", MODE_PRIVATE);

		        //先来获取到看他里面是否存在的值，同时把这个值赋给isFirstIn,第一次赋值是没有的
		        isFirstIn = prePreferences.getBoolean("isFirstIn", true);
		        if (!isFirstIn) {
		            mHandler.sendEmptyMessageDelayed(Go_HOME, TIME);
		        }else {
		            mHandler.sendEmptyMessageDelayed(GO_GUIDE, TIME);
		            //当进入过引导界面以后，将这个值储存起来
		            SharedPreferences.Editor editor = prePreferences.edit();
		            editor.putBoolean("isFirstIn",false);
		            editor.commit();//提交

		        }
		    }
		}




2.
	需要一个欢迎布局 welcomeactivity.xml

		<?xml version="1.0" encoding="utf-8"?>
		<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
		    android:orientation="vertical" android:layout_width="match_parent"
		    android:layout_height="match_parent">

		    <ImageView
		        android:layout_width="fill_parent"
		        android:layout_height="fill_parent"
		        android:background="@drawable/img_17"/>
		</LinearLayout>





3.

	去 AndroidManifest 中更改启动入口


		<?xml version="1.0" encoding="utf-8"?>
		<manifest xmlns:android="http://schemas.android.com/apk/res/android"
		    package="com.example.fangyi.viewpagerdemo">

		    <application
		        android:allowBackup="true"
		        android:icon="@mipmap/ic_launcher"
		        android:label="@string/app_name"
		        android:supportsRtl="true"
		        android:theme="@style/AppTheme">
		        <activity
		            android:name=".MainActivity"
		            android:label="@string/app_name"
		            android:theme="@style/AppTheme.NoActionBar">
		        </activity>

		        <activity android:name=".Guide">

		        </activity>
		        <activity android:name=".WelcomeActivity">
		            <intent-filter>
		                <action android:name="android.intent.action.MAIN" />

		                <category android:name="android.intent.category.LAUNCHER" />
		            </intent-filter>
		        </activity>

		    </application>

		</manifest>



/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


创建抽屉布局 06:40

本课时在 XML 文件中创建抽屉布局，完成划出侧滑菜单的功能。


http://developer.android.com/intl/zh-cn/training/implementing-navigation/nav-drawer.html#DrawerLayout




1.

	我们把跟布局 content_main.xml 全部删除，重新写一个

<?xml version="1.0" encoding="utf-8"?>
<android.support.v4.widget.DrawerLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/drawer_layout"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    
    <!-- The main content view -->
    <FrameLayout
        android:id="@+id/content_frame"
        android:layout_width="match_parent"
        android:layout_height="match_parent">
    </FrameLayout>

    <!-- The navigation drawer -->

    <ListView
        android:id="@+id/left_drawer"
        android:layout_width="240dp"
        android:layout_height="match_parent"
        android:background="#ffffcc"
		android:layout_gravity="start" //从左向右滑，指定end从右向左滑
		android:choiceMode="singleChoice"//单选模式
		android:divider="@android:color/transparent"//透明色
		android:dividerHeight="0dp"//这样就看不到项与项之间的分割条>
    </ListView>

</android.support.v4.widget.DrawerLayout>


注意事项：
	1、主内容视图一定要是 DrawerLayout 的第一个子视图
	
	2、主内容视图宽度和高度匹配父视图，即“mactch_parent”
	
	3、必须显示指定抽屉视图（如ListView）的androiid:layout_gravity属性
		1)、android:layout_gravity="start"时，从左向右滑出菜单
		2)、android:layout_gravity="end"时，从右向左滑出菜单
 		3)、不推荐使用“left”和“right”
	
	4、抽屉视图的宽度以dp为单位，，请不要超过320dp（为了总能看到一些主内容视图）



/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
//
//
//这段其实有问题，，，，有空找一个新的

初始化导航列表 09:44

本课时在 Java 文件中使用 DrawerLayout，完成导航列表项的填充，添加点击事件来插入相应的 Fragment。


1.
	在 MainActivity 中 、
	
		package com.example.fangyi.drawerlayoutusing;

		import android.app.Fragment;
		import android.app.FragmentManager;
		import android.os.Bundle;
		import android.support.design.widget.FloatingActionButton;
		import android.support.design.widget.Snackbar;
		import android.support.v4.widget.DrawerLayout;
		import android.support.v7.app.AppCompatActivity;
		import android.support.v7.widget.Toolbar;
		import android.view.View;
		import android.view.Menu;
		import android.view.MenuItem;
		import android.widget.AdapterView;
		import android.widget.ArrayAdapter;
		import android.widget.ListView;

		import java.util.ArrayList;

		public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
		    private DrawerLayout mDrawerLayout;
		    private ListView mDrawerlist;
		    private ArrayList<String> menuList;
		    private ArrayAdapter<String> adapter;

		    @Override
		    protected void onCreate(Bundle savedInstanceState) {
		        super.onCreate(savedInstanceState);
		        setContentView(R.layout.activity_main);

		        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		        mDrawerlist = (ListView) findViewById(R.id.left_drawer);
		        menuList = new ArrayList<String>();
		        for (int i = 0; i < 5; i++) {
		            menuList.add("知乎0" + i);
		        }
		        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menuList);
		        mDrawerlist.setAdapter(adapter);//就为左侧的抽屉添加上了内容

		        mDrawerlist.setOnItemClickListener(this);

		        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		        setSupportActionBar(toolbar);

		       FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
		        fab.setOnClickListener(new View.OnClickListener() {
		            @Override
		            public void onClick(View view) {
		                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
		                        .setAction("Action", null).show();
		            }
		        });
		    }

		    @Override
		    public boolean onCreateOptionsMenu(Menu menu) {
		        // Inflate the menu; this adds items to the action bar if it is present.
		        getMenuInflater().inflate(R.menu.menu_main, menu);
		        return true;
		    }

		    @Override
		    public boolean onOptionsItemSelected(MenuItem item) {
		        // Handle action bar item clicks here. The action bar will
		        // automatically handle clicks on the Home/Up button, so long
		        // as you specify a parent activity in AndroidManifest.xml.
		        int id = item.getItemId();

		        //noinspection SimplifiableIfStatement
		        if (id == R.id.action_settings) {
		            return true;
		        }

		        return super.onOptionsItemSelected(item);
		    }

		    @Override
		    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		        //动态插入一个Fragment到FrameLayout当中
		        Fragment contentFragment = new ContentFragment();
		        Bundle args = new Bundle();
		        args.putString("text", menuList.get(position));

		        FragmentManager fm = getFragmentManager();
		        fm.beginTransaction().replace(R.id.content_frame, contentFragment).commit();

		        mDrawerLayout.closeDrawer(mDrawerlist);//隐藏菜单


		    }
		}



2.
	创建一个 ContentFragment 类

		package com.example.fangyi.drawerlayoutusing;

		import android.app.Fragment;
		import android.os.Bundle;
		import android.support.annotation.Nullable;
		import android.view.LayoutInflater;
		import android.view.View;
		import android.view.ViewGroup;
		import android.widget.TextView;

		/**
		 * Created by FANGYI on 2016/2/16.
		 */
		public class ContentFragment extends Fragment {
		    private TextView textView;

		    @Nullable
		    @Override
		    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		        View view = inflater.inflate(R.layout.fragment_content, container, false);
		        textView = (TextView) view.findViewById(R.id.textView);

		        //接收
		        String text = getArguments().getString("text");
		        textView.setText(text);

		        return view;
		    }
		}


3.

	给 ContentFragment 类 创建一个布局 fragment_content.xml 

		<?xml version="1.0" encoding="utf-8"?>
		<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
		    android:orientation="vertical" android:layout_width="match_parent"
		    android:layout_height="match_parent">

		    <TextView
		        android:layout_width="match_parent"
		        android:layout_height="wrap_content"
		        android:id="@+id/textView"
		        android:textSize="25sp"/>

		</LinearLayout>

android:textSize="25sp"//字的



/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



监听抽屉的打开关闭事件 13:00

本课时结合 ActionBar 的基本使用，详解 ActionBarDrawerToggle 实现机制，为 DrawerLayout 设置 ActionBarDrawerToggle 监听事件，来完成监听抽屉状态变化的功能。


1、mDrawerLayout.setDrawerListener(DrawerLayout.DrawerListener);
2、ActionBarDrawerToggle 是 DrawerLayout.DrawerListener 的具体实现类
	1）、改变 android.R.id.home图标(构造方法)
	2）、Drawer拉出、隐藏，带有 android.R.id.home动画效果(syncState())
	3）、监听Drawer拉出、隐藏事件
3、覆写 ActionBarDrawerToggle 的 onDrawerOpened() 和 onDrawerClosed() 以监       听抽屉拉出或隐藏事件
4、覆写 Activity的onPostCreate() 和 onConfigurationChanged() 方法


编写过程

1.
	在 MainActivity 中添加

			package com.example.fangyi.drawerlayoutusing;

			import android.app.Fragment;
			import android.app.FragmentManager;
			import android.content.Intent;
			import android.content.res.Configuration;
			import android.net.Uri;
			import android.support.annotation.Nullable;
			import android.support.v4.widget.DrawerLayout;
			import android.support.v4.app.ActionBarDrawerToggle;
			import android.support.v7.app.AppCompatActivity;
			import android.os.Bundle;
			import android.view.Menu;
			import android.view.MenuItem;
			import android.view.View;
			import android.widget.AdapterView;
			import android.widget.ArrayAdapter;
			import android.widget.ListView;

			import java.util.ArrayList;

			public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

			    private DrawerLayout mDrawerLayout;
			    private ListView mDrawerlist;
			    private ArrayList<String> menuList;
			    private ArrayAdapter<String> adapter;
			    private ActionBarDrawerToggle mDrawerToggle;
			    private String mTitle;


			    @Override
			    protected void onCreate(Bundle savedInstanceState) {
			        super.onCreate(savedInstanceState);
			        setContentView(R.layout.activity_main);

			        mTitle = (String) getTitle();

			        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
			        mDrawerlist = (ListView) findViewById(R.id.left_drawer);
			        menuList = new ArrayList<String>();
			        for (int i = 0; i < 5; i++) {
			            menuList.add("知乎0" + i);
			        }
			        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menuList);
			        mDrawerlist.setAdapter(adapter);//就为左侧的抽屉添加上了内容

			        mDrawerlist.setOnItemClickListener(this);

			        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close) {
			            @Override
			            public void onDrawerOpened(View drawerView) {
			                super.onDrawerOpened(drawerView);
			                //更改标题
			                getSupportActionBar().setTitle("请选择");
			                invalidateOptionsMenu();
			            }

			            @Override
			            public void onDrawerClosed(View drawerView) {
			                super.onDrawerClosed(drawerView);
			                getSupportActionBar().setTitle(mTitle);
			                invalidateOptionsMenu();
			            }
			        };
			        mDrawerLayout.setDrawerListener(mDrawerToggle);

			        //开启ActionBar上APP ICON 功能
			        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//启动左上角返回图标
			        getSupportActionBar().setHomeButtonEnabled(true);//使左上角的HomeButton可用
			    }

			    @Override
			    public boolean onPrepareOptionsMenu(Menu menu) {
			        boolean isDrawerOpen = mDrawerLayout.isDrawerOpen(mDrawerlist);
			        menu.findItem(R.id.action_websearch).setVisible(!isDrawerOpen);
			        return super.onPrepareOptionsMenu(menu);
			    }

			    @Override
			    public boolean onOptionsItemSelected(MenuItem item) {
			        //将ActionBar上的图标与Drawer结合起来
			        if (mDrawerToggle.onOptionsItemSelected(item)) {
			            return true;//左上角图标打开或关闭抽屉
			        }
			        switch (item.getItemId()) {
			            case R.id.action_websearch:
			                Intent intent = new Intent();
			                intent.setAction("android.intent.action.VIEW");
			                Uri uri = Uri.parse("http://www.baidu.com");
			                intent.setData(uri);
			                startActivity(intent);
			            default:
			                break;
			        }
			        return super.onOptionsItemSelected(item);
			    }

			    @Override
			    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
			        super.onPostCreate(savedInstanceState);
			        //需要将ActionDrawerToggle与DrawerLayout的状态同步
			        //将ActionBarDrawerToggle中的drawer图标，设置为ActionBar中的Home-Buttond的ICON
			        mDrawerToggle.syncState();
			    }

			    @Override//屏幕发生旋转
			    public void onConfigurationChanged(Configuration newConfig) {
			        super.onConfigurationChanged(newConfig);
			        mDrawerToggle.onConfigurationChanged(newConfig);
			    }

			    @Override
			    public boolean onCreateOptionsMenu(Menu menu) {
			        // Inflate the menu; this adds items to the action bar if it is present.
			        getMenuInflater().inflate(R.menu.main, menu);
			        return true;
			    }

			    @Override
			    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			        Fragment contentFragment = new ContentFragment();
			        Bundle args = new Bundle();
			        args.putString("text", menuList.get(position));
			        contentFragment.setArguments(args);


			        FragmentManager fragmentManager = getFragmentManager();
			        fragmentManager.beginTransaction().replace(R.id.content_frame, contentFragment).commit();
			        mDrawerLayout.closeDrawer(mDrawerlist);

			    }
			}



2.

	在 menu 中添加

	
			<?xml version="1.0" encoding="utf-8"?>
		<menu xmlns:android="http://schemas.android.com/apk/res/android">

		        <item
		            android:id="@+id/action_websearch"
		            android:icon="@drawable/action_search"
		            android:title="webSearch"
		            android:showAsAction="ifRoom">

		        </item>
		</menu>








/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



启用 Action Bar 11:32

本课时讲解针对不同 Android 版本，在 AndroidManifest 文件中启用 Action Bar。


1. 在AndroidManifest中进行配置
		修改
       android:theme="@style/Theme.AppCompat.Light.DarkActionBar"
2.
	MainActivity 修改继承 为 

	ActionBarActivity 

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



在 Action Bar 上添加按钮 17:33

本课时讲解在XML文件中定义按钮，在 Java 文件中使用按钮并添加点击事件，启用 Action Bar 左上角的向上或返回按钮。



在Action Bar上添加按钮


		1、For Android 3.0 and higher only
			1）、android:showAsAction=“always“    
					一直显示在Action Bar上
			2）、android:showAsAction=“ifRoom“    
					如果Action Bar空间足够，则显示
		 	3）、android:showAsAction=“never“     
					不显示在Action Bar中，折叠在OverFlow里
			4）、android:showAsAction=“withText“
					菜单项和它的图标，菜单文本一起显示


		2、For Android 2.1 and higher 
			1）、在menu.xml中加入自定义命名空间
			   	xmlns:yourapp="http://schemas.android.com/apk/res-auto"

			2）、在showAsAction属性前指定命名空间
				yourapp:showAsAction="ifRoom"




/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

SurfaceView是简单高效的用于渲染图形的类，适合用于简单的2D游戏开发。







AndroidSurfaceView的使用 05:34

AndroidSurfaceView的使用视频教程,主讲surfaceview,android surfaceview,surfaceview 大小,了解surfaceview和view的区别,学习surfaceview的使用



2
AndroidSurfaceView绘制单个图形 04:05

AndroidSurfaceView绘制图形视频教程,主讲AndroidSurfaceView绘制图形,surfaceview,android surfaceview,surfaceview 透明,surfaceview 大小,学会用AndroidSurfaceView绘制单个图形

1.

	新建一个类 MyView 

			package com.example.fangyi.surfaceview;

			import android.content.Context;
			import android.graphics.Canvas;
			import android.graphics.Color;
			import android.graphics.Paint;
			import android.view.SurfaceHolder;
			import android.view.SurfaceView;

			/**
			 * Created by FANGYI on 2016/2/20.
			 */
			public class MyView extends SurfaceView implements SurfaceHolder.Callback{

			    private Paint paint = null;
			    public MyView(Context context) {
			        super(context);
			        paint = new Paint();
			        paint.setColor(Color.RED);
			        getHolder().addCallback(this);//回调函数
			    }

			    public void draw() {
			        Canvas canvas = getHolder().lockCanvas();//创建锁定画布
			        canvas.drawColor(Color.WHITE);
			        canvas.drawRect(0, 0, 100, 100, paint);//绘制方格



			        getHolder().unlockCanvasAndPost(canvas);//解锁画布
			    }

			    @Override//创建之后
			    public void surfaceCreated(SurfaceHolder holder) {
			        draw();
			    }

			    @Override//状态改变
			    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

			    }

			    @Override//结束
			    public void surfaceDestroyed(SurfaceHolder holder) {

			    }
			}


2.

	把 MainActivity 中的

	setContentView(R.id.activity_main);

	改成

	setContentView(new MyView(this));





/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



AndroidSurfaceView绘制多个图形 07:08

AndroidSurfaceView绘制多个图形视频教程,以android实例讲解AndroidSurfaceView绘制图形,了解surfaceview,android surfaceview,surfaceview 透明,surfaceview 大小等,掌握AndroidSurfaceView绘制多个图形



package com.example.fangyi.surfaceview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by FANGYI on 2016/2/20.
 */
public class MyView_2 extends SurfaceView implements SurfaceHolder.Callback{
    private Paint paint = null;
    public MyView_2(Context context) {
        super(context);

        paint = new Paint();
        paint.setColor(Color.RED);

        getHolder().addCallback(this);
    }

    private  void draw() {
        Canvas canvas = getHolder().lockCanvas();
        canvas.drawColor(0xFFFFFFFF);
        canvas.save();//保存
        canvas.rotate(90, getWidth()/2, getHeight()/2);//旋转
        canvas.drawLine(0, getHeight() / 2, getWidth(), getHeight(), paint);//画线
        canvas.restore();//复原，如果没有这句，下面的也都是旋转90度的效果
        canvas.drawLine(0, getHeight() / 2 + 100, getWidth(), getHeight() + 100, paint);

        getHolder().unlockCanvasAndPost(canvas);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        draw();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}



/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



AndroidSurfaceView绘制组合图形1 11:13

AndroidSurfaceView绘制组合图形视频教程,主讲AndroidSurfaceView绘制组合图形,绘图api,绘图 api,掌握用SurfaceView绘制组合图形的方法


1.

	package com.example.fangyi.surfaceview;

	import android.content.Context;
	import android.graphics.Canvas;
	import android.graphics.Color;
	import android.view.SurfaceHolder;
	import android.view.SurfaceView;

	import java.util.List;

	/**
	 * Created by FANGYI on 2016/2/20.
	 */
	public class MyView_3 extends SurfaceView implements SurfaceHolder.Callback{

	    private Contanier contanier;
	    private Rect rect;
	    private Circle circle;


	    public MyView_3(Context context) {
	        super(context);

	        contanier = new Contanier();
	        rect = new Rect();
	        circle = new Circle();

	        rect.addChildrenView(circle);
	        contanier.addChildrenView(rect);

	        getHolder().addCallback(this);
	    }

	    public void draw() {
	        Canvas canvas = getHolder().lockCanvas();
	        canvas.drawColor(Color.WHITE);
	        contanier.draw(canvas);

	        getHolder().unlockCanvasAndPost(canvas);
	    }



	    @Override
	    public void surfaceCreated(SurfaceHolder holder) {
	        draw();
	    }

	    @Override
	    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

	    }

	    @Override
	    public void surfaceDestroyed(SurfaceHolder holder) {

	    }
	}




2.

	package com.example.fangyi.surfaceview;

	import android.graphics.Canvas;

	import java.util.ArrayList;
	import java.util.List;

	/**
	 * Created by FANGYI on 2016/2/20.
	 */
	public class Contanier {
	    private List<Contanier> children = null;
	    public Contanier() {
	        children = new ArrayList<Contanier>();
	    }

	    public void draw(Canvas canvas) {
	        childrenView(canvas);
	        for (Contanier c : children) {
	            c.draw(canvas);
	        }

	    }

	    public void childrenView(Canvas canvas) {

	    }

	    public void addChildrenView(Contanier child) {
	        children.add(child);
	    }

	    public void removechildrenView(Contanier child) {
	        children.remove(child);
	    }
	}


3.


	package com.example.fangyi.surfaceview;

	import android.graphics.Canvas;
	import android.graphics.Color;
	import android.graphics.Paint;


	/**
	 * Created by FANGYI on 2016/2/20.
	 */
	public class Rect extends Contanier {

	    private Paint paint = null;
	    public Rect() {
	        paint = new Paint();
	        paint.setColor(Color.RED);
	    }

	    @Override
	    public void childrenView(Canvas canvas) {
	        super.childrenView(canvas);
	        canvas.drawRect(0, 0, 100, 100 , paint);
	    }
	}


4.

	package com.example.fangyi.surfaceview;

	import android.graphics.Canvas;
	import android.graphics.Color;
	import android.graphics.Paint;

	/**
	 * Created by FANGYI on 2016/2/20.
	 */
	public class Circle extends Contanier{
	    private Paint paint = null;
	    public Circle() {
	        paint = new Paint();
	        paint.setColor(Color.BLUE);
	    }

	    @Override
	    public void childrenView(Canvas canvas) {
	        super.childrenView(canvas);
	        canvas.drawCircle(50, 50, 50, paint);
	    }
	}




/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



AndroidSurfaceView绘制组合图形2 05:10

AndroidSurfaceView绘制组合图形2视频教程,继续主讲AndroidSurfaceView绘制组合图形,SurfaceView api,绘图api,绘图 api,全面掌握SurfaceView绘制组合图形的用法





1.


package com.example.fangyi.surfaceview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by FANGYI on 2016/2/20.
 */
public class MyView_3 extends SurfaceView implements SurfaceHolder.Callback{

    private Contanier contanier;
    private Rect rect;
    private Circle circle;


    public MyView_3(Context context) {
        super(context);

        contanier = new Contanier();
        rect = new Rect();
        circle = new Circle();

        rect.addChildrenView(circle);
        contanier.addChildrenView(rect);

        getHolder().addCallback(this);
    }

    private Timer timer = null;
    private TimerTask task = null;

    public void startTimer() {
        timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                draw();
            }
        };
        timer.schedule(task, 100, 100);
    }

    public void stopTimer() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    public void draw() {
        Canvas canvas = getHolder().lockCanvas();
        canvas.drawColor(Color.WHITE);
        contanier.draw(canvas);

        getHolder().unlockCanvasAndPost(canvas);
    }



    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        startTimer();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        stopTimer();
    }
}



2.


package com.example.fangyi.surfaceview;

import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FANGYI on 2016/2/20.
 */
public class Contanier {
    private List<Contanier> children = null;
    private float x = 0, y = 0;
    public Contanier() {
        children = new ArrayList<Contanier>();
    }

    public void draw(Canvas canvas) {

        canvas.save();
        canvas.translate(getX(), getY());//移动
        childrenView(canvas);
        for (Contanier c : children) {
            c.draw(canvas);
        }
        canvas.restore();
    }

    public void childrenView(Canvas canvas) {

    }

    public void addChildrenView(Contanier child) {
        children.add(child);
    }

    public void removechildrenView(Contanier child) {
        children.remove(child);
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}





3.

package com.example.fangyi.surfaceview;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;


/**
 * Created by FANGYI on 2016/2/20.
 */
public class Rect extends Contanier {

    private Paint paint = null;
    public Rect() {
        paint = new Paint();
        paint.setColor(Color.RED);
    }

    @Override
    public void childrenView(Canvas canvas) {
        super.childrenView(canvas);
        canvas.drawRect(0, 0, 100, 100 , paint);
        this.setY(this.getY() +1);
    }
}



/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

Android 中的消息通知 Toast 和 Notification



弹出通知Toast 15:22

本课时学习创建长短不一的Toast提示，并自定义Toast在屏幕上的位置以及Toast的外观。



    private Button showToastShort;
    private Button showToastLong;
    private Button showToastImage;


        showToastShort = (Button) findViewById(R.id.shouToast);
        showToastLong = (Button) findViewById(R.id.shouToastLong);
        showToastImage = (Button) findViewById(R.id.showToastImage);
        showToastShort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(MainActivity.this, "显示一个简短的Toast", Toast.LENGTH_SHORT).show();//显示时间短一点
                Toast ashortToast = Toast.makeText(MainActivity.this, "显示一个简短的Toast", Toast.LENGTH_SHORT);//需要把show()去掉，不然传进去的是空值
                ashortToast.setGravity(Gravity.CENTER, 0, 0);//Gravity.CENTER 屏幕的正中间，原点就在正中间，后面两个值，是偏移量
                ashortToast.show();
            }
        });
        showToastLong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "显示一个较长的Toast", Toast.LENGTH_LONG).show();//显示时间长一点
            }
        });

        showToastImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast imageToast = Toast.makeText(MainActivity.this, "显示图片的Toaset", Toast.LENGTH_LONG);
                ImageView imageView = new ImageView(MainActivity.this);
                imageView.setImageResource(R.mipmap.ic_launcher);
                imageToast.setView(imageView);
                imageToast.show();
            }
        });





/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


状态栏提示Notification 12:02

本课时学习创建Notification对象，为其指定标题、内容和图标，以及Notification的更新方法。


    private static final int NOTIFICATION_ID = 1200;
    private Button showNotification;
    private int counter = 0;


        showNotification = (Button) findViewById(R.id.button);
        showNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter ++;

                NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this);
                builder.setSmallIcon(R.mipmap.ic_launcher);
                builder.setContentTitle("哇哦！有"+ counter +"个新消息！");
                builder.setContentText("你已经可以创建新的Notification了。");
                Notification notification = builder.build();

                NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                manager.notify(NOTIFICATION_ID, notification);
            }
        });








/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


Android项目开发实战:自定义左右菜单


完成菜单布局 11:49

本课时讲解通过扩展，完成基本布局。包含左右菜单布局和中间内容区域，并设置颜色用于区分。

1.
	新建类 MainUI 


		ppackage com.example.fangyi.mymenu;

		import android.content.Context;
		import android.graphics.Color;
		import android.util.AttributeSet;
		import android.view.MotionEvent;
		import android.widget.FrameLayout;
		import android.widget.RelativeLayout;

		/**
		 * Created by FANGYI on 2016/2/21.
		 */

		//                   RelativeLayout(最大的边框)
		//        ***************(t)***************
		//        *         *           *         *
		//        *         *          (r)        *
		//        *leftMenu *           *         *
		//        *         *           *         *
		//        *         *middleMenu *         *
		//        *         *           *         *
		//        *        (L)          *rightMenu*
		//        *         *           *         *
		//        *         *           *         *
		//        ***************(b)***************


		public class MainUI extends RelativeLayout{
		    private Context context;
		    private FrameLayout leftMenu;
		    private FrameLayout middleMenu;
		    private FrameLayout rightMenu;

		    public MainUI(Context context) {
		        super(context);
		        initView(context);
		    }

		    public MainUI(Context context, AttributeSet attrs) {
		        super(context, attrs);
		        initView(context);
		    }

		    //初始化方法
		    private void initView(Context context) {
		        this.context = context;
		        leftMenu = new FrameLayout(context);//左菜单
		        middleMenu = new FrameLayout(context);//中间屏幕区域
		        rightMenu = new FrameLayout(context);//右菜单

		        leftMenu.setBackgroundColor(Color.RED);//赋色
		        middleMenu.setBackgroundColor(Color.GREEN);
		        rightMenu.setBackgroundColor(Color.BLUE);

		        addView(leftMenu);//填充到最大的RelativeLayout中
		        addView(middleMenu);
		        addView(rightMenu);
		    }

		    @Override//测量宽度、高度，下面函数的宽度和高度是屏幕的
		    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		        middleMenu.measure(widthMeasureSpec, heightMeasureSpec);

		        //先获取当前屏幕最大的宽度
		        int realWidth = MeasureSpec.getSize(widthMeasureSpec);
		        //屏幕的百分之80，第一个参数是size，第二个参数是以怎样的方式进行测量，EXACTLY精准,AT_MOST自适应，UNSPECIFIED未定义
		        int temoWidthMeasure = MeasureSpec.makeMeasureSpec((int)(realWidth*0.8f), MeasureSpec.EXACTLY);

		        leftMenu.measure(temoWidthMeasure, heightMeasureSpec);
		        rightMenu.measure(temoWidthMeasure, heightMeasureSpec);
		    }

		    @Override//填充，当前上下左右边界的位置，左l 上t，下b，右r
		    protected void onLayout(boolean changed, int l, int t, int r, int b) {
		        super.onLayout(changed, l, t, r, b);

		        //当前屏幕位置
		        middleMenu.layout(l, t, r ,b);

		        leftMenu.layout(l - leftMenu.getMeasuredWidth(), t, r, b);

		        rightMenu.layout(
		                l + middleMenu.getMeasuredWidth(), t,
		                l + middleMenu.getMeasuredWidth()
		                        + rightMenu.getMeasuredWidth(), b);
		    }

		}





2.
	在 MainActivity 中添加


			package com.example.fangyi.mymenu;

			import android.support.v7.app.AppCompatActivity;
			import android.os.Bundle;

			public class MainActivity extends AppCompatActivity {

			    private MainUI mainUI;

			    @Override
			    protected void onCreate(Bundle savedInstanceState) {
			        super.onCreate(savedInstanceState);
			        mainUI = new MainUI(this);
			        setContentView(mainUI);
			    }
			}




/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



菜单左右滑动 12:25

本课时讲解使用逻辑，找出滑动中间点，并对事件做处理，主要针对滑动事件。






    private boolean isTestCompete;//判断事件
    private boolean isleftrightEvent;

    @Override//事件分发
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (!isTestCompete) {
            getEventType(ev);//对事件进行处理
            return true;
        }
        if (isleftrightEvent) {//如果是左右滑动
            switch (ev.getActionMasked()) {
                case MotionEvent.ACTION_MOVE:
                    int curScrollX = getScrollX();//滚动的距离
                    int dis_x = (int) (ev.getX()-point.x);//手指放下，以及滑动的距离

                    int expectX = -dis_x + curScrollX;//>20向右，<20向左，他俩差值在20之间，只不过一个是正的一个是负的

                    int finalX = 0;//滑动多少滚动多少，

                    if (expectX <0) {
                        finalX = Math.max(expectX, -leftMenu.getMeasuredWidth());
                    }else {
                        finalX = Math.min(expectX, rightMenu.getMeasuredHeight());
                    }
                    scrollTo(finalX, 0);//移动到当前这个位置
                    point.x = (int) ev.getX();
                    break;

                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                        isleftrightEvent = false;
                        isTestCompete = false;
            }
        }else {//其他的初始化操作，
            switch (ev.getActionMasked()) {
                case MotionEvent.ACTION_UP:
                    isleftrightEvent = false;
                    isTestCompete = false;
                    break;
            }

        }


        return super.dispatchTouchEvent(ev);
    }

    private Point point = new Point(); //获取点，根据点来获取到滑动的距离，根据滑动距离判断是滑动还是点击
    private static final int TEST_DIS = 20;//大于20，滑动，小于20点击


    private void getEventType(MotionEvent ev) {
        switch (ev.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                point.x = (int) ev.getX();
                point.y = (int) ev.getY();

                break;

            case MotionEvent.ACTION_MOVE:   //移动
                int dX = (int) Math.abs(ev.getX() - point.x);//左右滑动的距离
                int dY = (int) Math.abs(ev.getY() - point.y);//上下滑动的距离
                if (dX >= TEST_DIS && dX>dY) {
                    //左右滑动
                    isleftrightEvent = true;
                    isTestCompete = true;
                    //为了保证每次滑动以后继续滑动，我们还要获取当前点的坐标
                    point.x = (int) ev.getX();
                    point.y = (int) ev.getY();



                }else if (dY >= TEST_DIS && dY>dX) {
                    //上下滑动
                    isleftrightEvent = false;
                    isTestCompete = true;
                    //为了保证每次滑动以后继续滑动，我们还要获取当前点的坐标
                    point.x = (int) ev.getX();
                    point.y = (int) ev.getY();
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL: //触摸边缘
                break;
        }
    }






/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


加入左右滑动动画 06:12

本课时讲解通过使用 Scroll 进行滑动事件处理，主要是自动滑动，增加用户体验。

添加蒙版效果 05:39

本课时讲解如何添加蒙版效果，通过增加 View，对 View 的透明度做处理，完成蒙版的添加。

1.



package com.jikexueyuan.mymenu;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Scroller;


/**
 * Created by FANGYI on 2016/2/21.
 */

//                   RelativeLayout(最大的边框)
//        ***************(t)***************
//        *         *           *         *
//        *         *          (r)        *
//        *leftMenu *           *         *
//        *         *           *         *
//        *         *middleMenu *         *
//        *         *           *         *
//        *        (L)          *rightMenu*
//        *         *           *         *
//        *         *           *         *
//        ***************(b)***************
//        
//        

public class MainUI extends RelativeLayout {
	private Context context;
	private FrameLayout leftMenu;
	private FrameLayout middleMenu;
	private FrameLayout rightMenu;
	private FrameLayout middleMask;
	private Scroller mScroller;
	public static final int LEFT_ID = 0xaabbcc;
	public static final int MIDEELE_ID = 0xaaccbb;
	public static final int RIGHT_ID = 0xccbbaa;

	public MainUI(Context context) {
		super(context);
		initView(context);
	}

	public MainUI(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
	}
 //初始化方法
	private void initView(Context context) {
		this.context = context;
		mScroller = new Scroller(context, new DecelerateInterpolator());

		leftMenu = new FrameLayout(context);//左菜单
		middleMenu = new FrameLayout(context);//中间屏幕区域
		rightMenu = new FrameLayout(context);//右菜单
		middleMask = new FrameLayout(context);

		leftMenu.setBackgroundColor(Color.RED);
		middleMenu.setBackgroundColor(Color.GREEN);
		rightMenu.setBackgroundColor(Color.RED);
		middleMask.setBackgroundColor(0x88000000);

		leftMenu.setId(LEFT_ID);
		middleMenu.setId(MIDEELE_ID);
		rightMenu.setId(RIGHT_ID);

		addView(leftMenu);//填充到最大的RelativeLayout中
		addView(middleMenu);
		addView(rightMenu);
		addView(middleMask);

		middleMask.setAlpha(0);
	}
	
	public float onMiddleMask(){
		System.out.println("透明度:"+middleMask.getAlpha());
		return middleMask.getAlpha();
	}
	
	@Override//根据滑动的距离变化而变化
	public void scrollTo(int x, int y) {
		super.scrollTo(x, y);
		onMiddleMask();
		int curX = Math.abs(getScrollX());
		float scale = curX/(float)leftMenu.getMeasuredWidth();
		middleMask.setAlpha(scale);
		
	}

	@Override//测量宽度、高度，下面函数的宽度和高度是屏幕的
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		middleMenu.measure(widthMeasureSpec, heightMeasureSpec);
		middleMask.measure(widthMeasureSpec, heightMeasureSpec);

		//先获取当前屏幕最大的宽度
		int realWidth = MeasureSpec.getSize(widthMeasureSpec);
		//屏幕的百分之80，第一个参数是size，第二个参数是以怎样的方式进行测量，EXACTLY精准,AT_MOST自适应，UNSPECIFIED未定义
		int tempWidthMeasure = MeasureSpec.makeMeasureSpec(
				(int) (realWidth * 0.8f), MeasureSpec.EXACTLY);

		leftMenu.measure(tempWidthMeasure, heightMeasureSpec);
		rightMenu.measure(tempWidthMeasure, heightMeasureSpec);
	}

	@Override//填充，当前上下左右边界的位置，左l 上t，下b，右r
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
		//当前屏幕位置
		middleMenu.layout(l, t, r, b);
		middleMask.layout(l, t, r, b);
		leftMenu.layout(l - leftMenu.getMeasuredWidth(), t, r, b);
		rightMenu.layout(
				l + middleMenu.getMeasuredWidth(),
				t,
				l + middleMenu.getMeasuredWidth()
						+ rightMenu.getMeasuredWidth(), b);
	}

	private boolean isTestCompete;
	private boolean isleftrightEvent;

	@Override//事件分发
	public boolean dispatchTouchEvent(MotionEvent ev) {
		if (!isTestCompete) {
			getEventType(ev);//对事件进行处理
			return true;
		}
		if (isleftrightEvent) {//如果是左右滑动
			switch (ev.getActionMasked()) {
			case MotionEvent.ACTION_MOVE:
				int curScrollX = getScrollX();//滚动的距离
				int dis_x = (int) (ev.getX() - point.x);//手指放下，以及滑动的距离
				int expectX = -dis_x + curScrollX;//>20向右，<20向左，他俩差值在20之间，只不过一个是正的一个是负的

				int finalX = 0;//滑动多少滚动多少，

				if (expectX < 0) {
					finalX = Math.max(expectX, -leftMenu.getMeasuredWidth());
				} else {
					finalX = Math.min(expectX, rightMenu.getMeasuredWidth());
				}
				scrollTo(finalX, 0);//移动到当前这个位置
				point.x = (int) ev.getX();
				break;

			case MotionEvent.ACTION_UP:
			case MotionEvent.ACTION_CANCEL:
				curScrollX = getScrollX();
				if (Math.abs(curScrollX) > leftMenu.getMeasuredWidth() >> 1) {
					//当滚动的距离大于一半时候，弹出来
					if (curScrollX < 0) {
						//<0,手指向右动，出现左菜单
						////为了体验效果，从手指的位置开始滚动出来,第五个参数是动画时间
						mScroller.startScroll(curScrollX, 0,
								-leftMenu.getMeasuredWidth() - curScrollX, 0,
								200);
					} else {
						//当滚动的距离小于一半，复原回原位
						mScroller.startScroll(curScrollX, 0,
								leftMenu.getMeasuredWidth() - curScrollX, 0,
								200);
					}

				} else {
					mScroller.startScroll(curScrollX, 0, -curScrollX, 0, 200);
				}
				invalidate();//View的重绘，刷新
				isleftrightEvent = false;
				isTestCompete = false;
				break;
			}
		} else {//其他的初始化操作
			switch (ev.getActionMasked()) {
			case MotionEvent.ACTION_UP:
				isleftrightEvent = false;
				isTestCompete = false;
				break;

			default:
				break;
			}
		}

		return super.dispatchTouchEvent(ev);
	}

	@Override//回调的机制
	public void computeScroll() {
		super.computeScroll();
		if (!mScroller.computeScrollOffset()) {
			return;
		}
		int tempX = mScroller.getCurrX();//定义一个滑动整体的值
		scrollTo(tempX, 0);
	}

	private Point point = new Point();//获取点，根据点来获取到滑动的距离，根据滑动距离判断是滑动还是点击
	private static final int TEST_DIS = 20;//大于20，滑动，小于20点击

	private void getEventType(MotionEvent ev) {
		switch (ev.getActionMasked()) {
		case MotionEvent.ACTION_DOWN:
			point.x = (int) ev.getX();
			point.y = (int) ev.getY();
			super.dispatchTouchEvent(ev);
			break;

		case MotionEvent.ACTION_MOVE://移动
			int dX = Math.abs((int) ev.getX() - point.x);//左右滑动的距离
			int dY = Math.abs((int) ev.getY() - point.y);//上下滑动的距离
			if (dX >= TEST_DIS && dX > dY) { // 左右滑动
				isleftrightEvent = true;
				isTestCompete = true;
				//为了保证每次滑动以后继续滑动，我们还要获取当前点的坐标
				point.x = (int) ev.getX();
				point.y = (int) ev.getY();
			} else if (dY >= TEST_DIS && dY > dX) { // 上下滑动
				isleftrightEvent = false;
				isTestCompete = true;
				//为了保证每次滑动以后继续滑动，我们还要获取当前点的坐标
				point.x = (int) ev.getX();
				point.y = (int) ev.getY();
			}
			break;
		case MotionEvent.ACTION_UP:
		case MotionEvent.ACTION_CANCEL://触摸边缘
			super.dispatchTouchEvent(ev);
			isleftrightEvent = false;
			isTestCompete = false;
			break;
		}
	}

}




2.


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

public class LeftMenu extends Fragment{

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.left, container,false);
		v.findViewById(R.id.button1).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				System.out.println("Hello jikexueyuan.com");
			}
		});
		return v;
	}
}




3.

	新建一个 left.xml 文件
	添加一个按钮，ID 为 button





/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////







2D翻转-创建动画 10:12

本课时讲解通过使用ScaleAnimation完成动画的创建，并且添加点击事件完成图片的缩放。

本课时讲解添加动画监听事件，复写方法，在动画结束后执行第二个动画并隐藏之前的View，实现2D卡片的翻转效果。





package com.example.fangyi.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

public class MainActivity extends Activity {

    private ImageView imageA;
    private ImageView imageB;

    private ScaleAnimation sato0 = new ScaleAnimation(1, 0, 1, 1,
            Animation.RELATIVE_TO_PARENT, 0.5f, Animation.RELATIVE_TO_PARENT,
            0.5f);
    private ScaleAnimation sato1 = new ScaleAnimation(0, 1, 1, 1,
            Animation.RELATIVE_TO_PARENT, 0.5f, Animation.RELATIVE_TO_PARENT,
            0.5f);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        findViewById(R.id.root).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (imageA.getVisibility() == View.VISIBLE) {
                    imageA.startAnimation(sato0);
                }else{
                    imageB.startAnimation(sato0);
                }
            }
        });

    }

    private void shwoImageA(){
        imageA.setVisibility(View.VISIBLE);
        imageB.setVisibility(View.INVISIBLE);
    }

    private void showImageB(){
        imageA.setVisibility(View.INVISIBLE);
        imageB.setVisibility(View.VISIBLE);
    }

    private void initView(){
        imageA = (ImageView) findViewById(R.id.ivA);
        imageB = (ImageView) findViewById(R.id.ivB);
        shwoImageA();
        sato0.setDuration(500);
        sato1.setDuration(500);

        sato0.setAnimationListener(new AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (imageA.getVisibility() == View.VISIBLE) {
                    imageA.setAnimation(null);
                    showImageB();
                    imageB.startAnimation(sato1);
                }else{
                    imageB.setAnimation(null);
                    shwoImageA();
                    imageA.startAnimation(sato1);
                }
            }
        });
    }
}



/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////




安卓Android触摸事件侦听 03:32

安卓Android触摸事件侦听视频教程,主讲Android触摸事件侦听,安卓触摸事件,android 触摸事件,android触摸事件,android 触摸屏事件,android 触摸监听,掌握为安卓组件添加触摸事件侦听器,并会Android侦听触摸操作



安卓Android触摸事件传递 01:58

安卓Android触摸事件传递视频教程,主讲android 事件传递,android 触摸事件传递,安卓触摸事件,android 触摸事件,学习android触摸事件传递过程和安卓事件传递特点



获取一个安卓Android触摸点坐标 04:48

获取一个安卓Android触摸点坐标视频教程,主讲android触摸点坐标,获取触摸,安卓触摸事件,android 触摸事件,android触摸事件,掌握读取单一触摸点坐标的原理和方法



4
获取多个安卓Android触摸点坐标 04:40

获取多个安卓Android触摸点坐标视频教程,主讲获取触摸,android触摸点坐标,android 多点触摸,多点触摸 android,安卓多点触摸,android 多点触摸,掌握多个触摸点坐标获取方法






    private FrameLayout root;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        root = (FrameLayout) findViewById(R.id.frame);
        imageView = (ImageView) findViewById(R.id.iv);


        //监听触摸事件
        root.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN://触摸按下

                        break;
                    case MotionEvent.ACTION_MOVE://触摸移动
                        //获取触摸点
                        System.out.println(String.format("x:%f y:%f", event.getX(), event.getY()));

                        //获取多个触摸点
                        System.out.println("触摸点个数：" + event.getPointerCount());
                        System.out.println(String.format("x1:%f y1:%f x2:%f y2:%f", event.getX(0), event.getY(0), event.getX(1), event.getY(1)));

                        //拖动图片
                        FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) imageView.getLayoutParams();
                        lp.leftMargin = (int) event.getX();
                        lp.topMargin = (int) event.getY();

                        imageView.setLayoutParams(lp);

                        break;
                    case MotionEvent.ACTION_UP://触摸弹起
                        break;
                }
                return true;//默认fals
            }
        });
    }




/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////




安卓Android缩放图片示例 12:08

安卓Android缩放图片示例视频教程,主讲安卓图片缩放,android图片缩放,android 图片缩放,android图片手势缩放,android 手势缩放,android图片放大缩小,学会两点触控进行缩放图片操作

获取到两个点的距离，只要判断增大还是减少，，实现放大和缩小



package com.example.fangyi.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private FrameLayout root;
    private ImageView imageView;
    float currentDistance;//我们当前的距离
    float lastDistance = -1; //最后一次我们记录的距离


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        root = (FrameLayout) findViewById(R.id.frame);
        imageView = (ImageView) findViewById(R.id.iv);


        //监听触摸事件
        root.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN://触摸按下

                        break;
                    case MotionEvent.ACTION_MOVE://触摸移动
                        if (event.getPointerCount() >= 2) {

                            float offsetX = event.getX(0) - event.getX(1);
                            float offsetY = event.getY(0) - event.getY(1);
                            currentDistance = (float) Math.sqrt(offsetX * offsetY + offsetX * offsetY);
                            if (lastDistance < 0) {
                                lastDistance = currentDistance;
                            }else {
                                if (currentDistance - lastDistance > 5) {
                                    System.out.println("放大的操作");

                                    FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) imageView.getLayoutParams();
                                    lp.width = (int) (1.1*imageView.getWidth());
                                    lp.height = (int) (1.1*imageView.getHeight());

                                    imageView.setLayoutParams(lp);


                                    lastDistance = currentDistance;
                                }else if (lastDistance - currentDistance > 5) {
                                    System.out.println("缩小的操作");
                                    FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) imageView.getLayoutParams();
                                    lp.width = (int) (0.9*imageView.getWidth());
                                    lp.height = (int) (0.9*imageView.getHeight());
                                    imageView.setLayoutParams(lp);

                                    lastDistance = currentDistance;
                                }
                            }

                        }

                        break;
                    case MotionEvent.ACTION_UP://触摸弹起
                        break;
                }
                return true;//默认fals
            }
        });
    }
}



/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


Android中传感器的基本概念 24:11

Android平台支持的丰富的传感器是其亮点之一，虽然相比iPhone来说稍有逊色，但相对于原来占据智能市场的Synbian等手机平台有一个明显的飞跃，现在看到的旅游出行必备的指南针，
甩一甩就显示火苗的模拟打火机都是基于Android内置的传感器。本课时介绍Android中传感器的一些基本概念。



传感器种类

	传感器是第二代智能手机的重要标志之一。可以毫不客气地说，现在市 面上的Android手机和平板电脑（TV除外）都内置了传感器。否则很多游戏 和应用就无法使用了。Android SDK 支持的传感器并
是每一部Android设备都支持所有的传感器。大多数Android设备只支持一部分传感器。例如， 方向传感器（电子罗盘）、重力传感器（屏幕翻转、赛车游戏等）。
 
   动作（Motion）传感器
 环境（Environmental）传感器
 位置（Position）传感器


1.动作传感器

	这类传感器在三个轴（X、Y、Z）上测量加速度和旋转角度。包括如下几 个传感器。
 
		加速（accelerometer）传感器
		重力（gravity）传感器
		陀螺仪（gyroscope）传感器
		旋转向量（rotational vector ）传感器


2.环境传感器

	这类传感器可以测量不同环境的参数，例如，周围环境的空气温度和压 强、光照强度和湿度。包括如下几个传感器。

		湿度（barometer）传感器
		光线（photometer）传感器
		温度（thermometer）传感器


3.位置传感器
	
	这类传感器可以测量设备的物理位置。包括如下几个传感器。
	
		方向（orientation）传感器
		磁力（magnetometer）传感器



/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


Android Sensor Framework

	Android SDK提供了Android sensor framework，可以用来访问当前 Android设备内置的传感器。ASF提供了很多类和接口，可以帮助我们完成 各种与传感器有关的任务。例如，可以利用ASF完成下面的工作。


	确定当前Android设备内置了哪些传感器。  
	确定某一个传感器的技术指标。例如，该传感器能测量的最大范围、传感器的制造商、对电量的要求、测量的精度等。  
	获取传感器传回来的数据，以及定义传感器回传数据的精度。  
	注册和注销传感器事件监听器，这些监听器用于监听传感器的变化，通常从传感器回传的数据需要利用这些监听器完成。


硬件传感器和软件传感器
	
		ASF 允许我们访问很多传感器类型，这些传感器有一些是基于硬件的传感器，还有一些是基于软件的传感器。基于硬件的传感器就是直接以芯片形 式嵌入到Android设备中，这些传感器直接从外部环境
	获取数据，例如，加 速传感器、磁场传感器都属于硬件传感器。基于软件的传感器并不是实际的 硬件芯片，尽管这些传感器重使用上很像基于硬件的传感器。基于软件的传感器传回的数据本质上也来自于
	基于硬件的传感器，只是这些数据通常会经 过二次加工，也就是说，基于软件的传感器传回的数据可能来自一个或多个 基于硬件的传感器，并且有可能Android系统使用某些算法处理了这些数据。所以基于
	软件的传感器也可以称为虚拟（virtual）传感器或合成（synthetic） 传感器。



ASF支持的传感器

	所有的常量都在Sensor类中定义。

		1. TYPE_ACCELEROMETER：加速传感器（硬件传感器）
		2. TYPE_AMBIENT_TEMPERATURE：温度传感器（硬件传感器）
		3. TYPE_GRAVITY：重力传感器（硬件或软件传感器）
		4. TYPE_GYROSCOPE：陀螺仪传感器（硬件传感器）
		5. TYPE_LIGHT：光线传感器（硬件传感器）
		6. TYPE_LINEAR_ACCELERATION：线性加速传感器（硬件或软件传感器）
		7. TYPE_MAGNETIC_FIELD：磁场传感器（硬件传感器）
		8. TYPE_ORIENTATION：方向传感器（软件传感器），数据来自重力和磁场传感器
		9. TYPE_PRESSURE：压力传感器（硬件传感器）
		10.TYPE_PROXIMITY：临近传感器（硬件传感器）
		11.TYPE_RELATIVE_HUMIDITY：湿度传感器（硬件传感器）
		12.TYPE_ROTATION_VECTOR：旋转向量传感器（硬件或软件传感器）
		13. TYPE_TEMPERATURE：温度传感器（硬件传感器），从 Android4.0（API Level = 14）开始被 TYPE_AMBIENT_TEMPERATURE 取代。

ASF中的主要类和接口

 	SensorManager类：用于创建sensor service的实例。该类提供了很多用于访问和枚举传感器，注册和注销传感器监听器的方法。而且还提供了与传感器精度、扫描频率、校正有关的常量。

 	Sensor类：提供了一些用于获取传感器技术参数的方法。如版本、类型、生产商等。

 	SensorEvent类：系统使用该类创建传感器事件对象。该对象可以提供与传感器事件有关的信息。传感器事件对象包括的信息有原始的传感器回传数据、传感器类型、数据的精度以及触发事件的时间。

 	SensorEventListener接口：该接口包含两个回调方法，当传感器的回传值或精度发生变化时，系统会调用这两个回调方法。




    private TextView tvSensors;
    private SensorManager sensorManager;


        tvSensors = (TextView) findViewById(R.id.tvSensors);
        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        for(Sensor sensor: sensors)
        {
            tvSensors.append(sensor.getName() + "\n");
        }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


动作传感器的组成及使用方法 35:40

Android平台支持一些用于监视设备动作的传感器（这样的传感器共有5个），其中两个（加速传感器和陀螺仪传感器）是纯的硬件传感器。本课时介绍动作传感器的组成及使用方法。




1.动作传感器

	Android 平台支持一些用于监视设备动作的传感器（这样的传感器共有5 个）。其中两个（加速传感器和陀螺仪传感器）是纯的硬件传感器。另外三 个（重力传感器、线性加速传感器和旋转向量传感器）
可能是硬件传感器， 也可能是软件传感器。例如，在一些Android设备中，这些基于软件的传感 器会从加速和磁力传感器中获取数据，但在另一些Android设备中也可能从 陀螺仪传感器中获取数据。也就是说，
同一种基于软件的传感器在不同的 Android 设备中回传的数据可能来自不同的硬件传感器。所以基于软件的同 一种传感器在不同的设备中可能精确度、使用范围有所不同。大多数高端 Android设备都会有加
速传感器，还有一些拥有陀螺仪传感器。



2.动作传感器的作用

	动作传感器对于监测设备的移动非常有用，例如，倾斜、震动、旋转和 摆动都属于动作传感器的监测范围。设备的移动通常是对用户输入的直接反应。例如，用户正在游戏中飙车，或控制游戏中的一个小球）。
除此之外，设备所处的物理环境也会反应在设备的动作上，例如，用户正常驾驶汽车，而这是Android设备正安静地躺在旁边的座椅上，尽管设备没有移动，但会 随着车的行驶而不断震动，而且设备也会随着汽车
的移动而移动。 对于第一种情况，可以对设备本身的相对位置进行监测。而对于第二种情况，需要考虑到设备以外的参照系。动作传感器本身一般并不会用于监测 设备的位置，关于设备的位置需要用其他类型的
传感器进行监测，例如，磁场传感器。



3.动作传感器的回传数据

	所有的动作传感器都会返回三个浮点数的值（通过长度为3的数组返回）， 但对于不同的传感器，这三个只的意义不同。例如，对于加速传感器，会返 回三个坐标轴的数据。对于陀螺仪传感器，会返回三个
坐标轴的旋转角速度。



4.加速传感器

	设备从左到右推动，X轴 加速度为正值。
	设备朝着自己推动，Y轴 加速度为正值。
	如果朝着天空以 A m/s^2 的加速度推动，那么Z轴的加速度为 A + 9.81， 所以如果计算实际的加速度（抵消重力加速度），需要减 9.81。






import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MotionSensorActivity extends Activity implements
		SensorEventListener {

	private TextView tvAccelerometer;
	private SensorManager mSensorManager;//传感器对象
	private float[] gravity = new float[3];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_motion_sensor);

		tvAccelerometer = (TextView) findViewById(R.id.tvAccelerometer);

		mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {

	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		switch (event.sensor.getType()) {
		case Sensor.TYPE_ACCELEROMETER: // 加速度传感器
			final float alpha = (float) 0.8;
			gravity[0] = alpha * gravity[0] + (1 - alpha) * event.values[0];
			gravity[1] = alpha * gravity[1] + (1 - alpha) * event.values[1];
			gravity[2] = alpha * gravity[2] + (1 - alpha) * event.values[2];

			String accelerometer = "加速度\n" + "X:"
					+ (event.values[0] - gravity[0]) + "\n" + "Y:"
					+ (event.values[1] - gravity[1]) + "\n" + "Z:"
					+ (event.values[2] - gravity[2]);
			Log.d("z", String.valueOf(event.values[2] - gravity[2]));//因为地球有重力加速度，所以减去才是我们用到的值
			tvAccelerometer.setText(accelerometer);
			
			// 9.81m/s^2
			break;
		case Sensor.TYPE_GRAVITY:
			gravity[0] = event.values[0];
			gravity[1] = event.values[1];
			gravity[2] = event.values[2];
			break;
		case Sensor.TYPE_PROXIMITY:
			setTitle(String.valueOf(event.values[0]));
			break;
		default:
			break;
		}

	}

	protected void onResume() {
		super.onResume();
		//第一个参数是实现前面接口的对象实例，第二个是注册哪一个传感器，第三个是采样率方式，SENSOR开头
		
		//加速度传感器
		mSensorManager.registerListener(this,
				mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_UI);
		//重力传感器，最快方式获取
		mSensorManager.registerListener(this,
				mSensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY),
				SensorManager.SENSOR_DELAY_FASTEST);
		//临近传感器
		mSensorManager.registerListener(this,
				mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY),
				SensorManager.SENSOR_DELAY_UI);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();

		mSensorManager.unregisterListener(this);//卸载传感器，当窗口关闭时，不再接获传感器的数据

	}

}




/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

































































/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



Android 文件读写操作

Android开发中，离不开对文件的操作，因为文件可以存储大量数据，掌握相关技术可以为开发带来许多便利。

核心内容：
1. 文件的基本操作。
2.读取Assets与raw文件夹中的数据。
3. 读写内部存储与外部存储。



文件的基本操作 21:40

本课时讲解File类的认识和相关操作技巧



package com.example.fangyi.fileoperation;

/**
 * Created by FANGYI on 2016/2/22.
 */
public class FileUtil {
    //公用文件名
    public static final String FILE__NAME = "myfile.txt";
    public static final String FOLDER__NAME = "NewFolder";//新文件夹
    public static final String FOLDER__NAME = 
    			"NewFolder2" + File.separator + "Subfolder2";//多级目录
}



package com.example.fangyi.fileoperation;

import java.io.File;

/**
 * Created by FANGYI on 2016/2/22.
 */
public class CreatFile {
	public static void main(String[] args) {
	    File file = new File(FileUtil.FILE__NAME);//传入一个文件名称，可以是绝对路径的也可是相对路径的,也可以仅仅是文件名
	    if (!file.exists()) {//如果不存在
	    	try {
	    		file.createNewFile();
	    		System.out.protected("文件已经创建了");
	    	} catch (IOExption e) {
	    		e.printStackTrace();
	    	}
	    }else {
	    	System.out.protected("文件已经存在");
	    	System.out.protected("文件名为：" + file.getName());
	    	System.out.protected("文件相对路径为：" + file.getPath() + "绝对路径：" + file.getAbsolutePath());
	    	//相对路径：my.file.txt
	    	//文件绝对路径：/Users/acely/Desktop`````````/myfile.txt
	    }
	} 
}





package com.example.fangyi.fileoperation;

/**
 * Created by FANGYI on 2016/2/22.
 * 删除
 */
public class DeleteFile {
    public static void main(String[] args) {
	    File file = new File(FileUtil.FILE__NAME);//传入一个文件名称，可以是绝对路径的也可是相对路径的,也可以仅仅是文件名
	    if (!file.exists()) {//如果当前的文件不存在
	    	try {
	    		file.createNewFile();//创建一个新的
	    		System.out.protected("文件已经创建了");
	    	} catch (IOExption e) {
	    		e.printStackTrace();
	    	}
	    }else {//如果当前文件存在
	    	System.out.protected("文件已经存在");
	    	System.out.protected("文件名为：" + file.getName());
	    	System.out.protected("文件相对路径为：" + file.getPath() + "绝对路径：" + file.getAbsolutePath());
	    	//相对路径：my.file.txt
	    	//文件绝对路径：/Users/acely/Desktop`````````/myfile.txt
	    	//
	    	file.delete();
	    	System.out.protected("文件已经删除了");
	    }
	} 
}



package com.example.fangyi.fileoperation;

/**
 * Created by FANGYI on 2016/2/22.
 * 重命名
 */
public class RenameFile {
    public static void main(String[] args) {
	    File file = new File(FileUtil.FILE__NAME);//myfile.txt
	    File newfile = new File("anotherFile.txt");

	    file.renameTo(newfile);//仅仅操作了文件本身，而文件对象本身（内容）是不会改变的
	    System.out.printlned("文件已经成功的重命名了：" + file.getName());//输出结果：myfile.txt,已经重命名了，但是并没有改变对象的名字
	    System.out.protected("文件大小为：" + file.length());//31，返回的是字节
	    System.out.protected("文件是否可读：" + file.canRead());
	    System.out.protected("文件是否可读：" + file.canRead());
	    System.out.protected("文件是否可写：" + file.canWride());
	   	System.out.protected("文件是否隐藏：" + file.isHidden());
	} 
}



package com.example.fangyi.fileoperation;

/**
 * Created by FANGYI on 2016/2/22.
 * 文件夹的操作
 */
public class RenameFile {
    public static void main(String[] args) {
	    File Folder = new File(FileUtil.FOLDER__NAME);//myfile.txt
	    if (!folder.exists()) {
	    	// try {
	    	// 	// folder.createNewFile();//创建一个新的不带后缀的文件
	    	// } catch (IOExption e) {
	    	// 	e.printStackTrace();
	    	// }

	    	folder.mkdir();
	    	folder.mkdirs();//可以创建多级目录中所有的文件夹

	    	folder.delete();	//文件夹的删除，不可能移除多级目录，必须是空的
	    }
	    
	} 
}


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////




读取 Assets 中的文件数据 09:39

本课时讲解 使用 getResources().getAssets().open(“filename”) 获取Assets文件夹中的文件数据流。



private static final String TAG = "ReadAssets"; 

		try {
			//getAssets() 固定读取Assets文件夹的数据
			InputStream is = getResources().getAssets().open("info.txt");//open 返回 InputStream 类型，返回字节流bit，所以当我们读取文本文件时候，需要包装成字符流
			InputStreamReader isr = new InputStreamReader(is, "UTF-8");//字节流包装字符流
			BufferedReader bfr = new BufferedReader(isr); //进一步包装，提供一个缓冲区
			// Log.i(TAG, bfr.readLine());//每次点击都重新读取一行,从bfr中读取

			String in = "";	//先指定一个空值						//一次输出所有行的数据
			while ((in = bfr.readLine()) != null) {//读取的内容不为空，表明当前行有内容
				Log.i(TAG, in);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



读取raw目录中的文件数据 08:03

本课时讲解使用 getResources().openRawResource(fileid) 方法获取raw文件夹中文件的数据流。

Ctrl+Alt+T：选中一块代码，按此组合键，可快速添加if 、for、try/catch等语句。



在res文件夹中默认没有raw文件夹

private static final String TAG = "RawData"; 


                try {
                    InputStream is = getResources().openRawResource(R.raw.info);//直接调用目录id，返回inputStream 类型
                    InputStreamReader isr = new InputStreamReader(is, "UTF-8");//字节流包装字符流
                    BufferedReader bfr = new BufferedReader(isr); //进一步包装，提供一个缓冲区
                    String inString = "";
                    while(inString = bfr.readLine()) != null) {
                        Log.i(Tag, inString)
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



读写内部存储的文件数据 17:53

本课讲解使用 openFileOutput() 方法获取内部文件的输出流与文件数据的写入，使用 openFileInput() 获取内部文件的输入流并将数据读取出来。 




    private String filename = "test";
    private EditText et;
    private TextView show;



        et = (EditText) findViewById(R.id.er);
        show = (TextView) findViewById(R.id.show);

        findViewById(R.id.writeBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileOutputStream fos = openFileOutput(filename, Context.MODE_PRIVATE);
                    OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
                    osw.write(et.getText().toString());
                    //输出所有缓冲区的内容
                    osw.flush();
                    fos.flush();

                    //关闭顺序，fos先打开的，后打开的先关闭，逐层关闭
                    osw.close();
                    fos.close();
                    Toast.makeText(getApplicationContext(), "写入完成", Toast.LENGTH_LONG).show();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


        findViewById(R.id.readBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileInputStream fis = openFileInput(filename);
                    InputStreamReader is = new InputStreamReader(fis, "UTF-8");
                    char input[] = new char[fis.available()];//获取文件长度
                    is.read(input);

                    is.close();
                    fis.close();
                    String readed = new String(input);

                    show.setText(readed);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


读取外部存储的文件数据 20:30

 本课讲解使用Environment.getExternalStorageDirectory()获取系统SD卡路径，并使用File类进行后续的操作。



        findViewById(R.id.writeBtn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                File myfile = new File(sdcard, "This is my file.txt");//参数第一个是存储路径，存在内存卡里
                if (!sdcard.exists()) {
                    //如果没有sd卡，报错
                    Toast.makeText(getApplicationContext(), "当前系统不具备SD卡目录", Toast.LENGTH_LONG).show();
                    return;
                }
                try {
                    myfile.createNewFile();
                    Toast.makeText(getApplicationContext(), "文件已经创建完成", Toast.LENGTH_LONG).show();
                    FileOutputStream fos = new FileOutputStream(myfile);
                    OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
                    osw.write(et.getText().toString());
                    osw.flush();
                    osw.close();
                    fos.close();

                    Toast.makeText(getApplicationContext(), "文件已经写入完成", Toast.LENGTH_LONG).show();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


        findViewById(R.id.readBtn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File myfile = new File(sdcard, "This is my file.txt");//参数第一个是存储路径，存在内存卡里
                if (myfile.exists()) {
                    try {
                        FileOutputStream fis = new FileOutputStream(myfile);
                        InputStreamReader is = new InputStreamReader(fis, "UTF-8");
                        char input[] = new char[fis.available()];//获取文件长度
                        is.read(input);
                        is.close();
                        fis.close();

                        String inString = new String(input);
                        show.setText(inString);

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }


                }
            }
        });


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
Android 读写首选项




SharedPreference 简介 01:41

SharedPreferences 是一种轻型的数据存储方式，它的本质是基于XML文件存储 Key-Value键值对数据，通常用来存储一些简单的配置信息，其存储位置在/data/data/<包名>/shared_prefs目录下。
本课时介绍 SharedPreference。





SharedPreference 数据的读写操作 13:43

SharedPreference s对象本身只能获取数据而不支持存储和修改，存储修改是通过Editor对象实现。本课时讲解 SharedPreference 数据的读写操作。


1. 新建 两个按钮，，读取和存储数据，再来一个EditText

static final String KEY = "MyValue";
private EditText et;
SharedPreferences preferences;
EditText editor;


		et = (EditText) findViewById(R.id.editText1);
		preferences = getPreferences(Activity.MODE_PRIVATE);
		editor = (EditText) preferences.edit();

		
		findViewById(R.id.readBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String in = preferences.getString(KEY, "当前数值不存在");//第二个参数 如果我们还没有保存KEY这个数据的话，那么我获取的String获取到的默认值
		 		Toast.makeText(getApplicationContext(), in, Toast.LENGTH_LONG).show();

		 	}
		 });

		findViewById(R.id.writeBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString(KEY, et.getText().toString());
                if (editor.commit()) {
                	Toast.makeText(getApplicationContext(), "写入成功", Toast.LENGTH_LONG).show();
                }
		 	}
		 });


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



PreferenceActivity使用方法  26:57

SharedPreference 是纯操作，如果需要配合界面的话，则需要额外的开发，PreferenceActivity 提供了一种快速创建配置首选项界面的方法，并且自动在后台存储首选项数据。


1.

	创建一个 MyPreferenceActivity.class

		package com.example.fangyi.preferenceactivity;

		import android.os.Bundle;
		import android.preference.PreferenceActivity;

		/**
		 * Created by FANGYI on 2016/3/2.
		 */
		public class MyPreferenceActivity extends PreferenceActivity {
		    @Override
		    protected void onCreate(Bundle savedInstanceState) {
		        super.onCreate(savedInstanceState);
		        addPreferencesFromResource(R.xml.mypreference);
		    }
		}

2.

	在 res 文件夹下创建 xml文件夹，在里面添加 mypreference.xml 文件

		<?xml version="1.0" encoding="utf-8"?>
		<PreferenceScreen xmlns:android="http://schemas.android.com/apk/res/android">
		    <CheckBoxPreference
		        android:key="checkbox"
		        android:title="是否开启"
		        android:summaryOn="已经开启"
		        android:summaryOff="已经关闭">

		    </CheckBoxPreference>
		</PreferenceScreen>



3.
	创建一个按钮

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MyPreferenceActivity.class));
            }
        });


4.
	在 AndroidManifest 文件中对 MyPreferenceActivity 进行注册
       

        <activity android:name=".MyPreferenceActivity"
            ></activity>



5.

	在 mypreference.xml 文件下添加

	<?xml version="1.0" encoding="utf-8"?>
	<PreferenceScreen xmlns:android="http://schemas.android.com/apk/res/android">
	    <CheckBoxPreference
	        android:key="checkbox"
	        android:title="是否开启"
	        android:summaryOn="已经开启"
	        android:summaryOff="已经关闭">
	    </CheckBoxPreference>

	    <ListPreference
	        android:key="list"
	        android:title="选择一个选项"
	        android:summary="请点击选择"
	        android:entries="@array/entries"
	        android:entryValues="@array/values">
	    </ListPreference>

	    <EditTextPreference
	        android:key="text"
	        android:dialogTitle="请输入你的名字"
	        android:dialogMessage="有劳了"
	        android:summary="请在此输入"
	        android:title="请输入">

	    </EditTextPreference>
	</PreferenceScreen>





6.

	在 values 中创建 mylistpreference.xml 文件

	上下选项一一对应的


		<?xml version="1.0" encoding="utf-8"?>
		<resources>
		    <string-array name="entries">
		        <item>Java</item>
		        <item>C++</item>
		        <item>C#</item>
		        <item>Swift</item>
		    </string-array>

		    <string-array name="values">
		        <item>Eclips</item>
		        <item>Visual Studio</item>
		        <item>Visual Studio</item>
		        <item>Xcode</item>
		    </string-array>
		</resources>


7.

	在程序中 对项目的数据读取

	public class MyPreferenceActivity extends PreferenceActivity {

	    //checkboxPreference
	    //listPreference
	    //edittextPreference

	    PreferenceManager manager;
	    CheckBoxPreference checkBoxPreference;
	    ListPreference listPreference;
    	EditTextPreference editTextPreference;

	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        addPreferencesFromResource(R.xml.mypreference);

	        manager = getPreferenceManager();
	    
	    //checkboxPreference
	        checkBoxPreference = (CheckBoxPreference) manager.findPreference("checkbox");
	        Toast.makeText(getApplicationContext(), "当前的状态为：" + checkBoxPreference.isChecked(), Toast.LENGTH_LONG).show();
		
		//listPreference
	        listPreference = (ListPreference) manager.findPreference("list");
	        Toast.makeText(getApplicationContext(), listPreference.getEntry() + "的开发环境为：" + listPreference.getValue(), Toast.LENGTH_LONG).show();
	    
	    //edittextPreference
            editTextPreference = (EditTextPreference) manager.findPreference("text");
    		Toast.makeText(getApplicationContext(), "你输入的内容为：" + editTextPreference.getText(), Toast.LENGTH_LONG).show();


	    }
	}


































































































































































































































































































































































