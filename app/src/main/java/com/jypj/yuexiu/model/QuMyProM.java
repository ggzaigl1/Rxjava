package com.jypj.yuexiu.model;

import com.jypj.yuexiu.http.HttpResult;

import java.util.List;

/**
 * Created by Administrator on 2016/10/14 0014.
 * //个人档案 quMyPro接口model
 */
public class QuMyProM extends HttpResult {

    private List<DataEntity> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public static class DataEntity extends Myrecorditem {

    }
}
