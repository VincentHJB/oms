package com.feifang.oms;

import com.feifang.oms.dao.entity.User;
import com.feifang.oms.dao.rep.UserRep;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 分页测试用例
 * @author : Vincent
 * @version : 1.0.0
 * @date : 2017/6/6
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PageTest {

    @Autowired
    private UserRep userRep;

    @Test
    public void test() throws Exception {
        Pageable pageable =new PageRequest(0, 10);
        Page<User> datas = userRep.findAll(pageable);
        System.out.println("总条数："+datas.getTotalElements());
        System.out.println("总页数："+datas.getTotalPages());
        for(User u : datas) {
            System.out.println("第" + u.getId() + "条： "+u.getUsername());
        }
    }
}
