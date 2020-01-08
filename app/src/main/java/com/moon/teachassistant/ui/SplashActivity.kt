package com.moon.teachassistant.ui

import android.Manifest
import android.os.Build
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.alibaba.android.arouter.launcher.ARouter
import com.moon.libbase.utils.permission.StartActivityUtil
import com.moon.libbase.utils.ui.AlertDialogUtils
import com.moon.libbase.utils.ui.WindowUtils
import com.moon.libcommon.base.BaseVMActivity
import com.moon.libcommon.sp.GSPSharedPreferences
import com.moon.libcommon.utils.ARouteAddress
import com.moon.libcommon.utils.PathUtils
import com.moon.teachassistant.R
import com.moon.teachassistant.databinding.ActivitySplashBinding
import com.moon.teachassistant.vm.SplashViewModel
import pub.devrel.easypermissions.EasyPermissions


class SplashActivity : BaseVMActivity<ActivitySplashBinding, SplashViewModel>(),
    EasyPermissions.PermissionCallbacks {
    override val layoutId: Int
        get() = R.layout.activity_splash

    private var resumeByOtherActivity = false

    override fun initView() {
        super.initView()
        viewModel = ViewModelProvider(this, viewModelFactory).get(SplashViewModel::class.java)
        init()
        WindowUtils.setScreenFull(window)
    }


    override fun onResume() {
        super.onResume()
        if (resumeByOtherActivity) {
            resumeByOtherActivity = false
            init()
        }
    }


    private fun init() {
        if (EasyPermissions.hasPermissions(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            if (!PathUtils.getInstance().isIsinit) {
                PathUtils.getInstance().initDirs(applicationContext)
            }
        } else {
            EasyPermissions.requestPermissions(
                this,
                getString(R.string.app_Storage_Permission),
                1000,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            return
        }

        if (!EasyPermissions.hasPermissions(this, Manifest.permission.READ_PHONE_STATE)) {
            EasyPermissions.requestPermissions(
                this,
                getString(R.string.app_Phone_Permission),
                1000,
                Manifest.permission.READ_PHONE_STATE
            )
            return
        }

        //权限处理后，开始处理逻辑
        viewModel.checkLogin()
        viewModel.startWelcome.observe(this, Observer {
            if (it) {
                ARouter.getInstance()
                    .build(ARouteAddress.ACCOUNT_ACT_LOGIN)
                    .navigation()
                GSPSharedPreferences.getInstance().clearUserInfo()
                finish()
            }
        })
        viewModel.startMain.observe(this, Observer {
            if (it) {
                //token登录下，EXTRA_PWD_DIALOG为false，即永不显示修改密码弹框
                ARouter.getInstance()
                    .build(ARouteAddress.APP_MAIN)
                    .navigation()
                finish()
            }
        })
    }


    override fun onPermissionsGranted(requestCode: Int, perms: List<String>) {
        if (requestCode == 1000) {
            init()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    private fun showAlwaysDeniedDialog(message: String) {
        AlertDialogUtils.showNormalAlert(
            this,
            getString(R.string.permission_tip),
            message,
            { _, _ ->
                resumeByOtherActivity = true
                StartActivityUtil.startPermissionActivity(
                    StartActivityUtil.START_APP_SETTING,
                    this@SplashActivity
                )
            },
            { _, _ -> finish() },
            false
        )
    }

    override fun onPermissionsDenied(requestCode: Int, perms: List<String>) {
        if (requestCode == 1000) {
            for (perm in perms) {
                if (perm == Manifest.permission.WRITE_EXTERNAL_STORAGE) {
                    if (Build.VERSION.SDK_INT > 23) {
                        if (EasyPermissions.somePermissionDenied(this, perm)) {
                            EasyPermissions.requestPermissions(
                                this,
                                getString(R.string.app_Storage_Permission),
                                1000,
                                perm
                            )
                        } else {
                            showAlwaysDeniedDialog(getString(R.string.app_Storage_Permission))
                        }
                    } else {
                        showAlwaysDeniedDialog(getString(R.string.app_Storage_Permission))
                    }
                } else if (perm == Manifest.permission.READ_PHONE_STATE) {
                    if (Build.VERSION.SDK_INT > 23) {
                        if (EasyPermissions.somePermissionDenied(this, perm)) {
                            EasyPermissions.requestPermissions(
                                this,
                                getString(R.string.app_Phone_Permission),
                                1000,
                                perm
                            )
                        } else {
                            showAlwaysDeniedDialog(getString(R.string.app_Phone_Permission))
                        }
                    } else {
                        showAlwaysDeniedDialog(getString(R.string.app_Phone_Permission))
                    }
                }
            }
        }

    }
}
