package querydsl.controller;

import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
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
import querydsl.vo.CityHotelVo;
import utils.PageBean;
import utils.ResultBean;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@RestController
@RequestMapping("/dsl1")
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

        list = Lists.newArrayList(c1,c2,c3,c4,c5);
    }

    /**
     * 单表分页操作
     * @return PageBean
     */
    @GetMapping("/s1")
    public PageBean<TCity> s1(){

        QTCity tCity = QTCity.tCity;
        Predicate predicate = tCity.id.longValue().lt(10)
                .and(tCity.name.like("%a%"));
        PageRequest pagerequest = PageRequest.of(0, 10, Sort.Direction.ASC, "id");
        Page<TCity> all = tCityRepo.findAll(predicate,pagerequest);

        return PageBean.ok(all.getTotalPages(),all.getTotalElements(),all.getContent());
    }

    /**
     * list查询
     * @return ResultBean
     */
    @GetMapping("/s2")
    public ResultBean s2(){

        QTCity tCity = QTCity.tCity;
        Predicate predicate = tCity.id.longValue().lt(10).and(tCity.name.like("%a%"));

        Iterable<TCity> all1 = tCityRepo.findAll(predicate);
        return ResultBean.ok(all1);
    }

    /**
     * List,联表
     * @return ResultBean
     */
    @GetMapping("/s3")
    public ResultBean s3(){
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
     * @return PageBean
     */
    @GetMapping("/s4")
    public PageBean s4(CityHotelVo vo){
        JPAQuery<CityHotelVo> query = new JPAQuery<>(em);
        QTCity c = QTCity.tCity;
        QTHotel h = QTHotel.tHotel;

        BooleanBuilder builder = this.builder1(vo);
        PageRequest pageRequest = PageRequest.of(0, 5, Sort.by(Sort.Order.asc("id")));
        JPAQuery<Tuple> on = query.select(
                c.id,
                c.name,
                h.name,
                h.address).from(c).leftJoin(h).on(c.id.eq(h.city)).where(builder);

        QueryResults<Tuple> rts = on.fetchResults();
        List<Tuple> results = rts.getResults();

        List<CityHotelVo> cityHotelVos = this.trans1(results);

        Page<CityHotelVo> page = new PageImpl<>(cityHotelVos, pageRequest, cityHotelVos.size());
        return PageBean.ok(page.getTotalPages(),page.getTotalElements(),page.getContent());
    }

    private BooleanBuilder builder1(CityHotelVo vo){

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

    private List<CityHotelVo> trans1(List<Tuple> tuple){

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
