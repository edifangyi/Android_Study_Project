一般的侧滑的实现
ViewGroup Menu + Context
onTouchEvent
MOVE:ViewGroup的lefMargin
UP:根据显示菜单的宽度，决定将其隐藏或者显示
	1.Scroller
	2.LeftMargin + Thread


继承： HorizontalScrollView

自定义ViewGroup
	1.onMeasure
	决定内部view（子View）的宽和高，以及自己的宽和高
	2.onLayout
	决定子View放置的位置
	3.onTouchEvent
	判断手指动作，决定效果

自定义属性：
允许用户自定义设置菜单离屏幕右边的边距
1.书写xml文件 valuse/attr.xml




////////////////////////////////////////////////////////////////////////////////////////////////////////

1.left_menu.xml
		
		//居中
        android:layout_centerInParent="true"
		//背景
    	android:background="@drawable/img_frame_background"
    	//右侧自定义ViewGroup(18:39)
        android:layout_toRightOf="@id/id_img1"
        android:layout_centerVertical="true"


<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="@drawable/img_frame_background">
    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_centerInParent="true"
        android:orientation="vertical">

        <RelativeLayout
            android:layout_width="fill_parent"
            android:layout_height="wrap_content">
            <ImageView
                android:id="@+id/id_img1"
                android:layout_width="50dp"
                android:layout_height="50dp"
                android:layout_marginLeft="20dp"
                android:layout_marginTop="20dp"
                android:layout_centerVertical="true"
                android:src="@drawable/img_1"/>
            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:textSize="20sp"
                android:textColor="#ffffff"
                android:text="第1个Item"
                android:layout_toRightOf="@+id/id_img1"
                android:layout_centerVertical="true"
                android:layout_marginLeft="20dp"/>
        </RelativeLayout>
        <RelativeLayout
            android:layout_width="fill_parent"
            android:layout_height="wrap_content">
            <ImageView
                android:id="@+id/id_img2"
                android:layout_width="50dp"
                android:layout_height="50dp"
                android:layout_marginLeft="20dp"
                android:layout_marginTop="20dp"
                android:layout_centerVertical="true"
                android:src="@drawable/img_2"/>
            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:textSize="20sp"
                android:textColor="#ffffff"
                android:text="第2个Item"
                android:layout_toRightOf="@+id/id_img2"
                android:layout_centerVertical="true"
                android:layout_marginLeft="20dp"/>
        </RelativeLayout>
        <RelativeLayout
            android:layout_width="fill_parent"
            android:layout_height="wrap_content">
            <ImageView
                android:id="@+id/id_img3"
                android:layout_width="50dp"
                android:layout_height="50dp"
                android:layout_marginLeft="20dp"
                android:layout_marginTop="20dp"
                android:layout_centerVertical="true"
                android:src="@drawable/img_3"/>
            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:textSize="20sp"
                android:textColor="#ffffff"
                android:text="第3个Item"
                android:layout_toRightOf="@+id/id_img3"
                android:layout_centerVertical="true"
                android:layout_marginLeft="20dp"/>
        </RelativeLayout>
        <RelativeLayout
            android:layout_width="fill_parent"
            android:layout_height="wrap_content">
            <ImageView
                android:id="@+id/id_img4"
                android:layout_width="50dp"
                android:layout_height="50dp"
                android:layout_marginLeft="20dp"
                android:layout_marginTop="20dp"
                android:layout_centerVertical="true"
                android:src="@drawable/img_4"/>
            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:textSize="20sp"
                android:textColor="#ffffff"
                android:text="第4个Item"
                android:layout_toRightOf="@+id/id_img4"
                android:layout_centerVertical="true"
                android:layout_marginLeft="20dp"/>
        </RelativeLayout>
        <RelativeLayout
            android:layout_width="fill_parent"
            android:layout_height="wrap_content">
            <ImageView
                android:id="@+id/id_img5"
                android:layout_width="50dp"
                android:layout_height="50dp"
                android:layout_marginLeft="20dp"
                android:layout_marginTop="20dp"
                android:layout_centerVertical="true"
                android:src="@drawable/img_5"/>
            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:textSize="20sp"
                android:textColor="#ffffff"
                android:text="第5个Item"
                android:layout_toRightOf="@+id/id_img5"
                android:layout_centerVertical="true"
                android:layout_marginLeft="20dp"/>
        </RelativeLayout>

    </LinearLayout>


</RelativeLayout>




////////////////////////////////////////////////////////////////////////////////////////////////////////

2.activity_main
//水平滚动条
<HorizontalScrollView
//菜单
<include layout="@layout/left_menu"/>



<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <HorizontalScrollView
        android:layout_width="match_parent"
        android:layout_height="match_parent">
        <LinearLayout
            android:layout_width="wrap_content"
            android:layout_height="match_parent"
            android:orientation="horizontal">
            <include layout="@layout/left_menu"/>
            <LinearLayout
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:background="@drawable/qq">

            </LinearLayout>

        </LinearLayout>

    </HorizontalScrollView>

</RelativeLayout>



//更改 HorizontalScrollView 为 com.example.fangyi.myqq50.View.SlidingMenu
//
//自定义ViewGroup


<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <com.example.fangyi.myqq50.View.SlidingMenu
        android:layout_width="match_parent"
        android:layout_height="match_parent">
        <LinearLayout
            android:layout_width="wrap_content"
            android:layout_height="match_parent"
            android:orientation="horizontal">
            <include layout="@layout/left_menu"/>
            <LinearLayout
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:background="@drawable/qq">

            </LinearLayout>

        </LinearLayout>

    </com.example.fangyi.myqq50.View.SlidingMenu>

</RelativeLayout>




////////////////////////////////////////////////////////////////////////////////////////////////////////

4.SlidingMenu.java


package com.example.fangyi.myqq50.View;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

/**
 * Created by FANGYI on 2016/3/19.
 */
public class SlidingMenu extends HorizontalScrollView{

    private LinearLayout mWapper;
    private ViewGroup mMenu;
    private ViewGroup mContent;

    //屏幕的宽度
    private int mScreenWidth;
    //菜单的宽度
    private int mMenuWidth;
    //菜单右侧与屏幕右侧边距的距离50dp
    private int mMenuRightPadding = 50;

    private boolean once;

    /**
     * 未使用自定义属性时，调用
     * @param context
     * @param attrs
     */
    public SlidingMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        //获取屏幕宽度
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(outMetrics);
        mScreenWidth = outMetrics.widthPixels;

        //把dp转换为px
        mMenuRightPadding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50,
                context.getResources().getDisplayMetrics());
    }

    /**
     * 设置子View的宽和高
     * 设置自己的宽和高
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (!once) {
            mWapper = (LinearLayout) getChildAt(0);
            mMenu = (ViewGroup) mWapper.getChildAt(0);
            mContent = (ViewGroup) mWapper.getChildAt(1);

            mMenuWidth = mMenu.getLayoutParams().width = mScreenWidth - mMenuRightPadding;
            mContent.getLayoutParams().width = mScreenWidth;

            once = true;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * 通过设置偏移量
     * 将Menu隐藏在左边界外，Content显示在屏幕内
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (changed) {
            //x正，滚动条右移动内容左移动
            //scrollTo将菜单完全隐藏,瞬间完成动作
            this.scrollTo(mMenuWidth, 0);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        //通过action我们可以判断用户手指是按下滑动还是抬起
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_UP:
                //隐藏在左边的宽度
                //scrollX内容区域左侧多出来的宽度
                int scrollX = getScrollX();

                if (scrollX >= mMenuWidth/2) {
                    //与scrollTo比，有动画,非瞬间
                    this.smoothScrollTo(mMenuWidth, 0);
                } else {
                    this.smoothScrollTo(0, 0);
                }
                return true;
        }
        return super.onTouchEvent(ev);
    }
}


////////////////////////////////////////////////////////////////////////////////////////////////////////
///

5.
AndroidManifest.xml

<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.example.fangyi.myqq50">

    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:supportsRtl="true"
        android:theme="@style/Theme.AppCompat.NoActionBar">
        <activity android:name=".MainActivity">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>

</manifest>








///////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////











