package tonels.mbdemo3.dao;

import tonels.mbdemo3.entity.Products;

public interface ProductsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table products
     *
     * @mbg.generated Tue Jul 23 15:05:27 CST 2019
     */
    int deleteByPrimaryKey(String productcode);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table products
     *
     * @mbg.generated Tue Jul 23 15:05:27 CST 2019
     */
    int insert(Products record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table products
     *
     * @mbg.generated Tue Jul 23 15:05:27 CST 2019
     */
    int insertSelective(Products record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table products
     *
     * @mbg.generated Tue Jul 23 15:05:27 CST 2019
     */
    Products selectByPrimaryKey(String productcode);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table products
     *
     * @mbg.generated Tue Jul 23 15:05:27 CST 2019
     */
    int updateByPrimaryKeySelective(Products record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table products
     *
     * @mbg.generated Tue Jul 23 15:05:27 CST 2019
     */
    int updateByPrimaryKeyWithBLOBs(Products record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table products
     *
     * @mbg.generated Tue Jul 23 15:05:27 CST 2019
     */
    int updateByPrimaryKey(Products record);
}