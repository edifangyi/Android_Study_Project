package com.example.fangyi.usingslidingmenu;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class MainActivity extends AppCompatActivity {

    private SlidingMenu slidingMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        slidingMenu = new SlidingMenu(this);//创建对象
        slidingMenu.setMode(SlidingMenu.LEFT);//设定模式，SlidingMenu在左边\
        slidingMenu.setBehindOffsetRes(R.dimen.sliding_menu_offset);//配置slidingmenu偏移出来的尺寸
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);//全屏都可以拖拽触摸，打开slidingmenu
        slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);//附加到当前的Aty上去

        slidingMenu.setMenu(R.layout.slidingmenu);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //重写KeyDown事件，菜单键按下也能拉出slidingmenu
        switch (keyCode) {
            case KeyEvent.KEYCODE_MENU:
                slidingMenu.toggle(true);
                break;
            default:
                break;
        }
        return super.onKeyDown(keyCode, event);
    }
}
