package com.example.fangyi.mygame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;

/**
 * Created by FANGYI on 2016/8/3.
 */

public class Man extends Sprite {
    public static final int TOP = 0;
    public static final int DOWN = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;
    int speed = 30;

    public Man(Bitmap defautBitmap, Point postion) {
        super(defautBitmap, postion);
    }


    public Face createFace(Context context, Point touchPoint) {
        //加载笑脸的图片
        Bitmap faceBitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.rating_small);
        Face face = new Face(faceBitmap, new Point(postion.x + 120, postion.y + 100), touchPoint);
        return face;
    }

    public void move(int d) {
        if (d == TOP) {
            this.postion.y -= speed;
        } else if (d == DOWN) {
            this.postion.y += speed;
        } else if (d == LEFT) {
            this.postion.x -= speed;
        } else if (d == RIGHT) {
            this.postion.x += speed;
        }
    }
}
