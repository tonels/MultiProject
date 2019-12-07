package samples.time;

import org.junit.Test;

import java.time.*;
import java.time.temporal.ChronoField;
import java.util.Date;

/**
 */
public class LocalDateTime1 {

    @Test
    public void t1() {
        LocalDateTime sylvester = LocalDateTime.of(2014, Month.DECEMBER, 31, 23, 59, 59);

        DayOfWeek dayOfWeek = sylvester.getDayOfWeek(); // WEDNESDAY

        Month month = sylvester.getMonth(); // DECEMBER

        long minuteOfDay = sylvester.getLong(ChronoField.MINUTE_OF_DAY);  // 1439

        Instant instant = sylvester
                .atZone(ZoneId.systemDefault())
                .toInstant();

        Date legacyDate = Date.from(instant); // Wed Dec 31 23:59:59 CET 2014
    }
}