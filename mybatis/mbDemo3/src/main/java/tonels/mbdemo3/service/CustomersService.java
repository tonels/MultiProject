package tonels.mbdemo3.service;

import tonels.mbdemo3.entity.Customers;
import tonels.mbdemo3.entity.Offices;
import tonels.mbdemo3.params.CustoParams;

import java.util.List;
import java.util.Set;

public interface CustomersService {
    Customers save(Customers customers);

    void update(Customers customers);

    void delete(Set<Integer> ids);

    List<Customers> findAll();

    List<Customers> findByCity(String city);

    List<Customers> findById(Integer id);

    List<Customers> findAll2();

    List<Offices> findJoin(Integer id);

    List<Customers> findWhere1(CustoParams params);

    List<Customers> findByCityAndFirstName(String city, String contactfirstname);
}
