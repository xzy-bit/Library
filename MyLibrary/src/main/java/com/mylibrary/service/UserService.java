package com.mylibrary.service;

import jakarta.servlet.http.HttpSession;

public interface UserService {
    //定义验证用户登录的方法 逻辑是:验证用户名和密码是否正确，如果正确，则为浏览器添加Session
    boolean Valid(String username, String password, HttpSession session);
}
