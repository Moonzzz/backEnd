package com.aiit.dao.filmdao;

import org.apache.ibatis.annotations.Select;

/**
 * 用户操作-找回密码
 */
public interface IUserDao {
    @Select("select password from user where loginName = #{loginName}")
    String findPassword(String loginName);
}
