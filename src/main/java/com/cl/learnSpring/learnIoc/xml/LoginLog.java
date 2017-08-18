package com.cl.learnSpring.learnIoc.xml;

import java.util.Date;

/**
 * Created by cl on 2017/8/18.
 */
public class LoginLog {
    private String ip;
    private int userId;
    private Date loginDate;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    @Override
    public String toString() {
        return "LoginLog{" +
                "ip='" + ip + '\'' +
                ", loginDate=" + loginDate +
                '}';
    }
}
