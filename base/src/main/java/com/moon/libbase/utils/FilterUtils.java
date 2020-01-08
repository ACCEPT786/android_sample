package com.moon.libbase.utils;

import android.text.TextUtils;

/**
 * @author ry
 * @date 2019-05-16
 * 字符串验证
 */
public class FilterUtils {

    /**
     * 手机号匹配
     * 规则: 以1开头的11位数字
     */
    public static boolean isPhone(String phone) {
        if (TextUtils.isEmpty(phone)){
            return false;
        }
        return phone.matches("^[1][0-9]{10}$");
    }

    /**
     * 密码匹配
     * 规则：6-12位中英文或数字，不能是纯数字
     */
    public static boolean isPassword(String password) {
        if (TextUtils.isEmpty(password)) {
            return false;
        }
        return !password.matches("^\\d{6,12}$") && password.matches("^\\w{6,12}$");
    }
}
