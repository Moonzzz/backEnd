package com.aiit.controller.recycleController;

import com.aiit.pojo.Director;
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
public class ActionDirector {
    @Autowired
    private IRecycleService recycleService;
    @RequestMapping(value="/toDirector.action",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String toDirector(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        int page = Integer.parseInt(request.getParameter("page"));
        int limit = Integer.parseInt(request.getParameter("limit"));
        List<Director> directors =this.recycleService.showAllDirector((page-1)*limit,limit);
        int count=this.recycleService.showDirectorCount();
        Gson gson=new Gson();
        String result=gson.toJson(directors);
        result = "{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+result+"}";
        return result;
    }
    @RequestMapping(value = "/toDeleteDirector.action",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public int toDeleteDirector(String id) throws UnsupportedEncodingException {
        String []ids=id.split(",");
        int i=0;
        for (String id1:ids) {
            i= this.recycleService.deleteDirector(Integer.parseInt(id1));
        }
        return i;
    }
    @RequestMapping(value = "/toRevocationDirector.action",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public int revocationDirector(String id)
    {
        String []ids=id.split(",");
        int i=0;
        for (String id1:ids) {
            i=  this.recycleService.revocationDirector(Integer.parseInt(id1));
            this.recycleService.deleteDirector(Integer.parseInt(id1));
        }
        return i;
    }

}
