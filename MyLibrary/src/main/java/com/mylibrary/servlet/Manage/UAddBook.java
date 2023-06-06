package com.mylibrary.servlet.Manage;

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

@WebServlet("/UAddBook")
public class UAddBook extends HttpServlet {
    BookService service;

    @Override
    public void init() throws ServletException {
        service = new BookImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ThymeleafUtil.process("Uadd-book.html",new Context(),resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("bookTitle");
        int num = Integer.parseInt(req.getParameter("bookNum"));
        String variety = req.getParameter("bookVariety");
        System.out.println(title);
        System.out.println(num);
        System.out.println(variety);
        service.addBook(title,variety,num);
        resp.sendRedirect("books");
    }
}
