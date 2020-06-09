package tonels;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import tonels.feature.deserialization.CarDeserializer;
import tonels.domian.Car;
import tonels.domian.Transaction;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class JacksonTest {

    private final static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * json string to Object
     *
     * @throws JsonProcessingException
     */
    @Test
    public void t1() throws JsonProcessingException {
        String carJson = "{ " +
                "\"brand\" : \"Mercedes\"," +
                "\"doors\" : 5 " +
                "}";
        Car car = objectMapper.readValue(carJson, Car.class);

        System.out.println("car brand = " + car.getBrand());
        System.out.println("car doors = " + car.getDoors());
        System.out.println(objectMapper.writeValueAsString(car));
    }

    /**
     * json File to Object
     * <p>
     * {"brand":"Mercedes","doors":5}
     *
     * @throws JsonProcessingException
     */
    @Test
    public void t2() throws IOException {

        File file = new File("D:\\GitRepository\\multiProject\\Jackson\\src\\test\\resources\\json\\car.json");

        Car car = objectMapper.readValue(file, Car.class);
        System.out.println(objectMapper.writeValueAsString(car));
    }

    /**
     * {
     * "brand": "Mercedes",
     * "doors": 5
     * }
     * tonels.domian.Car@78e67e0a
     *
     * @throws IOException
     */
    @Test
    public void t2_1() throws IOException {
        String resource = "json/car.json";
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(resource);
        String text = IOUtils.toString(is);
        System.out.println(text);

        final Car car = objectMapper.readValue(text, Car.class);
        System.out.println(car.toString());
    }

    /**
     * Car{brand='ford', doors=0}
     * Car{brand='Fiat', doors=0}
     *
     * @throws IOException
     */
    @Test
    public void t2_2() throws IOException {
        String jsonArray = "[{\"brand\":\"ford\"}, {\"brand\":\"Fiat\"}]";
        ObjectMapper objectMapper = new ObjectMapper();
        List<Car> cars1 = objectMapper.readValue(jsonArray, new TypeReference<List<Car>>() {
        });
        cars1.forEach(System.out::println);
    }

    /**
     * {brand=ford, doors=5}
     *
     * @throws IOException
     */
    @Test
    public void t2_3() throws IOException {
        String jsonObject = "{\"brand\":\"ford\", \"doors\":5}";
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> jsonMap = objectMapper.readValue(jsonObject,
                new TypeReference<Map<String, Object>>() {
                });
        System.out.println(jsonMap);
    }

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
     *  Cannot map `null` into type int
     * @throws IOException
     */
    @Test
    public void t2_5() throws IOException {
        objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, true);
        String carJson = "{ \"brand\":\"Toyota\", \"doors\":null }";
        Car car = objectMapper.readValue(carJson, Car.class);
        System.out.println(car);
    }

    /**
     * brand
     * doors
     * Car{brand='Ford', doors=6}
     * @throws IOException
     */
    @Test
    public void t2_6() throws IOException {
        String json = "{ \"brand\" : \"Ford\", \"doors\" : 6 }";
        SimpleModule module =
                new SimpleModule("CarDeserializer", new Version(3, 1, 8, null, null, null));
        module.addDeserializer(Car.class, new CarDeserializer(Car.class));
        objectMapper.registerModule(module);
        Car car = objectMapper.readValue(json, Car.class);
        System.out.println(car);
    }

    /**
     * 写测试
     * @throws IOException
     */
    @Test
    public void t3() throws IOException {
        Car car = new Car();
        car.setBrand("BMW" );
        car.setDoors(4) ;
        objectMapper.writeValue(
                new FileOutputStream("json/output-2.json"), car);
    }

    /**
     * {"brand":"BMW","doors":4}
     * @throws IOException
     */
    @Test
    public void t3_1() throws IOException {
        Car car = new Car();
        car.setBrand("BMW" );
        car.setDoors(4) ;
        String json = objectMapper.writeValueAsString(car);
        System.out.println(json);
    }


    // 时间转换

    /**
     * {"type":"transfer","date":1591519578900}
     * @throws IOException
     */
    @Test
    public void t4() throws IOException {
        Transaction transaction = new Transaction("transfer", new Date());
        String output = objectMapper.writeValueAsString(transaction);
        System.out.println(output);
    }

    /**
     * {"type":"transfer","date":"2020-06-07"}
     * @throws IOException
     */
    @Test
    public void t4_1() throws IOException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        objectMapper.setDateFormat(dateFormat);

        Transaction transaction = new Transaction("transfer", new Date());
        String output2 = objectMapper.writeValueAsString(transaction);
        System.out.println(output2);
    }

    // ---------------- Tree Model 转换

    /**
     * {"brand":"Mercedes","doors":5}
     * @throws IOException
     */
    @Test
    public void t5() throws IOException {
        String carJson =
                "{ \"brand\" : \"Mercedes\", \"doors\" : 5 }";
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readValue(carJson, JsonNode.class);
            System.out.println(jsonNode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * brand = Mercedes
     * doors = 5
     * john  =
     * field = value
     * @throws IOException
     */
    @Test
    public void t5_1() throws IOException {
        String carJson =
                "{ \"brand\" : \"Mercedes\", \"doors\" : 5," +
                        "  \"owners\" : [\"John\", \"Jack\", \"Jill\"]," +
                        "  \"nestedObject\" : { \"field\" : \"value\" } }";
        try {
            JsonNode jsonNode = objectMapper.readValue(carJson, JsonNode.class);

            JsonNode brandNode = jsonNode.get("brand");
            String brand = brandNode.asText();
            System.out.println("brand = " + brand);

            JsonNode doorsNode = jsonNode.get("doors");
            int doors = doorsNode.asInt();
            System.out.println("doors = " + doors);

            JsonNode array = jsonNode.get("owners");
            JsonNode jsonNode1 = array.get(0);
            String john = jsonNode.asText();
            System.out.println("john  = " + john);

            JsonNode child = jsonNode.get("nestedObject");
            JsonNode childField = child.get("field");
            String field = childField.asText();
            System.out.println("field = " + field);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // Yaml to String

    /**
     * brand: "John Doe"
     * doors: 5
     * @throws IOException
     */
    @Test
    public void t6() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());

        Car employee = new Car("John Doe", 5);

        String yamlString = null;
        try {
            yamlString = objectMapper.writeValueAsString(employee);
            System.out.println(yamlString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            // normally, rethrow exception here - or don't catch it at all.
        }
    }


























}
