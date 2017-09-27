package com.feifang.oms.controller.param;

/**
 * 用户登录参数
 */

public class UserLoginParam {

	private String userCode;//用户名
	
	private String userVerify;//密码
	
	public String getUserVerify() {
		return userVerify;
	}

	public void setUserVerify(String userVerify) {
		this.userVerify = userVerify;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
}
