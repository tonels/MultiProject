package querydsl.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import querydsl.utils.PageParam;
import querydsl.vo.CityHotelVo;
import querydsl.vo.OffEmpCustVo;

import java.util.List;

@Service
@Transactional
public class OffCustImpl implements OffEmpCustService {

    @Override
    public List<OffEmpCustVo> findAll(CityHotelVo param, PageParam pageParam) {

        return null;
    }
}
