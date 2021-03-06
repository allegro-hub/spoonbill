package org.allegro.spoonbill.test.asserts;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;

public class DateAssert {

    public static void assertEqual(Date expected, Date actual) {

        Calendar expectedCalendar = Calendar.getInstance();
        expectedCalendar.setTime(expected);

        Calendar actualCalendar = Calendar.getInstance();
        actualCalendar.setTime(actual);

        Assert.assertEquals(expectedCalendar.get(Calendar.YEAR), actualCalendar.get(Calendar.YEAR));
        Assert.assertEquals(expectedCalendar.get(Calendar.MONTH), actualCalendar.get(Calendar.MONTH));
        Assert.assertEquals(expectedCalendar.get(Calendar.DATE), actualCalendar.get(Calendar.DATE));
    }

    public static void assertNotEqual(Date expected, Date actual) {

        Calendar expectedCalendar = Calendar.getInstance();
        expectedCalendar.setTime(expected);

        Calendar actualCalendar = Calendar.getInstance();
        actualCalendar.setTime(actual);

        if (expectedCalendar.get(Calendar.YEAR) == actualCalendar.get(Calendar.YEAR)
                && expectedCalendar.get(Calendar.MONTH) == actualCalendar.get(Calendar.MONTH)
                && expectedCalendar.get(Calendar.DATE) == actualCalendar.get(Calendar.DATE)) {

            Assert.fail("Both date are the same");
        }
    }

    public static void assertEqual(LocalDate expected, LocalDate actual) {

        Assert.assertEquals(expected.getYear(), actual.getYear());
        Assert.assertEquals(expected.getMonth(), actual.getMonth());
        Assert.assertEquals(expected.getDayOfMonth(), actual.getDayOfMonth());
    }

    public static void assertNotEqual(LocalDate expected, LocalDate actual) {

        if (expected.getYear() == actual.getYear() && expected.getMonth() == actual.getMonth()
                && expected.getDayOfMonth() == actual.getDayOfMonth()) {

            Assert.fail("Both date are the same");
        }
    }
}
