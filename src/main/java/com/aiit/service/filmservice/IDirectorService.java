package com.aiit.service.filmservice;

import com.aiit.pojo.filmpojo.Director;

import java.util.List;

public interface IDirectorService {
    List<Director> showDirector(int offset, int limit);
    int showCount();
    int delDirector(int id);
    int addDirector(Director director);
}
