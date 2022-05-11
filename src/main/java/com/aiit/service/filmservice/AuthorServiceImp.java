package com.aiit.service.filmservice;

import com.aiit.dao.filmdao.IAuthorDao;
import com.aiit.pojo.filmpojo.Author;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * @author pc
 */
@Transactional
@Service(value="authorService")
public class AuthorServiceImp implements IAuthorService {
    private final IAuthorDao authorDao;
    public AuthorServiceImp(IAuthorDao authorDao){
        this.authorDao=authorDao;
    }
    @Override
    public List<Author> showAuthor(int offset, int limit) {
        return authorDao.showAuthor(offset, limit);
    }

    @Override
    public int showCount() {
        return authorDao.showCount();
    }

    @Override
    public int delAuthor(int id) {
        return authorDao.delAuthor(id);
    }

    @Override
    public int addAuthor(Author author) {
        return authorDao.addAuthor(author);
    }
}
