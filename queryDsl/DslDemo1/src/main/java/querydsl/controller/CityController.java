package querydsl.controller;

import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import querydsl.entity.QTCity;
import querydsl.entity.QTHotel;
import querydsl.entity.TCity;
import querydsl.repo.TCityRepo;
import querydsl.vo.*;
import utils.PageBean;
import utils.ResultBean;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/city1")
public class CityController {

    @Resource
    private TCityRepo tCityRepo;
    @PersistenceContext
    private EntityManager em;

    private static List<TCity> list;

    static {
        TCity c1 = new TCity().setId(1).setName("tokyo").setState("NY").setCountry("japan").setMap("m1");
        TCity c2 = new TCity().setId(2).setName("shanghai").setState("KL").setCountry("chinese").setMap("m2");
        TCity c3 = new TCity().setId(3).setName("shouer").setState("AI").setCountry("korean").setMap("m3");
        TCity c4 = new TCity().setId(4).setName("dld").setState("CT").setCountry("canada").setMap("m4");
        TCity c5 = new TCity().setId(5).setName("london").setState("CE").setCountry("english").setMap("m5");

        list = Lists.newArrayList(c1, c2, c3, c4, c5);
    }

    /**
     * 单表,模糊查询
     *
     * @return PageBean
     */
    @GetMapping("/s1")
    public ResultBean s1(String name) {

        QTCity tCity = QTCity.tCity;

        Predicate predicate = tCity.id.longValue().lt(10)
                .and(tCity.name.like("%" + name + "%"));
        Iterable<TCity> all1 = tCityRepo.findAll(predicate);
        return ResultBean.ok(all1);
    }

    /**
     * list查询
     *
     * @return ResultBean
     */
    @GetMapping("/s2")
    public PageBean s2() {

        QTCity tCity = QTCity.tCity;
        Predicate predicate = tCity.id.longValue().lt(10);

        PageRequest pageRequest = PageRequest.of(0, 5, Sort.by(Sort.Order.asc("id")));

        Page<TCity> page = tCityRepo.findAll(predicate, pageRequest);

        return PageBean.ok(page.getTotalPages(), page.getTotalElements(), page.getContent());
    }


    /**
     * List,联表
     *
     * @return ResultBean
     */
    @GetMapping("/s3")
    public ResultBean s3() {
        JPAQuery<CityHotelVo> query = new JPAQuery<>(em);
        QTCity c = QTCity.tCity;
        QTHotel h = QTHotel.tHotel;

        JPAQuery<Tuple> on = query.select(
                c.id,
                c.name,
                h.name,
                h.address).from(c).leftJoin(h).on(c.id.eq(h.city));
        /** 这是debug，看到的 ss
         * select tCity.id, tCity.name as cityName, tHotel.name as hotelName, tHotel.address
         * from TCity tCity
         *   left join THotel tHotel with tCity.id = tHotel.city
         */

        QueryResults<Tuple> rts = on.fetchResults();
        List<Tuple> results = rts.getResults();

        List<CityHotelVo> cityHotelVos = this.trans1(results);

        return ResultBean.ok(cityHotelVos);
    }

    /**
     * List,分页,动态条件
     *
     * @return PageBean
     */
    @GetMapping("/s4")
    public PageBean s4(CityHotelVo vo) {
        JPAQuery<CityHotelVo> query = new JPAQuery<>(em);
        QTCity c = QTCity.tCity;
        QTHotel h = QTHotel.tHotel;

        BooleanBuilder builder = this.builder1(vo);
        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Order.asc("id")));

        List<CityHotelVo> collect = query.select(
                c.id,
                c.name,
                h.name,
                h.address).from(c).leftJoin(h).on(c.id.eq(h.city))
                .where(builder)
                .orderBy(new OrderSpecifier<>(Order.DESC, c.id))
                .offset(pageRequest.getOffset()).limit(pageRequest.getPageSize()).fetchResults().getResults()
                .stream()
                .map(CityHotelVo::new)
                .collect(Collectors.toList());

        PageImpl<CityHotelVo> cityHotelVos1 = new PageImpl<>(collect, pageRequest, collect.size());
        return PageBean.ok(cityHotelVos1.getTotalPages(), cityHotelVos1.getTotalElements(), cityHotelVos1.getContent());
    }

    /**
     * 分页实现
     *
     * @param vo 前端传参
     * @return
     */
    @GetMapping("/s5")
    public ResultBean s5(CityHotelVo vo) {

        BooleanBuilder builder = this.builder1(vo);
        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Order.asc("id")));

        QueryResults<Tuple> results = tCityRepo.findCityAndHotelPage(builder, pageRequest);
        List<Tuple> list = results.getResults();

        // 使用无参构造处理
//        List<Vo1> list1 = Lists.newArrayList();
//        list.forEach(e -> {
//            Vo1 vo1 = new Vo1();
//            vo1.setId(e.get(QTCity.tCity.id));
//            vo1.setTHotel(e.get(QTHotel.tHotel));
//            list1.add(vo1);
//        });

        // 使用有参构造处理
        List<Vo1> collect = list.stream().map(Vo1::new).collect(Collectors.toList());
//        List<Vo1> collect = list.stream().map(e -> new Vo1(e)).collect(Collectors.toList());

        return ResultBean.ok(collect);
    }

    /**
     * list
     *
     * @return
     */
    @GetMapping("/s6")
    public ResultBean s6() {
        List<CityHotelVo> vos = tCityRepo.findcityHotel();
        return ResultBean.ok(vos);
    }

    /**
     * todo
     * 此处 tuple -> vo 自动映射，无需手动映射
     * 暂时没有调试成功
     * @return
     */
    @GetMapping("/s6-1")
    public ResultBean s6_1() {
        List<CityHotelVo> cityHotelVos = tCityRepo.findcityHotel_2();
        return ResultBean.ok(cityHotelVos);
    }
    @GetMapping("/s6-2")
    public ResultBean s6_2() {
        List<CityHotelVo2> cityHotelVos = tCityRepo.findcityHotel_3();
        return ResultBean.ok(cityHotelVos);
    }
    @GetMapping("/s6-3")
    public ResultBean s6_3() {
        List<CityHotelVo2> cityHotelVos = tCityRepo.findcityHotel_31();
        return ResultBean.ok(cityHotelVos);
    }

    @GetMapping("/s7")
    public ResultBean s7(CityHotelVo vo) {
        BooleanBuilder builder = this.builder1(vo);
        List<Tuple> list = tCityRepo.findCityAndHotel(builder);
        List<Vo2> collect = list.stream().map(Vo2::new).collect(Collectors.toList());
        return ResultBean.ok(collect);
    }

    // =========================================== 聚 合 函 数 函 数 的 使 用 ==============================

    @GetMapping("/s8")
    public ResultBean s8() {
        long l = tCityRepo.count1();
        return ResultBean.ok(l);
    }

    @GetMapping("/s9")
    public ResultBean s9() {
        List<Long> count2 = tCityRepo.count2();
        return ResultBean.ok(count2);
    }

    public static BooleanBuilder builder1(CityHotelVo vo) {

        QTCity c = QTCity.tCity;
        QTHotel h = QTHotel.tHotel;
        BooleanBuilder boob = new BooleanBuilder();

        if (!StrUtil.isBlankIfStr(vo.getId())) {
            boob.and(c.id.eq(vo.getId()));
        }
        if (!StrUtil.isBlankIfStr(vo.getCityName())) {
            boob.and(c.name.eq(vo.getCityName()));
        }
        if (!StrUtil.isBlankIfStr(vo.getHotelName())) {
            boob.and(h.name.eq(vo.getHotelName()));
        }
        if (!StrUtil.isBlankIfStr(vo.getAddress())) {
            boob.and(h.address.eq(vo.getAddress()));
        }
        return boob;
    }

    @GetMapping("/s10")
    public ResultBean getS10() {
        List<Tuple> list = tCityRepo.count3();
        List<Vo3> collect = list.stream().map(Vo3::new).collect(Collectors.toList());
        return ResultBean.ok(collect);
    }


    public static List<CityHotelVo> trans1(List<Tuple> tuple) {

        QTCity c = QTCity.tCity;
        QTHotel h = QTHotel.tHotel;

        List<CityHotelVo> list1 = Lists.newArrayList();
        for (Tuple tu : tuple) {
            CityHotelVo vo = new CityHotelVo();
            vo.setId(tu.get(c.id));
            vo.setCityName(tu.get(c.name));
            vo.setHotelName(tu.get(h.name));
            vo.setAddress(tu.get(h.address));
            list1.add(vo);
        }
        return list1;
    }


}
