package com.fangyi.mobilesafe.utils;

import android.app.ActivityManager;
import android.content.Context;

import com.jaredrummler.android.processes.AndroidProcesses;
import com.jaredrummler.android.processes.models.AndroidAppProcess;

import java.util.List;


/**
 * Created by FANGYI on 2016/6/16.
 */

public class SysTemInfoUtils {

    /**
     * 得到当前手机运行进程的总数
     * @return
     */
    public  static  int getRunningProcessCount() {
        List<AndroidAppProcess> processes = AndroidProcesses.getRunningAppProcesses();
        return processes.size();
    }

    /**
     * 得到可用内存
     * @param context
     * @return
     */
    public static long getAvailRam(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo outInfo = new ActivityManager.MemoryInfo();
        am.getMemoryInfo(outInfo);
        return outInfo.availMem;
    }

    /**
     * 得到总的内存
     * @param context
     * @return
     */
    public static long getTotalRam(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo outInfo = new ActivityManager.MemoryInfo();
        am.getMemoryInfo(outInfo);
        return outInfo.totalMem;
    }
}
