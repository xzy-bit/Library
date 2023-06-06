package com.mylibrary.filter;

import com.mylibrary.entity.Student;
import com.mylibrary.entity.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/*")
public class LoginFilter extends HttpFilter {
    @Override
    public void init(FilterConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String url = req.getRequestURI();
        //如果请求的资源是静态资源或者登录界面，则请求通过
        if (!url.contains("assets/")&&!url.endsWith("login")&&!url.endsWith("signup")){
            HttpSession session = req.getSession();
            User user = (User) session.getAttribute("user");
            Student student = (Student) session.getAttribute("student");
            if (user==null&&student==null){
                res.sendRedirect("login");
                return;
            }
        }
        chain.doFilter(req,res);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}

