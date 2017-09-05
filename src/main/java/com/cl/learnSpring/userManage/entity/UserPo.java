package com.cl.learnSpring.userManage.entity;


import java.io.Serializable;
import java.util.Date;

/**
 * 记录用户相关信息的实体类
 */
public class UserPo implements Serializable {
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

    public UserPo(Integer id, String username) {
        this.id = id;
        this.username = username;
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
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", pswd='" + pswd + '\'' +
                ", createTime=" + createTime +
                ", lastLoginTime=" + lastLoginTime +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserPo userPo = (UserPo) o;

        if (id != null ? !id.equals(userPo.id) : userPo.id != null) return false;
        if (nickname != null ? !nickname.equals(userPo.nickname) : userPo.nickname != null) return false;
        if (username != null ? !username.equals(userPo.username) : userPo.username != null) return false;
        if (email != null ? !email.equals(userPo.email) : userPo.email != null) return false;
        if (pswd != null ? !pswd.equals(userPo.pswd) : userPo.pswd != null) return false;
        if (createTime != null ? !createTime.equals(userPo.createTime) : userPo.createTime != null) return false;
        if (lastLoginTime != null ? !lastLoginTime.equals(userPo.lastLoginTime) : userPo.lastLoginTime != null)
            return false;
        return status != null ? status.equals(userPo.status) : userPo.status == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (nickname != null ? nickname.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (pswd != null ? pswd.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (lastLoginTime != null ? lastLoginTime.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    public static void main(String[] args) {
        UserPo userPo1 = new UserPo("111", "111");
        UserPo userPo2 = new UserPo("111", "111");
        if (userPo1 == userPo1) {
            System.out.println("userPo1 == userPo1");
        }
        if (userPo1.equals(userPo2)) {
            System.out.println("userPo1.equals(userPo2)");

        }
    }

    public boolean isVipMember(String nickname) {
        return "tom".equals(nickname);
    }
}