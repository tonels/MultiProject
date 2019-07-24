package tonels.mbdemo3.service;

import tonels.mbdemo3.entity.Customers;
import tonels.mbdemo3.entity.Offices;

import java.util.List;

public interface CustomersService {
    Customers save(Customers customers);

    Customers update(Customers customers);

    void delete(Integer id);

    List<Customers> findAll();

    List<Customers> findByCity(String city);

    List<Offices> findById(Integer id);
}
