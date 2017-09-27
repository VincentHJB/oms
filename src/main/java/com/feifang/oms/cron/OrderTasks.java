package com.feifang.oms.cron;

import com.feifang.oms.dao.entity.Order;
import com.feifang.oms.dao.rep.OrderRep;
import com.feifang.oms.model.OrderStatus;
import com.feifang.oms.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 订单定时任务
 * @author Vincent
 * @date 2017/9/14
 */
@Component
public class OrderTasks {

    @Autowired
    private OrderService orderService;

    /**
     * 修改订单状态（每天凌晨0点开始）
     */
//    @Scheduled(fixedRate = 60 * 1000)
    @Scheduled(cron = "0 0 0 * * ?")
    public void modifyOrder() {
        orderService.modifyOrder();
    }
}
