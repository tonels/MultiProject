package tonels.mbdemo3.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import tonels.mbdemo3.entity.Customers;
import tonels.mbdemo3.params.CustoParams;

import java.util.List;
import java.util.Set;

@Mapper
public interface CustomersMapper {

    int insert(Customers record);

    List<Customers> selectAll();

    List<Customers> selectVo1();

    List<Customers> selectByCity(String city);

    // TODO: 2019/7/25  @Param("customernumber") 对于一个参数，这个注解，可以没有，有的话，一定要对应xxxmapping.xml
    List<Customers> selectByPrimaryKey(/*@Param("customernumber")*/ Integer id); //

    List<Customers> selectVo2(CustoParams params);

    // TODO: 2019/7/25  @Param("customernumber") 对于多个参数，简单入参方式的话，这个注解一定要有，一定要对应 xxxmapping.xml
    List<Customers> selectByCityAndContactfirstname(@Param("city") String city, @Param("contactfirstname") String contactfirstname);


    void updateByPriKey(Customers customers);


    void deleteByPriKey(@Param("ids") Set<Integer> ids);








}