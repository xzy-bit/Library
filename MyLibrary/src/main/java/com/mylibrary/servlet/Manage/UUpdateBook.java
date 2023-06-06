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

@WebServlet("/updateBook")
public class UUpdateBook extends HttpServlet {
    BookService service;

    @Override
    public void init() throws ServletException {
        service = new BookImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bid =req.getParameter("bid");
        String variety = req.getParameter("variety");
        req.getSession().setAttribute("bid",bid);
        req.getSession().setAttribute("variety",variety);
        ThymeleafUtil.process("update-book.html",new Context(),resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bid = (String) req.getSession().getAttribute("bid");
        String variety = (String) req.getSession().getAttribute("variety");
        String title=req.getParameter("updateTitle");
        int num = Integer.parseInt(req.getParameter("updateNum"));
        System.out.println(variety);
        System.out.println(bid);
        System.out.println(title);
        System.out.println(num);
        if (num==0){
            service.deleteBookById(bid);

        }else {
            service.updateBook(bid, title, num);
        }
        resp.sendRedirect("books?variety="+variety);
    }
}
