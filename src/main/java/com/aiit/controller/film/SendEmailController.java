package com.aiit.controller.film;

import com.aiit.pojo.EmailConnecter;
import com.aiit.service.filmservice.IUserService;
import com.aiit.service.SendEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 找回密码的controller
 */
@Controller
public class SendEmailController {
    @Autowired
    private SendEmailService emailInstance;
    @Autowired
    IUserService userService;
    @Autowired
    private EmailConnecter connecter;

    @RequestMapping(value = "findPassword.action", method = RequestMethod.POST)
    @ResponseBody
    public String sendRecheckEmail(HttpServletRequest request) {
        String email = request.getParameter("email");
        String password = userService.findPassword(email);
        connecter.setLicensekey("apappnalxspoigdf");
        connecter.setFromport("1074356353");
        connecter.setToport(email);
        connecter.setTitle("找回密码");
        String context = "【电影优选】您的密码为" + password + ",工作人员不会向您索取验证码，请勿向任何单位或个人泄露。";
        connecter.setContext(context);
        System.out.println(connecter.toString());
        emailInstance.sendEmail(connecter);

        return "true";
    }

}
