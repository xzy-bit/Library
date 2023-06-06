package com.mylibrary.service;

import com.mylibrary.entity.Borrow;

import java.sql.Date;
import java.util.List;

public interface BorrowService {
    List<Borrow> getAllBorrows();
    List<Borrow> getBorrowsBySid(int id);
    List<Borrow> UgetBor(String str);
    List<Borrow> SgetBor(int sid,String str);
    void deleteBor(int sid,String bid);
    void addBor(int sid, String bid, Date bor,int slot);
    int getBorNum();
    int getBorNumById(int sid);

}
