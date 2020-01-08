package com.moon.libbase;

import com.moon.libbase.utils.DateUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * @author ry
 * @date 2019-10-17
 */
public class DateUtilsTest {

    private long time;
    @Before
    public void beforeTest(){
        System.out.println("before test");
        time = 1221;
    }

    @After
    public void afterTest(){
        System.out.println("after test");
    }

    @Test
    public void testSecToTimeString(){
        String result = DateUtils.secToTimeString(time);
        System.out.println(result);

        try {
            Date parse = new SimpleDateFormat("yyyy-MM-dd").parse("2019-11-18");
            System.out.println(parse.getTime());
            Date date = new Date(parse.getTime() + DateUtils.MILLIS_IN_DAY * 6);
            System.out.println(DateUtils.getTimeOfFormat(date.getTime(), "MM/dd"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        assertEquals("10分21秒", result);
    }

    @Test
    public void testSecToTime(){
        String result = DateUtils.secToTime(time);
        System.out.println(result);
    }


}
