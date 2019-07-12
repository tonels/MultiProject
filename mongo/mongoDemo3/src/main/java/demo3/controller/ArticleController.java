package demo3.controller;

import demo3.model.Article;
import demo3.repo.ArticleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.*;
import utils.ResultBean;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/mongo3")
public class ArticleController {

    @Autowired
    private ArticleRepo articleRepo;
    @Resource
    private MongoTemplate mongoTemplate;


    @PostMapping("/add")
    public ResultBean m1(){
        Article a1 = new Article().setAuthor("").setTitle("ss").setUrl("12.12.12.12").setVisitCount(100L);
        return ResultBean.ok(articleRepo.save(a1));
    }

    @PostMapping("/add11")
    public ResultBean m11(@RequestBody Article article){
        return ResultBean.ok(articleRepo.save(article));
    }

    @PostMapping("/add12")
    public ResultBean m12(@RequestBody Article article){
        return ResultBean.ok(articleRepo.insert(article));
    }
    @PostMapping("/add13")
    public ResultBean m13(@RequestBody Article article){
        return ResultBean.ok(articleRepo.save(article));
    }
    @PostMapping("/add14")
    public ResultBean m14(@RequestBody Article article){
        return ResultBean.ok(articleRepo.save(article));
    }
    @PostMapping("/add15")
    public ResultBean m15(@RequestBody Article article){
        return ResultBean.ok(articleRepo.save(article));
    }


    @GetMapping("/findAll")
    public ResultBean m2(){
        List<Article> all = articleRepo.findAll();
        return ResultBean.ok(all);
    }

    @GetMapping("/findOne")
    public ResultBean m3(String id){
        Optional<Article> byId = articleRepo.findById(id);
        return ResultBean.ok(byId.isPresent()?byId.get():null);
    }

    // 这里会返回，但如果返回类型定义的不对，会出现 Unique 的错误
    @GetMapping("/findOneByTitle")
    public ResultBean m4(String title){
        Optional<Article> byTitle = articleRepo.findByTitle(title);
        return ResultBean.ok(byTitle.isPresent()?byTitle.get():null);
    }

    @GetMapping("/findListByAuthor")
    public ResultBean m5(String author){
        List<Article> byTitle = articleRepo.findByAuthor(author);
        return ResultBean.ok(byTitle);
    }

    @GetMapping("/findByQuery")
    public ResultBean m6(String author){
        List<Article> byTitle = articleRepo.find1(author);
        return ResultBean.ok(byTitle);
    }

}
