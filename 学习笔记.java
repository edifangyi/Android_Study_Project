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

        System.out.println("lalalalalalalall            onCreate");

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

显式 Intent 

本课讲解显式 Intent 的声明与使用方式。


public class MyAty extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myaty);  //可以用过这段代码把 myaty 的Activity 绑定起来
    }
}


注册 MyAty 在 AndroidManifest.xml

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

可以在 app 中的 AndroidManifest.xml 标签处添加   android:exported=""  是否可以调用

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

        <activity 							加上下面这句
            android:name=".MyAty" android:label="MyAty">
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
            <intent-filter>
 加这段         <category android:name="android.intent.category.DEFAULT"/>
 加这段         <action android:name="com.example.fangyi.launchmode.intent.action.MyAty" />
            </intent-filter>
        </activity>

//效果， 在app1 中 选择 会有两个选择 MAty 和 MAty1


























