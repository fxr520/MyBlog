package com.fxr.myblog.service.Impl;

import com.fxr.myblog.dao.UserDao;
import com.fxr.myblog.po.User;
import com.fxr.myblog.service.UserService;
import com.fxr.myblog.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author fanXiaoRui
 * @date 2020/10/6 11:55
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User checkUser(String userName, String passWord) {
        User user = userDao.findByUserNameAndPassWord(userName, MD5Utils.code(passWord));
        return user;
    }
}
