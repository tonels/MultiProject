package samples.misc;

import org.junit.Test;

import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 */
public class String1 {

    /**
     * 字符除重
     * :abfor
     */
    @Test
    public void testChars() {
        String string = "foobar:foo:bar"
                .chars()
                .distinct()
                .mapToObj(c -> String.valueOf((char) c))
                .sorted()
                .collect(Collectors.joining());
        System.out.println(string);
    }

    /**
     * 字符串过滤操作
     * bar:foobar
     */
    @Test
    public void testPatternSplit() {
        String string = Pattern.compile(":")
                .splitAsStream("foobar:foo:bar")
                .filter(s -> s.contains("bar"))
                .sorted()
                .map(String::toUpperCase)
                .collect(Collectors.joining(":"));
        System.out.println(string);
    }

    /**
     * 从 Stream<String> 过滤，词频统计
     */
    @Test
    public void testPatternPredicate() {
        long count = Stream.of("bob@gmail.com", "alice@hotmail.com")
                .filter(Pattern.compile(".*@gmail\\.com").asPredicate())
                .count();
        System.out.println(count);
    }

    /**
     * 字符串拼接
     * foobar:foo:bar
     */
    @Test
    public void testJoin() {
        String string = String.join(":", "foobar", "foo", "bar");
        System.out.println(string);
    }
}
