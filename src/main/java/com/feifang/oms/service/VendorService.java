package com.feifang.oms.service;

import com.feifang.oms.controller.param.VendorParam;
import com.feifang.oms.dao.entity.User;
import com.feifang.oms.dao.entity.Vendor;
import com.feifang.oms.dao.rep.UserRep;
import com.feifang.oms.dao.rep.VendorRep;
import com.feifang.oms.model.ResultData;
import com.feifang.oms.model.vo.UserVo;
import com.feifang.oms.model.vo.VendorVo;
import com.feifang.oms.utils.MD5Util;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 供应商容器
 * @author Vincent
 * @date 2017/6/7
 */
@Service
public class VendorService {

    @Autowired
    private VendorRep vendorRep;

    /**
     * 添加供应商
     * @param vendorParam
     * @return
     */
    public ResultData addVendor(HttpServletRequest req, VendorParam vendorParam){
        ResultData resultData = new ResultData();
        Integer uid = (Integer) req.getSession().getAttribute("userId");
        if(uid != null){
            Vendor vendor = new Vendor();
            vendor.setName(vendorParam.getName());
            vendor.setPhone(vendorParam.getPhone());
            vendor.setAddress(vendorParam.getAddress());
            vendor.setStoreName(vendorParam.getStoreName());
            vendor.setType(vendorParam.getType());
            vendor.setBankName(vendorParam.getBankName());
            vendor.setAccountName(vendorParam.getAccountName());
            vendor.setCardNo(vendorParam.getCardNo());
            vendorRep.save(vendor);
            resultData.setSuccess(true);
        }else {
            resultData.setSuccess(false);
            resultData.setMsg("添加供应商失败，请重新登录");
        }
        return resultData;
    }

    /**
     * 查询单个供应商信息
     * @param id
     * @return
     */
    public VendorVo findOne(Integer id){
        Vendor vendor = vendorRep.findOne(id);
        VendorVo vendorVo = new VendorVo();

        vendorVo.setName(vendor.getName());
        vendorVo.setPhone(vendor.getPhone());
        vendorVo.setType(vendor.getType());
        vendorVo.setAddress(vendor.getAddress());
        vendorVo.setStoreName(vendor.getStoreName());
        vendorVo.setAccountName(vendor.getAccountName());
        vendorVo.setBankName(vendor.getBankName());
        vendorVo.setCardNo(vendor.getCardNo());

        return vendorVo;
    }

    /**
     * 修改单个供应商信息
     * @param vendorParam
     * @return
     */
    public ResultData modifyVendorInfo(HttpServletRequest req, VendorParam vendorParam){
        ResultData resultData = new ResultData();
        Integer uid = (Integer) req.getSession().getAttribute("userId");
        if(uid != null && vendorParam.getName() != null && vendorParam.getStoreName() != null){
            Vendor vendor = vendorRep.findByNameAndStoreName(vendorParam.getName(), vendorParam.getStoreName());
            vendor.setName(vendorParam.getName());
            vendor.setStoreName(vendorParam.getStoreName());
            vendor.setPhone(vendorParam.getPhone());
            vendor.setCardNo(vendorParam.getCardNo());
            vendor.setAccountName(vendorParam.getAccountName());
            vendor.setBankName(vendorParam.getBankName());
            vendor.setType(vendorParam.getType());
            vendor.setAddress(vendorParam.getAddress());
            vendorRep.save(vendor);
            resultData.setSuccess(true);
        }else {
            resultData.setSuccess(false);
            resultData.setMsg("参数有误，请重新输入！");
        }
        return resultData;
    }
//
//    /**
//     * 查找单个用户信息
//     * @param id
//     * @return
//     */
//    public UserVo findOne(Integer id) {
//        User user = userRep.findOne(id);
//        UserVo userVo = new UserVo();
//        userVo.setId(id);
//        userVo.setPhone(user.getPhone());
//        userVo.setPassword(user.getPassword());
//        BeanUtils.copyProperties(user, userVo);
//        return userVo;
//    }
//
    /**
     * 分页查询用户
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Map<String, Object> findAll(Integer pageNo, Integer pageSize) {
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        Pageable pageable = new PageRequest(pageNo, pageSize, sort);
        Page<Vendor> page = vendorRep.findAll(pageable);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("vo", page.getContent());
        if (null != page) {
            resultMap.put("total_page", page.getTotalPages());
        }

        return resultMap;
    }

    /**
     * 删除供应商
     * @param vendorId
     * @return
     */
    public Boolean delete(Integer vendorId) {
        if(null == vendorId){
            return false;
        }
        Vendor vendor = vendorRep.findOne(vendorId);
        vendorRep.delete(vendor.getId());
        return true;
    }

}
