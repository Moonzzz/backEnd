package com.aiit.dao;

import com.aiit.pojo.Film;
import com.aiit.pojo.FilmDetail;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @auther Mr Tang
 * @Date 2018/12/24 21:54
 */
public interface IFilmPageDao {

    @Select("SELECT DISTINCT\n" +
            "film.image,\n" +
            "film.`name`,\n" +
            "film.id,\n" +
            "film.datePublished,\n" +
            "genre.`name` as genre,\n" +
            "rating.ratingValue\n" +
            "FROM\n" +
            "film\n" +
            "INNER JOIN filmandgenre ON filmandgenre.fId = film.id\n" +
            "INNER JOIN genre ON filmandgenre.gId = genre.id\n" +
            "INNER JOIN rating ON rating.fId = film.id\n" +
            "WHERE\n" +
            "film.id <> #{fId} AND\n" +
            "genre.`name` = #{type} AND\n" +
            "rating.ratingValue>8\n" +
            "ORDER BY  RAND() LIMIT 3")
    List<FilmDetail> SimilarFilms(@Param("fId") String fId,
                                  @Param("type") String type);

    @Select("SELECT DISTINCT\n" +
            "genre.`name`\n" +
            "FROM\n" +
            "film\n" +
            "INNER JOIN filmandgenre ON filmandgenre.fId = film.id\n" +
            "INNER JOIN genre ON filmandgenre.gId = genre.id\n" +
            "WHERE\n" +
            "film.id = #{fId}\n")
    String returnTypeByfId(@Param("fId") String fId);

    @Select("SELECT DISTINCT \n" +
            "rating.ratingValue,\n" +
            "film.id,\n" +
            "film.image,\n" +
            "film.`name`\n" +
            "FROM\n" +
            "film\n" +
            "INNER JOIN filmandgenre ON filmandgenre.fId = film.id\n" +
            "INNER JOIN genre ON filmandgenre.gId = genre.id\n" +
            "INNER JOIN rating ON rating.fId = film.id\n" +
            "WHERE\n" +
            "film.`nation` LIKE CONCAT('%',#{nation},'%')\n" +
            "and genre.`name` LIKE CONCAT('%',#{type},'%')\n" +
            "and datePublished BETWEEN #{start} AND #{end}")
    List<FilmDetail> searchFilmByType_Day(@Param("nation") String nation,
                                          @Param("type") String type,
                                          @Param("start") String start,
                                          @Param("end") String end);

    @Select("select name from genre")
    List<String> returnAllFilmTypes();

    @Select("select * from film where id=#{id}")
    Film searchFilmById(@Param("id") String id);

    @Select("SELECT\n" +
            "actor.`name` AS actors,\n" +
            "film.id\n" +
            "FROM\n" +
            "film\n" +
            "INNER JOIN filmandactor ON filmandactor.fId = film.id\n" +
            "INNER JOIN actor ON filmandactor.aId = actor.id\n" +
            "WHERE\n" +
            "film.id=#{id}\n")
    List<String> searchActorsById(@Param("id") String id);

    @Select("SELECT\n" +
            "director.`name` AS directors,\n" +
            "film.id\n" +
            "FROM\n" +
            "film\n" +
            "INNER JOIN filmanddirector ON filmanddirector.fId = film.id\n" +
            "INNER JOIN director ON filmanddirector.dId = director.id\n" +
            "WHERE\n" +
            "film.id=#{id}\n")
    List<String> searchDirectorsById(@Param("id") String id);

    @Select("SELECT\n" +
            "film.`name`,\n" +
            "film.image,\n" +
            "film.datePublished,\n" +
            "film.description,\n" +
            "rating.count,\n" +
            "rating.ratingValue,\n" +
            "film.id\n" +
            "FROM\n" +
            "film\n" +
            "INNER JOIN rating ON rating.fId = film.id\n" +
            "WHERE\n" +
            "film.id=#{id}")
    FilmDetail searchFilmDetailById(@Param("id") String id);

    @Select("SELECT distinct \n" +
            "film.id,\n" +
            "film.`name`,\n" +
            "film.image,\n" +
            "film.datePublished,\n" +
            "film.description,\n" +
            "rating.ratingValue,\n" +
            "rating.count,\n" +
            "genre.`name` as genre\n" +
            "FROM\n" +
            "film\n" +
            "INNER JOIN rating ON rating.fId = film.id\n" +
            "INNER JOIN filmandgenre ON filmandgenre.fId = film.id\n" +
            "INNER JOIN genre ON filmandgenre.gId = genre.id\n" +
            "ORDER BY datePublished desc LIMIT 5 ;")
    List<FilmDetail> randomSarchFilms();

    @Select("SELECT\n" +
            "film.id,\n" +
            "film.`name`,\n" +
            "rating.ratingValue\n" +
            "FROM\n" +
            "film\n" +
            "INNER JOIN rating ON rating.fId = film.id\n" +
            "WHERE\n" +
            "rating.ratingValue >8\n" +
            "ORDER BY  RAND() LIMIT 10")
    List<FilmDetail> showTenFilms();

    @Select("SELECT distinct \n" +
            "film.id,\n" +
            "film.`name`,\n" +
            "film.image,\n" +
            "film.datePublished,\n" +
            "film.description,\n" +
            "rating.ratingValue,\n" +
            "rating.count,\n" +
            "genre.`name` as genre\n" +
            "FROM\n" +
            "film\n" +
            "INNER JOIN rating ON rating.fId = film.id\n" +
            "INNER JOIN filmandgenre ON filmandgenre.fId = film.id\n" +
            "INNER JOIN genre ON filmandgenre.gId = genre.id\n" +
            "WHERE\n" +
            "film.`name` LIKE CONCAT('%',#{name},'%')")
    List<FilmDetail> searchFilmDeatilsByName(@Param("name") String name);

    /*CONCAT('%',#{name},'%')*/
    @Select("SELECT\n" +
            "film.`name`\n" +
            "FROM\n" +
            "film\n" +
            "WHERE\n" +
            "film.`name` LIKE CONCAT('%',#{name},'%')")
    List<String> returnSearchNames(@Param("name") String name);
}
