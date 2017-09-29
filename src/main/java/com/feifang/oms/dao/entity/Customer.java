package com.feifang.oms.dao.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * 客户表
 * @author Vincent
 * @date 2017/5/22
 */
@Entity
@Table(name = "customer")
public class Customer extends BaseEntity {

    @NotNull
    private String name;//客户名称
    private String nationality;//国籍

    @NotNull
    //@Column(length = 11)
    private String customerNo;//客户编号(唯一，手动输入)

    @Column(length = 11)
    private String phone;//手机号码

    public Customer() {
    }

    public Customer(String name, String nationality, String customerNo, String phone) {
        this.name = name;
        this.nationality = nationality;
        this.customerNo = customerNo;
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }
}
