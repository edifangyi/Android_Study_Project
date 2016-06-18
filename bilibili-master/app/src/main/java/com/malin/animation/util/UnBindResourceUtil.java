package com.malin.animation.util;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.orhanobut.logger.Logger;

/**
 * @author malin.myemail@163.com
 * @date 16-5-15.15:28
 */
public class UnBindResourceUtil {

    private static final String TAG = UnBindResourceUtil.class.getSimpleName();

    private UnBindResourceUtil() {
    }

    /**
     * 做法也非常简单，在Activity onDestory时候从view的rootview开始，
     * 递归释放所有子view涉及的图片，背景，DrawingCache，监听器等等资源，
     * 让Activity成为一个不占资源的空壳，泄露了也不会导致图片资源被持有。
     *
     * @param view:the root view of the layout
     * @description Unbind the rootView
     * @author malin.myemail@gmail.com
     * @link http://stackoverflow.com/questions/9461364/exception-in-unbinddrawables
     * http://mp.weixin.qq.com/s?__biz=MzAwNDY1ODY2OQ==&mid=400656149&idx=1&sn=122b4f4965fafebf78ec0b4fce2ef62a&3rd=MzA3MDU4NTYzMw==&scene=6#rd
     * @since 2015.12.16
     */
    public static void unBindDrawables(View view) {
        if (view != null) {
            try {
                Drawable drawable = view.getBackground();
                if (drawable != null) {
                    drawable.setCallback(null);
                    Logger.d("drawable.setCallback(null)" + drawable.toString());
                } else {
                }
                if (view instanceof ViewGroup && !(view instanceof AdapterView)) {
                    ViewGroup viewGroup = (ViewGroup) view;
                    int viewGroupChildCount = viewGroup.getChildCount();
                    for (int j = 0; j < viewGroupChildCount; j++) {
                        unBindDrawables(viewGroup.getChildAt(j));
                    }
                    viewGroup.removeAllViews();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * Remove an onclick listener
     *
     * @param view
     * @author malin.myemail@gmail.com
     * @website https://github.com/androidmalin
     * @data 2016-05-16
     */
    public static void unBingListener(View view) {
        if (view != null) {
            try {
                if (view.hasOnClickListeners()) {
                    view.setOnClickListener(null);
                    Logger.d("view.setOnClickListener(null)" + view.toString());
                }

                if (view.getOnFocusChangeListener() != null) {
                    view.setOnFocusChangeListener(null);
                    Logger.d("view.setOnFocusChangeListener(null)" + view.toString());
                }

                if (view instanceof ViewGroup && !(view instanceof AdapterView)) {
                    ViewGroup viewGroup = (ViewGroup) view;
                    int viewGroupChildCount = viewGroup.getChildCount();
                    for (int i = 0; i < viewGroupChildCount; i++) {
                        unBingListener(viewGroup.getChildAt(i));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * 回收Bitmap
     *
     * @param bitmap
     */
    public static void unBindBitmap(Bitmap bitmap) {
        try {
            if (bitmap != null && !bitmap.isRecycled()) {
                bitmap.recycle();
                bitmap = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
