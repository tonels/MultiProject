package tonels.qbe.service;

import jpaCommon.model.CustomersEntity;

import java.util.List;

public interface CustomerService {

    List<CustomersEntity> findAllByExample(CustomersEntity customers);

    List<CustomersEntity> findAllBySpecification(CustomersEntity customers);
}
