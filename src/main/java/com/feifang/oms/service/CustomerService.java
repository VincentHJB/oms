package com.feifang.oms.service;

import com.feifang.oms.dao.entity.Customer;
import com.feifang.oms.dao.rep.CustomerRep;
import com.feifang.oms.model.ResultData;
import com.feifang.oms.model.vo.CustomerVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 客户增删查改
 * @author Vincent
 * @date 2017/6/17
 */
@Service
public class CustomerService {

    @Autowired
    private CustomerRep customerRep;

    /**
     * 添加客户
     * @param customerName
     * @param nationality
     * @param phone
     * @param customerNo
     * @return
     */
    public ResultData addCustomer(String customerName, String nationality, String phone, String customerNo) {
        ResultData resultData = new ResultData();
        if(null != customerName && null != customerNo){
            Customer customer = new Customer();
            customer.setName(customerName);
            customer.setNationality(nationality);
            customer.setPhone(phone);
            customer.setCustomerNo(customerNo);
            customerRep.save(customer);
            resultData.setSuccess(true);
        }else {
            resultData.setSuccess(false);
            resultData.setMsg("客户名称或客户编号不能为空");
        }
        return resultData;
    }

    /**
     * 查询单个客户信息
     * @param id
     * @return
     */
    public CustomerVo findOne(Integer id){
        Customer customer = customerRep.findOne(id);
        CustomerVo customerVo = new CustomerVo();

        customerVo.setCustomerNo(customer.getCustomerNo());
        customerVo.setId(customer.getId());
        customerVo.setName(customer.getName());
        customerVo.setNationality(customer.getNationality());
        customerVo.setPhone(customer.getPhone());
        return customerVo;
    }

    /**
     * 分页查询客户
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Map<String, Object> findAll(Integer pageNo, Integer pageSize) {
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        Pageable pageable = new PageRequest(pageNo, pageSize, sort);
        Page<Customer> page = customerRep.findAll(pageable);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("vo", page.getContent());
        if (null != page) {
            resultMap.put("total_page", page.getTotalPages());
        }

        return resultMap;
    }

    /**
     * 修改客户信息
     * @param customerId
     * @param customerName
     * @param nationality
     * @param phone
     * @param customerNo
     * @return
     */
    public ResultData modifyCustomer(Integer customerId, String customerName, String nationality, String phone,
                                     String customerNo) {
        ResultData resultData = new ResultData();
        Customer customer = customerRep.findOne(customerId);
        if(null != customerId && null != customer){
            customer.setName(customerName);
            customer.setNationality(nationality);
            customer.setPhone(phone);
            customer.setCustomerNo(customerNo);
            customerRep.save(customer);
            resultData.setSuccess(true);
        }else {
            resultData.setSuccess(false);
            resultData.setMsg("参数有误，请重新输入！");
        }
        return resultData;
    }


    /**
     * 删除客户
     * @param customerId
     * @return
     */
    public Boolean delete(Integer customerId) {
        if(null == customerId){
            return false;
        }
        Customer customer = customerRep.findOne(customerId);
        customerRep.delete(customer.getId());
        return true;
    }
}
