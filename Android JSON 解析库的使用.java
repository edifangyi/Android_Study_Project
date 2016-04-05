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

fastjson-1.2.5.jar

/**
 
 */

gson-2.2.4.jar


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


/**
 







 */



Fast-json 的基本用法

    ·定义实体类
    ·根据需要可以讲 JSON 生成单个实体或列表实体集合

Book 和 Tag 类 跟 Gson一样

/**
 
 */

package com.example.fangyi.jsondemo.Fastjson;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.fangyi.jsondemo.Fastjson.bean.Book;
import com.example.fangyi.jsondemo.R;

import java.util.ArrayList;
import java.util.List;

public class Fastjson extends AppCompatActivity {

    private String url = "https://api.douban.com/v2/book/1220562";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fastjson);

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
    }

    public void dealData(String result) {
        Book book = JSON.parseObject(result, Book.class);//解析一条数据

        //多个book列表，如果result是多本书的话，可以解析成列表，数组集合
        List<Book> books = JSON.parseObject(result, new TypeReference<List<Book>>(){});

        //想把很多个对象，转换成JSON格式的话
        Book book1 = new Book();
        book1.setTitle("标题1");
        Book book2 = new Book();
        book2.setTitle("标题2");
        Book book3 = new Book();
        book3.setTitle("标题3");

        JSON.toJSON(book1);//一个对象对象转换成JSON格式的话

        //集合转换JSON格式
        List<Book> list = new ArrayList<Book>();
        list.add(book1);
        list.add(book2);
        list.add(book3);
        JSON.toJSON(list);




        book.getTitle();
        book.getPublisher();
        book.getSummary();
        book.getTags().size();

    }
}



/**
 







 */

实现效果:

1.分别使用 Gson 和 Fast-json 实现复杂的 Json 数据解析
2.实现模拟图书列表 Json 数据的解析展现

/**
 
 */



package com.example.fangyi.jsondemo.BookBusiness;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.fangyi.jsondemo.BookBusiness.adapter.BookListAdapter;
import com.example.fangyi.jsondemo.BookBusiness.bean.Book;
import com.example.fangyi.jsondemo.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BookBusiness extends AppCompatActivity {

    private String url = "http://apis.juhe.cn/goodbook/catalog?key=cffd90e2deb9501950b3b5746e55236a&dtype=json";
    private ListView lv;
    private BookListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_business);

        lv = (ListView) this.findViewById(R.id.lv);

        getData();
    }

    private void getData() {
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                try {
                    dealData(s);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        Volley.newRequestQueue(getApplicationContext()).add(request);

    }

    private void dealData(String result) throws JSONException {
        JSONObject object = new JSONObject(result);
        ArrayList<Book> books = (ArrayList<Book>) JSON.parseArray(object.getString("result"), Book.class);

//        Gson gson = new Gson();
//        Type listType = new TypeToken<ArrayList<Book>>(){}.getType();
//
//        JSONObject object = new JSONObject(result);
//
//        ArrayList<Book> books = gson.fromJson(object.getString("result"), listType);


        adapter = new BookListAdapter(this, books);
        lv.setAdapter(adapter);
    }
}


/**
 
 */


package com.example.fangyi.jsondemo.BookBusiness.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.fangyi.jsondemo.BookBusiness.bean.Book;
import com.example.fangyi.jsondemo.R;

import java.util.ArrayList;

/**
 * Created by FANGYI on 2016/4/4.
 */
public class BookListAdapter extends BaseAdapter {

    private Context con;
    private ArrayList<Book> list;


    public BookListAdapter(Context context, ArrayList<Book> books) {
        this.con = context;
        this.list = books;
    }

    /**
     * How many items are in the data set represented by this Adapter.
     *
     * @return Count of items.
     */
    @Override
    public int getCount() {
        return list.size();
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
        return list.get(position);
    }

    /**
     * Get the row id associated with the specified position in the list.
     *
     * @param position The position of the item within the adapter's data set whose row id we want.
     * @return The id of the item at the specified position.
     */
    @Override
    public long getItemId(int position) {
        return position;
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
        ViewHolder holder = null;
        if (holder == null) {
            convertView = View.inflate(con, R.layout.book_business_item_list, null);
            holder = new ViewHolder();
            holder.tv = (TextView) convertView.findViewById(R.id.tv);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Book book = list.get(position);
        holder.tv.setText(book.getCatalog() + "\n" + book.getId());
        return convertView;
    }

    class ViewHolder {
        TextView tv;
    }
}



/**
 
 */


package com.example.fangyi.jsondemo.BookBusiness.bean;

/**
 * Created by FANGYI on 2016/4/4.
 */
public class Book {
    private String id;
    private String catalog;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catlog) {
        this.catalog = catlog;
    }
}

