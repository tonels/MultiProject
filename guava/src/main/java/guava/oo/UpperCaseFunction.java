package guava.oo;

import com.google.common.base.Function;

public class UpperCaseFunction<F, T> implements Function<F, T> {
    public Object apply(Object f) {
        return f.toString().toUpperCase();
    }
}