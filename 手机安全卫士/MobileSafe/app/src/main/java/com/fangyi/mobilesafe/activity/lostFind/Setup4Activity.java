package com.fangyi.mobilesafe.activity.lostFind;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.fangyi.mobilesafe.R;

/**
 * Created by FANGYI on 2016/5/30.
 */
public class Setup4Activity extends BaseSetupActivity {
    private SharedPreferences sp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sp = getSharedPreferences("config", MODE_PRIVATE);
        setContentView(R.layout.activity_setup4);
    }

    @Override
    public void showNext() {
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("configed", true);
        editor.commit();
        Intent intent = new Intent(this, LostFindActivity.class);
        startActivity(intent);
        //当前页面关闭
        finish();
        overridePendingTransition(R.anim.tran_next_in, R.anim.tran_next_out);
    }

    @Override
    public void showPre() {
        Intent intent = new Intent(this, Setup3Activity.class);
        startActivity(intent);
        //当前页面关闭
        finish();
        overridePendingTransition(R.anim.tran_pre_in, R.anim.tran_pre_out);
    }

}
