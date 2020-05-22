package samples.lambda;

import com.redisson.RunRedisson2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RunRedisson2.class)
public class RadissonTest {

    @Autowired
    private RedissonClient redissonClient;


    @Test
    public void t1() {
        System.out.println("ss");
    }

//
//    @Test
//    public void web1() {
//        RKeys keys = redissonClient.getKeys();
//        Iterable<String> keys1 = keys.getKeys();
//        keys1.forEach(System.out::println);
//        System.out.println(keys.toString());
//    }


}
