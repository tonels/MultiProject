package tonels;

import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tonels.dao.OfficeEmploCustDao;
import tonels.utils.PageParam;
import tonels.vo.OffEmpCustParamsVo;
import tonels.vo.OffEmpCustVo;
import utils.PageBean;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HsqlAppRun.class)
public class AppTest {
    @Resource
    private OfficeEmploCustDao dao;

    private List<OffEmpCustParamsVo> list;
    private PageParam p1;

    @Before
    public void inti(){
        OffEmpCustParamsVo o1 = new OffEmpCustParamsVo("a", 100, "ca");

        OffEmpCustParamsVo o2 = new OffEmpCustParamsVo("a", 100, "");

        list = Lists.newArrayList(o1, o2);

        p1 = new PageParam().setPage(1).setRows(10);
    }

    @Test
    public void t1(){
        PageBean<OffEmpCustVo> page = dao.list(list.get(0), p1);
        System.out.println(page.getTotalPage());
        System.out.println(page.getTotalCount());
        System.out.println(page.getResult());
        // 三个条件
        // select vo from .. where 1=1 and (oc.state like ?) and em.employeeNumber>=? and cu.state=? order by em.employeeNumber desc limit ?
        // select count(*) from .. where 1=1 and (oc.state like ?) and em.employeeNumber>=? and cu.state=? order by em.employeeNumber desc limit ?


    }

    @Test
    public void t2(){
        PageBean<OffEmpCustVo> page = dao.list(list.get(1), p1);
        System.out.println(page.getTotalPage());
        System.out.println(page.getTotalCount());
        System.out.println(page.getResult());

        // 两个条件
        // select vo from .. where 1=1 and (oc.state like ?) and em.employeeNumber>=? order by em.employeeNumber desc limit ?
        // select count(*) from .. where 1=1 and (oc.state like ?) and em.employeeNumber>=? order by em.employeeNumber desc limit ?
    }

}
