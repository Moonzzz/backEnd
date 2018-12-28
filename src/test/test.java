import com.aiit.dao.DaoTest;
import com.aiit.dao.IPostDao;
import com.aiit.pojo.Post;
import com.aiit.service.IPostService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring/spring_*.xml"})
public class test {
    @Autowired
    SqlSessionFactory sqlSessionFactory;

    @Test
    public void test() {
        SqlSession session = sqlSessionFactory.openSession();
        DaoTest dao = session.getMapper(DaoTest.class);
        List<String> list = dao.daoTest();
        System.out.println(list.toString());
    }

    @Autowired
    DaoTest daoTest;
    @Autowired
    IPostDao postDao;
    @Autowired
    IPostService postService;

    @Test
    public void daoTest() {
        daoTest.daoTest();
        String listJson = postService.getList(1, 10, "id");
        System.out.println(listJson);
    }

    @Test
    public void addPost(){
        for(int i=3;i<100;i++){
            Post post = new Post(2L,"test"+i,"context"+i,new Timestamp(System.currentTimeMillis()));
            postDao.insert(post);
        }
    }

}
