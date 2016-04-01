什么是注解式开发:

    ·JDK1.5后支持注解方式，想用注解式开发，就要自定义注解

    ·结构: @+ 注解名(也可以叫类名) + 传递的属性值，key和value 可设置目标
     范围: 方法(Method)、属性(Filed)、类(Type)

    ·自定义注解要用到 @interface:用于定义注解
    				  @Target:用于描述注解的适用范围
    				  @Retention:注解的生命周期，一般 RetentionPolicy.RUNTIME
    
    ·在 Android 中使用一般是简化代码，提升开发效率，清晰简介



主流注解式框架:

	Dagger:采用预编译技术，高效，但是对View绑定操作注解不是很方便

	Butter Knife:配置简单，强大的 View 注入绑定和简单的日常用法注解

	Android Annotations:配置麻烦，需要在项目清单里注册生成的子类。反射机制会占用资源内存和耗时



Butter Knife 特点:

    ·强大方便的处理 View 绑定和 Click 事件，简化代码，提上开发效率
    ·方便的处理 ListView 的 Adapter 里的 ViewHolder 绑定问题
    ·运行时不会影响APP效率，使用配置方便
    ·代码思路清晰，可读性强


/**
 
 */


Butter Knife 简单用法

    ·View 绑定
    	onCreate里注册: ButterKnife.inject(this);
    	Activity 声明绑定空间，例如: @InjectView(R.id.title) TextView title;

    ·Onclick 等事件处理
    	例如:
    	@Onclick(R.id.submit)
    	public void sayHi(Button button) {
    		button.setText("Hello");
    	}



/**
 
 */



package com.example.fangyi.butterknife;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


	@Optional	//标签 可以为不存在的id提供异常，防止程序崩溃

    @InjectView(R.id.id_tv_1)TextView tv_1;//绑定一个View
    @InjectView(R.id.id_tv_2)TextView tv_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.inject(this);//注册ButterKnife
    }

    //给TextView添加一个点击事件
    @OnClick(R.id.id_tv_1)
    public void sayHi(TextView textView) {  //可以在点击里面传进去一个具体的控件
        Toast.makeText(this, "你好", Toast.LENGTH_SHORT).show();
        textView.setText("ButterKnife");    //点击换字
    }

    //一个方法里面处理多个点击事件
    @OnClick({R.id.id_tv_1, R.id.id_tv_2})
    public void sayHito(TextView tv) {  //可以通过对view判断来进行设置
        Toast.makeText(this, "你好", Toast.LENGTH_SHORT).show();
        tv.setText("ButterKnife");//点击换字
    }
}




/**
 
 */


简单的设配器改写


package com.example.fangyi.butterknife;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by FANGYI on 2016/4/1.
 */
public class MyAdapter extends BaseAdapter{
    private Context mContext;

    public MyAdapter(Context context) {
        mContext = context;
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
        ViewHolder holder = null;
        if (holder==null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.activity_main, null);
            holder = new ViewHolder(convertView);
            holder.textView = (TextView) convertView.findViewById(R.id.);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textView.setText("更改");
        return convertView;
    }


    //传统的使用方法
    static class ViewHolder {
        @InjectView(R.id.id_tv_2)TextView textView;
        public ViewHolder(View view) {
            ButterKnife.inject(view);
        }
    }
}



/**
 
 */


实现效果:
	用 ListView 展示一个列表数据，每个 Item 里含有一个 Button，可以点击。全部使用 Butterknife 注解方式实现


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
    tools:context="com.example.fangyi.butterknife.MainActivity"
    android:orientation="vertical">

    <ListView
        android:id="@+id/lv"
        android:layout_width="match_parent"
        android:layout_height="wrap_content">

    </ListView>
</LinearLayout>





<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical"
    android:descendantFocusability="blocksDescendants"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <TextView
        android:id="@+id/id_tv_3"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />

    <Button
        android:id="@+id/btn"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />

</LinearLayout>






/**
 
 */
package com.example.fangyi.butterknife;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.OnItemClick;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.lv)ListView listView;
    private MyAdapter adapter;
    private ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.inject(this);//注册ButterKnife
        list = new ArrayList<String>();
        list.add("测试-0");
        list.add("测试-1");
        list.add("测试-2");
        list.add("测试-3");
        list.add("测试-4");
        list.add("测试-5");
        list.add("测试-6");
        list.add("测试-7");
        list.add("测试-8");
        list.add("测试-9");
        adapter = new MyAdapter(this, list);
        listView.setAdapter(adapter);
    }

    @OnItemClick(R.id.lv)
    public void onMyItemClick(int position) {
        Toast.makeText(this, position + "", Toast.LENGTH_SHORT).show();
    }
}




/**
 
 */

package com.example.fangyi.butterknife;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by FANGYI on 2016/4/1.
 */
public class MyAdapter extends BaseAdapter{
    private Context mContext;
    private ArrayList<String> lists;

    public MyAdapter(Context context, ArrayList<String>list) {
        mContext = context;
        lists = list;
    }



    /**
     * How many items are in the data set represented by this Adapter.
     *
     * @return Count of items.
     */
    @Override
    public int getCount() {
        return lists.size();
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
        return lists.get(position);
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
        if (convertView==null) {
            convertView = View.inflate(mContext, R.layout.item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        String s = lists.get(position);
        holder.textView.setText(s);
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "点击", Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }


    //传统的使用方法
    static class ViewHolder {
        @InjectView(R.id.id_tv_3)TextView textView;
        @InjectView(R.id.btn)Button button;
        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}




















