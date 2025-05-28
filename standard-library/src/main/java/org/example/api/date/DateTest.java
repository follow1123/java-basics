package org.example.api.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * jdk8之间的日期类
 */
public class DateTest {
    public void testSystem() {
        /*
        获取当前时间对应的毫秒数，时间戳
        当前时间与1970年1月1日0时0分0秒之间的毫秒数
         */
        long currentTimeMillis = System.currentTimeMillis();
        System.out.println(currentTimeMillis);
    }

    /**
     * java.util.Date
     */
    public void testUtilDate() {
        // 创建对象
        Date date1 = new Date();
        System.out.println(date1);

        // 获取时间戳
        long time = date1.getTime();
        System.out.println(time);

        // 基于时间戳创建对象
        Date date2 = new Date(1727334456255L);
        System.out.println(date2);
    }

    /**
     * java.sql.Date：是java.util.Date的子类
     */
    public void testSQLDate() {
        // 创建对象
        java.sql.Date date = new java.sql.Date(1727334456255L);

        // 打印时默认只显示年月日，方便数据库存储
        System.out.println(date);
    }

    /**
     * java.util.Date和java.sql.Date相互转换
     */
    public void testUtilDateAndSQLDate() {
        // java.util.Date转java.sql.Date
        Date utilDate = new Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

        // java.sql.Date转java.util.Date
        Date date = new Date(sqlDate.getTime());
    }

    /**
     * 用于日期格式化和解析
     * 格式化：日期 > 字符串
     * 解析：字符串 > 日期
     */
    public void testSimpleDateFormat() throws ParseException {
        // 格式化日期
        SimpleDateFormat sdf1 = new SimpleDateFormat();
        Date date1 = new Date();
        String dateStr1 = sdf1.format(date1);
        System.out.println(dateStr1);

        // 解析
        Date date2 = sdf1.parse(dateStr1);
        System.out.println(date2);


        // 指定日期格式进行格式化
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date3 = new Date();
        String dateStr2 = sdf2.format(date3);
        System.out.println(dateStr2);

        // 根据指定的日期格式进行解析
        Date date4 = sdf2.parse(dateStr2);
        System.out.println(date4);
    }

    /**
     * 日历，对日期进行修改
     */
    public void testCalendar() {
        // 创建对象
        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar);

        // 获取今天是这个月的第几天
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println(dayOfMonth);

        // 获取今天是这个星期的第几天
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        System.out.println(dayOfWeek);

        // 修改今天是这个月的第几天
        calendar.set(Calendar.DAY_OF_MONTH, 11);
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));

        // 增加或修改今天是这个月的第几天
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));

        // 和java.util.Date相互转换
        Date date1 = calendar.getTime();
        System.out.println(date1);

        Date date2 = new Date();
        calendar.setTime(date2);
    }
}
