package com.jypj.yuexiu.model;

import com.jypj.yuexiu.http.HttpResult;

import java.util.List;

/**
 * Created by Administrator on 2016/10/14.
 * OA来文接口的model
 */

public class FiledealM extends HttpResult {

    private List<DataEntity> data;

    public List<DataEntity> getData() {
        return data;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public static class DataEntity {
        private String title;
        private MsgContentEntity msgContent;
        private String createDate;
        private String href;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public MsgContentEntity getMsgContent() {
            return msgContent;
        }

        public void setMsgContent(MsgContentEntity msgContent) {
            this.msgContent = msgContent;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }

        public static class MsgContentEntity {
            private List<String> attach;
            /**
             * value : 转发：2016年正高级职称申报表及材料报送要求
             * key : 文件标题
             */

            private List<FileInfoEntity> fileInfo;


            public List<String> getAttach() {
                return attach;
            }

            public void setAttach(List<String> attach) {
                this.attach = attach;
            }

            public List<FileInfoEntity> getFileInfo() {
                return fileInfo;
            }

            public void setFileInfo(List<FileInfoEntity> fileInfo) {
                this.fileInfo = fileInfo;
            }


            public static class FileInfoEntity {
                private String value;
                private String key;

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }

                public String getKey() {
                    return key;
                }

                public void setKey(String key) {
                    this.key = key;
                }
            }
        }
    }
}
