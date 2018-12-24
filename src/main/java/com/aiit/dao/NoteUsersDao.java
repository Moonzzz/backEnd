package com.aiit.dao;

import com.aiit.pojo.Admin;
import com.aiit.pojo.Member;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("usersdao")
public class NoteUsersDao  implements IUsersDao {
    @Autowired
    static SqlSessionFactory sqlSessionFactory;

    @Override
    public Member searchMemberById(String id) {
        return null;
    }

    @Override
    public boolean AdminLoginCheck(Admin admin) {
        boolean flag = false;
        try {
            SqlSession session = sqlSessionFactory.openSession(true);
            IUsersDao iuser = session.getMapper(IUsersDao.class);
            flag = iuser.AdminLoginCheck(admin);
            session.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public List<Member> showAllMembers() {
        List<Member> list = new ArrayList<Member>();
        try {
            SqlSession session = sqlSessionFactory.openSession(true);
            IUsersDao iuser = session.getMapper(IUsersDao.class);
            list = iuser.showAllMembers();
            session.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * @param name
     * @return <p>这个函数的功能是根据用户名查找用户信息</>
     */


    @Override
    public Member searchMemberByName(String name) {
        Member member = new Member();
        try {
            SqlSession session = sqlSessionFactory.openSession(true);
            IUsersDao iuser = session.getMapper(IUsersDao.class);
            member = iuser.searchMemberByName(name);
            session.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return member;
    }

    @Override
    public Admin searchAdminByName(String name) {
        Admin admin = new Admin();
        try {
            SqlSession session = sqlSessionFactory.openSession(true);
            IUsersDao iuser = session.getMapper(IUsersDao.class);
            admin = iuser.searchAdminByName(name);
            session.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return admin;
    }

    @Override
    public boolean addMember(Member member) {
        boolean flag = false;
        try {
            SqlSession session = sqlSessionFactory.openSession(true);
            IUsersDao iuser = session.getMapper(IUsersDao.class);
            flag = iuser.addMember(member);
            session.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public int isExistMember(String name) {
        int count = 0;
        try {
            SqlSession session = sqlSessionFactory.openSession(true);
            IUsersDao iuser = session.getMapper(IUsersDao.class);
            count = iuser.isExistMember(name);
            session.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public int isExistEmail(String email) {
        int count = 0;
        try {
            SqlSession session = sqlSessionFactory.openSession(true);
            IUsersDao iuser = session.getMapper(IUsersDao.class);
            count = iuser.isExistEmail(email);
            session.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public String returnNameById(int id) {
        String name = "";
        try {
            SqlSession session = sqlSessionFactory.openSession(true);
            IUsersDao iuser = session.getMapper(IUsersDao.class);
            name = iuser.returnNameById(id);
            session.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return name;
    }

    @Override
    public boolean upDateMember(Member member) {
        boolean flag = false;
        try {
            SqlSession session = sqlSessionFactory.openSession(true);
            IUsersDao iuser = session.getMapper(IUsersDao.class);
            flag = iuser.upDateMember(member);
            session.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean upDateAdmin(Admin admin) {
        boolean flag = false;
        try {
            SqlSession session = sqlSessionFactory.openSession(true);
            IUsersDao iuser = session.getMapper(IUsersDao.class);
            flag = iuser.upDateAdmin(admin);
            session.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean deleteMemberById(int id) {
        boolean flag = false;
        try {
            SqlSession session = sqlSessionFactory.openSession(true);
            IUsersDao iuser = session.getMapper(IUsersDao.class);
            flag = iuser.deleteMemberById(id);
            session.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public int deleteMemberList(String[] ids) {
        int result = 0;
        try {
            SqlSession session = sqlSessionFactory.openSession();
            IUsersDao iuser = session.getMapper(IUsersDao.class);
            result = iuser.deleteMemberList(ids);
            session.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
