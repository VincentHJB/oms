package com.feifang.oms.service;

import com.feifang.oms.controller.param.OrderParam;
import com.feifang.oms.cron.OrderTasks;
import com.feifang.oms.dao.entity.*;
import com.feifang.oms.dao.rep.CustomerRep;
import com.feifang.oms.dao.rep.OrderImageRep;
import com.feifang.oms.dao.rep.OrderRep;
import com.feifang.oms.dao.rep.VendorRep;
import com.feifang.oms.model.FileUploadType;
import com.feifang.oms.model.OrderStatus;
import com.feifang.oms.model.ResultData;
import com.feifang.oms.model.vo.OrderVo;
import com.feifang.oms.utils.DateUtil;
import com.feifang.oms.utils.FileUploadUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Vincent
 * @date 2017/7/11
 */
@Service
public class OrderService {
    private static Logger logger = LoggerFactory.getLogger(OrderTasks.class);

    @Autowired
    private OrderRep orderRep;

    @Autowired
    private FileUploadUtil fileUploadUtil;

    @Autowired
    private OrderImageRep orderImageRep;

    @Autowired
    private VendorRep vendorRep;

    @Autowired
    private CustomerRep customerRep;

    /**
     * 定时修改今日订单
     * @return
     */
    public int modifyOrder() {

        List<Order> orderList = orderRep.findByStatus(OrderStatus.INI.getValue());
        if (orderList != null || orderList.size() != 0) {
            logger.info("更新订单状态定时任务开始执行，查询今日更新订单，共 [" + orderList.size() + "] 条。");

            for (Order item : orderList) {
                item.setStatus(OrderStatus.ORDERING.getValue());
            }
            this.orderRep.save(orderList);

            logger.info("执行完成，共修改 [" + orderList.size() + "] 条订单。");
        }
        return orderList.size();
    }

    /**
     * 添加图片
     * @param req
     * @return
     */
    @Transactional
    public boolean addPic(HttpServletRequest req) {
        String uid = (String) req.getSession().getAttribute("userId");

        Order order = orderRep.findByUid(uid);
        if(order == null){
            order = new Order();
            order.setUid(Integer.valueOf(uid));
            order = orderRep.save(order);
        }
        String picUrl = fileUploadUtil.uploadFileByReq(req, null, Integer.valueOf(order.getCustomerNo()), FileUploadType.HOTEL_BANNER.getValue(), "file");
        if (StringUtils.isNotBlank(picUrl)) {
            String[] urls = picUrl.split(",");
            for (String url : urls) {
                OrderImage orderImage = new OrderImage();
                orderImage.setUid(Integer.valueOf(uid));
                orderImage.setImageUrl(url);
                orderImageRep.save(orderImage);
            }
            return true;
        }
        return false;
    }

    /**
     * 保存订单信息
     * @param req
     * @param orderParam
     * @return
     */
    @Transactional
    public boolean save(HttpServletRequest req, OrderParam orderParam){
        Integer uid = (Integer) req.getSession().getAttribute("userId");
        Vendor vid = vendorRep.findOne(orderParam.getVid());
        Customer customer = customerRep.findByCustomerNo(orderParam.getCustomerNo());//客户编号
        Order order = null;
        //判断条件是否满足
        if(uid != null && vid != null && customer != null){
            //判断订单新增还是修改
            if(null != orderParam.getId()){//修改
                order = orderRep.findOne(orderParam.getId());
                order.setStatus(orderParam.getStatus());
            }else{//新增
                order = new Order();
                order.setStatus(OrderStatus.INI.getValue());
            }


            order.setUid(uid);
            order.setVid(orderParam.getVid());
            order.setCustomerNo(orderParam.getCustomerNo());
            order.setTradeName(orderParam.getTradeName());
            order.setNumber(orderParam.getNumber());
            order.setAmount(orderParam.getAmount());
            order.setPoNo(orderParam.getPoNo());
            order.setRemark(orderParam.getRemark());

            Date startAt = null;
            Date endAt = null;
            try {
                startAt = DateUtils.parseDate(orderParam.getStartAt(), "yyyy-MM-dd");
                endAt = DateUtils.parseDate(orderParam.getEndAt(), "yyyy-MM-dd");
            } catch (ParseException e) {
                e.printStackTrace();
            }
            order.setStartAt(startAt);
            order.setEndAt(endAt);
            order.setPaymentDay(orderParam.getPaymentDay());
            order.setDeposit(orderParam.getDeposit());
            orderRep.save(order);

            try{
                String imageUrl = fileUploadUtil.uploadFileByReq(req, order.getId(), uid, FileUploadType.HOTEL_ROOM.getValue(), "file");
                if (StringUtils.isNotBlank(imageUrl)) {
                    order.setImageUrl(imageUrl);
                }
            }catch (Exception e) {
                e.printStackTrace();
            }

            return true;

        }else {
          return false;
        }
    }

    /**
     * 分页查询订单
     * @param pageNo
     * @param pageSize
     * @return
     */
//    public Map<String, Object> findAll(Integer pageNo, Integer pageSize) {
//        Sort sort = new Sort(Sort.Direction.ASC, "id");
//        Pageable pageable = new PageRequest(pageNo, pageSize, sort);
//        Page<Order> page = orderRep.findAll(pageable);
//        Map<String, Object> resultMap = new HashMap<>();
//        resultMap.put("vo", page.getContent());
//        if (null != page) {
//            resultMap.put("total_page", page.getTotalPages());
//        }
//
//        return resultMap;
//    }

    /**
     * 删除订单
     * @param orderId
     * @return
     */
    public Boolean delete(Integer orderId) {
        if(null == orderId){
            return false;
        }
        Order order = orderRep.findOne(orderId);
        orderRep.delete(order.getId());
        return true;
    }


    /**
     * 查找单个订单信息
     * @param orderId
     * @return
     */
    public OrderVo findOne(Integer orderId) {
        Order order = orderRep.findOne(orderId);
        OrderVo orderVo = new OrderVo();
        orderVo.setId(orderId);
        orderVo.setTradeName(order.getTradeName());
        orderVo.setNumber(order.getNumber());
        orderVo.setAmount(order.getAmount());
        orderVo.setPoNo(order.getPoNo());
        orderVo.setPaymentDay(order.getPaymentDay());
        orderVo.setDeposit(order.getDeposit());
        orderVo.setRemark(order.getRemark());
        orderVo.setCustomerNo(order.getCustomerNo());
        orderVo.setVid(order.getVid());
        orderVo.setStartAt(DateUtil.DateToString(order.getStartAt(),"yyyy-MM-dd"));
        orderVo.setEndAt(DateUtil.DateToString(order.getEndAt(),"yyyy-MM-dd"));
        orderVo.setStatus(order.getStatus());
        orderVo.setImageUrl(order.getImageUrl());
        //BeanUtils.copyProperties(order, orderVo);
        return orderVo;
    }

    /**
     * 订单搜索
     * @param pageNo
     * @param pageSize
     * @param startAt
     * @param endAt
     * @param orderId
     * @param tradeName
     * @return
     */
    public Map<String, Object> findOrder(Integer pageNo, Integer pageSize, String startAt, String endAt, Integer orderId, String tradeName){
        List<OrderVo> orderVos = new ArrayList<>();
        Sort sort = new Sort(Sort.Direction.DESC, "CreatedAt");
        Pageable pageable = new PageRequest(pageNo, pageSize, sort);
        List<Order> orders = null;

        //根据条件查询订单
        Specification<Order> specification = new Specification<Order>() {
            @Override
            public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (StringUtils.isNotBlank(startAt) && StringUtils.isNotBlank(endAt)) {

                    String startStr = startAt + " 00:00:00";
                    String endStr = endAt + " 00:00:00";
                    SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date sDate = null;
                    Date eDate = null;
                    try {
                        sDate = format.parse(startStr);
                        eDate = format.parse(endStr);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    predicates.add(cb.greaterThanOrEqualTo(root.get("startAt"), sDate));
                    predicates.add(cb.lessThanOrEqualTo(root.get("endAt"), eDate));
                }
                if (orderId != null) {
                    predicates.add(cb.equal(root.get("id"), orderId));
                }
                if (StringUtils.isNotBlank(tradeName)) {
                    predicates.add(cb.like(root.get("tradeName"), "%" + tradeName + "%"));
                }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        Page<Order> page = orderRep.findAll(specification, pageable);
        orders = page.getContent();
        if (null != orders && orders.size() > 0) {
            for (Order item : orders) {
                Order order = orderRep.findOne(item.getId());
                OrderVo orderVo = OrderVo.genInstance(order);
                orderVos.add(orderVo);
            }
        }
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("vo", orderVos);
        if (null != page) {
            resultMap.put("total_page", page.getTotalPages());
        }
        return resultMap;
    }

    /**
     * 今日订单分页查询
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Map<String, Object> findOrderByToday(Integer pageNo, Integer pageSize){
        Sort sort = new Sort(Sort.Direction.DESC, "CreatedAt");
        Pageable pageable = new PageRequest(pageNo, pageSize, sort);
        Page<Order> page = orderRep.findByStatus(OrderStatus.INI.getValue(), pageable);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("vo", page.getContent());
        if (null != page) {
            resultMap.put("total_page", page.getTotalPages());
        }

        return resultMap;
    }
}
