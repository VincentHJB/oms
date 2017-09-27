package com.feifang.oms.controller;

import com.feifang.oms.model.ResultData;
import com.feifang.oms.model.vo.CustomerVo;
import com.feifang.oms.model.vo.UserVo;
import com.feifang.oms.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 客户（消费者）控制器
 * @author Vincent
 * @date 2017/6/17
 */
@Controller
@RequestMapping(value = "customer")
public class CustomerController {
    public static Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;

    /**
     * 跳转到添加客户页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/toAddCustomer", method = RequestMethod.GET)
    public String toAddCustomer(HttpServletRequest request) {
        if(request.getSession().getAttribute("userId") == null){
            return null;
        }
        return "customer/customer_add";
    }

    /**
     * 添加用户
     * @param customerName
     * @param nationality
     * @param phone
     * @param customerNo
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addCustomer", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResultData addCustomer(@RequestParam(name = "customerName", required = true) String customerName,
                                  @RequestParam(name = "nationality", required = true) String nationality,
                                  @RequestParam(name = "phone", required = true)String phone,
                                  @RequestParam(name = "customerNo", required = true)String customerNo) {
        ResultData result = customerService.addCustomer(customerName, nationality, phone, customerNo);
        logger.info("添加客户接口, 添加结果-->" + result.getMsg());
        return result;
    }

    /**
     * 跳转到修改客户页面
     * @param request
     * @return
     */
    @RequestMapping(value = "toModifyCustomer", method = RequestMethod.GET)
    public String toModifyCustomer(HttpServletRequest request, Integer id, ModelMap modelMap) {
        if(request.getSession().getAttribute("userId") == null){
            return null;
        }else if(null != id){
            CustomerVo customerVo = customerService.findOne(id);
            modelMap.addAttribute("customerVo", customerVo);
        }
        return "customer/customer_edit";
    }

    /**
     * 修改客户
     * @param customerId
     * @param customerName
     * @param nationality
     * @param phone
     * @param customerNo
     * @return
     */
    @RequestMapping(value = "modifyCustomer", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResultData modifyCustomer(@RequestParam(value = "customerId", required = true) Integer customerId,
                                     @RequestParam(value="customerName",required=true) String customerName,
                                     @RequestParam(value="nationality",required=true) String nationality,
                                     @RequestParam(value = "phone", required = true) String phone,
                                     @RequestParam(value = "customerNo", required = true) String customerNo) {
        ResultData result = customerService.modifyCustomer(customerId, customerName, nationality, phone, customerNo);
        logger.info("修改单个用户信息接口, 修改结果-->" + result.getMsg());
        return result;
    }

    /**
     * 跳转到客户列表
     * @param request
     * @return
     */
    @RequestMapping(value = "/toAll", method = RequestMethod.GET)
    public String toAll(HttpServletRequest request){
        if(null ==request.getSession().getAttribute("userId")){
            return null;
        }
        return "customer/customer_list";
    }

    /**
     * 客户列表(分页)
     * @param request
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/page/all", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> customerAll(HttpServletRequest request,
                                       @RequestParam(value = "page_no", defaultValue = "0") Integer pageNo,
                                       @RequestParam(value = "page_size", defaultValue = "10") Integer pageSize) {
        pageNo = pageNo <= 1 ? 0 : --pageNo;
        Map<String, Object> resultMap = customerService.findAll(pageNo, pageSize);
        return resultMap;
    }

    /**
     * 删除客户
     * @param modelmap
     * @param customerId
     * @return
     */
    @RequestMapping(value="delete",method = RequestMethod.POST)
    public String delete(ModelMap modelmap, Integer customerId){
        logger.info("删除客户数据：customer_id: " + customerId);
        boolean result = customerService.delete(customerId);
        if(result){
            logger.info("删除成功，customer_id = "+ customerId);
            modelmap.addAttribute("result_code", "success");
        }
        return "redirect:/customer/toAll";
    }
}
