package com.mylibrary.service;

import com.mylibrary.entity.Book;

import java.util.List;

public interface BookService {
    List<Book> getRandomBooks();
    List<Book> getActiveBook();
    List<Book> getBookByVariety(String variety);
    List<Book> searchBook(String str);
    int getBookNum();
    void addBook(String title,String variety,int num);
    void updateBook(String bid,String title,int num);
    void deleteBookById(String bid);
}
