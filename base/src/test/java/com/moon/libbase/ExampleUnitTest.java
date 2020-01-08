package com.moon.libbase;

import com.moon.libbase.utils.MathUtil;
import com.moon.libbase.utils.secret.Md5Util;
import com.moon.libbase.utils.system.SystemUtils;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import static kotlin.text.Typography.half;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void mathTest() {

//        float v = MathUtil.floatHalfUpValue(1.30f, 0);
//        System.out.println(v);
        double num = 90080070060.1d;
        System.out.println(round(num, 9));
        System.out.println(round2(num, 2));





    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    public static double round2(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}