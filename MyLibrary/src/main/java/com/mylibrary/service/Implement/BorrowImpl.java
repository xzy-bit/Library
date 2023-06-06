package com.mylibrary.service.Implement;

import com.mylibrary.entity.Borrow;
import com.mylibrary.mapper.BookMapper;
import com.mylibrary.mapper.BorrowMapper;
import com.mylibrary.service.BorrowService;
import com.mylibrary.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

public class BorrowImpl implements BorrowService {
    @Override
    public List<Borrow> getAllBorrows() {
        try(SqlSession session = MybatisUtil.getSession()){
            BorrowMapper mapper = session.getMapper(BorrowMapper.class);
            return mapper.getAllBor();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Borrow> getBorrowsBySid(int id) {
        try(SqlSession session = MybatisUtil.getSession()){
            BorrowMapper mapper = session.getMapper(BorrowMapper.class);
            return mapper.getBorById(id);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Borrow> UgetBor(String str) {
        try(SqlSession session = MybatisUtil.getSession()){
            BorrowMapper mapper = session.getMapper(BorrowMapper.class);
            List<Borrow> borrows = mapper.UgetBorByName(str);
            borrows.addAll(mapper.UgetBorByTitle(str));
            return borrows;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Borrow> SgetBor(int sid,String str) {
        try(SqlSession session = MybatisUtil.getSession()){
            BorrowMapper mapper = session.getMapper(BorrowMapper.class);
            return mapper.SgetBorByTitle(sid,str);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteBor(int sid, String bid) {
        try(SqlSession session = MybatisUtil.getSession()){
            BorrowMapper mapper = session.getMapper(BorrowMapper.class);
            BookMapper bookMapper = session.getMapper(BookMapper.class);
            bookMapper.BookNumAdd(bid);
            mapper.deleteBor(sid,bid);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addBor(int sid, String bid, Date bor,int slot) {
        try(SqlSession session = MybatisUtil.getSession()){
            BorrowMapper mapper = session.getMapper(BorrowMapper.class);
            BookMapper bookMapper = session.getMapper(BookMapper.class);
            bookMapper.BookNumSub(bid);
            mapper.addBor(sid,bid,bor,slot);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getBorNum() {
        try(SqlSession session = MybatisUtil.getSession()){
            BorrowMapper mapper = session.getMapper(BorrowMapper.class);
            return mapper.getBorNum();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int getBorNumById(int sid) {
        try(SqlSession session = MybatisUtil.getSession()){
            BorrowMapper mapper = session.getMapper(BorrowMapper.class);
            return mapper.getBorNumById(sid);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
