package tets;

import cn.hutool.http.HttpUtil;
import org.junit.Test;

public class HttpTest {

    private static String url = "localhost:1210";
    private static String get1 = "get1"; // 无参
    private static String get2 = "get2"; // 有参
    private static String post1 = "post1";// 无参
    private static String post2 = "post2"; // 有参


    @Test
    public void Http1(){

        String s = HttpUtil.get(url + get1);


    }


}
