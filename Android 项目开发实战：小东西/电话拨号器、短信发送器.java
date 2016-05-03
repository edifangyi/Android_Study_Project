Android 文字使用 sp，长度使用 dp ，不推荐使用 像素 px

 android:inputType="phone" //限制输入
 android:hint="请输入电话好吗..." //提示输入
 android:lines="5" //展示5行内容
 android:gravity="top" //内容文字对齐方式
 android:onClick="send"//如果指定了 值为 send ，必须定义一个 public void send(View view) {}  (在上下文当中 通常在 Activity)



*#*#4636#*#* //手机电话情报


 /**
  
  */
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:paddingBottom="@dimen/activity_vertical_margin"
    android:paddingLeft="@dimen/activity_horizontal_margin"
    android:paddingRight="@dimen/activity_horizontal_margin"
    android:paddingTop="@dimen/activity_vertical_margin"
    tools:context="com.example.fangyi.phonedialer.MainActivity"
    android:orientation="vertical">

    <EditText
        android:id="@+id/et_phone_number"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="请输入电话好吗..."
        android:textSize="25sp"
        android:textColor="#8C8C8C"
        android:inputType="phone"/>

    <Button
        android:id="@+id/but_click_to_dial"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="拨打"/>


    <EditText
        android:id="@+id/et_phone_message"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:lines="5"
        android:gravity="top"
        android:hint="请输入要发送的信息"
        />

    <Button
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:onClick="send"
        android:text="发送"/>
</LinearLayout>


  /**
  
  */
 
package com.example.fangyi.phonedialer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        assignViews();


    }
    private EditText etPhoneNumber;
    private EditText etPhoneMessage;
    private Button butClickToDial;

    private void assignViews() {
        //拿到Button对象
        butClickToDial = (Button) findViewById(R.id.but_click_to_dial);
        //给按钮设置点击监听
        butClickToDial.setOnClickListener(new MyListener());
    }

    /**
     * 点击监听 的方式 一
     *
     * 内部类
     */
    class MyListener implements View.OnClickListener {

        /**
         * 当按钮被用户点击时，调用此方法
         *
         * Called when a view has been clicked.
         *
         * @param v The view that was clicked.
         */
        @Override
        public void onClick(View v) {
            //拿到输入框对象
            etPhoneNumber = (EditText) findViewById(R.id.et_phone_number);
            //拿到用户输入的电话号码
            String phonenumber =  etPhoneNumber.getText().toString();
            //调用打电话方法
            //创建意图对象
            Intent intent = new Intent();
            //设置动作
            intent.setAction(Intent.ACTION_CALL);
            //设置号码
            intent.setData(Uri.parse("tel:" + phonenumber));
            //启动打电话应用
            startActivity(intent);
        }
    }

    /**
     * 点击监听 的 方式 二
     */
    public void send(View view) {
        etPhoneNumber = (EditText) findViewById(R.id.et_phone_number);
        etPhoneMessage = (EditText) findViewById(R.id.et_phone_message);

        String phone_number =  etPhoneNumber.getText().toString();
        String phone_message =  etPhoneNumber.getText().toString();

        //使用发送短信的api
        SmsManager sm = SmsManager.getDefault();

        //发送文本短信
        //发送到的电话号码，null(短信服务中心号码，一般不要动)，广播，广播
        sm.sendTextMessage(phone_number, null, phone_message, null, null);

    }

}


/**
 
 */

<uses-permission android:name="android.permission.CALL_PHONE"></uses-permission>
    <uses-permission android:name="android.permission.SEND_SMS"></uses-permission>


