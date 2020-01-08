package com.moon.libaccount.ui.fragment


import android.content.Context
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.moon.libaccount.R
import com.moon.libaccount.viewmodel.CodeViewModel
import com.moon.libaccount.viewmodel.LoginViewModel
import com.moon.libcommon.base.BaseVMFragment
import kotlinx.android.synthetic.main.fragment_verify_code.*

/**
 * 验证码界面
 */
class VerifyCodeFragment : BaseVMFragment<ViewDataBinding, CodeViewModel>() {
    private var listener: VerifyCodeListener? = null
    override val layoutId: Int
        get() = R.layout.fragment_verify_code

    override fun hasBinding(): Boolean {
        return false
    }

    override fun onInject() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(CodeViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is VerifyCodeListener) {
            listener = context
        }
    }


    override fun initListener() {
        super.initListener()
        verifyCodeButton.setOnClickListener {
            listener?.let {
                val phone = it.getCodePhone()
                viewModel.getPhoneCode(phone)
            }
        }
    }

    override fun observerData() {
        super.observerData()
        viewModel.countDownTime.observe(this, Observer {
            if (it >= 0) {
                verifyCodeButton.isEnabled = false
                verifyCodeButton.text =
                    String.format(getString(R.string.resend_time_format), it.toString())
            } else {
                verifyCodeButton.isEnabled = true
                verifyCodeButton.setText(R.string.resend)
            }
        })
        setupWithProgress(viewModel.progressStatus)
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface VerifyCodeListener {
        fun getCodePhone(): String
    }
}
