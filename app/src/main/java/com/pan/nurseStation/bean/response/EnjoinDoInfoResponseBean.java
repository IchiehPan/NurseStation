/**
 * Copyright 2019 bejson.com
 */
package com.pan.nurseStation.bean.response;

import java.util.ArrayList;
import java.util.List;

/**
 * Auto-generated: 2019-03-29 11:50:14
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class EnjoinDoInfoResponseBean {

    /**
     * 操作码，0表示成功，其他表示失败
     */
    private int ret;
    private Data data = new Data();
    private String msg = "";

    public void setRet(int ret) {
        this.ret = ret;
    }

    public int getRet() {
        return ret;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Data getData() {
        return data;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public static class Data {

        private String reg_no = "";
        private List<Group_list> group_list = new ArrayList<>();

        public void setReg_no(String reg_no) {
            this.reg_no = reg_no;
        }

        public String getReg_no() {
            return reg_no;
        }

        public void setGroup_list(List<Group_list> group_list) {
            this.group_list = group_list;
        }

        public List<Group_list> getGroup_list() {
            return group_list;
        }
    }

    public static class Group_list {

        private int pt_status;
        private List<List<MedicalOrder>> list = new ArrayList<>();

        public void setPt_status(int pt_status) {
            this.pt_status = pt_status;
        }

        public int getPt_status() {
            return pt_status;
        }

        public void setList(List<List<MedicalOrder>> list) {
            this.list = list;
        }

        public List<List<MedicalOrder>> getList() {
            return list;
        }
    }

    public static class MedicalOrder {

        /**
         * 主键ID
         */
        private String id = "";
        /**
         * 医嘱名称
         */
        private String title = "";
        /**
         * 组合号
         */
        private String combination = "";
        /**
         * 用量
         */
        private String dosage = "";
        /**
         * 用法
         */
        private String use_method = "";
        /**
         * 数量
         */
        private String amount = "";
        /**
         * 状态，0：待核药，1：待执行，2：执行中，3：已完成
         */
        private int pt_status;

        public void setId(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }

        public void setCombination(String combination) {
            this.combination = combination;
        }

        public String getCombination() {
            return combination;
        }

        public void setDosage(String dosage) {
            this.dosage = dosage;
        }

        public String getDosage() {
            return dosage;
        }

        public void setUse_method(String use_method) {
            this.use_method = use_method;
        }

        public String getUse_method() {
            return use_method;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getAmount() {
            return amount;
        }

        public void setPt_status(int pt_status) {
            this.pt_status = pt_status;
        }

        public int getPt_status() {
            return pt_status;
        }
    }

}