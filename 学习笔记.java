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
 		startActivity(new Intent(MainActivity.this,AnotherAty.class));