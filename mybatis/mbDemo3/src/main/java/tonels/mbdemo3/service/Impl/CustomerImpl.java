package tonels.mbdemo3.service.Impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tonels.mbdemo3.dao.CustomersMapper;
import tonels.mbdemo3.dao.OfficesMapper;
import tonels.mbdemo3.entity.Customers;
import tonels.mbdemo3.entity.Offices;
import tonels.mbdemo3.params.CustoParams;
import tonels.mbdemo3.service.CustomersService;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class CustomerImpl implements CustomersService {

    @Resource
    private CustomersMapper customersMapper;

    @Resource
    private OfficesMapper officesMapper;

    @Override
    public Customers save(Customers customers) {
        int i = customersMapper.insert(customers);
        return 1 == i ? customers : null;

    }

    @Override
    public Customers update(Customers customers) {
        int i = customersMapper.updateByPrimaryKey(customers);
        return 1 == i ? customers:null;
    }

    @Override
    public void delete(Integer id) {
        customersMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Customers> findAll() {
        return customersMapper.selectAll();
    }

    @Override
    public List<Customers> findAll2() {
        return customersMapper.selectVo1();
    }

    @Override
    public List<Customers> findByCity(String city) {
        List<Customers> customers = customersMapper.selectByCity(city);
        return customers;
    }

    @Override
    public List<Offices> findById(Integer id) {
        return officesMapper.selectByJoin();
    }

    @Override
    public List<Customers> findWhere1(CustoParams params) {
        List<Customers> customers = customersMapper.selectVo2(params);
        return customers;
    }
}





