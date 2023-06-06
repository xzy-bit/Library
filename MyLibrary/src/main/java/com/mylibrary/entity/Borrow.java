package com.mylibrary.entity;

import lombok.Data;

import java.sql.Date;

@Data
public class Borrow {
    int stu_id;
    String bookId;
    String title;
    String stuName;
    Date startTime;
    String variety;
    int slot;
}
