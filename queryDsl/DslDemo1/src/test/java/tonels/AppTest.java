//package tonels;
//
//import com.google.common.collect.Lists;
//import com.querydsl.core.QueryResults;
//import com.querydsl.core.Tuple;
//import com.querydsl.core.types.Predicate;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Sort;
//import org.springframework.test.context.junit4.SpringRunner;
//import querydsl.QueryDslRun;
//import querydsl.entity.QTCity;
//import querydsl.entity.QTHotel;
//import querydsl.entity.TCity;
//import querydsl.entity.THotel;
//import querydsl.repo.TCityRepo;
//import querydsl.repo.THotelRepo;
//import querydsl.vo.CityHotelVo;
//
//import javax.annotation.Resource;
//import java.util.List;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = QueryDslRun.class)
//public class AppTest {
//    @Autowired
//    private TCityRepo tCityRepository;
//    @Resource
//    private THotelRepo tHotelRepo;
//
//
//    private List<TCity> citys;
//    private List<THotel> hotels;
//
//    //动态条件
//    QTCity qtCity = QTCity.tCity;
//
//    QTHotel qtHotel = QTHotel.tHotel;
//
//    //单表操作系列 start
//    /**
//     * 非动态查询建议使用Query注解
//     */
//
//
//    @Before
//    public void init(){
//        TCity c1 = new TCity().setId(1).setName("tokyo").setState("NY").setCountry("japan").setMap("m1");
//        TCity c2 = new TCity().setId(2).setName("shanghai").setState("KL").setCountry("chinese").setMap("m2");
//        TCity c3 = new TCity().setId(3).setName("shouer").setState("AI").setCountry("korean").setMap("m3");
//        TCity c4 = new TCity().setId(4).setName("dld").setState("CT").setCountry("canada").setMap("m4");
//        TCity c5 = new TCity().setId(5).setName("london").setState("CE").setCountry("english").setMap("m5");
//
//        citys = Lists.newArrayList(c1, c2, c3, c4, c5);
//
//        THotel t1 = new THotel().setId(1).setName("星月").setCity(1).setAddress("街1");
//        THotel t2 = new THotel().setId(2).setName("七天").setCity(3).setAddress("街2");
//        THotel t3 = new THotel().setId(3).setName("如家").setCity(5).setAddress("街3");
//        THotel t4 = new THotel().setId(4).setName("庐舍").setCity(2).setAddress("街4");
//        THotel t5 = new THotel().setId(5).setName("旅社").setCity(3).setAddress("街5");
//        THotel t6 = new THotel().setId(6).setName("宾馆").setCity(5).setAddress("街6");
//
//        hotels = Lists.newArrayList(t1, t2, t3, t4, t5,t6);
//    }
//
//
//
//    // 先增加几条条测试数据
//    @Test
//    public void saveCity(){
//        List<TCity> tCities = tCityRepository.saveAll(citys);
//    }
//    @Test
//    public void saveHotel(){
//        tHotelRepo.saveAll(hotels);
//    }
//
//
//    @Test // 单表操作
//    // select . from t_city tcity0_ where cast(tcity0_.id as signed)<? and (tcity0_.name like ? escape '!') order by tcity0_.id asc limit ?
//
//    public void findDynamic(){
//        Predicate predicate = qtCity.id.longValue().lt(10)
//                .and(qtCity.name.like("%a%"));
//        //分页排序
//        PageRequest page = PageRequest.of(0, 10, Sort.Direction.ASC, "id");
//        //查找结果
//        Page<TCity> tCityPage = tCityRepository.findAll(predicate,page);
//        List<TCity> content = tCityPage.getContent();
//        System.out.println(content);
//    }
//
//    //多表操作
//    //针对返回的是Object[]提供了一个很好地解决方案
//    // select c.*,h.* from t_city c left outer join t_hotel h on (cast(h.city as signed)=cast(c.id as signed)) where c.name like ? escape '!'
//    @Test
//    public void findByLeftJoin(){
//        QTCity qtCity = QTCity.tCity;
//        QTHotel qtHotel = QTHotel.tHotel;
//        Predicate predicate = qtCity.name.like("%h%");
//        List<Tuple> result = tCityRepository.findCityAndHotel(predicate);
//        for (Tuple row : result) {
//            System.out.println("qtCity:"+row.get(qtCity));
//            System.out.println("qtHotel:"+row.get(qtHotel));
//            System.out.println("--------------------");
//        }
//        System.out.println(result);
//    }
//    @Test
//    // select . from t_city c left outer join t_hotel h on (cast(h.city as signed)=cast(c.id as signed))  where tcity0_.name like ? escape '!' limit ?
//    public void findByLeftJoinPage(){
//        QTCity qtCity = QTCity.tCity;
//        QTHotel qtHotel = QTHotel.tHotel;
//        Predicate predicate = qtCity.name.like("%h%");
//        PageRequest pageRequest = PageRequest.of(0,10);
//        QueryResults<Tuple> result = tCityRepository.findCityAndHotelPage(predicate,pageRequest);
//        for (Tuple row : result.getResults()) {
//            System.out.println("qtCity:"+row.get(qtCity));
//            System.out.println("qtHotel:"+row.get(qtHotel));
//            System.out.println("--------------------");
//        }
//        System.out.println(result.getResults());
//    }
//
//// 自定义返回 ,测试通过，是可以自定义返回的
//    @Test
//    // select c.id , c.name , h.name , h.address from t_city c left outer join t_hotel t on (c.id=t.city)
//    public void findcityHotel(){
//        List<CityHotelVo> cityHotelVos = tCityRepository.findcityHotel();
//        System.out.println(cityHotelVos);
//
//    }
//
//}
