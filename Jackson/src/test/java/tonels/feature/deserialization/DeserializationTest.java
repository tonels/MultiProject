package tonels.feature.deserialization;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import tonels.domian.FileMediaInjectVO;
import tonels.domian.Vo2;
import tonels.feature.ModuleTestBase;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;

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
        String resource = "  {\n" +
                "    \"createDate\": \"2020-05-26 14:05:14\",\n" +
                "    \"createUser\": \"administrator\"\n" +
                "  }";
        final Vo2 vo2 = objectMapper.readValue(resource, Vo2.class);
        System.out.println(vo2);
    }

    @Test
    public void t1_1() throws IOException {
        String resource = "json/VO21.json";
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(resource);

        String text = IOUtils.toString(is);
        System.out.println(text);
        final Vo2 vo2 = objectMapper.readValue(resource, Vo2.class);
        System.out.println(vo2);
    }

    @Test
    public void t1_2() throws IOException {
        String resource = "json/VO2.json";
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(resource);
        String text = IOUtils.toString(is);
        final Vo2 fileMediaInjectVOS = MAPPER.convertValue(text, new TypeReference<Vo2>(){});
        System.out.println(fileMediaInjectVOS);
    }

    @Test
    public void t1_3() throws IOException {
        String resource = "json/VO1.json";
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(resource);
        String text = IOUtils.toString(is);
        final List<FileMediaInjectVO> fileMediaInjectVOS = dateMapper.convertValue(text, new TypeReference<List<FileMediaInjectVO>>() {});
        fileMediaInjectVOS.forEach(System.out::println);
    }

    @Test
    public void t2() throws IOException {
        String resource = "[\n" +
                "  {\n" +
                "    \"createDate\": \"2020-05-18 18:08:24\",\n" +
                "    \"createUser\": \"administrator\",\n" +
                "    \"lastModifuDate\": \"2020-05-25T19:05:00\",\n" +
                "    \"lastModifyUser\": \"administrator\",\n" +
                "    \"status\": \"10\",\n" +
                "    \"targetCode\": \"125992298\",\n" +
                "    \"targetId\": 1,\n" +
                "    \"targetName\": \"jiangsu\",\n" +
                "    \"targetTypeId\": 2\n" +
                "  },\n" +
                "  {\n" +
                "    \"createDate\": \"2020-05-19 10:44:58\",\n" +
                "    \"createUser\": \"administrator\",\n" +
                "    \"lastModifuDate\": \"2020-05-25T18:14:04\",\n" +
                "    \"lastModifyUser\": \"administrator\",\n" +
                "    \"status\": \"10\",\n" +
                "    \"targetCode\": \"1\",\n" +
                "    \"targetId\": 2,\n" +
                "    \"targetName\": \"测试\",\n" +
                "    \"targetTypeId\": 2\n" +
                "  },\n" +
                "  {\n" +
                "    \"createDate\": \"2020-05-19 10:48:57\",\n" +
                "    \"createUser\": \"administrator\",\n" +
                "    \"lastModifuDate\": \"2020-05-25T18:14:04\",\n" +
                "    \"lastModifyUser\": \"administrator\",\n" +
                "    \"status\": \"10\",\n" +
                "    \"targetCode\": \"15\",\n" +
                "    \"targetId\": 3,\n" +
                "    \"targetName\": \"测试2\",\n" +
                "    \"targetTypeId\": 2\n" +
                "  },\n" +
                "  {\n" +
                "    \"createDate\": \"2020-05-19 11:06:11\",\n" +
                "    \"createUser\": \"administrator\",\n" +
                "    \"lastModifuDate\": \"2020-05-25T18:22:44\",\n" +
                "    \"lastModifyUser\": \"administrator\",\n" +
                "    \"status\": \"10\",\n" +
                "    \"targetCode\": \"lsde\",\n" +
                "    \"targetId\": 4,\n" +
                "    \"targetName\": \"name324\",\n" +
                "    \"targetTypeId\": 2\n" +
                "  },\n" +
                "  {\n" +
                "    \"createDate\": \"2020-05-19 11:10:32\",\n" +
                "    \"createUser\": \"administrator\",\n" +
                "    \"lastModifuDate\": \"2020-05-19T14:52:57\",\n" +
                "    \"lastModifyUser\": \"administrator\",\n" +
                "    \"status\": \"00\",\n" +
                "    \"targetCode\": \"23123\",\n" +
                "    \"targetId\": 5,\n" +
                "    \"targetName\": \"2313\",\n" +
                "    \"targetTypeId\": 3\n" +
                "  },\n" +
                "  {\n" +
                "    \"createDate\": \"2020-05-19 14:14:44\",\n" +
                "    \"createUser\": \"administrator\",\n" +
                "    \"lastModifuDate\": \"2020-05-19T14:52:57\",\n" +
                "    \"lastModifyUser\": \"administrator\",\n" +
                "    \"status\": \"00\",\n" +
                "    \"targetCode\": \"111\",\n" +
                "    \"targetId\": 6,\n" +
                "    \"targetName\": \"111\",\n" +
                "    \"targetTypeId\": 3\n" +
                "  },\n" +
                "  {\n" +
                "    \"createDate\": \"2020-05-19 14:53:40\",\n" +
                "    \"createUser\": \"administrator\",\n" +
                "    \"lastModifuDate\": \"2020-05-19T14:56:30\",\n" +
                "    \"lastModifyUser\": \"administrator\",\n" +
                "    \"status\": \"00\",\n" +
                "    \"targetCode\": \"312\",\n" +
                "    \"targetId\": 8,\n" +
                "    \"targetName\": \"3213\",\n" +
                "    \"targetTypeId\": 3\n" +
                "  },\n" +
                "  {\n" +
                "    \"createDate\": \"2020-05-19 14:56:19\",\n" +
                "    \"createUser\": \"administrator\",\n" +
                "    \"lastModifuDate\": \"2020-05-19T14:56:30\",\n" +
                "    \"lastModifyUser\": \"administrator\",\n" +
                "    \"status\": \"00\",\n" +
                "    \"targetCode\": \"123213\",\n" +
                "    \"targetId\": 9,\n" +
                "    \"targetName\": \"213\",\n" +
                "    \"targetTypeId\": 3\n" +
                "  },\n" +
                "  {\n" +
                "    \"createDate\": \"2020-05-19 14:57:14\",\n" +
                "    \"createUser\": \"administrator\",\n" +
                "    \"lastModifuDate\": \"2020-05-25T18:22:44\",\n" +
                "    \"lastModifyUser\": \"administrator\",\n" +
                "    \"status\": \"10\",\n" +
                "    \"targetCode\": \"321\",\n" +
                "    \"targetId\": 10,\n" +
                "    \"targetName\": \"213\",\n" +
                "    \"targetTypeId\": 2\n" +
                "  },\n" +
                "  {\n" +
                "    \"createDate\": \"2020-05-25 14:59:59\",\n" +
                "    \"createUser\": \"administrator\",\n" +
                "    \"lastModifuDate\": \"2020-05-26T15:51:44\",\n" +
                "    \"lastModifyUser\": \"administrator\",\n" +
                "    \"status\": \"10\",\n" +
                "    \"targetCode\": \"123\",\n" +
                "    \"targetId\": 11,\n" +
                "    \"targetName\": \"黑暗金刚\",\n" +
                "    \"targetTypeId\": 1\n" +
                "  },\n" +
                "  {\n" +
                "    \"createDate\": \"2020-05-25 15:00:43\",\n" +
                "    \"createUser\": \"administrator\",\n" +
                "    \"lastModifuDate\": \"2020-05-26T10:25:49\",\n" +
                "    \"lastModifyUser\": \"administrator\",\n" +
                "    \"status\": \"10\",\n" +
                "    \"targetCode\": \"123456\",\n" +
                "    \"targetId\": 12,\n" +
                "    \"targetName\": \"黑暗金刚\",\n" +
                "    \"targetTypeId\": 1\n" +
                "  },\n" +
                "  {\n" +
                "    \"createDate\": \"2020-05-25 15:01:20\",\n" +
                "    \"createUser\": \"administrator\",\n" +
                "    \"lastModifuDate\": \"2020-05-25T15:01:20\",\n" +
                "    \"lastModifyUser\": \"administrator\",\n" +
                "    \"status\": \"00\",\n" +
                "    \"targetCode\": \"1234\",\n" +
                "    \"targetId\": 13,\n" +
                "    \"targetName\": \"实打实打算\",\n" +
                "    \"targetTypeId\": 1\n" +
                "  },\n" +
                "  {\n" +
                "    \"createDate\": \"2020-05-25 15:15:37\",\n" +
                "    \"createUser\": \"administrator\",\n" +
                "    \"lastModifuDate\": \"2020-05-25T18:47:15\",\n" +
                "    \"lastModifyUser\": \"administrator\",\n" +
                "    \"status\": \"10\",\n" +
                "    \"targetCode\": \"请问请问\",\n" +
                "    \"targetId\": 14,\n" +
                "    \"targetName\": \"请问请问\",\n" +
                "    \"targetTypeId\": 2\n" +
                "  },\n" +
                "  {\n" +
                "    \"createDate\": \"2020-05-25 15:16:08\",\n" +
                "    \"createUser\": \"administrator\",\n" +
                "    \"lastModifuDate\": \"2020-05-25T18:47:15\",\n" +
                "    \"lastModifyUser\": \"administrator\",\n" +
                "    \"status\": \"10\",\n" +
                "    \"targetCode\": \"请问请问我去\",\n" +
                "    \"targetId\": 15,\n" +
                "    \"targetName\": \"驱蚊器饿我去\",\n" +
                "    \"targetTypeId\": 2\n" +
                "  },\n" +
                "  {\n" +
                "    \"createDate\": \"2020-05-25 15:37:38\",\n" +
                "    \"createUser\": \"administrator\",\n" +
                "    \"lastModifuDate\": \"2020-05-26T11:24:00\",\n" +
                "    \"lastModifyUser\": \"administrator\",\n" +
                "    \"status\": \"10\",\n" +
                "    \"targetCode\": \"666\",\n" +
                "    \"targetId\": 16,\n" +
                "    \"targetName\": \"ytce\",\n" +
                "    \"targetTypeId\": 1\n" +
                "  },\n" +
                "  {\n" +
                "    \"createDate\": \"2020-05-25 15:38:07\",\n" +
                "    \"createUser\": \"administrator\",\n" +
                "    \"lastModifuDate\": \"2020-05-25T15:38:07\",\n" +
                "    \"lastModifyUser\": \"administrator\",\n" +
                "    \"status\": \"00\",\n" +
                "    \"targetCode\": \"ytce1\",\n" +
                "    \"targetId\": 17,\n" +
                "    \"targetName\": \"ytce1\",\n" +
                "    \"targetTypeId\": 1\n" +
                "  },\n" +
                "  {\n" +
                "    \"createDate\": \"2020-05-25 17:44:19\",\n" +
                "    \"createUser\": \"administrator\",\n" +
                "    \"lastModifuDate\": \"2020-05-25T17:44:19\",\n" +
                "    \"lastModifyUser\": \"administrator\",\n" +
                "    \"status\": \"00\",\n" +
                "    \"targetCode\": \"3213\",\n" +
                "    \"targetId\": 18,\n" +
                "    \"targetName\": \"31231\",\n" +
                "    \"targetTypeId\": 1\n" +
                "  },\n" +
                "  {\n" +
                "    \"createDate\": \"2020-05-26 11:26:45\",\n" +
                "    \"createUser\": \"administrator\",\n" +
                "    \"lastModifuDate\": \"2020-05-26T15:16:36\",\n" +
                "    \"lastModifyUser\": \"administrator\",\n" +
                "    \"status\": \"00\",\n" +
                "    \"targetCode\": \"111111\",\n" +
                "    \"targetId\": 24,\n" +
                "    \"targetName\": \"请问请问\",\n" +
                "    \"targetTypeId\": 1\n" +
                "  },\n" +
                "  {\n" +
                "    \"createDate\": \"2020-05-26 11:30:31\",\n" +
                "    \"createUser\": \"administrator\",\n" +
                "    \"lastModifuDate\": \"2020-05-26T16:01:02\",\n" +
                "    \"lastModifyUser\": \"administrator\",\n" +
                "    \"status\": \"10\",\n" +
                "    \"targetCode\": \"按时大撒大按时大撒大按时大撒大按时大撒大\",\n" +
                "    \"targetId\": 25,\n" +
                "    \"targetName\": \"按时大撒大撒\",\n" +
                "    \"targetTypeId\": 1\n" +
                "  },\n" +
                "  {\n" +
                "    \"createDate\": \"2020-05-26 14:05:14\",\n" +
                "    \"createUser\": \"administrator\",\n" +
                "    \"lastModifuDate\": \"2020-05-26T14:53:30\",\n" +
                "    \"lastModifyUser\": \"administrator\",\n" +
                "    \"status\": \"00\",\n" +
                "    \"targetCode\": \"灌灌灌灌\",\n" +
                "    \"targetId\": 26,\n" +
                "    \"targetName\": \"123\",\n" +
                "    \"targetTypeId\": 1\n" +
                "  },\n" +
                "  {\n" +
                "    \"createDate\": \"2020-05-26 14:11:47\",\n" +
                "    \"createUser\": \"administrator\",\n" +
                "    \"lastModifuDate\": \"2020-05-26T15:16:19\",\n" +
                "    \"lastModifyUser\": \"administrator\",\n" +
                "    \"status\": \"00\",\n" +
                "    \"targetCode\": \"啊大苏打撒旦\",\n" +
                "    \"targetId\": 27,\n" +
                "    \"targetName\": \"阿三打撒飒飒大苏打按时大撒大撒大撒啊是大撒打算的撒大撒大撒的啊撒打算大师的阿三打撒大师大苏打阿三倒萨大师的撒大师大师大收到爱的阿三d\",\n" +
                "    \"targetTypeId\": 1\n" +
                "  },\n" +
                "  {\n" +
                "    \"createDate\": \"2020-05-26 15:46:32\",\n" +
                "    \"createUser\": \"administrator\",\n" +
                "    \"lastModifuDate\": \"2020-05-26T15:46:32\",\n" +
                "    \"lastModifyUser\": \"administrator\",\n" +
                "    \"status\": \"00\",\n" +
                "    \"targetCode\": \"按时大苏打按\",\n" +
                "    \"targetId\": 28,\n" +
                "    \"targetName\": \"按时大苏打按时大苏打按时大苏打按时大苏打按时大苏打按时大苏打按时大苏打按时大苏打按时大苏打按时大苏打按时大苏打按时大苏打按时大苏打按时大苏打按时大苏打按时大苏打按时大苏打按时大苏打按时大苏打按时大苏打\",\n" +
                "    \"targetTypeId\": 1\n" +
                "  },\n" +
                "  {\n" +
                "    \"createDate\": \"2020-05-26 15:49:19\",\n" +
                "    \"createUser\": \"administrator\",\n" +
                "    \"lastModifuDate\": \"2020-05-26T15:49:19\",\n" +
                "    \"lastModifyUser\": \"administrator\",\n" +
                "    \"status\": \"00\",\n" +
                "    \"targetCode\": \"撒发生发射点\",\n" +
                "    \"targetId\": 29,\n" +
                "    \"targetName\": \"撒发生发射点阿斯顿大\",\n" +
                "    \"targetTypeId\": 1\n" +
                "  },\n" +
                "  {\n" +
                "    \"createDate\": \"2020-05-27 09:28:38\",\n" +
                "    \"createUser\": \"administrator\",\n" +
                "    \"lastModifuDate\": \"2020-05-27T09:28:38\",\n" +
                "    \"lastModifyUser\": \"administrator\",\n" +
                "    \"status\": \"00\",\n" +
                "    \"targetCode\": \"2-1\",\n" +
                "    \"targetId\": 30,\n" +
                "    \"targetName\": \"二级节点测试\",\n" +
                "    \"targetTypeId\": 1\n" +
                "  },\n" +
                "  {\n" +
                "    \"createDate\": \"2020-05-27 09:29:16\",\n" +
                "    \"createUser\": \"administrator\",\n" +
                "    \"lastModifuDate\": \"2020-05-27T09:29:16\",\n" +
                "    \"lastModifyUser\": \"administrator\",\n" +
                "    \"status\": \"00\",\n" +
                "    \"targetCode\": \"2-2\",\n" +
                "    \"targetId\": 31,\n" +
                "    \"targetName\": \"二级节点测试\",\n" +
                "    \"targetTypeId\": 1\n" +
                "  }\n" +
                "]";
        final List<FileMediaInjectVO> fileMediaInjectVOS = dateMapper.convertValue(resource, new TypeReference<List<FileMediaInjectVO>>() {});
        fileMediaInjectVOS.forEach(System.out::println);
    }


    @Test
    public void t3() throws IOException {
        String resource = " {\n" +
                "    \"createDate\": \"2020-05-19 11:10:32\",\n" +
                "    \"createUser\": \"administrator\",\n" +
                "    \"lastModifuDate\": \"2020-05-19T14:52:57\",\n" +
                "    \"lastModifyUser\": \"administrator\",\n" +
                "    \"status\": \"00\",\n" +
                "    \"targetCode\": \"23123\",\n" +
                "    \"targetId\": 5,\n" +
                "    \"targetName\": \"2313\",\n" +
                "    \"targetTypeId\": 3\n" +
                "  }";
        final FileMediaInjectVO fileMediaInjectVOS = dateMapper.convertValue(resource, new TypeReference<FileMediaInjectVO>() {});
        System.out.println(fileMediaInjectVOS);
    }

}
