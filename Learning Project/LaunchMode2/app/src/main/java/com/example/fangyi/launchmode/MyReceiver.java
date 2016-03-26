package com.example.fangyi.launchmode;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyReceiver extends BroadcastReceiver {
    public MyReceiver() {
    }

    public static final String ACTION ="com.example.fangyi.launchmode.intent.action.MyReceiver";


    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("接收到了一个消息,消息的内容是：" + intent.getStringExtra("data"));

        abortBroadcast();
    }
}
