package tonels.feature.deserialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import tonels.feature.ModuleTestBase;

import java.io.IOException;
import java.io.InputStream;

public class DeserializationTest extends ModuleTestBase {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final ObjectMapper MAPPER = newMapper();
    private final ObjectWriter writer = MAPPER.writer();

    private final ObjectMapper dateMapper = newMapperBuilder()
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .build();


    /**
     * @throws IOException
     */
    @Test
    public void t1() throws IOException {
        String resource = "json/car.json";
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(resource);
        String text = IOUtils.toString(is);
        System.out.println(text);

        writer.

    }


}
