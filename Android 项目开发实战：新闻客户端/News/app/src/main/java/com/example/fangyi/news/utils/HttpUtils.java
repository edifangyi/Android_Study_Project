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
