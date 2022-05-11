package com.aiit.dao.filmdao;

import com.aiit.pojo.filmpojo.Actor;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IActorDao {
    @Select("select * from actor LIMIT #{offset},#{limit}")
    List<Actor> showActors(@Param("offset") int offset, @Param("limit") int limit);
    @Select("select count(*) from actor")
    int showCount();
    @Delete("delete from actor where id=#{id}")
    int delActor(int id);
    @Insert("INSERT INTO actor (name,url) VALUES(#{name},#{url})")
    int addActor(Actor actor);
}
