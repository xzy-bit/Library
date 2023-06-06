package com.mylibrary.service.Implement;

import com.mylibrary.entity.Book;
import com.mylibrary.mapper.BookMapper;
import com.mylibrary.service.BookService;
import com.mylibrary.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.List;

public class BookImpl implements BookService {
    @Override
    public List<Book> getRandomBooks() {
        try(SqlSession session = MybatisUtil.getSession()){
            BookMapper mapper = session.getMapper(BookMapper.class);
            return mapper.getRandomBooks();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Book> getActiveBook() {
        try(SqlSession session = MybatisUtil.getSession()){
            BookMapper mapper = session.getMapper(BookMapper.class);
            return mapper.getActiveBook();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Book> getBookByVariety(String variety) {
        try(SqlSession session = MybatisUtil.getSession()){
            BookMapper mapper = session.getMapper(BookMapper.class);
            return mapper.getBookByVariety(variety);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Book> searchBook(String str) {
        try(SqlSession session = MybatisUtil.getSession()){
            BookMapper mapper = session.getMapper(BookMapper.class);
            List<Book> books = null;
            books = mapper.searchBookByid(str);
            books.addAll(mapper.searchBookByTitle(str));
            return books;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int getBookNum() {
        try(SqlSession session = MybatisUtil.getSession()){
            BookMapper mapper = session.getMapper(BookMapper.class);
            return mapper.getBookNum();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void addBook(String title, String variety, int num) {
        try(SqlSession session = MybatisUtil.getSession()){
            BookMapper mapper = session.getMapper(BookMapper.class);
            int varietyNum = mapper.getBookNumByVariety(variety)+1;
            String bid = "All_000";
            switch (variety){
                case "Science":
                    bid = "Sci_"+varietyNum;
                    break;
                case "History":
                    bid = "Hst_"+varietyNum;
                    break;
                case "Article":
                    bid = "Atc_"+varietyNum;
                    break;
                case "Finance":
                    bid = "Fnc_"+varietyNum;
                    break;
            }
            mapper.addBook(bid,title,variety,num);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateBook(String bid, String title, int num) {
        try(SqlSession session = MybatisUtil.getSession()){
            BookMapper mapper = session.getMapper(BookMapper.class);
            mapper.updateBook(bid,title,num);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteBookById(String bid) {
        try(SqlSession session = MybatisUtil.getSession()){
            BookMapper mapper = session.getMapper(BookMapper.class);
            mapper.deleteBook(bid);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
