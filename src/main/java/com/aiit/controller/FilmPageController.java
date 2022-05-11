package com.aiit.controller;

import com.aiit.pojo.FilmComment;
import com.aiit.pojo.FilmDetail;
import com.aiit.pojo.Member;
import com.aiit.service.ICommentService;
import com.aiit.service.IFilmPageService;
import com.aiit.service.IMemberService;
import com.google.gson.Gson;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.*;

/**
 * @auther Mr Tang
 * @Date 2018/12/24 11:03
 */
@Controller
@RequestMapping("filmpage")
public class FilmPageController {
    private String fName;
    private List<FilmDetail> tenFilms = new ArrayList<>();
    private List<FilmDetail> films = new ArrayList<>();
    private Map<String, String> dataMap = new HashMap<String, String>();
    private List<FilmDetail> filmsList = new ArrayList<>();
    private List<FilmComment> commentList = new ArrayList<>();
    private List<String> actors = new ArrayList<>();
    private List<String> directors = new ArrayList<>();
    private List<String> types = new ArrayList<>();
    List<FilmDetail> similarList = new ArrayList<>();
    @Autowired
    IFilmPageService filmPageservice;

    @Autowired
    ICommentService commentService;

    @Autowired
    private IMemberService memberService;

    @Autowired
    Member member;

    @Autowired
    FilmDetail filmDetail;

    @Autowired
    SqlSessionFactory sqlSessionFactory;

    @RequestMapping(params = "/movepage")
    public ModelAndView memberPages(@RequestParam Map<String, String> map,
                                    HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        String pagename = map.get("pagename");
        switch (pagename) {
            case "login":
                modelAndView.setViewName("filmcenterpages/MemberLogin");
                break;
            case "writecomment":
                String fId = map.get("fId");
                modelAndView.addObject("fId", fId);
                modelAndView.setViewName("filmcomment/WriteCommentPage");
                break;
            case "destroy":
                session.removeAttribute("membername"); //注销session中的username对象
                modelAndView = this.mainPageResourceLoad();
                break;
            case "types":
                String type = map.get("type");
                System.out.println("类型是：" + type);
                if (type.equalsIgnoreCase("all")) {
                    type = "";
                }
                films = filmPageservice.searchFilmByType_Day("", type, "0001-01-01", "2099-12-31");
                types = filmPageservice.returnAllFilmTypes();
                modelAndView.addObject("tp", films.size() % 15 == 0 ? films.size() / 15 : films.size() / 15 + 1);
                modelAndView.addObject("pc", 1);
                modelAndView.addObject("films", films);
                modelAndView.addObject("types", types);
                modelAndView.setViewName("filmcenterpages/FilmTypesPage");
                break;
            default:
                break;
        }
        return modelAndView;
    }

    @RequestMapping(params = "/moveTypePage")
    public ModelAndView moveType(@RequestParam("pc") String pagenum) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("tp", films.size() % 15 == 0 ? films.size() / 15 : films.size() / 15 + 1);
        modelAndView.addObject("pc", pagenum);
        modelAndView.addObject("films", films);
        modelAndView.addObject("types", types);
        modelAndView.setViewName("filmcenterpages/FilmTypesPage");
        return modelAndView;
    }

    @RequestMapping(params = "/changeGroup", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String changeGroup(@RequestParam("fId") String fId
    ) {
        System.out.println("正在换组");
        similarList = filmPageservice.SimilarFilms(fId);
        Gson gson = new Gson();
        String result = gson.toJson(similarList);
        System.out.println(result);
        return result;
    }

    @RequestMapping(params = "/changeTenFilms", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String changeTenFilms(
    ) {
        System.out.println("正在换组");
        tenFilms=filmPageservice.showTenFilms();
        Collections.sort(tenFilms);
        Gson gson = new Gson();
        String result = gson.toJson(tenFilms);
        return result;
    }

    @RequestMapping(params = "/typefilm", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String loadTypeFilm(@RequestParam("nation") String nation,
                               @RequestParam("type") String type,
                               @RequestParam("year") String year
    ) {
        System.out.println(type + "/" + year);
        if (nation.equalsIgnoreCase("全部")) {
            nation = "";
        }
        if (type.equalsIgnoreCase("全部")) {
            type = "";
        }
        if (year.equalsIgnoreCase("")) {
            year = "全部";
        }
        dataMap = filmPageservice.returStart_End(year);
        String startYear = dataMap.get("start");
        String endYear = dataMap.get("end");
        films = filmPageservice.searchFilmByType_Day(nation, type, startYear, endYear);

        System.out.println(startYear + "//" + endYear + "//" + type);
        System.out.println(films.size());
        System.out.println("正在调用加载电影类型查询");
        Gson gson = new Gson();
        String result = gson.toJson(films);
        System.out.println(result);
        return result;
    }

    /**
     * 重定向主页完成资源加载
     *
     * @return
     */
    @RequestMapping(params = "/toFilmMainPage")
    public ModelAndView mainPageResourceLoad() {
        System.out.println("正在加载主页资源");
        ModelAndView modelAndView = new ModelAndView();
        List<FilmDetail> mList;
        List<FilmComment> cList;
        mList = filmPageservice.randomSarchFilms();
        cList = commentService.randomSearchFilmComments();
        tenFilms = filmPageservice.showTenFilms();
        Collections.sort(tenFilms);//按评分降序排列
        modelAndView.addObject("mList", mList);
        modelAndView.addObject("cList", cList);
        modelAndView.addObject("tFList", tenFilms);
        modelAndView.setViewName("filmcenterpages/FilmMainPage");
        return modelAndView;
    }

    /**
     * 转到电影详情页面
     *
     * @param fid
     * @return
     */
    @RequestMapping(params = "/filmdetail")
    public ModelAndView filmDetail(@RequestParam("fid") String fid) {
        similarList = filmPageservice.SimilarFilms(fid);
        System.out.println("正在中转站传递数据");
        ModelAndView modelAndView = new ModelAndView();
        filmDetail = filmPageservice.searchFilmDetailById(fid);
        directors = filmPageservice.searchDirectorsById(fid);
        actors = filmPageservice.searchActorsById(fid);
        commentList = commentService.searchFilmCommentByfId(fid);
        modelAndView.addObject("tp", commentList.size() % 2 == 0 ? commentList.size() / 2 : commentList.size() / 2 + 1);
        modelAndView.addObject("pc", 1);

        System.out.println(filmDetail.toString());
        modelAndView.addObject("cList", commentList);
        modelAndView.addObject("fdetail", filmDetail);
        modelAndView.addObject("directors", directors);
        modelAndView.addObject("actors", actors);
        modelAndView.addObject("smilFilms", similarList);
        modelAndView.setViewName("filmcenterpages/FilmDetailPage");
        return modelAndView;
    }

    @RequestMapping(params = "/moveCommentPage", method = RequestMethod.GET)
    public ModelAndView moveCommentPage(@RequestParam("pc") String pagenum) {
        ModelAndView modelAndView = new ModelAndView();
        System.out.println("要跳转的页数：" + pagenum);
        modelAndView.addObject("cList", commentList);
        modelAndView.addObject("fdetail", filmDetail);
        modelAndView.addObject("directors", directors);
        modelAndView.addObject("actors", actors);
        modelAndView.addObject("cList", commentList);
        modelAndView.addObject("tp", commentList.size() % 2 == 0 ? commentList.size() / 2 : commentList.size() / 2 + 1);
        modelAndView.addObject("pc", pagenum);
        modelAndView.addObject("smilFilms", similarList);
        modelAndView.setViewName("filmcenterpages/FilmDetailPage");
        return modelAndView;
    }

    @RequestMapping(params = "action=searchmovies", method = RequestMethod.POST)
    public ModelAndView searchMoviesByName(
            @RequestParam("filmname") String filmname
    ) {
        ModelAndView modelAndView = new ModelAndView();
        System.out.println("要搜索的电影名字是" + filmname);
        this.fName = filmname;
        filmsList = filmPageservice.searchFilmDeatilsByName(filmname);
        modelAndView.addObject("mList", filmsList);
        modelAndView.addObject("tp", filmsList.size() % 3 == 0 ? filmsList.size() / 3 : filmsList.size() / 3 + 1);
        modelAndView.addObject("pc", "1");
        modelAndView.setViewName("filmcenterpages/FilmSearchPage");
        return modelAndView;
    }

    @RequestMapping(params = "/moveSearchPage", method = RequestMethod.GET)
    public ModelAndView moveSearchMoviesPage(@RequestParam("pc") String pagenum) {
        ModelAndView modelAndView = new ModelAndView();
        System.out.println("要跳转的页数：" + pagenum);
        modelAndView.addObject("mList", filmsList);
        modelAndView.addObject("tp", filmsList.size() % 3 == 0 ? filmsList.size() / 3 : filmsList.size() / 3 + 1);
        modelAndView.addObject("pc", pagenum);
        modelAndView.setViewName("filmcenterpages/FilmSearchPage");
        return modelAndView;
    }

    @RequestMapping(params = "/memberlogin", method = RequestMethod.POST)
    @ResponseBody
    public void memberLogin(
            @RequestParam("logintype") String logintype,
            @RequestParam("name") String name,
            @RequestParam("pwd") String pwd,
            HttpSession session,
            PrintWriter out) {
        System.out.println("登陆类型" + logintype);
        //logintype 为1即为邮箱登陆
        if (logintype.equals("1")) {
            if (filmPageservice.memberLoginByEmail(name, pwd) == 1) {
                session.setAttribute("membername", memberService.searchMemberByEmail(name).getLoginName());
                session.setAttribute("memberId", memberService.searchMemberByEmail(name).getId());
                alertMessage(out, "success");
            } else {
                alertMessage(out, "fail");
            }
        } else if (logintype.equals("2")) {
            System.out.println("正在通过姓名的方式登陆");
            if (filmPageservice.memberLoginByName(name, pwd) == 1) {
                session.setAttribute("membername", name);
                session.setAttribute("memberId", "1");
                alertMessage(out, "success");
            } else {
                alertMessage(out, "fail");
            }
        }
    }

    public void alertMessage(PrintWriter out, String state) {
        switch (state) {
            case "success":
                out.flush();
                out.println("<script>" + "alert('登录成功');");
                out.print(" window.history.go(-1);");
                //out.print("window.open('filmpage.action?action=movepage&pagename=toFilmPage');");
                //out.print("window.location.href='filmpage.action?action=movepage&pagename=toFilmPage';");
                out.print("location.reload();");
                out.print("</script>");
                out.close();
                break;
            case "fail":
                out.flush();
                out.println("<script>" + "alert('抱歉，登录失败');");
                out.print(" window.history.go(-1);");
                // out.print("window.location.href='filmpage.action?action=movepage&pagename=login'");
                out.print("</script>");
                out.close();
                break;
        }
    }

    @RequestMapping(value = "/names", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<String> autoComplete(@RequestParam("key") String key) {
        System.out.println(key);
        System.out.println("正在调用自动补全函数");
        List<String> list = filmPageservice.returnSearchNames(key);
        System.out.println(list.toString());
        return list;
    }
}
