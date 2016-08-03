package com.example.fangyi.mygame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;

public class MainActivity extends AppCompatActivity {

    private GameUI gameUI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //因为当前GameUI 继承SurfaceView 实现了SurfaceHolder.Callback
        gameUI = new GameUI(getApplicationContext());
        //所以在主线程中调用SurfaceView 和 SurfaceHolder.Callback
        setContentView(gameUI);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gameUI.handlerTouch(event);//点击事件进行转移
        return super.onTouchEvent(event);
    }
}
