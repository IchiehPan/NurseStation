/**
 * Copyright 2019 bejson.com
 */
package com.pan.nurseStation.bean.response;

import java.util.List;

/**
 * Auto-generated: 2019-03-26 9:4:32
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class BedListResponseBean {

    private int ret;
    private Data data;
    private String msg;

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

    class Data {

        private int page;
        private String records;
        private int pages;
        private String patients;
        private String remain_bed;
        private List<PatientInfo> list;

        public void setPage(int page) {
            this.page = page;
        }

        public int getPage() {
            return page;
        }

        public void setRecords(String records) {
            this.records = records;
        }

        public String getRecords() {
            return records;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public int getPages() {
            return pages;
        }

        public void setPatients(String patients) {
            this.patients = patients;
        }

        public String getPatients() {
            return patients;
        }

        public void setRemain_bed(String remain_bed) {
            this.remain_bed = remain_bed;
        }

        public String getRemain_bed() {
            return remain_bed;
        }

        public void setList(List<PatientInfo> list) {
            this.list = list;
        }

        public List<PatientInfo> getList() {
            return list;
        }

    }

    class PatientInfo {

        private String department_id;
        private String bed_id;
        private String name;
        private String level;
        private String age;
        private String sex;
        private String hos_number;
        private String number;

        public void setDepartment_id(String department_id) {
            this.department_id = department_id;
        }

        public String getDepartment_id() {
            return department_id;
        }

        public void setBed_id(String bed_id) {
            this.bed_id = bed_id;
        }

        public String getBed_id() {
            return bed_id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getLevel() {
            return level;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getAge() {
            return age;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getSex() {
            return sex;
        }

        public void setHos_number(String hos_number) {
            this.hos_number = hos_number;
        }

        public String getHos_number() {
            return hos_number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getNumber() {
            return number;
        }

    }


}