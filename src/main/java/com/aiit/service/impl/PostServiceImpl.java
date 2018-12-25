package com.aiit.service.impl;

import com.aiit.dao.IPostDao;
import com.aiit.pojo.Post;
import com.aiit.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Moon
 */
@Service
public class PostServiceImpl implements IPostService {
    private final IPostDao postDao;

    @Autowired
    public PostServiceImpl(IPostDao postDao) {
        this.postDao = postDao;
    }

    @Override
    public List<Post> getList(int pageNo, int pageSize, String method) {
        int argCount = 3;
        Map<String, Object> argMap = new HashMap<>(argCount);
        argMap.put("pageNo", pageNo);
        argMap.put("pageSize", pageSize);
        argMap.put("method", method);
        List<Post> list = postDao.selectList(argMap);
        return list;
    }

    @Override
    public List<Post> getListByTitle(String title) {
        title = "%"+title+"%";
        List<Post> list = postDao.queryByTitle(title);
        return list;
    }

    @Override
    public boolean deletePost(Long id)
    {
        int rows = postDao.delete(id);

        return rows==1;
    }

    @Override
    public boolean updatePost(Post post) {
        int rows = postDao.update(post);
        return rows==1;
    }

    @Override
    public boolean addPost(Post post) {
        int rows = postDao.insert(post);
        return rows==1;
    }
}
