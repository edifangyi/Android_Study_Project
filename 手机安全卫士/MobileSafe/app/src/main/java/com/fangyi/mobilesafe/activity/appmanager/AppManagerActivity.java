package com.fangyi.mobilesafe.activity.appmanager;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.StatFs;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.format.Formatter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fangyi.mobilesafe.R;
import com.fangyi.mobilesafe.engine.AppInfo;
import com.fangyi.mobilesafe.engine.AppInfoProvider;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by FANGYI on 2016/6/15.
 */

public class AppManagerActivity extends AppCompatActivity {
    private TextView tvAvailRom;
    private TextView tvAvailSdcard;
    private ListView lvAppmanger;
    private LinearLayout llLoading;

    private List<AppInfo> appInfos; //所有应用程序的信息
    private List<AppInfo> systemAppInfos; //系统应用程序的信息
    private List<AppInfo> userAppInfos; //用户应用程序的信息

    private AppMangerAdapter adapter;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            adapter = new AppMangerAdapter();
            lvAppmanger.setAdapter(adapter);
            llLoading.setVisibility(View.GONE);
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appmanager);
        tvAvailRom = (TextView) findViewById(R.id.tv_avail_rom);
        tvAvailSdcard = (TextView) findViewById(R.id.tv_avail_sdcard);
        lvAppmanger = (ListView) findViewById(R.id.lv_appmanger);
        llLoading = (LinearLayout) findViewById(R.id.ll_loading);

        tvAvailRom.setText("内存可用：" + getAvailSpace(Environment.getDataDirectory().getAbsolutePath()));
        tvAvailSdcard.setText("sd卡可用：" + getAvailSpace(Environment.getExternalStorageDirectory().getAbsolutePath()));

        fillData();
    }

    /**
     *
     */
    private class AppMangerAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return userAppInfos.size() + systemAppInfos.size();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            AppInfo appInfo;

            if (position == 0) {
                TextView tv = new TextView(AppManagerActivity.this);
                tv.setBackgroundColor(Color.GRAY);
                tv.setText("用户程序(" + userAppInfos.size()+")");
                tv.setTextColor(Color.WHITE);
                return tv;
            } else if (position == userAppInfos.size() + 1) {
                TextView tv = new TextView(AppManagerActivity.this);
                tv.setBackgroundColor(Color.GRAY);
                tv.setText("系统程序(" + systemAppInfos.size()+")");
                tv.setTextColor(Color.WHITE);
                return tv;
            } else if (position <= userAppInfos.size()) {
                //用户程序
                int newposition = position -1;
                appInfo = userAppInfos.get(newposition);
            } else {
                int newposition = position - userAppInfos.size() - 1 -1;
                appInfo = systemAppInfos.get(newposition);
            }

            View view;
            ViewHolder viewholder;
            if (convertView != null && convertView instanceof RelativeLayout) {
                view = convertView;
                viewholder = (ViewHolder) view.getTag();
            } else {
                view = View.inflate(AppManagerActivity.this, R.layout.activity_appmanager_item, null);
                viewholder = new ViewHolder();

                viewholder.tvAppName = (TextView) view.findViewById(R.id.tv_app_name);
                viewholder.tvAppLocation = (TextView) view.findViewById(R.id.tv_app_location);
                viewholder.ivIcon = (ImageView) view.findViewById(R.id.iv_app_icon);

                //把对应关系保存起来
                view.setTag(viewholder);
            }
//            //得到应用程序的信息
//            AppInfo appInfo;
//            if (position < userAppInfos.size()) {
//                //用户程序
//                appInfo = userAppInfos.get(position);
//            } else {
//                //系统程序
//                appInfo = systemAppInfos.get(position - userAppInfos.size());
//            }

            viewholder.tvAppName.setText(appInfo.getName());
            if (appInfo.isRom()) {
                //手机内部
                viewholder.tvAppLocation.setText("内部储存");
            } else {
                //外部存储
                viewholder.tvAppLocation.setText("外部存储");
            }
            viewholder.ivIcon.setImageDrawable(appInfo.getIcon());

            return view;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

    }

    static class ViewHolder {
        TextView tvAppName;
        TextView tvAppLocation;
        ImageView ivIcon;
    }

    /**
     * 加载数据
     */
    private void fillData() {
        llLoading.setVisibility(View.VISIBLE);
        new Thread() {
            @Override
            public void run() {
                appInfos = AppInfoProvider.getAllAppInfos(AppManagerActivity.this);

                //划分数据
                systemAppInfos = new ArrayList<>();
                userAppInfos = new ArrayList<>();

                for (AppInfo appInfo : appInfos) {
                    if (appInfo.isUser()) {
                        userAppInfos.add(appInfo);
                    }else {
                        systemAppInfos.add(appInfo);
                    }
                }

                handler.sendEmptyMessage(0);
            }
        }.start();
    }


    /**
     * 可到某个目录的可用空间
     * @param path 路径
     * @return
     */
    private String getAvailSpace(String path) {

        StatFs fs = new StatFs(path);
        //得到多少块可用空间
        long blocks = fs.getAvailableBlocksLong();
        //得到每块的可用大小
        long size = fs.getBlockSizeLong();

        Formatter.formatFileSize(this, blocks * size);

        return Formatter.formatFileSize(this, blocks * size);
    }
}
