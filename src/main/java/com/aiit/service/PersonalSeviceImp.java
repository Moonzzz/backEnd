package com.aiit.service;

import com.aiit.dao.IMemberDao;
import com.aiit.dao.IUsersDao;
import com.aiit.pojo.Member;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @auther Mr Tang
 * @Date 2019/1/1 20:13
 */
@Transactional
@Service("personalSevice")
public class PersonalSeviceImp implements IPersonalService {
    private final IUsersDao usersDao;
    private final IMemberDao memberDao;

    public PersonalSeviceImp(IUsersDao usersDao, IMemberDao memberDao) {
        this.usersDao = usersDao;
        this.memberDao = memberDao;
    }

    @Override
    public Member returnMemberByName(String name) {
        return usersDao.returnMemberByName(name);
    }

    @Override
    public boolean addConcern(String mIder, String mIded) {
        return memberDao.addConcern(mIder, mIded);
    }

    @Override
    public int countConcernNum(String mIder) {
        return memberDao.countConcernNum(mIder);
    }

    @Override
    public int countBeConcernNum(String mIded) {
        return memberDao.countBeConcernNum(mIded);
    }

    @Override
    public int isExistConcern(String mIder, String mIded) {
        return memberDao.isExistConcern(mIder, mIded);
    }

    @Override
    public boolean updateMemberInfo(Member member) {
        return memberDao.updateMemberInfo(member);
    }
}
