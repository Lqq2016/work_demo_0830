package com.lqq.test.work_demo_0830.Pojo;

/**
 * Created by lqq on 2016/9/2.
 */
public class UserDetail {

    private int id;
    private String username;
    private String password;
    private String nickname;
    private String telephone;
    private String picurl;

    public UserDetail() {
    }

    public UserDetail(int id, String username, String password, String nickname, String telephone, String picurl) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.telephone = telephone;
        this.picurl = picurl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }

    @Override
    public String toString() {
        return "UserDetail{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", telephone='" + telephone + '\'' +
                ", picurl='" + picurl + '\'' +
                '}';
    }
}
