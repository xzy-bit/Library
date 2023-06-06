package com.mylibrary.servlet.Manage;

import com.mylibrary.service.Implement.StuImpl;
import com.mylibrary.service.StuService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/deleteStu")
public class UDelStu extends HttpServlet {
    StuService service;

    @Override
    public void init() throws ServletException {
        service = new StuImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int sid = Integer.parseInt(req.getParameter("sid"));
        service.deleteStuById(sid);
        resp.sendRedirect("Users");
    }
}
