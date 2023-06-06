package com.mylibrary.servlet.Pages;

import com.mylibrary.entity.Student;
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
import java.util.List;

@WebServlet("/Users")
public class UsersPage extends HttpServlet {
    StuService service;

    @Override
    public void init() throws ServletException {
        service = new StuImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Student> students = service.getAllStudent();
        Context context = new Context();
        context.setVariable("students",students);
        ThymeleafUtil.process("Users.html",context,resp.getWriter());
    }
}
