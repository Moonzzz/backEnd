package com.aiit.controller.film;

import com.aiit.pojo.filmpojo.Actor;
import com.aiit.service.filmservice.IActorService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author pc
 */
@Controller
public class ActorController {
    @Autowired
    IActorService actorService;

    @RequestMapping(value="toShowActor.action",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String toShow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String pagestr = request.getParameter("page");
        String limitStr = request.getParameter("limit");
        int page = Integer.parseInt(pagestr);
        int limit = Integer.parseInt(limitStr);
        List<Actor> actors = actorService.showActor((page-1)*limit,limit);
        int count = actorService.showCount();
        Gson gson;
        gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        String result;
        result = gson.toJson(actors);
        result = "{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+result+"}";
        return result;
    }
    /**
     * 单个删除
     * @param request
     * @return
     */
    @RequestMapping(value="delActor.action", method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String toDelete(HttpServletRequest request){
        String id = request.getParameter("id");
        int Aid;
        Aid = Integer.parseInt(id);
        System.out.print(Aid);
        actorService.delActor(Aid);
        return "true";
    }
    /**
     * 多项删除
     */
    @RequestMapping(value = "delActors.action",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String bulkDelete(HttpServletRequest request){
        String[] ids=request.getParameterValues("id");
        for (int i=0;i<ids.length;i++){
            int id = Integer.parseInt(ids[i]);
            System.out.println(id);
            actorService.delActor(id);
        }
        return "true";
    }
    /**
     * 新增
     */
    @RequestMapping(value = "addActor.action" ,method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String add(HttpServletRequest request){
        String name= request.getParameter("name");
        String url = request.getParameter("url");
        Actor actor = new Actor(name,url);
        actorService.addActor(actor);
        return "true";
    }
}
