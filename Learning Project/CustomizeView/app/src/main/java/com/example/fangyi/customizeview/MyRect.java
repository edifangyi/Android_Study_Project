package com.example.fangyi.customizeview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by FANGYI on 2016/2/12.
 */
public class MyRect extends View {

    public MyRect(Context context) {
        super(context);
    }

    //资源解析器来访问
    public MyRect(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MyView);

        //可以获得颜色值
        int color = ta.getColor(R.styleable.MyView_rect_color, 0xffff0000);
        setBackgroundColor(color);

        ta.recycle();
    }
}
