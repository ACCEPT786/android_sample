package com.moon.libbase.base

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.moon.libbase.R
import com.moon.libbase.base.net.NetworkState
import com.moon.libbase.base.net.Status
import com.moon.libbase.widget.RefreshViewCallBack
import com.moon.libbase.widget.dialog.ProgressDialog
import com.moon.libbase.widget.dialog.ProgressDialogFactory

abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity() {

    abstract val layoutId: Int
    lateinit var dataBinding: T
    //标记是否需要binding
    open fun hasBinding() = true

    var dialog: ProgressDialog? = null
    private var isFirstShow = true


    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        beforeOnCreate()
        super.onCreate(savedInstanceState)
        if (hasBinding()) {
            dataBinding = DataBindingUtil.setContentView(this, layoutId)
        } else {
            setContentView(layoutId)
        }
        ActivityManager.getInstance().addActivity(this)
        initStatusBar()
        initToolBar()
        initView()
        initData()
        initListener()
    }

    fun setupWithProgress(liveData: LiveData<Boolean>) {
        liveData.observe(this, Observer {
            if (it) {
                showDialog()
            } else {
                dismissDialog()
            }
        })
    }

    fun setupWithRefreshRecycler(
        state: LiveData<NetworkState>,
        refreshLayout: RefreshViewCallBack
    ) {
        state.observe(this, Observer {
            if (it.status != Status.LOADING) {
                refreshLayout.finishRefresh()
            }
            if (isFirstShow) {
                when (it.status) {
                    Status.LOADING -> refreshLayout.showLoading()
                    Status.SUCCESS -> {
                        refreshLayout.showContent()
                        isFirstShow = false
                    }
                    Status.EMPTY -> refreshLayout.showEmpty()
                    Status.FAILED, Status.NETERROR -> refreshLayout.showRetry()
                }
            }
        })
    }

    override fun onDestroy() {
        dismissDialog()
        ActivityManager.getInstance().removeActivity(this)
        super.onDestroy()
    }

    private fun showDialog() {
        if (dialog == null) {
            dialog = ProgressDialogFactory.Builder(this).build()
        }
        dialog?.show()
    }

    private fun dismissDialog() {
        if (dialog?.isShowing == true) {
            dialog?.dismiss()
        }
    }


    private fun initToolBar() {
        val toolbar: Toolbar? = findViewById(R.id.toolbar)
        toolbar?.let {
            setSupportActionBar(it)
            setupToolbar(it)
        }
    }


    /**
     * onCreate方法执行之前，用于一些初始化方法
     */
    open fun beforeOnCreate() {}

    /**
     * 状态栏操作
     */
    open fun initStatusBar() {}

    /**
     * 对toolbar的操作方法
     */
    open fun setupToolbar(toolbar: Toolbar) {}

    @CallSuper
    open fun initView() {
    }

    @CallSuper
    open fun initData() {
    }

    @CallSuper
    open fun initListener() {
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return true
    }

}

