package com.feifang.oms.model.vo;

import com.feifang.oms.dao.entity.UserLogin;

import java.util.Date;

/**
 * @author Vincent
 * @date 2017/6/14
 */
public class UserLoginVo {

//    private String accessToken;//接入token
//    private String refreshToken;//刷新token
    private Integer expiresIn;//有效时间
    private Date loginAt;
    private Integer loginId;
    private String avatar;
    private Integer bid;
//    private Integer hotelId;
//    private String hotelName;
//    private String hotelUrl;






//    public String getHotelUrl() {
//        return hotelUrl;
//    }
//
//    public void setHotelUrl(String hotelUrl) {
//        this.hotelUrl = hotelUrl;
//    }
//
//    public String getHotelName() {
//        return hotelName;
//    }
//
//    public void setHotelName(String hotelName) {
//        this.hotelName = hotelName;
//    }
//
//    public Integer getHotelId() {
//        return hotelId;
//    }
//
//    public void setHotelId(Integer hotelId) {
//        this.hotelId = hotelId;
//    }

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getLoginId() {
        return loginId;
    }

    public void setLoginId(Integer loginId) {
        this.loginId = loginId;
    }



    public UserLoginVo(){

    }

    public UserLoginVo(UserLogin userLogin){
//        this.accessToken = userLogin.getAccessToken();
//        this.refreshToken = userLogin.getRefreshToken();
        //this.expiresIn = userLogin.getExpiresIn();
        this.loginAt = userLogin.getLoginAt();
        this.bid = userLogin.getBid();
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
    public Integer getExpiresIn() {
        return expiresIn;
    }
    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }
    public Date getLoginAt() {
        return loginAt;
    }
    public void setLoginAt(Date loginAt) {
        this.loginAt = loginAt;
    }


}
