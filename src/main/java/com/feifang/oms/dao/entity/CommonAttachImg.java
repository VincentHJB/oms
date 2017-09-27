package com.feifang.oms.dao.entity;


import javax.persistence.*;
import java.io.Serializable;

/**
 * @author : Jent
 * @version : 1.0.0
 * @date : 2016/12/7
 */
@Entity
@Table(name = "common_attach_img")
public class CommonAttachImg extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String fileName;//文件名
    private String realPath;//机器真实目录
    private String virtualPath;//虚拟路径
    private String domain;//域名
    private String url;//路径：http://domain/virtual_path
    private String module;//模块如：用户中心、票务中心、订单中心等
    private Integer orderId;//订单ID
    private String postfix;
    private Long size;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getRealPath() {
        return realPath;
    }

    public void setRealPath(String realPath) {
        this.realPath = realPath;
    }

    public String getVirtualPath() {
        return virtualPath;
    }

    public void setVirtualPath(String virtualPath) {
        this.virtualPath = virtualPath;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getPostfix() {
        return postfix;
    }

    public void setPostfix(String postfix) {
        this.postfix = postfix;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }
}
