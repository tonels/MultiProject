package mbDemo2.service.impl;

import mbDemo2.enti.Productlines;
import mbDemo2.mapper.ProductlinesMapper;
import mbDemo2.service.IProductlinesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tonels
 * @since 2019-07-23
 */
@Service
public class ProductlinesServiceImpl extends ServiceImpl<ProductlinesMapper, Productlines> implements IProductlinesService {

}
