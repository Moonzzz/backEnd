package com.aiit.controller;

import com.aiit.pojo.Admin;
import com.aiit.pojo.EmailConnecter;
import com.aiit.pojo.Member;
import com.aiit.service.ICommentService;
import com.aiit.service.IMRegisteService;
import com.aiit.service.IMemberService;
import com.aiit.service.SendEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

@Controller
@RequestMapping("LoginCheck")
public class UserLogRegController {
    //URL=http://localhost:8080/backEnd/LoinCheck.action?action=AdminLoginCheck
    @Autowired
    private ICommentService commentService;

    @Autowired
    private SendEmailService emailInstance;

    @Autowired
    private EmailConnecter connecter;

    @Autowired
    private IMRegisteService mRegisteservice;

    @Autowired
    private IMemberService memberService;

    @Autowired
    private Admin admin;

    /**
     * 管理员登陆验证
     *
     * @param adminName
     * @param adminPsw
     * @param session
     * @return
     */
    @RequestMapping(params = "action=AdminLoginCheck", method = RequestMethod.POST)
    public ModelAndView AdminLoginCheck(
            @RequestParam("adminName") String adminName,
            @RequestParam("adminpsw") String adminPsw,
            HttpSession session) {
        Admin admin = new Admin(adminName, adminPsw);
        boolean flag = false;
        flag = mRegisteservice.AdminLoginCheck(admin);
        ModelAndView view = new ModelAndView();
        if (flag == true) {
            view.setViewName("admin/MainPage");
            session.setAttribute("adminName", adminName);
        } else {
            view.setViewName("member/error");
        }
        return view;
    }
    @RequestMapping(params = "/findpsw", method = RequestMethod.POST)
    @ResponseBody
    public String memberFindPsw( @RequestParam("email") String email){
        boolean flag=false;
        String password="";
        if(mRegisteservice.isExistEmail(email)==1) {
            password = memberService.findPassword(email);
            connecter.setLicensekey("dtbhcinorxnihhih");
            connecter.setFromport("1074356353");
            connecter.setToport(email);
            connecter.setTitle("找回密码");
            String context = "【电影优选】您的密码为" + password + ",工作人员不会向您索取验证码，请勿向任何单位或个人泄露。";
            connecter.setContext(context);
            System.out.println(connecter.toString());
            if(emailInstance.sendEmail(connecter)){
                return "true";
            }else{
                return "false";
            }
        }
        return "noExist";
    }
    /**
     * 发送验证码
     *
     * @param email
     * @param checkcode
     * @return
     */
    @RequestMapping(params = "action=codeCheck", method = RequestMethod.GET)
    @ResponseBody
    public String sendRecheckEmail(
            @RequestParam("email") String email,
            @RequestParam("checkcode") String checkcode
    ) {
        String state = "500";
        boolean flag = false;
        System.out.println("验证码：" + checkcode);
        connecter.setLicensekey("shkhyyamdcocdgfi");
        connecter.setFromport("3161814915");
        connecter.setLicensekey("dtbhcinorxnihhih");
        connecter.setFromport("1074356353");
        connecter.setToport(email);
        connecter.setTitle("验证码信息");
        String context = "您好，您正在使用您的QQ邮箱申请电影优选会员,您的动态码为" + checkcode + ",工作人员不会向您索取验证码，请勿向任何单位或个人泄露。";
        connecter.setContext(context);
        System.out.println(connecter.toString());
        flag = emailInstance.sendEmail(connecter);
        if (flag) {
            state = "200";
        }
        return state;
    }

    /**
     * @param name 验证是否存在使用该名称的用户
     */
    @RequestMapping(value = "/isExistName", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String isExistName(
            @RequestParam("name") String name
    ) {
        System.out.println("正在调用此isExistName函数");
        String state = "500";
        if (memberService.isExistMember(name) == 0) {
            state = "200";
        }
        return state;
    }

    /**
     * @param email 验证是否存在使用该邮箱的用户
     */
    @RequestMapping(value = "/isExistEmail", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String isExistEmail(
            @RequestParam("email") String email
    ) {
        System.out.println("正在调用此isExistEmail函数");
        String state = "500";
        if (mRegisteservice.isExistEmail(email) == 0) {
            state = "200";
        }
        return state;
    }

    /**
     * @param email
     * @param name
     * @param pwd
     * @return 注册会员
     */
    @RequestMapping(params = "action=registermember", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public void registerMember(
            @RequestParam("email") String email,
            @RequestParam("name") String name,
            @RequestParam("pwd") String pwd,
            PrintWriter out
    ) {
        String joindate = commentService.getCurrentTime();
        System.out.println("正在调用此registerMember函数");
        Member member = new Member(name, pwd, email, joindate);
        System.out.println(member.toString());
        if (memberService.addMember(member)) {
            //return "200";
            out.flush();
            out.println("<script>" + "alert('恭喜你，注册会员成功~~~');");
            out.print(" window.history.go(-1);");
            out.print("</script>");
            out.close();
        } else {
            out.flush();
            out.println("<script>" + "alert('抱歉，注册会员失败!~~~');");
            out.print(" window.history.go(-1);");
            out.print("</script>");
            out.close();
        }
        //return "500";
    }
}
