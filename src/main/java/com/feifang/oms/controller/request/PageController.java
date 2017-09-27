package com.feifang.oms.controller.request;

import com.feifang.oms.dao.entity.User;
import com.feifang.oms.dao.rep.UserRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Iterator;

/**
 * @author Vincent
 * @date 2017/6/6
 */
@Controller
public class PageController {

    @Autowired
    private UserRep userRep;

    @RequestMapping(value = "/params", method= RequestMethod.GET)
    @ResponseBody
    public String getEntryByParams(@RequestParam(value = "username", defaultValue = "") String username,
                                   @RequestParam(value = "page", defaultValue = "0") Integer page,
                                   @RequestParam(value = "size", defaultValue = "15") Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(page, size, sort);
        Page<User> pages=userRep.findByUsername(username, pageable);
        Iterator<User> it=pages.iterator();
        while(it.hasNext()){
            System.out.println("value:"+((User)it.next()).getId());
        }
        return "页码：" + page + "<br/>" + "每页页数：" + size + "<br/>" +"success...login....";
    }
}

