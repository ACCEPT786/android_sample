package com.moon.libaccount.ui

import android.graphics.Color
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.moon.libaccount.BuildConfig
import com.moon.libaccount.R
import com.moon.libaccount.databinding.ActivityLoginBinding
import com.moon.libaccount.ui.fragment.VerifyCodeFragment
import com.moon.libaccount.viewmodel.LoginViewModel
import com.moon.libbase.utils.ui.WindowUtils
import com.moon.libcommon.base.BaseVMActivity
import com.moon.libcommon.utils.ARouteAddress
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.fragment_verify_code.*

@Route(path = ARouteAddress.ACCOUNT_ACT_LOGIN)
class LoginActivity : BaseVMActivity<ActivityLoginBinding, LoginViewModel>(),
    VerifyCodeFragment.VerifyCodeListener {
    override val layoutId: Int
        get() = R.layout.activity_login

    private var codeLogin = true

    override fun onInject() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(LoginViewModel::class.java)
    }

    override fun initStatusBar() {
        super.initStatusBar()
        //黑色状态栏文字，白色状态栏颜色
        WindowUtils.setLightStatusBar(window)
        WindowUtils.setStatusBarColor(window, Color.WHITE)
    }

    override fun initView() {
        super.initView()
        switchVerityButton.text = getString(R.string.phone_pwd_login)
        versionView.text = "v${BuildConfig.VERSION_NAME}"
        val protocolText = SpannableString(getString(R.string.confirm_user_protocol))
        protocolText.setSpan(
            ForegroundColorSpan(
                ContextCompat.getColor(
                    this,
                    R.color.colorPrimary
                )
            ),
            protocolText.length - 6,
            protocolText.length,
            SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        protocolView.text = protocolText
    }

    override fun observerData() {
        viewModel.loginResult.observe(this, Observer {
            if (it) {
                ARouter.getInstance().build(ARouteAddress.APP_MAIN).navigation()
                finish()
            }
        })
        setupWithProgress(viewModel.progressStatus)
    }

    override fun initListener() {
        super.initListener()
        switchVerityButton.setOnClickListener {
            codeLogin = !codeLogin
            if (codeLogin) {
                switchVerityButton.text = getString(R.string.phone_pwd_login)
            } else {
                switchVerityButton.text = getString(R.string.phone_code_login)
            }
            verifyTypeSwitcher.showNext()
        }
        //用户协议跳转
        protocolView.setOnClickListener {
            ARouter.getInstance().build(ARouteAddress.ACCOUNT_USER_PROTOCOL).navigation()
        }
        loginButton.setOnClickListener {
            if (codeLogin) {
                startCodeLogin()
            } else {
                startPwdLogin()
            }
            //todo 删除跳转
            ARouter.getInstance().build(ARouteAddress.APP_MAIN).navigation()
        }
    }

    /**
     * 验证码登录
     */
    private fun startCodeLogin() {
        val phoneNum = phoneView.text.toString()
        val code = verifyCodeView.text.toString()
        viewModel.phoneCodeLogin(phoneNum, code)
    }

    /**
     * 密码登录
     */
    private fun startPwdLogin() {
        val phoneNum = phoneView.text.toString()
        val pwd = passwordView.text.toString()
        viewModel.phonePwdLogin(phoneNum, pwd)
    }

    override fun getCodePhone(): String {
        return phoneView.text.toString()
    }

}
