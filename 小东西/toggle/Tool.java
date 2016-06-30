package com.example.fangyi.demo.toggle;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;

/**
 * Created by FANGYI on 2016/6/27.
 */
public class Tool {
    /**
     * 播放旋转动画并且隐藏控件
     *
     * @param view
     */
    public static void hideView(ViewGroup view) {
        hideView(view, 0);
    }

    /**
     * 播放旋转动画并且显示控件
     *
     * @param view
     */
    public static void showView(ViewGroup view) {
        showView(view, 0);
    }


    public static void hideView(ViewGroup view, int setStartOffset) {
        /**
         * 第一个参数：旋转时的起始角度
         * 第二个参数：旋转到哪个角度
         * 第三、第四：旋转圆心的X轴和Y轴坐标
         */
        RotateAnimation ra = new RotateAnimation(0, 180, view.getWidth() / 2, view.getHeight());
        ra.setDuration(500);//播放时长
        ra.setFillAfter(true);//让停止在播放动画后的状态
        ra.setStartOffset(setStartOffset);//播放动画时间延迟
        view.startAnimation(ra);
        view.setEnabled(false);
        view.setVisibility(View.GONE);//隐藏不可见
        int childCount = view.getChildCount();
        for (int i = 0; i < childCount; i++) {
            view.getChildAt(i).setEnabled(false);
            view.getChildAt(i).setVisibility(View.GONE);
        }
    }

    /**
     * 播放旋转动画并且显示控件
     *
     * @param view
     */
    public static void showView(ViewGroup view, int setStartOffset) {
        /**
         * 第一个参数：旋转时的起始角度
         * 第二个参数：旋转到哪个角度
         * 第三、第四：旋转圆心的X轴和Y轴坐标
         */
        RotateAnimation ra = new RotateAnimation(180, 360, view.getWidth() / 2, view.getHeight());
        ra.setDuration(500);//播放时长
        ra.setFillAfter(true);//让停止在播放动画后的状态
        ra.setStartOffset(setStartOffset);//播放动画时间延迟
        view.startAnimation(ra);
        view.setEnabled(true);
        view.setVisibility(View.VISIBLE);//可见
        int childCount = view.getChildCount();
        for (int i = 0; i < childCount; i++) {
            view.getChildAt(i).setEnabled(true);
            view.getChildAt(i).setVisibility(View.VISIBLE);
        }
    }
}
