package demo3.repo.customize;

import com.querydsl.core.types.Predicate;
import demo3.vo.OffEmpCustVo;

import java.util.List;

public interface CustomerRepoCustom {

    // 自定义返回
    public List<OffEmpCustVo> findOffEmpCust();

    public List<OffEmpCustVo> findOffEmpCust(Predicate predicate);

}
