package tonels.qbe.service;

import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jpaCommon.model.CustomersEntity;
import tonels.qbe.repo.CustomerRepo;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.startsWith;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Resource
    private CustomerRepo customerRepo;

    @Override
    public List<CustomersEntity> findAllByExample(CustomersEntity customers) {
//
//        ExampleMatcher matcher = ExampleMatcher.matching()
//                .withMatcher("firstname", endsWith())
//                .withMatcher("lastname", startsWith().ignoreCase()); // 这里支持静态引包
        return customerRepo.findAll(Example.of(customers,
                ExampleMatcher.matching().withMatcher("contactFirstName", startsWith().ignoreCase())
                ));
    }

    @Override
    public List<CustomersEntity> findAllBySpecification(CustomersEntity customers) {
        return customerRepo.findAll(new Specification<CustomersEntity>() {
            @Override
            public Predicate toPredicate(Root<CustomersEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = Lists.newArrayList();
                if (!StrUtil.isBlank(customers.getContactFirstName())) {
                    predicates.add(cb.like(root.get("contactFirstName"), customers.getContactFirstName()));
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
}
