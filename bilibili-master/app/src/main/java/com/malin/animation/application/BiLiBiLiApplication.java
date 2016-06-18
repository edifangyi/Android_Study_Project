package com.malin.animation.application;

import android.app.Application;

import com.malin.animation.BuildConfig;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.crashreport.CrashReport;

import butterknife.ButterKnife;

/**
 * @author malin.myemail@163.com
 * @date 16-5-21 19:31
 */
public class BiLiBiLiApplication extends Application {
    private static final String TAG = BiLiBiLiApplication.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        CrashReport.initCrashReport(getApplicationContext(), "900031274", false);//BugLy
        if (BuildConfig.DEBUG) {
            Logger.init(TAG).logLevel(LogLevel.FULL);
            Bugly.enable = true;
            CrashReport.closeCrashReport();
        } else {
            Logger.init(TAG).logLevel(LogLevel.NONE);
        }
        ButterKnife.setDebug(BuildConfig.DEBUG);
    }
}
