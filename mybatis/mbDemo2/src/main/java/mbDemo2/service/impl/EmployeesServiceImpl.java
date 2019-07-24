package mbDemo2.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import mbDemo2.enti.Employees;
import mbDemo2.mapper.EmployeesMapper;
import mbDemo2.service.IEmployeesService;
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
public class EmployeesServiceImpl extends ServiceImpl<EmployeesMapper, Employees> implements IEmployeesService {

}
