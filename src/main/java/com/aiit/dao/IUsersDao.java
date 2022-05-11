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
    @Select("select password from member where email = #{email}")
    String findPassword(@Param("email")String email);

    @Select("SELECT *\n" +
            "FROM member\n" +
            "WHERE loginName  LIKE CONCAT('%',#{username},'%')")
    List<Member> selectMembersName(@Param("username") String username);

    @Select("SELECT *\n" +
            "FROM member\n" +
            "WHERE joindate\n" +
            "BETWEEN #{start} AND #{end}\n" +
            "and loginName  LIKE CONCAT('%',#{username},'%')")
    List<Member> selectMembersByDate_Name(@Param("start") String start,
                                          @Param("end") String end,
                                          @Param("username") String username);

    @Select("select * from member where id=#{id}")
    Member searchMemberById(@Param("id") String id);

    @Select("select count(*) from admin where adminname=#{adminname} and adminpsw=#{adminpsw}")
    boolean AdminLoginCheck(Admin admin);

    @Select("select count(*) from member where email=#{email} and password=#{psw}")
    int memberLoginByEmail(@Param("email") String emaill, @Param("psw") String psw);

    @Select("select count(*) from member where loginName=#{name} and password=#{psw}")
    int memberLoginByName(@Param("name") String name, @Param("psw") String psw);

    @Select("select * from member")
    List<Member> showAllMembers();

    @Select("select * from member where loginName=#{name}")
    Member returnMemberByName(@Param("name") String name);

    @Select("select * from member where loginName=#{loginName}")
    Member searchMemberByName(@Param("loginName") String name);

    @Select("select * from member where email=#{email}")
    Member searchMemberByEmail(@Param("email") String email);

    @Select("select * from admin where adminname=#{adminname}")
    Admin searchAdminByName(@Param("adminname") String name);

    @Insert("INSERT INTO member (loginName,password,email,joindate)" +
            "VALUES (#{loginName},#{password},#{email},#{joindate})")
    boolean addMember(Member member);

    @Select("select count(*) from member where loginname=#{name}")
    int isExistMember(@Param("name") String name);

    @Select("select count(*) from member where email=#{email}")
    int isExistEmail(@Param("email") String email);

    @Select("select loginName from member where id=#{id}")
    String returnNameById(@Param("id") String id);

    @Update("update member set password=#{password},email=#{email}," +
            "sex=#{sex},phonenum=#{phonenum},birthday=#{birthday}," +
            "loginName=#{loginName} where id=#{id}")
    boolean upDateMember(Member member);

    @Update("update admin set adminname=#{adminname},adminpsw=#{adminpsw}" +
            "where adminid=#{adminid}")
    boolean upDateAdmin(Admin admin);

    @Delete("DELETE FROM member WHERE id = #{id}")
    boolean deleteMemberById(@Param("id") String id);

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
