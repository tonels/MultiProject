package mbDemo2.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import mbDemo2.enti.Payments;
import mbDemo2.mapper.PaymentsMapper;
import mbDemo2.service.IPaymentsService;
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
public class PaymentsServiceImpl extends ServiceImpl<PaymentsMapper, Payments> implements IPaymentsService {

}
