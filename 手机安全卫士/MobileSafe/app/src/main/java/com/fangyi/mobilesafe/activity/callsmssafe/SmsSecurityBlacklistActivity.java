package com.fangyi.mobilesafe.activity.callsmssafe;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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
    private BlackNumberDao dao;
    //所有的黑名单号码
    private List<BlackNumberInfo> infos;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_security_black_list);

        lvSmsSecurityBlacklistList = (ListView) findViewById(R.id.lv_sms_security_blacklist_list);

        fillData();
        lvSmsSecurityBlacklistList.setAdapter(new SmsSecurityBlackListAdapter());

    }

    private class  SmsSecurityBlackListAdapter extends BaseAdapter {

        //得到黑名单的总数
        @Override
        public int getCount() {
            return infos.size();
        }

        @Override
        public Object getItem(int position) {
            TextView tv = new TextView(SmsSecurityBlackListActivity.this);
            tv.setTextSize(18);
            tv.setText(infos.get(position).toString());
            return tv;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return null;
        }
    }

    /**
     * 加载数据
     */
    private void fillData() {
        dao = new BlackNumberDao(this);
        infos = dao.queryAll();
    }
 }
