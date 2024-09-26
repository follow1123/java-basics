package cn.y.java.api.date;

import org.junit.jupiter.api.Test;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.Format;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalUnit;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/**
 * jdk8中的日期时间
 */
public class JDK8DateTest {

    /**
     * LocalDate：日期
     * LocalTime：时间
     * LocalDateTime：日期和时间
     */
    @Test
    public void testLocalDateTime() {
        // 创建对象
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        LocalDateTime localDateTime = LocalDateTime.now();

        System.out.println(localDate);
        System.out.println(localTime);
        System.out.println(localDateTime);

        // 指定日期时间并创建对象
        LocalDate localDate1 = LocalDate.of(2022, 10, 11);
        LocalDateTime localDateTime1 = LocalDateTime.of(2022, 10, 11, 12, 13, 14);

        System.out.println(localDate1);
        System.out.println(localDateTime1);

        // 获取时间
        LocalDateTime localDateTime2 = LocalDateTime.now();

        // 获取这个月的第几天
        int dayOfMonth = localDateTime2.getDayOfMonth();
        System.out.println(dayOfMonth);

        // 修改这个月的第几天
        LocalDateTime localDateTime3 = localDateTime2.withDayOfMonth(1);
        System.out.println(localDateTime2);
        System.out.println(localDateTime3);

        // 增加或减少天数
        LocalDateTime localDateTime4 = localDateTime2.plusDays(3);
        LocalDateTime localDateTime5 = localDateTime4.minusDays(4);
        System.out.println(localDateTime4);
        System.out.println(localDateTime5);
    }

    /**
     * 瞬时，时间戳
     * 指格林维治时间1970年01月01日00时00分00秒起至现在的总秒数
     * 和北京时间相差8小时
     */
    @Test
    public void testInstant() {
        // 实例化
        Instant instant1 = Instant.now();
        System.out.println(instant1);

        // 指定当前时区并实例化
        Instant instant2 = Instant.now(Clock.systemDefaultZone());
        System.out.println(instant2);

        // 获取毫秒数
        long epochMilli = instant2.toEpochMilli();
        System.out.println(epochMilli);
    }

    /**
     * Duration：时间段
     * Period：日期段
     */
    @Test
    public void testDurationAndPeriod() {
        // 获取两个时间段相差的秒数
        Duration duration = Duration.between(LocalTime.now(), LocalTime.now().plusHours(5));
        System.out.println(duration.getSeconds());

        Period period = Period.between(LocalDate.now(), LocalDate.now().minusDays(3));
        // 获取两个日期段相差的天数
        System.out.println(period.getDays());
    }

    /**
     * 日期时间格式化
     */
    @Test
    public void testDateTimeFormatter() {
        // 格式化
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateStr = dtf.format(LocalDateTime.now());
        System.out.println(dateStr);

        // 解析
        TemporalAccessor ta = dtf.parse(dateStr);
        LocalDateTime localDateTime = LocalDateTime.from(ta);
        System.out.println(localDateTime);
    }

    /**
     * 和旧日期时间api之间的相互转换
     */
    @Test
    public void testConvertWithOldApis() {
        // java.time.Instant与java.util.Date
        Instant instant1 = Instant.now();
        Date date1 = Date.from(instant1);
        Instant instant2 = date1.toInstant();

        // java.time.Instant与java.sql.Timestamp
        Instant instant3 = Instant.now();
        Timestamp timestamp = Timestamp.from(instant3);
        Instant instant4 = timestamp.toInstant();

        // java.time.ZonedDateTime与java.util.GregorianCalendar
        ZonedDateTime zonedDateTime1 = ZonedDateTime.now();
        GregorianCalendar gregorianCalendar1 = GregorianCalendar.from(zonedDateTime1);
        ZonedDateTime zonedDateTime2 = gregorianCalendar1.toZonedDateTime();

        // java.time.LocalDate与java.sql.Date
        LocalDate localDate1 = LocalDate.now();
        java.sql.Date sqlDate1 = java.sql.Date.valueOf(localDate1);
        LocalDate localDate2 = sqlDate1.toLocalDate();

        // java.time.LocalTime与java.sql.Time
        LocalTime localTime1 = LocalTime.now();
        Time sqlTime1 = Time.valueOf(localTime1);
        LocalTime localTime2 = sqlTime1.toLocalTime();

        // java.time.LocalDateTime与java.sql.Timestamp
        LocalDateTime localDateTime1 = LocalDateTime.now();
        Timestamp timestamp1 = Timestamp.valueOf(localDateTime1);
        LocalDateTime localDateTime2 = timestamp1.toLocalDateTime();

        // java.time.ZoneId与java.util.TimeZone
        ZoneId zoneId1 = ZoneId.systemDefault();
        TimeZone timeZone1 = TimeZone.getTimeZone(zoneId1);
        ZoneId zoneId2 = timeZone1.toZoneId();

        // java.time.format.DateTimeFormatter与java.text.DateFormat，没有转回DateTimeFormatter的操作
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Format format = dateTimeFormatter.toFormat();
    }
}
