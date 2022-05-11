package com.aiit.dao.filmdao;

import com.aiit.pojo.filmpojo.Author;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IAuthorDao {
    @Select("select * from author LIMIT #{offset},#{limit}")
    List<Author> showAuthor(@Param("offset") int offset, @Param("limit") int limit);
    @Select("select count(*) from author")
    int showCount();
    @Delete("delete from author where id=#{id}")
    int delAuthor(int id);
    @Insert("INSERT INTO author (name,url) VALUES(#{name},#{url})")
    int addAuthor(Author author);
}
