//package com.feifang.oms.controller;
//
//import com.feifang.oms.dao.entity.User;
//import com.feifang.oms.dao.rep.UserRep;
//import io.swagger.annotations.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
///**
// * 用户控制器
// * @author Vincent
// * @date 2017/6/7
// */
//@RestController
//@RequestMapping("/user")
//@Api("userController相关api")
//public class UserControllerApi {
//
//    @Autowired
//    private UserRep userRep;
//
//    @ApiOperation("获取用户信息")
//    @ApiImplicitParams({
//            @ApiImplicitParam(paramType="header",name="username",dataType="String",required=true,value="用户的姓名"),
//            @ApiImplicitParam(paramType="query",name="password",dataType="String",required=true,value="用户的密码")
//    })
//    @ApiResponses({
//            @ApiResponse(code=400,message="请求参数没填好"),
//            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对")
//    })
//    @RequestMapping(value="/getUser",method= RequestMethod.GET)
//    public User getUser(@RequestHeader("username") String username,
//                        @RequestParam("password") String password) {
//        return userRep.findByUsernameAndPassword(username,password);
//    }
//}
//
