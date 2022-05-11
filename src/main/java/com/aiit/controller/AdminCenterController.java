package com.aiit.controller;

import com.aiit.pojo.FilmComment;
import com.aiit.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @auther Mr Tang
 * @Date 2019/1/2 22:10
 */
@Controller
@RequestMapping("admin")
public class AdminCenterController {

    private List<FilmComment> commentList = new ArrayList<>();
    @Autowired
    ICommentService commentService;

    @Autowired
    FilmComment filmComment;

    /**
     * 跳转到指定页面
     */
    @RequestMapping(params = "/movepage", method = RequestMethod.GET)
    public ModelAndView convertPage(HttpSession session,
                                    HttpServletRequest request,
                                    @RequestParam("/") String name) {
        ModelAndView modelAndView = new ModelAndView();
        switch (name) {
            case "comments":
                commentList = commentService.showAllFComments();
                modelAndView.addObject("cList", commentList);
                modelAndView.addObject("tp", commentList.size() % 3 == 0 ? commentList.size() / 3 : commentList.size() / 3 + 1);
                modelAndView.addObject("pc", 1);
                modelAndView.setViewName("filmcomment/AllCommentsPage");
                break;
            case "showcontent":
                String cId = request.getParameter("cId");
                filmComment = commentService.searchFilmCommentById(cId);
                modelAndView.addObject("cItem", filmComment);
                modelAndView.setViewName("admin/CommentText");
                break;
        }
        return modelAndView;
    }

    @RequestMapping(params = "/moveCommentPage", method = RequestMethod.GET)
    public ModelAndView moveCommentPage(@RequestParam("pc") String pagenum) {
        ModelAndView modelAndView = new ModelAndView();
        System.out.println("要跳转的页数：" + pagenum);
        modelAndView.addObject("cList", commentList);
        modelAndView.addObject("tp", commentList.size() % 3 == 0 ? commentList.size() / 3 : commentList.size() / 3 + 1);
        modelAndView.addObject("pc", pagenum);
        modelAndView.setViewName("filmcomment/AllCommentsPage");
        return modelAndView;
    }
}
