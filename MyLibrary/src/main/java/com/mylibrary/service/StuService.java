package com.mylibrary.service;

import com.mylibrary.entity.Student;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public interface StuService {
    boolean StuValid(int id, String name, HttpSession session);
    List<Student> getAllStudent();
    void addStudent(int id,String name);
    boolean isIdExist(int id);
    int getStuNum();
    void deleteStuById(int sid);
}
