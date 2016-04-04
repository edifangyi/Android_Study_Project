Gson 的特点和优势
	Gson 是 Google 提供的用来在 Java 对象和 JSON 数据之间进行映射的 Java 类库。
	可以将一个 JSON 字符串转换一个 Java 对象，或者反过来。
    ·快速、高效
    ·代码量少、简洁
    ·面向对象
    ·数据传递和解析方便

Fast-json 的特点和优势
	Fast-json 是一个性能很好的Java语言实现的 JSON 解析器和生成器，来自阿里巴巴的工程师开发。
	具有极快的性能，超越其他的 JAva Json parser。
    ·快速FAST(比其他任何基于Java的解析器和生成器更快，包括 jackson)
    ·支持普通JDK类包括任意 Java Bean Class、Collection、Map、Cate、enum
    ·零依赖(没有依赖其他任何类库除了JDK)
    ·支持注解、支持全类型序列化


/**
 
 */

Gson 的基本用法

    ·定义实体类
    ·根据需要可以讲 JSON 生成单个实体或列表实体集合

代码演示:

	使用 Gson 解析 JsonObject
	使用 Gson 解析 JsonArray
	使用 Gson 解析 将实体转为 JSON 数据

/**
 
 */


package com.example.fangyi.jsondemo.GsonDemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.fangyi.jsondemo.R;
import com.example.fangyi.jsondemo.GsonDemo.bean.Book;
import com.google.gson.Gson;

public class GsonDemo extends AppCompatActivity {

    private String url = "https://api.douban.com/v2/book/1220562";
    private TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gson_demo);

        getData();
    }

    public void getData() {
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                dealData(s);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        new Volley().newRequestQueue(getApplicationContext()).add(request);
    }

    /**
     * 返回的JSON数据结构进行解析
     * @param result
     */
    private void dealData(String result) {
        Gson gson = new Gson();
        Book book = gson.fromJson(result, Book.class);
        
        book.getTitle();
        book.getPublisher();
        book.getSummary();
        book.getTags().size();
    }

}

/**
 
 */


package com.example.fangyi.jsondemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.fangyi.jsondemo.GsonDemo.GsonDemo;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        assignViews();
    }

    private Button btnGson;
    private Button btnFastjson;
    private Button idBtn;

    private void assignViews() {
        btnGson = (Button) findViewById(R.id.btn_Gson);
        btnFastjson = (Button) findViewById(R.id.btn_Fastjson);
        idBtn = (Button) findViewById(R.id.id_btn);

        btnGson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, GsonDemo.class));
            }
        });
    }

}


/**
 
 */


package com.example.fangyi.jsondemo.GsonDemo.bean;

import java.util.ArrayList;

/**
 * Created by FANGYI on 2016/4/4.
 */
public class Book {

    private String title;
    private String publisher;
    private String summary;
    private ArrayList<Tag> tags;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public ArrayList<Tag> getTags() {
        return tags;
    }

    public void setTags(ArrayList<Tag> tags) {
        this.tags = tags;
    }
}





/**
 
 */


package com.example.fangyi.jsondemo.GsonDemo.bean;

/**
 * Created by FANGYI on 2016/4/4.
 */
public class Tag {

    private String count;
    private String name;
    private String title;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}



















































































