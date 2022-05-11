package com.aiit.service;

import com.aiit.dao.FilmReplyDao;
import com.aiit.pojo.Reply;
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
public class FilmReplyService {

    private final
    FilmReplyDao filmReplyDao;

    @Autowired
    public FilmReplyService(FilmReplyDao filmReplyDao) {
        this.filmReplyDao = filmReplyDao;
    }

    public List<Reply> getListByPostId(int pageNo, int pageSize, Long postId) {
        Map<String, Object> argMap = new HashMap<>(3);
        argMap.put("offset", (pageNo - 1) * pageSize);
        argMap.put("pageSize", pageSize);
        argMap.put("postId", postId);
        return filmReplyDao.selectListByPostId(argMap);
    }

    public boolean updateLikeNum(Map argMap) {
        return filmReplyDao.updateReplyLike(argMap) > 0;
    }

    public boolean addreply(Reply reply) {
        String format = "yyyy-MM-dd hh:mm:ss";
        reply.setDatePublished(DateUtil.getDateFormat(format));
        return filmReplyDao.insertReply(reply) > 0;
    }
}
