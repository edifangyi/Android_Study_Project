package com.fangyi.mobilesafe.activity.callsmssafe;

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
import android.widget.Toast;

import com.fangyi.mobilesafe.R;
import com.fangyi.mobilesafe.activity.atools.AToolsActivity;


/**
 * Created by FANGYI on 2016/6/21.
 */

public class CallSmsSafeActivity extends AppCompatActivity {
    private ListView listView;
    private static final String names[] = {"常用号码查询", "黑名单管理", "位置3"};
    private static final int ids[] = {R.drawable.ic_activity_atools_main_1, R.drawable.ic_activity_atools_main_2, R.drawable.ic_activity_atools_main_3};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_callsmssafe_main);

        listView = (ListView) findViewById(R.id.lv_atools_list_main);
        listView.setAdapter(new CallSmsSafeActivity.HomeAdapter());

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent;
                switch (position) {
                    case 0://常用号码查询
                        intent = new Intent(CallSmsSafeActivity.this, AToolsActivity.class);
                        startActivity(intent);
                        break;
                    case 1://黑名单管理
                        intent = new Intent(CallSmsSafeActivity.this, SmsSecurityBlackListActivity.class);
                        startActivity(intent);
                        break;
                    case 2://进入
                        Toast.makeText(CallSmsSafeActivity.this, "没有设计", Toast.LENGTH_SHORT).show();
                        break;

                }
            }

        });
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
            View view = View.inflate(CallSmsSafeActivity.this, R.layout.activity_atools_main_item, null);
            ImageView ivAToolsIcon = (ImageView) view.findViewById(R.id.iv_atools_icon);
            TextView tvAToolsName = (TextView) view.findViewById(R.id.tv_atools_name);
            ivAToolsIcon.setImageResource(ids[position]);
            tvAToolsName.setText(names[position]);
            return view;
        }
    }
}
