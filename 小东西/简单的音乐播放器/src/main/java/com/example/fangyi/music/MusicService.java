package com.example.fangyi.music;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by FANGYI on 2016/5/21.
 */
public class MusicService extends Service {

    MediaPlayer player;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MusicBinder();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        player = new MediaPlayer();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //此方法调用，player就不能用了，所以卸载摧毁方法里，摧毁方法一调用就说明用户退出了播放器
        player.release();
        player = null;

    }

    class MusicBinder extends Binder implements MusicInterface{
        public void play() {
            MusicService.this.play();
        }

        public void pause() {
            MusicService.this.pause();
        }
        public void continuePlay() {
            MusicService.this.continuePlay();
        }
        public void stop() {
            MusicService.this.stop();
        }

        public void seekTo(int progress) {
            MusicService.this.seekTo(progress);
        }
    }

    private void play() {
        player.reset();
        try {
            player.setDataSource("http://m1.music.126.net/4Dyp0YZUPYmojzG_muvc3A==/1377688080392626.mp3");
            //同步准备
//            player.prepare();
            //异步准备
            player.prepareAsync();
            //设置准备侦听，知道子线程何时准备完毕
            player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                //准备完毕时调用
                @Override
                public void onPrepared(MediaPlayer mp) {
                    player.start();
                    addSeekBar();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
//        player.start();
    }

    private void pause() {
        player.pause();
    }

    private void continuePlay() {
        player.start();

    }

    private void stop() {
        player.stop();
    }

    /**
     * 显示进度条
     */
    private void addSeekBar() {

        //使用计时器去不断的执行获取播放进度的代码
        Timer timer = new Timer();
        //设置计时任务
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //获取当前曲目的持续时间
                //返回处理歌曲的毫秒值
                int duration = player.getDuration();
                //获取当前的播放进度
                //当前播放的位置
                int currentPosition = player.getCurrentPosition();
                Message msg = MainActivity.mHandler.obtainMessage();
                //搭载信息
                Bundle bundle = new Bundle();
                bundle.putInt("duration", duration);
                bundle.putInt("currentPosition", currentPosition);
                msg.setData(bundle);
                //发送消息让进度条更新
                MainActivity.mHandler.sendMessage(msg);
            }
            //参数，5毫秒开始执行任务，每500毫秒执行一次任务
        }, 5, 500);
    }

    /**
     * 拖拽进度条更改播放时间
     */
    private void seekTo(int progress) {
        player.seekTo(progress);
    }
}
