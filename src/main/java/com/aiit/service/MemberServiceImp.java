package com.aiit.service;

import com.aiit.dao.IUsersDao;
import com.aiit.pojo.Admin;
import com.aiit.pojo.Member;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auther Mr Tang
 * @Date 2018/12/21 21:20
 */
@Service(value = "memberService")
public class MemberServiceImp implements IMemberService {

    @Autowired
    SqlSessionFactory sqlSessionFactory;

    @Override
    public List<Member> showAllMembers() {
        List<Member> list;
        SqlSession session = sqlSessionFactory.openSession(true);
        IUsersDao iuser = session.getMapper(IUsersDao.class);
        list = iuser.showAllMembers();
        return list;
    }

    @Override
    public int isExistMember(String name) {
        int count = 0;
        SqlSession session = sqlSessionFactory.openSession(true);
        IUsersDao iuser = session.getMapper(IUsersDao.class);
        count = iuser.isExistMember(name);
        return count;
    }

    @Override
    public boolean deleteMemberById(int id) {
        boolean flag = false;
        SqlSession session = sqlSessionFactory.openSession(true);
        IUsersDao iuser = session.getMapper(IUsersDao.class);
        flag = iuser.deleteMemberById(id);
        return flag;
    }

    @Override
    public int deleteMemberList(String[] ids) {
        int result = 0;
        SqlSession session = sqlSessionFactory.openSession();
        IUsersDao iuser = session.getMapper(IUsersDao.class);
        result = iuser.deleteMemberList(ids);
        return result;
    }

    @Override
    public boolean upDateMember(Member member) {
        boolean flag = false;
        SqlSession session = sqlSessionFactory.openSession(true);
        IUsersDao iuser = session.getMapper(IUsersDao.class);
        flag = iuser.upDateMember(member);
        return flag;
    }

    @Override
    public String returnNameById(int id) {
        String name = "";
        SqlSession session = sqlSessionFactory.openSession(true);
        IUsersDao iuser = session.getMapper(IUsersDao.class);
        name = iuser.returnNameById(id);
        return name;
    }

    @Override
    public boolean upDateAdmin(Admin admin) {
        boolean flag;
        SqlSession session = sqlSessionFactory.openSession(true);
        IUsersDao iuser = session.getMapper(IUsersDao.class);
        flag = iuser.upDateAdmin(admin);
        return flag;
    }

    @Override
    public boolean addMember(Member member) {
        boolean flag;
        SqlSession session = sqlSessionFactory.openSession(true);
        IUsersDao iuser = session.getMapper(IUsersDao.class);
        flag = iuser.addMember(member);
        return flag;
    }

    @Override
    public Admin searchAdminByName(String name) {
        Admin admin = new Admin();
        SqlSession session = sqlSessionFactory.openSession(true);
        IUsersDao iuser = session.getMapper(IUsersDao.class);
        admin = iuser.searchAdminByName(name);
        return admin;
    }

    @Override
    public Member searchMemberById(String id) {
        Member member = new Member();
        SqlSession session = sqlSessionFactory.openSession(true);
        IUsersDao iuser = session.getMapper(IUsersDao.class);
        member = iuser.searchMemberById(id);
        return member;
    }
}
