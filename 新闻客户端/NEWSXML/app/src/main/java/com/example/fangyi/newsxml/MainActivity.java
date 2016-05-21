package com.example.fangyi.newsxml;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Xml;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.loopj.android.image.SmartImageView;

import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<News> newsList;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            ListView lv = (ListView) findViewById(R.id.lv);
            lv.setAdapter(new MyAdapter());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getNewsInfo();


    }

    /**
     * 适配器
     */
    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return newsList.size();
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
            View v;
            if (convertView == null) {
                v = View.inflate(MainActivity.this, R.layout.item_listview, null);

            } else {
                v = convertView;
            }

            TextView tv_title = (TextView) v.findViewById(R.id.tv_title);
            TextView tv_detail = (TextView) v.findViewById(R.id.tv_detail);
            TextView tv_comment = (TextView) v.findViewById(R.id.tv_comment);
            SmartImageView smartImageView = (SmartImageView) v.findViewById(R.id.siv);

            News news = newsList.get(position);

            tv_title.setText(news.getTitle());
            tv_detail.setText(news.getDetail());
            tv_comment.setText(news.getComment() + "条评论");
            smartImageView.setImageUrl(news.getImageUrl());


            return v;
        }
    }



    /**
     * 获取信息
     */
    private void getNewsInfo() {
        Thread t = new Thread() {
            @Override
            public void run() {
                String path = "https://raw.githubusercontent.com/edifangyi/edifangyi.github.io/master/news.xml";

                try {
                    URL url = new URL(path);
                    //拿到链接对象
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    //初始化链接对象
                    conn.setRequestMethod("GET");
                    conn.setReadTimeout(5000);
                    conn.setConnectTimeout(5000);
                    //链接建立
                    if (conn.getResponseCode() == 200) {
                        InputStream is = conn.getInputStream();
                        parseXml(is);

                        //XML文件解析完毕，把newsList里的内容显示至ListView，子线程无法显示，发送消息通知主线程显示

                        handler.sendEmptyMessage(1);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();
    }

    /**
     * 解析XML文件
     * @param is
     */
    public void parseXml(InputStream is) {
        XmlPullParser xp = Xml.newPullParser();
        try {
            xp.setInput(is, "utf-8");
            News news = null;
            int type = xp.getEventType();
            while (type != XmlPullParser.END_DOCUMENT) {
                switch (type) {
                    case XmlPullParser.START_TAG:
                        if ("newslist".equals(xp.getName())) {
                            newsList = new ArrayList<>();
                        } else if ("news".equals(xp.getName())) {
                            news = new News();
                        } else if ("title".equals(xp.getName())) {
                            String title = xp.nextText();
                            news.setTitle(title);
                        } else if ("datail".equals(xp.getName())) {
                            String datail = xp.nextText();
                            news.setDetail(datail);
                        } else if ("comment".equals(xp.getName())) {
                            String comment = xp.nextText();
                            news.setComment(comment);
                        } else if ("image".equals(xp.getName())) {
                            String image = xp.nextText();
                            news.setImageUrl(image);
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if ("news".equals(xp.getName())) {
                            newsList.add(news);
                        }
                        break;
                }
                // 把指针移动到下一个节点
                type = xp.next();
            }

            for (News n : newsList) {
                System.out.println(n.toString());
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
