package demo.gson;

import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.FieldNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import demo.bean.BagOfPrimitives;
import demo.bean.SomeObject;
import demo.bean.UserNaming;
import demo.bean.VersionedClass;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.*;

public class JsonTest {

    /**
     * Primitives 序列化和反序列化
     * 1
     * "abcd"
     * 10
     * [1]
     */
    @Test
    public void t1() {
        // Serialization
        Gson gson = new Gson();
        System.out.println(gson.toJson(1));
        System.out.println(gson.toJson("abcd"));
        System.out.println(gson.toJson(new Long(10)));
        int[] values = {1};
        System.out.println(gson.toJson(values));

        // Deserialization
        int o1 = gson.fromJson("1", int.class);
        Integer o2 = gson.fromJson("1", Integer.class);
        Long o3 = gson.fromJson("1", Long.class);
        Boolean o4 = gson.fromJson("false", Boolean.class);
        String o5 = gson.fromJson("\"abc\"", String.class);
        String[] anotherStr = gson.fromJson("[\"abc\"]", String[].class);

    }

    /**
     * Object Example
     * {"value1":1,"value2":"abc"}
     */

    @Test
    public void t2() {
        // Serialization
        BagOfPrimitives obj = new BagOfPrimitives();
        Gson gson = new Gson();
        String json = gson.toJson(obj);
        System.out.println(json);

        // Deserialization
        BagOfPrimitives obj2 = gson.fromJson(json, BagOfPrimitives.class);
        System.out.println(obj2.toString());
    }

    /**
     * Gson可以很容易地序列化和反序列化静态嵌套类。
     * 但是，不能自动反序列化纯内部类，因为它们的无参数构造函数还需要一个对包含对象的引用，
     * 而在反序列化时这个引用是不可用的。可以通过使内部类为静态或为其提供自定义InstanceCreator来解决这个问题。这里有一个例子:
     */

//    public class InstanceCreatorForB implements InstanceCreator<NestedA.B> {
//        private final A a;
//        public InstanceCreatorForB(A a)  {
//            this.a = a;
//        }
//        public A.B createInstance(Type type) {
//            return a.new B();
//        }
//    }


    /**
     * Array Examples
     */
    @Test
    public void t3() {
        Gson gson = new Gson();
        int[] ints = {1, 2, 3, 4, 5};
        String[] strings = {"abc", "def", "ghi"};

// Serialization
        gson.toJson(ints);     // ==> [1,2,3,4,5]
        gson.toJson(strings);  // ==> ["abc", "def", "ghi"]

// Deserialization
        int[] ints2 = gson.fromJson("[1,2,3,4,5]", int[].class);

    }

    /**
     * Collections Examples
     */
    @Test
    public void t4() {
        Gson gson = new Gson();
        Collection<Integer> ints = Lists.newArrayList(1, 2, 3, 4, 5);

        // Serialization
        String json = gson.toJson(ints);  // ==> json is [1,2,3,4,5]

        // Deserialization
        Type collectionType = new TypeToken<Collection<Integer>>() {
        }.getType();
        Collection<Integer> ints2 = gson.fromJson(json, collectionType);
    }


    /**
     * 泛型的序列化和反序列化 Generic Types
     */
    class Foo<T> {
        T value;
    }

    class Bar {
        String value;
    }

    @Test
    public void t5() {
        Gson gson = new Gson();
        Foo<Bar> foo = new Foo<Bar>();

        // 错误示例
        String s = gson.toJson(foo);// 不是很正确
        gson.fromJson(s, foo.getClass()); // 失败

        // 以下是正确序列化/反序列化
        Type fooType = new TypeToken<Foo<Bar>>() {
        }.getType();
        String s1 = gson.toJson(foo, fooType);

        gson.fromJson(s1, fooType);

    }

    /**
     * 混合类型 集合，如 ['hello',5,{name:'GREETINGS',source:'guest'}]
     */
    class Event {
        private String name;
        private String source;

        private Event(String name, String source) {
            this.name = name;
            this.source = source;
        }
    }

    @Test
    public void t6() {
        Collection collection = new ArrayList();
        collection.add("hello");
        collection.add(5);
        collection.add(new Event("GREETINGS", "guest"));


    }

    /**
     * pretty style
     * <p>
     * {
     * "name": "GREETINGS",
     * "source": "guest"
     * }
     * {
     * "name": "GREETINGS"
     * }
     */
    @Test
    public void t7() {
        Event event = new Event("GREETINGS", "guest");
        Event event2 = new Event("GREETINGS", null);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonOutput = gson.toJson(event);
        String jsonOutput2 = gson.toJson(event2);

        System.out.println(jsonOutput);
        System.out.println(jsonOutput2);
    }

    /**
     * 空值 赋 null
     * {"name":"GREETINGS","source":null}
     */
    @Test
    public void t8() {
        Event event2 = new Event("GREETINGS", null);

        Gson gson = new GsonBuilder().serializeNulls().create();
        String jsonOutput2 = gson.toJson(event2);
        System.out.println(jsonOutput2);
    }

    /**
     * 版本控制
     * {"newField":"new","field":"old"}
     * <p>
     * {"newerField":"newer","newField":"new","field":"old"}
     */
    @Test
    public void t9() {

        Gson gson = new GsonBuilder().setVersion(1.0).create();

        VersionedClass versionedObject = new VersionedClass();

        String jsonOutput = gson.toJson(versionedObject);
        System.out.println(jsonOutput);
        System.out.println();

        gson = new Gson();
        jsonOutput = gson.toJson(versionedObject);
        System.out.println(jsonOutput);
    }

    /**
     * 自定义命名
     * {"custom_naming":"first","SomeOtherField":"second"}
     */
    @Test
    public void t10() {
        SomeObject someObject = new SomeObject("first", "second");
        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
        String jsonRepresentation = gson.toJson(someObject);
        System.out.println(jsonRepresentation);
    }

    /**
     * 时间序列化
     */
    @Test
    public void t11() {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();

        String date = "\"2013-02-10T13:45:30+0100\"";
        Date test = gson.fromJson(date, Date.class);
        System.out.println("date:" + test);
    }

    /**
     *
     */
    @Test
    public void t12() {
        Map<String, Object> a = new HashMap<String, Object>() {{
            put("area_id", 100);
            put("area_name", null);
        }};
        Map<String, Object> b = new HashMap<>();

        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();

        a.forEach((k, v) -> b.put(gson.toJson(k), v));
        System.out.println(b);
    }

    /**
     * 原序列
     * FieldNamingPolicy.IDENTITY 测试
     * {"Name":"Norman","email_of_developer":"norman@futurestud.io","isDeveloper":true,"_ageOfDeveloper":26}
     */
    @Test
    public void t13() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.IDENTITY);
        Gson gson = gsonBuilder.create();

        UserNaming user = new UserNaming("Norman", "norman@futurestud.io", true, 26);
        String usersJson = gson.toJson(user);

        System.out.println(usersJson);
    }

    /**
     * 下划线
     * FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES，测试
     * {"name":"Norman","email_of_developer":"norman@futurestud.io","is_developer":true,"_age_of_developer":26}
     */
    @Test
    public void t14() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        Gson gson = gsonBuilder.create();

        UserNaming user = new UserNaming("Norman", "norman@futurestud.io", true, 26);
        String usersJson = gson.toJson(user);

        System.out.println(usersJson);
    }

    /**
     * 驼峰中横线
     * FieldNamingPolicy.LOWER_CASE_WITH_DASHES，测试
     * {"name":"Norman","email_of_developer":"norman@futurestud.io","is-developer":true,"_age-of-developer":26}
     */
    @Test
    public void t15() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_DASHES);
        Gson gson = gsonBuilder.create();

        UserNaming user = new UserNaming("Norman", "norman@futurestud.io", true, 26);
        String usersJson = gson.toJson(user);

        System.out.println(usersJson);
    }

    /**
     * 驼峰首字母大写
     * FieldNamingPolicy.UPPER_CAMEL_CASE，测试
     * {"Name":"Norman","Email_of_developer":"norman@futurestud.io","IsDeveloper":true,"_AgeOfDeveloper":26}
     */
    @Test
    public void t16() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE);
        Gson gson = gsonBuilder.create();

        UserNaming user = new UserNaming("Norman", "norman@futurestud.io", true, 26);
        String usersJson = gson.toJson(user);

        System.out.println(usersJson);
    }

    /**
     * 驼峰转空格
     * FieldNamingPolicy.UPPER_CAMEL_CASE，测试
     * {"Name":"Norman","Email_of_developer":"norman@futurestud.io","Is Developer":true,"_Age Of Developer":26}
     */
    @Test
    public void t17() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE_WITH_SPACES);
        Gson gson = gsonBuilder.create();

        UserNaming user = new UserNaming("Norman", "norman@futurestud.io", true, 26);
        String usersJson = gson.toJson(user);

        System.out.println(usersJson);
    }

    /**
     * 自定义 - “”替换下划线
     */
    @Test
    public void t18() {
        FieldNamingStrategy customPolicy = new FieldNamingStrategy() {
            @Override
            public String translateName(Field f) {
                return f.getName().replace("_", "");
            }
        };

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingStrategy(customPolicy);
        Gson gson = gsonBuilder.create();

        UserNaming user = new UserNaming("Norman", "norman@futurestud.io", true, 26);
        String usersJson = gson.toJson(user);

        System.out.println(usersJson);
    }

    /**
     * 自定义字段 转换
     * {"Name":"Norman","emailOfDeveloper":"norman@futurestud.io","isDeveloper":true,"Ageofdeveloper":26}
     */
    @Test
    public void t19() {
        FieldNamingStrategy customPolicy = new FieldNamingStrategy() {
            @Override
            public String translateName(Field f) {
                return StrUtil.toCamelCase(f.getName());
            }
        };

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingStrategy(customPolicy);
        Gson gson = gsonBuilder.create();

        UserNaming user = new UserNaming("Norman", "norman@futurestud.io", true, 26);
        String usersJson = gson.toJson(user);

        System.out.println(usersJson);
    }


}
