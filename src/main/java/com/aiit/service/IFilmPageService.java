package com.aiit.service;

import com.aiit.pojo.FilmDetail;

import java.util.List;
import java.util.Map;

public interface IFilmPageService {
    List<FilmDetail> SimilarFilms(String fId);

    Map<String, String> returStart_End(String dateStr);

    List<FilmDetail> searchFilmByType_Day(String nation, String type, String start, String end);

    List<String> returnAllFilmTypes();

    List<String> returnSearchNames(String name);

    int memberLoginByEmail(String emaill, String psw);

    int memberLoginByName(String name, String psw);

    FilmDetail searchFilmDetailById(String id);

    List<String> searchActorsById(String id);

    List<String> searchDirectorsById(String id);

    List<FilmDetail> searchFilmDeatilsByName(String name);

    List<FilmDetail> randomSarchFilms();

    List<FilmDetail> showTenFilms();
}
