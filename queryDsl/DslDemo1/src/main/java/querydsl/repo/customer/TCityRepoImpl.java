package querydsl.repo.customer;

import cn.hutool.system.UserInfo;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.types.dsl.StringTemplate;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import querydsl.entity.QTCity;
import querydsl.entity.QTHotel;
import querydsl.vo.*;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

public class TCityRepoImpl implements TCityRepoCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Tuple> findCityAndHotel(Predicate predicate) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        JPAQuery<Tuple> jpaQuery = queryFactory.select(
                QTCity.tCity,
                QTHotel.tHotel).from(QTCity.tCity)
                .leftJoin(QTHotel.tHotel)
                .on(QTHotel.tHotel.city.longValue().eq(QTCity.tCity.id.longValue()));
        jpaQuery.where(predicate);
        return jpaQuery.fetch();
    }

    // todo 排序写死
    @Override
    public QueryResults<Tuple> findCityAndHotelPage(Predicate predicate, Pageable pageable) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        JPAQuery<Tuple> jpaQuery = queryFactory.select(
                QTCity.tCity.id,
                QTHotel.tHotel).from(QTCity.tCity)
                .leftJoin(QTHotel.tHotel)
                .on(QTHotel.tHotel.city.longValue().eq(QTCity.tCity.id.longValue()))
                .where(predicate)
//                .orderBy(new OrderSpecifier<>(Order.DESC,QTCity.tCity.id))
                .orderBy(QTCity.tCity.id.asc()) // 只能这样写死的
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());
        return jpaQuery.fetchResults();
    }

    // todo 接收排序条件
    // 已完成
    @Override
    public QueryResults<Tuple> findCityAndHotelPage2(Predicate predicate, Pageable pageable) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        JPAQuery<Tuple> jpaQuery = queryFactory.select(
                QTCity.tCity.id,
                QTHotel.tHotel).from(QTCity.tCity)
                .leftJoin(QTHotel.tHotel)
                .on(QTHotel.tHotel.city.longValue().eq(QTCity.tCity.id.longValue()))
                .where(predicate)
                .offset(pageable.getOffset())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        PathBuilder<Entity> entityPath = new PathBuilder<>(Entity.class, "tCity");
        for (Sort.Order order : pageable.getSort()) {
            PathBuilder<Object> path = entityPath.get(order.getProperty());
            jpaQuery.orderBy(new OrderSpecifier(com.querydsl.core.types.Order.valueOf(order.getDirection().name()), path));
        }

        return jpaQuery.fetchResults();
    }

    /**
     * @return
     */
    @Override
    public List<CityHotelVo> findcityHotel() {
        JPAQuery<CityHotelVo> query = new JPAQuery<>(em);
        QTCity c = QTCity.tCity;
        QTHotel h = QTHotel.tHotel;

        JPAQuery<Tuple> on = query.select(
                c.id,
                c.name,
                h.name,
                h.address).from(c).leftJoin(h).on(c.id.eq(h.city));

        QueryResults<Tuple> rts = on.fetchResults();
        List<Tuple> results = rts.getResults();

        return results.stream().map(CityHotelVo::new).collect(Collectors.toList());
    }

    /**
     * 方式一 Bean投影
     * todo 这里暂未调通
     *
     * @return
     */
    @Override
    public List<CityHotelVo> ProjectionsBean() {
        JPAQuery<CityHotelVo> query = new JPAQuery<>(em);
        QTCity c = QTCity.tCity;
        QTHotel h = QTHotel.tHotel;

        List<CityHotelVo> results1 = query.select(Projections.bean(CityHotelVo.class,
                c.id.as("id"),
                c.name.as("cityName"),
                h.name.as("hotelName"),
                h.address.as("address"))).from(c).leftJoin(h).on(c.id.eq(h.city)).fetchResults().getResults();
        return results1;
    }

    /**
     * 方式二 fields 投影
     * todo 调试成功
     * @return
     */
    @Override
    public List<CityHotelVo2> projectionsFields() {
        JPAQuery<CityHotelVo> query = new JPAQuery<>(em);
        QTCity c = QTCity.tCity;
        QTHotel h = QTHotel.tHotel;

        JPAQuery<CityHotelVo2> on = query.select(
                Projections.fields(CityHotelVo2.class,
                        c.id,
                        c.name,
                        h.address))
                .from(c).leftJoin(h).on(c.id.eq(h.city));
        List<CityHotelVo2> resultList = on.createQuery().getResultList();
        return resultList;
    }


    /**
     * todo 成功测试
     * 经测试，使用构造器方式可以映射
     *
     * @return
     */
    @Override
    public List<CityHotelVo2> findcityHotel_31() {
        QTCity c = QTCity.tCity;
        QTHotel h = QTHotel.tHotel;
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        JPAQuery<CityHotelVo2> on = queryFactory.select(
                Projections.constructor(CityHotelVo2.class,
                        c.id,
                        c.name,
                        h.address))
                .from(c).leftJoin(h).on(c.id.eq(h.city));
        List<CityHotelVo2> results = on.fetchResults().getResults();
        return results;
    }

    @Override
    public List<CityHotelVo3> findcityHotel_32() {

        JPAQuery<CityHotelVo> query = new JPAQuery<>(em);
        QTCity c = QTCity.tCity;
        QTHotel h = QTHotel.tHotel;

        List<CityHotelVo3> results1 = query.select(Projections.fields(CityHotelVo3.class,
                c.id.as("id"),
                c.name.as("cityName"),
                h.name.as("hotelName"),
                h.address.as("address"))).from(c).leftJoin(h).on(c.id.eq(h.city)).fetchResults().getResults();
        return results1;
    }

//     * List <UserInfo> result = querydsl.from(user)
//            *     .where(user.valid.eq(true))
//            *     .select(new QUserInfo(user.firstName, user.lastName))
//            *     .fetch();
    @Override
    public List<CityHotelVo21> findcityHotelCons1() {
        JPAQuery<CityHotelVo> query = new JPAQuery<>(em);
        QTCity c = QTCity.tCity;
        QTHotel h = QTHotel.tHotel;

        JPAQuery<CityHotelVo21> select = query.from(c)
                .leftJoin(h).on(c.id.eq(h.city))
                .select(Projections.fields(CityHotelVo21.class,
                c.id,
                c.name,
                h.address));
        List<CityHotelVo21> results = select.createQuery().getResultList();
        return results;




    }

    // select count(tcity0_.map) as col_0_0_ from t_city tcity0_
    @Override
    public long count1() {
        JPAQuery<CityHotelVo> query = new JPAQuery<>(em);
        QTCity c = QTCity.tCity;
        Long one = query.select(
                c.map.count()
        ).from(c).fetchOne();
        return one;
    }

    // select count(tcity0_.map) as col_0_0_ from t_city tcity0_ group by tcity0_.map
    @Override
    public List<Long> count2() {
        JPAQuery<CityHotelVo> query = new JPAQuery<>(em);
        QTCity c = QTCity.tCity;
        return query.select(
                c.map.count()
        ).from(c).groupBy(c.map).fetch();
    }

    // select tcity0_.map as col_0_0_, count(tcity0_.map) as col_1_0_ from t_city tcity0_ group by tcity0_.map
    @Override
    public List<Tuple> count3() {
        JPAQuery<CityHotelVo> query = new JPAQuery<>(em);
        QTCity c = QTCity.tCity;
        return query.select(
                c.map,
                c.map.count()
        ).from(c).groupBy(c.map).fetch();
    }





//------------------------------- 下面是测试 引入 Mysql 内置函数
    @Override
    public QueryResults<Tuple> func1(Predicate predicate, Pageable pageable) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        JPAQuery<Tuple> jpaQuery = queryFactory.select(
                QTCity.tCity.id,
                QTHotel.tHotel).from(QTCity.tCity)
                .leftJoin(QTHotel.tHotel)
                .on(QTHotel.tHotel.city.longValue().eq(QTCity.tCity.id.longValue()))
                .where(predicate)
                .offset(pageable.getOffset())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        PathBuilder<Entity> entityPath = new PathBuilder<>(Entity.class, "tCity");
        for (Sort.Order order : pageable.getSort()) {
            PathBuilder<Object> path = entityPath.get(order.getProperty());
            jpaQuery.orderBy(new OrderSpecifier(com.querydsl.core.types.Order.valueOf(order.getDirection().name()), path));
        }

        return jpaQuery.fetchResults();
    }

    // todo 待调试
    @Override
    public List<CityHotelVo4> dateFormat(CityHotelVo vo) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QTCity c = QTCity.tCity;
        QTHotel h = QTHotel.tHotel;

        StringTemplate dateFormat = Expressions.stringTemplate("DATE_FORMAT({0},'%Y-%m-%d')", c.cityDateTime);

        // 拼接内置函数
        JPAQuery<CityHotelVo4> on = queryFactory.select(
                Projections.constructor(CityHotelVo4.class,
                        // todo 待调试
                        c.id.as("id"),
                        c.name.as("cityName"),
                        h.name.as("hotelName"),
                        h.address,
                        dateFormat.as("formatTime")
                )
        )
                .from(c).leftJoin(h).on(c.id.eq(h.city));
        List<CityHotelVo4> results = on.fetchResults().getResults();
        return results;
    }
//------------------------------- 以上是测试 引入 Mysql 内置函数
}
