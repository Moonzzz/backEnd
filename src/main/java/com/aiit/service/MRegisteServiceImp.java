package com.aiit.service;

import com.aiit.dao.IUsersDao;
import com.aiit.pojo.Admin;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.SQLClientInfoException;

/**
 * @auther Mr Tang
 * @Date 2018/12/21 22:13
 */
@Service("mRegisteservice")
public class MRegisteServiceImp implements IMRegisteService {
    @Autowired
    SqlSessionFactory sqlSessionFactory;

    @Override
    public int isExistEmail(String email) {
        int count = 0;
        SqlSession session = sqlSessionFactory.openSession(true);
        IUsersDao iuser = session.getMapper(IUsersDao.class);
        count = iuser.isExistEmail(email);
        return count;
    }

    @Override
    public boolean AdminLoginCheck(Admin admin) {
        boolean flag = false;
        try {
            SqlSession session = sqlSessionFactory.openSession(true);
            IUsersDao iuser = session.getMapper(IUsersDao.class);
            flag = iuser.AdminLoginCheck(admin);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return flag;
    }

}
