package demo.collections;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

import static org.testng.AssertJUnit.assertEquals;

@Test
public class ImmutableCollectionsTest {


    // 自动同步，复制
    public void shouldProveUnmodifiableListIsAView() {
        final List<String> baseList = Lists.newArrayList("A", "B", "C");
        final List<String> unmodifiableList = Collections.unmodifiableList(baseList);

        assertEquals(3, baseList.size());
        assertEquals(3, unmodifiableList.size());

        baseList.add("D");

        assertEquals(4, baseList.size());
        assertEquals(4, unmodifiableList.size());
    }

    public void shouldProveImmutableListIsADifferentList() {
        final List<String> baseList = Lists.newArrayList("A", "B", "C");
        final List<String> unmodifiableList = ImmutableList.copyOf(baseList);

        assertEquals(3, baseList.size());
        assertEquals(3, unmodifiableList.size());

        baseList.add("D");

        assertEquals(4, baseList.size());
        assertEquals(3, unmodifiableList.size());
    }

    public void shouldBuildImmutableList() {
        final List<String> immutableList = ImmutableList.of("A", "B", "C", "D");
//        immutableList.add("E"); // 不可变集合，强行添加会报错
        assertEquals(4, immutableList.size());
    }

    // 这里可实现集合的叠加
    public void shouldBuildImmutableListWithBuilder() {
        final List<String> list1 = Lists.newArrayList("A", "B");
        final List<String> list2 = Lists.newArrayList("C", "D");
        final List<String> list3 = Lists.newArrayList("E", "F");

        final List<String> immutableList = ImmutableList.<String>builder()
                .addAll(list1)
                .addAll(list2)
                .addAll(list3)
                .add("X", "Y", "Z")
                .build();
        assertEquals(9, immutableList.size());
    }

    // 自定义复杂 Map操作
    public void shouldBuildImmutableMultimapWithBuilder() {
        Multimap<String, Integer> immutableMultimap = ImmutableMultimap.<String, Integer>builder()
                .put("A", 1)
                .putAll("B", 1, 2, 3, 4)
                .putAll("C", 10, 15, 20)
                .build();

        assertEquals(1, immutableMultimap.get("A").size());
        assertEquals(4, immutableMultimap.get("B").size());
        assertEquals(3, immutableMultimap.get("C").size());
    }
}
