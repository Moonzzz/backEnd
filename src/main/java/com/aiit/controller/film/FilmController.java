package com.aiit.controller.film;

import com.aiit.pojo.filmpojo.Film;
import com.aiit.pojo.filmpojo.Genre;
import com.aiit.service.filmservice.IFilmService;
import com.aiit.service.filmservice.IGenreService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
public class FilmController {
    @Autowired
    private IFilmService filmService;
    @Autowired
    private IGenreService genreService;
    @RequestMapping(value="toShowFilm.action",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String toshow(HttpServletRequest request)throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String pagestr = request.getParameter("page");
        String limitStr = request.getParameter("limit");
        int page;
        page = Integer.parseInt(pagestr);
        int limit = Integer.parseInt(limitStr);
        List<Film> films = filmService.show((page-1)*limit,limit);
        int count = filmService.showCount();
        return getString(films, count);
    }
    @RequestMapping(value="todelFilm.action", method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public  String toDelete(HttpServletRequest request)
    {
        String fNo = request.getParameter("fNo");
        System.out.println(fNo);
        this.filmService.delete(fNo);
        return "true";
    }

    @RequestMapping(value="tomodFilm.action", method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String toModify(HttpServletRequest request) throws ParseException {
        String id = request.getParameter("id");
        System.out.print(id);
        String fNo = request.getParameter("fno");
        String data = request.getParameter("data");
        String name = request.getParameter("name");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date utildate = null;
        Date sqldate = null;
        utildate = formatter.parse(data);
        sqldate = new Date(utildate.getTime());
        Film film = new Film(fNo,name,sqldate);
        filmService.modFilm(film);
        System.out.println(fNo);
        System.out.println(sqldate);
        return "true";
    }
    @RequestMapping(value="bulkFilm.action", method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String bulkFilm(HttpServletRequest request){
        String[] id=request.getParameterValues("id");
        for (int i=0;i<id.length;i++){
            System.out.println(id[i]);
            filmService.delete(id[i]);
        }
        return "true";
    }
    @RequestMapping(value="ShowFilms.action",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String toshowFilms(HttpServletRequest request)throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String pagestr;
        pagestr = request.getParameter("page");
        String limitStr = request.getParameter("limit");
        int page = Integer.parseInt(pagestr);
        int limit = Integer.parseInt(limitStr);
        List<Film> films = filmService.getFilm((page-1)*limit,limit);
        int count;
        count = filmService.showCount();
        return getString(films, count);
    }

    /**
     * 得到电影类型
     * @param request
     * @return
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(value="getgenre.action",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String toshowgenre(HttpServletRequest request)throws IOException {
        request.setCharacterEncoding("UTF-8");
        List<Genre> genres = genreService.getGenres();
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd")
                .create();
        String result=gson.toJson(genres);
        System.out.print(result);
        return result;
    }
    private String getString(List<Film> films, int count) {
        System.out.println(count);
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd")
                .create();
        String result=gson.toJson(films);
        result = "{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+result+"}";
        return result;
    }
}
