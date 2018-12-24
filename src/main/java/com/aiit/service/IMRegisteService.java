package com.aiit.service;

import com.aiit.pojo.Admin;

/**
 * @auther Mr Tang
 * @Date 2018/12/21 22:07
 */
public interface IMRegisteService {
    boolean AdminLoginCheck(Admin admin);
    int isExistEmail(String email);

}
