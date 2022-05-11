package com.aiit.controller.recycleController;

import com.aiit.pojo.Author;
import com.aiit.service.IRecycleService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
public class ActionAuthor {
    @Autowired
    private IRecycleService recycleService;
    @RequestMapping(value="/toAuthor.action",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String toAuthor(HttpServletRequest request) throws UnsupportedEncodingException {
        int page = Integer.parseInt(request.getParameter("page"));
        int limit = Integer.parseInt(request.getParameter("limit"));
        List<Author> authors =this.recycleService.showAllAuthor((page-1)*limit,limit);
        int count=this.recycleService.showAuthorCount();
        System.out.println(count);
        Gson gson=new Gson();
        String result=gson.toJson(authors);
        result = "{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+result+"}";
        return result;
    }

    @RequestMapping(value = "/toDeleteAuthor.action",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public int toDeleteAutor(String id) throws UnsupportedEncodingException {
        String []ids=id.split(",");
        int i=0;
        for (String id1:ids) {
            i= this.recycleService.deleteAuthor(Integer.parseInt(id1));
        }
        return i;
    }
    @RequestMapping(value = "/toRevocationAuthor.action",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public int revocationAuthor(String id)
    {
        String []ids=id.split(",");
        int i=0;
        for (String id1:ids) {
            i=  this.recycleService.revocationAuthor(Integer.parseInt(id1));
            this.recycleService.deleteAuthor(Integer.parseInt(id1));
        }
        return i;
    }

}
