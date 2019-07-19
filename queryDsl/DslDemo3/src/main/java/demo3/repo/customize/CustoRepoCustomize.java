package demo3.repo.customize;

import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Predicate;
import demo3.vo.OffEmpCustVo;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustoRepoCustomize {


    /**
     * 关联查询示例,查询出城市和对应的旅店
     * @param predicate 查询条件
     * @return 查询实体
     */
    public List<Tuple> findCustPay(Predicate predicate);

    /**
     * 关联查询示例,查询出城市和对应的旅店
     * @param predicate 查询条件
     * @return 查询实体
     */
    public QueryResults<Tuple> findCustOrder(Predicate predicate, Pageable pageable);

    // 自定义返回
    public List<OffEmpCustVo> findOffEmpCust(Predicate predicate);

    // 自定义返回
    public List<OffEmpCustVo> findOffEmpCust();

}
