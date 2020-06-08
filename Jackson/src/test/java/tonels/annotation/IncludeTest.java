package tonels.annotation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Tests to verify that it is possibly to merge {@link JsonInclude.Value}
 * instances for overrides
 */
public class IncludeTest extends TestBase {
    private final JsonInclude.Value EMPTY = JsonInclude.Value.empty();

    @JsonInclude(value = Include.NON_EMPTY, content = Include.NON_DEFAULT)
    private final static class Bogus {
    }

    @JsonInclude(value = Include.CUSTOM, valueFilter = Integer.class,
            content = Include.CUSTOM, contentFilter = Long.class)
    private final static class Custom {
    }

    public void testEquality() {
        assertTrue(EMPTY.equals(EMPTY));

        JsonInclude.Value v1 = JsonInclude.Value.construct(Include.NON_ABSENT, null);
        JsonInclude.Value v2 = JsonInclude.Value.construct(Include.NON_ABSENT, null);
        JsonInclude.Value v3 = JsonInclude.Value.construct(Include.NON_ABSENT, Include.NON_EMPTY);

        assertTrue(v1.equals(v2));
        assertTrue(v2.equals(v1));

        assertFalse(v1.equals(v3));
        assertFalse(v3.equals(v1));
        assertFalse(v2.equals(v3));
        assertFalse(v3.equals(v2));
    }

    public void testFromAnnotation() {
        JsonInclude ann = Bogus.class.getAnnotation(JsonInclude.class);
        JsonInclude.Value v = JsonInclude.Value.from(ann);
        assertEquals(Include.NON_EMPTY, v.getValueInclusion());
        assertEquals(Include.NON_DEFAULT, v.getContentInclusion());
    }

    public void testFromAnnotationWithCustom() {
        JsonInclude ann = Custom.class.getAnnotation(JsonInclude.class);
        JsonInclude.Value v = JsonInclude.Value.from(ann);
        assertEquals(Include.CUSTOM, v.getValueInclusion());
        assertEquals(Include.CUSTOM, v.getContentInclusion());
        assertEquals(Integer.class, v.getValueFilter());
        assertEquals(Long.class, v.getContentFilter());

        assertEquals(
                "JsonInclude.Value(value=CUSTOM,content=CUSTOM,valueFilter=java.lang.Integer.class,contentFilter=java.lang.Long.class)",
                v.toString());
    }

    public void testStdOverrides() {
        assertEquals("JsonInclude.Value(value=NON_ABSENT,content=USE_DEFAULTS)",
                JsonInclude.Value.construct(Include.NON_ABSENT, null).toString());
        int x = EMPTY.hashCode();
        if (x == 0) {
            fail();
        }
        assertFalse(EMPTY.equals(null));
        assertFalse(EMPTY.equals(""));
    }

//    public void testSimpleMerge() {
//        JsonInclude.Value empty = JsonInclude.Value.empty();
//
//        assertEquals(Include.USE_DEFAULTS, empty.getValueInclusion());
//        assertEquals(Include.USE_DEFAULTS, empty.getContentInclusion());
//
//        JsonInclude.Value v2 = empty.withValueInclusion(Include.NON_ABSENT);
//
//        assertEquals(Include.NON_ABSENT, v2.getValueInclusion());
//        assertEquals(Include.USE_DEFAULTS, v2.getContentInclusion());
//
//        JsonInclude.Value v3 = new JsonInclude.Value(Include.NON_EMPTY, Include.ALWAYS,null, null);
//        assertEquals(Include.NON_EMPTY, v3.getValueInclusion());
//        assertEquals(Include.ALWAYS, v3.getContentInclusion());
//
//        // Ok; but then overrides, which should skip 'USE_DEFAULT' overrides
//        JsonInclude.Value merged = v3.withOverrides(empty);
//        // no overrides with "empty":
//        assertEquals(v3.getValueInclusion(), merged.getValueInclusion());
//        assertEquals(v3.getContentInclusion(), merged.getContentInclusion());
//
//        // but other values ought to be overridden (value, yes, content, no because it's default)
//        merged = JsonInclude.Value.merge(v3, v2);
//        assertEquals(v2.getValueInclusion(), merged.getValueInclusion());
//        assertEquals(v3.getContentInclusion(), merged.getContentInclusion());
//
//        merged = JsonInclude.Value.mergeAll(empty, v3);
//        assertEquals(v3.getValueInclusion(), merged.getValueInclusion());
//        assertEquals(v3.getContentInclusion(), merged.getContentInclusion());
//    }

    // for [annotations#76]
    public void testContentMerge76() {
        JsonInclude.Value v1 = JsonInclude.Value.empty()
                .withContentInclusion(Include.ALWAYS)
                .withValueInclusion(Include.NON_ABSENT);
        JsonInclude.Value v2 = JsonInclude.Value.empty()
                .withContentInclusion(Include.NON_EMPTY)
                .withValueInclusion(Include.USE_DEFAULTS);

        JsonInclude.Value v12 = v2.withOverrides(v1); // v1 priority
        JsonInclude.Value v21 = v1.withOverrides(v2); // v2 priority

        assertEquals(Include.ALWAYS, v12.getContentInclusion());
        assertEquals(Include.NON_ABSENT, v12.getValueInclusion());

        assertEquals(Include.NON_EMPTY, v21.getContentInclusion());
        assertEquals(Include.NON_ABSENT, v21.getValueInclusion());
    }

    public void testFilters() {
        JsonInclude.Value empty = JsonInclude.Value.empty();
        assertNull(empty.getValueFilter());
        assertNull(empty.getContentFilter());

        // note: filter class choices are arbitrary, just confirming assingments
        JsonInclude.Value v1 = empty.withValueFilter(String.class);
        assertEquals(Include.CUSTOM, v1.getValueInclusion());
        assertEquals(String.class, v1.getValueFilter());
        assertNull(v1.withValueFilter(null).getValueFilter());
        assertNull(v1.withValueFilter(Void.class).getValueFilter());

        JsonInclude.Value v2 = empty.withContentFilter(Long.class);
        assertEquals(Include.CUSTOM, v2.getContentInclusion());
        assertEquals(Long.class, v2.getContentFilter());
        assertNull(v2.withContentFilter(null).getContentFilter());
        assertNull(v2.withContentFilter(Void.class).getContentFilter());
    }
}
