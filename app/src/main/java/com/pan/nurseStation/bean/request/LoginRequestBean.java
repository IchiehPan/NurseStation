package com.pan.nurseStation.bean.request;

public class LoginRequestBean {
    /**
     * 用户工号
     */
    private String number;
    /**
     * 用户密码
     */
    private String password;

    public LoginRequestBean(String number, String password) {
        this.number = number;
        this.password = password;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
