package com.fangyi.mobilesafe.activity.callsmssafe;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.fangyi.mobilesafe.R;
import com.fangyi.mobilesafe.db.dao.BlackNumberDao;
import com.fangyi.mobilesafe.domain.BlackNumberInfo;

import java.util.List;

/**
 * Created by FANGYI on 2016/6/10.
 */
public class SmsSecurityBlackListActivity extends AppCompatActivity {
    private ListView lvSmsSecurityBlacklistList;
    private LinearLayout llDoading;
    private BlackNumberDao dao;
    //所有的黑名单号码
    private List<BlackNumberInfo> infos;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            lvSmsSecurityBlacklistList.setAdapter(new SmsSBLAdapter());
            Log.e("4.数据加载完成","隐藏");
            //数据加载完成，隐藏
            llDoading.setVisibility(View.INVISIBLE);
        }
    };



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_security_black_list);

        lvSmsSecurityBlacklistList = (ListView) findViewById(R.id.lv_sms_security_blacklist_list);
        llDoading = (LinearLayout) findViewById(R.id.ll_loading);
        fillData();


    }

    private class SmsSBLAdapter extends BaseAdapter {

        //得到黑名单的总数
        @Override
        public int getCount() {
            return infos.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }
        //每显示一行就执行一次
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View view;
            ViewHolder holder;

            if (convertView != null) {
                view = convertView;
                System.out.println("使用历史缓存的 View==" + position);
                holder = (ViewHolder) view.getTag();
            } else {
                view = View.inflate(SmsSecurityBlackListActivity.this, R.layout.activity_sms_security_black_list_item, null);
                System.out.println("创建新的 View==" + position);
                //当这个View对象呗创建的时候，就把id给查找了，并且放在容器（类）
                holder = new ViewHolder();
                holder.tvNumber = (TextView) view.findViewById(R.id.tv_black_number);
                holder.tvMode = (TextView) view.findViewById(R.id.tv_black_mode);

                //view对象和容器进行关联，保存View对象的层次结构
                view.setTag(holder);
            }


            //得到黑名单数据 - 根据位置去得到
            BlackNumberInfo info = infos.get(position);
            holder.tvNumber.setText(info.getNumber());
            String mode = info.getMode();
            if ("0".equals(mode)) {
                //电话拦截
                holder.tvMode.setText("电话拦截");
            } else if ("1".equals(mode)) {
                //短信拦截
                holder.tvMode.setText("短信拦截");
            } else {
                //全部拦截
                holder.tvMode.setText("短信 + 电话拦截");
            }
            return view;
        }
    }

    /**
     * 容器
     */
    static class ViewHolder {
        TextView tvNumber;
        TextView tvMode;
    }


    /**
     * 加载数据
     */
    private void fillData() {
        dao = new BlackNumberDao(this);
        //数据加载前，显示
        Log.e("1.数据加载前","显示");
        llDoading.setVisibility(View.VISIBLE);
        new Thread() {
            @Override
            public void run() {
                Log.e("2.执行数据加载","隐藏");
                infos = dao.queryAll();//联网的操作或者耗时的操作
                handler.sendEmptyMessage(0);
            }
        }.start();
    }
 }
