package com.feifang.oms.controller.request;

import com.alibaba.fastjson.JSONObject;
import com.feifang.oms.controller.param.UserLoginParam;
import com.feifang.oms.service.UserLoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户登录/注销接口
 */
@Controller
@RequestMapping
public class LoginController {
	public static Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	UserLoginService userLoginService;

	//后端登录
	@RequestMapping(value = "/admin", method = RequestMethod.POST)
	public String adminLogin(UserLoginParam userLoginParam, HttpServletRequest request, ModelMap map,
							 HttpServletResponse response)throws Exception {
		logger.info("登录接口-->登录参数: " + JSONObject.toJSONString(userLoginParam));

        String username = userLoginParam.getUserCode();
		boolean userLogin = userLoginService.loginValidPassword(username, userLoginParam.getUserVerify());
		if(!userLogin){
            logger.error("登录失败, 请重试！");
            return "login/login";
		}
        logger.info("登录成功");
        userLoginService.loginSession(request, username, response);
        return "index/index";
	}

	//退出系统
    @RequestMapping(value = "/logout", produces = {"application/json;charset=UTF-8" })
    public String cleanToken(HttpServletRequest request,HttpServletResponse response) {
        logger.info("注销账户-->退出系统");
        Cookie[] cookies = request.getCookies();
        for (int i = 0; i < cookies.length; i++) {
            if (cookies[i].getName().equalsIgnoreCase("userName")) {
                cookies[i].setValue(null);
                cookies[i].setMaxAge(0);//设置为0为立即删除该Cookie
                response.addCookie(cookies[i]);
            }
        }
        userLoginService.deleteSession(request);
        return "login/login";
    }



}
