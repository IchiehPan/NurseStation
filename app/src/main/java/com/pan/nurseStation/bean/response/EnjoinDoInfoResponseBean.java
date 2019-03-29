/**
 * Copyright 2019 bejson.com
 */
package com.pan.nurseStation.bean.response;

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
    private List<Data> data;
    private String msg;

    public void setRet(int ret) {
        this.ret = ret;
    }

    public int getRet() {
        return ret;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public List<Data> getData() {
        return data;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    private int status;
    private List<List> list;

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setList(List<List> list) {
        this.list = list;
    }

    public List<List> getList() {
        return list;
    }


    public static class Data {

        private int status;
        private List<MedicalOrder> list;

        public void setStatus(int status) {
            this.status = status;
        }

        public int getStatus() {
            return status;
        }

        public void setList(List<MedicalOrder> list) {
            this.list = list;
        }

        public List<MedicalOrder> getList() {
            return list;
        }

        public static class MedicalOrder {

            /**
             * 主键ID
             */
            private String id;
            /**
             * 医嘱名称
             */
            private String title;
            /**
             * 组合号
             */
            private String combination;
            /**
             * 用量
             */
            private String dosage;
            /**
             * 用法
             */
            private String use_method;
            /**
             * 数量
             */
            private String amount;
            /**
             * 状态，0：待核药，1：待执行，2：执行中，3：已完成
             */
            private String status;

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

            public void setStatus(String status) {
                this.status = status;
            }

            public String getStatus() {
                return status;
            }
        }
    }

}