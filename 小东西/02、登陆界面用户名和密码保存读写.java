package com.example.fangyi.myapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by FANGYI on 2016/5/4.
 */
public class Login extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
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
//                    File file = new File("data/data/com.example.fangyi.myapp/info.txt");

                    //返回一个File对象，路径是data/data/com.example.fangyi.myapp/files/
                    File file = new File(getFilesDir(), "info.txt");
                    
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

        readAccout();
    }

    private EditText etLoginName;
    private EditText etLoginKey;
    private CheckBox cbLogin;
    private TextView tvShow;
    private Button btnStartLogin;



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

}




/**
 
 */

<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical" android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:paddingBottom="@dimen/activity_vertical_margin"
    android:paddingLeft="@dimen/activity_horizontal_margin"
    android:paddingRight="@dimen/activity_horizontal_margin"
    android:paddingTop="@dimen/activity_vertical_margin">

    <EditText
        android:id="@+id/et_login_name"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="请输入用户名..."/>

    <EditText
        android:id="@+id/et_login_key"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="请输入密码..."
        android:inputType="textPassword"/>

    <RelativeLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content">

        <CheckBox
            android:id="@+id/cb_login"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="记住用户名和密码"
            android:layout_centerVertical="true"/>

        <Button
            android:id="@+id/btn_start_login"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="登陆"
            android:layout_alignParentRight="true" />
    </RelativeLayout>

    <TextView
        android:id="@+id/tv_show"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="显示保存本地中的用户名和密码"
        android:lines="5"/>


</LinearLayout>


/**
 
 */

<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.example.fangyi.myapp">

    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:supportsRtl="true"
        android:theme="@style/AppTheme">
        <activity android:name=".MainActivity">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>

        <activity android:name=".Login"/> ////////////////////////////////////////、这里
    </application>

</manifest>