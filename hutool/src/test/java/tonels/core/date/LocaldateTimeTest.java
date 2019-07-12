package tonels.core.date;

import org.junit.Test;

import java.time.LocalDate;
import java.util.Date;

public class LocaldateTimeTest {

    @Test
    public void test1() {
        LocalDate localDate = LocalDate.now();
        Date date = new Date();
//        java.sql.Date sqlDate1 = new java.sql.Date();


        System.out.println("date ； " + date );
        System.out.println("localDate : " + localDate ); // 2019-07-11
    }


}
