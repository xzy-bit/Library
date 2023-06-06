package com.mylibrary.servlet.Pages;

import com.mylibrary.entity.Borrow;
import com.mylibrary.entity.Student;
import com.mylibrary.service.BorrowService;
import com.mylibrary.service.Implement.BorrowImpl;
import com.mylibrary.utils.ThymeleafUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.context.Context;

import java.io.IOException;
import java.util.List;

@WebServlet("/SBorrows")
public class SBorrows extends HttpServlet {
    BorrowService service;

    @Override
    public void init() throws ServletException {
        service = new BorrowImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Student student =(Student) req.getSession().getAttribute("student");
        int id = student.getId();
        Context context = new Context();
        List<Borrow> myBorrows = service.getBorrowsBySid(id);
        context.setVariable("myBorrows",myBorrows);
        ThymeleafUtil.process("StuBorrows.html",context,resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String str = req.getParameter("Ssearch");
        Student student =(Student) req.getSession().getAttribute("student");
        int id = student.getId();
        Context context = new Context();
        if (str!=null){
            context.setVariable("myBorrows",service.SgetBor(id,str));
            ThymeleafUtil.process("StuBorrows.html",context,resp.getWriter());
            return;
        }
        resp.sendRedirect("SBorrows");
    }
}
