package com.moon.libcommon.base

import android.Manifest
import android.os.Build
import androidx.databinding.ViewDataBinding
import com.moon.libbase.base.BaseActivity
import com.moon.libbase.utils.permission.StartActivityUtil
import com.moon.libbase.utils.ui.AlertDialogUtils
import com.moon.libcommon.R
import com.moon.libcommon.utils.PathUtils
import pub.devrel.easypermissions.EasyPermissions

abstract class BaseCommonActivity<T : ViewDataBinding> : BaseActivity<T>(),
    EasyPermissions.PermissionCallbacks {


    override fun initView() {
        super.initView()
        initPermission()
    }

    private fun initPermission() {
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
    }

    override fun onPermissionsGranted(requestCode: Int, perms: List<String>) {
        if (requestCode == 1000) {
            if (!PathUtils.getInstance().isIsinit) {
                PathUtils.getInstance().initDirs(applicationContext)
            }
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
                StartActivityUtil.startAppSetting(this@BaseCommonActivity)
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
                }
            }
        }
    }

}

