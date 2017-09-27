package com.feifang.oms.model;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回json对象
 * @author Vincent
 * @date 2017/6/8
 */
public class ResultData {
    private boolean success;//返回状态
    private String msg;//返回消息
    private Map<String, Object> content = new HashMap<>();//返回数据

    public ResultData() {
        this.success = true;
        this.msg = "操作成功！";
    }

    public void helpSetContent(String key, Object object){
        content.put(key, object);
    };

    public boolean isSuccess() {
        return success;
    }
    public boolean setSuccess(boolean success) {
        return this.success = success;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public Map<String, Object> getContent() {
        return content;
    }
    public void setContent(Map<String, Object> content) {
        this.content = content;
    }


}