package com.aiit.service;

import com.aiit.pojo.*;

import java.util.List;

public interface IRecycleService {
 public List<Reply> showReplyAll(int offset, int limit);
 public int showReplyCount();
 public int  deleteReply(int id);
 public int  revocationReply(int id);


 public List<Post>  showPostAll(int offset, int limit);
 public int showPostCount();
 public int  deletePost(int id);
 public int  revocationPost(int id);

 //回收actor
 public List<Actor> showAll(int offset, int limit);
 public int showCount();
 public int  delete(int id);
 public int  revocationActor(int id);

 //回收Film
 public List<Film> showAllFilm(int offset, int limit);
 public int showFilmCount();
 public int deleteFilm(int id);
 public int  revocationFilm(int id);

 //回收Author
 public List<Author> showAllAuthor(int offset, int limit);
 public int showAuthorCount();
 public  int deleteAuthor(int id);
 public int  revocationAuthor(int id);

 //回收director
 public  List<Director> showAllDirector(int offset, int limit);
 public int showDirectorCount();
 public  int deleteDirector(int id);
 public int  revocationDirector(int id);


 //回收Member
 public List<Member> showAllMember(int offset, int limit);
 public int showMemberCount();
 public  int deleteMember(int id);
 public int  revocationMember(int id);
}
