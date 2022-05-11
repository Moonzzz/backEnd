package com.aiit.service;

import com.aiit.dao.IFilmPageDao;
import com.aiit.dao.IUsersDao;
import com.aiit.pojo.FilmDetail;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auther Mr Tang
 * @Date 2018/12/24 14:49
 */
@Transactional
@Service("filmPageservice")
public class FilmPageServiceImp implements IFilmPageService {
    private final IFilmPageDao filmDao;
    private final IUsersDao usersDao;

    public FilmPageServiceImp(IFilmPageDao filmDao, IUsersDao usersDao) {
        this.filmDao = filmDao;
        this.usersDao = usersDao;
    }

    @Override
    public List<FilmDetail> SimilarFilms(String fId) {
        String  type=filmDao.returnTypeByfId(fId);//先由电影找到电影类型
        return filmDao.SimilarFilms(fId,type);//再随机查找到三个评分再8分以上的同类型电影
    }

    @Override
    public Map<String, String> returStart_End(String dateStr) {
        Map<String, String> dataMap = new HashMap<String, String>();
        switch (dateStr) {
            case "全部":
                dataMap.put("start", "0001-01-01");
                dataMap.put("end", "2099-12-31");
                break;
            case "2019":
                dataMap.put("start", "2019-01-01");
                dataMap.put("end", "2019-12-31");
                break;
            case "2018":
                dataMap.put("start", "2018-01-01");
                dataMap.put("end", "2018-12-31");
                break;
            case "2017":
                dataMap.put("start", "2017-01-01");
                dataMap.put("end", "2017-12-31");
                break;
            case "2016":
                dataMap.put("start", "2016-01-01");
                dataMap.put("end", "2016-12-31");
                break;
            case "2015-2010":
                dataMap.put("start", "2010-01-01");
                dataMap.put("end", "2015-12-31");
                break;
            case "2010-2000":
                dataMap.put("start", "2000-01-01");
                dataMap.put("end", "2009-12-31");
                break;
            case "90年代":
                dataMap.put("start", "1990-01-01");
                dataMap.put("end", "1999-12-31");
                break;
            case "80年代":
                dataMap.put("start", "1980-01-01");
                dataMap.put("end", "1989-12-31");
                break;
            case "70年代":
                dataMap.put("start", "1970-01-01");
                dataMap.put("end", "1979-12-31");
                break;
            case "60年代":
                dataMap.put("start", "1960-01-01");
                dataMap.put("end", "1969-12-31");
                break;
            case "更早":
                dataMap.put("start", "0001-01-01");
                dataMap.put("end", "1959-12-31");
                break;
            default:
                dataMap.put("start", "0001-01-01");
                dataMap.put("end", "2099-12-31");
                break;
        }
        return dataMap;
    }

    @Override
    public List<FilmDetail> searchFilmByType_Day(String nation,String type, String start, String end) {
        return filmDao.searchFilmByType_Day(nation,type, start, end);
    }

    @Override
    public List<String> returnAllFilmTypes() {
        return filmDao.returnAllFilmTypes();
    }

    @Override
    public List<String> returnSearchNames(String name) {
        return filmDao.returnSearchNames(name);
    }

    @Override
    public int memberLoginByEmail(String email, String psw) {
        return usersDao.memberLoginByEmail(email, psw);
    }

    @Override
    public int memberLoginByName(String name, String psw) {
        return usersDao.memberLoginByName(name, psw);
    }

    public FilmDetail searchFilmDetailById(String id) {
        return filmDao.searchFilmDetailById(id);
    }

    public List<String> searchActorsById(String id) {
        List<String> list;
        list = filmDao.searchActorsById(id);
        if (list.size() == 0) {
            list.add("无");
        }
        return list;
    }

    @Override
    public List<String> searchDirectorsById(String id) {
        List<String> list;
        list = filmDao.searchDirectorsById(id);
        if (list.size() == 0) {
            list.add("无");
        }
        return list;
    }

    @Override
    public List<FilmDetail> showTenFilms() {
        return filmDao.showTenFilms();
    }

    @Override
    public List<FilmDetail>
    randomSarchFilms() {
        return filmDao.randomSarchFilms();
    }

    @Override
    public List<FilmDetail> searchFilmDeatilsByName(String name) {
        return filmDao.searchFilmDeatilsByName(name);
    }
}
