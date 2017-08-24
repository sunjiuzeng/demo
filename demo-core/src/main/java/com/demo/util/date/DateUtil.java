package com.demo.util.date;

import java.util.Calendar;

/**
 * Desc:
 * User: sunjz
 * Email: jiuzeng.sun@leappmusic.com
 * Date: 2017/6/13
 * Time: 下午2:00
 * To change this template use File | Settings | File Templates.
 */
public class DateUtil {

    public static final int dayDuration = 24 * 60 * 60;

    /**
     * Gets monday of this week.
     *
     * @param time the time
     * @return the monday of this week
     */
    public static int getMondayOfThisWeek(long time) {
        return getMondayOfDiffWeek(time, 0);
    }

    /**
     * Gets sunday of this week.
     *
     * @param time the time
     * @return the sunday of this week
     */
    public static int getSundayOfThisWeek(long time) {
        return getSundayOfDiffWeek(time, 0);
    }

    /**
     * Gets monday of this week.
     *
     * @param time the time
     * @param diff the diff
     * @return the monday of this week
     */
    public static int getMondayOfDiffWeek(long time, int diff) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (dayOfWeek == 0) {
            dayOfWeek = 7;
        }

        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        calendar.add(Calendar.DATE, -dayOfWeek + 1 + (diff * 7));
        return convertSecondTime(calendar.getTimeInMillis());
    }

    /**
     * Gets sunday of this week.
     *
     * @param time the time
     * @param diff the diff
     * @return the sunday of this week
     */
    public static int getSundayOfDiffWeek(long time, int diff) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (dayOfWeek == 0) {
            dayOfWeek = 7;
        }

        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);

        calendar.add(Calendar.DATE, -dayOfWeek + 7 + (diff * 7));
        return convertSecondTime(calendar.getTimeInMillis());
    }

    /**
     * Gets today start time.
     *
     * @return the today start time
     */
    public static int getTodayStartTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return convertSecondTime(calendar.getTimeInMillis());
    }

    /**
     * Gets today end time.
     *
     * @return the today end time
     */
    public static int getTodayEndTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);

        return convertSecondTime(calendar.getTimeInMillis());
    }

    public static int getEndTimeByStartTimeAndDuration(int startTime, int duration){
        int time = startTime + duration;
        return time;
    }

    public static int getDayEndTimeByStartTime(int startTime){
        return getEndTimeByStartTimeAndDuration(startTime, dayDuration);
    }

    /**
     * Gets current time.
     *
     * @return the current time
     */
    public static int getCurrentTime() {
        return convertSecondTime(System.currentTimeMillis());
    }

    /**
     * Convert second time int.
     *
     * @param time the time
     * @return the int
     */
    public static int convertSecondTime(long time) {
        return (int) (time / 1000l);
    }

    /**
     * Convert milli second time long.
     *
     * @param time the time
     * @return the long
     */
    public static long convertMilliSecondTime(int time) {
        return ((long) time) * 1000l;
    }

}
