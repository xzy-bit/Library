package com.mylibrary.servlet.Pages;

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

@WebServlet("/UBorrows")
public class UBorrows extends HttpServlet {
    BorrowService borrowService = new BorrowImpl();
    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Context context = new Context();
        context.setVariable("allborrows",borrowService.getAllBorrows());
        ThymeleafUtil.process("UserBorrows.html",context,resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String str = req.getParameter("search");
        Context context = new Context();
        if (str!=null){
            context.setVariable("allborrows",borrowService.UgetBor(str));
            ThymeleafUtil.process("UserBorrows.html",context,resp.getWriter());
            return;
        }
        resp.sendRedirect("Uborrows");
    }
}
