import demo3.MongoApp3;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MongoApp3.class)
public class Mongo3Test {

    @Test
    public void contextLoads(){
        System.out.println("s");
    }

}
