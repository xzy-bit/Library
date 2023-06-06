package com.mylibrary.servlet.Manage;

import com.mylibrary.entity.User;
import com.mylibrary.service.BorrowService;
import com.mylibrary.service.Implement.BorrowImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/return-book")
public class ReturnBook extends HttpServlet {
    BorrowService service;
    @Override
    public void init() throws ServletException {
        service=new BorrowImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("sid"));
        String bid = req.getParameter("bid");
        User user =(User) req.getSession().getAttribute("user");
        if (user!=null){
            service.deleteBor(id,bid);
            resp.sendRedirect("UBorrows");
        }else {
            service.deleteBor(id,bid);
            resp.sendRedirect("SBorrows");
        }

    }
}
