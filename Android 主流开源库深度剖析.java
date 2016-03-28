HTTP 网络请求库框架的使用

/**
 

 */
HTTP 网络请求库框架的使用

Http 网络数据交互请求是极其重要并且使用很频繁的一个模块，也是最基本的网络 编程所需要学习的。
网络请求方式的效率和性能直接影响到APP的整体用户体验流畅性，所以学习并掌握一款好的Hptt请求框
架对我们的网络开发非常重要。Http 请求在移动网络编程中主要是 Get 和 Pots 请求接口数据的使用。


这里将会选择两款比较优秀的开源pHtt网络请求框架：Volley 和 Android-async-http (Async)



Volley 特别适合数据量不大但是通信频繁的场景

Volley 特点:
	·能使网络通信更快，更简单，更健壮
	·Get、Post 网络请求及网络图像的高效率异步处理请求
	·可以对网络请求进行排序优先级管理 
	·网络请求的缓存 
	·多级别取消请求
	·和 Activity 生命周期的联动(Activity 结束时同时取消所有网络请求)

Volley 可以简化一些网络通信的开发，当然 Volley 不适合大数据 (large payload) 和流媒体的网络请求。
例如上百兆的文件、视频下载。



Android-async-http 是一款国外的开源框架。搞笑的网络数据请求，文件下载和上传。

Async 特点:
	·清晰的网络请求回调
	·请求使用 ThreadPool ，限制并发资源使用情况
	·GET/POST 基于参数构建使用 (RequeParams)，方便
	·Multipart 文件上传，大数据上传下载
	·自动智能请求重试，优化了质量不一的移动连接
	·内置响应解析成 JSON，使用 JsonHttpResponseHandler
	·持久化 cookie 存储，保证 cookie 到你的应用程序的 SharedPreferences
	·二进制文件 (图片等) 的下载，使用 BinaryHttpResponseHandler

/**
 

 */

Volley 的用法

本课时主要介绍谷歌开源库 Volley 的 Get 和 Post 请求的基本用法及拓展功能。

Get 和 Post 用于与服务器间请求和发送数据

	建立请求首先建立队列，将请求添加到请求队列里
	然后进行相应的 Get 和 Post 请求，请求结果回调里获取解析



	使用聚合API接口进行


	https://www.juhe.cn


注:
	添加
    <uses-permission android:name="android.permission.INTERNET"></uses-permission>
    
    和
    
    <application    
        android:name=".MyApplication"




1.
	先建立 请求队列 MyApplication.class

package com.example.fangyi.volleydemo;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by FANGYI on 2016/3/28.
 */
public class MyApplication extends Application {

    //Volley请求队列
    public static RequestQueue queue;

    @Override
    public void onCreate() {
        super.onCreate();
        //实例化，这里使用全局的上下文
        queue = Volley.newRequestQueue(getApplicationContext());
    }

    /**
     * 暴露一个方法，来返回全局的请求队列
     * 方便再添加请求的时候进行调用
     * @return
     */
    public static RequestQueue getHttpQueue() {
    return queue;
    }
}


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

2.
	MyApplication.class

package com.example.fangyi.volleydemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        volley_GET();
        volley_Post();
    }

    /**
     * GET方式 基本请求流程
     */
    private void volley_GET() {
        //聚合天气预报API
        String url = "http://v.juhe.cn/weather/index?format=2&cityname=%E5%8C%97%E4%BA%AC&key=be1282ff3497bac21d72ab2b724149c8";
        //建立请求队列
        //四个参数：请求方式,url,请求的回调,请求错误或者失败回调的方法
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(MainActivity.this, "网络请求失败", Toast.LENGTH_LONG).show();
            }
        });
        request.setTag("abcGet");//当想销毁这个请求，可以通过这个标签进行取消
        MyApplication.getHttpQueue().add(request);//将请求添加到队列里面
    }

    /**
     * Post方式 基本请求流程
     */
    private void volley_Post() {
        String url = "http://v.juhe.cn/weather/index?";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(MainActivity.this, "网络请求失败", Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("cityname", "%E5%8C%97%E4%BA%AC");
                map.put("key","be1282ff3497bac21d72ab2b724149c8");
                return map;
            }
        };
        request.setTag("abcPost");
        MyApplication.getHttpQueue().add(request);
    }

    /**
     * 和 Activity 生命周期的联动
     * 当 Activity 销毁关闭时候，销毁后台网络请求
     */
    @Override
    protected void onStop() {
        super.onStop();
        MyApplication.getHttpQueue().cancelAll("abcGet");
        MyApplication.getHttpQueue().cancelAll("abcPost");

    }
}


/**
 

 */


Android-async-http 的用法

