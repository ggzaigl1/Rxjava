package com.jypj.yuexiu.http;

import android.util.Log;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public abstract class ResponseHandler extends JsonHttpResponseHandler {
    protected int code = 1;

    @Override
    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
        Log.e("Tag", response.toString());
        if (response.has("code")) {
            try {
                code = response.getInt("code");
                switch (code) {
                    case 0:
                        onSuccess(response.toString());
                        break;
                    default:
                        onFailure(response.getString("msg"));
                        break;
                }
            } catch (JSONException e) {
                onFailure("无法连接到网络,请检查网络设置！");
                e.printStackTrace();
            }
        }
        super.onSuccess(statusCode, headers, response);
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
        onFailure("无法连接到网络,请检查网络设置！");
        super.onFailure(statusCode, headers, throwable, errorResponse);
    }

    public abstract void onSuccess(String response);

    public abstract void onFailure(String message);

}