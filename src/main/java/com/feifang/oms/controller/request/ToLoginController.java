package com.feifang.oms.controller.request;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 跳转到登录页面
 * @author : Vincent
 * @version : 1.0.0
 * @date : 2017/5/23
 */
@Controller
public class ToLoginController {
    public static Logger logger = LoggerFactory.getLogger(ToLoginController.class);

    @RequestMapping("/login")
    public String toAdminLogin(ModelMap modelMap, HttpServletRequest request) {
        String errorMsg = request.getParameter("errorMsg");
        if (errorMsg == "") {
            modelMap.addAttribute("errorMsg", errorMsg);
        }
        logger.info("跳转到后台登录页面");
        return "login/login";
    }

}
