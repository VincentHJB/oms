//package com.feifang.oms;
//
//import com.feifang.oms.dao.entity.Order;
//import com.feifang.oms.dao.entity.User;
//import com.feifang.oms.dao.entity.Vendor;
//import com.feifang.oms.dao.rep.OrderRep;
//import com.feifang.oms.dao.rep.UserRep;
//import com.feifang.oms.dao.rep.VendorRep;
//import org.apache.commons.lang3.time.DateUtils;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import java.util.Date;
//
///**
// * 用户测试用例
// * @author Vincent
// * @date 2017/6/6
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest
//public class UserTest {
//
//    @Autowired
//    private UserRep userRep;
//
//    @Autowired
//    private VendorRep vendorRep;
//
//    @Autowired
//    private OrderRep orderRep;
//
//    @Test
//    public void addTest() throws Exception {
//        // 创建100条记录
//        for(int i = 1; i <= 100; i++){
//            userRep.save(new User("user" + i, "123456", "13312341234", 1));
//        }
//    }
//
////    @Test
////    public void addTest() throws Exception {
////        // 创建100条记录
////        for(int i = 1; i <= 100; i++){
////            vendorRep.save(new Vendor("v" + i, "188250503802", "广州市", "家电", "店铺"+ i, "000", "000", "000"));
////        }
////    }
//
////    @Test
////    public void addTest() throws Exception {
////        // 创建100条记录
////        for(int i = 1; i <= 100; i++){
////            orderRep.save(new Order("小米充电宝", 100, 10000.00, 0000+i, "备注", "6602", 1, 1, null, null, 10, 500.00, 0));
////        }
////    }
//
//}
