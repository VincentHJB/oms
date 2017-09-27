package com.feifang.oms.dao.rep;


import com.feifang.oms.dao.entity.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * @author Vincent
 * @date 2017/5/23
 */
@Repository
public interface VendorRep extends JpaRepository<Vendor, Serializable> {
    Vendor findByName(String name);

    Vendor findByNameAndStoreName(String name, String storeName);
}
