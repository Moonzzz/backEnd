package com.aiit.service;

import com.aiit.pojo.Admin;
import com.aiit.pojo.Member;

import java.util.List;

/**
 * @auther Mr Tang
 * @Date 2018/12/21 21:19
 */
public interface IMemberService {
     List<Member> showAllMembers();
     Member searchMemberById(String id);
     Admin searchAdminByName(String name);
     int isExistMember(String name);
     boolean addMember(Member member);
     boolean upDateAdmin(Admin admin);
     String returnNameById(int id);
     boolean upDateMember(Member member);
     boolean deleteMemberById(int id);
     int deleteMemberList(String[] ids);
}
