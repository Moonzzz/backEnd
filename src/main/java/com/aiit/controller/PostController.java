package com.aiit.controller;

import com.aiit.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Moon
 */
@RestController
@RequestMapping("/post")
public class PostController {

    private final IPostService postService;

    @Autowired
    public PostController(IPostService postService) {
        this.postService = postService;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getList(int page, int limit,@RequestParam(defaultValue = "id") String method) {
        return postService.getList(page, limit, method);
    }
}
