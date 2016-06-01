package com.fangyi.mobilesafe.activity.lostFind;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.fangyi.mobilesafe.R;

/**
 * Created by FANGYI on 2016/5/30.
 */
public class Setup3Activity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup3);
    }

    /**
     * 下一步按钮的点击事件
     * @param v
     */
    public void next(View v) {
        Intent intent = new Intent(this, Setup4Activity.class);
        startActivity(intent);
        //当前页面关闭
        finish();
        overridePendingTransition(R.anim.tran_next_in, R.anim.tran_next_out);
    }

    /**
     * 上一步按钮的点击事件
     * @param v
     */
    public void pre(View v) {
        Intent intent = new Intent(this, Setup2Activity.class);
        startActivity(intent);
        //当前页面关闭
        finish();
        overridePendingTransition(R.anim.tran_pre_out, R.anim.tran_pre_in);
    }
}
