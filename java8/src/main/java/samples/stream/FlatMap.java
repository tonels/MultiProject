package samples.stream;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.Before;
import org.junit.Test;
import samples.lambda.Person2;

import java.util.HashMap;
import java.util.List;

public class FlatMap {

    private List<Person2> list;
    private List<String> strings;

    // 初始化数据需要的
    @Before
    public void inti() {
        Person2 p1 = new Person2(13, "男", "张2");
        Person2 p2 = new Person2(2, "男", "张6");
        Person2 p3 = new Person2(4, "男", "张1");
        Person2 p4 = new Person2(6, "女", "张3");
        Person2 p5 = new Person2(1, "男", "张5");
        Person2 p6 = new Person2(10, "男", "张8");
        Person2 p7 = new Person2(3, "女", "张4");
        Person2 p8 = new Person2(31, "女", "张4");
        Person2 p9 = new Person2(21, "女", "张4");
        Person2 p10 = new Person2(19, "女", "张4");

        list = Lists.newArrayList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10);
        strings = Lists.newArrayList("a1", "a2", "b1", "c2", "c1");
    }


    @Test
    public void t1(){
//        list.stream().flatMap()






    }

    @Test
    public void t2(){
        HashMap<Object, Object> maps = Maps.newHashMap();
//        maps.stream()

    }



}
