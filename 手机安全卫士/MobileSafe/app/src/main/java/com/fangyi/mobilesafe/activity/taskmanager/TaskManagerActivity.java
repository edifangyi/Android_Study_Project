package com.fangyi.mobilesafe.activity.taskmanager;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fangyi.mobilesafe.R;
import com.fangyi.mobilesafe.domain.TaskInfo;
import com.fangyi.mobilesafe.engine.TaskInfoProvider;
import com.fangyi.mobilesafe.utils.SysTemInfoUtils;

import java.util.List;


/**
 * Created by FANGYI on 2016/6/15.
 */

public class TaskManagerActivity extends AppCompatActivity {

    private TextView tvRunProcessCount;
    private TextView tvAvailRam;
    private ListView lvTaskmanger;
    private LinearLayout llTaskLoading;
    private TextView tvTaskStatus;

    private List<TaskInfo> taskInfos;

    private int runningProcessConut;//系统运行进程
    private long availRam;//剩余内存
    private long totalRam;//可用内存

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            lvTaskmanger.setAdapter(new TaskInfoAdapter());
            llTaskLoading.setVisibility(View.GONE);
        }
    };
//    private int i = 1;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taskmanager);

        tvRunProcessCount = (TextView) findViewById(R.id.tv_run_process_count);
        tvAvailRam = (TextView) findViewById(R.id.tv_avail_ram);
        lvTaskmanger = (ListView) findViewById(R.id.lv_taskmanger);
        llTaskLoading = (LinearLayout) findViewById(R.id.ll_task_loading);
        tvTaskStatus = (TextView) findViewById(R.id.tv_task_status);


//        runningProcessConut = SysTemInfoUtils.getRunningProcessCount(this);//5.1挂掉了
        runningProcessConut = SysTemInfoUtils.getRunningProcessCount();
        availRam = SysTemInfoUtils.getAvailRam(this);
        totalRam = SysTemInfoUtils.getTotalRam(this);


        tvRunProcessCount.setText("运行中进程：" + runningProcessConut + "个");
        tvAvailRam.setText("剩余/总内存：" + Formatter.formatFileSize(this, availRam) + "/" + Formatter.formatFileSize(this, totalRam));
        Log.e("AAA", "剩余/总内存：" + Formatter.formatFileSize(this, availRam) + "/" + Formatter.formatFileSize(this, totalRam));

        fillData();


    }

    /**
     * 加载数据
     */
    private void fillData() {
        llTaskLoading.setVisibility(View.VISIBLE);
        new Thread() {
            @Override
            public void run() {
                taskInfos = TaskInfoProvider.getAllTaskInfos(TaskManagerActivity.this);
                handler.sendEmptyMessage(0);
            }
        }.start();
    }

    /**
     * 填充数据
     */
    private class TaskInfoAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return taskInfos.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view;
            ViewHolder viewholder;
            if (convertView != null && convertView instanceof RelativeLayout) {
                view = convertView;
                viewholder = (ViewHolder) view.getTag();
            } else {
                view = View.inflate(TaskManagerActivity.this, R.layout.activity_taskmanager_item, null);
                viewholder = new ViewHolder();

                viewholder.tvTaskName = (TextView) view.findViewById(R.id.tv_task_name);
                viewholder.tvMeninFoSize = (TextView) view.findViewById(R.id.tv_meninfosize);
                viewholder.ivTaskIcon = (ImageView) view.findViewById(R.id.iv_task_icon);

                //把对应关系保存起来
                view.setTag(viewholder);
            }
            TaskInfo taskInfo = taskInfos.get(position);
            viewholder.tvTaskName.setText(taskInfo.getName());
            viewholder.tvMeninFoSize.setText(Formatter.formatFileSize(TaskManagerActivity.this, taskInfo.getMeninfosize()));
            viewholder.ivTaskIcon.setImageDrawable(taskInfo.getIcon());
//            TextView textView = new TextView(TaskManagerActivity.this);
//            textView.setTextColor(Color.BLUE);
//            textView.setText(i + ":" + taskInfos.get(position).toString());
//            i++;

            return view;
        }
    }

    static class ViewHolder {
        TextView tvTaskName;
        TextView tvMeninFoSize;
        ImageView ivTaskIcon;
    }

}
