package demo.strings;

import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Map;

import static org.testng.AssertJUnit.assertEquals;

/**
 * User: gunters
 * Created: 05/12/2011 08:53
 */
@Test
public class SplitterTest {

    public void shouldSplitIntoList() {
        final String input = "first,second,third,fourth,,,seventh";

        ArrayList<String> strings = Lists.newArrayList("first", "second", "third", "fourth", "", "", "seventh");

        ArrayList<String> strings1 = Lists.newArrayList(Splitter.on(",").split(input));
        ArrayList<String> strings2 = Lists.newArrayList(Splitter.on(",").omitEmptyStrings().split(input));
        ArrayList<String> strings3 = Lists.newArrayList(Splitter.on(",").trimResults().split(input));
    }

    public void shouldSplitIntoMap() {
        final String input = "key1=value1&key2=value2";

        ImmutableMap<String, String> of = ImmutableMap.of("key1", "value1", "key2", "value2");
        Map<String, String> split = Splitter.on("&").withKeyValueSeparator("=").split(input);
    }
}
