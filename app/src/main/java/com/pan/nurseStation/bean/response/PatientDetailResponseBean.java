/**
 * Copyright 2019 bejson.com
 */
package com.pan.nurseStation.bean.response;

import java.io.Serializable;
import java.util.Date;

/**
 * Auto-generated: 2019-03-26 10:26:37
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class PatientDetailResponseBean {

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

    public static class Data implements Serializable {

        private String name = "";
        private String bed_id = "";
        private String level = "";
        private String age = "";
        private String sex = "";
        private String hos_number = "";
        private String number = "";
        private String department_id = "";
        private String time = "";
        private String doctor = "";
        private String diagnose = "";
        private String cost_type = "";
        private String cost_state = "";
        private String notes = "";
        private String contact = "";
        private String relation = "";
        private String phone = "";
        private String department_name = "";

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setBed_id(String bed_id) {
            this.bed_id = bed_id;
        }

        public String getBed_id() {
            return bed_id;
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

        public void setDepartment_id(String department_id) {
            this.department_id = department_id;
        }

        public String getDepartment_id() {
            return department_id;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getTime() {
            return time;
        }

        public void setDoctor(String doctor) {
            this.doctor = doctor;
        }

        public String getDoctor() {
            return doctor;
        }

        public void setDiagnose(String diagnose) {
            this.diagnose = diagnose;
        }

        public String getDiagnose() {
            return diagnose;
        }

        public void setCost_type(String cost_type) {
            this.cost_type = cost_type;
        }

        public String getCost_type() {
            return cost_type;
        }

        public void setCost_state(String cost_state) {
            this.cost_state = cost_state;
        }

        public String getCost_state() {
            return cost_state;
        }

        public void setNotes(String notes) {
            this.notes = notes;
        }

        public String getNotes() {
            return notes;
        }

        public void setContact(String contact) {
            this.contact = contact;
        }

        public String getContact() {
            return contact;
        }

        public void setRelation(String relation) {
            this.relation = relation;
        }

        public String getRelation() {
            return relation;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getPhone() {
            return phone;
        }

        public void setDepartment_name(String department_name) {
            this.department_name = department_name;
        }

        public String getDepartment_name() {
            return department_name;
        }

    }

}