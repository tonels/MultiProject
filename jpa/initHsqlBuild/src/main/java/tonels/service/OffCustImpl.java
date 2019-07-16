package tonels.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tonels.dao.OfficeEmploCustDao;
import tonels.utils.PageParam;
import tonels.vo.OffEmpCustParamsVo;
import tonels.vo.OffEmpCustVo;
import utils.PageBean;

import javax.annotation.Resource;

@Service
@Transactional
public class OffCustImpl implements OffEmpCustService {

    @Resource
    private OfficeEmploCustDao dao;

    @Override
    public PageBean<OffEmpCustVo> findAll(OffEmpCustParamsVo param, PageParam pageParam) {

        return dao.list(param,pageParam);
    }
}
