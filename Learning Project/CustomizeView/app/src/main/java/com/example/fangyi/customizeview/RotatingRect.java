package com.example.fangyi.customizeview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by FANGYI on 2016/2/12.
 */
public class RotatingRect extends View {

    public RotatingRect(Context context) {
        super(context);

        initProperties();
    }

    public RotatingRect(Context context, AttributeSet attrs) {
        super(context, attrs);

        initProperties();
    }

    public RotatingRect(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initProperties();
    }



    private Paint p;//画笔对象，控制我们绘制图形的样式
    private float degrees = 0;//绘制旋转角度

    //初始化属性
    private void initProperties() {
        p = new Paint();
        p.setColor(Color.RED);//给个红色
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        canvas.save();//保存状态
        //        canvas.rotate(degrees); //默认绕着左上角旋转，degrees是旋转一个角度
        canvas.translate(200,200);//调整坐标
        canvas.rotate(degrees,50,50);//第一个是角度，第二个和第三个是旋转坐标点
        canvas.drawRect(0,0,100,100,p);//绘图API，绘制了一个正方形
        degrees ++;
        canvas.restore();
        invalidate();//使这个View无效，太耗资源，可以用延时
        //重绘的时候查看是否是有效状态，有效，就不重绘了，重新绘制，就会重新执行draw方法
        //Handler 延时绘制
    }
}