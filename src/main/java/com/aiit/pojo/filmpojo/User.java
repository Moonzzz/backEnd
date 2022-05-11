package com.aiit.pojo.filmpojo;


public class User {

  private long id;
  private String loginName;
  private String password;

  public User() {
  }

  public User(String loginName, String password) {
    this.loginName = loginName;
    this.password = password;
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

  @Override
  public String toString() {
    return "User{" +
            "id=" + id +
            ", loginName='" + loginName + '\'' +
            ", password='" + password + '\'' +
            '}';
  }
}
