package com.mylibrary.mapper;

import com.mylibrary.entity.Book;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface BookMapper {
    @Select("select * from book order by rand() limit 10")
    @Results({
            @Result(column = "book_id",property = "id"),
            @Result(column = "title",property = "title"),
            @Result(column = "variety",property = "variety"),
            @Result(column = "num",property = "num")
    })
    List<Book> getRandomBooks();

    @Select("select * from book where num>0")
    @Results({
            @Result(column = "book_id",property = "id"),
            @Result(column = "title",property = "title"),
            @Result(column = "variety",property = "variety"),
            @Result(column = "num",property = "num")
    })
    List<Book> getActiveBook();

    @Update("update book set num=num+1 where book_id=#{bid}")
    void BookNumAdd(@Param("bid")String bid);

    @Update("update book set num=num-1 where book_id=#{bid}")
    void BookNumSub(@Param("bid")String bid);

    @Select("select * from book where variety=#{variety}")
    @Results({
            @Result(column = "book_id",property = "id"),
            @Result(column = "title",property = "title"),
            @Result(column = "variety",property = "variety"),
            @Result(column = "num",property = "num")
    })
    List<Book> getBookByVariety(@Param("variety")String variety);

    @Select("select * from book where title like '%${str}%'")
    @Results({
            @Result(column = "book_id",property = "id"),
            @Result(column = "title",property = "title"),
            @Result(column = "variety",property = "variety"),
            @Result(column = "num",property = "num")
    })
    List<Book> searchBookByTitle(String str);

    @Select("select * from book where book_id like '%${str}%'")
    @Results({
            @Result(column = "book_id",property = "id"),
            @Result(column = "title",property = "title"),
            @Result(column = "variety",property = "variety"),
            @Result(column = "num",property = "num")
    })
    List<Book> searchBookByid(String str);

    @Select("select count(*) from book")
    int getBookNum();

    @Select("select count(*) from book where variety=#{variety}")
    int getBookNumByVariety(@Param("variety")String variety);

    @Insert("insert into book values(#{bid},#{title},#{variety},#{num})")
    void addBook(@Param("bid")String bid,@Param("title")String title,@Param("variety")String variety,@Param("num")int num);

    @Update("update book set title=#{title},num=#{num} where book_id=#{bid}")
    void updateBook(@Param("bid")String bid,@Param("title")String title,@Param("num") int num);

    @Delete("delete from book where book_id=#{bid}")
    void deleteBook(@Param("bid")String bid);
}
