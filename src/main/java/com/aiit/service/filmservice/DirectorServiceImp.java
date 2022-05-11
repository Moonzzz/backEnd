package com.aiit.service.filmservice;

import com.aiit.dao.filmdao.IDirectorDao;
import com.aiit.pojo.filmpojo.Director;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
@Service(value = "directorService")
public class DirectorServiceImp implements IDirectorService {
    private final IDirectorDao directorDao;

    public DirectorServiceImp(IDirectorDao directorDao) {
        this.directorDao = directorDao;
    }
    @Override
    public List<Director> showDirector(int offset, int limit) {
        return directorDao.showDirector(offset, limit);
    }

    @Override
    public int showCount() {
        return directorDao.showCount();
    }

    @Override
    public int delDirector(int id) {
        return directorDao.delDirector(id);
    }

    @Override
    public int addDirector(Director director) {
        return directorDao.addDirector(director);
    }
}
