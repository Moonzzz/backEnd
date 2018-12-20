package com.aiit.test;

import com.aiit.dao.DaoTest;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring_mvc.xml","classpath:appContext.xml"})
public class test{
    @Autowired
    SqlSessionFactory sqlSessionFactory;
    @Test
    public void test(){
        SqlSession session = sqlSessionFactory.openSession();
        DaoTest dao = session.getMapper(DaoTest.class);
         List<String> list = dao.daoTest();
        System.out.println(list.toString());
    }
}
