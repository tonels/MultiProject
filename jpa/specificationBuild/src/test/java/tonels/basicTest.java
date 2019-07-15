package tonels;

import cn.hutool.core.util.StrUtil;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class basicTest {

    @Test
    public void d(){
        String a = "";
        Object b = null;
        System.out.println(StrUtil.isBlank(a));
        System.out.println(StrUtil.isEmpty(a));

        System.out.println(StrUtil.isEmpty(a));
    }
}
