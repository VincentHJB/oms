package com.feifang.oms.dao.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * 订单图片表
 * @author Vincent
 * @date 2017/5/22
 */
@Entity
@Table(name = "order_image")
public class OrderImage extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer uid;//用户id
    private Integer oid;//订单ID
    private String imageUrl;//图片地址

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;


    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public OrderImage() {
    }

    public OrderImage(Integer uid, String imageUrl) {
        this.uid = uid;
        this.imageUrl = imageUrl;
    }
}
