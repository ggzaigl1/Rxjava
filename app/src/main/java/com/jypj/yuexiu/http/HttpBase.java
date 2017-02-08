package com.jypj.yuexiu.http;

import com.loopj.android.http.AsyncHttpClient;

public class HttpBase {

    public static final String HOST_STRING = "http://121.8.163.48/portal/api/";

    public static String token = "";
    protected AsyncHttpClient mClient;

    public HttpBase() {
        mClient = new AsyncHttpClient();
        mClient.setTimeout(15000);
        mClient.addHeader("token", token);
    }
}