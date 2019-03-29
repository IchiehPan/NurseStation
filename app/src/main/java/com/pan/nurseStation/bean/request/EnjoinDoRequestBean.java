package com.pan.nurseStation.bean.request;

import java.util.Map;

public class EnjoinDoRequestBean {
    /**
     * 数组，例["id号"=>"状态号"]
     */
    private Map<String, String> ids;

    public void setIds(Map<String, String> ids) {
        this.ids = ids;
    }

    public Map<String, String> getIds() {
        return ids;
    }
}
