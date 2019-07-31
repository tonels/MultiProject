package tets;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.google.common.collect.Maps;
import com.tonels.webDemo2.model.User;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class BeanMap {

    /**
     * 测试BeanToMap
      */

    @Test
    public void BtoM(){
        User user = new User(1, "zhsan");

        String toJsonStr = JSONUtil.toJsonStr(user);
        JSON parse = JSONUtil.parse(user);
        JSONObject jsonObject = JSONUtil.parseObj(user);

        System.out.println(toJsonStr);
        System.out.println(parse);
        System.out.println(jsonObject);

        System.out.println("map格式数据。。。。");
        HashMap<String, Object> hashmap = Maps.newHashMap();
        hashmap.put("age",1);
        hashmap.put("name","zhsan");
        System.out.println(hashmap);

        String s = JSONUtil.toJsonStr(hashmap);
        System.out.println(s);

        /** 首先查看 Map 和 Bean 的数据格式
         * {"name":"zhsan","age":1}
         * {"name":"zhsan","age":1}
         * {"name":"zhsan","age":1}
         * map格式数据。。。。
         * {name=zhsan, age=1}
         * {"name":"zhsan","age":1}
         */

    }

    @Test
    public void BtoM2(){
        User user = new User(1, "zhsan");
        Map<String, Object> hashmap = Maps.newHashMap();
        BeanUtil.copyProperties(user, hashmap);
        System.out.println(hashmap);
        System.out.println(JSONUtil.toJsonStr(hashmap));
    }

    /**
     *  { name=zhsan, age=1}
     *  {"name":"zhsan","age":1}
     */







}
