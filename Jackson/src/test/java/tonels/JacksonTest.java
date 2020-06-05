package tonels;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import tonels.domian.Car;

import java.io.File;
import java.io.IOException;
import java.net.URL;

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

    @Test
    public void t3() throws IOException {
        URL url = new URL("https://gitee.com/tonels/data-manage/blob/master/car.json");

        Car car = objectMapper.readValue(url, Car.class);
        System.out.println(objectMapper.writeValueAsString(car));
    }


}
