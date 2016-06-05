package com.fangyi.mobilesafe.activity.atools;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.fangyi.mobilesafe.R;
import com.fangyi.mobilesafe.activity.SettingActivity;
import com.fangyi.mobilesafe.activity.atools.numaddressquery.NumberAddressQueryActivity;

/**
 * Created by FANGYI on 2016/6/5.
 */
public class AToolsActivity extends AppCompatActivity {
    private ListView listView;
    private static final  String names[] = {"号码归属地查询", "程序锁", "短信备份"};
    private static final  int ids[] = {R.drawable.ic_activity_atools_main_1, R.drawable.ic_activity_atools_main_2, R.drawable.ic_activity_atools_main_3};


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atools_main);

        listView = (ListView) findViewById(R.id.lv_atools_list_main);
        listView.setAdapter(new HomeAdapter());

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0://号码归属地查询
                        Intent numberAddressQueryIntent = new Intent(AToolsActivity.this, NumberAddressQueryActivity.class);
                        startActivity(numberAddressQueryIntent);
                        break;
                    case 1://进入程序锁
                        Intent settingIntent = new Intent(AToolsActivity.this, SettingActivity.class);
                        startActivity(settingIntent);
                        break;

                }
            }

        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
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
            View view = View.inflate(AToolsActivity.this, R.layout.activity_atools_main_item, null);
            ImageView ivAToolsIcon = (ImageView) view.findViewById(R.id.iv_atools_icon);
            TextView tvAToolsName = (TextView) view.findViewById(R.id.tv_atools_name);
            ivAToolsIcon.setImageResource(ids[position]);
            tvAToolsName.setText(names[position]);
            return view;
        }
    }
}
