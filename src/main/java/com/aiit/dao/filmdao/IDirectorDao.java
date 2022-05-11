package com.aiit.dao.filmdao;

import com.aiit.pojo.filmpojo.Director;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IDirectorDao {
    @Select("select * from director LIMIT #{offset},#{limit}")
    public List<Director> showDirector(@Param("offset") int offset, @Param("limit") int limit);
    @Select("select count(*) from director")
    public int showCount();
    @Delete("delete from director where id=#{id}")
    public int delDirector(int id);
    @Insert("INSERT INTO director (name,url) VALUES(#{name},#{url})")
    public int addDirector(Director director);
}
