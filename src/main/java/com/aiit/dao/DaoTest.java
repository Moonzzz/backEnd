package com.aiit.dao;

import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DaoTest {
    @Select("select name from film")
    List<String> daoTest();
}
