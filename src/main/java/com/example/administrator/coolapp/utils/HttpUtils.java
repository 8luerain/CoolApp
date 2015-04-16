package com.example.administrator.coolapp.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2015/4/16.
 */
public  class HttpUtils {

    public static void getHttpResponseAsGET(final String httpURL,final HttpListener httpListener) {
        String response;
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection httpURLConnection = null;
                try {
                    URL url = new URL(httpURL);
                    httpURLConnection = (HttpURLConnection) url.getContent();
                    httpURLConnection.setRequestMethod("GET");
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    String tmp ;
                    StringBuilder response = new StringBuilder();
                    while((tmp = bufferedReader.readLine())!=null) {
                        response.append(tmp);
                    }
                    if (httpListener != null) {
                        httpListener.successed(response.toString());
                    }
                } catch (Exception e) {
                    if (httpListener != null) {
                        httpListener.error(e);
                    }
                }finally {
                    if (httpListener != null) {
                        httpURLConnection.disconnect();
                    }
                }

            }
        });
    }
}
