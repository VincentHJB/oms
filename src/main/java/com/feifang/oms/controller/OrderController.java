package com.feifang.oms.controller;

import com.alibaba.fastjson.JSONObject;
import com.feifang.oms.controller.param.OrderParam;
import com.feifang.oms.model.ResultData;
import com.feifang.oms.model.vo.OrderVo;
import com.feifang.oms.service.OrderService;
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
 * 订单控制器
 * @author Vincent
 * @date 2017/6/8
 */
@Controller
@RequestMapping(value = "order")
public class OrderController {
    public static Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;


    /**
     * 跳转到添加订单页面
     * @param request
     * @param map
     * @param response
     * @return
     */
    @RequestMapping(value = "/toAdd", method = RequestMethod.GET)
    public String toAddOrder(HttpServletRequest request, ModelMap map, HttpServletResponse response){
        if(null == request.getSession().getAttribute("userId")){
            return null;
        }
        return "order/order_add";
    }

    /**
     * 添加订单
     * @param req
     * @param orderParam
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public String save(HttpServletRequest req, ModelMap modelmap, OrderParam orderParam){
        logger.info("订单信息保存：" + JSONObject.toJSONString(orderParam));
        boolean result =  orderService.save(req, orderParam);
        if(result){
            logger.info("保存成功");
            modelmap.addAttribute("result_code", "success");
        }
        return "redirect:/order/toAll";
    }

    /**
     * 跳转到添加图片
     */
    @RequestMapping(value = "/toAddPic", method = RequestMethod.POST)
    public String toAddPic(Integer total, ModelMap modelMap) {
        modelMap.addAttribute("total", total);
        return "order/add_pic";
    }

    /**
     * 添加图片接口
     * @param req
     * @return
     */
    @RequestMapping(value = "/addPic", method = RequestMethod.POST)
    @ResponseBody
    public String addPic(HttpServletRequest req) {
        boolean result = orderService.addPic(req);
        if (result) {
            return "success";
        } else {
            return "false";
        }
    }

    /**
     * 跳转到订单列表
     * @param request
     * @return
     */
    @RequestMapping(value = "/toAll", method = RequestMethod.GET)
    public String toAll(HttpServletRequest request){
        if(null ==request.getSession().getAttribute("userId")){
            return null;
        }
        return "order/order_all";
    }

    /**
     * 分页查询订单列表
     * @param request
     * @param pageNo
     * @param pageSize
     * @return
     */
//    @ResponseBody
//    @RequestMapping(value = "/page/all", method = RequestMethod.GET)
//    public Map<String, Object> userAll(HttpServletRequest request,
//                                       @RequestParam(value = "page_no", defaultValue = "0") Integer pageNo,
//                                       @RequestParam(value = "page_size", defaultValue = "10") Integer pageSize) {
//        Integer userId = (Integer) request.getSession().getAttribute("userId");
//        logger.info("分页查询订单：userId= " + userId + ", page_no = " + pageNo + ", page_size = " + pageSize);
//        pageNo = pageNo <= 1 ? 0 : --pageNo;
//        Map<String, Object> resultMap = orderService.findAll(pageNo, pageSize);
//        return resultMap;
//    }

    /**
     * 删除订单
     * @param modelmap
     * @param orderId
     * @return
     */
    @RequestMapping(value="delete",method = RequestMethod.POST)
    public String delete(ModelMap modelmap, Integer orderId){
        //Integer userId = (Integer) request.getSession().getAttribute("userId");
        logger.info("删除订单数据： order_id = " + orderId);
        boolean result = orderService.delete(orderId);
        if(result){
            logger.info("删除订单成功，order_id = "+ orderId);
            modelmap.addAttribute("result_code", "success");
        }
        return "redirect:/order/toAll";
    }

    /**
     * 跳转到修改订单页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/toOrderInfo", method = RequestMethod.GET)
    public String toOrderInfo(HttpServletRequest request, Integer orderId, ModelMap modelMap){
        if(null ==request.getSession().getAttribute("userId")){
            return null;
        }else if(null != orderId){
            OrderVo orderVo = orderService.findOne(orderId);
            modelMap.addAttribute("orderVo", orderVo);
        }
        return "order/order_modify";
    }

    /**
     * 某个订单的详细信息
     * @param req
     * @param orderId
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "toDetails", method = RequestMethod.GET)
    public String detail(HttpServletRequest req, Integer orderId, ModelMap modelMap) {
        logger.info("查询单个订单的详细信息,订单编号： " + orderId);
        OrderVo orderVo = orderService.findOne(orderId);
        modelMap.addAttribute("orderVo", orderVo);
        return "order/order_details";
    }

    /**
     * 搜索订单
     * @param request
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public Map<String, Object> orderSearch(HttpServletRequest request,
                                           @RequestParam(value = "page_no", defaultValue = "0") Integer pageNo,
                                           @RequestParam(value = "page_size", defaultValue = "10") Integer pageSize,
                                           String startAt, String endAt, Integer orderId, String tradeName) {
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        pageNo = pageNo <= 1 ? 0 : --pageNo;
        logger.info("分页查询订单：userId= " + userId + ", page_no = " + pageNo + ", page_size = " + pageSize + ", startAt = " + startAt + ", endAt = " + endAt +
        ", orderId = " + orderId + ", tradeName = " + tradeName);
        Map<String, Object> resultMap = orderService.findOrder(pageNo, pageSize, startAt, endAt, orderId, tradeName);
        return resultMap;
    }

    /**
     * 跳转到今日订单
     * @return
     */
    @RequestMapping(value = "/toTodayOrder", method = RequestMethod.GET)
    public String todayOrder(){
        return "index/today_order";
    }

    /**
     * 今日订单
     * @param request
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/todayOrder", method = RequestMethod.GET)
    public Map<String, Object> userAll(HttpServletRequest request,
                                       @RequestParam(value = "page_no", defaultValue = "0") Integer pageNo,
                                       @RequestParam(value = "page_size", defaultValue = "10") Integer pageSize) {
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        logger.info("查询今日订单：userId= " + userId + ", page_no = " + pageNo + ", page_size = " + pageSize);
        pageNo = pageNo <= 1 ? 0 : --pageNo;
        Map<String, Object> resultMap = orderService.findOrderByToday(pageNo, pageSize);
        return resultMap;
    }
}

