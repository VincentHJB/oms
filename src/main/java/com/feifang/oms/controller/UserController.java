package com.feifang.oms.controller;

import com.feifang.oms.model.vo.UserVo;
import com.feifang.oms.service.UserService;
import com.feifang.oms.model.ResultData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 用户控制器
 * @author Vincent
 * @date 2017/6/8
 */
@Controller
@RequestMapping(value = "user")
public class UserController {
    public static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    /**
     * 跳转到添加用户页面
     * @param request
     * @param map
     * @param response
     * @return
     */
    @RequestMapping(value = "/toAdd", method = RequestMethod.GET)
    public String toAddUser(HttpServletRequest request, ModelMap map, HttpServletResponse response){
        if(null == request.getSession().getAttribute("userId")){
            return null;
        }
        return "user/add";
    }

    /**
     * 添加用户
     * @param username
     * @param password
     */
    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResultData addUser(@RequestParam(name = "username", required = true) String username,
                              @RequestParam(name = "password", required = true) String password,
                              @RequestParam(name = "phone", required = true) String phone){
        ResultData result = userService.addUser(username, password, phone);
        logger.info("添加用户接口, 添加结果-->" + result.getMsg());
        return result;
    }

    /**
     * 跳转到修改手机页面
     * @param request
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/toEdit", method = RequestMethod.GET)
    public String toEditMobile(HttpServletRequest request,ModelMap modelMap,HttpServletResponse response){
        if(request.getSession().getAttribute("userId") == null){
            return null;
        }
        return "user/modify_phone";
    }

    /**
     * 修改手机号
     * @param phone
     * @param new_mobile
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/edit", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResultData editMobile(HttpServletRequest request,
                                 @RequestParam (name = "phone")String phone,
                                 @RequestParam (name = "new_mobile")String new_mobile) {
        Integer id = (Integer) request.getSession().getAttribute("userId");
        ResultData result = userService.savePhone(id, phone, new_mobile);
        logger.info("修改手机号码接口, 修改结果-->" + result.getMsg());
        return result;
    }

    /**
     * 跳转到修改我的密码页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/toModifyPassword", method = RequestMethod.GET)
    public String toModifyPassword(HttpServletRequest request){
        if(null == request.getSession().getAttribute("userId")){
            return null;
        }
        return "user/modify_password";
    }

    /**
     * 修改我的密码
     * @param request
     * @param oldPwd
     * @param newPwd
     * @param confirmPwd
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/modifyPassword", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResultData modifyPassword(HttpServletRequest request,
                                     @RequestParam(value="oldPwd",required=true) String oldPwd,
                                     @RequestParam(value="newPwd",required=true) String newPwd,
                                     @RequestParam(value="confirmPwd",required=false) String confirmPwd){
        Integer id = (Integer) request.getSession().getAttribute("userId");
        ResultData result = userService.modifyPassword(id, oldPwd, newPwd);
        logger.info("修改我的密码接口, 修改结果-->" + result.getMsg());
        return result;
    }

    /**
     * 跳转到修改单个用户信息页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/toUserInfo", method = RequestMethod.GET)
    public String toUserInfo(HttpServletRequest request, Integer id, ModelMap modelMap){
        if(null ==request.getSession().getAttribute("userId")){
            return null;
        } else if(null != id){
            UserVo userVo = userService.findOne(id);
            modelMap.addAttribute("userVo", userVo);
        }
        return "user/user_password";
    }


    /**
     * 修改单个用户信息
     * @param id
     * @param oldPwd
     * @param newPwd
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/modifyUserInfo", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResultData modifyUserInfo(@RequestParam(value = "id", required = true) Integer id,
                                     @RequestParam(value="oldPwd",required=true) String oldPwd,
                                     @RequestParam(value="newPwd",required=true) String newPwd,
                                     @RequestParam(value = "phone", required = true) String phone){
        ResultData result = userService.modifyUserInfo(id, oldPwd, newPwd, phone);
        logger.info("修改单个用户信息接口, 修改结果-->" + result.getMsg());
        return result;
    }

    /**
     * 跳转到用户列表（显示的数据要除掉superadmin自己）
     * @param request
     * @return
     */
    @RequestMapping(value = "/toAll", method = RequestMethod.GET)
    public String toAll(HttpServletRequest request){
        if(null ==request.getSession().getAttribute("userId")){
            return null;
        }
        return "user/user_list";
    }

    /**
     * 修改用户状态
     * @param userId
     * @param status
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/status", method = RequestMethod.POST)
    public String editUserStatus(@RequestParam(value = "userId", required = true) Integer userId,
                                 @RequestParam(value = "status", required = true) Integer status){

        boolean result = userService.modifyStatus(userId, status);
        if(result){
            logger.info("修改用户状态接口，userId = " + userId + "status : " + status);
            return "success";
        }else{
            return "fail";
        }
    }

    /**
     * 分页查询用户
     * @param request
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/page/all", method = RequestMethod.GET)
    public Map<String, Object> userAll(HttpServletRequest request,
                              @RequestParam(value = "page_no", defaultValue = "0") Integer pageNo,
                              @RequestParam(value = "page_size", defaultValue = "10") Integer pageSize) {
        pageNo = pageNo <= 1 ? 0 : --pageNo;
        Map<String, Object> resultMap = userService.findAll(pageNo, pageSize);
        return resultMap;
    }

    /**
     * 删除用户
     * @param modelmap
     * @param userId
     * @return
     */
    @RequestMapping(value="delete",method = RequestMethod.POST)
    public String delete(ModelMap modelmap, Integer userId){
        logger.info("删除用户数据：user_id: " + userId);
        boolean result = userService.delete(userId);
        if(result){
            logger.info("删除成功，user_id = "+ userId);
            modelmap.addAttribute("result_code", "success");
        }
        return "redirect:/user/toAll";
    }
}
