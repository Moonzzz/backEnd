package com.aiit.service.filmservice;

import com.aiit.pojo.filmpojo.Film;

import java.util.List;

public interface IFilmService {
    public List<Film> show(int offset, int limit);
    public int showCount();
    public int  delete(String fNo);
    public  int modFilm(Film film);
    List<Film> getFilm(int offset, int limit);
}
