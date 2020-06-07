package samples.util;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ListUtils {

    public static <T> List<T> distinctList(List<T> list, Function<? super T, ?>... keyExtractors) {
        return list.stream()
                .filter(distinctByKeys(keyExtractors))
                .collect(Collectors.toList());
    }

    private static <T> Predicate<T> distinctByKeys(Function<? super T, ?>... keyExtractors) {
        final Map<List<?>, Boolean> seen = new ConcurrentHashMap<>();
        return new Predicate<T>() {
            @Override
            public boolean test(T t) {
                final List<?> keys = Arrays.stream(keyExtractors)
                        .map(ke -> ke.apply(t))
                        .collect(Collectors.toList());
                return seen.putIfAbsent(keys, Boolean.TRUE) == null;
            }
        };
    }
}
