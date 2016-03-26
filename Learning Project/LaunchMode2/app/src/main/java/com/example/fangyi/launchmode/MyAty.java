package com.example.fangyi.launchmode;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by FANGYI on 2016/1/22.
 */

public class MyAty extends Activity{

    public static final String ACTION ="com.example.fangyi.launchmode.intent.action.MyAty";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myaty);
    }
}