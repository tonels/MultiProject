package tonels.mbdemo3.dao;

import tonels.mbdemo3.entity.Employees;

public interface EmployeesMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table employees
     *
     * @mbg.generated Tue Jul 23 15:05:27 CST 2019
     */
    int deleteByPrimaryKey(Integer employeenumber);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table employees
     *
     * @mbg.generated Tue Jul 23 15:05:27 CST 2019
     */
    int insert(Employees record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table employees
     *
     * @mbg.generated Tue Jul 23 15:05:27 CST 2019
     */
    int insertSelective(Employees record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table employees
     *
     * @mbg.generated Tue Jul 23 15:05:27 CST 2019
     */
    Employees selectByPrimaryKey(Integer employeenumber);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table employees
     *
     * @mbg.generated Tue Jul 23 15:05:27 CST 2019
     */
    int updateByPrimaryKeySelective(Employees record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table employees
     *
     * @mbg.generated Tue Jul 23 15:05:27 CST 2019
     */
    int updateByPrimaryKey(Employees record);
}