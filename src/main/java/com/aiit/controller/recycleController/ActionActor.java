package com.aiit.controller.recycleController;
import com.aiit.pojo.Actor;
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
public class ActionActor {
    @Autowired
    private IRecycleService recycleService;
    @RequestMapping(value="/toActor.action",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String toActor(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        int page = Integer.parseInt(request.getParameter("page"));
        int limit = Integer.parseInt(request.getParameter("limit"));
        List<Actor> actors =this.recycleService.showAll((page-1)*limit,limit);
        int count=this.recycleService.showCount();
        Gson gson=new Gson();
        String result=gson.toJson(actors);
        result = "{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+result+"}";
        return result;
    }
    @RequestMapping(value = "/toDeleteActor.action",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public  int  toDelete(String id) throws UnsupportedEncodingException {
        String []ids=id.split(",");
         int i=0;
        for (String id1:ids) {
         i= this.recycleService.delete(Integer.parseInt(id1));
        }
        return i;
    }


    @RequestMapping(value = "/toRevocationActor.action",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public int revocationActor(String id)
    {
        String []ids=id.split(",");
        int i=0;
        for (String id1:ids) {
            i=  this.recycleService.revocationActor(Integer.parseInt(id1));
              this.recycleService.delete(Integer.parseInt(id1));
        }
       return i;
    }
}
