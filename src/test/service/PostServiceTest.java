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
import java.util.TimeZone;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring/spring_*.xml")
public class PostServiceTest {
    @Autowired
    IPostService postService;

    @Test
    public void getListTest() {
        String listJson = postService.getList(1, 10, "id");
        System.out.println(listJson);
    }

    @Test
    public void getPostByTitleTest() {
        List<Post> list = postService.getListByTitle("b");
        System.out.println(list.get(0));
    }

    @Test
    public void addPostTest() {
        Timestamp date = new Timestamp(System.currentTimeMillis());
        Post post = new Post(2L, "bbb", "bbb", date);
        boolean isDone = postService.addPost(post);
        System.out.println(isDone+"----id:"+post.getId());
    }

    @Test
    public void deletePostTest(){
        boolean isDone = postService.deletePost(3L);
        System.out.println(isDone);
    }
    @Test
    public void updatePostTest(){
        TimeZone timeZone = TimeZone.getDefault();
        System.out.println(timeZone);
        Timestamp date = new Timestamp(System.currentTimeMillis());
        Post post = new Post(2L, "bbb", "Ccc", date);
        post.setId(2L);
        boolean isDone = postService.updatePost(post);
        System.out.println(isDone);
    }

}
