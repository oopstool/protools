package com.github.oopstool.date;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

/**
 * 时间工具测试类
 *
 * @author : HouGY
 * @since : 2021/3/17
 */
public class DateUtilsTest {

    @Test
    public void convertTimeToString() {
        String timeToString = DateUtils.convertTimeToString(System.currentTimeMillis(), DatePattern.NORM_DATETIME_PATTERN);
        Assert.assertNotNull(timeToString);
        System.out.println(timeToString);
    }


    @Test
    public void convertSecTimeToString() {
        String convertSecTimeToString = DateUtils
            .convertSecTimeToString(System.currentTimeMillis() / 1000, DatePattern.NORM_DATETIME_MS_PATTERN);
        Assert.assertNotNull(convertSecTimeToString);
        System.out.println(convertSecTimeToString);
    }

    @Test
    public void getYear() {
        int year = DateUtils.getYear();
        assert year==2021;
    }

    @Test
    public void getMonth() {
        int month = DateUtils.getMonth();
        assert month==3;
    }

    @Test
    public void getDayOfMonth() {
        int dayOfMonth = DateUtils.getDayOfMonth();
        assert dayOfMonth==24;
    }

    @Test
    public void format() {
        String format = DateUtils.format(new Date(), DatePattern.NORM_DATETIME_PATTERN);

        String format1 = DateUtils.format(LocalDateTime.now(), DatePattern.NORM_DATETIME_PATTERN);
        System.out.println(format);
        System.out.println(format1);
    }


    @Test
    public void parseByPattern() {
        LocalDateTime localDateTime = DateUtils
            .parseByPattern("2021-03-24 15:50:41", DatePattern.NORM_DATETIME_PATTERN);
        System.out.println(localDateTime.toString());
    }

    @Test
    public void addMinutes() {
        Date date = DateUtils.addMinutes(new Date(), 2L);
        System.out.println(DateUtils.format(date,DatePattern.PURE_DATETIME_PATTERN));
    }

    @Test
    public void addHour() {
        Date date = DateUtils.addHour(new Date(), 1L);
        System.out.println(DateUtils.format(date,DatePattern.NORM_DATETIME_PATTERN));
    }

    @Test
    public void addMonth() {
        Date date = DateUtils.addMonth(1);
        System.out.println(DateUtils.format(date,DatePattern.NORM_DATETIME_PATTERN));
    }

    @Test
    public void addDay() {
        Date date = DateUtils.addDay(new Date(),1);
        System.out.println(DateUtils.format(date,DatePattern.NORM_DATETIME_PATTERN));
    }

    @Test
    public void addWeek() {
        Date date = DateUtils.addWeek(1);
        System.out.println(DateUtils.format(date,DatePattern.NORM_DATETIME_PATTERN));
    }

    @Test
    public void testAddMonth() {
        Date date = DateUtils.addMonth(DateUtils.parseDateByPattern("2021-03-22 15:50:41",DatePattern.NORM_DATETIME_PATTERN),1);
        System.out.println(DateUtils.format(date,DatePattern.NORM_DATETIME_PATTERN));
    }

    @Test
    public void getStartTime() {
        Date startTime = DateUtils.getStartTime();
        System.out.println(DateUtils.format(startTime,DatePattern.NORM_DATETIME_PATTERN));
    }

    @Test
    public void getEndTime() {
        Date startTime = DateUtils.getEndTime();
        System.out.println(DateUtils.format(startTime,DatePattern.NORM_DATETIME_PATTERN));
    }

    @Test
    public void localDate2Date() {
        Date date = DateUtils.localDate2Date(LocalDate.now());
        System.out.println(DateUtils.format(date,DatePattern.NORM_DATETIME_PATTERN));
    }

    @Test
    public void localDateTime2Date() {
        Date date = DateUtils.localDateTime2Date(LocalDateTime.now());
        System.out.println(DateUtils.format(date,DatePattern.NORM_DATETIME_PATTERN));
    }

    @Test
    public void getFirstDayOfCurrentYear() {
        String firstDayOfCurrentYear = DateUtils.getFirstDayOfCurrentYear(DatePattern.NORM_DATETIME_PATTERN);
        System.out.println(firstDayOfCurrentYear);
    }

    @Test
    public void getLastMonthFirstDayOfPreviousYear() {
        String firstDayOfCurrentYear = DateUtils.getLastMonthFirstDayOfPreviousYear(DatePattern.NORM_DATETIME_PATTERN);
        System.out.println(firstDayOfCurrentYear);
    }

    @Test
    public void getLastMonthLastDayOfPreviousYear() {
        String lastMonthLastDayOfPreviousYear = DateUtils
            .getLastMonthLastDayOfPreviousYear(DatePattern.NORM_DATE_PATTERN);
        System.out.println(lastMonthLastDayOfPreviousYear);
    }

    @Test
    public void getFirstDayOfMonth() {
        String firstDayOfMonth = DateUtils.getFirstDayOfMonth(new Date(), DatePattern.NORM_DATE_PATTERN);
        System.out.println(firstDayOfMonth);
    }

    @Test
    public void getCurrentDay() {
        String currentDay = DateUtils.getCurrentDay(DatePattern.NORM_DATETIME_PATTERN);
        System.out.println(currentDay);
    }

    @Test
    public void convertTimeToLong() throws ParseException {
        Long aLong = DateUtils.convertTimeToLong("2021-03-24 16:13:33", DatePattern.NORM_DATETIME_PATTERN);
        System.out.println(aLong);
    }

    @Test
    public void convertTimeToSecLong() throws ParseException {
        Long aLong = DateUtils.convertTimeToSecLong("2021-03-24 16:13:33", DatePattern.NORM_DATETIME_PATTERN);
        System.out.println(aLong);
    }

    @Test
    public void testGetMonth() {
        int month = DateUtils.getMonth();
        System.out.println(month);
    }


    @Test
    public void getToday() {
    }

    @Test
    public void getYesterday() {
        String yesterday = DateUtils.getYesterday(DatePattern.NORM_DATE_PATTERN);
        System.out.println(yesterday);
    }




    @Test
    public void getLastDayOfMonth() {
        String lastDayOfMonth = DateUtils.getLastDayOfMonth(new Date(),DatePattern.NORM_DATE_PATTERN);
        System.out.println(lastDayOfMonth);
    }

    @Test
    public void getLastMonth() {

    }

    @Test
    public void getDateOfLastMonth() {
    }

    @Test
    public void testGetDateOfLastMonth() {
    }


    @Test
    public void currentTimeMillis() {
        long timeMillis = DateUtils.currentTimeMillis();
        System.out.println(timeMillis);
    }

    @Test
    public void formatSecTime() {
        String formatSecTime = DateUtils.formatSecTime(DateUtils.currentTimeMillis() / 1000);
        System.out.println(formatSecTime);
    }

    @Test
    public void getDatesNumBetween() {
        long datesNumBetween = DateUtils.getDatesNumBetween(LocalDate.parse("2021-03-01"), LocalDate.now());
        System.out.println(datesNumBetween);
    }

    @Test
    public void getDatesBetween() {
        List<LocalDate> datesBetween = DateUtils.getDatesBetween(LocalDate.parse("2021-03-01"), LocalDate.now());
        System.out.println(datesBetween.toString());
    }

    @Test
    public void parseDateByPattern() {
        Date date = DateUtils.parseDateByPattern("2021-03-01", DatePattern.NORM_DATE_PATTERN);
        System.out.println(date.toString());
    }
}
