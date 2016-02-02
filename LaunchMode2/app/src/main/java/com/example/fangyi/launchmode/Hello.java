package com.example.fangyi.launchmode;

import android.content.Context;
import android.content.pm.PackageManager;

/**
 * Created by FANGYI on 2016/2/2.
 */
public class Hello {
    public static final String PLERISSION_SAY_HELLO="com.example.fangyi.launchmode.permission.SAY_HELLO";
    //需要检查一个权限， 定义一个静态的字符串，他的值是 我们的包名，.permission.SAY_HELLO

    public static void sayHello(Context context) {
        int checkResult = context.checkCallingOrSelfPermission(PLERISSION_SAY_HELLO);

        if(checkResult!= PackageManager.PERMISSION_GRANTED){
            throw new SecurityException("执行sayHello方法需要有：com.example.fangyi.launchmode.permission.SAY_HELLO 权限");
        }

        System.out.println("Hello ABCD");//有权限，就会输出
    }
}
