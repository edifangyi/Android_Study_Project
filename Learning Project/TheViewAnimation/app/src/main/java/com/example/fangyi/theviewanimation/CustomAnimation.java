package com.example.fangyi.theviewanimation;

import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Created by FANGYI on 2016/2/12.
 */
public class CustomAnimation extends Animation {
    //需要了解目标组件的宽高
    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        //float interpolatedTime ：补间时间，动画在执行完毕以后是1，在执行的过程当中applyTransformation()在不断的执行，interpolatedTime在0-1之间
        //Transformation t ：来对目标组件状态进行修改

//        t.setAlpha(interpolatedTime);//设置透明度0-1之间的透明变化效果
//        t.getMatrix().setTranslate(200, 200);//直接瞬间跳到指定的位置,然后一秒回来
//        t.getMatrix().setTranslate(200*interpolatedTime, 200);//Y轴立即跳到指定的坐标，X轴慢慢的挪过去，然后一秒回来
//        t.getMatrix().setTranslate(200*interpolatedTime, 200*interpolatedTime);//两轴慢慢的挪到指定的坐标，然后一秒回来


        //我们来设置一个摇头的动画效果,往X轴里面放一个周期，括弧里面第一个*是摇晃速度，外面第二个*是摇晃幅度
        t.getMatrix().setTranslate((float) (Math.sin(interpolatedTime*10)*50), 0);

        super.applyTransformation(interpolatedTime, t);
    }
}
