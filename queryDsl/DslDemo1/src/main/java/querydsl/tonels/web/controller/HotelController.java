package querydsl.tonels.web.controller;

import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;
import querydsl.entity.QTCity;
import querydsl.entity.QTHotel;
import querydsl.entity.THotel;
import querydsl.tonels.repo.THotelRepo;
import querydsl.tonels.repo.customer.HotelRepoCust;
import querydsl.vo.CityHotelVo;
import utils.PageBean;
import utils.ResultBean;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/hotel1")
public class HotelController {

    @Resource
    private THotelRepo tHotelRepo;
    @Resource
    private HotelRepoCust custRepo;

    /**
     * 单表动态条件
     *
     * @param tHotel
     * @return select thotel0_.id as id1_1_, thotel0_.address as address2_1_, thotel0_.city as city3_1_, thotel0_.name as name4_1_ from t_hotel thotel0_ where thotel0_.id=1 and thotel0_.name=? and thotel0_.address=? order by thotel0_.id asc limit ?
     */
    @GetMapping("s1")
    public PageBean s1(THotel tHotel) {
        Page<THotel> all = tHotelRepo.findAll(new Specification<THotel>() {
            @Override
            public Predicate toPredicate(Root<THotel> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = Lists.newArrayList();

                if (!StrUtil.isBlankIfStr(tHotel.getId())) {
                    predicates.add(cb.equal(root.get("id"), tHotel.getId()));
                }
                if (!StrUtil.isBlankIfStr(tHotel.getName())) {
                    predicates.add(cb.equal(root.get("name"), tHotel.getName()));
                }
                if (!StrUtil.isBlankIfStr(tHotel.getCity())) {
                    predicates.add(cb.equal(root.get("city"), tHotel.getCity()));
                }
                if (!StrUtil.isBlankIfStr(tHotel.getAddress())) {
                    predicates.add(cb.equal(root.get("address"), tHotel.getAddress()));
                }
                return cb.and(predicates.toArray(new Predicate[0]));
            }
        }, PageRequest.of(0, 20, Sort.by(Sort.Order.asc("id"))));
        return PageBean.ok(all.getTotalPages(), all.getTotalElements(), all.getContent());
    }

    /**
     * 使用自定义存储库，没有继承通过@Resource注入方式
     *
     * @return ResultBean
     */
    @GetMapping("s2")
    public ResultBean s2() {

        List<Tuple> tuples = custRepo.findcityHotel();
        List<CityHotelVo> cityHotelVos = CityController.trans1(tuples);
        return ResultBean.ok(cityHotelVos);
    }

    /**
     * vo 入参
     *
     * @param vo
     * @return
     */
    @GetMapping("s3")
    public ResultBean s3(CityHotelVo vo) {

        BooleanBuilder builder = CityController.builder1(vo);

        PageRequest pageRequest = PageRequest.of(0, 5, Sort.by(Sort.Order.asc("id")));
        QueryResults<Tuple> tuples = tHotelRepo.findCityAndHotelPage(builder, pageRequest);
        List<Tuple> results = tuples.getResults();
        results.forEach(System.out::println);
        return ResultBean.ok();
    }


    @GetMapping("s4")
    public ResultBean s4() {


        List<Tuple> tuples = custRepo.findcityHotel();

        QTCity c = QTCity.tCity;
        QTHotel h = QTHotel.tHotel;
        List<CityHotelVo> collect = tuples.stream().map(e -> {
            CityHotelVo vo = new CityHotelVo();
            vo.setId(e.get(c.id))
                    .setCityName(e.get(c.name))
                    .setHotelName(e.get(h.name))
                    .setAddress(e.get(h.address));
            return vo;
        }).collect(Collectors.toList());
        return ResultBean.ok(collect);
    }


    @PostMapping("/post")
    public ResultBean p1(@RequestBody CityHotelVo vo) {

        return ResultBean.ok(vo);

    }


}
