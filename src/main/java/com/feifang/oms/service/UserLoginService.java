package com.feifang.oms.service;

import com.feifang.oms.dao.entity.User;
import com.feifang.oms.dao.rep.UserRep;
import com.feifang.oms.utils.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户登录验证
 * @author : Vincent
 * @version : 1.0.0
 * @date : 2017/5/23
 */
@Service
public class UserLoginService {
    public static Logger logger = LoggerFactory.getLogger(UserLoginService.class);

    @Autowired
    private UserRep userRep;

    /**
     * 验证用户名及密码
     * @param userCode
     * @param password
     * @return
     */
    public boolean loginValidPassword(String userCode, String password) {
           User username =  userRep.findByUsername(userCode);
           String pwd = username.getPassword();
           if(userCode.isEmpty()){
               logger.error("用户名不能为空！");//
               return false;

           }
           else if(password == null){
               logger.error("密码不能为空， 请重试！");
               return false;
           }
           else if(userCode != null && !((MD5Util.md5(password)).equals(pwd))){
               logger.error("用户名或密码错误， 请重试！");
               return false;
           }
           else {
               logger.info("验证通过！");
               return true;
           }
    }

    //生成缓存
    public String loginSession(HttpServletRequest request, String userCode, HttpServletResponse response) {
        User username =  userRep.findByUsername(userCode);
        Integer sessionId = username.getId();
        request.getSession().setAttribute("userName", userCode);
        request.getSession().setAttribute("userId", sessionId);
        Cookie cookie = new Cookie("userName", userCode);
        cookie.setMaxAge(60 * 60 * 24 * 15);// 设置为15天
        cookie.setPath("/");
        response.addCookie(cookie);
        return String.valueOf(response);
    }

    //删除缓存
    public void deleteSession(HttpServletRequest request) {
        String uname = (String) request.getSession().getAttribute("userName");
        String uid = (String) request.getSession().getAttribute("userId");
        if (null != uname && null != uid) {
            request.getSession().removeAttribute("userName");
            request.getSession().removeAttribute("userId");
        }
    }

}
