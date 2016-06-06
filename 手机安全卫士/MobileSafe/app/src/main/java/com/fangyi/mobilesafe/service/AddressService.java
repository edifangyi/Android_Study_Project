package com.fangyi.mobilesafe.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import com.fangyi.mobilesafe.activity.atools.numaddressquery.NumberAddressQueryDao;

/**
 * Created by FANGYI on 2016/6/6.
 */
public class AddressService extends Service {

    /**
     * 电话服务
     */
    private TelephonyManager tm;

    private MyPhoneStateListener listener;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        listener = new MyPhoneStateListener();
        tm.listen(listener, PhoneStateListener.LISTEN_CALL_STATE);
    }

    private class MyPhoneStateListener extends PhoneStateListener {

        /**
         * 呼叫状态
         * 来电号码
         * @param state
         * @param incomingNumber
         */
        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            super.onCallStateChanged(state, incomingNumber);
            switch (state) {
                case TelephonyManager.CALL_STATE_RINGING://来电-铃声响起
                    String address = NumberAddressQueryDao.getAddess(incomingNumber);
                    Toast.makeText(AddressService.this, address, Toast.LENGTH_SHORT).show();
                    break;

            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        /**
         * 取消监听
         */
        tm.listen(listener, PhoneStateListener.LISTEN_NONE);
        listener = null;
    }
}
