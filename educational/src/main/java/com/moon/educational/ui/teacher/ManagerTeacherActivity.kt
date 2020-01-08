package com.moon.educational.ui.teacher

import android.view.Menu
import android.view.MenuItem
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.moon.educational.R
import com.moon.educational.adapter.TeacherListAdapter
import com.moon.educational.ui.teacher.vm.ManagerTeacherVM
import com.moon.libcommon.base.BaseVMActivity
import com.moon.libcommon.utils.ARouteAddress
import com.moon.libcommon.utils.ARouteAddress.EDU_ADD_TEACHER
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import kotlinx.android.synthetic.main.layout_search_view.*

@Route(path = ARouteAddress.EDU_MANAGER_TEACHER)
class ManagerTeacherActivity : BaseVMActivity<ViewDataBinding, ManagerTeacherVM>() {
    override fun hasBinding(): Boolean {
        return false
    }

    override val layoutId: Int
        get() = R.layout.activity_manager_teacher

    private val adapter = TeacherListAdapter()

    override fun onInject() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(ManagerTeacherVM::class.java)
    }

    override fun initView() {
        super.initView()
        refreshRecycler.setAdapter(adapter)
    }

    override fun observerData() {
        super.observerData()
        viewModel.teacherList.observe(this, Observer {
            adapter.submitList(it)
        })
        setupWithRefreshRecycler(viewModel.netState, refreshRecycler)
    }

    override fun initData() {
        super.initData()
        viewModel.getTeacherList(null)
    }

    override fun initListener() {
        super.initListener()
        refreshRecycler.setOnRefreshListener(OnRefreshListener {
            viewModel.getTeacherList(null)
        })
        adapter.clickListener = {
            ARouter.getInstance().build(ARouteAddress.EDU_TEACHER_DETAIL)
                .withParcelable(ARouteAddress.EXTRA_TEACHER, it)
                .navigation()
        }

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_teacher, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.add_teacher) {
            ARouter.getInstance().build(EDU_ADD_TEACHER)
                .navigation()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}
