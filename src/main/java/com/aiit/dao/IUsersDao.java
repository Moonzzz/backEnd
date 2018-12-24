package com.aiit.dao;

import com.aiit.pojo.Admin;
import com.aiit.pojo.Member;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @auther Mr Tang
 * @Date 2018/12/17 15:36
 */

public interface IUsersDao {
    @Select("select * from member where id=#{id}")
    Member searchMemberById(@Param("id") String id);

    @Select("select * from admin where adminname=#{adminname} and adminpsw=#{adminpsw}")
    boolean AdminLoginCheck(Admin admin);

    @Select("select * from member")
    List<Member> showAllMembers();

    @Select("select * from member where loginName=#{loginName}")
    Member searchMemberByName(@Param("loginName") String name);

    @Select("select * from admin where adminname=#{adminname}")
    Admin searchAdminByName(@Param("adminname") String name);

    @Insert("INSERT INTO member (loginName,password,email)" +
            "VALUES (#{loginName},#{password},#{email})")
    boolean addMember(Member member);

    @Select("select count(*) from member where loginname=#{name}")
    int isExistMember(@Param("name") String name);

    @Select("select count(*) from member where email=#{email}")
    int isExistEmail(@Param("email") String email);

    @Select("select loginName from member where id=#{id}")
    String returnNameById(@Param("id") int id);

    @Update("update member set password=#{password},email=#{email}," +
            "sex=#{sex},phonenum=#{phonenum},birthday=#{birthday}," +
            "loginName=#{loginName} where id=#{id}")
    boolean upDateMember(Member member);

    @Update("update admin set adminname=#{adminname},adminpsw=#{adminpsw}" +
            "where adminid=#{adminid}")
    boolean upDateAdmin(Admin admin);

    @Delete("DELETE FROM member WHERE id = #{id}")
    boolean deleteMemberById(@Param("id") int id);

    @Delete({
            "<script>" +
                    "delete from member where id in"
                    + "<foreach item='status' index='index' collection='array' open='(' separator=',' close=')'>"
                    + "#{status} "
                    + "</foreach>" +
                    "</script>"
    })
    int deleteMemberList(String[] ids);
}
