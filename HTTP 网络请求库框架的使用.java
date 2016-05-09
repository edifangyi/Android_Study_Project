HTTP 网络请求库框架的使用

/**
 

 */
HTTP 网络请求库框架的使用

Http 网络数据交互请求是极其重要并且使用很频繁的一个模块，也是最基本的网络 编程所需要学习的。
网络请求方式的效率和性能直接影响到APP的整体用户体验流畅性，所以学习并掌握一款好的Hptt请求框
架对我们的网络开发非常重要。Http 请求在移动网络编程中主要是 Get 和 Pots 请求接口数据的使用。


这里将会选择两款比较优秀的开源pHtt网络请求框架：Volley 和 Android-async-http (Async)



Volley 特别适合数据量不大但是通信频繁的场景

    Volley.jar

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

    android-async-http-1.4.3.jar

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
	//在项目清单里，添加网络权限
    <uses-permission android:name="android.permission.INTERNET"></uses-permission>
    
    和
    //给MyApplication注册
    <application    
        android:name=".MyApplication"




1.
	先建立 全局 请求队列 MyApplication.class

package com.example.fangyi.volleydemo;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by FANGYI on 2016/3/28.
 */
public class MyApplication extends Application {

    //建立Volley请求队列
    public static RequestQueue queue;

    @Override
    public void onCreate() {
        super.onCreate();
        //实例化，这里使用全局的上下文
        queue = Volley.newRequestQueue(getApplicationContext());
    }

    /**
     * 暴露一个方法，来返回全局的请求队列
     * 
     * 用于获取全局的请求队列
     * 方便再添加请求的时候进行调用
     * @return
     */
    public static RequestQueue getHttpQueue() {
    return queue;
    }
}


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
2.
	MyApplication.class

package com.example.fangyi.volleydemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EditText minput;
    private ListView mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }


    /**
     * 布局初始化
     */
    private  void initView() {

        mDatas = (ListView) findViewById(R.id.id_listView);
        minput = (EditText) findViewById(R.id.id_input);


        findViewById(R.id.btn_Volley_Get).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volley_GET();
            }
        });

        findViewById(R.id.btn_Volley_Post).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volley_Post();
            }
        });

        findViewById(R.id.btn_Async_GET).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                asynchttp_Get();
            }
        });

        findViewById(R.id.btn_Async_POST).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                asynchttp_Post();
            }
        });

    }

    /**
     * GET方式 基本请求流程
     */
    private void volley_GET() {
        //聚合天气预报API

        if (minput.getText().toString() != null) {
            String url = "http://apis.juhe.cn/mobile/get?phone="+ minput.getText().toString() + "&key=6f2167fca88470b1cf1a9c60a7f85f56";
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
            request.setTag("abcGet");//当想取消这个请求，可以通过这个标签进行取消
            MyApplication.getHttpQueue().add(request);//将请求添加到队列里面
        }else {
            Toast.makeText(MainActivity.this, "请输入正确手机号", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Post方式 基本请求流程
     */
    private void volley_Post() {
        String url = "http://apis.juhe.cn/mobile/get?";
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
                map.put("phone", minput.getText().toString());
                map.put("key","6f2167fca88470b1cf1a9c60a7f85f56");
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
/**

 */
Android-async-http 的用法



Get 和 Post 用于与服务器间请求和发送数据

	建立请求首先建立请求客户端对象
	然后进行相应的 Get 和 Post 请求，请求结果回调里获取解析
	Android-async-http 的 Get 和 Post 请求可以基于参数化请求 (RequeParams) ，
	可以清晰方便的将你的请求参数放到里面，然后进行GEt 和 Post 请求

Async-http 有自己的默认的回调逻辑封装，例如请求成功，请求失败，重试请求等回调
	
	但是为了整个APP的网络模块的封装和请求管理，要进行一个二次的定制化回调封装，这样可以全面控制和管理定制我们的网络请求整个过程


    /**
     * Async-http_get方法
     */
    private void asynchttp_Get() {
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "http://apis.juhe.cn/mobile/get?phone="+ minput.getText().toString() + "&key=6f2167fca88470b1cf1a9c60a7f85f56";

        //请求地址，请求回调
        client.get(url, new AsyncHttpResponseHandler() {
            //请求成功
            @Override
            public void onSuccess(String s) {
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
            }
            //请求失败
            @Override
            public void onFailure(Throwable throwable) {
                Toast.makeText(MainActivity.this, "网络请求失败", Toast.LENGTH_SHORT).show();

                super.onFailure(throwable);
            }
        });
    }

    /**
     * Async-http_post方法
     */
    private void asynchttp_Post() {w
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "http://apis.juhe.cn/mobile/get?";
        RequestParams params = new RequestParams();
        params.put("phone", minput.getText().toString());
        params.put("key","6f2167fca88470b1cf1a9c60a7f85f56");


        //使用定制回调
        RequestUtils.ClientPost(url, params, new NetCallBack() {
            
            //请求成功时，此方法调用
            @Override
            public void onMySuccess(String s) {
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
            }
            //请求失败时，此方法调用
            @Override
            public void onMyFailure(Throwable throwable) {
                Toast.makeText(MainActivity.this, "网络请求失败", Toast.LENGTH_SHORT).show();
            }
        });

        //post方式
        //
//        client.post(url, params, new AsyncHttpResponseHandler() {
//            @Override
//            public void onSuccess(String s) {
//                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
//                super.onSuccess(s);
//            }
//
//            @Override
//            public void onFailure(Throwable throwable) {
//                Toast.makeText(MainActivity.this, "网络请求失败", Toast.LENGTH_SHORT).show();
//                super.onFailure(throwable);
//            }
//        });
    }


1.定制的请求回调

package com.example.fangyi.volleydemo;

import android.util.Log;

import com.loopj.android.http.AsyncHttpResponseHandler;

/**
 * Created by FANGYI on 2016/3/31.
 */
public abstract class NetCallBack extends AsyncHttpResponseHandler {

    @Override
    public void onStart() {
        Log.i("info", "请求开始，弹出进度条框");
        super.onStart();
    }

    @Override
    public void onSuccess(String s) {
        Log.i("info", "请求成功，隐藏进度条框" + s);
        onMySuccess(s);
        super.onSuccess(s);
    }

    @Override
    public void onFailure(Throwable throwable) {
        Log.i("info", "请求失败，隐藏进度条框" + throwable);
        onMyFailure(throwable);
        super.onFailure(throwable);
    }

    public abstract void onMySuccess(String s);
    public abstract void onMyFailure(Throwable throwable);
}





/**
 

 */
/**
 

 */
/**
 

 */



//全部代码




<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.example.fangyi.volleydemo">
    <uses-permission android:name="android.permission.INTERNET"></uses-permission>
    <application
        android:name=".MyApplication"
        android:allowBackup="true"
        android:icon="@drawable/phone_grey"
        android:label="@string/app_name"
        android:supportsRtl="true"
        android:theme="@style/AppTheme"
>
        <activity android:name=".MainActivity">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>

</manifest>


 

/**
 
 */

<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:paddingBottom="@dimen/activity_vertical_margin"
    android:paddingLeft="@dimen/activity_horizontal_margin"
    android:paddingRight="@dimen/activity_horizontal_margin"
    android:paddingTop="@dimen/activity_vertical_margin"
    tools:context="com.example.fangyi.volleydemo.MainActivity"
    android:orientation="vertical"
    android:weightSum="1">


    <EditText
        android:id="@+id/id_input"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="请输入..."/>

    <Button
        android:id="@+id/btn_Volley_Get"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:textAllCaps="false"
        android:text="Volley_GET"/>

    <Button
        android:id="@+id/btn_Volley_Post"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:textAllCaps="false"
        android:text="Volley_POST"/>

    <Button
        android:id="@+id/btn_Async_GET"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:textAllCaps="false"
        android:text="Async_GET"/>

    <Button
        android:id="@+id/btn_Async_POST"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:textAllCaps="false"
        android:text="Async_POST"/>

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="horizontal">

        <TextView
            android:layout_width="50dp"
            android:layout_height="wrap_content"
            android:gravity="center"
            android:text="省份"/>
        <TextView
            android:layout_width="50dp"
            android:layout_height="wrap_content"
            android:gravity="center"
            android:text="城市" />
        <TextView
            android:layout_width="50dp"
            android:layout_height="wrap_content"
            android:gravity="center"
            android:text="区号" />
        <TextView
            android:layout_width="50dp"
            android:layout_height="wrap_content"
            android:gravity="center"
            android:text="邮编" />
        <TextView
            android:layout_width="60dp"
            android:layout_height="wrap_content"
            android:gravity="center"
            android:text="运营商" />
        <TextView
            android:layout_width="60dp"
            android:layout_height="wrap_content"
            android:gravity="center"
            android:text="卡类型" />

    </LinearLayout>
    
    <LinearLayout

        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="horizontal">

        <TextView
            android:id="@+id/id_province"
            android:layout_width="50dp"
            android:layout_height="wrap_content"
            android:gravity="center"
            android:text="省份"/>
        <TextView
            android:id="@+id/id_city"
            android:layout_width="50dp"
            android:layout_height="wrap_content"
            android:gravity="center"
            android:text="城市" />
        <TextView
            android:id="@+id/id_areacode"
            android:layout_width="50dp"
            android:layout_height="wrap_content"
            android:gravity="center"
            android:text="区号" />
        <TextView
            android:id="@+id/id_zip"
            android:layout_width="50dp"
            android:layout_height="wrap_content"
            android:gravity="center"
            android:text="邮编" />
        <TextView
            android:id="@+id/id_company"
            android:layout_width="60dp"
            android:layout_height="wrap_content"
            android:gravity="center"
            android:text="运营商" />
        <TextView
            android:id="@+id/id_card"
            android:layout_width="60dp"
            android:layout_height="wrap_content"
            android:gravity="center"
            android:text="卡类型" />

    </LinearLayout>


    <ListView
        android:id="@+id/id_listView"
        android:layout_width="fill_parent"
        android:layout_height="wrap_content">

    </ListView>

</LinearLayout>


/**
 
 */


package com.example.fangyi.volleydemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EditText minput;
    private ListView mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }


    /**
     * 布局初始化
     */
    private  void initView() {

        mDatas = (ListView) findViewById(R.id.id_listView);
        minput = (EditText) findViewById(R.id.id_input);


        findViewById(R.id.btn_Volley_Get).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volley_GET();
            }
        });

        findViewById(R.id.btn_Volley_Post).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volley_Post();
            }
        });

        findViewById(R.id.btn_Async_GET).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                asynchttp_Get();
            }
        });

        findViewById(R.id.btn_Async_POST).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                asynchttp_Post();
            }
        });

    }

    /**
     * GET方式 基本请求流程
     */
    private void volley_GET() {
        //聚合天气预报API

        if (minput.getText().toString() != null) {
            String url = "http://apis.juhe.cn/mobile/get?phone="+ minput.getText().toString() + "&key=6f2167fca88470b1cf1a9c60a7f85f56";
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
            request.setTag("abcGet");//当想取消这个请求，可以通过这个标签进行取消
            MyApplication.getHttpQueue().add(request);//将请求添加到队列里面
        }else {
            Toast.makeText(MainActivity.this, "请输入正确手机号", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Post方式 基本请求流程
     */
    private void volley_Post() {
        String url = "http://apis.juhe.cn/mobile/get?";
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
                map.put("phone", minput.getText().toString());
                map.put("key","6f2167fca88470b1cf1a9c60a7f85f56");
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

    /**
     * Async-http_get方法
     */
    private void asynchttp_Get() {
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "http://apis.juhe.cn/mobile/get?phone="+ minput.getText().toString() + "&key=6f2167fca88470b1cf1a9c60a7f85f56";

        //请求地址，请求回调
        client.get(url, new AsyncHttpResponseHandler() {
            //请求成功
            @Override
            public void onSuccess(String s) {
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
            }
            //请求失败
            @Override
            public void onFailure(Throwable throwable) {
                Toast.makeText(MainActivity.this, "网络请求失败", Toast.LENGTH_SHORT).show();

                super.onFailure(throwable);
            }
        });
    }

    /**
     * Async-http_post方法
     */
    private void asynchttp_Post() {
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "http://apis.juhe.cn/mobile/get?";
        RequestParams params = new RequestParams();
        params.put("phone", minput.getText().toString());
        params.put("key","6f2167fca88470b1cf1a9c60a7f85f56");
        RequestUtils.ClientPost(url, params, new NetCallBack() {
            @Override
            public void onMySuccess(String s) {
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onMyFailure(Throwable throwable) {
                Toast.makeText(MainActivity.this, "网络请求失败", Toast.LENGTH_SHORT).show();
            }
        });


//        client.post(url, params, new AsyncHttpResponseHandler() {
//            @Override
//            public void onSuccess(String s) {
//                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
//                super.onSuccess(s);
//            }
//
//            @Override
//            public void onFailure(Throwable throwable) {
//                Toast.makeText(MainActivity.this, "网络请求失败", Toast.LENGTH_SHORT).show();
//                super.onFailure(throwable);
//            }
//        });
    }

}


/**
 
 */


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



/**
 
 */
package com.example.fangyi.volleydemo;

import android.util.Log;

import com.loopj.android.http.AsyncHttpResponseHandler;

/**
 * Created by FANGYI on 2016/3/31.
 */
public abstract class NetCallBack extends AsyncHttpResponseHandler {

    @Override
    public void onStart() {
        Log.i("info", "请求开始，弹出进度条框");
        super.onStart();
    }

    @Override
    public void onSuccess(String s) {
        Log.i("info", "请求成功，隐藏进度条框" + s);
        onMySuccess(s);
        super.onSuccess(s);
    }

    @Override
    public void onFailure(Throwable throwable) {
        Log.i("info", "请求失败，隐藏进度条框" + throwable);
        onMyFailure(throwable);
        super.onFailure(throwable);
    }

    public abstract void onMySuccess(String s);
    public abstract void onMyFailure(Throwable throwable);
}
