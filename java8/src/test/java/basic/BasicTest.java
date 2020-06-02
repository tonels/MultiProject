package basic;

import cn.hutool.core.util.StrUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class BasicTest {


    @Test
    public void t2() {
        Map<String, Object> a = new HashMap<String, Object>() {{
            put("area_id", 100);
            put("area_Name", null);
        }};
        Map<String, Object> b = new HashMap<>();
        a.forEach((k,v) -> b.put(StrUtil.toCamelCase(k), v));
        System.out.println(b);
    }

}
