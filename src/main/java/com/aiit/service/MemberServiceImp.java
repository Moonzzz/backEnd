package com.aiit.service;

import com.aiit.dao.IUsersDao;
import com.aiit.pojo.Admin;
import com.aiit.pojo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

/**
 * @auther Mr Tang
 * @Date 2018/12/21 21:20
 */
@Transactional
@Service(value = "memberService")
public class MemberServiceImp implements IMemberService {
    private final IUsersDao usersDao;

    @Autowired
    public MemberServiceImp(IUsersDao usersDao) {
        this.usersDao = usersDao;
    }

    @Autowired
    Member member;


    @Override
    public String findPassword(String email) {
        String psw="";
        try{
           psw= usersDao.findPassword(email);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return psw;
    }

    @Override
    public List<Member> selectMembersName(String username) {
        List<Member> list;
        list = usersDao.selectMembersName(username);
        if (list.size() == 0) {
            member = new Member("无", "/", "/", "/");
            list.add(member);
        }
        return list;
    }

    @Override
    public List<Member> selectMembersByDate_Name(String start, String end, String username) {
        List<Member> list;
        list = usersDao.selectMembersByDate_Name(start, end, username);
        if (list.size() == 0) {
            member = new Member("无", "/", "/", "/");
            list.add(member);
        }
        return list;
    }

    @Override
    public List<Member> showAllMembers() {
        return usersDao.showAllMembers();
    }

    @Override
    public Member searchMemberByEmail(String email) {
        return usersDao.searchMemberByEmail(email);
    }

    @Override
    public int isExistMember(String name) {
        return usersDao.isExistMember(name);
    }

    @Override
    public boolean deleteMemberById(String id) {
        return usersDao.deleteMemberById(id);
    }

    @Override
    public int deleteMemberList(String[] ids) {
        return usersDao.deleteMemberList(ids);
    }

    @Override
    public boolean upDateMember(Member member) {
        return usersDao.upDateMember(member);
    }

    @Override
    public String returnNameById(String id) {
        return usersDao.returnNameById(id);
    }

    @Override
    public boolean upDateAdmin(Admin admin) {
        return usersDao.upDateAdmin(admin);
    }

    @Override
    public boolean addMember(Member member) {
        return usersDao.addMember(member);
    }

    @Override
    public Admin searchAdminByName(String name) {
        return usersDao.searchAdminByName(name);
    }

    @Override
    public Member searchMemberById(String id) {
        return usersDao.searchMemberById(id);
    }
}
