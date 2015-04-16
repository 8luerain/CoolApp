package com.example.administrator.coolapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrator.coolapp.model.City;
import com.example.administrator.coolapp.model.County;
import com.example.administrator.coolapp.model.Province;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/4/15.
 */
public class CoolAppDB {
    private static final String DB_NAME = "CoolApp.db";
    private static final int DB_VERSION = 1;
    private static final int TABLE_PROVINCE = 10;
    private static final int TABLE_CITY = 11;
    private static final int TABLE_COUNTY = 12;
    private SQLiteDatabase sqLiteDatabase;
    private static CoolAppDB coolAppDB;
    private MyDBHelper myDBHelper;

    private CoolAppDB(Context context) {
        myDBHelper = new MyDBHelper(context, DB_NAME, null, DB_VERSION);
    }

    public static synchronized CoolAppDB getInstance(Context context) {
        if (coolAppDB != null) {
            return coolAppDB;
        } else {
            return new CoolAppDB(context);
        }
    }

    public List<Province> loadProvinces() {
        List<Province> provinces = new ArrayList<Province>();
        SQLiteDatabase readableDatabase = myDBHelper.getReadableDatabase();
        Cursor province = readableDatabase.query("province", null, null, null, null, null, null, null);
        if (province.moveToNext()) {
            do {
                String provinceCode = province.getString(province.getColumnIndex("provinceCode"));
                String provinceName = province.getString(province.getColumnIndex("provinceName"));
                Province province1 = new Province();
                province1.setProvinceName(provinceName);
                province1.setProvinceCode(provinceCode);
                provinces.add(province1);
            } while (province.moveToNext());
        }
        return provinces;

    }

    public List<City> loadCity(int provinceId) {
        List<City> cities = new ArrayList<City>();
        SQLiteDatabase readableDatabase = myDBHelper.getReadableDatabase();
        Cursor query = readableDatabase.query("city", null, "provinceId=?", new String[]{String.valueOf(provinceId)}, null, null, null, null);
        if (query.moveToNext()) {
            do {
                String cityCode = query.getString(query.getColumnIndex("cityCode"));
                String cityName = query.getString(query.getColumnIndex("cityName"));
                City city = new City();
                city.setProvinceId(provinceId);
                city.setCityName(cityName);
                city.setCityCode(cityCode);
                cities.add(city);
            } while (query.moveToNext());
        }
        return cities;
    }

    public List<County> loadCounty(int cityId) {
        List<County> counties = new ArrayList<County>();
        SQLiteDatabase readableDatabase = myDBHelper.getReadableDatabase();
        Cursor query = readableDatabase.query("county", null, "cityId=?", new String[]{String.valueOf(cityId)}, null, null, null, null);
        if (query.moveToNext()) {
            do {
                String countyCode = query.getString(query.getColumnIndex("countyCode"));
                String countyName = query.getString(query.getColumnIndex("countyName"));
                County county = new County();
                county.setCountyName(countyName);
                county.setCountyCode(countyCode);
                counties.add(county);
            } while (query.moveToNext());
        }
        return counties;

    }

    public <T> void insert(T modelName) {
        SQLiteDatabase writableDatabase = myDBHelper.getWritableDatabase();
        if (modelName.getClass().equals(Province.class)) {
            Province province = (Province) modelName;
            String provinceCode = province.getProvinceCode();
            String provinceName = province.getProvinceName();
            ContentValues contentValues = new ContentValues();
            contentValues.put("provinceCode", provinceCode);
            contentValues.put("provinceName", provinceName);
            writableDatabase.insert("province", null, contentValues);
        }
        if (modelName.getClass().equals(City.class)) {
            City city = (City) modelName;
            String cityCode = city.getCityCode();
            String cityName = city.getCityName();
            int provinceId = city.getProvinceId();
            ContentValues contentValues = new ContentValues();
            contentValues.put("cityCode", cityCode);
            contentValues.put("cityName", cityName);
            contentValues.put("provinceId", provinceId);
            writableDatabase.insert("city", null, contentValues);
        }
        if (modelName.getClass().equals(County.class)) {
            County county = (County) modelName;
            int cityId = county.getCityId();
            String countyCode = county.getCountyCode();
            String countyName = county.getCountyName();
            ContentValues contentValues = new ContentValues();
            contentValues.put("cityId", cityId);
            contentValues.put("countyCode", countyCode);
            contentValues.put("countyName", countyName);
            writableDatabase.insert("county", null, contentValues);
        }
        return;
    }

}
