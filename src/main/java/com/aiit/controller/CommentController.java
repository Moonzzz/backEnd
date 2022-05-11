package com.aiit.controller;

import com.aiit.pojo.Comment;
import com.aiit.service.FilmCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * @author Moon
 */
@Controller
@RequestMapping(value = "comment")
public class CommentController {
    private final
    FilmCommentService filmCommentService;

    @Autowired
    public CommentController(FilmCommentService filmCommentService) {
        this.filmCommentService = filmCommentService;
    }

    @RequestMapping(value = "getHtmlByFilmId", method = RequestMethod.GET)
    public ModelAndView getHtmlByFilmId(@RequestParam(defaultValue = "0") int pageNo,
                                        @RequestParam(defaultValue = "10") int pageSize, Long filmId) {
        ModelAndView modelAndView = new ModelAndView();
        List<Comment> commentList = filmCommentService.getListByFilmId(pageNo, pageSize, filmId);
        modelAndView.addObject("comments", commentList);
        modelAndView.addObject("filmId", filmId);
        modelAndView.setViewName("comment/comment");
        return modelAndView;
    }

    @RequestMapping(value = "updateLike", method = RequestMethod.GET)
    public void updateLike(@RequestParam Map argMap) {
        if (argMap.size() > 0) {
            filmCommentService.updateLikeNum(argMap);
        }
    }

    @RequestMapping(value = "addComment", method = RequestMethod.GET)
    @ResponseBody
    public String addComment(Comment comment) {
        boolean isDone = false;
        if (comment != null && comment.getFilmId() != null && comment.getMemberId() != null && comment.getContent().trim().length() != 0) {
            isDone = filmCommentService.addComment(comment);
        }
        if (isDone) {
            return "<script>alert('success!');window.location=document.referrer;</script>";
        }
        return "<script>alert('failed!');window.location=document.referrer;</script>";
    }
}
