package com.fangyi.mobilesafe.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.telephony.SmsMessage;

import com.fangyi.mobilesafe.R;

/**
 * Created by FANGYI on 2016/6/3.
 */
public class SMSReceiver extends BroadcastReceiver {
    private SharedPreferences sp;
    @Override
    public void onReceive(Context context, Intent intent) {
        sp = context.getSharedPreferences("config", Context.MODE_APPEND);
        Object[] pdus = (Object[]) intent.getExtras().get("pdus");
        for (Object pdu : pdus) {
            SmsMessage sms = SmsMessage.createFromPdu((byte[]) pdu);

            //得到发送者
            String sender = sms.getOriginatingAddress();
            String safenumber = sp.getString("safenumber", "");
            //得到内容
            String body = sms.getMessageBody();

            //模拟器这么做，真机的话，就不能这样了
            if (sender.contains(safenumber)) {
                if ("#location#".equals(body)) {
                    //得到手机的GPS位置
                    abortBroadcast();
                } else if ("#alarm#".equals(body)) {
                    MediaPlayer player = MediaPlayer.create(context, R.raw.jd);
                    player.setVolume(1.0f, 1.0f);
                    player.setLooping(true);//循环播放
                    player.start();
                    //播放报警音乐
                    abortBroadcast();
                } else if ("#wipedate#".equals(body)) {
                    //远程数据销毁
                    abortBroadcast();
                } else if ("#*locksereen#".equals(body)) {
                    //远程锁屏
                    abortBroadcast();
                }
            }
        }
    }
}
