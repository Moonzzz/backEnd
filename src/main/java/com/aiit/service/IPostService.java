package com.aiit.service;

import com.aiit.pojo.Post;

import java.util.List;

/**
 * @author Moon
 */
public interface IPostService {
    /**
     * 返回post列表
     *
     * @param pageNo   页号
     * @param pageSize 页大小
     * @param method   排序方法
     * @return post集合
     */
    List<Post> getList(int pageNo, int pageSize, String method);

    /**
     * 按标题查找
     *
     * @param title 标题
     * @return postList
     */
    List<Post> getListByTitle(String title);

    /**
     * 按id删除
     *
     * @param id postId
     * @return isDone
     */
    boolean deletePost(Long id);

    /**
     * 修改帖子
     *
     * @param post 修改后的帖子
     * @return isDone
     */
    boolean updatePost(Post post);

    /**
     * 插入post
     *
     * @param post 插入的post
     * @return isDone
     */
    boolean addPost(Post post);
}
