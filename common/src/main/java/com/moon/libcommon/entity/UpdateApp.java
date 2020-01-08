package com.moon.libcommon.entity;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UpdateApp implements Serializable {
    @SerializedName("ifUpdate")
    private boolean ifupdate;//是否有更新

    private boolean forcedUpdate;//是否强制更新
    @SerializedName("appName")
    private String appname;//名字
    @SerializedName("resName")
    private String appurl;//下载地址
    @SerializedName("appVersion")
    private String appversion;//版本名
    @SerializedName("versionCode")
    private String versioncode;//版本号
    @SerializedName("updateComment")
    private String updateComment;//更新内容

    public boolean isIfupdate() {
        return ifupdate;
    }

    public void setIfupdate(boolean ifupdate) {
        this.ifupdate = ifupdate;
    }

    public boolean isForcedUpdate() {
        return forcedUpdate;
    }

    public void setForcedUpdate(boolean forcedUpdate) {
        this.forcedUpdate = forcedUpdate;
    }

    public String getAppname() {
        return appname;
    }

    public void setAppname(String appname) {
        this.appname = appname;
    }

    public String getAppurl() {
        return appurl;
    }

    public void setAppurl(String appurl) {
        this.appurl = appurl;
    }

    public String getAppversion() {
        return appversion;
    }

    public void setAppversion(String appversion) {
        this.appversion = appversion;
    }

    public String getVersioncode() {
        return versioncode;
    }

    public void setVersioncode(String versioncode) {
        this.versioncode = versioncode;
    }

    public String getUpdateComment() {
        return updateComment;
    }

    public void setUpdateComment(String updateComment) {
        this.updateComment = updateComment;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
