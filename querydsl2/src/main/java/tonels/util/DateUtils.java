package tonels.util;

import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DateUtils {

    // =======以上为常用时间转换=====================
    // ==============以下为常用时间和String的转换=====================
    public static final String DATE_FORMAT_10 = "yyyy-MM-dd";
    public static final String DATE_FORMAT_14 = "yyyyMMddHHmmss";
    public static final String DATE_FORMAT_17 = "yyyyMMdd HH:mm:ss";
    public static final String DATE_FORMAT_19 = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT_19_FORWARD_SLASH = "yyyy/MM/dd HH:mm:ss";
    private static final String MINUS = "-";

    private DateUtils() {
    }

    // ======以下为常用时间转换===================
    public static Date asDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date asDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDate asLocalDate(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static LocalDateTime asLocalDateTime(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     * convert string to date
     *
     * @param dateString
     * @param dateFormat
     * @return
     * @throws ParseException
     */
    public static Date parseDate(String dateString, String dateFormat) throws ParseException {
        if (StringUtils.isEmpty(dateFormat)) {
            dateFormat = switchDateFormat(dateString);
        }
        return new SimpleDateFormat(dateFormat).parse(dateString);
    }

    /**
     * convert string to date
     *
     * @param dateString
     * @return
     * @throws ParseException
     */
    public static Date parseDate(String dateString) throws ParseException {
        return parseDate(dateString, switchDateFormat(dateString));
    }

    /**
     * switch date format
     *
     * @param dateString
     * @return
     */
    private static String switchDateFormat(String dateString) {
        int length = dateString.length();
        switch (length) {
            case 19:
                if (dateString.contains(MINUS)) {
                    return DATE_FORMAT_19;
                } else {
                    return DATE_FORMAT_19_FORWARD_SLASH;
                }
            case 17:
                return DATE_FORMAT_17;
            case 14:
                return DATE_FORMAT_14;
            case 10:
                return DATE_FORMAT_10;
            default:
                throw new IllegalArgumentException("can not find date format for：" + dateString);
        }
    }

    /**
     * Format date
     * <p>
     * yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @return
     */
    public static String format(Date date) {
        return format(date, null);
    }

    /**
     * Format date
     *
     * @param date
     * @param dateFormat
     * @return
     */
    public static String format(Date date, String dateFormat) {
        if (date == null) {
            return "";
        }
        if (StringUtils.isEmpty(dateFormat)) {
            dateFormat = DATE_FORMAT_19;
        }
        return new SimpleDateFormat(dateFormat).format(date);
    }


    // 这里是 Java8中时间和String的转化
    public static String format(LocalDate localDate, String dateFormat) {
        Date date = asDate(localDate);
        if (date == null) {
            return "";
        }
        if (StringUtils.isEmpty(dateFormat)) {
            dateFormat = DATE_FORMAT_19;
        }
        return new SimpleDateFormat(dateFormat).format(date);
    }

    public static String format(LocalDateTime localDateTime, String dateFormat) {
        Date date = asDate(localDateTime);
        if (date == null) {
            return "";
        }
        if (StringUtils.isEmpty(dateFormat)) {
            dateFormat = DATE_FORMAT_19;
        }
        return new SimpleDateFormat(dateFormat).format(date);
    }

    // String 转 LocalDate/LocalDateTime
    public static LocalDate parseLocalDate(String dateString) throws ParseException {
        Date date = parseDate(dateString, switchDateFormat(dateString));
        return asLocalDate(date);
    }

    public static LocalDateTime parseLocalDateTime(String dateString) throws ParseException {
        Date date = parseDate(dateString, switchDateFormat(dateString));
        return asLocalDateTime(date);
    }
}

