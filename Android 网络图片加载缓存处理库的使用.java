Android 中图片处理存在的难点：

    ·OOM 内存溢出
    ·图片尺寸和缩略图处理的平衡
    ·网络图片的加载与缓存机制

Universal-ImageLoader 框架
    ·支持本地图片和网络图片的多线程异步加载和缓存处理
    ·个性化的配置自己项目的 ImageLoader(缓存空间大小，同时开启的线程数，当图片加载失败默认显示图片，缓存路径)
    ·图片加载过程的监听回调
    ·自动对加载的图片针对当前剩余内存进行剪裁优化，防止OOM
    ·较好的控制图片的加载过程，例如暂停图片加载，重新开始加载图片

	缺点：没有对本地文件压缩处理的相关API方法以及默认都是Src模式设置图片，没有针对Background属性开放API

Picasso 框架
    ·加载网络或本地图片并自动缓存处理
    ·链式调用
    ·图形转换操作，如变换大小，旋转等，提供了接口来让用户可以自定义转换操作
    ·在 Adapter 中回收和取消当前的下载功能

        图片异步加载：
            Picasso.with(context).load("http://ww2.sinaimg.cn/large/006gWxMegw1f2g5o6gz7lj30gt0mejuc.jpg").into(imageView);
        
        图片转换：转换图片以适应布局大小并减少内存占用
            Picasso.with(context).load().resize(50, 50).centerCrop().into(imageView);
        
        Adapter 中的下载：Adapter 的重用会被自动检测到，Picasso 会取消上次的加载

        空白或者错误占位图片设置方法及本地资源文件的加载方法

        Picasso 采用链式调用加载和处理图片方式(方法嵌套方法)

        除了加载网络图片， Picasso 还支持加载 Resources, assets, files, content, providers 中的本地资源文件

总结：
    ·都有搞笑的网络图片下载和缓存性能
    ·Universal-ImageLoader 功能多，灵活使用配置
    ·Picasso 使用复杂的图片压缩转换来尽可能的减少内存消耗
    ·在 Adapter 中需要取消已经不在视野范围的 ImageView 图片资源的加载，否则会导致图片错位，Picasso 已经解决了





/**
 

 */



<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.example.fangyi.imageloaderdemo">
   	
    <!--网络权限-->
    <uses-permission android:name="android.permission.INTERNET"></uses-permission>
    <!--读SD卡权限-->
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"></uses-permission>
    <!--写SD卡权限-->
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"></uses-permission>

    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:supportsRtl="true"
        android:theme="@style/AppTheme"
        android:name=".MyApplication">
        <activity android:name=".MainActivity">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>

</manifest>


/**
 

 */




package com.example.fangyi.imageloaderdemo;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

public class MainActivity extends AppCompatActivity {
    private ImageLoader loader;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loader = ImageLoader.getInstance();
        imageView = (ImageView) this.findViewById(R.id.id_image);
        //加载本地图片
        String url="file:///"+"本地路径";

        //加载网络图片
        loader.displayImage("http://ww2.sinaimg.cn/large/006gWxMegw1f2g5o6gz7lj30gt0mejuc.jpg", imageView);
        //监听效果
        loader.displayImage("http://ww2.sinaimg.cn/large/006gWxMegw1f2g5o6gz7lj30gt0mejuc.jpg", imageView, new ImageLoadingListener() {
            /**
             * 加载开始
             * @param s
             * @param view
             */
            @Override
            public void onLoadingStarted(String s, View view) {
                Toast.makeText(MainActivity.this, "加载完毕", Toast.LENGTH_LONG).show();
            }

            /**
             * 加载失败
             * @param s
             * @param view
             * @param failReason
             */
            @Override
            public void onLoadingFailed(String s, View view, FailReason failReason) {

            }

            /**
             * 加载完成
             * @param s
             * @param view
             * @param bitmap
             */
            @Override
            public void onLoadingComplete(String s, View view, Bitmap bitmap) {

            }

            /**
             * 加载取消
             * @param s
             * @param view
             */
            @Override
            public void onLoadingCancelled(String s, View view) {

            }
        });
    }
}


/**
 

 */




package com.example.fangyi.imageloaderdemo;

import android.app.Application;
import android.graphics.Bitmap;
import android.os.Environment;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

import java.io.File;

/**
 * Created by FANGYI on 2016/3/31.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                this)
                .memoryCacheExtraOptions(480, 800)
                // max width, max height，即保存的每个缓存文件的最大长宽
                .discCacheExtraOptions(480, 800, null)
                // Can slow ImageLoader, use it carefully (Better don't use
                // it)/设置缓存的详细信息，最好不要设置这个
                .threadPoolSize(3)
                // 线程池内加载的数量
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024))
                // You can pass your own memory cache
                // implementation/你可以通过自己的内存缓存实现
                .memoryCacheSize(2 * 1024 * 1024)
                .discCacheSize(50 * 1024 * 1024)
                //硬盘设置的最大的缓存数，这里就是50mb
                .discCacheFileNameGenerator(new Md5FileNameGenerator())
                // 将保存的时候的URI名称用MD5 加密
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .discCacheFileCount(100)
                // 缓存的文件数量
                .discCache(
                        new UnlimitedDiscCache(new File(Environment
                                .getExternalStorageDirectory()
                                + "/myApp/imgCache")))
                // 自定义缓存路径
                .defaultDisplayImageOptions(getDisplayOptions())
                .imageDownloader(
                        new BaseImageDownloader(this, 5 * 1000, 30 * 1000))//前面是连接时间，后边是超时时间
                .writeDebugLogs() // Remove for release app
                .build();// 开始构建
        ImageLoader.getInstance().init(config);//将写好的配置设置进去
    }

    private DisplayImageOptions getDisplayOptions() {
        DisplayImageOptions options;
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.ic_launcher) // 设置图片在下载期间显示的图片
                .showImageForEmptyUri(R.mipmap.ic_launcher)// 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.mipmap.ic_launcher) // 设置图片加载/解码过程中错误时候显示的图片
                .cacheInMemory(true)// 设置下载的图片是否缓存在内存中
                .cacheOnDisc(true)// 设置下载的图片是否缓存在SD卡中
                .considerExifParams(true) // 是否考虑JPEG图像EXIF参数（旋转，翻转）
                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)// 设置图片以如何的编码方式显示
                .bitmapConfig(Bitmap.Config.RGB_565)// 设置图片的解码类型//
                // .delayBeforeLoading(int delayInMillis)//int
                // delayInMillis为你设置的下载前的延迟时间
                // 设置图片加入缓存前，对bitmap进行设置
                // .preProcessor(BitmapProcessor preProcessor)
                .resetViewBeforeLoading(true)// 设置图片在下载前是否重置，复位
                .displayer(new RoundedBitmapDisplayer(20))// 是否设置为圆角，弧度为多少
                .displayer(new FadeInBitmapDisplayer(100))// 是否图片加载好后渐入的动画时间
                .build();// 构建完成
        return options;
    }

}

/**
 

 */
    <ImageView
        android:id="@+id/id_image_prcasso"
        android:layout_width="fill_parent"
        android:layout_height="wrap_content" />

/**
 

 */

    private ImageView imageViewP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageViewP = (ImageView) findViewById(R.id.id_image_prcasso);
        //加载图片
        Picasso.with(this).load("http://ww3.sinaimg.cn/large/005Di1mtgw1f1wr02wwfej30sg0lcn24.jpg").into(imageViewP);
        
        //调整图片
        Picasso.with(this).load("http://ww3.sinaimg.cn/large/005Di1mtgw1f1wr02wwfej30sg0lcn24.jpg").resize(100, 100).into(imageViewP);
        
        //图片错误后，显示的图片
        Picasso.with(this).load("http://ww3.sinaimg.cn/large/005Di1mtgw1f1wr02wwfej30sg0lcn24.jpg").error(R.mipmap.ic_launcher).into(imageViewP);
