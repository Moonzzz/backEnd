package com.aiit.service.filmservice;

import com.aiit.dao.filmdao.IUserDao;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service(value="userService")
public class UserServiceImp implements IUserService{
    @Autowired
    SqlSessionFactory sqlSessionFactory;
    @Override
    public String findPassword(String loginName) {
        SqlSession session = sqlSessionFactory.openSession();
        IUserDao userDao = session.getMapper(IUserDao.class);
        return userDao.findPassword(loginName);
    }
}
