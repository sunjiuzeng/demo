package com.demo.constance;

/**
 * 缓存时长静态变量
 * Created by sunjz on 2017/4/7.
 */
public interface CacheTime {

    /**
     * 秒
     */
    int SECOND_5 = 5;

    int SECOND_10 = 10;

    int SECOND_15 = 15;

    int SECOND_20 = 20;

    int SECOND_30 = 30;

    int SECOND_45 = 45;

    int SECOND_60 = 60;

    /**
     * 分钟
     */
    int MINUTE_2 = 2 * 60;

    int MINUTE_5 = 5 * 60;

    int MINUTE_10 = 10 * 60;

    int MINUTE_15 = 15 * 60;

    int MINUTE_20 = 20 * 60;

    int MINUTE_30 = 30 * 60;

    int MINUTE_45 = 45 * 60;

    int MINUTE_60 = 60 * 60;

    /**
     * 小时
     */
    int HOUR_2 = 2 * 60 * 60;

    int HOUR_6 = 6 * 60 * 60;

    int HOUR_12 = 12 * 60 * 60;

    int HOUR_24 = 24 * 60 * 60;

    /**
     * 天
     */
    int DAY_2 = 2 * 24 * 60 * 60;

    int DAY_5 = 2 * 24 * 60 * 60;

    int DAY_7 = 2 * 24 * 60 * 60;

    int DAY_15 = 2 * 24 * 60 * 60;

    int DAY_30 = 2 * DAY_15;

    int MONTH_3 = 3 * DAY_30;

}
