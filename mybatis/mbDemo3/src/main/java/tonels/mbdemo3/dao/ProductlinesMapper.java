package tonels.mbdemo3.dao;

import tonels.mbdemo3.entity.Productlines;
import tonels.mbdemo3.entity.ProductlinesWithBLOBs;

public interface ProductlinesMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table productlines
     *
     * @mbg.generated Tue Jul 23 15:05:27 CST 2019
     */
    int deleteByPrimaryKey(String productline);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table productlines
     *
     * @mbg.generated Tue Jul 23 15:05:27 CST 2019
     */
    int insert(ProductlinesWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table productlines
     *
     * @mbg.generated Tue Jul 23 15:05:27 CST 2019
     */
    int insertSelective(ProductlinesWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table productlines
     *
     * @mbg.generated Tue Jul 23 15:05:27 CST 2019
     */
    ProductlinesWithBLOBs selectByPrimaryKey(String productline);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table productlines
     *
     * @mbg.generated Tue Jul 23 15:05:27 CST 2019
     */
    int updateByPrimaryKeySelective(ProductlinesWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table productlines
     *
     * @mbg.generated Tue Jul 23 15:05:27 CST 2019
     */
    int updateByPrimaryKeyWithBLOBs(ProductlinesWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table productlines
     *
     * @mbg.generated Tue Jul 23 15:05:27 CST 2019
     */
    int updateByPrimaryKey(Productlines record);
}