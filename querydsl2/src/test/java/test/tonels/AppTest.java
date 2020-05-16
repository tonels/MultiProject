package test.tonels;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.*;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.types.dsl.SimpleTemplate;
import com.querydsl.core.types.dsl.StringTemplate;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sun.xml.internal.ws.developer.Serialization;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import test.tonels.vo.CustConstruct;
import tonels.Qsl2;
import tonels.entity.CustomersEntity;
import tonels.entity.QCustomersEntity;
import tonels.entity.QOrderdetailsEntity;
import tonels.entity.QOrdersEntity;
import tonels.repo.TCityRepo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Qsl2.class)
public class AppTest {


    @Autowired
    private TCityRepo tCityRepository;
    @PersistenceContext
    private EntityManager em;

//    private static final QCustomersEntity cm = QCustomersEntity.customersEntity;
//    private static final QOrdersEntity od = QOrdersEntity.ordersEntity;
//    private static final QOrderdetailsEntity ot = QOrderdetailsEntity.orderdetailsEntity;

    private static final QCustomersEntity cm = new QCustomersEntity("cm");
    private static final QOrdersEntity od = new QOrdersEntity("od");
    private static final QOrderdetailsEntity ot = new QOrderdetailsEntity("ot");

    @Test
    public void t1() {
        tCityRepository.groupConcat1();

    }

    // 简单条件拼接
    @Test
    public void simple() {
        assertNotNull("customersEntity.city = ds", cm.city.eq("ds"));
        assertNotNull("containsIc(customersEntity.city,ds)", cm.city.containsIgnoreCase("ds"));
        assertNotNull("customersEntity.city + ds", cm.city.concat("ds"));
        assertNotNull("endsWith(customersEntity.city,ds)", cm.city.endsWith("ds"));
        assertNotNull("locate(ds,customersEntity.city)", cm.city.locate("ds"));
        assertNotNull("coalesce(customersEntity.city, ds)", cm.city.coalesce("ds"));
        assertNotNull("upper(customersEntity.city) as ss = op", cm.city.upper().as("ss").eq("op"));
    }

    // ---------------------------- 基本查询 -------------------------------
    // 这里需要额外的配置
    @Test
    public void select1() {
        JPAQueryFactory jqf = new JPAQueryFactory(em);
        SimpleTemplate<String> simpleTemplate = Expressions.simpleTemplate(String.class, "group_concat({0})", cm.city);
        System.out.println(jqf.select(
                cm.country,
                cm.contactFirstName,
                simpleTemplate
        ).from(cm).groupBy(cm.country).fetch().size());
    }

    // DateFormat 不需要额外配置即可完成

    /**
     * select ordersenti0_.customerNumber                     as col_0_0_,
     * ordersenti0_.comments                           as col_1_0_,
     * date_format(ordersenti0_.orderDate, '%Y-%m-%d') as col_2_0_
     * from orders ordersenti0_
     */
    @Test
    public void select2() {
        JPAQueryFactory jqf = new JPAQueryFactory(em);

        StringTemplate dateExpr = Expressions.stringTemplate("DATE_FORMAT({0},'%Y-%m-%d')", od.orderDate);
        final JPAQuery<Tuple> from = jqf.select(
                od.customerNumber,
                od.comments,
                dateExpr
        ).from(od);
        final List<Tuple> fetch = from.fetch();
        final JPAQuery<Tuple> tupleJPAQuery = from.fetchAll();

        System.out.println(fetch);
        System.out.println(tupleJPAQuery.fetch());


    }

    /**
     * select ordersenti0_.customerNumber      as col_0_0_,
     * sum(ordersenti0_.customerNumber) as col_1_0_,
     * ordersenti0_.comments            as col_2_0_
     * from orders ordersenti0_
     * group by date_format(ordersenti0_.orderDate, '%Y-%m-%d')
     */
    @Test
    public void select2_1() {
        JPAQueryFactory jqf = new JPAQueryFactory(em);

        StringTemplate dateExpr = Expressions.stringTemplate("DATE_FORMAT({0},'%Y-%m-%d')", od.orderDate);
        System.out.println(jqf.select(
                od.customerNumber,
                od.customerNumber.sum(),
                od.comments
        ).from(od).groupBy(dateExpr).fetch().size());
    }

    /**
     * 构造器
     * new CustConstruct(cm.contactLastName, cm.customerNumber)
     * <p>
     * select customerse0_.contactLastName as col_0_0_, customerse0_.customerNumber as col_1_0_ from customers customerse0_
     * test.tonels.vo.CustConstruct@6f8667bb
     * test.tonels.vo.CustConstruct@100d0218
     * test.tonels.vo.CustConstruct@2774dcf4
     */
    @Test
    public void select3() {
        ConstructorExpression<CustConstruct> c =
                Projections.constructor(
                        CustConstruct.class,
//                        new Class<?>[]{String.class},
                        cm.contactLastName,
                        cm.customerNumber
                );

        JPAQueryFactory jqf = new JPAQueryFactory(em);
        System.out.println(c.toString());

        final List<CustConstruct> fetch = jqf.select(c).from(cm).fetch();
        fetch.forEach(System.out::println);

//        assert("new " + tonels.entity.CustomersEntity.class.getName() + "(cm.name)",c);
//        assertToString("new " + getClass().getName() + "$BookmarkDTO(cat.name)",new QBookmarkDTO(cat.name));
    }

    // str ASC
    @Test
    public void select4() {
        OrderSpecifier<?> order = new OrderSpecifier<String>(Order.ASC, Expressions.stringPath("str"));
        System.out.println(order.toString());
    }

    @Test
    public void select5() {

        Integer page = 1;
        Integer rows = 2;
//        String sidx = "id";
//        String sord = "asc";

        Sort sortId = new Sort(Sort.Direction.ASC, "id");
        sortId.and(new Sort(Sort.Direction.DESC,"idc"));

        Pageable pageable = PageRequest.of(page - 1, rows, sortId);

//        for (Sort.Order order : pageable.getSort()) {
//            PathBuilder<Object> path = entityPath.get(order.getProperty());
//            limit.orderBy(new OrderSpecifier(com.querydsl.core.types.Order.valueOf(order.getDirection().name()), path));
//        }

        for (Sort.Order order : pageable.getSort()) {
            System.out.println(order.getProperty());
            System.out.println(order.getDirection());
        }

    }

}
