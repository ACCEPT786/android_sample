package com.moon.libcommon.sp;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.moon.libbase.base.BaseApplication;
import com.moon.libcommon.entity.UserInfo;

import timber.log.Timber;


public class GSPSharedPreferences {
    private static GSPSharedPreferences instance;

    public static final String SP_NAME = "moon_teach_config";
    private static SharedPreferences sharedPreferences = null;
    //用户信息
    public static final String KEY_UID = "uid";
    public static final String KEY_TOKEN = "token";
    public static final String KEY_IDEN = "key_iden";//已登录身份：1普通老师，2管理员
    public static final String KEY_USERINFO = "key_user_info";//登录的用户信息

    //引导页保存的VERSIONCODE
    public static final String KEY_VERSIONCODE = "key_versioncode";
    private Gson gson = new Gson();

    static {
        instance = new GSPSharedPreferences(BaseApplication.Companion.getInstance());
    }

    private GSPSharedPreferences(Context context) {
        sharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
    }

    public static GSPSharedPreferences getInstance() {
        return instance;
    }

    /**
     * 清除用户信息
     */
    public void clearUserInfo() {
        //注销清除sp信息，包括用户信息，uid，token及身份信息
        sharedPreferences.edit()
                .remove(KEY_UID)
                .remove(KEY_TOKEN)
                .remove(KEY_IDEN)
                .remove(KEY_USERINFO)
                .apply();
    }


    public String getToken() {
        return sharedPreferences.getString(KEY_TOKEN, "");
    }

    public void setToken(String token) {
        sharedPreferences.edit().putString(KEY_TOKEN, token).apply();
    }

    public String getUid() {
        return sharedPreferences.getString(KEY_UID, "");
    }

    public void setUid(String uid) {
        sharedPreferences.edit().putString(KEY_UID, uid).apply();
    }


    /**
     * 引导页存储版本号
     */
    public long isFirstStart() {
        return sharedPreferences.getLong(KEY_VERSIONCODE, 0);
    }
    public void setFirstStart(long code) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(KEY_VERSIONCODE, code);
        editor.apply();
    }

    /**
     * 存储用户信息的json
     */
    public void setUserInfo(UserInfo userInfo) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USERINFO, gson.toJson(userInfo));
        editor.apply();
    }

    /**
     * 获取用户信息
     */
    @Nullable
    public UserInfo getUserInfo() {
        String infoJson = sharedPreferences.getString(KEY_USERINFO, "");
        if (infoJson.isEmpty()) {
            return null;
        }
        try {
            return gson.fromJson(infoJson, UserInfo.class);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }

}
