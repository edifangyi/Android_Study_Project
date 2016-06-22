package com.fangyi.mobilesafe.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by FANGYI on 2016/6/5.
 */
public class NumberAddressQueryDao {
    private static String path = "/data/data/com.fangyi.mobilesafe/files/NumeberAddressQuery.db";
    /**
     * 号码归属地的查询
     * @param number 要查询的电话号码
     * @return  号码的归属地
     */
    public static String getAddess(String number) {
        String address =number;
//        String path = "file:///android_asset/NumeberAddressQuery.db";//SQLiteDatabase对这个路径不是别
//        WebView 加载图片
        SQLiteDatabase db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);

        //手机号码 - 个数11位, 13,14,15,16,17,18 正则表达式
        //^1[45678]\d{9}$
        if (number.matches("^1[345678]\\d{9}$")) {
            Cursor cursor = db.rawQuery("SELECT location FROM data2 WHERE id = (SELECT outkey from data1 where id = ?)", new String[]{number.substring(0, 7)});
            while (cursor.moveToLast()) {
                String location =  cursor.getString(0);
                address = location;
            }
            cursor.close();
            db.close();
        } else {
            //110, 119, 5554, 长途
            switch (number.length()) {
                case 3://110,119,120
                    address = "匪警电话";
                    break;
                case 4://5556,5554
                    address = "模拟器";
                    break;
                case 5://10086
                    address = "客服电话";
                    break;
                default:
                    if (number != null && number.startsWith("0") && number.length() >= 10) {
                        //010988777777
                        Cursor cursor = db.rawQuery("SELECT location FROM data2 WHERE area =? ", new String[]{number.substring(1, 3)});
                        while (cursor.moveToLast()) {
                            String location =  cursor.getString(0);
                            //上海电信 --> 上海
                            address = location.substring(0, location.length()-2);
                        }

                        cursor = db.rawQuery("SELECT location FROM data2 WHERE area =? ", new String[]{number.substring(1, 4)});
                        while (cursor.moveToLast()) {
                            String location =  cursor.getString(0);
                            //上海电信 --> 上海
                            address = location.substring(0, location.length()-2);
                        }

                        cursor.close();
                        db.close();
                    }
            }
        }

        return address;
    }
}
