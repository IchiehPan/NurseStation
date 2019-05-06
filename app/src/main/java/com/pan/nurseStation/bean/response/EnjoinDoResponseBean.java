package com.pan.nurseStation.bean.response;

import java.util.ArrayList;
import java.util.List;

public class EnjoinDoResponseBean {
    private int ret;
    private List<String> data = new ArrayList<>();
    private String msg = "";

    public void setRet(int ret) {
        this.ret = ret;
    }

    public int getRet() {
        return ret;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

    public List<String> getData() {
        return data;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
