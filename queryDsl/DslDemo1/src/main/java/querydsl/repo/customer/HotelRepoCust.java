package querydsl.repo.customer;

import com.querydsl.core.Tuple;

import java.util.List;

/**
 * 自定义储存库的引入方式
 */
public interface HotelRepoCust {

    // 自定义返回
    public List<Tuple> findcityHotel();

}
