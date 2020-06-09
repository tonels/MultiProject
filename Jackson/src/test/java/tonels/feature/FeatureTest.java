package tonels.feature;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Test;
import tonels.domian.Car;
import tonels.feature.domain.Person;
import tonels.feature.domain.Person1;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Date;

public class FeatureTest extends ModuleTestBase {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final ObjectMapper MAPPER = newMapper();
    private final ObjectWriter writer = MAPPER.writer();


    /**
     * Car{brand='null', doors=0}
     *
     * @throws IOException
     */
    @Test
    public void t2_4() throws IOException {
        objectMapper.configure(
                DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        String jsonObject = "{\"brand\":null, \"doors\":null}";
        final Car car = objectMapper.readValue(jsonObject, Car.class);
        System.out.println(car);
    }

    /**
     * Cannot map `null` into type int
     *
     * @throws IOException
     */
    @Test
    public void t2_5() throws IOException {
        objectMapper.configure(SerializationFeature.CLOSE_CLOSEABLE, true);
        String carJson = "{ \"brand\":\"Toyota\", \"doors\":null }";
        Car car = objectMapper.readValue(carJson, Car.class);
        System.out.println(car);
    }


//    public void testNewInstance() {
//
//        ObjectMapper objectMapper1 = new ObjectMapper();
//        ObjectMapper objectMapper2 = new ObjectMapper();
//        assertNotSame(objectMapper1, objectMapper2);
//
//        // Deserialization feature
//        objectMapper1.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
//        objectMapper2.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        assertTrue(objectMapper1.getDeserializationConfig().isEnabled(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES));
//        assertFalse(objectMapper2.getDeserializationConfig().isEnabled(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES));
//        // Serialization feature
//        objectMapper1.configure(SerializationFeature.INDENT_OUTPUT, true);
//        objectMapper2.configure(SerializationFeature.INDENT_OUTPUT, false);
//        assertTrue(objectMapper1.getSerializationConfig().isEnabled(SerializationFeature.INDENT_OUTPUT));
//        assertFalse(objectMapper2.getSerializationConfig().isEnabled(SerializationFeature.INDENT_OUTPUT));
//    }


//    public FilePreferences(File preferencesFile) {
//        this.mapper = new ObjectMapper();
//        mapper.enable(SerializationFeature.INDENT_OUTPUT);
//        this.preferencesFile = preferencesFile;
//        loadFile();
//    }


//    public FileBackedBuffer(final String basePath, final String prefix, final boolean deleteFilesOnClose, final int segmentsSize, final int maxNbSegments) throws IOException {
//        this.basePath = basePath;
//        this.prefix = prefix;
//        this.deleteFilesOnClose = deleteFilesOnClose;
//        smileObjectMapper = new ObjectMapper(smileFactory);
//        smileObjectMapper.registerModule(new JodaModule());
//        smileObjectMapper.enable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
//        final MemBuffersForBytes bufs = new MemBuffersForBytes(segmentsSize, 1, maxNbSegments);
//        inputBuffer = bufs.createStreamyBuffer(1, maxNbSegments);
//        recycle();
//    }

    /**
     * SerializationFeature 关于 时间的序列化四种特性
     * {"id":1,"name":"zxc","javaDate":"2020-06-09 10:55:46"}
     * {"id":1,"name":"zxc","javaDate":1591671346406}
     *
     * @throws JsonProcessingException
     */
    @Test
    public void t4() throws JsonProcessingException {
        //序列化的时候序列对象的所有属性
        objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        //取消时间的转化格式,默认是时间戳,可以取消,同时需要设置要表现的时间格式
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

        Person1 person = new Person1(1, "zxc", new Date());
        //把对象转换为json字符串
        String personJson = objectMapper.writeValueAsString(person);
        System.out.println(personJson);

        //默认为true,会显示时间戳
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true);
        personJson = objectMapper.writeValueAsString(person);
        System.out.println(personJson);
    }

    /**
     * 1
     * name
     * Tue Jun 09 11:15:17 CST 2020
     * 2020-06-09
     * 2020-06-09T11:15:17.237
     * 2020-06-09T03:15:17.237Z
     * PT24H
     * SystemClock[Asia/Shanghai]
     *
     * @throws JsonProcessingException
     */
    @Test
    public void t4_1() throws JsonProcessingException {
        Person name = new Person(1,
                "name",
                new Date(),
                LocalDate.now(),
                LocalDateTime.now(),
                Instant.now(),
                Duration.ofDays(1L),
                Clock.systemDefaultZone());

        String s = objectMapper.writeValueAsString(name);
        System.out.println(name.getId());
        System.out.println(name.getName());
        System.out.println(name.getJavaDate());
        System.out.println(name.getLocalDate());
        System.out.println(name.getLocalDateTime());
        System.out.println(name.getInstant());
        System.out.println(name.getDuration());
        System.out.println(name.getClock());
    }

    /**
     * 只是对 java.util.Date 序列化机制
     * WRITE_DATES_AS_TIMESTAMPS -> true  1591673234970
     * WRITE_DATES_AS_TIMESTAMPS -> false  "2020-06-09T03:27:14.970+00:00"
     * WRITE_DATES_WITH_ZONE_ID -> true  "2020-06-09T03:27:14.970+00:00"
     * WRITE_DATES_WITH_ZONE_ID -> false  "2020-06-09T03:27:14.970+00:00"
     * WRITE_DATE_KEYS_AS_TIMESTAMPS -> true  "2020-06-09T03:27:14.970+00:00"
     * WRITE_DATE_KEYS_AS_TIMESTAMPS -> false  "2020-06-09T03:27:14.970+00:00"
     * WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS -> true  "2020-06-09T03:27:14.970+00:00"
     * WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS -> false  "2020-06-09T03:27:14.970+00:00"
     *
     * @throws JsonProcessingException
     */
    @Test
    public void t4_2() throws JsonProcessingException {
        Person name = new Person(1,
                "name",
                new Date(),
                LocalDate.now(),
                LocalDateTime.now(),
                Instant.now(),
                Duration.ofDays(1L),
                Clock.systemDefaultZone());

        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true);
        System.out.println("WRITE_DATES_AS_TIMESTAMPS -> true  " + objectMapper.writeValueAsString(name.getJavaDate()));
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        System.out.println("WRITE_DATES_AS_TIMESTAMPS -> false  " + objectMapper.writeValueAsString(name.getJavaDate()));

        objectMapper.configure(SerializationFeature.WRITE_DATES_WITH_ZONE_ID, true);
        System.out.println("WRITE_DATES_WITH_ZONE_ID -> true  " + objectMapper.writeValueAsString(name.getJavaDate()));
        objectMapper.configure(SerializationFeature.WRITE_DATES_WITH_ZONE_ID, false);
        System.out.println("WRITE_DATES_WITH_ZONE_ID -> false  " + objectMapper.writeValueAsString(name.getJavaDate()));


        objectMapper.configure(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS, true);
        System.out.println("WRITE_DATE_KEYS_AS_TIMESTAMPS -> true  " + objectMapper.writeValueAsString(name.getJavaDate()));
        objectMapper.configure(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS, false);
        System.out.println("WRITE_DATE_KEYS_AS_TIMESTAMPS -> false  " + objectMapper.writeValueAsString(name.getJavaDate()));

        objectMapper.configure(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS, true);
        System.out.println("WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS -> true  " + objectMapper.writeValueAsString(name.getJavaDate()));
        objectMapper.configure(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS, false);
        System.out.println("WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS -> false  " + objectMapper.writeValueAsString(name.getJavaDate()));

    }

    /**
     * WRITE_DATES_AS_TIMESTAMPS -> true  {"year":2020,"month":"JUNE","chronology":{"id":"ISO","calendarType":"iso8601"},"dayOfMonth":9,"monthValue":6,"dayOfYear":161,"leapYear":true,"dayOfWeek":"TUESDAY","era":"CE"}
     * WRITE_DATES_AS_TIMESTAMPS -> false  {"year":2020,"month":"JUNE","chronology":{"id":"ISO","calendarType":"iso8601"},"dayOfMonth":9,"monthValue":6,"dayOfYear":161,"leapYear":true,"dayOfWeek":"TUESDAY","era":"CE"}
     *
     * @throws JsonProcessingException
     */
    @Test
    public void t4_3() throws JsonProcessingException {
        Person name = new Person(1,
                "name",
                new Date(),
                LocalDate.now(),
                LocalDateTime.now(),
                Instant.now(),
                Duration.ofDays(1L),
                Clock.systemDefaultZone());

        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true);
        System.out.println("WRITE_DATES_AS_TIMESTAMPS -> true  " + objectMapper.writeValueAsString(name.getLocalDate()));
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        System.out.println("WRITE_DATES_AS_TIMESTAMPS -> false  " + objectMapper.writeValueAsString(name.getLocalDate()));

    }

    /**
     * 添加Jdk8 Module依赖后 LocalDate 测试
     * WRITE_DATES_AS_TIMESTAMPS -> true  [2020,6,9]
     * WRITE_DATES_AS_TIMESTAMPS -> false  "2020-06-09"
     * WRITE_DATES_WITH_ZONE_ID -> true  "2020-06-09"
     * WRITE_DATES_WITH_ZONE_ID -> false  "2020-06-09"
     * WRITE_DATE_KEYS_AS_TIMESTAMPS -> true  "2020-06-09"
     * WRITE_DATE_KEYS_AS_TIMESTAMPS -> false  "2020-06-09"
     * WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS -> true  "2020-06-09"
     * WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS -> false  "2020-06-09"
     *
     * @throws JsonProcessingException
     */
    @Test
    public void t5() throws JsonProcessingException {
        Person name = new Person(1,
                "name",
                new Date(),
                LocalDate.now(),
                LocalDateTime.now(),
                Instant.now(),
                Duration.ofDays(1L),
                Clock.systemDefaultZone());

        MAPPER.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true);
        System.out.println("WRITE_DATES_AS_TIMESTAMPS -> true  " + MAPPER.writeValueAsString(name.getLocalDate()));
        MAPPER.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        System.out.println("WRITE_DATES_AS_TIMESTAMPS -> false  " + MAPPER.writeValueAsString(name.getLocalDate()));

        MAPPER.configure(SerializationFeature.WRITE_DATES_WITH_ZONE_ID, true);
        System.out.println("WRITE_DATES_WITH_ZONE_ID -> true  " + MAPPER.writeValueAsString(name.getLocalDate()));
        MAPPER.configure(SerializationFeature.WRITE_DATES_WITH_ZONE_ID, false);
        System.out.println("WRITE_DATES_WITH_ZONE_ID -> false  " + MAPPER.writeValueAsString(name.getLocalDate()));

        MAPPER.configure(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS, true);
        System.out.println("WRITE_DATE_KEYS_AS_TIMESTAMPS -> true  " + MAPPER.writeValueAsString(name.getLocalDate()));
        MAPPER.configure(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS, false);
        System.out.println("WRITE_DATE_KEYS_AS_TIMESTAMPS -> false  " + MAPPER.writeValueAsString(name.getLocalDate()));

        MAPPER.configure(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS, true);
        System.out.println("WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS -> true  " + MAPPER.writeValueAsString(name.getLocalDate()));
        MAPPER.configure(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS, false);
        System.out.println("WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS -> false  " + MAPPER.writeValueAsString(name.getLocalDate()));
    }

    /**
     * LocalDateTime 测试
     *
     * WRITE_DATES_AS_TIMESTAMPS -> true  [2020,6,9,12,25,46,723000000]
     * WRITE_DATES_AS_TIMESTAMPS -> false  "2020-06-09T12:25:46.723"
     * WRITE_DATES_WITH_ZONE_ID -> true  "2020-06-09T12:25:46.723"
     * WRITE_DATES_WITH_ZONE_ID -> false  "2020-06-09T12:25:46.723"
     * WRITE_DATE_KEYS_AS_TIMESTAMPS -> true  "2020-06-09T12:25:46.723"
     * WRITE_DATE_KEYS_AS_TIMESTAMPS -> false  "2020-06-09T12:25:46.723"
     * WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS -> true  "2020-06-09T12:25:46.723"
     * WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS -> false  "2020-06-09T12:25:46.723"
     * @throws JsonProcessingException
     */
    @Test
    public void t5_1() throws JsonProcessingException {
        Person name = new Person(1,
                "name",
                new Date(),
                LocalDate.now(),
                LocalDateTime.now(),
                Instant.now(),
                Duration.ofDays(1L),
                Clock.systemDefaultZone());

        MAPPER.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true);
        System.out.println("WRITE_DATES_AS_TIMESTAMPS -> true  " + MAPPER.writeValueAsString(name.getLocalDateTime()));
        MAPPER.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        System.out.println("WRITE_DATES_AS_TIMESTAMPS -> false  " + MAPPER.writeValueAsString(name.getLocalDateTime()));

        MAPPER.configure(SerializationFeature.WRITE_DATES_WITH_ZONE_ID, true);
        System.out.println("WRITE_DATES_WITH_ZONE_ID -> true  " + MAPPER.writeValueAsString(name.getLocalDateTime()));
        MAPPER.configure(SerializationFeature.WRITE_DATES_WITH_ZONE_ID, false);
        System.out.println("WRITE_DATES_WITH_ZONE_ID -> false  " + MAPPER.writeValueAsString(name.getLocalDateTime()));

        MAPPER.configure(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS, true);
        System.out.println("WRITE_DATE_KEYS_AS_TIMESTAMPS -> true  " + MAPPER.writeValueAsString(name.getLocalDateTime()));
        MAPPER.configure(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS, false);
        System.out.println("WRITE_DATE_KEYS_AS_TIMESTAMPS -> false  " + MAPPER.writeValueAsString(name.getLocalDateTime()));

        MAPPER.configure(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS, true);
        System.out.println("WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS -> true  " + MAPPER.writeValueAsString(name.getLocalDateTime()));
        MAPPER.configure(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS, false);
        System.out.println("WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS -> false  " + MAPPER.writeValueAsString(name.getLocalDateTime()));
    }

    @Test
    public void t5_2() throws JsonProcessingException {
        Person name = new Person(1,
                "name",
                new Date(),
                LocalDate.now(),
                LocalDateTime.now(),
                Instant.now(),
                Duration.ofDays(1L),
                Clock.systemDefaultZone());

        MAPPER.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true);
        System.out.println("WRITE_DATES_AS_TIMESTAMPS -> true  " + MAPPER.writeValueAsString(name.getLocalDateTime()));
        MAPPER.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        System.out.println("WRITE_DATES_AS_TIMESTAMPS -> false  " + MAPPER.writeValueAsString(name.getLocalDateTime()));

        MAPPER.configure(SerializationFeature.WRITE_DATES_WITH_ZONE_ID, true);
        System.out.println("WRITE_DATES_WITH_ZONE_ID -> true  " + MAPPER.writeValueAsString(name.getLocalDateTime()));
        MAPPER.configure(SerializationFeature.WRITE_DATES_WITH_ZONE_ID, false);
        System.out.println("WRITE_DATES_WITH_ZONE_ID -> false  " + MAPPER.writeValueAsString(name.getLocalDateTime()));

        MAPPER.configure(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS, true);
        System.out.println("WRITE_DATE_KEYS_AS_TIMESTAMPS -> true  " + MAPPER.writeValueAsString(name.getLocalDateTime()));
        MAPPER.configure(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS, false);
        System.out.println("WRITE_DATE_KEYS_AS_TIMESTAMPS -> false  " + MAPPER.writeValueAsString(name.getLocalDateTime()));

        MAPPER.configure(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS, true);
        System.out.println("WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS -> true  " + MAPPER.writeValueAsString(name.getLocalDateTime()));
        MAPPER.configure(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS, false);
        System.out.println("WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS -> false  " + MAPPER.writeValueAsString(name.getLocalDateTime()));
    }

}
