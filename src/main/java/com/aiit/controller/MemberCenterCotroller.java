package com.aiit.controller;

import com.aiit.dao.FileDao;
import com.aiit.pojo.FilmComment;
import com.aiit.pojo.Member;
import com.aiit.service.ICommentService;
import com.aiit.service.IMemberService;
import com.aiit.service.IPersonalService;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @auther Mr Tang
 * @Date 2018/12/31 17:09
 */
@Controller
@RequestMapping("membercenter")
public class MemberCenterCotroller {
    private List<FilmComment> commentList = new ArrayList<>();
    @Autowired
    private IPersonalService personalService;

    @Autowired
    private ICommentService commentService;

    @Autowired
    private IMemberService memberService;

    @Autowired
    FileDao fileDao;

    @Autowired
    private Member member;

    /**
     * 跳转到指定页面
     */
    @RequestMapping(params = "/movepage", method = RequestMethod.GET)
    public ModelAndView convertPage(HttpSession session,
                                    @RequestParam Map<String, String> map) {
        ModelAndView modelAndView = new ModelAndView();
        String name = map.get("pagename");
        switch (name) {
            case "pInfo":
                String mName = (String) session.getAttribute("membername");
                member = commentService.searchMemberByName(mName);
                modelAndView.addObject("mItem", member);
                modelAndView.setViewName("membercenter/PInfoPage");
                break;
            case "pCenter":
                modelAndView.setViewName("membercenter/PersonalPage");
                break;
            case "update":
                String id = map.get("id");
                System.out.println("待修改会员id为" + id);
                member = memberService.searchMemberById(id);
                modelAndView.addObject("item", member);
                modelAndView.setViewName("membercenter/PUpdatePage");
                break;
        }
        return modelAndView;
    }

    @RequestMapping(params = "/concern", method = RequestMethod.GET)
    @ResponseBody
    public String concernOutherMember(@RequestParam("mId") String mId, HttpSession session) {
        String mName = (String) session.getAttribute("membername");
        String localId = personalService.returnMemberByName(mName).getId();
        if (localId.equalsIgnoreCase(mId)) {
            return "100";
        }
        if (personalService.isExistConcern(localId, mId) == 1) {
            return "150";
        }
        if (personalService.addConcern(localId, mId)) {
            return "200";
        }
        return "500";
    }

    @RequestMapping(params = "/toPersonOut", method = RequestMethod.GET)
    ModelAndView toPersonOutPage(@RequestParam("mId") String mId) {
        ModelAndView modelAndView = new ModelAndView();
        commentList = commentService.searchFilmCommentBymId(mId);
        int concernNum = personalService.countConcernNum(mId);
        int beConcernNum = personalService.countBeConcernNum(mId);
        member = memberService.searchMemberById(mId);

        modelAndView.addObject("concernNum", concernNum);
        modelAndView.addObject("beConcernNum", beConcernNum);

        modelAndView.addObject("mItem", member);
        modelAndView.addObject("cList", commentList);
        modelAndView.setViewName("membercenter/PersonOutPage");
        return modelAndView;
    }

    @RequestMapping(params = "/pComment", method = RequestMethod.GET)
    ModelAndView pComment(
            HttpSession session
    ) {
        ModelAndView modelAndView = new ModelAndView();
        String mName = (String) session.getAttribute("membername");
        String mId = personalService.returnMemberByName(mName).getId();
        commentList = commentService.searchFilmCommentBymId(mId);
        modelAndView.addObject("cList", commentList);
        modelAndView.addObject("tp", commentList.size() % 3 == 0 ? commentList.size() / 3 : commentList.size() / 3 + 1);
        modelAndView.addObject("pc", 1);
        modelAndView.setViewName("membercenter/PFCommentPage");
        return modelAndView;
    }

    @RequestMapping(params = "/moveCommentPage", method = RequestMethod.GET)
    public ModelAndView moveCommentPage(@RequestParam("pc") String pagenum) {
        ModelAndView modelAndView = new ModelAndView();
        System.out.println("要跳转的页数：" + pagenum);
        modelAndView.addObject("cList", commentList);
        modelAndView.addObject("tp", commentList.size() % 3 == 0 ? commentList.size() / 3 : commentList.size() / 3 + 1);
        modelAndView.addObject("pc", pagenum);
        modelAndView.setViewName("membercenter/PFCommentPage");
        return modelAndView;
    }

    @RequestMapping(params = "/update", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public void writeComment(
            PrintWriter out,
            HttpSession session,
            @RequestParam("email") String email,
            @RequestParam("name") String name,
            @RequestParam("memberid") String memberid,
            @RequestParam("pass") String pass,
            @RequestParam("sex") String sex,
            @RequestParam("imgsrc") String imgsrc,
            @RequestParam("phonenum") String phonenum,
            @RequestParam("birthday") String birthday,
            @RequestParam("image") MultipartFile image) {
        /* 第一步：将新图片上传到文件夹里,若上传失败将不进行后面的操作;
         * 第二步：将新图片的虚拟地址连同其他数据打包成Member对象更新到数据库里;
         * 第三步：将原图片按照绝对路径删除，*/
        /*
        绝对路径前缀：
        /pic/users/1546003486IMG_20181203_011657.JPG
        对以上类似格式图片的虚拟路径进行切割，拼接成旧图片的绝对路径，
        调用photoDao里的方法根据绝对路径将其删除。
        */
        // String str = "/pic/users/1546003486IMG_20181203_011657.JPG";
        int randomNum = commentService.getSecondTimestamp();
        String suffix = commentService.getSuffix(image.getOriginalFilename());

        String realPath = "E:/commentimgs/users/" + randomNum + suffix;
        String dummyPath = "/pic/users/" + randomNum + suffix;

        System.out.println("真实路径是：" + realPath);
        System.out.println("虚拟路径是：" + dummyPath);

        member = new Member(memberid, name, pass, sex, phonenum, email, birthday, dummyPath);

        boolean flag1, flag2 = false, flag3 = false;
        flag1 = commentService.upLoadPhoto(image, realPath);
        if (flag1) {
            flag2 = personalService.updateMemberInfo(member);
        }
        if (flag2) {
            if (!imgsrc.equalsIgnoreCase("")) {
                String[] strarray = imgsrc.split("/");
                String realPath_old = "E:/commentimgs/users/" + strarray[3];
                fileDao.deleteFileMethod(realPath_old);//从文件夹里删除旧图片;
            }
            session.setAttribute("membername", name);
            alertMessage(out, "success");
        }
        alertMessage(out, "fail");
    }

    public void alertMessage(PrintWriter out, String state) {
        switch (state) {
            case "success":
                out.flush();
                out.println("<script>" + "alert('修改信息成功');");
                out.print(" window.history.go(-1);");
                out.print("location.reload();");
                out.print("</script>");
                out.close();
                break;
            case "fail":
                out.flush();
                out.println("<script>" + "alert('抱歉，修改信息失败');");
                out.print(" window.history.go(-1);");
                out.print("location.reload();");
                out.print("</script>");
                out.close();
                break;
        }
    }
}
