package com.aiit.dao.filmdao;

import com.aiit.pojo.filmpojo.Film;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface IFilmDao {
    @Select("select * from film LIMIT #{offset},#{limit}")
    List<Film> selectAll(@Param("offset") int offset, @Param("limit") int limit);
    @Select("select count(*) from film")
    int showCount();
    @Delete("delete from film where fNo=#{fNo}")
    int delFilm(String fNo);
    @Update("update film set datePublished=#{datePublished} where fNo=#{fNo}")
    int modFilm(Film film);
    @Select("SELECT\n" +
            "genre.`name` AS genre,\n" +
            "film.fNo,\n" +
            "film.`name`,\n" +
            "film.url,\n" +
            "film.image,\n" +
            "film.datePublished,\n" +
            "film.description\n" +
            "FROM\n" +
            "film\n" +
            "INNER JOIN filmandgenre ON filmandgenre.fId = film.id\n" +
            "INNER JOIN genre ON filmandgenre.gId = genre.id\n"+
             "LIMIT #{offset},#{limit}\n")
    List<Film> getFilm(@Param("offset") int offset, @Param("limit") int limit);
}
