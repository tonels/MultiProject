//package tonels.dao;
//
//import cn.hutool.core.collection.CollectionUtil;
//
//import cn.hutool.core.util.StrUtil;
//import org.apache.poi.util.StringUtil;
//import org.hibernate.SQLQuery;
//import org.hibernate.transform.Transformers;
//import org.springframework.stereotype.Repository;
//import tonels.utils.PageParam;
//import utils.PageBean;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.persistence.Query;
//import java.math.BigInteger;
//import java.time.LocalDateTime;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * 团队公告动态查询
// **/
//
//@Repository
//public class NoticeDao {
//
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    private final String[] sidxs = {"noticeId", "title", "content"};
//
//
//    @SuppressWarnings("unchecked")
//    public PageBean<Notice> findAllNotice(NoticeDto noticeDto, PageParam pageParam) {
//        String vo = " new " + Notice.class.getName() + "(noticeId,title,status,priority,scope,groupScope,publishTime,endTime,createBy,createTime) ";
//        String from = " from " + Notice.class.getSimpleName() + " ";
//
//        Map<String, Object> paramMap = new HashMap<String, Object>();
//        String where = getWhere(noticeDto, paramMap);
//
//        String orderBy = " order by priority asc ";
//        if (pageParam.orderByIsCompliant()) {
//            if (pageParam.getSidx().equals("createTime")) {
//                orderBy += "," + pageParam.getSidx() + " " + pageParam.DESC;
//            }
//        }
//
//        Query query = entityManager.createQuery("select " + vo + from + where + orderBy);
//        paramMap.forEach(query::setParameter);
//        query.setFirstResult((pageParam.getPage() - 1) * pageParam.getRows()).setMaxResults(pageParam.getRows());
//        List<Notice> list = query.getResultList();
//        // 查询总数
//        query = entityManager.createQuery("select count(*) " + from + where);
//        paramMap.forEach(query::setParameter);
//        Long total = (Long) query.getSingleResult();
//
//        Double totalPage = Math.ceil(total.doubleValue() / pageParam.getRows());
//
//        return PageBean.ok(totalPage.intValue(), total, list);
//    }
//
//    private String getWhere(NoticeDto noticeDto, Map<String, Object> paramMap) {
//        StringBuilder sb = new StringBuilder(" where isDelete = 0 ");
//        if (noticeDto != null) {
//            if ("0".equals(noticeDto.getIsExpired())) {
//                sb.append(" and endTime >= :currentTime ");
//                paramMap.put("currentTime", LocalDateTime.now());
//            } else if ("1".equals(noticeDto.getIsExpired())) {
//                sb.append(" and endTime <= :currentTime ");
//                paramMap.put("currentTime", LocalDateTime.now());
//            }
//            if (!StringUtil.isNullStr(noticeDto.getPriority())) {
//                sb.append(" and priority = :priority ");
//                paramMap.put("priority", noticeDto.getPriority());
//            }
//            if (!StringUtil.isNullStr(noticeDto.getStatus())) {
//                sb.append(" and status = :status ");
//                paramMap.put("status", noticeDto.getStatus());
//            }
//            if (!StringUtil.isNullStr(noticeDto.getTitle())) {
//                sb.append(" and title like :title ");
//                paramMap.put("title", "%" + noticeDto.getTitle() + "%");
//            }
//            if (noticeDto.getStartTime() != null) {
//                sb.append(" and endTime >= :startTime ");
//                paramMap.put("startTime", noticeDto.getStartTime());
//            }
//            if (noticeDto.getEndTime() != null) {
//                sb.append(" and endTime <= :endTime ");
//                paramMap.put("endTime", noticeDto.getEndTime());
//            }
//            if ("0".equals(noticeDto.getRange())) {
//                sb.append(" and scope = :scope1 ");
//                paramMap.put("scope1", noticeDto.getRange());
//            }
//            if (!StringUtil.isNullStr(noticeDto.getRange()) && !noticeDto.getRange().equals("0")) {
//                sb.append(" and ( scope = :scope2 ");
//                paramMap.put("scope2", "0");
//
//                String[] split = noticeDto.getRange().split(",");
//                for (int i = 0; i < split.length; i++) {
//                    sb.append(" or groupScope like :groupScope" + i);
//                    paramMap.put("groupScope" + i, "%," + split[i] + ",%");
//                }
//                sb.append(") ");
//            }
//        }
//
//        return sb.toString();
//    }
//
//
//    /**
//     * @description 查询当前用户可以看到的公告(普通用户)
//     * @auther yangyu
//     * @date
//     */
//    public Integer count(List<Long> groupIds) {
//        Integer count = 0;
//        Map<String, Object> paramMap = new HashMap<>();
//        paramMap.put("isDelete", NoticeEnum.YesOrNo.NO.getKey());
//        paramMap.put("status", NoticeEnum.Status.PUBLISHED.getKey());
//        String sql = "select count(n.notice_id) from notice n where n.end_time > now() AND n.is_delete = :isDelete AND n.STATUS = :status ";
//        if (!CollectionUtil.isEmpty(groupIds)) {
//            sql += "and ( n.scope = 0 or ";
//            for (Long groupId : groupIds) {
//                sql = sql + "n.group_scope like '%," + groupId + ",%' or ";
//            }
//            sql = sql.substring(0, sql.lastIndexOf("or"));
//            sql += ")";
//        }
//        Query query = this.entityManager.createNativeQuery(sql);
//        paramMap.forEach(query::setParameter);
//        List resultList = query.getResultList();
//        if (resultList.size() > 0) {
//            count = Integer.parseInt(resultList.get(0).toString());
//        }
//        return count;
//    }
//
//    public Integer count() {
//        return count(null);
//    }
//}