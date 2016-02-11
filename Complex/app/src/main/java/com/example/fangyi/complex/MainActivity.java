package com.example.fangyi.complex;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.nio.channels.AsynchronousCloseException;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {


    private SlidingMenu slidingMenu;

    private PullToRefreshListView pullToRefreshListView;
    private ArrayAdapter<String> adapter;
//    private String[] data = new String[]{"Hello",
//            "fangyi1896@gmail.com",
//            "fangyi186@outlook.com",
//            "fangyi1896@163.com"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
         *Android SlidingMenu 菜单栏程序设计开发
         */
        slidingMenu = new SlidingMenu(this);//创建对象
        slidingMenu.setMode(SlidingMenu.LEFT);//设定模式，SlidingMenu在左边\
        slidingMenu.setBehindOffsetRes(R.dimen.sliding_menu_offset);//配置slidingmenu偏移出来的尺寸

        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);//全屏都可以拖拽触摸，打开slidingmenu
        slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);//附加到当前的Aty上去
        slidingMenu.setMenu(R.layout.slidingmenu);


        /*
         *Android 通用下拉刷新控件的使用
         */

        //下拉菜单以后 效果：放开以刷新
        pullToRefreshListView = (PullToRefreshListView) findViewById(R.id.pullToRefreshListViewlayout);

        //集合
        List<String> data = new ArrayList<String>();
        data.add("fangyi1896@gmail.com");
        data.add("fangyi186@outlook.com");
        data.add("fangyi1896@163.com");

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);
        pullToRefreshListView.setAdapter(adapter);

        //下拉菜单以后 效果：正在载入···
        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... params) {
                        //休眠2秒钟，模拟网络通信的效果
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }

                    //可以用来修改UI控件的变化
                    @Override
                    protected void onPostExecute(Void aVoid) {
                        super.onPostExecute(aVoid);

                        adapter.addAll("87649669@qq.com", "475714960@qq.com");

                        //然后我们通过 pullToRefreshListView 成功加载了上面的数据
                        pullToRefreshListView.onRefreshComplete();
                    }
                }.execute();
            }
        });




        /*
         *那个小邮件图标
         */
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //重写KeyDown事件，菜单键按下也能拉出slidingmenu
        switch (keyCode) {
            case KeyEvent.KEYCODE_MENU:
                slidingMenu.toggle(true);
                break;
            default:
                break;
        }
        return super.onKeyDown(keyCode, event);
    }
}
