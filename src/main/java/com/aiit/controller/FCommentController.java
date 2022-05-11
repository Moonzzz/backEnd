package com.aiit.controller;

import com.aiit.dao.IFileDao;
import com.aiit.filter.SenstiveWordsFiter;
import com.aiit.pojo.Film;
import com.aiit.pojo.FilmComment;
import com.aiit.pojo.Member;
import com.aiit.service.ICommentService;
import com.aiit.service.IFilmPageService;
import com.aiit.service.IMemberService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("comment")
public class FCommentController {
    @Autowired
    SenstiveWordsFiter wordsFiter;

    @Autowired
    FilmComment filmComment;

    @Autowired
    Film film;

    @Autowired
    ICommentService commentService;

    @Autowired
    IFilmPageService filmPageService;

    @Autowired
    IFileDao fileDao;

    @RequestMapping(params = "/readcomment", method = RequestMethod.GET)
    public ModelAndView readFilmComment(@RequestParam("id") String id) {
        ModelAndView modelAndView = new ModelAndView();
        filmComment = commentService.searchFilmCommentById(id);
        //film=filmPageService.
        commentService.addViewNumById(id);
        modelAndView.addObject("cItem", filmComment);
        modelAndView.setViewName("filmcomment/ReadCommentPage");
        return modelAndView;
    }

    @RequestMapping(params = "/delectOne", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String deleteCommentById(String commentid) {
        filmComment = commentService.searchFilmCommentById(commentid);
        String old_imgsrc = filmComment.getImage();
        String old_videosrc = filmComment.getVideo();
        String[] strarray = old_imgsrc.split("/");
        String realPath_old = "E:/commentimgs/" + strarray[2];//待删除照片的真实地址

        System.out.println("要删除的影评ID：" + commentid);
        String statunum = "500";
        if (commentService.deleteCommentById(commentid)) {
            if (fileDao.deleteFileMethod(realPath_old)) {
                if (!old_videosrc.equalsIgnoreCase("null")) {
                    String[] v_strarray = old_videosrc.split("/");
                    String v_realPath_old = "E:/commentimgs/videos/" + v_strarray[3];//待删除视频的真实地址
                    if (fileDao.deleteFileMethod(v_realPath_old)) {
                        return "200";
                    }
                }
                statunum = "200";
            }
        }
        return statunum;
    }

    @RequestMapping(params = "/delectcomments", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String deleteComments(String ids) {
        //从前台接收到的数据是json格式，必须将其转化为Sring类型的数组。
        Type type = new TypeToken<ArrayList<String>>() {
        }.getType();
        //首先利用Gson里的方法将其转化为List<String>
        Gson gson = new Gson();
        List<String> arrs = gson.fromJson(ids, type);
        boolean flag = true;
        for (String commentid : arrs) {
            filmComment = commentService.searchFilmCommentById(commentid);
            String old_imgsrc = filmComment.getImage();
            String old_videosrc = filmComment.getVideo();

            String[] strarray = old_imgsrc.split("/");
            String realPath_old = "E:/commentimgs/" + strarray[2];//待删除照片的真实地址
            if (!fileDao.deleteFileMethod(realPath_old)) {
                flag = false;
            }
            if (!old_videosrc.equalsIgnoreCase("null")) {
                String[] v_strarray = old_videosrc.split("/");
                String v_realPath_old = "E:/commentimgs/videos/" + v_strarray[3];//待删除视频的真实地址
                if (!fileDao.deleteFileMethod(v_realPath_old)) {
                    flag = false;
                }
            }
        }
        //再将List<String>转化为String类型的数组
        String[] list = arrs.toArray(new String[arrs.size()]);
        String statunum = "500";
        if (commentService.deleteCommentList(list) == arrs.size()) {
            if (flag) {
                statunum = "200";
            }
        }
        return statunum;
    }


    @RequestMapping(params = "/writeComment", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public void writeFilmComment(
            HttpSession session,
            PrintWriter out,
            @RequestParam("fId") String fId,
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam("picFile") MultipartFile picFile,
            @RequestParam("videoFile") MultipartFile videoFile) {
        Object mName = session.getAttribute("membername");
        String mId = commentService.searchMemberByName((String) mName).getId() + "";
        String dateString = commentService.getCurrentTime();
        String newContext = wordsFiter.fiteSenstiveWords(content);

        System.out.println("mp4 size:  " + videoFile.getSize());
        System.out.println("pic size:  " + picFile.getSize());
        filmComment = new FilmComment(fId, mId, dateString, title, newContext);
        long pSize = picFile.getSize();
        long vSize = videoFile.getSize();

        String state = "fail";
        if (pSize > 10 && vSize < 100) {
            filmComment.setVideo("null");
            if (commentService.setCommentPic(filmComment, picFile)) {
                state = "success";
            }
        } else if (pSize > 10 && vSize > 100) {
            if (commentService.setCommentPicVideo(filmComment, picFile, videoFile)) {
                state = "success";
            }
        }
        alertMessage(out, state);
    }

    public void alertMessage(PrintWriter out, String state) {
        switch (state) {
            case "success":
                out.flush();
                out.println("<script>" + "alert('上传影评成功');");
                out.print("window.location.href='filmpage.action?/toFilmMainPage';");
                out.print("</script>");
                out.close();
                break;
            case "fail":
                out.flush();
                out.println("<script>" + "alert('抱歉，上传影评失败');");
                out.print(" window.history.go(-1);");
                out.print("location.reload();");
                out.print("</script>");
                out.close();
                break;
        }
    }
}
