package com.example.fangyi.mygame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;

/**
 * Created by FANGYI on 2016/8/3.
 */

public class BaseButton extends Sprite {
    private boolean isClick;
    private Bitmap pressedBitmap;
    private TopButton.OnClickListener onClickListener;

    public interface OnClickListener{
        void click();
    }
    //设置点击事件
    public void setOnClickListener(TopButton.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public BaseButton(Bitmap defautBitmap, Point postion, Bitmap pressedBitmap) {
        super(defautBitmap, postion);
        this.pressedBitmap = pressedBitmap;
    }

    public void setIsClick(boolean isClick) {
        this.isClick = isClick;
    }

    //当按钮被点击的时候 调用该方法
    public void click() {
        if (onClickListener != null) {
            onClickListener.click();
        }
    }

    /**
     * 判断这个点是否点中了按钮
     * @param touchPoint
     * @return
     */
    public boolean isClick(Point touchPoint) {
        //黄健一个矩形， 矩形指的是当前按钮的矩形
        Rect rect = new Rect(postion.x, postion.y,
                postion.x + pressedBitmap.getWidth(), postion.y+pressedBitmap.getHeight());
        //如果保护这个点了 认为被点击了
        isClick = rect.contains(touchPoint.x, touchPoint.y);
        return isClick;
    }

    /**
     * 修改自身按钮
     * @param canvas
     */
    @Override
    public void drawSelf(Canvas canvas) {
        if (isClick) {
            //绘制按下的图片
            canvas.drawBitmap(pressedBitmap, postion.x, postion.y, null);
        } else {
            //绘制默认的图片
            super.drawSelf(canvas);
        }

    }
}
