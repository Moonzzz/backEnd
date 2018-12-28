package com.aiit.dao;

import com.aiit.pojo.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface IRecycleDao {

    public List<Reply> selectReplyAll(@Param("offset") int offset,@Param("limit") int limit);
    public int  selectReplyCount();
    public  int deleteOneReply(int id);
    public int revocationReplys(int id);

    public List<Post>  selectPostAll(@Param("offset") int offset,@Param("limit") int limit);
    public int  selectPostCount();
    public  int deleteOnePost(int id);
    public int revocationPosts(int id);

    public List<Actor> selectAll(@Param("offset") int offset,@Param("limit") int limit);
    public int  selectCount();
    public  int deleteOne(int id);
    public int revocationOne(int id);

    public List<Film> selectAllFlim(@Param("offset") int offset,@Param("limit") int limit);
    public int  selectFilmCount();
    public  int deleteOneFilm(int id);
    public int revocationFilms(int id);

    public List<Author> selectAllAuthor(@Param("offset")int offset, @Param("limit")int limit);
    public int  selectAuthorCount();
    public  int deleteOneAuthor(int id);
    public int revocationAuthors(int id);



    public List<Director> selectAllDirector(@Param("offset") int offset, @Param("limit")int limit);
    public int  selectDirectorCount();
    public  int deleteOneDirector(int id);
    public int revocationDirectors(int id);

    public List<Member> selectAllMember(@Param("offset") int offset,@Param("limit") int limit);
    public int  selectMemberCount();
    public  int deleteOneMember(int id);
    public int revocationMembers(int id);
}
