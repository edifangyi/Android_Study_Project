package com.example.fangyi.myviewdemo.view_1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.fangyi.myviewdemo.R;
import com.example.fangyi.myviewdemo.view_1.view.ViewPagerIndicator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by FANGYI on 2016/6/30.
 */

/**
 * 自定义ViewPager指示器
 * 本课可以学到：
 * 1、自定义ViewPagerIndictor
 * 2、合理计算滑动位置
 * 3、Fragment+ViewPager经典使用方法
 */

public class MyViewPager extends AppCompatActivity {

    private ViewPagerIndicator mIndicator;
    private ViewPager mViewPager;
    private ViewPagerIndicator mIndicator2;
    private ViewPager mViewPager2;

    private List<String> mTitles = Arrays.asList("短信", "收藏", "推荐");
    private List<String> mTitles2 = Arrays.asList("短信1", "收藏2", "推荐3", "短信4", "收藏5", "推荐6", "短信7", "收藏8", "推荐9");
    private List<VpSimpleFragment> mContents = new ArrayList<>();
    private List<VpSimpleFragment> mContents2 = new ArrayList<>();
    private FragmentPagerAdapter mAdapter;
    private FragmentPagerAdapter mAdapter2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_1);

        initViews();
        initDatas();
        mViewPager.setAdapter(mAdapter);
        mIndicator.setViewPager(mViewPager, 0);

        /**
         * 使用的自定义接口，可以回调做进一步操作
         */
//        mIndicator.setOnPageChangeListener(new ViewPagerIndicator.PageOnchangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });

        mIndicator2.setVisibleTabCount2(5);
        mIndicator2.setTabItemTitles(mTitles2);
        mViewPager2.setAdapter(mAdapter2);
        mIndicator2.setViewPager(mViewPager2, 0);

    }


    private void initDatas() {
        for (String mTitle : mTitles) {
            VpSimpleFragment fragment = VpSimpleFragment.newInstance(mTitle);
            mContents.add(fragment);
        }

        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mContents.get(position);
            }

            @Override
            public int getCount() {
                return mContents.size();
            }
        };


        for (String mTitle : mTitles2) {
            VpSimpleFragment fragment = VpSimpleFragment.newInstance(mTitle);
            mContents2.add(fragment);
        }
        mAdapter2 = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mContents2.get(position);
            }

            @Override
            public int getCount() {
                return mContents2.size();
            }
        };

    }

    private void initViews() {

        mIndicator = (ViewPagerIndicator) findViewById(R.id.id_indicator);
        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);
        mIndicator2 = (ViewPagerIndicator) findViewById(R.id.id_indicator2);
        mViewPager2 = (ViewPager) findViewById(R.id.id_viewpager2);
    }


}
