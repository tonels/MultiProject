package tonels.service;

import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jpaCommon.model.CustomersEntity;
import tonels.repo.CustomerRepo;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Resource
    private CustomerRepo customerRepo;
    @Resource
    private EntityManager entityManager;

    @Override
    public List<CustomersEntity> findAllBySpecification(CustomersEntity customers) {
        return customerRepo.findAll(new Specification<CustomersEntity>() {
            @Override
            public Predicate toPredicate(Root<CustomersEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = Lists.newArrayList();
                if (!StrUtil.isBlank(customers.getContactFirstName())) {
                    predicates.add(cb.like(root.get("contactFirstName"), customers.getContactFirstName() + "%"));
                }
                if (!StrUtil.isBlank(customers.getCity())) {
                    predicates.add(cb.equal(root.get("city"), customers.getCity()));
                }
                if (null != customers.getSalesRepEmployeeNumber()) {
                    predicates.add(cb.ge(root.get("salesRepEmployeeNumber"), customers.getSalesRepEmployeeNumber()));
                }
                return cb.and(predicates.toArray(new Predicate[0]));
            }
        });
    }

    @Override
    public List<CustomersEntity> findBySpecification(CustomersEntity customers) {
        return customerRepo.findAll(new Specification<CustomersEntity>() {
            @Override
            public Predicate toPredicate(Root<CustomersEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                final List<Predicate> andPredicate = Lists.newArrayList();
                final List<Predicate> orPredicate = Lists.newArrayList();

                // and 条件
                if (!StrUtil.isBlank(customers.getCity())) {
                    andPredicate.add(cb.equal(root.get("city"), customers.getCity()));
                }
                if (!StrUtil.isBlank(customers.getCountry())) {
                    andPredicate.add(cb.equal(root.get("country"), customers.getCountry()));
                }
                // or 条件
                if (!StrUtil.isBlank(customers.getState())) {
                    orPredicate.add(cb.equal(root.get("state"), customers.getState()));
                }
                if (null != customers.getCustomerNumber()) {
                    orPredicate.add(cb.ge(root.get("customerNumber"), customers.getCustomerNumber()));
                }
                return cb.and(cb.and(andPredicate.toArray(new Predicate[0])), cb.or(orPredicate.toArray(new Predicate[0])));
            }
        });
    }

    // 这个返回行不通，会报异常
    // .ExtendedEntityManagerCreator$ExtendedEntityManagerInvocationHandler.invoke
    // SharedEntityManagerCreator$SharedEntityManagerInvocationHandler.invoke
    @Override
    public List<CustomersEntity> findBySpecification2(CustomersEntity customers) {
//          List<Predicate> predicatesList = new ArrayList<Predicate>();
//          predicatesList.add(cb.and(cb.equal(root.get("d"), customers.getCity()),cb.equal(root.get("d"),customers.getCountry())));

        return customerRepo.findAll(new Specification<CustomersEntity>() {
            @Override
            public Predicate toPredicate(Root<CustomersEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate p1 = null;
                Predicate p2 = null;
                Predicate p3 = null;
                Predicate p4 = null;
                // and 条件
                if (!StrUtil.isBlank(customers.getCity())) {
                    p1 = cb.equal(root.get("city"), customers.getCity());
                }
                if (!StrUtil.isBlank(customers.getCountry())) {
                    p2 = cb.equal(root.get("country"), customers.getCountry());
                }
                // or 条件
                if (!StrUtil.isBlank(customers.getState())) {
                    p3 = cb.equal(root.get("state"), customers.getState());
                }
                if (null != customers.getCustomerNumber()) {
                    p4 = cb.ge(root.get("customerNumber"), customers.getCustomerNumber());
                }
                query.where(cb.and(cb.and(p1, p2), cb.or(p3, p4)));
                query.orderBy(cb.desc(root.get("customerNumber")));
                return query.getRestriction();
            }
        });
    }


}



