package com.mylibrary.servlet.Log;

import com.mylibrary.service.Implement.StuImpl;
import com.mylibrary.service.StuService;
import com.mylibrary.utils.ThymeleafUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.context.Context;

import java.io.IOException;

@WebServlet("/signup")
public class SignUp extends HttpServlet {
    StuService service;

    @Override
    public void init() throws ServletException {
        service = new StuImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Context context = new Context();
        if (req.getSession().getAttribute("idexist")!=null){
            context.setVariable("idexist",true);
            req.getSession().removeAttribute("idexist");
        }
        ThymeleafUtil.process("signup.html",context,resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("stuid"));
        String name = req.getParameter("stuname");
        if (service.isIdExist(id)){
            req.getSession().setAttribute("idexist",true);
            resp.sendRedirect("signup");
        }else{
            service.addStudent(id,name);
            resp.sendRedirect("login");
        }
    }
}
