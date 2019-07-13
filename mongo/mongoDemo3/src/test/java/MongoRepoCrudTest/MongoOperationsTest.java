package MongoRepoCrudTest;

import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import demo3.MongoApp3;
import demo3.model.Article;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = MongoApp3.class)
public class MongoOperationsTest {

    @Autowired
    private MongoOperations mongoOperations;

    // 这里先初始化一个List用于构建测试数据
    private List<Article> list;
    @Before
    public void setup() {
        Article article1 = new Article().setAuthor("author1").setTitle("title1").setUrl("www1").setVisitCount(500L).setTags(StrUtil.splitTrim("a,v,b", ","));
        Article article2 = new Article().setAuthor("author2").setTitle("title2").setUrl("www2").setVisitCount(500L).setTags(StrUtil.splitTrim("m,g,s", ","));
        Article article3 = new Article().setAuthor("author3").setTitle("title3").setUrl("www3").setVisitCount(500L).setTags(StrUtil.splitTrim("p,h,d", ","));
        list = Lists.newArrayList(article1,article2,article3);
    }

    @Test
    public void save1(){
        mongoOperations.save(list.get(2));
    }

    @Test
    public void insert1(){
        mongoOperations.insert(list.get(0));
    }

    @Test
    public void upsert1(){
        mongoOperations.upsert(new Query(Criteria.where("id").is("1")),
                new Update().set("title", "titt啊11"), Article.class);
    }

    @Test
    public void findAll1(){
        List<Article> all = mongoOperations.findAll(Article.class);
        System.out.println(all);
    }

    @Test
    public void findone1(){
        Article byId = mongoOperations.findById("1", Article.class);
        System.out.println(byId);
    }

    @Test
    public void findlist1(){
        List<Article> articles = mongoOperations.find(new Query(Criteria.where("title").is("title1")), Article.class);
        articles.forEach(System.out::println);
    }
}



























