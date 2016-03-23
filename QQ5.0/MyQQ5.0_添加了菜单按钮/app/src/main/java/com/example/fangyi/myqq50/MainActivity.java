package com.example.fangyi.myqq50;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.fangyi.myqq50.View.SlidingMenu;

public class MainActivity extends AppCompatActivity {
    private SlidingMenu menuLeftMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        menuLeftMenu = (SlidingMenu) findViewById(R.id.id_menu);
    }

    public void toggleMenu(View view) {
        menuLeftMenu.toggle();//如果打开则关闭，如果关闭则打开
    }
}
