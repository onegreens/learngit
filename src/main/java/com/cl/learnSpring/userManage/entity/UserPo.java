package com.cl.learnSpring.userManage.entity;


import java.io.Serializable;
import java.util.Date;

/**
 * 记录用户相关信息的实体类
 */
public class UserPo implements Serializable{
    //0:禁止登录
    public static final Integer _0 = new Integer(2);
    //1:有效
    public static final Integer _1 = new Integer(1);

    private Integer id;
    /**
     * 昵称
     */
    private String nickname;

    /**
     * 昵称
     */
    private String username;

    /**
     * 邮箱 | 登录帐号
     */
    private String email;
    /**
     * 密码
     */
    private transient String pswd;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 最后登录时间
     */
    private Date lastLoginTime;
    /**
     * 1:有效，0:禁止登录
     */
    private Integer status;

    public UserPo() {
    }

    public UserPo(String username, String pswd) {
        this.username = username;
        this.pswd = pswd;
    }

    public UserPo(String nickname, String email, Integer userId, Integer statuts) {
        this.nickname = nickname;
        this.email = email;
        this.id = userId;
        this.status = statuts;
    }

    public UserPo(String nickname, String username, String email, String password, Integer status) {
        this.nickname = nickname;
        this.username = username;
        this.email = email;
        this.pswd = password;
        this.status = status;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPswd() {
        return pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    @Override
    public String toString() {
        return "UserPo{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", pswd='" + pswd + '\'' +
                ", createTime=" + createTime +
                ", lastLoginTime=" + lastLoginTime +
                ", status=" + status +
                '}';
    }
}