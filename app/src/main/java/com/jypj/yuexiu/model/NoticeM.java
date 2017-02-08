package com.jypj.yuexiu.model;

import com.jypj.yuexiu.http.HttpResult;

import java.util.List;

/**
 * Created by Administrator on 2016/10/13.
 * /通知公告 notice接口的model
 */

public class NoticeM extends HttpResult {

    /**
     * title : 越秀区教育局 越秀区城市管理局关于公布2016年越秀区幼儿园区级垃圾分类教育 示范基地名单的通知
     * date : 2016-09-28
     * href : http://www.gzyxedu.net/yxweb/resources/upload/page/201609/20160928145238_13571.htm
     */

    private List<DataEntity> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public static class DataEntity {
        private String title;
        private String date;
        private String href;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }
    }
}
