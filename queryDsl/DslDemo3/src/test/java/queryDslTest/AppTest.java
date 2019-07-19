package queryDslTest;

import com.google.common.collect.Lists;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Visitor;
import demo3.Demo3Run;
import demo3.repo.CustomerRepo;
import demo3.repo.customize.CustoRepoCustomize;
import demo3.vo.OffEmpCustParamsVo;
import demo3.vo.OffEmpCustVo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Nullable;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Demo3Run.class)
public class AppTest {

//    @Resource
//    private CustomerRepo customerRepo;
    @PersistenceContext
    private EntityManager em;

    @Resource
    private CustoRepoCustomize customerRepo;

    private List<OffEmpCustParamsVo> list;

    @Before
    public void inti(){
        OffEmpCustParamsVo o1 = new OffEmpCustParamsVo("a", 100, "ca");

        OffEmpCustParamsVo o2 = new OffEmpCustParamsVo("a", 100, "");

        list = Lists.newArrayList(o1, o2);
    }


    @Test
    // select .. from Person p where p.firstname=?
    public void t1(){
        Predicate predicate = new Predicate() {
            @Nullable
            @Override
            public <R, C> R accept(Visitor<R, C> visitor, @Nullable C c) {
                return null;
            }

            @Override
            public Class<? extends Boolean> getType() {
                return null;
            }

            @Override
            public Predicate not() {
                return null;
            }
        };
        List<OffEmpCustVo> offEmpCustVoList = customerRepo.findOffEmpCust(predicate);
        System.out.println(offEmpCustVoList);
    }

    @Test
    public void t2(){
        List<OffEmpCustVo> offEmpCust = customerRepo.findOffEmpCust();
        System.out.println(offEmpCust);
    }



}