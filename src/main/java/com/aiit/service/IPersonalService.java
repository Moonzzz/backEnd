package com.aiit.service;

import com.aiit.pojo.Member;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @auther Mr Tang
 * @Date 2018/12/31 21:13
 */
public interface IPersonalService {
    Member returnMemberByName(@Param("name") String name);
    boolean updateMemberInfo(Member member);
    boolean addConcern(String mIder,String mIded);
    int isExistConcern(String mIder,String mIded);
    int countConcernNum(@Param("mIder") String mIder);
    int countBeConcernNum(@Param("mIded") String mIded);

}
