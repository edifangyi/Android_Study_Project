
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


2.JSON解析

解析JSON数据
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




////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


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

/**
 * Created by FANGYI on 2016/4/12.
 */
public class NewsAdapter extends BaseAdapter {

    private Context context;
    public NewsAdapter(Context context) {
        this.context = context;
    }


    /**
     * How many items are in the data set represented by this Adapter.
     *
     * @return Count of items.
     */
    @Override
    public int getCount() {
        return 0;
    }

    /**
     * Get the data item associated with the specified position in the data set.
     *
     * @param position Position of the item whose data we want within the adapter's
     *                 data set.
     * @return The data at the specified position.
     */
    @Override
    public Object getItem(int position) {
        return null;
    }

    /**
     * Get the row id associated with the specified position in the list.
     *
     * @param position The position of the item within the adapter's data set whose row id we want.
     * @return The id of the item at the specified position.
     */
    @Override
    public long getItemId(int position) {
        return 0;
    }

    /**
     * Get a View that displays the data at the specified position in the data set. You can either
     * create a View manually or inflate it from an XML layout file. When the View is inflated, the
     * parent View (GridView, ListView...) will apply default layout parameters unless you use
     * {@link LayoutInflater#inflate(int, ViewGroup, boolean)}
     * to specify a root view and to prevent attachment to the root.
     *
     * @param position    The position of the item within the adapter's data set of the item whose view
     *                    we want.
     * @param convertView The old view to reuse, if possible. Note: You should check that this view
     *                    is non-null and of an appropriate type before using. If it is not possible to convert
     *                    this view to display the correct data, this method can create a new view.
     *                    Heterogeneous lists can specify their number of view types, so that this View is
     *                    always of the right type (see {@link #getViewTypeCount()} and
     *                    {@link #getItemViewType(int)}).
     * @param parent      The parent that this view will eventually be attached to
     * @return A View corresponding to the data at the specified position.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.news_item, null);

        }
        TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
        TextView tbDesc = (TextView) convertView.findViewById(R.id.tvDesc);
        TextView tvTime = (TextView) convertView.findViewById(R.id.tvTime);
        ImageView ivPic = (ImageView) convertView.findViewById(R.id.ivPic);


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

import android.os.Handler;
import android.os.Message;

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
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////






/**
 

 */

















/**
 

 */































