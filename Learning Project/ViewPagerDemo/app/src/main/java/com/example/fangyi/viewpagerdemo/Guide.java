package com.example.fangyi.viewpagerdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FANGYI on 2016/2/15.
 */                                 //点的改变需要ViewPager，所以我们需要一个监听事件，监听ViewPager
                                    //我们要复写三个方法
public class Guide extends Activity implements ViewPager.OnPageChangeListener {

    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private List<View> views;
    private ImageView[] dots;
    private int[] ids = {R.id.iv1,R.id.iv2,R.id.iv3};
    private Button start_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guide);//加载好当前的视图
        initViews();
        initDots();
    }

    //初始化的方法        首先我们要加载额外的三个view 切换的三个view
    private void initViews() {
        LayoutInflater inflater = LayoutInflater.from(this);//先来通过这个来加载

        //view 是要放在集合当中的，所以我们先将view进行实例化操作
        views = new ArrayList<View>();
        views.add(inflater.inflate(R.layout.one, null));
        views.add(inflater.inflate(R.layout.two, null));
        views.add(inflater.inflate(R.layout.three, null));

        //之后，我们来实例化viewPagerAdapter
        viewPagerAdapter = new ViewPagerAdapter(views, this);
        //下面，我们要找到当前的view对象，也就是viewPagerAdapter的对象
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(viewPagerAdapter);

        //直接findViewById不行，因为当前加载的View是guide，所以我们应该到views列表里找到，谁加载了这个View
        start_btn = (Button) views.get(2).findViewById(R.id.start_btn);
        start_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //直接跳转到主界面就可以了，因为主界面至始至终都没有打开过，所以直接跳转打开新界面
                Intent i = new Intent(Guide.this,MainActivity.class);
                startActivity(i);
                finish();//将当期没用的收回
            }
        });

        viewPager.addOnPageChangeListener(this);//回调
    }

    //点的操作方法
    private void initDots() {
        dots = new ImageView[views.size()];

        for (int i = 0; i < views.size(); i++) {
            dots[i] = (ImageView) findViewById(ids[i]);
        }
    }

    @Override//当前新的页面被选中时候调用
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override//当页面滑动时候调用
    public void onPageSelected(int position) {
        for (int i = 0; i < ids.length; i++) {
            if (position == i) {
                dots[i].setImageResource(R.drawable.login_point_selected);
            }else {
                dots[i].setImageResource(R.drawable.login_point);
            }
        }
    }

    @Override//滑动状态改变时候进行调用
    public void onPageScrollStateChanged(int state) {

    }
}
