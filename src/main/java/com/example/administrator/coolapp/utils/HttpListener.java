package com.example.administrator.coolapp.utils;

/**
 * Created by Administrator on 2015/4/16.
 */
public interface HttpListener {
    public void successed(String response);

    public void error(Exception error);
}
