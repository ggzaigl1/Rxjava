package com.jypj.yuexiu.model;

import com.jypj.yuexiu.http.HttpResult;

import java.util.List;

/**
 * Created by Administrator on 2016/10/13.
 */

public class FlashM extends HttpResult {

    private List<DataEntity> data;

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
