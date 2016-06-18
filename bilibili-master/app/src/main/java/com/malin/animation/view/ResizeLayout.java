package com.malin.animation.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * 监听布局的高度变化
 *
 * @author malin.myemail@163.com
 * @date 16-5-16 03:13
 */

public class ResizeLayout extends RelativeLayout {
    private OnResizeListener mOnResizeListener;

    public interface OnResizeListener {
        void OnResize(int w, int h, int oldw, int oldh);
    }


    public void setOnSizeChangedListener(OnResizeListener listener) {
        this.mOnResizeListener = listener;
    }

    public ResizeLayout(Context context) {
        super(context);
    }

    public ResizeLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ResizeLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }


    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        if (mOnResizeListener != null) {
            mOnResizeListener.OnResize(w, h, oldw, oldh);
        }
    }
}
