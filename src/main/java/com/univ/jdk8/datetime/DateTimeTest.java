package com.univ.jdk8.datetime;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

import org.junit.Test;

/**
 * @author univ
 * @date 2019/11/12 6:42 PM
 * @description 注意，LocalDate LocalTime LocalDateTime等都是不可变类
 * 常用操作：
 * 1. 当前日期(时间)；
 * 2. 加减日期(时间)；
 * 3. 比较日期(时间)；
 * 4. date与time互相拼凑形成LocalDateTime；
 * 5. 格式化输出
 */
public class DateTimeTest {

    /**
     * LocalDate：表示年月日
     */
    @Test
    public void localDateTest() {
        // 今天的年月日
        LocalDate now = LocalDate.now();
        System.out.println("today: " + now);
        System.out.println("年月日的日期形式: " + now.toString());

        // 指定年月日
        LocalDate of = LocalDate.of(2019, 10, 11);
        System.out.println("2019-10-11" + of);
        // 从字符串解析年月日：严格按照ISO yyyy-MM-dd验证，写成2019-1-12不行
        LocalDate parse = LocalDate.parse("2019-01-02");
        System.out.println("parse: " + parse);

        int year = of.getYear();
        System.out.println("year: " + year);

        // 注意，月份是用Month表示，是一个枚举
        Month month = of.getMonth();
        System.out.println("month: " + month);
        
        int dayOfMonth = of.getDayOfMonth();
        System.out.println("这个月的第几天: " + dayOfMonth);

        // 注意，星期用DayOfWeek表示，是一个枚举
        DayOfWeek dayOfWeek = of.getDayOfWeek();
        System.out.println("星期几: " + dayOfWeek);
        
        int dayOfYear = of.getDayOfYear();
        System.out.println("今年的第几天:" + dayOfYear);

        // 日期比较
        boolean before = now.isBefore(of);
        boolean after = now.isAfter(of);
        System.out.println(before);
        System.out.println(after);

        // 加减运算(plusDays, minusDays)
        // 加上天数
        LocalDate localDate = now.plusDays(10);
        System.out.println("今天加上10天: " + localDate);

        // 加上月数
        LocalDate localDate1 = now.plusMonths(12);
        System.out.println("今天加上12个月: " + localDate1);

        // 加上星期数
        LocalDate localDate2 = now.plusWeeks(3);
        System.out.println("今天加上3个星期: " + localDate2);

        // 加上年数
        LocalDate localDate3 = now.plusYears(2);
        System.out.println("今天加上2年: " + localDate3);

        // 取某个固定的日期，使用with方法，TemporalAdjusters定义好了很好简便方法
        /**
         * finding the first or last day of the month：取当月的第一天
         * finding the first day of next month：取下月的第一天
         * finding the first or last day of the year：取当年的第一天
         * finding the first day of next year：取下一年的第一天
         * finding the next or previous day-of-week, such as "next Thursday"：取下(上)周的某一天
         */
        LocalDate with = now.with(TemporalAdjusters.firstDayOfMonth());
        System.out.println("当月的第一天: " + with);
        LocalDate with1 = now.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println("当月的最后一天: " + with1);
    }

    /**
     * LocalTime：表示时分秒
     */
    @Test
    public void localTimeTest() {
        LocalTime now = LocalTime.now();
        System.out.println("now(默认包含纳秒): " + now);

        LocalTime noNano = LocalTime.now().withNano(0);
        System.out.println("now(不用纳秒的时分秒): " + noNano);
        
        // 其它常规操作同LocalDate
    }

    /**
     * LocalDateTime：年月日时分秒
     */
    @Test
    public void localDateTime() {
        LocalDate now = LocalDate.now();
        // 取年-月-日 00:00:00，即一天的开始
        LocalDateTime begin = now.atTime(0, 0, 0);
        System.out.println("一天的开始: " + begin);

        // 取年-月-日 23:00:00，即一天的结束
        LocalDateTime end = now.atTime(23, 59, 59);
        System.out.println("一天的结束: " + end);
        
        // 格式化输出
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String format = begin.format(dateTimeFormatter);
        System.out.println("格式化后的日期：" + format);
    }

    /**
     * Instant表示某个具体的时刻，与Date有些类似，不过Date最多可以表示毫秒级别的时刻，而 Instant 可以表示纳秒级别的时刻
     */
    @Test
    public void instantTest(){
        Instant now = Instant.now();
        System.out.println("当前instant: " + now);
        long epochSecond = now.getEpochSecond();
        System.out.println("时间戳为: " + epochSecond);
        int nano = now.getNano();
        System.out.println("时间戳的纳秒为: " + nano);

        Instant instant = Instant.ofEpochSecond(10000000);
        System.out.println(instant);
    }

    /**
     * 以年月日的形式表示一个时间段，粒度较粗
     */
    @Test
    public void periodTest() {
        LocalDate of = LocalDate.of(2019, 1, 10);
        LocalDate of1 = LocalDate.of(2018, 5, 18);
        // 因为Period是以年月日的形式表示一段时间差，所以between方法的类型只能为LocalDate
        Period period = Period.between(of1, of);
        int years = period.getYears();
        int months = period.getMonths();
        int days = period.getDays();
        // 特别注意理解这里的输出
        System.out.println("这段时间相差多少年: " + years);
        System.out.println("除去相差的年部分外，这段时间相差多少月: " + months);
        System.out.println("除去相差的年和月部分外，这段时间相差多少日: " + days);
        
        // 下面好理解些
        System.out.println("这段时间相差 " + years + " 年, " + months + " 个月，" + days +  "天");
    }

    /**
     * Duration：与Period一样都是表示一段时间，只是Duration以秒、毫秒的方式来表示，粒度更细
     */
    @Test
    public void durationTest() {
        LocalDateTime of = LocalDateTime.of(2019, 11, 10, 10, 12, 25);
        LocalDateTime of1 = LocalDateTime.of(2018, 1, 8, 9, 10, 12);
        // 表示2017-01-08 09:10:12与2019-11-10 10:12:25这段时间
        Duration duration = Duration.between(of, of1);
        System.out.println("这段时间为：" + duration);

        long days = duration.toDays();
        long hours = duration.toHours();
        long minutes = duration.toMinutes();
        long millis = duration.toMillis();
        System.out.println("这段时间总共有(两个时间相差)多少天: " + days);
        System.out.println("这段时间总共有(两个时间相差)多少小时: " + hours);
        System.out.println("这段时间总共有(两个时间相差)多少分钟: " + minutes);
        System.out.println("这段时间总共有(两个时间相差)多少毫秒: " + millis);
    }
}
