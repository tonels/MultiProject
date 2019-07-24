package mbDemo2.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import mbDemo2.enti.Products;
import mbDemo2.mapper.ProductsMapper;
import mbDemo2.service.IProductsService;
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
public class ProductsServiceImpl extends ServiceImpl<ProductsMapper, Products> implements IProductsService {

}
