package com.aiit.service.filmservice;

import com.aiit.dao.filmdao.IFilmDao;
import com.aiit.dao.filmdao.IGenreDao;
import com.aiit.pojo.filmpojo.Genre;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
@Service(value = "genreService")
public class GenreServiceImp implements IGenreService {
    private final IGenreDao genreDao;

    public GenreServiceImp(IGenreDao genreDao) {
        this.genreDao = genreDao;
    }

    @Override
    public List<Genre> getGenres() {
        return genreDao.getGenres();
    }
}
