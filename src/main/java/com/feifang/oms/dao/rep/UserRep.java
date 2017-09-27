package com.feifang.oms.dao.rep;

import com.feifang.oms.dao.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/** User持久化类
 * @author Vincent
 * @date 2017/5/22
 */
@Repository
public interface UserRep extends JpaRepository<User, Serializable> {
    User findByUsername(String userName);
    Page<User> findByUsername(String userName, Pageable pageable);
    User findByUsernameAndPassword(String username, String password);
    User findByPhone(String phone);

//    @Query(value = "SELECT * FROM user WHERE ", nativeQuery = true)
//    Page<User> findAll(Pageable pageable);
}
