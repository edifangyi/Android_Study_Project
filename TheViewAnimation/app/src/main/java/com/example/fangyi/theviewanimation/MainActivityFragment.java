package com.example.fangyi.theviewanimation;

import android.media.session.PlaybackState;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private RotateAnimation rotateAnimation;
    private TranslateAnimation translateAnimation;
    private ScaleAnimation scaleAnimation;
    private AnimationSet animationSet;
    private AlphaAnimation alphaAnimation;
    private CustomAnimation customAnimation;



    public MainActivityFragment() {
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        /*
            根目录列表动画,效果：慢慢缩放出来
         */
        LinearLayout rootView = (LinearLayout) inflater.inflate(R.layout.fragment_main, container, false);


        scaleAnimation = new ScaleAnimation(0, 1, 0, 1);
        scaleAnimation.setDuration(500);
        LayoutAnimationController lac = new LayoutAnimationController(scaleAnimation, 0.5f);//第二个参数是，假设为0.5f，当第一个按钮动画执行到0.5f时，第二个动画开始加载执行动画
        lac.setOrder(LayoutAnimationController.ORDER_NORMAL);//默认三个属性：ORDER_NORMA从上到下,LORDER_RANDOM随机,ORDER_REVERSE从下往上
        rootView.setLayoutAnimation(lac);

        /*
            为布局添加动画效果
         */










        /*
            呈现另一个Fragment
        */
        rootView.findViewById(R.id.btnShowAnotherFragment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.fragment, new AnotherFragment())
                        .commit();
            }
        });




        /*
            View动画-透明动画效果
         */
        rootView.findViewById(R.id.btnAnimMe).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                AlphaAnimation btnAnimMe = new AlphaAnimation(0, 1);//透明度0到1的动画效果
//                btnAnimMe.setDuration(1000);//设置动画时间长度
//                v.startAnimation(btnAnimMe);
                v.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.animation_transparent));//xml文件配置
            }
        });




        /*
            View动画-旋转动画效果
         */
//        rotateAnimation = new RotateAnimation(0, 360);//默认左上角旋转，0到360度
//        rotateAnimation = new RotateAnimation(0, 360, 100, 100);//指明在哪个点旋转
//        rotateAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);//相对自身的中心点
//        rotateAnimation.setDuration(1000);//旋转一秒

        rootView.findViewById(R.id.btnRotateMe).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                v.startAnimation(rotateAnimation);
                v.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.animation_rotate));
            }
        });




        /*
            View动画-移动动画效果
         */
//        translateAnimation = new TranslateAnimation(0, 200 , 0, 200);//当前位置，往右移动200，往下移动200
//        translateAnimation.setDuration(1000);

        rootView.findViewById(R.id.btnTranslateMe).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                v.startAnimation(translateAnimation);
                v.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.animation_translate));//xml文件配置
            }
        });




        /*
            View动画-缩放动画效果
         */
//        scaleAnimation = new ScaleAnimation(0, 1, 0, 1);//默认左上角， 按比例从 0 到 1 缩放
//        scaleAnimation = new ScaleAnimation(0, 1, 0, 1, 100, 50);//后面两个是缩放中心点，从缩放点，按比例从 0 到 1 缩放
//        scaleAnimation = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);//相对于自身中心点进行缩放
//        scaleAnimation.setDuration(1000);

        rootView.findViewById(R.id.btnScaleMe).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                v.startAnimation(scaleAnimation);
                v.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.animation_scale));//xml文件配置
            }
        });




        /*
            View动画-动画效果混合
         */
//        animationSet = new AnimationSet(true);//是否使用动画补间
//        animationSet.setDuration(1000);
//
//        alphaAnimation = new AlphaAnimation(0, 1);//透明度0到1的动画效果
//        alphaAnimation.setDuration(1000);//设置动画时间长度
//        animationSet.addAnimation(alphaAnimation);
//
//        translateAnimation = new TranslateAnimation(0, 200 , 0, 200);//当前位置，往右移动200，往下移动200
//        translateAnimation.setDuration(1000);
//        animationSet.addAnimation(translateAnimation);

        rootView.findViewById(R.id.btnTwoAnimaion).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                v.startAnimation(animationSet);
                Animation a = AnimationUtils.loadAnimation(getActivity(), R.anim.animation);

                a.setAnimationListener(new Animation.AnimationListener() {
                    @Override//动画开始
                    public void onAnimationStart(Animation animation) {
//                        Toast.makeText(getActivity(), "Animation End", Toast.LENGTH_SHORT).show();
                    }

                    @Override//动画重复
                    public void onAnimationEnd(Animation animation) {

                    }

                    @Override//动画结束
                    public void onAnimationRepeat(Animation animation) {
                        Toast.makeText(getActivity(), "Animation End", Toast.LENGTH_SHORT).show();
                    }
                });

                v.startAnimation(a);//xml文件配置
            }
        });



        /*
            自定义动画效果
         */

        customAnimation = new CustomAnimation();
        customAnimation.setDuration(1000);
        rootView.findViewById(R.id.btnCustomAnimaion).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(customAnimation);
            }
        });





        return rootView;
    }
}
