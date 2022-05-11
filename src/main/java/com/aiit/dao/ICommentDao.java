package com.aiit.dao;

import com.aiit.pojo.FilmComment;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @auther Mr Tang
 * @Date 2018/12/28 17:50
 */
public interface ICommentDao {

    @Delete({
            "<script>" +
                    "delete from post where id in"
                    + "<foreach item='status' index='index' collection='array' open='(' separator=',' close=')'>"
                    + "#{status} "
                    + "</foreach>" +
                    "</script>"
    })
    int deleteCommentList(String[] ids);

    @Delete("DELETE FROM post WHERE id = #{id}")
    boolean deleteCommentById(@Param("id") String id);

    @Select("SELECT\n" +
            "`post`.id as id,\n" +
            "`post`.mId as mId,\n" +
            "`post`.fId as fId,\n" +
            "`post`.title,\n" +
            "`post`.content,\n" +
            "`post`.date,\n" +
            "`post`.viewnum,\n" +
            "`post`.image,\n" +
            "member.loginName as mName,\n" +
            "member.imgsrc as mImgsrc,\n" +
            "film.`name` as fName\n" +
            "FROM\n" +
            "`post`\n" +
            "INNER JOIN member ON `post`.mId = member.id\n" +
            "INNER JOIN film ON `post`.fId = film.id\n" +
            "WHERE\n" +
            "post.mId=#{mId}")
    List<FilmComment> searchFilmCommentBymId(@Param("mId") String mId);

    @Select("SELECT `post`.viewnum FROM`post`\n" +
            "WHERE `post`.id=#{id}")
    String selectViewNumById(@Param("id") String id);

    @Update("update post set viewnum=#{num} where id=#{id}")
    boolean addViewNumById(@Param("id") String id, @Param("num") int num);

    @Insert("INSERT into `post`(fId,mId,title,content,date,image,video)\n" +
            "VALUES(#{fId},#{mId},#{title},#{content},#{date},#{image},#{video})")
    boolean addFilmComment(FilmComment filmComment);

    @Select("SELECT\n" +
            "`post`.mId as mId,\n" +
            "`post`.fId as fId,\n" +
            "`post`.title,\n" +
            "`post`.content,\n" +
            "`post`.date,\n" +
            "`post`.viewnum as viewNum,\n" +
            "`post`.image,\n" +
            "`post`.video,\n" +
            "member.loginName as mName,\n" +
            "member.imgsrc as mImgsrc,\n" +
            "film.`name` as fName\n" +
            "FROM\n" +
            "`post`\n" +
            "INNER JOIN member ON `post`.mId = member.id\n" +
            "INNER JOIN film ON `post`.fId = film.id\n" +
            "WHERE\n" +
            "post.id=#{id}")
    FilmComment searchFilmCommentById(@Param("id") String id);

    @Select("SELECT\n" +
            "`post`.id as id,\n" +
            "`post`.mId as mId,\n" +
            "`post`.fId as fId,\n" +
            "`post`.title,\n" +
            "`post`.content,\n" +
            "`post`.date,\n" +
            "`post`.viewnum,\n" +
            "`post`.image,\n" +
            "`post`.video,\n" +
            "member.loginName as mName,\n" +
            "member.imgsrc as mImgsrc,\n" +
            "film.`name` as fName,\n" +
            "film.`description` as fDescrip\n" +
            "FROM\n" +
            "`post`\n" +
            "INNER JOIN member ON `post`.mId = member.id\n" +
            "INNER JOIN film ON `post`.fId = film.id\n" +
            "WHERE\n" +
            "post.fId=#{fId}")
    List<FilmComment> searchFilmCommentByfId(@Param("fId") String fId);

    @Select("SELECT\n" +
            "`post`.title,\n" +
            "`post`.content,\n" +
            "`post`.date,\n" +
            "`post`.viewnum as viewNum,\n" +
            "`post`.image,\n" +
            "`post`.video,\n" +
            "member.loginName AS mName,\n" +
            "member.id AS mId,\n" +
            "member.`imgsrc` AS mImgsrc,\n" +
            "film.`name` AS fName,\n" +
            "film.`id` AS fId,\n" +
            "film.`description` AS fDescrip,\n" +
            "post.`mId` AS mId,\n" +
            "`post`.id\n" +
            "FROM\n" +
            "`post`\n" +
            "INNER JOIN member ON `post`.mId = member.id\n" +
            "INNER JOIN film ON `post`.fId = film.id\n" +
            //"WHERE `post`.id >= (SELECT FLOOR( MAX(`post`.id) * RAND()) FROM `post` )\n" +
            "WHERE `post`.id >0 ORDER BY  RAND() LIMIT 5")
    List<FilmComment> randomSearchFilmComments();

    @Select("SELECT\n" +
            "`post`.id,\n" +
            "`post`.title,\n" +
            "`post`.content,\n" +
            "`post`.date,\n" +
            "`post`.image,\n" +
            "film.`name` as fName,\n" +
            "member.loginName as mName\n" +
            "FROM\n" +
            "`post`\n" +
            "INNER JOIN film ON `post`.fId = film.id\n" +
            "INNER JOIN member ON `post`.mId = member.id")
    List<FilmComment> showAllFComments();
}
