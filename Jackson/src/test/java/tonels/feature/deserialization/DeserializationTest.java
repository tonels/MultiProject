package tonels.feature.deserialization;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import tonels.domian.FileMediaInjectVO;
import tonels.domian.Vo2;
import tonels.domian.Vo21;
import tonels.domian.Vo22;
import tonels.feature.ModuleTestBase;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;

public class DeserializationTest extends ModuleTestBase {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final ObjectMapper MAPPER = newMapper();
    private final ObjectWriter writer = MAPPER.writer();

    private final ObjectMapper dateMapper = newMapperBuilder()
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .build();


    /**
     * Vo2{createUser='administrator', createDate='2020-05-26 14:05:14'}
     * @throws IOException
     */
    @Test
    public void t1() throws IOException {
        String resource = "  {\n" +
                "    \"createDate\": \"2020-05-26 14:05:14\",\n" +
                "    \"createUser\": \"administrator\"\n" +
                "  }";
        final Vo2 vo2 = objectMapper.readValue(resource, Vo2.class);
        System.out.println(vo2);
    }

    /**
     * {
     *   "createDate": "2020-05-18 18:08:24",
     *   "createUser": "administrator"
     * }
     * Vo2{createUser='administrator', createDate='2020-05-18 18:08:24'}
     * @throws IOException
     */
    @Test
    public void t1_1() throws IOException {
        String resource = "json/VO21.json";
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(resource);
        String text = IOUtils.toString(is);
        System.out.println(text);
        final Vo2 vo2 = objectMapper.readValue(text, Vo2.class);
        System.out.println(vo2);
    }

    /**
     * Vo2{createUser='administrator', createDate='2020-05-18 18:08:24'}
     * Vo2{createUser='administrator', createDate='2020-05-19 10:44:58'}
     * Vo2{createUser='administrator', createDate='2020-05-19 10:48:57'}
     * Vo2{createUser='administrator', createDate='2020-05-19 11:06:11'}
     * Vo2{createUser='administrator', createDate='2020-05-19 11:10:32'}
     * Vo2{createUser='administrator', createDate='2020-05-19 14:14:44'}
     * Vo2{createUser='administrator', createDate='2020-05-19 14:53:40'}
     * Vo2{createUser='administrator', createDate='2020-05-19 14:56:19'}
     * Vo2{createUser='administrator', createDate='2020-05-19 14:57:14'}
     * ...
     * ...
     *
     * @throws IOException
     */
    @Test
    public void t1_2() throws IOException {
        String resource = "json/VO2.json";
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(resource);
        String text = IOUtils.toString(is);
        Vo2[] asArray = objectMapper.readValue(text, Vo2[].class);
        for (Vo2 vo2 : asArray) {
            System.out.println(vo2.toString());
        }
    }

    /**
     * Vo2{createUser='administrator', createDate='2020-05-18 18:08:24'}
     * Vo2{createUser='administrator', createDate='2020-05-19 10:44:58'}
     * Vo2{createUser='administrator', createDate='2020-05-19 10:48:57'}
     * Vo2{createUser='administrator', createDate='2020-05-19 11:06:11'}
     * Vo2{createUser='administrator', createDate='2020-05-19 11:10:32'}
     * Vo2{createUser='administrator', createDate='2020-05-19 14:14:44'}
     * Vo2{createUser='administrator', createDate='2020-05-19 14:53:40'}
     * Vo2{createUser='administrator', createDate='2020-05-19 14:56:19'}
     * Vo2{createUser='administrator', createDate='2020-05-19 14:57:14'}
     * ...
     * ...
     * @throws IOException
     */
    @Test
    public void t1_4() throws IOException {
        String resource = "json/VO2.json";
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(resource);
        String text = IOUtils.toString(is);

        CollectionType javaType = objectMapper.getTypeFactory()
                .constructCollectionType(List.class, Vo2.class);
        List<Vo2> asList = objectMapper.readValue(text, javaType);
        asList.forEach(System.out::println);
    }

    /**
     * 2020-05-26T14:05:14
     * @throws IOException
     */
    @Test
    public void t2() throws IOException {
        String resource = "  {\n" +
                "    \"createDate\": \"2020-05-26T14:05:14\",\n" +
                "    \"createUser\": \"administrator\"\n" +
                "  }";
        final Vo21 vo21 = dateMapper.readValue(resource, Vo21.class);
        System.out.println(vo21.getCreateDate());
    }

    /**
     * 2020-05-26T14:05:14
     * @throws IOException
     */
    @Test
    public void t2_1() throws IOException {
        String resource = "  {\n" +
                "    \"createDate\": \"2020-05-26 14:05:14\",\n" +
                "    \"createUser\": \"administrator\"\n" +
                "  }";
        final Vo21 vo21 = dateMapper.readValue(resource, Vo21.class);
        System.out.println(vo21.getCreateDate());
    }

    /**
     * 2020-05-26T14:05:14
     * @throws IOException
     */
    @Test
    public void t2_2() throws IOException {
        String resource = "  {\n" +
                "    \"createDate\": \"2020-05-26 14:05:14\",\n" +
                "    \"createUser\": \"administrator\"\n" +
                "  }";
        final Vo22 vo22 = dateMapper.readValue(resource, Vo22.class);
        System.out.println(vo22.getCreateDate());
    }

    /**
     * nstance of `java.time.LocalDateTime` (no Creators,
     * @throws IOException
     */
    @Test
    public void t2_3() throws IOException {
        String resource = "  {\n" +
                "    \"createDate\": \"2020-05-26 14:05:14\",\n" +
                "    \"createUser\": \"administrator\"\n" +
                "  }";
        final Vo22 vo22 = objectMapper.readValue(resource, Vo22.class);
        System.out.println(vo22.getCreateDate());
    }

    /**
     * 2020-05-26T14:05:14
     * @throws IOException
     */
    @Test
    public void t2_4() throws IOException {
        String resource = "  {\n" +
                "    \"createDate\": \"2020-05-26 14:05:14\",\n" +
                "    \"createUser\": \"administrator\"\n" +
                "  }";
        final Vo22 vo22 = MAPPER.readValue(resource, Vo22.class);
        System.out.println(vo22.getCreateDate());
    }




}
