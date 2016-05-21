
使用 PHP 生成新闻 JSON 数据 

本课时介绍在 MySQL 中新建数据库和相应的表、字段，添加初始数据，在 PHP 中连接数据库，查询对应的新闻项并生成 JSON 数据，实现供外部程序访问的功能。


http://127.0.0.1:8080/phpmyadmin/index.php?token=026b556cdb5efd03f6a4e49118972355

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

1.新建数据库 
	
	→ 数据库名:newsdemo, 排序规则:utf8_general_ci 

	→ 新建数据表

		名称:news ，字段数:6 

		→ 	id 		/*ID*/			类型:INT 	长度/值:10		索引:PRIMARY  A_I:√
			title 	/*标题*/		 	 TEXT 			200
			desc	/*概述*/			 TEXT			200
			time 	/*时间*/			 TIMESTAMP				默认值:CURRENT_TIME//插入一条新纪录的当前时间
			content_url /*新闻链接*/	 TEXT			200
			pic_url		/*新闻缩略图链接*/TEXT			200

												Collation:utf8_general_ci


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

2.选择:插入
	
	参考 MySQL 图片

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
3.创建

	D:\xampp\htdocs\NewsDemo



	1.mysql_connect.php


		<?php

			/**
			 * 连接数据库
			 */
			  
			$con = mysql_connect("127.0.0.1:3366", "root", "root");//服务器地址， 服务器用户名， 密码
			//设置字符集utf8
			mysql_query("SET NAMES 'utf8'");
			mysql_query("SET CHARACTER SET 'utf8'");
			mysql_query("SET CHARACTER_SET_RESULT = utf8");

			if (!$con) {
				die(mysql_error());
			}
			mysql_select_db("newsdemo", $con);

		?>





	2.getNewsJSON.php


		<?php

		/*
		 * 获得JSON数据
		 * 返回值：title desc time content_url pic_url
		 */
		 
		 require 'mysql_connect.php';
		 
		 $n = 0;
		 $result = mysql_query("select * from news");
		 while ($row = mysql_fetch_array($result)){
		 	$arr[$n++] = array("title" => $row['title'],
		 						"desc" => $row['desc'],
		 						"time" => $row['time'],
		 						"content_url" => $row['content_url'],
		 						"pic_url" => $row['pic_url']
							);
		 }
		 
		 //数组转换为JSON字符串
		 echo json_encode($arr);

		?>

/**
 

 */
1.Adapter


public class NewsAdapter extends BaseAdapter{
	//控制该Adapter包含列表项的个数
	public int getCount(){}
	 //决定第position处的列表项内容	
	public Object getItem(int position){} 
	//决定第position处的列表项ID
	public long getItemId(int position){}
	//决定第position处的列表项组件、即某一项里面的布局
	public View getView(int position, View convertView, 
						ViewGroup parent){}
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

1、JSON数组格式
[
     {“title”:“xxx”,“desc”:“xxx”,“time”:“xxx”,“content_url”:“xxx”,“pic_url”:“xxx.jpg”},
     {“title”:“yyy”,“desc”:“yyy”,“time”:“yyy”,“content_url”:“yyy”,“pic_url”:“yyy.jpg”}
]


2、解析JSON数据
	JSONArray jsonArray = new JSONArray(jsonData);
	for (int i=0;i<jsonArray.length();i++){
 		JSONObject object = jsonArray.getJSONObject(i);		
		String title = object.getString("title");
		String desc = object.getString("desc"); 
		……
	}



/**
 

 */

实现新闻列表界面的布局

本课时讲解在 XML 中添加 ListView，使用 BaseAdapter 作为其适配器，完成新闻列表项布局的创建。



1.activity_main.xml 主布局


<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="com.example.fangyi.news.MainActivity"
    android:orientation="vertical">


    <ListView
        android:id="@+id/lvNews"
        android:layout_width="match_parent"
        android:layout_height="match_parent">
    </ListView>

</LinearLayout>


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

2.news_item.xml   Adapter 布局文件


<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical" android:layout_width="match_parent"
    android:layout_height="match_parent">

    <ImageView
        android:id="@+id/ivPic"
        android:layout_width="42dp"
        android:layout_height="42dp"
        android:src="@mipmap/ic_launcher"/>

    <TextView
        android:id="@+id/tvTitle"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_toRightOf="@+id/ivPic"	//在 ivPic 右边
        android:textSize="18sp"
        android:text="tilte"/>

    <TextView
        android:id="@+id/tvDesc"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_below="@+id/tvTitle"		//在 tvTitle 下面
        android:layout_toRightOf="@+id/ivPic"	//在 ivPic 右边
        android:text="desc"/>

    <TextView
        android:id="@+id/tvTime"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_alignParentRight="true"	//在右上角
        android:textSize="10sp"
        android:text="time"/>

</RelativeLayout>



/**
 
 */

3. adapter/NewsAdapter.class  //adapter 文件
package com.example.fangyi.news.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fangyi.news.R;
import com.example.fangyi.news.model.News;
import com.example.fangyi.news.utils.HttpUtils;

import java.util.List;


public class NewsAdapter extends BaseAdapter {

    private Context context;
    private List<News> newsList;

    public NewsAdapter(Context context, List<News> newsList){
        this.context = context;
        this.newsList = newsList;
    }

    @Override
    public int getCount() {
        return newsList.size();
    }

    @Override
    public News getItem(int position) {
        return newsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.news_item, null);
        }
        TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
        TextView tvDesc = (TextView) convertView.findViewById(R.id.tvDesc);
        TextView tvTime = (TextView) convertView.findViewById(R.id.tvTime);
        ImageView ivPic = (ImageView) convertView.findViewById(R.id.ivPic);

        News news = newsList.get(position);
        tvTitle.setText(news.getTitle());
        tvDesc.setText(news.getDesc());
        tvTime.setText(news.getTime());

        String pic_url = news.getPic_url();
        HttpUtils.setPicBitmap(ivPic, pic_url);

        return convertView;
    }

}


/**
 

 */

实现新闻列表界面的 JSON 数据解析和填充

本课时讲解编写网络工具类实现网络资源的获取，并将获取到的 JSON 数据解析，显示在 ListView 上面。



1. 因为在本地搭建的服务器，所以先找到本机 IP 地址

	在 cmd 中输入 ipconfig -all 
	
	无线局域网适配器 WLAN:
 		IPv4 地址 . . . . . . . . . . . . : 10.9.1.167(首选)

	    public static final String GET_NEWS_URL = "http://  IP地址  /NewsDemo/getNewsJSON.php";	//外网可以访问了


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


2.	utlis/HttpUtils.class 	//工具类，填充



package com.example.fangyi.news.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

;

/**
 * Created by FANGYI on 2016/4/12.
 */
public class HttpUtils {

    public  static void getNewsJSON(final String url, final Handler handler) {

        //因为访问网络，开启线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection conn;
                InputStream is;

                try {

                    /**
                     *  获取JSON数据
                     */
                    conn = (HttpURLConnection) new URL(url).openConnection();
                    conn.setRequestMethod("GET");
                    is = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is));

                    //一行一行的读取数据
                    String line = "";
                    StringBuilder result = new StringBuilder();   //StringBuilder在String 拼接上比 单纯的加 高效很多
                    while ((line = reader.readLine()) != null) {
                        result.append(line);//line添加到result末尾
                    }


                    /**
                     * 获取后，通知主线程，用Handler
                     */
                    Message msg = new Message();
                    msg.obj = result.toString();    //obj属性可以放任意类型对象
                    handler.sendMessage(msg);   //通知主线程，msg已经接收到，接下来有handler处理，回调方法在 MainActivity 的 getNewsJSON 中


                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    /**
     * 获取图片
     *
     * @param ivPic
     * @param pic_url
     */
    public static void setPicBitmap(final ImageView ivPic, final String pic_url ) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    HttpURLConnection connection = (HttpURLConnection) new URL(pic_url).openConnection();
                    connection.connect();
                    InputStream is = connection.getInputStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(is);
                    ivPic.setImageBitmap(bitmap);
                    is.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

3. model/News.class //方便数据管理

package com.example.fangyi.news.model;

/**
 * Created by FANGYI on 2016/4/12.
 * 组织数据，每条新闻 对应 五个元素
 *          另一条新闻 也 对应 五个元素
 */
public class News {
    private String title;
    private String desc;
    private String time;
    private String content_url;
    private String pic_url;

    public News(String title, String desc, String time, String content_url, String pic_url) {

        setTitle(title);
        setDesc(desc);
        setTime(time);
        setContent_url(content_url);
        setPic_url(pic_url);

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent_url() {
        return content_url;
    }

    public void setContent_url(String content_url) {
        this.content_url = content_url;
    }

    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }
}



/**
 

 */

实现新闻列表界面的跳转并展示详情 06:35

本课时讲解为 ListView 添加点击事件，实现点击某条新闻后跳转到新闻详情浏览页的功能。




package com.example.fangyi.news;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * Created by FANGYI on 2016/4/12.
 */
public class BrowseNewsActivity extends Activity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivy_browse_news);

        webView = (WebView) findViewById(R.id.webView);
        String pic_url = getIntent().getStringExtra("content_url");
        webView.loadUrl(pic_url);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);//缓存
    }
}




/**
 

 */


主函数



package com.example.fangyi.news;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.fangyi.news.adapter.NewsAdapter;
import com.example.fangyi.news.model.News;
import com.example.fangyi.news.utils.HttpUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView lvNews;
    private NewsAdapter adapter;    //NewsAdapter.class
    private List<News> newsList;    //News.class

    public static final String GET_NEWS_URL = "http://127.0.0.1:8080/NewsDemo/getNewsJSON.php";


    /**
     * 当获取到 News JSON数据之后处理数据
     */
    private Handler getNewsHandler = new Handler() {
        // 即当在 HttpUtils.class 中的 handler 调用 .sendMessage(msg);
        // 时就会调用 getNewsHandler 中下面这个回调方法
        @Override
        public void handleMessage(Message msg) {
            String jsonData = (String) msg.obj;

            System.out.println(jsonData);

            //解析JSON数据
            try {
                JSONArray jsonArray = new JSONArray(jsonData);
                for (int i = 0; i<jsonArray.length(); i++) {
                    JSONObject object = jsonArray.getJSONObject(i);
                    String title = object.getString("title");
                    String desc = object.getString("desc");
                    String time = object.getString("time");
                    String content_url = object.getString("content_url");
                    String pic_url = object.getString("pic_url");

                    newsList.add(new News(title, desc, time, content_url, pic_url));
                }
                //通知Adapter更新
                adapter.notifyDataSetChanged();

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvNews = (ListView) findViewById(R.id.lvNews);
        newsList = new ArrayList<News>();
        adapter = new NewsAdapter(this, newsList);

        lvNews.setAdapter(adapter);
        lvNews.setOnItemClickListener(this);//点击事件

        HttpUtils.getNewsJSON(GET_NEWS_URL, getNewsHandler);
    }

    /**
     * 添加点击事件
     *
     * Callback method to be invoked when an item in this AdapterView has
     * been clicked.
     * <p/>
     * Implementers can call getItemAtPosition(position) if they need
     * to access the data associated with the selected item.
     *
     * @param parent   The AdapterView where the click happened.
     * @param view     The view within the AdapterView that was clicked (this
     *                 will be a view provided by the adapter)
     * @param position The position of the view in the adapter.
     * @param id       The row id of the item that was clicked.
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        News news = newsList.get(position);
        Intent intent = new Intent(this, BrowseNewsActivity.class);
        intent.putExtra("content_url", news.getContent_url());
        startActivity(intent);
    }
}

























