package com.aiit.service.impl;

import com.aiit.dao.IRecycleDao;

import com.aiit.pojo.*;
import com.aiit.service.IRecycleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecycleServiceImp implements IRecycleService {
    @Autowired
    private IRecycleDao recycleDao;

    public List<Reply> showReplyAll(int offset, int limit) {
        return this.recycleDao.selectReplyAll(offset, limit);
    }

    @Override
    public int showReplyCount() {
        return this.recycleDao.selectReplyCount();
    }

    public int deleteReply(int id) {
        return this.recycleDao.deleteOneReply(id);
    }

    @Override
    public int revocationReply(int id) {
        return this.recycleDao.revocationReplys(id);
    }

    @Override
    public List<Post> showPostAll(int offset, int limit) {
        return this.recycleDao.selectPostAll(offset, limit);
    }

    @Override
    public int showPostCount() {
        return this.recycleDao.selectPostCount();
    }

    public int deletePost(int id) {
        return this.recycleDao.deleteOnePost(id);
    }

    @Override
    public int revocationPost(int id) {
        return this.recycleDao.revocationPosts(id);
    }

    //回收actor
    public List<Actor> showAll(int offset, int limit) {
        return this.recycleDao.selectAll(offset, limit);
    }

    public int showCount() {
        return this.recycleDao.selectCount();
    }

    public int delete(int id) {
        return this.recycleDao.deleteOne(id);
    }

    @Override
    public int revocationActor(int id) {
        return this.recycleDao.revocationOne(id);
    }


    public List<Film> showAllFilm(int offset, int limit) {
        return this.recycleDao.selectAllFlim(offset, limit);
    }

    @Override
    public int showFilmCount() {
        return this.recycleDao.selectFilmCount();
    }

    public int deleteFilm(int id) {
        return this.recycleDao.deleteOneFilm(id);
    }

    @Override
    public int revocationFilm(int id) {
        return this.recycleDao.revocationFilms(id);
    }

    @Override
    public List<Author> showAllAuthor(int offset, int limit) {
        return this.recycleDao.selectAllAuthor(offset, limit);
    }

    @Override
    public int showAuthorCount() {
        return this.recycleDao.selectAuthorCount();
    }

    public int deleteAuthor(int id) {
        return this.recycleDao.deleteOneAuthor(id);
    }

    @Override
    public int revocationAuthor(int id) {
        return this.recycleDao.revocationAuthors(id);
    }

    @Override
    public List<Director> showAllDirector(int offset, int limit) {
        return this.recycleDao.selectAllDirector(offset, limit);
    }

    @Override
    public int showDirectorCount() {
        return this.recycleDao.selectDirectorCount();
    }

    public int deleteDirector(int id) {
        return this.recycleDao.deleteOneDirector(id);
    }

    @Override
    public int revocationDirector(int id) {
        return this.recycleDao.revocationDirectors(id);
    }

    public List<Member> showAllMember(int offset, int limit) {
        return this.recycleDao.selectAllMember(offset, limit);
    }

    @Override
    public int showMemberCount() {
        return this.recycleDao.selectMemberCount();
    }

    public int deleteMember(int id) {
        return this.recycleDao.deleteOneMember(id);
    }

    @Override
    public int revocationMember(int id) {
        return this.recycleDao.revocationMembers(id);
    }
}