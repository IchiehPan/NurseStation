package com.pan.nurseStation.bean.request;

import java.util.List;

public class EnjoinDoRequestBean {
    /**
     * 数组，例["id号"=>"状态号"]
     */
    private List<String> ids;

    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    public List<String> getIds() {
        return ids;
    }
}
