package com.aiit.controller;

import com.aiit.pojo.Reply;
import com.aiit.service.FilmReplyService;
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
@RequestMapping(value = "reply")
public class ReplyController {
    private final
    FilmReplyService filmReplyService;

    @Autowired
    public ReplyController(FilmReplyService filmReplyService) {
        this.filmReplyService = filmReplyService;
    }

    @RequestMapping(value = "getHtmlByPostId", method = RequestMethod.GET)
    public ModelAndView getHtmlByPostId(@RequestParam(defaultValue = "0") int pageNo,
                                        @RequestParam(defaultValue = "10") int pageSize, Long postId, Long filmId) {
        ModelAndView modelAndView = new ModelAndView();
        System.out.println("调用短评函数");
        List<Reply> replyList = filmReplyService.getListByPostId(pageNo, pageSize, postId);
        System.out.println(replyList.toString());
        modelAndView.addObject("replys", replyList);
        modelAndView.addObject("postId", postId);
        modelAndView.addObject("filmId", filmId);
        modelAndView.setViewName("comment/reply");
        return modelAndView;
    }

    @RequestMapping(value = "updateLike", method = RequestMethod.GET)
    public void updateLike(@RequestParam Map argMap) {
        if (argMap.size() > 0) {
            filmReplyService.updateLikeNum(argMap);
        }
    }

    @RequestMapping(value = "addReply", method = RequestMethod.GET)
    @ResponseBody
    public String addReply(Reply reply) {
        boolean isDone = false;
        if (reply != null && reply.getPostId() != null && reply.getMemberId() != null && reply.getContent().trim().length() != 0) {
            isDone = filmReplyService.addreply(reply);
        }
        if (isDone) {
            return "<script>alert('success!');window.location=document.referrer;</script>";
        }
        return "<script>alert('failed!');window.location=document.referrer;</script>";
    }
}
