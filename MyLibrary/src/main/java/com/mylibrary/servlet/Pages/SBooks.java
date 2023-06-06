package com.mylibrary.servlet.Pages;

import com.mylibrary.service.BookService;
import com.mylibrary.service.Implement.BookImpl;
import com.mylibrary.utils.ThymeleafUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.context.Context;

import java.io.IOException;

@WebServlet("/stu-books")
public class SBooks extends HttpServlet {
    BookService service;

    @Override
    public void init() throws ServletException {
        service = new BookImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Context context = new Context();
        String variety = req.getParameter("variety");
        System.out.println(variety);
        context.setVariable("stubooks",service.getBookByVariety(variety));
        ThymeleafUtil.process("stu-books.html",context,resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String str = req.getParameter("stusearch");
        Context context = new Context();
        if (str!=null){
            context.setVariable("stubooks",service.searchBook(str));
            ThymeleafUtil.process("stu-books.html",context,resp.getWriter());
            return;
        }
        resp.sendRedirect("stu-books");
    }
}
