package tets;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.google.common.base.Splitter;
import com.google.common.collect.Maps;
import com.tonels.AsyncHandlerInterceptor.model.Book;
import com.tonels.AsyncHandlerInterceptor.model.User;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = DkDemo1.class)
public class TestMap2 {

    @Test
    public void t1(){

//         TODO: 2019/7/29   POJO 和 map之间的 互转示例
        Book book = new Book().setId("1").setName("霍乱").setAuthor("马尔克斯").setPrice("12");
        System.out.println("book    " + book);
        // book Book(id=1, name=霍乱, author=马尔克斯, price=12)

        String s = JSONUtil.toJsonStr(book);
        System.out.println("book to str " + s);
        // book to str {"author":"马尔克斯","price":"12","name":"霍乱","id":"1"}

        HashMap<String, String> hashMap = Maps.newHashMap();
        hashMap.put("id","1");
        hashMap.put("name","huoluan");
        hashMap.put("author","amerkesi");
        hashMap.put("price","12");
        System.out.println("hashMap " + hashMap);
        // hashMap {author=amerkesi, price=12, name=huoluan, id=1}

        Book book1 = JSONUtil.toBean(JSONUtil.toJsonStr(hashMap), Book.class);
        System.out.println("hashmap to bean   "+ book1);
        //hashmap to bean   Book(id=1, name=huoluan, author=amerkesi, price=12)


//        Map<String, String> map = MapUtil.createMap(Book.class,);
//        System.out.println(map);

//       示例一  pojo to Map

        Map<String, String> pojoToMap = Splitter.on(",").withKeyValueSeparator(":").split(JSONUtil.toJsonStr(book));
        System.out.println(pojoToMap);

        // {{"author"="马尔克斯", "price"="12", "name"="霍乱", "id"="1"}}

//       示例二  pojo to Map
        final String s2 = JSONUtil.toJsonStr(book);
        HashMap<String, String> toMap2 = (HashMap<String, String>) Arrays.asList(s2.split(",")).stream().map(as -> as.split(":")).collect(Collectors.toMap(e -> e[0], e ->e[1]));

        System.out.println(toMap2);

        // {{"author"="马尔克斯", "price"="12", "name"="霍乱", "id"="1"}}

    }

    @Test
    public void pojo1(){
        Book book = new Book().setId("1").setName("霍乱").setAuthor("马尔克斯").setPrice("12");
        String s = JSONUtil.toJsonStr(book);
        String replace = s.replace(":", "=");
        Map<String, String> pojoToMap = Splitter.on(",").withKeyValueSeparator(":").split(s);

        System.out.println(s);
        System.out.println(replace);
        System.out.println(pojoToMap);
    }



    @Test
    public void S(){
        Map map = new HashMap();
        User user = new User(23, "king");
        map.put("age",25);
        map.put("name","梁帅");
        String mapResult = HttpUtil.post("http://localhost:1210/filter/map", map);
        String bodyResult = HttpUtil.post("http://localhost:1210/filter/body", map);

        System.out.println(mapResult);
        System.out.println(bodyResult);


    }


}

