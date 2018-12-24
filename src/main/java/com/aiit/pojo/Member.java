package com.aiit.pojo;

import org.springframework.stereotype.Component;

@Component(value = "member")
public class Member {

    private long id;
    private String loginName;
    private String password;
    private String sex;
    private String phonenum;
    private String email;
    private String birthday;
    private String joindate;

    public Member() {
    }

    public Member(String loginName, String password, String email) {
        this.loginName = loginName;
        this.password = password;
        this.email = email;
    }

    public Member(String loginName, String password, String sex, String phonenum, String email, String birthday, String joindate) {
        this.loginName = loginName;
        this.password = password;
        this.sex = sex;
        this.phonenum = phonenum;
        this.email = email;
        this.birthday = birthday;
        this.joindate = joindate;
    }

    public Member(int id, String loginName, String password, String sex, String phonenum, String email, String birthday) {
        this.id = id;
        this.loginName = loginName;
        this.password = password;
        this.sex = sex;
        this.phonenum = phonenum;
        this.email = email;
        this.birthday = birthday;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }


    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }


    public String getJoindate() {
        return joindate;
    }

    public void setJoindate(String joindate) {
        this.joindate = joindate;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", loginName='" + loginName + '\'' +
                ", password='" + password + '\'' +
                ", sex='" + sex + '\'' +
                ", phonenum='" + phonenum + '\'' +
                ", email='" + email + '\'' +
                ", birthday='" + birthday + '\'' +
                ", joindate='" + joindate + '\'' +
                '}';
    }
}
