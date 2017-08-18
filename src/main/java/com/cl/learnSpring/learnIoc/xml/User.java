package com.cl.learnSpring.learnIoc.xml;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cl on 2017/8/18.
 */
public class User {
    private Integer userId;
    private String userName;
    private List<LoginLog> logs;

    public User() {
        this.logs = new ArrayList<LoginLog>();
    }

    public List<LoginLog> getLoginLogList() {
        return logs;
    }

    public void setLoginLogList(List<LoginLog> loginLogList) {
        this.logs = loginLogList;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void addLoginLog(LoginLog loginLog){
        logs.add(loginLog);
    }
}
