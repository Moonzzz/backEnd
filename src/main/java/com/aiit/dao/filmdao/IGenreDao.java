package com.aiit.dao.filmdao;

import com.aiit.pojo.filmpojo.Genre;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IGenreDao {
    @Select("select * from genre")
    List<Genre> getGenres();
}
