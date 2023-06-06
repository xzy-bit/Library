package com.mylibrary.servlet.Manage;

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
import java.sql.Date;

@WebServlet("/SAddBor")
public class SAddBor extends HttpServlet {
    StuService stuService;
    BookService bookService;
    BorrowService borrowService;

    @Override
    public void init() throws ServletException {
        stuService = new StuImpl();
        bookService = new BookImpl();
        borrowService = new BorrowImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Context context = new Context();
        context.setVariable("act_book",bookService.getActiveBook());
        ThymeleafUtil.process("Sadd-borrow.html",context,resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Student student = (Student) req.getSession().getAttribute("student");
        int sid = student.getId();
        int slot = req.getParameter("time").toCharArray()[0]-'0';
        String bid = req.getParameter("book");
        System.out.println(sid);
        System.out.println(slot);
        System.out.println(bid);
        borrowService.addBor(sid,bid,new Date(System.currentTimeMillis()),slot);
        resp.sendRedirect("SBorrows");
    }
}
