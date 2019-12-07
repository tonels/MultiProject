package samples.time;

import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class LocalDate1 {


    @Test
    public void t1() {
        LocalDate today = LocalDate.now(); // 2019-12-07
        LocalDate tomorrow = today.plus(1, ChronoUnit.DAYS); // 2019-12-08
        LocalDate yesterday = tomorrow.minusDays(2); // 2019-12-06

        LocalDate independenceDay = LocalDate.of(2014, Month.JULY, 4); // 2014-07-04
        DayOfWeek dayOfWeek = independenceDay.getDayOfWeek(); //FRIDAY

        DateTimeFormatter germanFormatter =
                DateTimeFormatter
                        .ofLocalizedDate(FormatStyle.MEDIUM)
                        .withLocale(Locale.GERMAN);

        LocalDate xmas = LocalDate.parse("24.12.2014", germanFormatter); // 2014-12-24
    }
}