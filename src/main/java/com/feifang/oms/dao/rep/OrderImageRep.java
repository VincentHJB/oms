package com.feifang.oms.dao.rep;

import com.feifang.oms.dao.entity.Order;
import com.feifang.oms.dao.entity.OrderImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * @author Vincent
 * @date 2017/7/30
 */
@Repository
public interface OrderImageRep  extends JpaRepository<OrderImage, Serializable> {

}
