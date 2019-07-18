package parking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 
 * @author gustavojotz
 *
 */
@SpringBootApplication
//@ComponentScan(basePackageClasses = ParkingApplication.class)
public class ParkingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParkingApplication.class, args);
	}

}