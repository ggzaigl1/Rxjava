package com.jypj.yuexiu.http;

import android.util.Log;

import com.loopj.android.http.RequestParams;

public class MainHttp extends HttpBase {

    public MainHttp() {
        super();
    }

    //登录
    public void login(String loginname, String password, ResponseHandler responsHandeler) {
        RequestParams params = new RequestParams();
        params.put("loginname", loginname);
        params.put("password", password);
        mClient.get(HOST_STRING + "login?", params, responsHandeler);
        Log.e("Tag", HOST_STRING + "login" + params);
    }

    //改变角色
    public void changeRole(String roleType, ResponseHandler responsHandeler) {
        RequestParams params = new RequestParams();
        params.put("roleType", roleType);
        //  String params="roleType="+roleType;
        mClient.get(HOST_STRING + "changeRole?", params, responsHandeler);

    }

    //个人档案
    public void quMyPro(ResponseHandler responsHandeler) {
        RequestParams params = new RequestParams();
        mClient.post(HOST_STRING + "quMyPro?", params, responsHandeler);
        Log.e("Tag", HOST_STRING + "quMyPro" + params);
    }

    //通知公告
    public void notice(ResponseHandler responsHandeler) {
        RequestParams params = new RequestParams();
        mClient.post(HOST_STRING + "query/notice?", params, responsHandeler);
        Log.e("Tag", HOST_STRING + "query/notice" + params);
    }

    //越秀快讯
    public void flash(ResponseHandler responsHandeler) {
        RequestParams params = new RequestParams();
        mClient.post(HOST_STRING + "query/flash?", params, responsHandeler);
        Log.e("Tag", HOST_STRING + "query/flash" + params);
    }

    //招考信息
    public void exam(ResponseHandler responsHandeler) {
        RequestParams params = new RequestParams();
        mClient.post(HOST_STRING + "query/exam?", params, responsHandeler);
        Log.e("Tag", HOST_STRING + "query/exam" + params);
    }


    //空间消息
    public void queryMsg(ResponseHandler responsHandeler) {
        RequestParams params = new RequestParams();
        mClient.post(HOST_STRING + "queryMsg?", params, responsHandeler);
        Log.e("Tag", HOST_STRING + "queryMsg" + params);
    }

    //OA来文
    public void filedeal(ResponseHandler responsHandeler) {
        RequestParams params = new RequestParams();
        mClient.post(HOST_STRING + "queryOA/filedeal?", params, responsHandeler);
        Log.e("Tag", HOST_STRING + "queryOA/filedeal" + params);
    }

    //OA待处理事件
    public void dealing(ResponseHandler responsHandeler) {
        RequestParams params = new RequestParams();
        mClient.post(HOST_STRING + "queryOA/dealing?", params, responsHandeler);
        Log.e("Tag", HOST_STRING + "queryOA/dealing" + params);
    }

    //OA文件通知
    public void fileNotice(ResponseHandler responsHandeler) {
        RequestParams params = new RequestParams();
        mClient.post(HOST_STRING + "queryOA/fileNotice?", params, responsHandeler);
        Log.e("Tag", HOST_STRING + "queryOA/fileNotice" + params);
    }


    //获奖信息
    public void award(ResponseHandler responsHandeler) {
        RequestParams params = new RequestParams();
        mClient.post(HOST_STRING + "query/award?", params, responsHandeler);
        Log.e("Tag", HOST_STRING + "query/award" + params);
    }

    //获奖信息详情
    public void award(String id, ResponseHandler responsHandeler) {
        mClient.get(HOST_STRING + "query/award/" + id, null, responsHandeler);
        Log.e("Tag", HOST_STRING + "query/award" + null);
    }


}