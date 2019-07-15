package tonels.service;

import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tonel.model.CustomersEntity;
import tonels.repo.CustomerRepo;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.endsWith;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.startsWith;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Resource
    private CustomerRepo customerRepo;

    @Override
    public List<CustomersEntity> findAllBySpecification(CustomersEntity customers) {
        return customerRepo.findAll(new Specification<CustomersEntity>() {
            @Override
            public Predicate toPredicate(Root<CustomersEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = Lists.newArrayList();
                if (!StrUtil.isBlank(customers.getContactFirstName())) {
                    predicates.add(cb.like(root.get("contactFirstName"),  customers.getContactFirstName() + "%"));
                }
                if (!StrUtil.isBlank(customers.getCity())) {
                    predicates.add(cb.equal(root.get("city"), customers.getCity()));
                }
                if (null != customers.getSalesRepEmployeeNumber()){
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
            public Predicate toPredicate(Root<CustomersEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
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
                return cb.and(andPredicate.toArray(new Predicate[0])/*,orPredicate.toArray(new Predicate[0])*/);;
            }
        });
    }
}
