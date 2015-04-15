package com.example.administrator.coolapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Administrator on 2015/4/15.
 */
public class CoolAppDB {
    private static final String DB_NAME = "CoolApp.db";
    private static final int DB_VERSION= 1;
    private SQLiteDatabase sqLiteDatabase;
    private static CoolAppDB coolAppDB;

    private CoolAppDB(Context context) {
        MyDBHelper myDBHelper = new MyDBHelper(context,DB_NAME, null,DB_VERSION);
    }
    public static synchronized CoolAppDB getInstance(Context context) {
        if (coolAppDB != null) {
            return coolAppDB;
        } else {
            return new CoolAppDB(context);
        }
    }


}
