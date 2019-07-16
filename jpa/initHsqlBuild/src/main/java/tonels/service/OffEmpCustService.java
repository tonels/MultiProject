package tonels.service;

import tonels.utils.PageParam;
import tonels.vo.OffEmpCustParamsVo;
import tonels.vo.OffEmpCustVo;
import utils.PageBean;

public interface OffEmpCustService {

    PageBean<OffEmpCustVo> findAll(OffEmpCustParamsVo param, PageParam pageParam); // 动态条件拼接
}
