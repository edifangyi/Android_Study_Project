package com.example.fangyi.mygame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 如果想要SurfaceHolder.Callback 生效，必须 SurfaceHolder.addCallBack(SurfaceHolder.Callback);
 * <p>
 * Created by FANGYI on 2016/8/3.
 */

public class GameUI extends SurfaceView implements SurfaceHolder.Callback {
    private RenderThread renderThread;
    private boolean flag;//线程运行的标记
    private final SurfaceHolder holder;

    /*****************************************/
    private Man man;
    private List<Face> faceList;
    private TopButton topButton;//用到的按钮
    private DownButton downButton;//用到的按钮
    private LeftButton leftButton;//用到的按钮
    private RightButton rightButton;//用到的按钮


    public GameUI(Context context) {
        super(context);
        holder = getHolder();
        holder.addCallback(this);//保证 surfaceCreated，surfaceChanged，surfaceDestroyed 生效
    }

    /**
     * 处理屏幕的点击事件
     *
     * @param event
     */
    public void handlerTouch(MotionEvent event) {
        switch (event.getAction()) {
//            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_DOWN:
                int x = (int) event.getRawX();
                int y = (int) event.getRawY();
                if (topButton.isClick(new Point(x, y))) {
//                    man.move(Man.DOWN);
                    topButton.click();
                } else if (downButton.isClick(new Point(x, y))) {
                    downButton.click();
                } else if (leftButton.isClick(new Point(x, y))) {
                    leftButton.click();
                } else if (rightButton.isClick(new Point(x, y))) {
                    rightButton.click();
                } else {
                    Face face = man.createFace(getContext(), new Point(x, y));
                    faceList.add(face);
                }


                break;
            case MotionEvent.ACTION_UP:
                topButton.setIsClick(false);//当手指抬起的时候，让按钮点击的状态改成false
                downButton.setIsClick(false);//当手指抬起的时候，让按钮点击的状态改成false
                leftButton.setIsClick(false);//当手指抬起的时候，让按钮点击的状态改成false
                rightButton.setIsClick(false);//当手指抬起的时候，让按钮点击的状态改成false

                break;
        }
    }

    /**
     * 线程
     */
    private class RenderThread extends Thread {
        @Override
        public void run() {
            super.run();
            while (flag) {
                try {
                    drawUI();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }

    /**
     * 绘制界面
     */
    private void drawUI() {
        //锁定画布
        Canvas canvas = holder.lockCanvas();
        //绘制界面
        Paint paint = new Paint();//画笔
        paint.setColor(Color.GRAY);
        //绘制了矩形
        canvas.drawRect(0, 0, getWidth(), getHeight(), paint);//绘制了整个屏幕大小的矩形，盖住以前绘制出来的小人和笑脸

        man.drawSelf(canvas);//绘制
        topButton.drawSelf(canvas);
        downButton.drawSelf(canvas);//绘制
        rightButton.drawSelf(canvas);
        leftButton.drawSelf(canvas);

        for (Face face : faceList) {
            face.drawSelf(canvas);
            face.move();

            if (face.postion.x < 0
                    || face.postion.x > getWidth()
                    || face.postion.y < 0
                    || face.postion.y > getHeight()) {
                faceList.remove(face);

            }
        }

        //解锁画布并提交
        holder.unlockCanvasAndPost(canvas);
    }


    /**
     * 当Suraface创建的时候调用
     *
     * @param holder
     */
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Bitmap manBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.avatar_boy);
        man = new Man(manBitmap, new Point(0, 0));
        faceList = new CopyOnWriteArrayList<>();//可以再遍历的过程中进行增删
        Bitmap topDefaultBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.top_default);
        Bitmap topPressBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.top_press);
        topButton = new TopButton(topDefaultBitmap, new Point(200, getHeight() - 400), topPressBitmap);
        topButton.setOnClickListener(new TopButton.OnClickListener() {
            @Override
            public void click() {
                man.move(Man.TOP);
            }
        });

        Bitmap downDefaultBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.down_default);
        Bitmap downPressBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.down_press);
        downButton = new DownButton(downDefaultBitmap, new Point(100, getHeight() - 250), downPressBitmap);
        downButton.setOnClickListener(new DownButton.OnClickListener() {
            @Override
            public void click() {
                man.move(Man.DOWN);
            }
        });

        Bitmap leftDefaultBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.left_default);
        Bitmap leftPressBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.left_press);
        leftButton = new LeftButton(leftDefaultBitmap, new Point(300, getHeight() - 250), leftPressBitmap);
        leftButton.setOnClickListener(new LeftButton.OnClickListener() {
            @Override
            public void click() {
                man.move(Man.LEFT);
            }
        });

        Bitmap rightDefaultBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.right_default);
        Bitmap rightPressBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.right_press);
        rightButton = new RightButton(rightDefaultBitmap, new Point(200, getHeight() - 200), rightPressBitmap);
        rightButton.setOnClickListener(new RightButton.OnClickListener() {
            @Override
            public void click() {
                man.move(Man.RIGHT);
            }
        });


        renderThread = new RenderThread();
        flag = true;
        renderThread.start();//开启线程

    }

    /**
     * 当Suraface改变的时候调用
     *
     * @param holder
     * @param format
     * @param width
     * @param height
     */
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    /**
     * 当Suraface销毁的时候调用
     *
     * @param holder
     */
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        //renderThread.stop();//不推荐使用
        //一般停止线程 都是控制线程的循环
        flag = false;
    }
}
