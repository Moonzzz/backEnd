package com.aiit.service;

import com.aiit.pojo.EmailConnecter;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @auther Mr Tang
 * @Date 2018/12/19 21:11
 * 发送邮件的类
 */
@Transactional
@Repository("emailInstance")
public class SendEmailService {
    public boolean sendEmail(EmailConnecter connecter) {
        System.out.println("正在发送邮件");
        System.out.println(connecter.toString());
        //创建邮件发送器
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        //开启  POP3/SMTP服务
        mailSender.setHost("smtp.qq.com");// QQ邮箱smtp发送服务器地址
        // mailSender.setPort(587);// 端口号
        mailSender.setUsername(connecter.getFromport() + "@qq.com"); // 使用你自己的账号
        // mailSender.setPassword("wiefzostsyuqfjeb");//授权码
        mailSender.setPassword(connecter.getLicensekey());
        System.out.println(mailSender.getSession());
        // 具体邮件信息
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(connecter.getFromport() + "@qq.com"); // 发件人邮箱
        // msg.setTo("3161814915@qq.com"); // 收件人邮箱
        simpleMailMessage.setTo(connecter.getToport());
        simpleMailMessage.setSubject(connecter.getTitle()); // 标题
        simpleMailMessage.setText(connecter.getContext()); // 文本信息
        System.out.println(simpleMailMessage.toString());
        try {
            mailSender.send(simpleMailMessage);
            System.out.println("邮件发送成功!");
            return true;
        } catch (MailException ex) {
            System.err.println("发送失败:" + ex.getMessage());
            return false;
        }
    }
/*    public static String main(String[] args) {
        boolean flag;
        SendEmail testSpringEmail = new SendEmail();
        EmailConnecter connecter = new EmailConnecter();
        connecter.setFromport("1074356353");
        connecter.setToport("3161814915@qq.com");
        connecter.setLicensekey("wiefzostsyuqfjeb");
        connecter.setTitle("验证码信息");
        connecter.setContext("【电影优选】您的手机动态码 766122,请勿向任何单位或个人泄露。");
        flag = testSpringEmail.sendEmail(connecter);
        System.out.println(flag);
        return "200";
    }*/
}
