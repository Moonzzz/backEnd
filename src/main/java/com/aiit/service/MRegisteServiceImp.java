package com.aiit.service;

import com.aiit.dao.IUsersDao;
import com.aiit.pojo.Admin;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.sql.SQLClientInfoException;

/**
 * @auther Mr Tang
 * @Date 2018/12/21 22:13
 */
@Transactional
@Service("mRegisteService")
public class MRegisteServiceImp implements IMRegisteService {
    private final IUsersDao usersDao;

    @Autowired
    public MRegisteServiceImp(IUsersDao usersDao) {
        this.usersDao = usersDao;
    }

    @Override
    public int isExistEmail(String email) {
       return usersDao.isExistEmail(email);
    }

    @Override
    public boolean AdminLoginCheck(Admin admin) {
        return usersDao.AdminLoginCheck(admin);
    }

}
