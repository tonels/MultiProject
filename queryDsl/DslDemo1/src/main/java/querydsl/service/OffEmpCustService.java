package querydsl.service;

import querydsl.utils.PageParam;
import querydsl.vo.CityHotelVo;
import querydsl.vo.OffEmpCustVo;

import java.util.List;

public interface OffEmpCustService {

    List<OffEmpCustVo> findAll(CityHotelVo param, PageParam pageParam); // 动态条件拼接

}
