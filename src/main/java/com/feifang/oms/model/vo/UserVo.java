package com.feifang.oms.model.vo;

import java.io.Serializable;

/**
 * @author Vincent
 * @date 2017/6/14
 */
public class UserVo implements Serializable {

    private static final long serialVersionUID = -66443756212985910L;
    private Integer id;
    private String username;
    private String password;
    private String phone;
    private Integer status;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
