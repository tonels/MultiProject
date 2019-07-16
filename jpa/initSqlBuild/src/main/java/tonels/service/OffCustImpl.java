package tonels.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tonels.dao.OfficeEmploCustDao2;
import tonels.utils.PageParam;
import tonels.vo.OffEmpCustParamsVo;
import tonels.vo.OffEmpCustVo;
import utils.PageBean;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class OffCustImpl implements OffEmpCustService {

    @Resource
    private OfficeEmploCustDao2 dao;

    @Override
    public List<OffEmpCustVo> findAll(OffEmpCustParamsVo param, PageParam pageParam) {

        return dao.list(param,pageParam);
    }
}
