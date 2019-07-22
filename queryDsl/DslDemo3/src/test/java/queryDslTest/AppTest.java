package queryDslTest;

import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import com.querydsl.core.BooleanBuilder;
import demo3.Demo3Run;
import demo3.model.CustomersEntity;
import demo3.model.QEntity.QCustomersEntity;
import demo3.model.QEntity.QEmployeesEntity;
import demo3.model.QEntity.QOfficesEntity;
import demo3.repo.CustomerRepo;
import demo3.vo.OffEmpCustParamsVo;
import demo3.vo.OffEmpCustVo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.Instant;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Demo3Run.class)
public class AppTest {

    @PersistenceContext
    private EntityManager em;
    @Resource
    private CustomerRepo customerRepo;

    private List<OffEmpCustParamsVo> list;

    @Before
    public void inti(){
        OffEmpCustParamsVo o1 = new OffEmpCustParamsVo("a", 100, "ca");

        OffEmpCustParamsVo o2 = new OffEmpCustParamsVo("a", 100, "");

        list = Lists.newArrayList(o1, o2);
    }

    @Test
    public void t1(){
        List<OffEmpCustVo> offEmpCust = customerRepo.findOffEmpCust();
        System.out.println(offEmpCust);
    }

    @Test
    public void t2(){

        OffEmpCustParamsVo param = list.get(0);
        QOfficesEntity offices = QOfficesEntity.officesEntity;
        QEmployeesEntity employees = QEmployeesEntity.employeesEntity;
        QCustomersEntity customers = QCustomersEntity.customersEntity;


        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (StrUtil.isNotBlank(param.getOffState())) {
            booleanBuilder.and(offices.state.like("%"+param.getOffState()+"%"));
        }
        if (null != param.getEmploNumber()) {
            booleanBuilder.and(employees.employeeNumber.gt(param.getEmploNumber()));
        }
        if (StrUtil.isNotBlank(param.getCustState())) {
            booleanBuilder.and(customers.state.eq(param.getCustState()));
        }
        List<OffEmpCustVo> offEmpCust = customerRepo.findOffEmpCust(booleanBuilder);

        // 三个条件拼装
        // select .. from table on .. where (officesent0_.state like ? escape '!') and employeese1_.employeeNumber>? and customerse2_.state=?

        System.out.println(offEmpCust.get(0));
        System.out.println(offEmpCust.size());
    }

    @Test
    public void t3(){

        OffEmpCustParamsVo param = list.get(1);
        QOfficesEntity offices = QOfficesEntity.officesEntity;
        QEmployeesEntity employees = QEmployeesEntity.employeesEntity;
        QCustomersEntity customers = QCustomersEntity.customersEntity;


        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (StrUtil.isNotBlank(param.getOffState())) {
            booleanBuilder.and(offices.state.like("%"+param.getOffState()+"%"));
        }
        if (null != param.getEmploNumber()) {
            booleanBuilder.and(employees.employeeNumber.gt(param.getEmploNumber()));
        }
        if (StrUtil.isNotBlank(param.getCustState())) {
            booleanBuilder.and(customers.state.eq(param.getCustState()));
        }
        List<OffEmpCustVo> offEmpCust = customerRepo.findOffEmpCust(booleanBuilder);

        // 2个条件拼装
        // select .. from table on .. where (officesent0_.state like ? escape '!') and employeese1_.employeeNumber>?

        System.out.println(offEmpCust);
        System.out.println(offEmpCust.size());
    }

    @Test
    public void t4(){

        OffEmpCustParamsVo param = new OffEmpCustParamsVo("",null,"");
        QOfficesEntity offices = QOfficesEntity.officesEntity;
        QEmployeesEntity employees = QEmployeesEntity.employeesEntity;
        QCustomersEntity customers = QCustomersEntity.customersEntity;


        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (StrUtil.isNotBlank(param.getOffState())) {
            booleanBuilder.and(offices.state.like("%"+param.getOffState()+"%"));
        }
        if (null != param.getEmploNumber()) {
            booleanBuilder.and(employees.employeeNumber.gt(param.getEmploNumber()));
        }
        if (StrUtil.isNotBlank(param.getCustState())) {
            booleanBuilder.and(customers.state.eq(param.getCustState()));
        }
        List<OffEmpCustVo> offEmpCust = customerRepo.findOffEmpCust(booleanBuilder);

        // 全条件条件拼装
        // select from on ..

        System.out.println(offEmpCust);
        System.out.println(offEmpCust.size());
    }






}