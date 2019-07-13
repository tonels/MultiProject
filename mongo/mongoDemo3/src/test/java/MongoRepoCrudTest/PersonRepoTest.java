package MongoRepoCrudTest;

import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import demo3.MongoApp3;
import demo3.model.Article;
import demo3.repo.ArticleRepo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MongoApp3.class)
public class PersonRepoTest{

    // mongoRepository 类似JPA，封装了很多常用的方法，就不多多介绍了

    @Autowired
    private ArticleRepo articleRepo;

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
    public void add1() throws Exception{
        Article article = list.get(0);
        articleRepo.save(article);
        Assert.assertTrue("1".equals("3"));


    }
    @Test
    public void fin(){
        System.out.println("ss");


    }

}