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
