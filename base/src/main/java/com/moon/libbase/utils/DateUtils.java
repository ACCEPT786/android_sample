package com.moon.libbase.utils;


import android.content.res.Resources;

import androidx.annotation.Nullable;

import com.moon.libbase.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Created by Ben on 2015/3/11.
 */
public class DateUtils {
    public static final long MILLIS_IN_MINUTES = 1000 * 60;
    public static final long MILLIS_IN_HOUR = MILLIS_IN_MINUTES * 60;
    public static final long MILLIS_IN_DAY = MILLIS_IN_HOUR * 24;
    public static final long MILLIS_IN_MONTH = MILLIS_IN_DAY * 30;
    public static final long MILLIS_IN_YEAR = MILLIS_IN_DAY * 365;


    public static String getTimestampString(long timeMillis) {
        boolean isToady = android.text.format.DateUtils.isToday(timeMillis);
        String dateString;
        if (isToady) {
            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
            dateString = formatter.format(timeMillis);
            dateString = dateString.substring(0, 5);
        } else {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            dateString = formatter.format(timeMillis);
            dateString = dateString.substring(2, dateString.length());
        }
        return dateString;
    }

    public static String getTimeOfFormat(long timeMillis, String formatString) {
        return new SimpleDateFormat(formatString, Locale.getDefault()).format(timeMillis);
    }

    public static boolean isCloseEnough(long msgTime, long msgTime1) {
        if (Math.abs(msgTime1 - msgTime) < 30 * 1000)
            return true;
        return false;
    }

    @Nullable
    public static Date formatTimeDate(String time) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getTodayString() {
        return new SimpleDateFormat("yy/MM/dd").format(System.currentTimeMillis());
    }

    public static int getHourInterval(long msgTime) {
        long now = System.currentTimeMillis();
        long length = msgTime - now;
        int interval;
        if (length <= 0) {
            interval = 0;
        } else {
            interval = (int) (length / MILLIS_IN_HOUR) + 1;
        }
        return interval;
    }

    /**
     * 距离现在的时间
     *
     * @param msgTime
     * @return
     */

    public static String getNowString(Resources resources, long msgTime) {
        long now = System.currentTimeMillis();
        String format = resources.getString(R.string.timeLine);
        String unit;
        int time;
        long length = now - msgTime;
        if (length < 0 || length < MILLIS_IN_MINUTES) {
            return resources.getString(R.string.just_now);
        } else if (length < MILLIS_IN_HOUR) {
            unit = resources.getString(R.string.minute);
            time = (int) (length / MILLIS_IN_MINUTES);
        } else if (length < MILLIS_IN_DAY) {
            unit = resources.getString(R.string.hour);
            time = (int) (length / MILLIS_IN_HOUR);
        } else if (length < MILLIS_IN_MONTH) {
            unit = resources.getString(R.string.day);
            time = (int) (length / MILLIS_IN_DAY);
        } else if (length < MILLIS_IN_YEAR) {
            unit = resources.getString(R.string.month);
            time = (int) (length / MILLIS_IN_MONTH);
        } else {
            unit = resources.getString(R.string.year);
            time = (int) (length / MILLIS_IN_YEAR);
        }
        return String.format(format, time, unit);

    }


    /**
     * 是否是新的一天
     *
     * @param ms1 老时间
     * @param ms2 新时间
     * @return
     */
    public static boolean isNewDay(final long ms1, final long ms2) {
        return (ms2 / MILLIS_IN_DAY) != (ms1 / MILLIS_IN_DAY);
    }

    /**
     * 年月日格式化
     *
     * @param year
     * @param month
     * @param day
     * @return
     */

    public static String getCalendarString(int year, int month, int day) {
        GregorianCalendar date = new GregorianCalendar(year, month, day);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(date.getTime());
    }

    public static String getCalendarString(long time) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(time);
    }


    public static String secToTime(long seconds) {
        long day = seconds / (3600 * 24);
        long hour = (seconds - day * 3600 * 24) / 3600;
        long minute = (seconds - day * 3600 * 24 - hour * 3600) / 60;
        long second = (seconds - day * 3600 * 24 - hour * 3600 - minute * 60);

        StringBuffer sb = new StringBuffer();
        if (day > 0) {
            sb.append(day + "天");
        }
        sb.append(hour + ":");

        if (minute > 0 && minute < 10) {
            sb.append("0" + minute + ":");
            if (second > 0 && second < 10) {
                sb.append("0" + second);
            } else if (second > 9) {
                sb.append(second);
            } else if (second == 0) {
                sb.append("00");
            }
        } else if (minute > 9) {
            sb.append(minute + ":");
            if (second > 0 && second < 10) {
                sb.append("0" + second);
            } else if (second > 9) {
                sb.append(second);
            } else if (second == 0) {
                sb.append("00");
            }
        } else {
            if (second > 0 && second < 10) {
                sb.append("00:0" + second);
            } else if (second > 9) {
                sb.append("00:" + second);
            } else if (second == 0) {
                sb.append("00:00");
            }
        }


        return sb.toString();
    }

    public static String secToTimeString(long seconds) {
        long day = seconds / (3600 * 24);
        long hour = (seconds - day * 3600 * 24) / 3600;
        long minute = (seconds - day * 3600 * 24 - hour * 3600) / 60;
        long second = (seconds - day * 3600 * 24 - hour * 3600 - minute * 60);

        StringBuffer sb = new StringBuffer();
        if (day > 0) {
            sb.append(day).append("天");
        }
        if (hour > 0) {
            sb.append(hour).append("小时");
        }
        if (minute > 0) {
            sb.append(minute).append("分");
        }
        sb.append(second).append("秒");
        return sb.toString();
    }

    public static int getConstellation(int month, int day) {
        int[] DayArr = {22, 20, 19, 21, 20, 21, 22, 23, 23, 23, 24, 23};  // 两个星座分割日
        int index = (month) % 12;
        if (day < DayArr[index]) {
            index = index - 1;
        }
        if (index < 0) {
            return index + 12;
        }
        return index % 12;
    }


    public static boolean chekTimeThreeday(long uploadtime, long nowtime, long error_time) {
        if ((nowtime - uploadtime) > error_time) {
            return true;
        } else {
            return false;
        }
    }
}
