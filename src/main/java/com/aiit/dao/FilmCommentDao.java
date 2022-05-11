package com.aiit.dao;

import com.aiit.pojo.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author Moon
 */

public interface FilmCommentDao {
    /**
     * 返回comment列表
     *
     * @param argMap offset   页号
     *               pageSize 页大小
     *               filmId   fIlmId
     * @return post集合
     */
    List<Comment> selectListByFilmId(Map argMap);

    /**
     * 根据Id 和 likeNums 是否大于0进行更新 +1 || -1
     * @param argMap key commentId
     *               value likeNum
     * @return 返回受影响行数
     */
    int updateCommentLike(@Param("argMap") Map argMap);

    /**
     * 添加短评
     * @param comment 要添加的短评
     * @return 受影响行数
     */
    int insertComment(Comment comment);
}
