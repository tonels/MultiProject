package tonels.dao;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.google.common.collect.Lists;
import org.apache.poi.util.StringUtil;
import org.hibernate.SQLQuery;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;
import org.springframework.stereotype.Repository;
import tonels.utils.PageParam;
import tonels.vo.OffEmpCustParamsVo;
import tonels.vo.OffEmpCustVo;
import utils.PageBean;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class OfficeEmploCustDao2 {

//    @PersistenceContext ,本来是这个注解
    @Resource
    private EntityManager entityManager;
    private static final List ORDER_BY_FIELDS =  Lists.newArrayList("state","country");

    private String sqlEnd = " from " + "group_member_rel g left join member m " + "on g.member_id = m.member_id "
            + "where g.group_id = :groupId and g.group_id != 0";
    private static Map<String, Type> typeMap = new HashMap<String, Type>(16);

    static {
        typeMap.put("officeCode", StandardBasicTypes.STRING);
        typeMap.put("offCountry", StandardBasicTypes.STRING);
        typeMap.put("offState", StandardBasicTypes.STRING);
        typeMap.put("emploNumber", StandardBasicTypes.INTEGER);
        typeMap.put("emploEmail", StandardBasicTypes.STRING);
        typeMap.put("custNumber", StandardBasicTypes.INTEGER);
        typeMap.put("custState", StandardBasicTypes.STRING);
    }

    @SuppressWarnings("unchecked")
    public List<OffEmpCustVo> list(OffEmpCustParamsVo params, PageParam pageParam){
        if (pageParam.getPage() == null || pageParam.getPage() < 1) {
            return Collections.EMPTY_LIST;
        }
        if (pageParam.getRows() == null || pageParam.getRows() < 1) {
            return Collections.EMPTY_LIST;
        }

        StringBuilder sb = new StringBuilder();
        String vo = "SELECT oc.officeCode, oc.country AS offCountry, oc.state AS offState, em.employeeNumber AS emploNumber, em.email AS emploEmail, cu.customerNumber AS custNumber, cu.state AS custState ";

        String from = "FROM offices oc LEFT JOIN employees em ON oc.officeCode = em.officeCode " +
                "LEFT JOIN customers cu ON em.employeeNumber = cu.salesRepEmployeeNumber";

        Map<String, Object> paramMap = new HashMap<String, Object>();
        String where = getWhere(params, paramMap);

        String orderBy = getOrderBy(pageParam);

        sb.append(vo).append(from).append(where).append(orderBy);

        Query createNativeQuery = entityManager.createNativeQuery(sb.toString());

        createNativeQuery.unwrap(NativeQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

//        NativeQuery query = createNativeQuery.unwrap(NativeQuery.class);
//        for (Map.Entry<String, Type> e : typeMap.entrySet()) {
//            String k = e.getKey();
//            Type v = e.getValue();
//            query.addScalar(k, v);
//        }
//        for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
//            String key = entry.getKey();
//            Object value = entry.getValue();
//            query.setParameter(key, value);
//        }

        List<OffEmpCustVo> collect = createNativeQuery.getResultList();
        return collect;
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
