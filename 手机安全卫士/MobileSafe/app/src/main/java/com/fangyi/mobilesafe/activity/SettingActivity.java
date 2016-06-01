package com.fangyi.mobilesafe.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.fangyi.mobilesafe.R;
import com.fangyi.mobilesafe.view.SettingItemView;

/**
 * Created by FANGYI on 2016/5/29.
 */
public class SettingActivity extends AppCompatActivity {
    private SettingItemView sivUpdate;
    private SharedPreferences sp;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sp = getSharedPreferences("config", MODE_PRIVATE);
        setContentView(R.layout.activity_setting);
        sivUpdate = (SettingItemView) findViewById(R.id.siv_update);
        boolean update = sp.getBoolean("update", true);
        if (update) {
            //自动升级已经开启
//            sivUpdate.setDescription("当前状态为自动升级已经开启");
        } else {
            //自动升级已将关闭
//            sivUpdate.setDescription("当前状态为自动升级已经关闭");
        }
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
    }
}
