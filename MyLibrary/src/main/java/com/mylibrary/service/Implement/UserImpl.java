package com.mylibrary.service.Implement;

import com.mylibrary.entity.User;
import com.mylibrary.mapper.UserMapper;
import com.mylibrary.service.UserService;
import com.mylibrary.utils.MybatisUtil;
import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;

public class UserImpl implements UserService {

    @Override
    public boolean Valid(String username, String password, HttpSession session) {
        try(SqlSession sqlSession = MybatisUtil.getSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            User user = mapper.ValidUser(username,password);
            if (user!=null){
                session.setAttribute("user",user);
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
