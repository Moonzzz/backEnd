package com.aiit.service;

import com.aiit.pojo.Admin;
import com.aiit.pojo.Member;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @auther Mr Tang
 * @Date 2018/12/21 21:19
 */
public interface IMemberService {

    String findPassword(String email);

    List<Member> selectMembersName(String username);

    List<Member> selectMembersByDate_Name(String start, String end, String username);

    List<Member> showAllMembers();

    Member searchMemberByEmail(String email);

    Member searchMemberById(String id);

    Admin searchAdminByName(String name);

    int isExistMember(String name);

    boolean addMember(Member member);

    boolean upDateAdmin(Admin admin);

    String returnNameById(String id);

    boolean upDateMember(Member member);

    boolean deleteMemberById(String id);

    int deleteMemberList(String[] ids);
}
