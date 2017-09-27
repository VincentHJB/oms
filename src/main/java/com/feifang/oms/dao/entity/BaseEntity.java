package com.feifang.oms.dao.entity;


import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.io.Serializable;
import java.util.Date;

/**
 * User : Jent
 * Date : 2016/11/15
 * Version : V1.0
 * Describe : 基类
 */
@MappedSuperclass
public abstract  class BaseEntity implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 75955796629306167L;

	//创建时间
    @Column(name="created_at",insertable = true, updatable = false)
    protected Date createdAt;

    //更新时间
    @Column(name="updated_at",insertable = true, updatable = true)
    protected Date updatedAt;

    @PrePersist
    public void onCreate(){
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    @PreUpdate
    public void onPersist(){
        this.updatedAt = new Date();
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
