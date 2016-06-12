package com.fangyi.mobilesafe.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.SystemClock;
import android.util.Log;

import com.fangyi.mobilesafe.db.BlackNumberDBOpenHelper;
import com.fangyi.mobilesafe.domain.BlackNumberInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FANGYI on 2016/6/12.
 */

public class BlackNumberDao {
    private BlackNumberDBOpenHelper mHelper;

    public BlackNumberDao(Context context) {
        mHelper = new BlackNumberDBOpenHelper(context);
    }

    /**
     * 黑名单的添加
     * @param number 黑名单号码
     * @param mode 拦截模式，0电话拦截，1短信拦截，2全部拦截
     */
    public void add(String number, String mode) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("number", number);
        values.put("mode", mode);
        db.insert("blacknumber", null, values);
        db.close();
    }

    /**
     * 删除一条黑名单数据
     * @param number 要删除的黑名单号码
     */
    public void delete(String number) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        db.delete("blacknumber", "number=?", new String[]{number});
        db.close();
    }

    /**
     * 修改一条数据
     * @param number 要修改的电话号码
     * @param newmode 新的拦截模式
     */
    public void update(String number, String newmode) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("mode", newmode);
        db.update("blacknumber", values, "number=?", new String[]{number});
        db.close();
    }

    /**
     * 查询黑名单是否存在
     * @param number 要查询的电话号码
     * @return
     */
    public boolean queryNumber(String number) {
        boolean result = false;
        SQLiteDatabase db = mHelper.getWritableDatabase();
        Cursor cursor = db.query("blacknumber", null, "number=?", new String[]{number}, null, null, null);
        if (cursor.moveToNext()) {
            result = true;
        }
        db.close();
        return false;
    }

    /**
     * 查询黑名单的拦截模式
     * @param number 要查询的电话号码
     * @return
     */
    public String queryMode(String number) {
        String result = "2";
        SQLiteDatabase db = mHelper.getWritableDatabase();
        Cursor cursor = db.query("blacknumber", new String[]{"mode"}, "number=?", new String[]{number}, null, null, null);
        if (cursor.moveToNext()) {
            result = cursor.getString(0);
        }
        db.close();
        return result;
    }

    /**
     * 所有的黑名单信息
     * @return
     */
    public List<BlackNumberInfo> queryAll() {
        Log.e("3.执行耗时操作","显示");
        SystemClock.sleep(3000);//模拟耗时操作
        List<BlackNumberInfo> result = new ArrayList<>();
        SQLiteDatabase db = mHelper.getWritableDatabase();
        Cursor cursor = db.query("blacknumber", new String[]{"number", "mode"}, null, null, null, null, null);
        while (cursor.moveToNext()) {
            BlackNumberInfo info = new BlackNumberInfo();
            String number = cursor.getString(0);
            info.setNumber(number);
            String mode = cursor.getString(1);
            info.setMode(mode);

            result.add(info);
        }
        db.close();
        return result;
    }
}
