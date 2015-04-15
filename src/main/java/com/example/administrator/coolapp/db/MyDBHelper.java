package com.example.administrator.coolapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2015/4/15.
 */
public class MyDBHelper extends SQLiteOpenHelper {
    private static final String CREATE_TABLE_PROVINCE = "create table province(id integer key autoincrement,province_name text,province_code text)";
    private static final String CREATE_TABLE_CITY = "create table city(id integer key autoincrement,city_name text,city_code text,province_id integer)";
    private static final String CREATE_TABLE_COUNTY = "create table country(id integer key autoincrement,country_name text,country_code text,city_id integer)";

    public MyDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CITY);
        db.execSQL(CREATE_TABLE_COUNTY);
        db.execSQL(CREATE_TABLE_PROVINCE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
