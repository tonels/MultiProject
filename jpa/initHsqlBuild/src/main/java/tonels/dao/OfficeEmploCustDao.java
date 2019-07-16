package tonels.dao;

import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Repository;
import tonel.model.CustomersEntity;
import tonel.model.EmployeesEntity;
import tonel.model.OfficesEntity;
import tonels.utils.PageParam;
import tonels.vo.OffEmpCustParamsVo;
import tonels.vo.OffEmpCustVo;
import utils.PageBean;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Parameter;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Repository
public class OfficeEmploCustDao {

//    @PersistenceContext ,本来是这个注解
    @Resource
    private EntityManager entityManager;

    private static final List ORDER_BY_FIELDS =  Lists.newArrayList("state","country");

    private static final String VO = " new " + OffEmpCustVo.class.getName()
            + "(oc.officeCode,oc.country,oc.state,em.employeeNumber,em.email,cu.customerNumber,cu.state)";
    private static final String FROM = " from " + OfficesEntity.class.getSimpleName() + " oc left join "
            + EmployeesEntity.class.getSimpleName() + " em on oc.officeCode = em.officeCode "
            +" left join " + CustomersEntity.class.getSimpleName() + " cu on em.employeeNumber = cu.salesRepEmployeeNumber";

/**
 * 分页查询条件
 */
public PageBean<OffEmpCustVo> list(OffEmpCustParamsVo param, PageParam pageParam) {
    if (pageParam == null || !pageParam.pageIsCompliant()) {
        return PageBean.empty();
    }

    Map<String, Object> paramMap = new HashMap<String, Object>(16);
    String where = getWhere(param, paramMap);

    String orderBy = getOrderBy(pageParam);
    Query query = entityManager.createQuery("select " + VO + FROM + where + orderBy);

    // 这里看看这个query里是什么数据
    Set<Parameter<?>> parameters = query.getParameters();
    System.out.println("parameters" + parameters);
    // 集合 [org.hibernate.engine.query.spi.NamedParameterDescriptor@68ac491,
    // org.hibernate.engine.query.spi.NamedParameterDescriptor@3f187397]

    for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
        String key = entry.getKey();
        Object value = entry.getValue();
        query.setParameter(key, value);
    }

    query.setFirstResult((pageParam.getPage() - 1) * pageParam.getRows()).setMaxResults(pageParam.getRows());
    List<OffEmpCustVo> list = query.getResultList();

    query = entityManager.createQuery("select count(*) " + FROM + where);
    paramMap.forEach(query::setParameter);
    Long total = (Long) query.getSingleResult();

    Double totalPage = Math.ceil(total.doubleValue() / pageParam.getRows());
    return PageBean.ok(totalPage.intValue(), total, list);
}

    private String getWhere(OffEmpCustParamsVo param, Map<String, Object> paramMap) {
        StringBuilder sb = new StringBuilder(" where 1 = 1 ");
        if (param != null) {
            if (StrUtil.isNotBlank(param.getOffState())) {
                sb.append(" and oc.state like :state ");
                paramMap.put("state", "%" + param.getOffState() + "%");
            }
            if (null != param.getEmploNumber()) {
                sb.append(" and em.employeeNumber >= :employeeNumber");
                paramMap.put("employeeNumber", param.getEmploNumber());
            }
            if (StrUtil.isNotBlank(param.getCustState())) {
                sb.append(" and cu.state = :state2 ");
                paramMap.put("state2", param.getCustState());
            }
        }
        return sb.toString();
    }

    private String getOrderBy(PageParam pageParam) {
        if (pageParam == null || !pageParam.orderByIsCompliant()) {
            return " order by em.employeeNumber desc ";
        }
        if (ORDER_BY_FIELDS.contains(pageParam.getSidx())) {
            return " order by " + pageParam.getSidx() + " " + pageParam.getSord() + " ";
        }
        return "";
    }






}
