package com.feifang.oms.model.vo;

import java.io.Serializable;

/**
 * @author Vincent
 * @date 2017/6/14
 */
public class CustomerVo implements Serializable {

    private static final long serialVersionUID = -66443756212985910L;
    private Integer id;
    private String name;
    private String customerNo;
    private String phone;
    private String nationality;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
}
