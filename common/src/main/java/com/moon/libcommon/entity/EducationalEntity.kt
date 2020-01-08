package com.moon.libcommon.entity

import android.os.Parcel
import android.os.Parcelable

/**
 * @author ry
 * @date 2019-12-21
 */
data class Teacher(
    var teacherId: String?,
    var name: String?,
    var gender: Int,
    var phone: String?,
    var status: Int,
    var subjectName: String?,
    var monthClassHour: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(teacherId)
        parcel.writeString(name)
        parcel.writeInt(gender)
        parcel.writeString(phone)
        parcel.writeInt(status)
        parcel.writeString(subjectName)
        parcel.writeInt(monthClassHour)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Teacher> {
        override fun createFromParcel(parcel: Parcel): Teacher {
            return Teacher(parcel)
        }

        override fun newArray(size: Int): Array<Teacher?> {
            return arrayOfNulls(size)
        }
    }

    fun getGenderString(): String {
        return when (gender) {
            1 -> "男"
            2 -> "女"
            else -> ""
        }
    }
}

data class AddTeacherRequest(
    var icon: String,
    var subjectList: List<Int>,
    var name: String,
    var phone: String,
    var gender: Int
)

data class TeacherClass(
    var capacity: Int,
    var classId: String,
    var courseid: String,
    var coursename: String,
    var name: String,
    var studentNumber: Int
)

data class TeacherStudent(
    var phone: String,
    var studentId: String,
    var studentName: String
)
