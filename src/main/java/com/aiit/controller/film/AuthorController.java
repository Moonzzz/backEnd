package com.aiit.controller.film;

import com.aiit.pojo.filmpojo.Author;
import com.aiit.service.filmservice.IAuthorService;
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
 * 编剧操作
 * @Author
 */
@Controller
public class AuthorController {
    @Autowired
    IAuthorService authorService;
    @RequestMapping(value = "toShowAuthor.action",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String toShow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String pagestr;
        pagestr = request.getParameter("page");
        String limitStr;
        limitStr = request.getParameter("limit");
        int page;
        page = Integer.parseInt(pagestr);
        int limit = Integer.parseInt(limitStr);
        List<Author> actors = authorService.showAuthor((page-1)*limit,limit);
        int count;
        count = authorService.showCount();
        Gson gson;
        gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        String result = gson.toJson(actors);
        result = "{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+result+"}";
        return result;
    }
    /**
     * 单个删除
     * @param request
     * @return
     */
    @RequestMapping(value="todelAuthor.action", method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String toDelete(HttpServletRequest request){
        String id = request.getParameter("id");
        int Aid;
        Aid = Integer.parseInt(id);
        authorService.delAuthor(Aid);
        return "true";
    }
    /**
     * 多项删除
     */
    @RequestMapping(value = "delAuthors.action" ,method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String bulkDelete(HttpServletRequest request){
        String[] ids=request.getParameterValues("id");
        String state="";
        for (int i=0;i<ids.length;i++){
            int id = Integer.parseInt(ids[i]);
            System.out.println(id);
            authorService.delAuthor(id);
            state = "true";
        }
        return state;
    }
    /**
     * 新增
     */
    @RequestMapping(value = "addAuthor.action" ,method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String add(HttpServletRequest request){
        String name= request.getParameter("name");
        String url = request.getParameter("url");
        Author author = new Author(name,url);
        authorService.addAuthor(author);
        return "true";
    }
}
