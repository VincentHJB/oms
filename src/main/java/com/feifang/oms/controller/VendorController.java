package com.feifang.oms.controller;

import com.feifang.oms.controller.param.VendorParam;
import com.feifang.oms.model.ResultData;
import com.feifang.oms.model.vo.UserVo;
import com.feifang.oms.model.vo.VendorVo;
import com.feifang.oms.service.UserService;
import com.feifang.oms.service.VendorService;
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
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 供应商控制器
 * @author Vincent
 * @date 2017/6/8
 */
@Controller
@RequestMapping(value = "vendor")
public class VendorController {
    public static Logger logger = LoggerFactory.getLogger(VendorController.class);

    @Autowired
    private VendorService vendorService;

    /**
     * 跳转到添加供应商页面
     * @param request
     * @param map
     * @param response
     * @return
     */
    @RequestMapping(value = "/toAdd", method = RequestMethod.GET)
    public String toAddVendor(HttpServletRequest request, ModelMap map, HttpServletResponse response){
        if(null == request.getSession().getAttribute("userId")){
            return null;
        }
        return "vendor/vendor_add";
    }

    /**
     * 添加供应商
     * @param req
     * @param vendorParam
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResultData addVendor(HttpServletRequest req, VendorParam vendorParam){
        ResultData result = vendorService.addVendor(req, vendorParam);
        logger.info("添加供应商接口, 添加结果-->" + result.getMsg());
        return result;
    }

    /**
     * 跳转到修改单个供应商信息页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/toVendorInfo", method = RequestMethod.GET)
    public String toVendorInfo(HttpServletRequest request, Integer id, ModelMap modelMap){
        if(null ==request.getSession().getAttribute("userId")){
            return null;
        } else if(null != id){
            VendorVo vendorVo = vendorService.findOne(id);
            modelMap.addAttribute("vendorVo", vendorVo);
        }
        return "vendor/vendor_modify";
    }


    /**
     * 修改单个用户信息
     * @param req
     * @param vendorParam
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/modifyVendorInfo", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResultData modifyUserInfo(HttpServletRequest req, VendorParam vendorParam){
        ResultData result = vendorService.modifyVendorInfo(req, vendorParam);
        logger.info("修改单个供应商信息接口, 修改结果-->" + result.getMsg());
        return result;
    }

    /**
     * 跳转到供应商列表（显示的数据要除掉superadmin自己）
     * @param request
     * @return
     */
    @RequestMapping(value = "/toAll", method = RequestMethod.GET)
    public String toAll(HttpServletRequest request){
        if(null ==request.getSession().getAttribute("userId")){
            return null;
        }
        return "vendor/vendor_list";
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
        Map<String, Object> resultMap = vendorService.findAll(pageNo, pageSize);
        return resultMap;
    }

    /**
     * 删除客户
     * @param modelmap
     * @param vendorId
     * @return
     */
    @RequestMapping(value="/delete",method = RequestMethod.POST)
    public String delete(ModelMap modelmap, Integer vendorId){
        logger.info("删除供应商数据：vendor_id: " + vendorId);
        boolean result = vendorService.delete(vendorId);
        if(result){
            logger.info("删除成功，vendor_id = "+ vendorId);
            modelmap.addAttribute("result_code", "success");
        }
        return "redirect:/vendor/toAll";
    }
}
