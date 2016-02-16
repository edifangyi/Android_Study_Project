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
    private static final int GO_HOME = 1000;
    private static final int GO_GUIDE =1001;

//    我们等待的时间不能直接让他在主线程中沉睡，这是不合理的
//    mHandler发送的消息是很关键的，所有我们需要一个boolean类型的值，判定哪个消息 我们写一个方法init()
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GO_HOME:
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


    private void init() {
        //SharedPreferences来进行储存，首先获取
        SharedPreferences prePreferences = getSharedPreferences("jike", MODE_PRIVATE);

        //先来获取到看他里面是否存在的值，同时把这个值赋给isFirstIn,第一次赋值是没有的
        isFirstIn = prePreferences.getBoolean("isFirstIn", true);
        if (!isFirstIn) {
            mHandler.sendEmptyMessageDelayed(GO_HOME, TIME);
        }else {
            mHandler.sendEmptyMessageDelayed(GO_GUIDE, TIME);
            //当进入过引导界面以后，将这个值储存起来
            SharedPreferences.Editor editor = prePreferences.edit();
            editor.putBoolean("isFirstIn",false);
            editor.commit();//提交

        }
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


}
