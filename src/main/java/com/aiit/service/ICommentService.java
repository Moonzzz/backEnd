package com.aiit.service;

import com.aiit.pojo.FilmComment;
import com.aiit.pojo.Member;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ICommentService {
    boolean setCommentPic(FilmComment filmComment, MultipartFile picFile);

    boolean setCommentPicVideo(FilmComment filmComment, MultipartFile picFile, MultipartFile videoFile);

    int deleteCommentList(String[] ids);

    boolean deleteCommentById(String id);

    List<FilmComment> searchFilmCommentBymId(String mId);

    boolean addViewNumById(String id);

    List<FilmComment> searchFilmCommentByfId(String fId);

    String getSuffix(String originalName);

    List<FilmComment> randomSearchFilmComments();

    FilmComment searchFilmCommentById(String id);

    Member searchMemberByName(String name);

    String getCurrentTime();

    boolean addFilmComment(FilmComment filmComment);

    int getSecondTimestamp();

    boolean deleteFile(String fileName);

    boolean upLoadPhoto(MultipartFile file, String realPath);

    boolean upLoadVideo(MultipartFile file, String realPath);

    List<FilmComment> showAllFComments();
}
