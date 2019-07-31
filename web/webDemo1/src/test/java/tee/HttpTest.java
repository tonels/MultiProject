package tee;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import demo1.model.User;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class HttpTest {

    private static String url = "http://192.168.217.225:1210/http";
    private static String get1 = "/get1"; // 无参
    private static String get2 = "/get2"; // 有参
    private static String post1 = "/post1";// 无参
    private static String post2 = "/post2"; // 有参

//    @Resource
//    private RestTemplate restTemplate;

   // get 无参调用http接口
    @Test
    public void get1() throws URISyntaxException {

        RestTemplate restTemplate = new RestTemplate(); // 这里为什么 @resource引入 会出现空指针？
        URI uri = new URI(url+get1);
        ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
        System.out.println(result.getBody());
    }

    // get 有参调用http接口
    @Test
    public void get2() throws URISyntaxException {

        RestTemplate restTemplate = new RestTemplate(); // 这里为什么 @resource引入 会出现空指针？

        HashMap<String, String> map = new HashMap<>();
        map.put("age","12");
        map.put("name","lo");

        ResponseEntity<String> forEntity = restTemplate.getForEntity(url + get2, String.class, map);
        System.out.println(forEntity.getBody());
        // 这里应该返回 get2 ..lo,
        // 实际返回 get2...null

    }



    @Test
    public void get21() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        User user = new User(1, "op");
        HttpEntity<User> entity = new HttpEntity<>(user);
        ResponseEntity<String> post = restTemplate.getForEntity(url+get2,String.class,entity);
        System.out.println(post.getBody());
        // 这里应该返回 get2 ..lo,
        // 实际返回 get2...null
    }

    @Test
    public void get22() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> post = restTemplate.getForEntity(url+get2+"?name=op",String.class);
        System.out.println(post.getBody());
        // 可以把参数拼接在路径中，解决上面的问题
    }



    @Test
    public void post1() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        URI uri = new URI(url+ post1);

        User user = new User(1, "os");
        ResponseEntity<String> result = restTemplate.postForEntity(uri, user, String.class);
        String body = result.getBody();
        System.out.println(body);
    }

    @Test
    public void post2() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        URI uri = new URI(url+ post2);

        User user = new User(1, "os");
        ResponseEntity<String> result = restTemplate.postForEntity(uri, user, String.class);
        String body = result.getBody();
        System.out.println(body);
    }

    @Test
    public void httpUtil1(){

        User user = new User(23, "梁帅真的帅");

        Map map = new HashMap();
        map.put("age",25);
        map.put("name","ss");

        String body = JSONUtil.toJsonStr(user);

        String bodyResult = HttpUtil.post(url + post2, body);
        System.out.println(bodyResult);

    }

    @Test
    public void httpUtil2(){

    }



}
