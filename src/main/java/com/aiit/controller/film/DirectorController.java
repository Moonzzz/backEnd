package com.aiit.controller.film;

import com.aiit.pojo.filmpojo.Director;
import com.aiit.service.filmservice.IDirectorService;
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
 * 对导演的操作
 * @author pc
 */
@Controller
public class DirectorController {
    @Autowired
    IDirectorService directorService;
    @RequestMapping(value = "toShowDirector.action",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String toShow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String pagestr = request.getParameter("page");
        String limitStr;
        limitStr = request.getParameter("limit");
        int page = Integer.parseInt(pagestr);
        int limit;
        limit = Integer.parseInt(limitStr);
        List<Director> actors = directorService.showDirector((page-1)*limit,limit);
        int count;
        count = directorService.showCount();
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        String result;
        result = gson.toJson(actors);
        result = "{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+result+"}";
        //System.out.println(result);
        return result;
    }

    /**
     * 单个删除
     * @param request
     * @return
     */
    @RequestMapping(value="todelDirector.action", method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String toDelete(HttpServletRequest request){
        String id = request.getParameter("id");
        int dirid = Integer.parseInt(id);
        directorService.delDirector(dirid);
        return "true";
    }
    /**
     * 多项删除
     */
    @RequestMapping(value="todelDirectors.action" ,method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String bulkDelete(HttpServletRequest request){
        String[] ids=request.getParameterValues("id");
        for(int i = 0;i<ids.length;i++) {
            int id = Integer.parseInt(ids[i]);
            System.out.println(id);
            directorService.delDirector(id);
        }
        return "true";
    }
    /**
     * 新增
     */
    @RequestMapping(value = "addDirector.action" ,method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String add(HttpServletRequest request){
        String name;
        name = request.getParameter("name");
        String url;
        url = request.getParameter("url");
        Director director = new Director(name,url);
        directorService.addDirector(director);
        return "true";
    }
}
