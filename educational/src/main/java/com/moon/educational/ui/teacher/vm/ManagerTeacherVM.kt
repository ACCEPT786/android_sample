package com.moon.educational.ui.teacher.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.moon.educational.http.EducationalRepo
import com.moon.libbase.base.net.NetworkState
import com.moon.libcommon.entity.Teacher
import com.moon.libcommon.http.NetworkStateObserver
import javax.inject.Inject

/**
 * @author ry
 * @date 2019-12-20
 */
class ManagerTeacherVM @Inject constructor(private val repo: EducationalRepo) : ViewModel() {

    val teacherList = MutableLiveData<List<Teacher>?>()

    val netState = MutableLiveData<NetworkState>()

    fun getTeacherList(key: String?) {
        repo.getTeacherList(key, object : NetworkStateObserver<List<Teacher>>(netState) {
            override fun onSuccess(result: List<Teacher>?) {
                if (result.isNullOrEmpty()) {
                    netState.value = NetworkState.EMPTY
                } else {
                    teacherList.value = result
                    netState.value = NetworkState.SUCCESS
                }
            }
        })
    }

}