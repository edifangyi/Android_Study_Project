package com.malin.animation.util;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;

/**
 * 获取彩色的图片
 *
 * @author malin.myemail@163.com
 * @date 16-5-25.20:01
 * <p/>
 */
public class ColorBitmapUtilTwo {


    private ColorBitmapUtilTwo() {
    }

    /**
     * 在内存中绘制可变色的Icon
     */
    public static Bitmap getColorBitmap(Bitmap bitmap, int color, int alpha) {

        Bitmap mBitmap = null;
        if (bitmap != null) {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            mBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
            Canvas mCanvas = new Canvas(mBitmap);
            Paint mPaint = new Paint();
            mPaint.setColor(color);
            mPaint.setAntiAlias(true);
            mPaint.setDither(true);
            mPaint.setAlpha(alpha);
            Rect mIconRect = new Rect(0, 0, width, height);
            mCanvas.drawRect(mIconRect, mPaint);
            mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
            mPaint.setAlpha(255);
            mCanvas.drawBitmap(bitmap, null, mIconRect, mPaint);
        }
        return mBitmap;
    }

    /**
     * 从资源文件中获取图片，给图片着色
     *
     * @param resources
     * @param resId
     * @param color
     * @param alpha
     * @return
     */
    public static Bitmap getColorBitmap(Resources resources, int resId, int color, int alpha) {

        Bitmap bitmap = BitmapFactory.decodeResource(resources, resId);
        Bitmap mBitmap = null;
        if (bitmap != null) {
            mBitmap = getColorBitmap(bitmap, color, alpha);
        }
        return mBitmap;
    }
}
