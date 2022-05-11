package com.aiit.controller;

import com.aiit.pojo.Admin;
import com.aiit.pojo.Member;
import com.aiit.service.ICommentService;
import com.aiit.service.IMemberService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserPageController {
    @Autowired
    Admin admin;
    @Autowired
    Member member;

    @Autowired
    ICommentService commentService;

    @Autowired
    IMemberService memberService;
    private List<Member> list;

    @RequestMapping(value = "searchMembers", method = RequestMethod.POST)
    public ModelAndView test(@RequestParam("start") String start,
                             @RequestParam("end") String end,
                             @RequestParam("username") String username) {
        ModelAndView modelAndView = new ModelAndView();
        if (start.equalsIgnoreCase("") || end.equalsIgnoreCase("")) {
            list = memberService.selectMembersName(username);
        } else {
            list = memberService.selectMembersByDate_Name(start, end, username);
        }
        modelAndView.addObject("mList", list);
        modelAndView.addObject("tp", list.size() % 5 == 0 ? list.size() / 5 : list.size() / 5 + 1);
        modelAndView.setViewName("member/MemberPage");
        return modelAndView;
    }

    /**
     * 会员管理页面跳转中心
     *
     * @param pagename
     * @param request
     * @return
     */
    @RequestMapping("memberControl")
    public ModelAndView memberPages(@RequestParam("pagename") String pagename, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        switch (pagename) {
            case "add":
                modelAndView.setViewName("member/MemberAdd");
                break;
            case "member":
                list = memberService.showAllMembers();
                System.out.println(list.toString());
                modelAndView.addObject("mList", list);
                modelAndView.addObject("tp", list.size() % 5 == 0 ? list.size() / 5 : list.size() / 5 + 1);
                modelAndView.setViewName("member/MemberPage");
                break;
            case "movepage":
                list = memberService.showAllMembers();
                String pagenum = request.getParameter("pc");
                System.out.println("要跳转的页数：" + pagenum);
                modelAndView.addObject("mList", list);
                modelAndView.addObject("tp", list.size() % 5 == 0 ? list.size() / 5 : list.size() / 5 + 1);
                modelAndView.addObject("pc", pagenum);
                modelAndView.setViewName("member/MemberPage");
                break;
            case "update":
                String id = request.getParameter("id");
                System.out.println("待修改会员id为" + id);
                member = memberService.searchMemberById(id);
                modelAndView.addObject("item", member);
                modelAndView.setViewName("member/MemberUpdate");
                break;
            case "register":
                modelAndView.setViewName("member/MemberRegister");
                break;
            default:
                modelAndView.setViewName("member/error");
                break;
        }
        return modelAndView;
    }

    /**
     * 管理员页面跳转中心
     *
     * @param pagename
     * @param request
     * @return
     */
    @RequestMapping("adminControl")
    public ModelAndView adminPages(@RequestParam("pagename") String pagename, HttpServletRequest request) {
        ModelAndView view = new ModelAndView();
        switch (pagename) {
            case "update":
                String name = request.getParameter("name");
                admin = memberService.searchAdminByName(name);
                view.addObject("item", admin);
                view.setViewName("admin/AdminUpdate");
                break;
        }
        return view;
    }

    /**
     * 添加会员的动作
     *
     * @param name
     * @param email
     * @param pass
     * @param out
     * @throws IOException
     */
    @RequestMapping("member/add")
    public void addMember(
            @RequestParam("username") String name,
            @RequestParam("email") String email,
            @RequestParam("pass") String pass,
            PrintWriter out
    ) throws IOException {
        String joindate = commentService.getCurrentTime();
        System.out.println("我正在被调用");
        boolean flag = false;
        member = new Member(name, pass, email, joindate);
        if (memberService.isExistMember(member.getLoginName()) == 0) {
            flag = memberService.addMember(member);
        } else {
            System.out.println("用户名已经存在");
        }
        if (!flag) {
            out.flush();
            out.println("<script>" + "alert('抱歉，添加失败，可能是因为存在相同名字的会员~~~');");
            out.print(" window.history.go(-1);");
            out.print("</script>");
            out.close();
        } else {
            out.flush();
            out.println("<script>" + "alert('恭喜你，添加成功~~~');");
            out.print(" window.history.go(-1);");
            out.print("</script>");
            out.close();
        }
    }

    /**
     * 修改管理员信息的动作
     *
     * @param id
     * @param name
     * @param pass
     * @param out
     */
    @RequestMapping("admin/update")
    public void updateAdmin(
            @RequestParam("adiminid") int id,
            @RequestParam("name") String name,
            @RequestParam("pass") String pass,
            PrintWriter out
    ) {
        admin = new Admin(id, name, pass);
        boolean flag = false;
        flag = memberService.upDateAdmin(admin);
        if (!flag) {
            out.flush();
            out.println("<script>" + "alert('抱歉，修改失败');");
            out.print(" window.history.go(-1);");
            out.print("</script>");
            out.close();
        } else {
            out.flush();
            out.println("<script>" + "alert('恭喜你，修改信息成功~~~');");
            out.print(" window.history.go(-1);");
            out.print("</script>");
            out.close();
        }
    }

    /**
     * 修改会员信息的动作
     *
     * @param mid
     * @param name
     * @param email
     * @param pass
     * @param sex
     * @param phonenum
     * @param birthday
     * @param out
     * @throws IOException
     */
    @RequestMapping("member/update")
    public void updateMember(
            @RequestParam("memberid") String mid,
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("pass") String pass,
            @RequestParam("sex") String sex,
            @RequestParam("phonenum") String phonenum,
            @RequestParam("birthday") String birthday,
            PrintWriter out
    ) throws IOException {
        System.out.println("正在修改会员信息");
        boolean flag = false;
        member = new Member(mid, name, pass, sex, phonenum, email, birthday);
        //如果用户名没有修改，则直接修改信息，无需判断修改后的用户名是否存在
        if (memberService.returnNameById(mid).equals(name)) {
            flag = memberService.upDateMember(member);
        } else {
            //如果用户名已修改，则先判断修改后的用户名是否存在，再确认是否执行修改
            if (memberService.isExistMember(member.getLoginName()) == 0) {
                flag = memberService.upDateMember(member);
            } else {
                System.out.println("用户名已经存在");
            }
        }
        if (!flag) {
            out.flush();
            out.println("<script>" + "alert('抱歉，修改失败，可能是因为存在相同名字的会员~~~');");
            out.print(" window.history.go(-1);");
            out.print("</script>");
            out.close();
        } else {
            out.flush();
            out.println("<script>" + "alert('恭喜你，修改成功~~~');");
            out.print(" window.history.go(-1);");
            out.print("</script>");
            out.close();
        }
    }

    @RequestMapping(value = "member/delect", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String deleteMemberById(String memberid) {
        String statunum = "500";
        if (memberService.deleteMemberById(memberid)) {
            statunum = "200";
        }
        return statunum;
    }

    @RequestMapping(value = "member/delectmembers", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String deleteMembers(String ids) {
        //从前台接收到的数据是json格式，必须将其转化为Sring类型的数组。
        Type type = new TypeToken<ArrayList<String>>() {
        }.getType();
        //首先利用Gson里的方法将其转化为List<String>
        Gson gson = new Gson();
        List<String> arrs = gson.fromJson(ids, type);
        //再将List<String>转化为String类型的数组
        String[] list = arrs.toArray(new String[arrs.size()]);
        String statunum = "500";
        if (memberService.deleteMemberList(list) == arrs.size()) {
            statunum = "200";
        }
        return statunum;
    }
}
