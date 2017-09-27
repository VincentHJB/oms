package com.feifang.oms.dao.rep;

import com.feifang.oms.dao.entity.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * @author Vincent
 * @date 2017/6/16
 */
@Repository
public interface UserLoginRep extends JpaRepository<UserLogin, Serializable> {

}
