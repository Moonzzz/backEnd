package com.aiit.service.filmservice;

import com.aiit.pojo.filmpojo.Author;

import java.util.List;

public interface IAuthorService {
    List<Author> showAuthor(int offset, int limit);
    int showCount();
    int delAuthor(int id);
    int addAuthor(Author author);
}
