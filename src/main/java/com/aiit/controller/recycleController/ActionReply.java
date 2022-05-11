package com.aiit.controller.recycleController;

import com.aiit.pojo.Reply;
import com.aiit.service.IRecycleService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
public class ActionReply {
    @Autowired
    private IRecycleService recycleService;
    @RequestMapping(value="/toReply.action",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String toReply(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        int page = Integer.parseInt(request.getParameter("page"));
        int limit = Integer.parseInt(request.getParameter("limit"));
        List<Reply> replys =this.recycleService.showReplyAll((page-1)*limit,limit);
        int count=this.recycleService.showReplyCount();
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd")
                .create();
        String result=gson.toJson(replys);
        System.out.println(result);
        result = "{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+result+"}";
        return result;
    }
    @RequestMapping(value = "/toDeleteReply.action",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public int toDeleteReply(String id) throws UnsupportedEncodingException {
        String []ids=id.split(",");
        int i=0;
        for (String id1:ids) {
            i= this.recycleService.deleteReply(Integer.parseInt(id1));
        }
        return i;
    }
    @RequestMapping(value = "/toRevocationReply.action",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public int revocationPost(String id)
    {
        String []ids=id.split(",");
        int i=0;
        for (String id1:ids) {
            i=  this.recycleService.revocationReply(Integer.parseInt(id1));
            this.recycleService.deleteReply(Integer.parseInt(id1));
        }
        return i;
    }
}
