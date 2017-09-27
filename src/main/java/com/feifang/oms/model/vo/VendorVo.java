package com.feifang.oms.model.vo;

import java.io.Serializable;

/**
 * @author Vincent
 * @date 2017/8/19
 */
public class VendorVo implements Serializable {

    private static final long serialVersionUID = -66443756212985910L;
    private Integer id;
    private String name;                //供应商姓名
    private String phone;               //手机
    private String address;             //地址
    private String type;                //行业类型
    private String storeName;           //店铺名称
    private String accountName;         //银行账户名称
    private String cardNo;              //银行卡号
    private String bankName;            //银行名称

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

}
