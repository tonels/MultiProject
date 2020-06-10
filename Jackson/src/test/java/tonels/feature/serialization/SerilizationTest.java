package tonels.feature.serialization;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Test;
import tonels.feature.ModuleTestBase;
import tonels.feature.domain.P4;

import java.time.LocalDateTime;

public class SerilizationTest extends ModuleTestBase {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final ObjectMapper MAPPER = newMapper();
    private final ObjectWriter writer = MAPPER.writer();

    private final ObjectMapper dateMapper = newMapperBuilder()
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .build();


    /**
     * {"id":1,"name":"na","age":0,"sex":"M","birth":{"month":"JUNE","year":2020,"dayOfMonth":9,"hour":15,"minute":12,"monthValue":6,"nano":137000000,"second":12,"dayOfWeek":"TUESDAY","dayOfYear":161,"chronology":{"id":"ISO","calendarType":"iso8601"}}}
     * {"id":1,"name":"na","age":0,"sex":"M","birth":[2020,6,9,15,12,12,137000000]}
     * {"id":1,"name":"na","age":0,"sex":"M","birth":[2020,6,9,15,12,12,137000000]}
     * <p>
     * {"id":1,"name":"na","age":0,"sex":"M","birth":"2020-06-09T15:12:12.137"}
     *
     * @throws JsonProcessingException
     */
    @Test
    public void t1() throws JsonProcessingException {
        P4 p4 = new P4(1, "na", 0, "M", LocalDateTime.now());
        System.out.println(objectMapper.writeValueAsString(p4));
        System.out.println(MAPPER.writeValueAsString(p4));
        System.out.println(writer.writeValueAsString(p4));
        System.out.println(dateMapper.writeValueAsString(p4));
    }

    /**
     * {"id":1,"name":"na","age":0,"sex":null,"birth":null}
     * {"id":1,"name":"na","age":0,"sex":null,"birth":null}
     * {"id":1,"name":"na","age":0,"sex":null,"birth":null}
     * {"id":1,"name":"na","age":0,"sex":null,"birth":null}
     *
     * @throws JsonProcessingException
     */
    @Test
    public void t2() throws JsonProcessingException {
        P4 p4 = new P4(1, "na", 0);
        System.out.println(objectMapper.writeValueAsString(p4));
        System.out.println(MAPPER.writeValueAsString(p4));
        System.out.println(writer.writeValueAsString(p4));
        System.out.println(dateMapper.writeValueAsString(p4));
    }

    /**
     * {"id":1,"name":null,"age":null,"sex":"M","birth":null}
     * {"id":1,"name":null,"age":null,"sex":"M","birth":null}
     * {"id":1,"name":null,"age":null,"sex":"M","birth":null}
     * {"id":1,"name":null,"age":null,"sex":"M","birth":null}
     *
     * @throws JsonProcessingException
     */
    @Test
    public void t3() throws JsonProcessingException {
        P4 p4 = new P4(1, null, "M");
        System.out.println(objectMapper.writeValueAsString(p4));
        System.out.println(MAPPER.writeValueAsString(p4));
        System.out.println(writer.writeValueAsString(p4));
        System.out.println(dateMapper.writeValueAsString(p4));
    }

    /**
     * {"id":1,"name":"na","age":null,"sex":"","birth":null}
     * {"id":1,"name":"na","age":null,"sex":"","birth":null}
     * @throws JsonProcessingException
     */
    @Test
    public void t4() throws JsonProcessingException {
        P4 p4 = new P4(1, "na", "");
        System.out.println(dateMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false).writeValueAsString(p4));
        System.out.println(dateMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, true).writeValueAsString(p4));
    }

    /**
     * {
     *   "id" : 1,
     *   "name" : "na",
     *   "age" : null,
     *   "sex" : "",
     *   "birth" : null
     * }
     * @throws JsonProcessingException
     */
    @Test
    public void t4_1() throws JsonProcessingException {
        P4 p4 = new P4(1, "na", "");
        System.out.println(dateMapper.configure(SerializationFeature.INDENT_OUTPUT, true).writeValueAsString(p4));
    }


    /**
     * {
     *   "id" : 1,
     *   "name" : "na",
     *   "sex" : ""
     * }
     * @throws JsonProcessingException
     */
    @Test
    public void t4_3() throws JsonProcessingException {
        P4 p4 = new P4(1, "na", "");
        dateMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        System.out.println(dateMapper.configure(SerializationFeature.INDENT_OUTPUT, true).writeValueAsString(p4));
    }


    @Test
    public void t4_2() throws JsonProcessingException {
        P4 p4 = new P4(1, "na", "");
        System.out.println(dateMapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true).writeValueAsString(p4));
    }



}
