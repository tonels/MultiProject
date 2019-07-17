package querydsl.service;

import querydsl.utils.PageParam;
import querydsl.vo.OffEmpCustParamsVo;
import querydsl.vo.OffEmpCustVo;

import java.util.List;

public interface OffEmpCustService {

    List<OffEmpCustVo> findAll(OffEmpCustParamsVo param, PageParam pageParam); // 动态条件拼接

}
