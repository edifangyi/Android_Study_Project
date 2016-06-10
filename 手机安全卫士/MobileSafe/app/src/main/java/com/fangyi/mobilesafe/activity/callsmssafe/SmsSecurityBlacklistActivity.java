package com.fangyi.mobilesafe.activity.callsmssafe;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.fangyi.mobilesafe.R;

/**
 * Created by FANGYI on 2016/6/10.
 */
public class SmsSecurityBlacklistActivity extends AppCompatActivity {
    private ListView lvSmsSecurityBlacklistList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_security_blacklist);

        lvSmsSecurityBlacklistList = (ListView) findViewById(R.id.lv_sms_security_blacklist_list);

    }

    public void addBlackNumber(View v) {

    }
 }
