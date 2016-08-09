package com.example.fangyi.myviewdemo.view_1.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.fangyi.myviewdemo.R;

import java.util.List;

/**
 * Created by FANGYI on 2016/6/30.
 */

public class ViewPagerIndicator extends LinearLayout {

    private int mTabVisibleCount;//可见数量
    private static final int COUNT_DEFAULT_TAB = 3;//默认数量
    private static final int COLOR_TEXT_NORMAL = 0x77FFFFFF;//字体颜色
    private static final int COLOR_TEXT_HIGHLIGHT = 0xFFFFFFFF;//高亮的颜色
    private List<String> mTitles;


    /**
     * 三角形的参数
     */
    private Paint mPaint;

    private Path mPath;//通过path构造三角形

    private int mTriangleWidth;//三角形底边的宽度
    private int mTriangleHeight;//三角形的高度
    private static final float RADIO_TRIANGLE_WIDTH = 1 / 6f;//三角形的底边与 LinearLayout 的高度比例
    private final int DIMENSION_TRIANGLE_WIDTH_MAX = (int) (getScreenWidth()/3 * RADIO_TRIANGLE_WIDTH);//三角形底边最大宽度
    private int mInitTranslationX;//初始化偏移位置，显示第一个title中间
    private int mTranslationX;//移动时候的偏移位置


    public ViewPagerIndicator(Context context) {
        this(context, null);
    }

    public ViewPagerIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        //获取自定义属性：可见Tab的数量
        getVisibleTabCount(context, attrs);
        //初始化画笔
        initializePaint();
    }


    /**
     * 在此方法中确定三角形的大小
     * 适用于知道宽高的图形，当图形变化的时候都会回调这个方法
     *
     * @param w 屏幕的宽
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mTriangleWidth = (int) (w / mTabVisibleCount * RADIO_TRIANGLE_WIDTH);
        mTriangleWidth = Math.min(mTriangleWidth, DIMENSION_TRIANGLE_WIDTH_MAX);//在从两个数中挑一个最小的
        mInitTranslationX = w / mTabVisibleCount / 2 - mTriangleWidth / 2;// w/3/2 是一个title一半的宽度

        initTriangle();

    }

    /**
     * 初始化三角形
     */
    private void initTriangle() {
        mTriangleHeight = mTriangleWidth / 2;//高度为宽度的1/2，也就是角度为45°

        mPath = new Path();
        mPath.moveTo(0, 0);//，然后往回向上走斜边，然后封闭图形
        mPath.lineTo(mTriangleWidth, 0);//先走一条横线
        mPath.lineTo(mTriangleWidth / 2, -mTriangleHeight);//然后往回向上走斜边 也就是到达三角形的顶点
        //因为三角形的左顶点是原点，Y轴向下是正的，所以要给高度一个符号，让他往上去走
        mPath.close();//完成闭合

    }


    /**
     * 绘制出三角形
     */
    @Override
    protected void dispatchDraw(Canvas canvas) {
        canvas.save();
        canvas.translate(mInitTranslationX + mTranslationX, getHeight() + 2);//平移
        canvas.drawPath(mPath, mPaint);

        canvas.restore();

        super.dispatchDraw(canvas);
    }

    /**
     * 获得屏幕的宽度
     *
     * @return
     */
    public int getScreenWidth() {
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();

        wm.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    /**
     * 当xml加载完以后会回调这个方法
     * 在里面设置了每个title的宽度和个数
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        int cCount = getChildCount();//子元素的个数
        if (cCount == 0) {
            return;
        }
        for (int i = 0; i < cCount; i++) {
            View view = getChildAt(i);
            LinearLayout.LayoutParams lp = (LayoutParams) view.getLayoutParams();
            lp.weight = 0;
            lp.width = getScreenWidth() / mTabVisibleCount;
            view.setLayoutParams(lp);
        }
        setItemClickEvent();//点击事件
    }


    /**
     * 指示器跟随手指进行滚动
     *
     * @param position
     * @param offset
     */
    public void scroll(int position, float offset) {
        int tabWidth = getWidth() / mTabVisibleCount;
        mTranslationX = (int) (tabWidth * (offset + position));

        //容器移动，在tab处于移动至最后一个时
        if (position >= (mTabVisibleCount - 2) && offset > 0 && getChildCount() > mTabVisibleCount) {
            if (mTabVisibleCount != 1) {
                this.scrollTo(
                        (position - (mTabVisibleCount - 2)) * tabWidth + (int) (tabWidth * offset), 0);
            } else {
                this.scrollTo(position * tabWidth + (int) (tabWidth * offset), 0);
            }


        }

        invalidate();        //重绘
    }

    /**
     * 重置TAB文本颜色
     */
    private void resetTextViewColor() {
        for (int i = 0; i < getChildCount(); i++) {
            View view =getChildAt(i);
            if (view instanceof TextView) {
                ((TextView) view).setTextColor(COLOR_TEXT_NORMAL);
            }
        }
    }

    /**
     * 高亮某个Tab的文本
     *
     * @param pos
     */
    private void highLightTextView(int pos) {
        resetTextViewColor();
        View view =getChildAt(pos);
        if (view instanceof TextView) {
            ((TextView) view).setTextColor(COLOR_TEXT_HIGHLIGHT);
        }
    }

    /**
     * 设置Tab的点击事件
     */
    private void setItemClickEvent() {
        int cCount = getChildCount();
        for (int i = 0; i < cCount; i++) {
            final int j = i;
            View view =getChildAt(i);
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    mViewPager.setCurrentItem(j);
                }
            });
        }
    }



    /***********************************************************************************************
     ******   ViewPagerIndicator(Context context, AttributeSet attrs) 调用方法      *****************
     **********************************************************************************************/
    /**
     * 获取自定义属性：可见Tab的数量
     *
     * @param context
     * @param attrs
     */
    private void getVisibleTabCount(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ViewPagerIndicator);
        mTabVisibleCount = ta.getInt(R.styleable.ViewPagerIndicator_visible_tab_count, COUNT_DEFAULT_TAB);

        if (mTabVisibleCount < 0) {
            mTabVisibleCount = COUNT_DEFAULT_TAB;
        }

        ta.recycle();
    }

    /**
     * 初始化画笔
     */
    private void initializePaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.parseColor("#ffffff"));
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setPathEffect(new CornerPathEffect(3));//CornerPathEffect顶端有一个圆角的效果
        //设置抗锯齿
        mPaint.setAntiAlias(true);
    }

    /***********************************************************************************************
     **************    设置set方法    ***************************************************************
     **********************************************************************************************/
    /**
     * 1.传进去字符串数组
     *
     * @param titles
     */
    public void setTabItemTitles(List<String> titles) {
        if (titles != null && titles.size() > 0) {
            this.removeAllViews();
            mTitles = titles;
            for (String title : titles) {
                addView(generaetTextView(title));
            }

            setItemClickEvent();//Tab点击事件
        }
    }

    /**
     * 2.设置可见的Tab数量
     *
     * @param count
     */
    public void setVisibleTabCount2(int count) {
        mTabVisibleCount = count;
    }

    /**
     * 3.根据 List<String> titles 创建TextView
     *
     * @param title
     * @return
     */
    private View generaetTextView(String title) {
        TextView tv = new TextView(getContext());
        LinearLayout.LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        lp.width = getScreenWidth() / mTabVisibleCount;
        tv.setText(title);
        tv.setGravity(Gravity.CENTER);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        tv.setTextColor(COLOR_TEXT_NORMAL);
        tv.setLayoutParams(lp);
        return tv;
    }

    /**
     * 1.设计接口，可以让用户回调setOnPageChangeListener方法，进行自定义设置
     */
    private ViewPager mViewPager;

    public interface PageOnchangeListener {
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels);

        public void onPageSelected(int position);

        public void onPageScrollStateChanged(int state);
    }

    public PageOnchangeListener mListener;

    public void setOnPageChangeListener(PageOnchangeListener listener) {
        this.mListener = listener;
    }

    /**
     * 2.设置关联的ViewPager
     *
     * @param viewPager
     * @param pos
     */
    public void setViewPager(ViewPager viewPager, int pos) {
        mViewPager = viewPager;
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                //从第一页移动到第二页，三角形的偏移量是
                //tabWidth*positionOffset + position*tabWidth
                scroll(position, positionOffset);

                //设计回调
                if (mListener != null) {
                    mListener.onPageScrolled(position, positionOffset, positionOffsetPixels);
                }
            }

            @Override
            public void onPageSelected(int position) {
                if (mListener != null) {
                    mListener.onPageSelected(position);

                }
                highLightTextView(position);//设置高亮

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (mListener != null) {
                    mListener.onPageScrollStateChanged(state);
                }
            }
        });

        mViewPager.setCurrentItem(pos);
        highLightTextView(pos);//设置高亮
    }


}
