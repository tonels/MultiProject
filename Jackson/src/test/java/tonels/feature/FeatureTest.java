package tonels.feature;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import junit.framework.TestCase;
import org.junit.Test;
import tonels.domian.Car;
import tonels.feature.domain.Person;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FeatureTest extends TestCase {

    private final static ObjectMapper objectMapper = new ObjectMapper();


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


    public void testNewInstance() {

        ObjectMapper objectMapper1 = new ObjectMapper();
        ObjectMapper objectMapper2 = new ObjectMapper();
        assertNotSame(objectMapper1, objectMapper2);

        // Deserialization feature
        objectMapper1.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
        objectMapper2.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        assertTrue(objectMapper1.getDeserializationConfig().isEnabled(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES));
        assertFalse(objectMapper2.getDeserializationConfig().isEnabled(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES));
        // Serialization feature
        objectMapper1.configure(SerializationFeature.INDENT_OUTPUT, true);
        objectMapper2.configure(SerializationFeature.INDENT_OUTPUT, false);
        assertTrue(objectMapper1.getSerializationConfig().isEnabled(SerializationFeature.INDENT_OUTPUT));
        assertFalse(objectMapper2.getSerializationConfig().isEnabled(SerializationFeature.INDENT_OUTPUT));
    }


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


    public void t4() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        //序列化的时候序列对象的所有属性
        objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        //取消时间的转化格式,默认是时间戳,可以取消,同时需要设置要表现的时间格式
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

        Person person = new Person(1, "zxc", new Date());
        //把对象转换为json字符串
        String personJson = objectMapper.writeValueAsString(person);
        System.out.println(personJson);

        //默认为true,会显示时间戳
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true);
        personJson = objectMapper.writeValueAsString(person);
        System.out.println(personJson);
    }


}
