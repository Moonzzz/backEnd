package com.aiit.dao;


import com.aiit.pojo.Post;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author Moon
 */
public interface IPostDao {
    /**
     * 返回post列表
     *
     * @param argMap
     * pageNo   页号
     * pageSize 页大小
     * method   排序方法
     * @return post集合
     */
    List<Post> selectList(Map argMap);

    /**
     * 按标题查找
     *
     * @param title 标题
     * @return postList
     */
    @Select("select * from post where title like #{title}")
    List<Post> queryByTitle(String title);

    /**
     * 按id删除
     *
     * @param id postId
     * @return 受影响行数
     */
    @Delete("delete from post where id = #{id}")
    int delete(Long id);


    /**
     * 修改帖子
     *
     * @param post 修改后的帖子
     * @return 受影响行数
     */
    int update(Post post);


    /**
     * 插入post
     *
     * @param post 插入的post
     * @return 受影响行数
     */
    @Insert("insert into post values(#{id},#{memberId},#{title},#{content},#{datePublished},#{dateLastModified});")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    int insert(Post post);

    /**
     * 返回总记录数
     * @return 总记录数
     */
    @Select("select count(id) from post")
    int count();
}
