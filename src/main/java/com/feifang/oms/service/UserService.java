package com.feifang.oms.service;

import com.feifang.oms.dao.entity.User;
import com.feifang.oms.dao.rep.UserRep;
import com.feifang.oms.model.ResultData;
import com.feifang.oms.model.vo.UserVo;
import com.feifang.oms.utils.MD5Util;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户增删查改
 * @author Vincent
 * @date 2017/6/7
 */
@Service
public class UserService {

    @Autowired
    private UserRep userRep;

    /**
     * 添加用户
     * @param username
     * @param password
     */
    public ResultData addUser(String username, String password, String phone){
        ResultData resultData = new ResultData();
        if(username != null && password != null){
            User user = new User();
            user.setUsername(username);
            user.setPassword(MD5Util.md5(password));
            user.setPhone(phone);
            user.setStatus(1);
            userRep.save(user);
            resultData.setSuccess(true);
        }else {
            resultData.setSuccess(false);
            resultData.setMsg("用户名或密码为空");
        }
        return resultData;
    }

    /**
     * 修改手机号
     * @param phone
     * @param new_mobile
     * @return
     */
    public ResultData savePhone(Integer id, String phone,String new_mobile){
        ResultData resultData = new ResultData();
        User user = userRep.findOne(id);
        if(/*phone != null && */phone.equals(user.getPhone())){//不能用等号
            user.setPhone(new_mobile);
            userRep.save(user);
            resultData.setSuccess(true);
        }else {
            resultData.setSuccess(false);
            resultData.setMsg("原手机号不正确");//手机号为空或
        }
        return resultData;
    }

    /**
     * 修改我的密码
     * @param id
     * @param oldPwd
     * @param newPwd
     * @return
     */
    public ResultData modifyPassword(Integer id, String oldPwd,String newPwd){
        ResultData resultData = new ResultData();
        User user = userRep.findOne(id);
        if((MD5Util.md5(oldPwd)).equals(user.getPassword())){//不能用等号
            user.setPassword(MD5Util.md5(newPwd));
            userRep.save(user);
            resultData.setSuccess(true);
        }else {
            resultData.setSuccess(false);
            resultData.setMsg("原密码不正确");//手机号为空或
        }
        return resultData;
    }

    /**
     * 修改单个用户信息
     * @param id
     * @param oldPwd
     * @param newPwd
     * @return
     */
    public ResultData modifyUserInfo(Integer id, String oldPwd, String newPwd, String phone){
        ResultData resultData = new ResultData();
        User user = userRep.findOne(id);
        if(null != id && (MD5Util.md5(oldPwd)).equals(user.getPassword())){//不能用等号
            user.setPassword(MD5Util.md5(newPwd));
            user.setPhone(phone);
            userRep.save(user);
            resultData.setSuccess(true);
        }else {
            resultData.setSuccess(false);
            resultData.setMsg("原密码不正确");//手机号为空或
        }
        return resultData;
    }

    /**
     * 获取所有用户
     * @return
     */
//    public List<User> getAllUser() {
//    List<User> userList =  userRep.findAll();
//    return  userList;
//    }

    /**
     * 修改用户状态
     * @param userId
     * @param status
     * @return
     */
    public Boolean modifyStatus(Integer userId, Integer status) {
        User user = userRep.findOne(userId);
        if(null != user) {
            user.setStatus(status);
            userRep.save(user);
            return true;
        }
        return false;
    }

    /**
     * 查找单个用户信息
     * @param id
     * @return
     */
    public UserVo findOne(Integer id) {
        User user = userRep.findOne(id);
        UserVo userVo = new UserVo();
        userVo.setId(id);
        userVo.setPhone(user.getPhone());
        userVo.setPassword(user.getPassword());
        BeanUtils.copyProperties(user, userVo);
        return userVo;
    }

    /**
     * 分页查询用户
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Map<String, Object> findAll(Integer pageNo, Integer pageSize) {
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        Pageable pageable = new PageRequest(pageNo, pageSize, sort);
        Page<User> page = userRep.findAll(pageable);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("vo", page.getContent());
        if (null != page) {
            resultMap.put("total_page", page.getTotalPages());
        }

        return resultMap;
    }

    /**
     * 删除用户
     * @param userId
     * @return
     */
    public Boolean delete(Integer userId) {
        if(null == userId){
            return false;
        }
        User user = userRep.findOne(userId);
        userRep.delete(user.getId());
        return true;
    }
}
