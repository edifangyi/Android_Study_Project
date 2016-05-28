package com.fangyi.mobilesafe.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.fangyi.mobilesafe.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        assignViews();
    }

    private GridView listMain;
    private static final  String names[] = {"手机防盗", "通信卫士", "应用管理", "进程管理", "流量统计", "手机杀毒", "缓存管理", "高级功能", "设置中心"};
    private static final  int ids[] = {R.drawable.plane, R.drawable.pointer, R.drawable.rss, R.drawable.star, R.drawable.ticket, R.drawable.twitter, R.drawable.user, R.drawable.view, R.drawable.weather};
    private void assignViews() {
        listMain = (GridView) findViewById(R.id.list_main);
        //设置适配器

        listMain.setAdapter(new HomeAdapter());
    }

    private class HomeAdapter extends BaseAdapter {

        //返回长度
        @Override
        public int getCount() {
            return names.length;
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
            View view = View.inflate(MainActivity.this, R.layout.main_item, null);
            ImageView ivIcon = (ImageView) view.findViewById(R.id.iv_icon);
            TextView tvName = (TextView) view.findViewById(R.id.tv_name);
            ivIcon.setImageResource(ids[position]);
            tvName.setText(names[position]);
            return view;
        }
    }

}
