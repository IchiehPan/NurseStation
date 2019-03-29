package com.pan.nurseStation.bean.request;

import java.util.List;

public class EnjoinDoRequestBean {
    /**
     * 数组，例["id号"=>"状态号"]
     */
    private List<Data> ids;

    public void setIds(List<Data> ids) {
        this.ids = ids;
    }

    public List<Data> getIds() {
        return ids;
    }

    public static class Data {
        private String id;
        private String status;

        public void setId(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getStatus() {
            return status;
        }
    }
}
