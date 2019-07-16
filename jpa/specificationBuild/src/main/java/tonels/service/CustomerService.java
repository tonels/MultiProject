package tonels.service;

import tonel.model.CustomersEntity;

import java.util.List;

public interface CustomerService {

    List<CustomersEntity> findAllBySpecification(CustomersEntity customers);

    List<CustomersEntity> findBySpecification(CustomersEntity customers);

    List<CustomersEntity> findBySpecification2(CustomersEntity customers);
}
