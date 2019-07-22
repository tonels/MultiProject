package demo3.repo.customize;

import cn.hutool.json.JSONUtil;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import demo3.model.QEntity.QCustomersEntity;
import demo3.model.QEntity.QEmployeesEntity;
import demo3.model.QEntity.QOfficesEntity;
import demo3.vo.OffEmpCustVo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerRepoCustomImpl implements CustomerRepoCustom {

    @PersistenceContext
    private EntityManager em;

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

        // select off.officeCode , off.country, off.state , em.employeeNumber, em.email, cu.customerNumber , cu.state  from offices off left outer join employees em on (off.officeCode=em.officeCode) left outer join customers cu on (em.employeeNumber=cu.salesRepEmployeeNumber)
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
                customers.state.as("custState")).from(office).leftJoin(employee).on(office.officeCode.eq(employee.officeCode)).leftJoin(customers).on(employee.employeeNumber.eq(customers.salesRepEmployeeNumber)).where(predicate).fetchResults().getResults();

        List<OffEmpCustVo> voList = list.stream().map(r -> JSONUtil.toBean(JSONUtil.toJsonStr(r), OffEmpCustVo.class)).collect(Collectors.toList());

        return voList;
    }
}
