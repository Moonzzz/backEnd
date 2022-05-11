package com.aiit.dao;

import com.aiit.pojo.Reply;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author Moon
 */

public interface FilmReplyDao {
    /**
     * 返回reply列表
     *
     * @param argMap offset   页号
     *               pageSize 页大小
     *               postId   postId
     * @return post集合
     */
    List<Reply> selectListByPostId(Map argMap);

    /**
     * 根据Id 和 likeNums 是否大于0进行更新 +1 || -1
     * @param argMap key replyId
     *               value likeNum
     * @return 返回受影响行数
     */
    int updateReplyLike(@Param("argMap") Map argMap);

    /**
     * 添加短评
     * @param reply 要添加的短评
     * @return 受影响行数
     */
    int insertReply(Reply reply);
}
