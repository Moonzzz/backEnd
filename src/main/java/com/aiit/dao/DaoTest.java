package com.aiit.dao;

import com.aiit.pojo.Film;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DaoTest {

    @Select("SELECT\n" +
            "genre.`name`,\n" +
            "film.`name` AS image,\n" +
            "film.id\n" +
            "FROM\n" +
            "film\n" +
            "INNER JOIN filmandgenre ON filmandgenre.fId = film.id\n" +
            "INNER JOIN genre ON filmandgenre.gId = genre.id")
    public List<Film> returnAllGenre();


    @Select("select name from film")
    List<String> daoTest();

    @Select("select id from filmandactor where fid=42")
    List<String> test();
}
