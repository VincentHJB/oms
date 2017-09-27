package com.feifang.oms.dao.rep;
import com.feifang.oms.dao.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * @author Vincent
 * @date 2017/5/22
 */
@Repository
public interface OrderRep extends CrudRepository<Order, Serializable>,JpaSpecificationExecutor<Order> {
    Order findByUid(String uid);

    @Query(value = " SELECT * FROM orders WHERE uid = ?1 ", nativeQuery = true)
    List<Order> findUid(String uid);

    Page<Order> findByStatus(Integer status, Pageable pageable);

    List<Order> findByStatus(Integer status);
}
