package com.aiit.service;

import com.aiit.dao.FilmCommentDao;
import com.aiit.pojo.Comment;
import com.aiit.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Moon
 */
@Service
public class FilmCommentService {

    private final
    FilmCommentDao filmCommentDao;

    @Autowired
    public FilmCommentService(FilmCommentDao filmCommentDao) {
        this.filmCommentDao = filmCommentDao;
    }

    public List<Comment> getListByFilmId(int pageNo, int pageSize, Long filmId) {
        Map<String, Object> argMap = new HashMap<>(3);
        argMap.put("offset", (pageNo - 1) * pageSize);
        argMap.put("pageSize", pageSize);
        argMap.put("filmId", filmId);
        return filmCommentDao.selectListByFilmId(argMap);
    }

    public boolean updateLikeNum(Map argMap) {
        return filmCommentDao.updateCommentLike(argMap) > 0;
    }

    public boolean addComment(Comment comment) {

        String format = "yyyy-MM-dd hh:mm:ss";
        comment.setDatePublished(DateUtil.getDateFormat(format));
        return filmCommentDao.insertComment(comment) > 0;
    }
}
