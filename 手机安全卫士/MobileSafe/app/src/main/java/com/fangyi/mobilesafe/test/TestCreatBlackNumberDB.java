package com.fangyi.mobilesafe.test;

import android.test.AndroidTestCase;

import com.fangyi.mobilesafe.db.BlackNumberDBOpenHelper;
import com.fangyi.mobilesafe.db.dao.BlackNumberDao;

import java.util.Random;

/**
 * Created by FANGYI on 2016/6/12.
 */

public class TestCreatBlackNumberDB extends AndroidTestCase {
    public void testCreatBlackNumberDB() {
        BlackNumberDBOpenHelper helper = new BlackNumberDBOpenHelper(getContext());
        helper.getWritableDatabase();

    }

    public void add() {
        BlackNumberDao dao = new BlackNumberDao(getContext());
        //13512345600 - 13512345699
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            dao.add("1351234560" + i, String.valueOf(random.nextInt(3)));
        }

    }

    public void delete() {
        BlackNumberDao dao = new BlackNumberDao(getContext());
        dao.delete("119");
    }

    public void update() {
        BlackNumberDao dao = new BlackNumberDao(getContext());
        dao.update("119", "1");
    }

    public void queryNumber() {
        BlackNumberDao dao = new BlackNumberDao(getContext());
        boolean result = dao.queryNumber("119");
        assertEquals(true, result);


    }

    public void queryMode() {
        BlackNumberDao dao = new BlackNumberDao(getContext());
        String mode = dao.queryMode("119");
    }
}
