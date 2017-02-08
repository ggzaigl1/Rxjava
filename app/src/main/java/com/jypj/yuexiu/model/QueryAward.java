package com.jypj.yuexiu.model;

import com.jypj.yuexiu.http.HttpResult;

import java.util.List;

/**
 * Created by Administrator on 2016/10/18 0018.
 * //获奖信息 award接口model
 */

public class QueryAward extends HttpResult {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String id;
        private String awardInfo;
        private String awardDate;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAwardInfo() {
            return awardInfo;
        }

        public void setAwardInfo(String awardInfo) {
            this.awardInfo = awardInfo;
        }

        public String getAwardDate() {
            return awardDate;
        }

        public void setAwardDate(String awardDate) {
            this.awardDate = awardDate;
        }
    }
}
