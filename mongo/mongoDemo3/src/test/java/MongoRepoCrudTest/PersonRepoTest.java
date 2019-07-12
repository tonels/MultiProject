package MongoRepoCrudTest;

import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import demo3.model.Article;
import demo3.repo.PersonRepo;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;
import java.util.Optional;

public class PersonRepoTest {

    @Autowired
    private PersonRepo personRepo;

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
    public void add1(){
        Optional<PersonRepo> personRepo = Optional.ofNullable(this.personRepo);


    }
    @Test
    public void add2(){
        System.out.println("ss");


    }

}