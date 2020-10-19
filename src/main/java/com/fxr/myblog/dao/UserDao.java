package com.fxr.myblog.dao;

import com.fxr.myblog.po.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author fanXiaoRui
 * @date 2020/10/6 11:58
 */
public interface UserDao extends JpaRepository<User,Long> {

    User findByUserNameAndPassWord(String username,String password);
}
