package com.example.fangyi.mygame;

import android.graphics.Bitmap;
import android.graphics.Point;

/**
 * Created by FANGYI on 2016/8/3.
 */

public class Face extends Sprite {
    int speed = 60;
    private final int tX;
    private final int tY;

    public Face(Bitmap defautBitmap, Point postion, Point touchPoint) {
        super(defautBitmap, postion);

        int X = touchPoint.x - postion.x;
        int Y = touchPoint.y - postion.y;//算出来两个直角边

        int D = (int) Math.sqrt(X * X + Y * Y);

        //每次移动的偏移量
        tX = speed * X / D;
        tY = speed * Y / D;
    }

    //移动的方法
    public void move() {
        this.postion.x += tX;
        this.postion.y += tY;
    }


}
