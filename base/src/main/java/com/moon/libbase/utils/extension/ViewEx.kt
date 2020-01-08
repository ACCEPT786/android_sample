package com.moon.libbase.utils.extension

import android.text.Editable
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.EditText
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

/**
 * @author ry
 * @date 2019-12-12
 */

/**
 * editText 一键清除功能
 */
fun EditText.setEditWithClearIcon(clearView: View){
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
        }

        override fun beforeTextChanged(
            s: CharSequence?,
            start: Int,
            count: Int,
            after: Int
        ) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if (s.isNullOrEmpty()) {
                clearView.visibility = View.GONE
            } else {
                clearView.visibility = View.VISIBLE
            }
        }
    })

    this.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
        if (hasFocus && this.text.toString().isNotEmpty()) {
            clearView.visibility = View.VISIBLE
        } else {
            clearView.visibility = View.GONE
        }
    }

    clearView.setOnClickListener {
        this.setText("")
    }
}


fun EditText.showPassword(isShow: Boolean){
    if (isShow) {
        this.transformationMethod = HideReturnsTransformationMethod.getInstance()
    } else {
        this.transformationMethod = PasswordTransformationMethod.getInstance()
    }
    this.setSelection(this.text.length)
}

fun TabLayout.setupWithViewPager2(viewPager: ViewPager2, labels: List<String>) {

    if (labels.size != viewPager.adapter?.itemCount)
        throw Exception("The size of list and the tab count should be equal!")

    TabLayoutMediator(this, viewPager,
        TabLayoutMediator.TabConfigurationStrategy { tab, position ->
            tab.text = labels[position]
        }).attach()
}