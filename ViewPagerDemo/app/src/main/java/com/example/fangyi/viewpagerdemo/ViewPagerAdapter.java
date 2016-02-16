package com.example.fangyi.viewpagerdemo;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by FANGYI on 2016/2/15.
 */
public class ViewPagerAdapter extends PagerAdapter {
//重写方法  直接 alt+enter


    private List<View> views;
    private Context context;

    public ViewPagerAdapter(List<View> views, Context context) {
        this.views = views;
        this.context = context;
    }


    @Override//我们不可能都保存每一个views，我们可以将其销毁
    public void destroyItem(ViewGroup container, int position, Object object) {

        //我们使用container.removeView来进行移除，通过views来知道当前个数，position来进行索引
//            container.removeView(views.get(position));
//
        container.removeView(views.get(position));
    }

    @Override//我们需要一个加载views的方法，
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(views.get(position));

        return views.get(position);
    }


//    @Override
//    public void destroyItem(View container, int position, Object object) {
//        ((ViewPager)container).removeView(views.get(position));
//    }
//
//    @Override
//    public Object instantiateItem(View container, int position) {
//        ((ViewPager)container).removeView(views.get(position));
//
//        return views.get(position);
//    }

    @Override//返回当前views 的数量
    public int getCount() {
        return views.size();
    }

    @Override//来判断当前view是不是我们需要的对象
    public boolean isViewFromObject(View view, Object object) {
        return (view == object);
    }
}
