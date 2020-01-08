package com.moon.libbase.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author ry
 * @date 2019-11-12
 * 数学工具类
 */
public class MathUtil {
    /**
     * double类型四舍五入
     */
    public static BigDecimal RoundValue(double value, int newScale) {
        BigDecimal bd = BigDecimal.valueOf(value);
        return bd.setScale(newScale, RoundingMode.HALF_UP);
    }

}
