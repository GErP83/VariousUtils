package com.gerp83.variousutils;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateUtils {

    /**
     * converts two Date's difference into years, months, weeks, days, hours, minutes or seconds
     * always one value is > 0
     *
     * @param from first Date
     * @param to second Date
     * @return Long array of [years, months, weeks, days, hours, minutes, seconds]
     */
    public static long[] timeDiff(Date from, Date to){
        long[] longArray = {0, 0, 0, 0, 0, 0, 0};
        int increment = 0;
        long years;
        long months;
        long days;

        Calendar fromDate = Calendar.getInstance();
        Calendar toDate = Calendar.getInstance();
        fromDate.setTime(from);
        toDate.setTime(to);

        if (fromDate.get(Calendar.DAY_OF_MONTH) > toDate.get(Calendar.DAY_OF_MONTH)) {
            increment = fromDate.getActualMaximum(Calendar.DAY_OF_MONTH);
        }
        if (increment != 0) {
            days = toDate.get(Calendar.DAY_OF_MONTH) + increment - fromDate.get(Calendar.DAY_OF_MONTH);
            increment = 1;
        } else {
            days = toDate.get(Calendar.DAY_OF_MONTH) - fromDate.get(Calendar.DAY_OF_MONTH);
        }
        if (fromDate.get(Calendar.MONTH) + increment > toDate.get(Calendar.MONTH)) {
            months = toDate.get(Calendar.MONTH) + 12 - (fromDate.get(Calendar.MONTH) + increment);
            increment = 1;
        } else {
            months = toDate.get(Calendar.MONTH) - (fromDate.get(Calendar.MONTH) + increment);
            increment = 0;
        }

        years = toDate.get(Calendar.YEAR) - (fromDate.get(Calendar.YEAR) + increment);
        if (years >= 2) {
            longArray[0] = years;
            return longArray;

        } else if (years == 1) {
            longArray[0] = 1;
            return longArray;
        }
        if (months >= 2) {
            longArray[1] = months;
            return longArray;

        } else if (months == 1) {
            longArray[1] = 1;
            return longArray;
        }
        if (days > 0) {
            if (days > 7) {
                long weeks = days / 7;
                if (weeks >= 2) {
                    longArray[2] = weeks;
                    return longArray;

                } else {
                    longArray[2] = 1;
                    return longArray;
                }
            }
            if (days >= 2) {
                longArray[3] = days;
                return longArray;

            } else {
                longArray[3] = 1;
                return longArray;
            }
        }

        long diffInMills = Math.abs(from.getTime() - to.getTime());
        long hours = TimeUnit.HOURS.convert(diffInMills, TimeUnit.MILLISECONDS);
        if (hours >= 2L) {
            longArray[4] = hours;
            return longArray;

        } else if (hours == 1L) {
            longArray[4] = 1;
            return longArray;
        }

        long minutes = TimeUnit.MINUTES.convert(diffInMills, TimeUnit.MILLISECONDS);
        if (minutes >= 2L) {
            longArray[5] = minutes;
            return longArray;

        } else if (minutes == 1L) {
            longArray[5] = 1;
            return longArray;
        }

        long seconds = diffInMills / 1000;
        if (seconds >= 2L) {
            longArray[6] = seconds;
            return longArray;

        } else {
            longArray[6] = seconds;
            return longArray;
        }
    }

}
