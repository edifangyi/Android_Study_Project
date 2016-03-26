package com.example.fangyi.ai_cat.text;

import android.test.AndroidTestCase;
import android.util.Log;

import com.example.fangyi.ai_cat.utils.HttpUtils;

/**
 * Created by FANGYI on 2016/3/25.
 */
public class TestHttpUtils extends AndroidTestCase {
    public void testSendInfo() {
        String res = HttpUtils.doGet("给我讲个笑话");
        Log.e("TAG",res);
        res = HttpUtils.doGet("给我讲个鬼故事");
        Log.e("TAG",res);
        res = HttpUtils.doGet("你好");
        Log.e("TAG",res);
        res = HttpUtils.doGet("你真美");
        Log.e("TAG",res);
    }
}
