package com.fangyi.mobilesafe.activity.lostFind;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.fangyi.mobilesafe.R;

/**
 * Created by FANGYI on 2016/5/30.
 */
public class Setup3Activity extends BaseSetupActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup3);
    }

    @Override
    public void showNext() {
        Intent intent = new Intent(this, Setup4Activity.class);
        startActivity(intent);
        //当前页面关闭
        finish();
        overridePendingTransition(R.anim.tran_next_in, R.anim.tran_next_out);
    }

    @Override
    public void showPre() {
        Intent intent = new Intent(this, Setup2Activity.class);
        startActivity(intent);
        //当前页面关闭
        finish();
        overridePendingTransition(R.anim.tran_pre_in, R.anim.tran_pre_out);
    }

}
