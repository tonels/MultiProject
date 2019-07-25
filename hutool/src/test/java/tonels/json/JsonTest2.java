package tonels.json;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.junit.Assert;
import org.junit.Test;
import tonels.json.test.bean.Book1;
import tonels.json.test.bean.Book2;

import java.util.HashMap;
import java.util.Map;

public class JsonTest2 {

    @Test
    public void AtoB() {
        Book1 b1 = new Book1().setBookId("1").setBookName("pingfan").setBookAuthor("liuyao");

        Book2 book2 = JSONUtil.toBean(JSONUtil.toJsonStr(b1), Book2.class);

        System.out.println(b1);
        System.out.println(book2);

        // Book1(bookName=pingfan, bookAuthor=liuyao, bookId=1)
        // Book2(book_name=null, book_author=null, book_id=null)

    }
 // 注意区别
    @Test
    public void BtoA() {
        Book2 b2 = new Book2().setBook_id("1").setBook_name("pingfan").setBook_author("liuyao");

        Book1 b1 = JSONUtil.toBean(JSONUtil.toJsonStr(b2), Book1.class);

        System.out.println(b2);
        System.out.println(b1);

        // Book2(book_name=pingfan, book_author=liuyao, book_id=1)
        // Book1(bookName=pingfan, bookAuthor=liuyao, bookId=1)
    }





}
