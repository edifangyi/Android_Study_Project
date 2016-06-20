package com.fangyi.mobilesafe.service;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.text.format.Formatter;
import android.widget.ProgressBar;
import android.widget.RemoteViews;

import com.fangyi.mobilesafe.R;
import com.fangyi.mobilesafe.receiver.MyAppWidget;
import com.fangyi.mobilesafe.utils.SystemInfoUtils;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by FANGYI on 2016/6/20.
 */

public class UpdateAppWidgetService extends Service {
    private AppWidgetManager awm;
    private Timer timer;
    private TimerTask task;
    private ProgressBar mProgressBar;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        awm = AppWidgetManager.getInstance(this);


        timer = new Timer();//定时器
        task = new TimerTask() {
            @Override
            public void run() {
                //激活的组件 - 通讯
                ComponentName componentName = new ComponentName(UpdateAppWidgetService.this, MyAppWidget.class);
                //序列化 更新远程view的布局
                RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.my_appwidget);
                //使用内存  总内存-剩余内存
                remoteViews.setTextViewText(R.id.tv_appwidget_used, "RAM Used: " + Formatter.formatFileSize(UpdateAppWidgetService.this, SystemInfoUtils.getTotalRam(UpdateAppWidgetService.this) - SystemInfoUtils.getAvailRam(UpdateAppWidgetService.this)));
                //剩余内存
                remoteViews.setTextViewText(R.id.tv_appwidget_free, "Free: " + Formatter.formatFileSize(UpdateAppWidgetService.this, SystemInfoUtils.getAvailRam(UpdateAppWidgetService.this)));


                awm.updateAppWidget(componentName, remoteViews);
            }
        };
        timer.schedule(task, 0, 4000);
    }




    @Override
    public void onDestroy() {
        super.onDestroy();
        //取消定时器工作
        timer.cancel();
        task.cancel();
        timer = null;
        task = null;
    }

}
