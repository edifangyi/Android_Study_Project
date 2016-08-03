package com.example.fangyi.mygame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;

/**
 * 精灵 所有显示对象最小基类
 * Created by FANGYI on 2016/8/3.
 */

public abstract class Sprite {
    private Bitmap defautBitmap;//默认显示的图片
    protected Point postion;//位置

    public Sprite(Bitmap defautBitmap, Point postion) {
        this.defautBitmap = defautBitmap;
        this.postion = postion;
    }

    //把图片绘制到位置
    public void drawSelf(Canvas canvas) {
        canvas.drawBitmap(defautBitmap, postion.x, postion.y, null);
    }
}
