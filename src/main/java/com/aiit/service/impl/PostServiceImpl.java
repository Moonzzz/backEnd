package com.aiit.service.impl;

import com.aiit.dao.IPostDao;
import com.aiit.pojo.Post;
import com.aiit.service.IPostService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
    private final Gson gson;

    @Autowired
    public PostServiceImpl(IPostDao postDao, GsonBuilder gsonBuilder) {
        this.postDao = postDao;
        this.gson = gsonBuilder.setDateFormat("yyyy-MM-hh HH:mm:ss").create();
    }

    @Override
    public String getList(int pageNo, int pageSize, String method) {
        int argCount = 3;
        Map<String, Object> argMap = new HashMap<>(argCount);
        argMap.put("offset", (pageNo-1)*pageSize);
        argMap.put("pageSize", pageSize);
        argMap.put("method", method);
        argMap.put("max",5000);
        List<Post> list = postDao.selectList(argMap);
        int count = postDao.count();
        String listJson = "{\"code\": 0, \"msg\": \"\", \"count\": "+count+", \"data\": ";
        listJson+=gson.toJson(list)+"}";
        return listJson;
    }

    @Override
    public List<Post> getListByTitle(String title) {
        title = "%" + title + "%";
        return postDao.queryByTitle(title);
    }

    @Override
    public boolean deletePost(Long id) {
        int rows = postDao.delete(id);

        return rows == 1;
    }

    @Override
    public boolean updatePost(Post post) {
        int rows = postDao.update(post);
        return rows == 1;
    }

    @Override
    public boolean addPost(Post post) {
        int rows = postDao.insert(post);
        return rows == 1;
    }
}
