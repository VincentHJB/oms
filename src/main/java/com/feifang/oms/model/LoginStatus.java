package com.feifang.oms.model;

/**
 * 登录状态
 * @author Vincent
 * @date 2017/5/24
 */
public enum LoginStatus {
    Pass("1", "验证通过"),
    Failure("0", "验证失败");

    private String code;
    private String value;

    LoginStatus(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
