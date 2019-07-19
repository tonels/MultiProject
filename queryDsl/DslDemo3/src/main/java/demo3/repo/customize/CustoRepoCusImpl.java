package demo3.repo.customize;

import cn.hutool.json.JSONUtil;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import demo3.model.QEntity.QCustomersEntity;
import demo3.model.QEntity.QEmployeesEntity;
import demo3.model.QEntity.QOfficesEntity;
import demo3.vo.OffEmpCustVo;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Repository
public class CustoRepoCusImpl implements CustoRepoCustomize {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Tuple> findCustPay(Predicate predicate) {
        return null;
    }

    @Override
    public QueryResults<Tuple> findCustOrder(Predicate predicate, Pageable pageable) {
        return null;
    }

    @Override
    public List<OffEmpCustVo> findOffEmpCust(Predicate predicate) {
        JPAQuery<OffEmpCustVo> query = new JPAQuery<>(em);

        QCustomersEntity customers = QCustomersEntity.customersEntity;
        QOfficesEntity office = QOfficesEntity.officesEntity;
        QEmployeesEntity employee = QEmployeesEntity.employeesEntity;


        List<Tuple> list = query.select(
                office.officeCode,
                office.country.as("offCountry"),
                office.state.as("offState"),
                employee.employeeNumber.as("emploNumber"),
                employee.email.as("emploEmail"),
                customers.customerNumber.as("custNumber"),
                customers.state.as("custState")).from(office).leftJoin(employee).on(office.officeCode.eq(employee.officeCode)).leftJoin(customers).on(employee.employeeNumber.eq(customers.salesRepEmployeeNumber)).where(predicate).fetch();

        List<OffEmpCustVo> voList = list.stream().map(r -> JSONUtil.toBean(JSONUtil.toJsonStr(r), OffEmpCustVo.class)).collect(Collectors.toList());

        return voList;
    }

    @Override
    public List<OffEmpCustVo> findOffEmpCust() {
        JPAQuery<OffEmpCustVo> query = new JPAQuery<>(em);

        QCustomersEntity customers = QCustomersEntity.customersEntity;
        QOfficesEntity office = QOfficesEntity.officesEntity;
        QEmployeesEntity employee = QEmployeesEntity.employeesEntity;


        List<Tuple> list = query.select(
                office.officeCode,
                office.country.as("offCountry"),
                office.state.as("offState"),
                employee.employeeNumber.as("emploNumber"),
                employee.email.as("emploEmail"),
                customers.customerNumber.as("custNumber"),
                customers.state.as("custState")).from(office).leftJoin(employee).on(office.officeCode.eq(employee.officeCode)).leftJoin(customers).on(employee.employeeNumber.eq(customers.salesRepEmployeeNumber)).fetchResults().getResults();

        List<OffEmpCustVo> voList = list.stream().map(r -> JSONUtil.toBean(JSONUtil.toJsonStr(r), OffEmpCustVo.class)).collect(Collectors.toList());

        return voList;
    }
}
