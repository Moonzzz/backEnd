package com.aiit.pojo;

import org.springframework.stereotype.Component;

/**
 * @auther Mr Tang
 * @Date 2018/12/19 21:14
 * 建立发送邮箱的实体类，将发送邮箱需要的各种必要信息打包
 */
@Component("connecter")
public class EmailConnecter {
    private String fromport;//发送方
    private String toport;//接收方
    private String licensekey;//授权码
    private String title;//邮件标题
    private  String context;//邮件正文

    public EmailConnecter() {
    }

    public EmailConnecter(String fromport, String toport, String licensekey, String title, String context) {
        this.fromport = fromport;
        this.toport = toport;
        this.licensekey = licensekey;
        this.title = title;
        this.context = context;
    }

    public String getFromport() {
        return fromport;
    }

    public void setFromport(String fromport) {
        this.fromport = fromport;
    }

    public String getToport() {
        return toport;
    }

    public void setToport(String toport) {
        this.toport = toport;
    }

    public String getLicensekey() {
        return licensekey;
    }

    public void setLicensekey(String licensekey) {
        this.licensekey = licensekey;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    @Override
    public String toString() {
        return "EmailConnecter{" +
                "fromport='" + fromport + '\'' +
                ", toport='" + toport + '\'' +
                ", licensekey='" + licensekey + '\'' +
                ", title='" + title + '\'' +
                ", context='" + context + '\'' +
                '}';
    }
}
