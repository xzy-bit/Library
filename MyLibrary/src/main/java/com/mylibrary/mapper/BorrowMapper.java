package com.mylibrary.mapper;


import com.mylibrary.entity.Borrow;
import org.apache.ibatis.annotations.*;

import java.sql.Date;
import java.util.List;
public interface BorrowMapper {
    @Select("select * from borrow,book,student where book.book_id = borrow.book_id and borrow.stu_id=student.stu_id")
    @Results({
            @Result(column = "stu_id",property = "stu_id"),
            @Result(column = "book_id",property = "bookId"),
            @Result(column = "title",property = "title"),
            @Result(column = "name",property = "stuName"),
            @Result(column = "bor_date",property = "startTime"),
            @Result(column = "slot",property = "slot"),
            @Result(column = "variety",property = "variety")
    })
    List<Borrow> getAllBor();

    @Select("select * from borrow,book,student where book.book_id = borrow.book_id and borrow.stu_id=student.stu_id and student.stu_id=#{id}")
    @Results({
            @Result(column = "stu_id",property = "stu_id"),
            @Result(column = "book_id",property = "bookId"),
            @Result(column = "title",property = "title"),
            @Result(column = "name",property = "stuName"),
            @Result(column = "bor_date",property = "startTime"),
            @Result(column = "variety",property = "variety"),
            @Result(column = "slot",property = "slot")
    })
    List<Borrow> getBorById(@Param("id") int id);

    @Select("select * from borrow,book,student where book.book_id = borrow.book_id and borrow.stu_id=student.stu_id and name like '%${str}%'")
    @Results({
            @Result(column = "stu_id",property = "stu_id"),
            @Result(column = "book_id",property = "bookId"),
            @Result(column = "title",property = "title"),
            @Result(column = "name",property = "stuName"),
            @Result(column = "bor_date",property = "startTime"),
            @Result(column = "slot",property = "slot"),
            @Result(column = "variety",property = "variety")
    })
    List<Borrow> UgetBorByName(@Param("str")String str);
    @Select("select * from borrow,book,student where book.book_id = borrow.book_id and borrow.stu_id=student.stu_id and title like '%${str}%'")
    @Results({
            @Result(column = "stu_id",property = "stu_id"),
            @Result(column = "book_id",property = "bookId"),
            @Result(column = "title",property = "title"),
            @Result(column = "name",property = "stuName"),
            @Result(column = "bor_date",property = "startTime"),
            @Result(column = "slot",property = "slot"),
            @Result(column = "variety",property = "variety")
    })
    List<Borrow> UgetBorByTitle(@Param("str")String str);

    @Select("select * from borrow,book,student where book.book_id = borrow.book_id and borrow.stu_id=student.stu_id and borrow.stu_id=#{sid} and title like '%${str}%'")
    @Results({
            @Result(column = "stu_id",property = "stu_id"),
            @Result(column = "book_id",property = "bookId"),
            @Result(column = "title",property = "title"),
            @Result(column = "name",property = "stuName"),
            @Result(column = "bor_date",property = "startTime"),
            @Result(column = "slot",property = "slot"),
            @Result(column = "variety",property = "variety")
    })
    List<Borrow> SgetBorByTitle(@Param("sid") int sid,@Param("str")String str);

    @Select("select count(*) from borrow")
    int getBorNum();

    @Select("select count(*) from borrow where stu_id=#{sid}")
    int getBorNumById(@Param("sid")int sid);

    @Delete("delete from borrow where stu_id=#{sid} and book_id=#{bid}")
    void deleteBor(@Param("sid") int sid,@Param("bid") String bid);

    @Insert("insert into borrow values(#{sid},#{bid},#{bor_date},#{slot})")
    void addBor(@Param("sid")int sid, @Param("bid") String bid, @Param("bor_date")Date bor_date,@Param("slot")int slot);
}
