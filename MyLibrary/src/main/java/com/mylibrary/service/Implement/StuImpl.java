package com.mylibrary.service.Implement;

import com.mylibrary.entity.Student;
import com.mylibrary.mapper.StuMapper;
import com.mylibrary.service.StuService;
import com.mylibrary.utils.MybatisUtil;
import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.List;

public class StuImpl implements StuService {
    @Override
    public boolean StuValid(int id, String name, HttpSession session) {
        try (SqlSession sqlSession = MybatisUtil.getSession()) {
            StuMapper mapper = sqlSession.getMapper(StuMapper.class);
            Student student = mapper.StuVerify(id, name);
            if (student != null) {
                session.setAttribute("student", student);
                System.out.println(student);
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Student> getAllStudent() {
        try (SqlSession sqlSession = MybatisUtil.getSession()) {
            StuMapper mapper = sqlSession.getMapper(StuMapper.class);
            return mapper.getAllStudent();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addStudent(int id,String name) {
        try (SqlSession sqlSession = MybatisUtil.getSession()) {
            StuMapper mapper = sqlSession.getMapper(StuMapper.class);
            mapper.addStudent(id,name);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isIdExist(int id) {
        try (SqlSession sqlSession = MybatisUtil.getSession()) {
            StuMapper mapper = sqlSession.getMapper(StuMapper.class);
            Student student = mapper.getStuById(id);
            if (student==null){
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public int getStuNum() {
        try (SqlSession sqlSession = MybatisUtil.getSession()) {
            StuMapper mapper = sqlSession.getMapper(StuMapper.class);
            return mapper.getStuNum();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void deleteStuById(int sid) {
        try (SqlSession sqlSession = MybatisUtil.getSession()) {
            StuMapper mapper = sqlSession.getMapper(StuMapper.class);
            mapper.deleteStuBySid(sid);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
