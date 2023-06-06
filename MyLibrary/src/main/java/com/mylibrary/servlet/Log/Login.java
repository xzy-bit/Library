package com.mylibrary.servlet.Log;

import com.mylibrary.service.Implement.StuImpl;
import com.mylibrary.service.Implement.UserImpl;
import com.mylibrary.service.StuService;
import com.mylibrary.service.UserService;
import com.mylibrary.utils.ThymeleafUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.context.Context;

import java.io.IOException;

@WebServlet("/login")
public class Login extends HttpServlet {
    UserService Uservice;
    StuService Sservice;

    @Override
    public void init() throws ServletException {
        Uservice = new UserImpl();
        Sservice = new StuImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //对于没有登录的用户，登录页面展示即可;如果登录失败了，那么会包含failure字段，请求的页面会发生变化
        Context context = new Context();
        //显然failure是设置在Session字段的,因此先要获取req的Session
        if (req.getSession().getAttribute("failure")!=null){
            context.setVariable("failure",true);
            //只有登陆失败的那一次会显示红字，在这里将req中的Session清理
            req.getSession().removeAttribute("failure");
        }
        ThymeleafUtil.process("login.html",context,resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("signin-email");
        String password = req.getParameter("signin-password");
        String identity = req.getParameter("identity");
        System.out.println(identity);
        System.out.println(username);
        System.out.println(password);
        if (identity.equals("admin")||username.matches("\\D+")){
            //如果用户登录成功，则返回index.html页面;否则重新获取login并且提示错误
            if (Uservice.Valid(username,password,req.getSession())){
                resp.sendRedirect("Uindex");
            }else {
                //需要从req中获取Session再添加failure字段
                req.getSession().setAttribute("failure",true);
                resp.sendRedirect("login");
            }
        }else if (identity.equals("stu")){
            int id = Integer.parseInt(username);
            if (Sservice.StuValid(id,password,req.getSession())){
                resp.sendRedirect("Sindex");
            }else {
                //需要从req中获取Session再添加failure字段
                req.getSession().setAttribute("failure",true);
                resp.sendRedirect("login");
            }
        }
    }
}
