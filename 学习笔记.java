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
                i.putExtra("data","hello 傻逼桑");
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





3.在AnotherAty中创建
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




























