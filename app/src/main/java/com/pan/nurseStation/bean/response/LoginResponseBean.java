package com.pan.nurseStation.bean.response;

public class LoginResponseBean {
    /**
     * 操作码，0表示成功，1表示失败
     */
    private int ret;
    /**
     * 提示信息
     */
    private String msg = "";

    private Data data = new Data();

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data {

        private String number = "";
        private String department_id = "";
        private String department_name = "";

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

        public void setDepartment_name(String department_name) {
            this.department_name = department_name;
        }

        public String getDepartment_name() {
            return department_name;
        }

    }
}
