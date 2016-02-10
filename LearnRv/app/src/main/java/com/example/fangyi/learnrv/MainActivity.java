package com.example.fangyi.learnrv;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        rv = new RecyclerView(this);
        setContentView(rv);//把 rv 当成 Activity 的布局


        rv.setLayoutManager(new GridLayoutManager(this,3, LinearLayoutManager.HORIZONTAL, false));//第一个参数传进去this，第二个参数传进去多少列。如果水平的话，第二个参数就是多少行
//        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));//使用线性布局

        //填充内容
        rv.setAdapter(new MyAdapter());
    }

}
