package dockerDemo1.controller;

import com.google.common.collect.Lists;
import dockerDemo1.model.Book;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class DkController {

    private static List<Book> books = null;

    static {
        Book b1 = new Book().setId("1").setName("霍乱").setAuthor("马尔克斯").setPrice("12");
        Book b2 = new Book().setId("2").setName("百年孤独").setAuthor("马尔克斯").setPrice("12");
        Book b3 = new Book().setId("3").setName("少年闰土").setAuthor("鲁迅").setPrice("12");
        Book b4 = new Book().setId("4").setName("三味书屋").setAuthor("鲁迅").setPrice("12");

        books = Lists.newArrayList(b1, b2, b3, b4);
    }

    @GetMapping("/book")
    public Book test() {
        Book book = new Book().setId("1").setName("霍乱").setAuthor("马尔克斯").setPrice("12");
        return book;
    }

    @GetMapping("/list")
    public List<Book> testAll() {
        return books;
    }



}
