package com.fangyi.mobilesafe.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.fangyi.mobilesafe.R;
import com.fangyi.mobilesafe.service.AddressService;
import com.fangyi.mobilesafe.utils.ServiceStatusUtils;
import com.fangyi.mobilesafe.view.SettingItemView;

/**
 * Created by FANGYI on 2016/5/29.
 */
public class SettingActivity extends AppCompatActivity {

    private SharedPreferences sp;
    //设置自动更新
    private SettingItemView sivUpdate;
    //设置号码归属地显示
    private SettingItemView sivShowAddress;
    private Intent addressIntent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sp = getSharedPreferences("config", MODE_PRIVATE);
        setContentView(R.layout.activity_setting);

        //设置自动更新
        sivUpdate = (SettingItemView) findViewById(R.id.siv_update);
        boolean update = sp.getBoolean("update", true);

        sivUpdate.setChecked(update);

        sivUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sp.edit();
                //得到他是否被勾选
                if (sivUpdate.isChecked()) {
                    //变为非勾选
                    sivUpdate.setChecked(false);
//                    sivUpdate.setDescription("当前状态为自动升级已经关闭");
                    editor.putBoolean("update", false);
                } else {
                    //变为勾选
                    sivUpdate.setChecked(true);
//                    sivUpdate.setDescription("当前状态为自动升级已经开启");
                    editor.putBoolean("update", true);
                }
                editor.commit();
            }
        });

        //设置号码归属地显示
        sivShowAddress = (SettingItemView) findViewById(R.id.siv_showaddress);
        addressIntent = new Intent(this, AddressService.class);
        boolean addressService = ServiceStatusUtils.inRunning(this, "com.fangyi.mobilesafe.service.AddressService");

//        if (addressService) {
//            sivShowAddress.setChecked(true);
//        } else {
//            sivShowAddress.setChecked(false);
//        }
        sivShowAddress.setChecked(addressService);

        sivShowAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sivShowAddress.isChecked()) {
                    //非勾选
                    sivShowAddress.setChecked(false);
                    //关闭服务
                    stopService(addressIntent);
                } else {
                    //勾选
                    sivShowAddress.setChecked(true);
                    //开启服务
                    startService(addressIntent);
                }
            }
        });
    }

    //获得焦点
    @Override
    protected void onResume() {
        super.onResume();
        boolean addressService = ServiceStatusUtils.inRunning(this, "com.fangyi.mobilesafe.service.AddressService");
        sivShowAddress.setChecked(addressService);
    }
}
