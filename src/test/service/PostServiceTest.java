package service;

import com.aiit.pojo.Post;
import com.aiit.service.IPostService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring_*.xml")
public class PostServiceTest {
    @Autowired
    IPostService postService;

    @Test
    public void getListTest() {
        List<Post> list = postService.getList(1, 10, "id");
        System.out.println(list.size());
    }

    @Test
    public void getPostByTitleTest() {
        List<Post> list = postService.getListByTitle("a");
        System.out.println(list.size());
    }

    @Test
    public void addPostTest() {
        Timestamp date = new Timestamp(System.currentTimeMillis());
        Post post = new Post(2L, "bbb", "bbb", date);
        boolean isDone = postService.addPost(post);
        System.out.println(isDone+"----id:"+post.getId());
    }

}
