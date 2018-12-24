package com.aiit.pojo;

import org.springframework.stereotype.Component;

/**
 * @auther Mr Tang
 * @Date 2018/12/17 15:34
 */
@Component(value = "admin")
public class Admin {
    private int adminid;
    private String adminname;
    private String adminpsw;

    public Admin() {
    }

    public Admin(String adminname, String adminpsw) {
        this.adminname = adminname;
        this.adminpsw = adminpsw;
    }

    public Admin(int adminid, String adminname, String adminpsw) {
        this.adminid = adminid;
        this.adminname = adminname;
        this.adminpsw = adminpsw;
    }

    public int getAdminid() {
        return adminid;
    }

    public void setAdminid(int adminid) {
        this.adminid = adminid;
    }

    public String getAdminname() {
        return adminname;
    }

    public void setAdminname(String adminname) {
        this.adminname = adminname;
    }

    public String getAdminpsw() {
        return adminpsw;
    }

    public void setAdminpsw(String adminpsw) {
        this.adminpsw = adminpsw;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "adminid=" + adminid +
                ", adminname='" + adminname + '\'' +
                ", adminpsw='" + adminpsw + '\'' +
                '}';
    }
}
