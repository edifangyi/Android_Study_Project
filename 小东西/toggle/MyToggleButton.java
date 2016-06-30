package com.example.fangyi.demo.toggle;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.fangyi.demo.R;

/**
 * Created by FANGYI on 2016/6/27.
 */

public class MyToggleButton extends View implements View.OnClickListener {

    /**
     * 一个控件View从创建到显示过程中主要的方法
     * 1.构造方法-实例化
     * 2.测量：onMeasure(int, int); 如果这个View是ViewGroup，有义务测量孩子的宽高
     * 3.指定View的大小和位置：onLayout(boolean, int,int,int,int);
     * 如果这个View是ViewGroup，有义务测量孩子的大小和位置
     * 4.绘制视图：onDraw(canvas)
     */
    private Bitmap backgroundBitmap;
    private Bitmap slideBitmap;
    //画笔
    private Paint paint;
    //图片距离左边的距离
    private float maxSlide;
    //距离左边的最大距离
    private int maxLeft;

    private boolean isStatus = true;
    /**
     * 点击事件是否生效，如果生效滑动事件就失效
     * true 生效
     */
    private boolean isClicked = false;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //设置测量的值宽和高
        setMeasuredDimension(backgroundBitmap.getWidth(), backgroundBitmap.getHeight());
    }

    /**
     * 绘制View
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//        canvas.drawColor(Color.RED);
//        canvas.drawCircle(20, 50, 60 ,paint);
        canvas.drawBitmap(backgroundBitmap, 0, 0, paint);

        canvas.drawBitmap(slideBitmap, maxSlide, 0, paint);
    }

    private void initView() {
        paint = new Paint();
        //设置抗锯齿
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);

        backgroundBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.alert);
        slideBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.alert_btn);

        maxLeft = backgroundBitmap.getWidth() - slideBitmap.getHeight();

        //设置点击事件
//        setOnClickListener(this);

    }

    /**
     * 滑动效果
     *
     * @param event
     * @return
     */
    //第一次按下的X轴坐标
    private float lastX;
    //第一次按下的X轴坐标的历史记录
    private float startX;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN://按下
                //1.第一次按下坐标
                lastX = startX = event.getX();
                isClicked = true;
                break;

            case MotionEvent.ACTION_MOVE://移动
                //2.来到新的X轴
                float newX = event.getX();
                //3.计算偏移量
                float dX = newX - startX;
                //4.更新控件的位置
                maxSlide += dX;
                //移动5个像素，视为滑动事件
                if (Math.abs(event.getX() - lastX) > 5) {
                    isClicked = false;
                }
                //屏蔽非法定位
                flushView();
                //5.重新记录坐标
                startX = event.getX();
                break;

            case MotionEvent.ACTION_UP://离开
                if (!isClicked) {
                    if (maxSlide > maxLeft / 2) {
                        //开
                        isStatus = true;
                    } else if (maxSlide <= maxLeft / 2) {
                        //关
                        isStatus = false;
                    }
                    flushStatus();
                }

                break;
        }
        return true;
    }

    /**
     * 按钮的开关状态
     * true 开， false关
     */


    @Override
    public void onClick(View v) {
        if (isClicked) {
            isStatus = !isStatus;
            flushStatus();
        }

    }

    /**
     * 刷新数据
     */
    private void flushStatus() {

        if (isStatus) {
            //开
            maxSlide = maxLeft;
        } else {
            //关
            maxSlide = 0;
        }

        flushView();
    }

    /**
     * 屏蔽非法定位
     */
    private void flushView() {
        if (maxSlide <= 0) {
            maxSlide = 0;
        }
        if (maxSlide >= maxLeft) {
            maxSlide = maxLeft;
        }
        invalidate();
    }


    /**
     * 在代码中使用
     *
     * @param context
     */
    public MyToggleButton(Context context) {
        super(context);
        initView();
    }


    /**
     * Android系统规定的，在布局文件使用这个类，实例化的时候使用
     *
     * @param context
     * @param attrs
     */
    public MyToggleButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    /**
     * 设置自己的样式的时候用到的
     *
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public MyToggleButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }


}
