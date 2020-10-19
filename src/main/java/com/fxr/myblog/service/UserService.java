package com.fxr.myblog.service;

import com.fxr.myblog.po.User;

/**
 * @author fanXiaoRui
 * @date 2020/10/6 10:57
 */
public interface UserService {

    User checkUser(String userName, String passWord);
}
