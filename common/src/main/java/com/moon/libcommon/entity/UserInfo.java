package com.moon.libcommon.entity;


import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

/**
 * @author ry
 * @date 2019-06-28
 */
public class UserInfo {
    private long uid;
    private String token;
    @SerializedName("icon")
    private String img; //头像
    private String nickname;//昵称,家长端直接就是手机号
    private String sex;//性别
    private String email;//邮箱
    private int userType;//登录类型 1、学生，2、老师，3、家长
    private boolean hasGuardPwd;//是否有监护密码
    private boolean hasBind;//是否绑定，暂时未用
    @Deprecated
    private Long bindUid;//绑定学生的uid
    private String subject;
    private String phone;//手机号
    private String schoolId;
    private String schoolName;
    private String studentId;
    private String classId;
    private String className;
    private boolean manager;//是否是班主任
    private String teacherId;

    private Integer gradeId;//年级id

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public boolean isHasGuardPwd() {
        return hasGuardPwd;
    }

    public void setHasGuardPwd(boolean hasGuardPwd) {
        this.hasGuardPwd = hasGuardPwd;
    }

    public boolean isHasBind() {
        return hasBind;
    }

    public void setHasBind(boolean hasBind) {
        this.hasBind = hasBind;
    }

    public boolean isManager() {
        return manager;
    }

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    @Nullable
    public Long getBindUid() {
        return bindUid;
    }

    public void setBindUid(Long bindUid) {
        this.bindUid = bindUid;
    }

    @Nullable
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getClassId() {
        return classId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public void setManager(boolean manager) {
        this.manager = manager;
    }


    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
