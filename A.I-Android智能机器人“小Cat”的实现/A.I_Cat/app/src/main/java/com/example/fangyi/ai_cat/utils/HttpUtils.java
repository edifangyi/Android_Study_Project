package com.example.fangyi.ai_cat.utils;

import com.example.fangyi.ai_cat.bean.ChatMessage;
import com.example.fangyi.ai_cat.bean.Result;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;

/**
 * Created by FANGYI on 2016/3/25.
 */
public class HttpUtils {
    private static final String URL = "http://www.tuling123.com/openapi/api";
    private static final String API_KEY = "fac686fd393f9f3131b3f6b4f807639c";

    /**
     * 发送一个消息，得到返回的消息
     * @param msg
     * @return
     */
    public static ChatMessage sendMessage(String msg) {
        ChatMessage chatMessage = new ChatMessage();

        String jsonRes = doGet(msg);//通过get请求拿到返回的数据
        Gson gson = new Gson();
        Result result = null;//将返回的数据转换为Result对象


        try {   //转换成功
            result = gson.fromJson(jsonRes, Result.class);
            chatMessage.setMsg((result.getText()));//聊天的消息
        } catch (JsonSyntaxException e) {
            //转换失败
            chatMessage.setMsg("服务器繁忙，请稍候再试");
        }
        chatMessage.setDate(new Date());
        chatMessage.setType(ChatMessage.Type.INCOMING);
        return chatMessage;
    }

    /**
     * 由用户传入一个消息
     * @param msg
     * @return
     */
    public static String doGet(String msg) {
        String result = "";
        String url = setParams(msg);
        ByteArrayOutputStream byteArrayOutputStream = null;
        InputStream inputStream = null;

        try {
            java.net.URL urlNet = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlNet.openConnection();
            connection.setReadTimeout(5*1000);//5秒
            connection.setConnectTimeout(5*1000);
            connection.setRequestMethod("GET");//请求方式

            inputStream = connection.getInputStream();//拿到服务器返回的流
            int len = -1;
            byte[] buf = new byte[128];//缓冲区，128个字节

            byteArrayOutputStream = new ByteArrayOutputStream();//把byte转换为String
            //对流进行读操作
            while ((len = inputStream.read(buf)) != -1) {
                byteArrayOutputStream.write(buf, 0, len);
            }
            byteArrayOutputStream.flush();//清除缓冲区
            result = new String(byteArrayOutputStream.toByteArray());//将本地流转换为字符串

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally { //释放所有的资源
            if (byteArrayOutputStream != null ) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }

    /**
     * 为 URL设置参数
     * @param msg
     * @return
     */
    public static String setParams(String msg) {
        String url = "";
        try {
            url = URL + "?key=" + API_KEY + "&info=" + URLEncoder.encode(msg,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return url;
    }

}
