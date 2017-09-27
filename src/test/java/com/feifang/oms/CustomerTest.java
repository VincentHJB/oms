package com.feifang.oms;

import com.feifang.oms.dao.entity.Customer;
import com.feifang.oms.dao.rep.CustomerRep;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 客户测试用例
 * @author Vincent
 * @date 2017/6/6
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CustomerTest {

    @Autowired
    private CustomerRep customerRep;

    @Test
    public void addTest() throws Exception {
        // 创建100条记录
        for(int i = 1; i <= 10; i++){
            customerRep.save(new Customer("黄先生"+i, "美国", "00"+i, "13312341234"));
        }
    }

}
