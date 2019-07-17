package tonels.dao;

import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import org.hibernate.SQLQuery;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;
import org.springframework.stereotype.Repository;
import jpaCommon.model.OfficesEntity;
import tonels.utils.PageParam;
import tonels.vo.OffEmpCustParamsVo;
import tonels.vo.OffEmpCustVo;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class OfficeEmploCustDao2 {

//    @PersistenceContext // ,本来是这个注解
    @Resource
    private EntityManager entityManager;

    private static final List ORDER_BY_FIELDS =  Lists.newArrayList("state","country");

    public List<OffEmpCustVo> list(OffEmpCustParamsVo params, PageParam pageParam){

        Map<String, Type> typeMap = new HashMap<>();
        typeMap.put("officeCode", StandardBasicTypes.STRING);
        typeMap.put("offCountry", StandardBasicTypes.STRING);
        typeMap.put("offState", StandardBasicTypes.STRING);
        typeMap.put("emploNumber", StandardBasicTypes.INTEGER);
        typeMap.put("emploEmail", StandardBasicTypes.STRING);
        typeMap.put("custNumber", StandardBasicTypes.INTEGER);
        typeMap.put("custState", StandardBasicTypes.STRING);

        Map<String, Object> paramMap = new HashMap<String, Object>();

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

        String where = getWhere(params, paramMap);
        String orderBy = getOrderBy(pageParam);
        sb.append(vo).append(from).append(where).append(orderBy);

        Query createNativeQuery = this.entityManager.createNativeQuery(sb.toString());
        NativeQuery query = createNativeQuery.unwrap(NativeQuery.class);

        for (Map.Entry<String, Type> e : typeMap.entrySet()) {
            String k = e.getKey();
            Type v = e.getValue();
            query.addScalar(k, v);
        }
        for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            query.setParameter(key, value);
        }
        // 这个是可以的
        query.unwrap(NativeQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List<OffEmpCustVo> resultList = query.getResultList();
        return resultList;
    }

    public List<OffEmpCustVo> list_2(OffEmpCustParamsVo params, PageParam pageParam){

        Map<String, Type> typeMap = new HashMap<>();
        typeMap.put("officeCode", StandardBasicTypes.STRING);
        typeMap.put("offCountry", StandardBasicTypes.STRING);
        typeMap.put("offState", StandardBasicTypes.STRING);
        typeMap.put("emploNumber", StandardBasicTypes.INTEGER);
        typeMap.put("emploEmail", StandardBasicTypes.STRING);
        typeMap.put("custNumber", StandardBasicTypes.INTEGER);
        typeMap.put("custState", StandardBasicTypes.STRING);

        Map<String, Object> paramMap = new HashMap<String, Object>();

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

        String where = getWhere(params, paramMap);
        String orderBy = getOrderBy(pageParam);
        sb.append(vo).append(from).append(where).append(orderBy);

        Query createNativeQuery = this.entityManager.createNativeQuery(sb.toString());
        NativeQuery query = createNativeQuery.unwrap(NativeQuery.class);

        typeMap.forEach((k, v) -> query.addScalar(k, v));
        paramMap.forEach((key, value) -> query.setParameter(key, value));

        // 这里会报错，不能直接对象接收，但可以先 Map 在自动映射成对象，参照上面的写法
        query.unwrap(NativeQuery.class).setResultTransformer(Transformers.aliasToBean(OffEmpCustVo.class));
        List<OffEmpCustVo> resultList = query.getResultList();

        return resultList;
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


    public List<OfficesEntity> findAll1(){

        StringBuilder sb = new StringBuilder();

        String vo = "select * ";
        String from = "FROM offices oc ";

        sb.append(vo + from );

        Query createNativeQuery = this.entityManager.createNativeQuery(sb.toString());

        // 这个是可以实现的，从 object[] 转 map ，然后底层会自动映射成 Model ,就是方法过期了
//        createNativeQuery.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        createNativeQuery.unwrap(SQLQuery.class).setResultTransformer(Transformers.aliasToBean( OfficesEntity.class)); // 也是可以的

        List<OfficesEntity> resultList = createNativeQuery.getResultList();
        return resultList;
    }

    public List<OfficesEntity> findAll2(){

        StringBuilder sb = new StringBuilder();

        String vo = "select * ";
        String from = "FROM offices oc ";

        sb.append(vo + from );

        Query createNativeQuery = this.entityManager.createNativeQuery(sb.toString());

        // 这个也是可以实现的，从 object[] 转 map ，然后底层会自动映射成 Model ,就是方法过期了
//        createNativeQuery.unwrap(NativeQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        createNativeQuery.unwrap(NativeQuery.class).setResultTransformer(Transformers.aliasToBean( OfficesEntity.class)); // 也是可以的

        List<OfficesEntity> resultList = createNativeQuery.getResultList();
        return resultList;
    }


    // 这个方法不过时，但没有用，运行会报错，没调通
    // cannot be cast to org.hibernate.query.internal.NativeQueryImpl
    public List<OfficesEntity> findAll3(){
        StringBuilder sb = new StringBuilder();
        String vo = "select * ";
        String from = "FROM offices oc ";
        sb.append(vo + from );

        Query createNativeQuery = this.entityManager.createNativeQuery(sb.toString());
        createNativeQuery.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

        List<OfficesEntity> resultList = createNativeQuery.getResultList();
        return resultList;
    }









}
