package com.aiit.test;

import com.aiit.service.ICommentService;
import com.aiit.service.IFilmPageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring/spring_*.xml"})
public class test {
    @Autowired
    IFilmPageService filmPageService;
    @Autowired
    ICommentService commentService;
    @Test
    public void t2() {
        System.out.println(commentService.randomSearchFilmComments().toString());
    }
}
