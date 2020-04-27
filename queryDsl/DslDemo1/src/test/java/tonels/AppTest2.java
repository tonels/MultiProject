package tonels;

import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringTemplate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import querydsl.QueryDslRun;
import querydsl.entity.QTCity;
import querydsl.entity.QTHotel;
import querydsl.entity.TCity;
import querydsl.entity.THotel;
import querydsl.repo.TCityRepo;
import querydsl.repo.THotelRepo;
import querydsl.vo.CityHotelVo;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = QueryDslRun.class)
public class AppTest2 {
    @Autowired
    private TCityRepo tCityRepository;
    @Resource
    private THotelRepo tHotelRepo;
    @PersistenceContext
    private EntityManager em;


    private List<TCity> citys;
    private List<THotel> hotels;

    //动态条件
    QTCity qtCity = QTCity.tCity;

    QTHotel qtHotel = QTHotel.tHotel;

    //单表操作系列 start

    /**
     * 非动态查询建议使用Query注解
     */


    // 先增加几条条测试数据
    @Test
    public void saveCity() {
        List<TCity> tCities = tCityRepository.saveAll(citys);
    }

    @Test
    public void saveHotel() {
        tHotelRepo.saveAll(hotels);
    }

    @Test // 单表操作
    // select . from t_city tcity0_ where cast(tcity0_.id as signed)<? and (tcity0_.name like ? escape '!') order by tcity0_.id asc limit ?

    public void findDynamic() {
        Predicate predicate = qtCity.id.longValue().lt(10)
                .and(qtCity.name.like("%a%"));
        //分页排序
        PageRequest page = PageRequest.of(0, 10, Sort.Direction.ASC, "id");
        //查找结果
        Page<TCity> tCityPage = tCityRepository.findAll(predicate, page);
        List<TCity> content = tCityPage.getContent();
        System.out.println(content);
    }

    //多表操作
    //针对返回的是Object[]提供了一个很好地解决方案
    // select c.*,h.* from t_city c left outer join t_hotel h on (cast(h.city as signed)=cast(c.id as signed)) where c.name like ? escape '!'
    @Test
    public void findByLeftJoin() {
        QTCity qtCity = QTCity.tCity;
        QTHotel qtHotel = QTHotel.tHotel;
        Predicate predicate = qtCity.name.like("%h%");
        List<Tuple> result = tCityRepository.findCityAndHotel(predicate);
        for (Tuple row : result) {
            System.out.println("qtCity:" + row.get(qtCity));
            System.out.println("qtHotel:" + row.get(qtHotel));
            System.out.println("--------------------");
        }
        System.out.println(result);
    }

    @Test
    // select . from t_city c left outer join t_hotel h on (cast(h.city as signed)=cast(c.id as signed))  where tcity0_.name like ? escape '!' limit ?
    public void findByLeftJoinPage() {
        QTCity qtCity = QTCity.tCity;
        QTHotel qtHotel = QTHotel.tHotel;
        Predicate predicate = qtCity.name.like("%h%");
        PageRequest pageRequest = PageRequest.of(0, 10);
        QueryResults<Tuple> result = tCityRepository.findCityAndHotelPage(predicate, pageRequest);
        for (Tuple row : result.getResults()) {
            System.out.println("qtCity:" + row.get(qtCity));
            System.out.println("qtHotel:" + row.get(qtHotel));
            System.out.println("--------------------");
        }
        System.out.println(result.getResults());
    }

    // 自定义返回 ,测试通过，是可以自定义返回的
    @Test
    // select c.id , c.name , h.name , h.address from t_city c left outer join t_hotel t on (c.id=t.city)
    public void findcityHotel() {
        List<CityHotelVo> cityHotelVos = tCityRepository.findcityHotel();
        System.out.println(cityHotelVos);
    }

    // date_format()测试
    @Test
    // select tcity0_.country as col_0_0_, tcity0_.city_date_time as col_1_0_ from t_city tcity0_
    public void rowNumber() {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        final List<Tuple> list = queryFactory.select(
                qtCity.country,
                qtCity.cityDateTime
        ).from(qtCity).fetchResults().getResults();
        System.out.println(list.toString());
    }

    @Test
    // date_formate
    // select tcity0_.country as col_0_0_, date_format(tcity0_.city_date_time, '%Y-%m-%d') as col_1_0_ from t_city tcity0_
    public void date2() {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        StringTemplate dateExpr = Expressions.stringTemplate("DATE_FORMAT({0},'%Y-%m-%d')",
                qtCity.cityDateTime);

        final List<Tuple> list = queryFactory.select(
                qtCity.country,
                dateExpr
        ).from(qtCity).fetchResults().getResults();
        System.out.println(list.toString());
    }

    @Test
    // round,四舍五入
//    select tcity0_.country as col_0_0_, round(tcity0_.id, 1) as col_1_0_ from t_city tcity0_
    public void round() {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        StringTemplate Expr = Expressions.stringTemplate("ROUND({0},1)",
                qtCity.id);

        final List<Tuple> list = queryFactory.select(
                qtCity.country,
                Expr
        ).from(qtCity).fetchResults().getResults();
        System.out.println(list.toString());
    }

    @Test
    // round,四舍五入
//    select tcity0_.country as col_0_0_, round(avg(tcity0_.id), 1) as col_1_0_ from t_city tcity0_ group by tcity0_.country
    public void round2() {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        StringTemplate Expr = Expressions.stringTemplate("ROUND({0},1)",
                qtCity.id.avg());

        final List<Tuple> list = queryFactory.select(
                qtCity.country,
                Expr
        ).from(qtCity).groupBy(qtCity.country).fetchResults().getResults();
        System.out.println(list.toString());
    }


}
