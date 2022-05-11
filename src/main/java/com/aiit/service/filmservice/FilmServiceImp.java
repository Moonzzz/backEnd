package com.aiit.service.filmservice;

import com.aiit.dao.filmdao.IFilmDao;
import com.aiit.pojo.filmpojo.Film;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
@Service(value = "filmService")
public class FilmServiceImp implements IFilmService {
    private final IFilmDao filmDao;

    public FilmServiceImp(IFilmDao filmDao) {
        this.filmDao = filmDao;
    }

    @Override
    public List<Film> show(int offset, int limit) {
        return filmDao.selectAll(offset, limit);
    }

    @Override
    public int showCount() {
        return filmDao.showCount();
    }

    @Override
    public int delete(String fNo) {
        return filmDao.delFilm(fNo);
    }

    @Override
    public int modFilm(Film film) {
        return filmDao.modFilm(film);
    }

    @Override
    public List<Film> getFilm(int offset, int limit) {

        return filmDao.getFilm(offset, limit);
    }
}
