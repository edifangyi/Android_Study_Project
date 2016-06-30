package com.example.fangyi.demo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FANGYI on 2016/6/27.
 */

public class ViewPagerDeom extends AppCompatActivity {
    private ViewPager viewpager;
    private LinearLayout llPoint;
    private List<ImageView> imageViews;
    private TextView tvTitle;

    private int[] ids = {R.mipmap.lks1, R.mipmap.lks2, R.mipmap.lks3, R.mipmap.lks4};
    private String[] imageDescriptions = {"图片标题==1", "图片标题==2", "图片标题==3", "图片标题==4"};

    private int lastPositon;
    private boolean isRuning = false;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            //得到当前的选中的位置
            viewpager.setCurrentItem(viewpager.getCurrentItem() + 1);
            if (isRuning) {
                handler.sendEmptyMessageDelayed(0, 2000);
            }

        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpagerdeom);
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        llPoint = (LinearLayout) findViewById(R.id.ll_point);
        tvTitle = (TextView) findViewById(R.id.tv_title);


        initDots();
        initListener();
    }

    /**
     * 监听页面
     */
    private void initListener() {
        viewpager.setAdapter(new MyPagerAdapter());
        //设置第一个title为默认
        String msg = imageDescriptions[0];
        tvTitle.setText(msg);

        //设置中间值
        //无限循环
        int index = Integer.MAX_VALUE / 2 - Integer.MAX_VALUE / 2 % imageViews.size();
        viewpager.setCurrentItem(index);

        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            //当页面滚动了的时候回调
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            //当选择了某个页面的时候回调
            //选择了哪个页面：position
            @Override
            public void onPageSelected(int position) {
                int myindex = position % imageViews.size();//无限循环
                String msg = imageDescriptions[myindex];
                tvTitle.setText(msg);
                //当前指示点设置高亮
                llPoint.getChildAt(myindex).setEnabled(true);
                //上一次的高亮了的，设置为默认
                llPoint.getChildAt(lastPositon).setEnabled(false);
                lastPositon = myindex;
            }

            //当页面状态发送变化的时候回调
            //静止-滑动
            //滑动-静止
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //启动自动轮放
        isRuning = true;
        handler.sendEmptyMessageDelayed(0, 2000);
    }

    /**
     * 实例化数据
     */
    private void initDots() {
        imageViews = new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(ids[i]);
            imageViews.add(imageView);
            initPoint(i);
        }
    }

    /**
     * 设置指示点
     *
     * @param i
     */
    private void initPoint(int i) {
        ImageView point = new ImageView(this);
        point.setBackgroundResource(R.drawable.point_selector);
        //设置参数的时候，主要是控制父类是什么，就导入谁的包
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(30, 30);
        params.leftMargin = 15;
        point.setLayoutParams(params);
        llPoint.addView(point);
        if (i == 0) {
            Log.e("================", "true");
            point.setEnabled(true);
        } else {
            point.setEnabled(false);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //停止自动播放动画
        isRuning = false;
    }

    private class MyPagerAdapter extends PagerAdapter {
        //返回总条数
        @Override
        public int getCount() {
            return Integer.MAX_VALUE;//无限循环
        }

        /**
         * 判断View是否是instantiateItem方法的返回值
         *
         * @param view   当前滑动的view
         * @param object 将要进入的新创建的view，由instantiateItem方法创建
         * @return true: 表示不去创建，使用缓存  false:去重新创建
         */
        @Override
        public boolean isViewFromObject(View view, Object object) {
//            if (view == object) {
//                return true;
//            }else {
//                return false;
//            }
            return (view == object);
        }

        /**
         * 类似于BaseAdapger的getView方法
         * 由于它最多就3个界面，不需要viewHolder
         *
         * @param container ViewPager容器
         * @param position  位置
         * @return 实例化图片返回结果
         */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            //得到图片
            ImageView view = imageViews.get(position % imageViews.size());//无限循环
            //添加到容器中
            container.addView(view);
            //返回实例化对象,最好直接返回实例
            return view;
        }

        /**
         * @param container
         * @param position  当前需要消耗第几个page
         * @param object    当前需要消耗的page
         */
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            /**
             * 销毁某个View
             */
            container.removeView((View) object);
//            super.destroyItem(container, position, object);
        }

    }

}
