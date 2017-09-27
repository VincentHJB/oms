package com.feifang.oms.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Vincent
 * @date 2017/6/16
 */

@Entity
@Table(name="user_login")
public class UserLogin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(unique=true)
    private Integer bid;

//    private String accessToken;//接入token
//    private String refreshToken;
//    private Integer expiresIn;//过期时间

    private String loginIp;

    @Temporal(TemporalType.TIMESTAMP)
    private Date loginAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @JsonIgnore
    @OneToOne(cascade=CascadeType.REFRESH, optional = false)
    @JoinColumn(name = "bid",referencedColumnName="id", unique = true,insertable = false, updatable = false)
    private User user;



    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getBid() {
        return bid;
    }
    public void setBid(Integer bid) {
        this.bid = bid;
    }
//    public String getAccessToken() {
//        return accessToken;
//    }
//    public void setAccessToken(String accessToken) {
//        this.accessToken = accessToken;
//    }
//    public String getRefreshToken() {
//        return refreshToken;
//    }
//    public void setRefreshToken(String refreshToken) {
//        this.refreshToken = refreshToken;
//    }
//    public Integer getExpiresIn() {
//        return expiresIn;
//    }
//    public void setExpiresIn(Integer expiresIn) {
//        this.expiresIn = expiresIn;
//    }
    public String getLoginIp() {
        return loginIp;
    }
    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }
    public Date getLoginAt() {
        return loginAt;
    }
    public void setLoginAt(Date loginAt) {
        this.loginAt = loginAt;
    }
    public Date getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    public Date getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

}

