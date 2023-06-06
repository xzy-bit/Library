package com.mylibrary.servlet.Pages;

import com.mylibrary.entity.Student;
import com.mylibrary.service.BookService;
import com.mylibrary.service.BorrowService;
import com.mylibrary.service.Implement.BookImpl;
import com.mylibrary.service.Implement.BorrowImpl;
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

@WebServlet("/Sindex")
public class StuIndex extends HttpServlet {
    BookService service;
    BorrowService borrowService;
    StuService stuService;
    @Override
    public void init() throws ServletException {
        service = new BookImpl();
        borrowService = new BorrowImpl();
        stuService = new StuImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Context context = new Context();
        Student student = (Student) req.getSession().getAttribute("student");
        int id = student.getId();
        context.setVariable("sbooknum",service.getBookNum());
        context.setVariable("myborrownum",borrowService.getBorNumById(id));
        context.setVariable("sstunum",stuService.getStuNum());
        context.setVariable("randomBooks",service.getRandomBooks());
        ThymeleafUtil.process("StudentIndex.html",context,resp.getWriter());
    }
}
