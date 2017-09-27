package com.feifang.oms.dao.entity;

import javax.persistence.*;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * 系统用户信息实体
 * @author Vincent
 * @date 2017/5/22
 */
@Entity
@Table(name = "user")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;// 用户id
    private String username;// 用户名
    private String password; // 密码;
    private String phone;    //手机号
    private Integer status;//用户状态： 0 锁定, 1 未锁定

    public User() {
    }

    public User(String username, String password, String phone, Integer status) {
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer state) {
        this.status = state;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
}
