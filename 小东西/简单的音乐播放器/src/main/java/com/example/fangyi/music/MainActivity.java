package com.example.fangyi.music;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {
    private MusicInterface mi;
    public static SeekBar sb;

    public static Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            int duration = bundle.getInt("duration");
            int currentPosition = bundle.getInt("currentPosition");

            sb.setMax(duration);
            sb.setProgress(currentPosition);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sb = (SeekBar) findViewById(R.id.sb);
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            //当手指拖动进度条圆圈，此方法调用
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }
            //当手指按下进度条圆圈，此方法调用
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            //当手指离开进度条圆圈，此方法调用
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int progress = seekBar.getProgress();
                mi.seekTo(progress);
            }
        });


        Intent intent = new Intent(this, MusicService.class);
        startService(intent);
        bindService(intent, new MyServiceConn(), BIND_AUTO_CREATE);
    }

    private class MyServiceConn implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mi = (MusicInterface) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }

    public void play(View v) {
        mi.play();
    }

    public void pause(View v) {
        mi.pause();
    }

    public void continuePlay(View v) {
        mi.continuePlay();
    }

    public void stop(View v) {
        mi.stop();
    }

}
