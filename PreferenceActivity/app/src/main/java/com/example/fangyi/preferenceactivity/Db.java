package com.example.fangyi.preferenceactivity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by FANGYI on 2016/3/2.
 */
public class Db extends SQLiteOpenHelper {

    public Db(Context context) {
        super(context, "db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建表
        db.execSQL("CREATE TABLE user(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT DEFAULT \"\"," +
                "sex TEXT DEFAULT \"\")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
