package test.tonels;

import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Ops;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.Template;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.SimpleTemplate;
import com.querydsl.core.types.dsl.StringTemplate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tonels.Qsl2;
import tonels.entity.CustomersEntity;
import tonels.entity.QCustomersEntity;
import tonels.entity.QOrderdetailsEntity;
import tonels.entity.QOrdersEntity;
import tonels.repo.TCityRepo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
        assertNotNull("customersEntity.city = ds",cm.city.eq("ds"));
        assertNotNull("containsIc(customersEntity.city,ds)",cm.city.containsIgnoreCase("ds"));
        assertNotNull("customersEntity.city + ds",cm.city.concat("ds"));
        assertNotNull("endsWith(customersEntity.city,ds)",cm.city.endsWith("ds"));
        assertNotNull("locate(ds,customersEntity.city)",cm.city.locate("ds"));
        assertNotNull("coalesce(customersEntity.city, ds)",cm.city.coalesce("ds"));
        assertNotNull("upper(customersEntity.city) as ss = op",cm.city.upper().as("ss").eq("op"));
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
     select ordersenti0_.customerNumber                     as col_0_0_,
     	   ordersenti0_.comments                           as col_1_0_,
     	   date_format(ordersenti0_.orderDate, '%Y-%m-%d') as col_2_0_
     from orders ordersenti0_
     */
    @Test
    public void select2() {
        JPAQueryFactory jqf = new JPAQueryFactory(em);

        StringTemplate dateExpr = Expressions.stringTemplate("DATE_FORMAT({0},'%Y-%m-%d')", od.orderDate);
        System.out.println(jqf.select(
                od.customerNumber,
                od.comments,
                dateExpr
        ).from(od).fetch().size());
    }

    /**

     select ordersenti0_.customerNumber      as col_0_0_,
     sum(ordersenti0_.customerNumber) as col_1_0_,
     ordersenti0_.comments            as col_2_0_
     from orders ordersenti0_
     group by date_format(ordersenti0_.orderDate, '%Y-%m-%d')

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




//    @Test
//    public void select3() {
//        JPAQueryFactory jqf = new JPAQueryFactory(em);
//
//
////        Expressions.
//
//        System.out.println(jqf.select(
//                od.customerNumber,
//                od.comments,
////                dateExpr
//        ).from(od).fetch().size());
//    }

    /**
     * 构造器
     *
     *
     *
     */
    @Test
    public void select3() {
//        JPAQueryFactory jqf = new JPAQueryFactory(em);

        ConstructorExpression<CustomersEntity> c =
                Projections.constructor(
                        CustomersEntity.class,
                        new Class<?>[]{String.class},
                        cat.name);
        assertToString("new " + com.querydsl.jpa.domain.Cat.class.getName() + "(cat.name)", c);
        assertToString("new " + getClass().getName() + "$BookmarkDTO(cat.name)",new QBookmarkDTO(cat.name));
    }

}
