package org.oops.protools.core.date;

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
    public void testConvertTimeToString() {
    }

    @Test
    public void convertSecTimeToString() {
    }

    @Test
    public void getYear() {
    }

    @Test
    public void getMonth() {
    }

    @Test
    public void getMonthOfDay() {
    }

    @Test
    public void format() {
    }

    @Test
    public void testFormat() {
    }

    @Test
    public void parseByPattern() {
    }

    @Test
    public void addMinutes() {
    }

    @Test
    public void addHour() {
    }

    @Test
    public void addMonth() {
    }

    @Test
    public void addDay() {
    }

    @Test
    public void addWeek() {
    }

    @Test
    public void testAddMonth() {
    }

    @Test
    public void getStartTime() {
    }

    @Test
    public void getEndTime() {
    }

    @Test
    public void localDate2Date() {
    }

    @Test
    public void localDateTime2Date() {
    }

    @Test
    public void getFirstDayOfCurrentYear() {
    }

    @Test
    public void getLastMonthFirstDayOfPreviousYear() {
    }

    @Test
    public void getLastMonthLastDayOfPreviousYear() {
    }

    @Test
    public void getFirstDayOfMonth() {
    }

    @Test
    public void getCurrentDay() {
    }

    @Test
    public void convertTimeToLong() {
    }

    @Test
    public void convertTimeToSecLong() {
    }

    @Test
    public void testGetMonth() {
    }

    @Test
    public void parseDate() {
    }

    @Test
    public void formatDateTime() {
    }

    @Test
    public void getToday() {
    }

    @Test
    public void getYestoday() {
    }

    @Test
    public void getFirstDayOfThisMonth() {
    }

    @Test
    public void anyDay() {
    }

    @Test
    public void getAnyYesterday() {
    }

    @Test
    public void getLastDayOfMonth() {
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
    public void daysBetweenTwoDate() {
    }
}
