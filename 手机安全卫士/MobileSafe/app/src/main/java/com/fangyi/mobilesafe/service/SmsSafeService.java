package com.fangyi.mobilesafe.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.telephony.SmsMessage;

import com.fangyi.mobilesafe.db.dao.BlackNumberDao;

/**
 * Created by FANGYI on 2016/6/13.
 */

public class SmsSafeService extends Service{
    private InnerSMSReceiver receiver;
    private BlackNumberDao dao;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    //广播接收者 - 拦截短信
    private class InnerSMSReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Object[] pdus = (Object[]) intent.getExtras().get("pdus");
            for(Object pdu : pdus) {
                SmsMessage sms = SmsMessage.createFromPdu((byte[]) pdu);

                String sender = sms.getOriginatingAddress();

                //通过数据库中添加的电话号进行拦截
                if (dao.queryNumber(sender)) {
                    //要拦截的电话号码
                    String mode = dao.queryMode(sender);
                    if ("1".equals(mode) || "2".equals(mode)) {
                        //短信拦截
                        abortBroadcast();//把短信广播终止
                    }
                }

                String bode = sms.getMessageBody();

                //按照内容去拦截
                if (bode.contains("fapiao")) {
                    abortBroadcast();//把短信广播终止
                }
            }
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        dao = new BlackNumberDao(this);

        //注册监听短信
        receiver = new InnerSMSReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.provider.Telephony.SMS_RECEIVED");//对短信感兴趣
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);//注册最高优先级
        registerReceiver(receiver, filter);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //取消注册监听短信
    }
}
