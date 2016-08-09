package com.example.fangyi.myviewdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by FANGYI on 2016/7/4.
 */

public class MyView extends View {
    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
//        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.GREEN);
//        paint.setStrokeWidth(3);
        paint.setAntiAlias(true);
//        canvas.drawCircle(80, 80, 60, paint);

//        canvas.drawText("带你飞",  100, 100, paint);
        RectF rectF = new RectF(10, 10, 100, 100);
        rectF.offset(100, 20);
        canvas.drawArc(rectF, -10, -160, false, paint);

        paint.setColor(Color.WHITE);

        canvas.drawCircle(135, 50, 4, paint);
        canvas.drawCircle(175, 50, 4, paint);

        paint.setColor(Color.GREEN);
        paint.setStrokeWidth(3);
        canvas.drawLine(120, 15, 130, 40, paint);
        canvas.drawLine(190, 15, 180, 40, paint);

        canvas.drawRect(110, 75, 200, 150, paint);


    }
}
