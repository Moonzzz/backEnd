package com.aiit.dao;

import com.aiit.pojo.Member;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface IMemberDao {
    @Update("update member set imgsrc=#{imgsrc},password=#{password},email=#{email}," +
            "sex=#{sex},phonenum=#{phonenum},birthday=#{birthday}," +
            "loginName=#{loginName} where id=#{id}")
    boolean updateMemberInfo(Member member);

    @Insert("INSERT INTO concern (mIder,mIded)" +
            "VALUES (#{mIder},#{mIded})")
    boolean addConcern(@Param("mIder") String mIder,@Param("mIded") String mIded);

   @Select("SELECT\n" +
           "count(*)\n" +
           "FROM\n" +
           "concern\n" +
           "WHERE\n" +
           "concern.mIder = #{mIder} AND\n" +
           "concern.mIded = #{mIded}")
    int isExistConcern(@Param("mIder") String mIder,@Param("mIded")String mIded);

    @Select("SELECT\n" +
            "count(*)\n" +
            "FROM\n" +
            "concern\n" +
            "WHERE\n" +
            "concern.mIder=#{mIder}\n")
    int countConcernNum(@Param("mIder") String mIder);

    @Select("SELECT\n" +
            "count(*)\n" +
            "FROM\n" +
            "concern\n" +
            "WHERE\n" +
            "concern.mIded=#{mIded}\n")
    int countBeConcernNum(@Param("mIded") String mIded);
}
