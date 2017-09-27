package com.feifang.oms.dao.rep;


import com.feifang.oms.dao.entity.CommonAttachImg;
import com.feifang.oms.dao.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * @author Vincent
 * @date 2017/5/23
 */
@Repository
public interface CommonAttachImgRep extends JpaRepository<CommonAttachImg, Serializable> {
}
