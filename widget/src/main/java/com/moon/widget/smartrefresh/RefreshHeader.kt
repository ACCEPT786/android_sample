package com.moon.widget.smartrefresh

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.moon.widget.R
import com.scwang.smartrefresh.layout.api.RefreshHeader
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.constant.RefreshState
import com.scwang.smartrefresh.layout.constant.SpinnerStyle
import com.scwang.smartrefresh.layout.internal.InternalAbstract
import timber.log.Timber

/**
 * @author ry
 * @date 2019-12-21
 * 刷新控件头部样式
 */
class RefreshHeader @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : InternalAbstract(context, attrs, defStyleAttr),
    RefreshHeader {

    private val resetImageView: ImageView
    private val mHeaderText: TextView
    private val progressBar: ProgressBar

    init {
        val inflateView = View.inflate(getContext(), R.layout.xm_refresh_header, this)
        resetImageView = inflateView.findViewById(R.id.xmRefreshHeaderResetView)
        mHeaderText = inflateView.findViewById(R.id.xmRefreshHeaderHintView)
        progressBar = inflateView.findViewById(R.id.xmRefreshHeaderLoadingProgressBar)
    }


    override fun getSpinnerStyle(): SpinnerStyle {
        return SpinnerStyle.Translate//指定为平移
    }

    override fun onFinish(refreshLayout: RefreshLayout, success: Boolean): Int {
        if (success){
            mHeaderText.text = context.getString(R.string.xm_refresh_complete)
        } else {
            mHeaderText.text = context.getString(R.string.xm_refresh_failed)
        }
        Timber.d("onFinish")
        return 500
    }

    override fun onStartAnimator(refreshLayout: RefreshLayout, height: Int, maxDragHeight: Int) {
        //开始动画
        progressBar.visibility = View.VISIBLE
        resetImageView.visibility = View.GONE
    }

    override fun onStateChanged(
        refreshLayout: RefreshLayout,
        oldState: RefreshState,
        newState: RefreshState
    ) {
        when(newState){
            RefreshState.None,RefreshState.PullDownToRefresh->{
                mHeaderText.text = context.getString(R.string.xm_pull_to_refresh)
                progressBar.visibility = View.GONE
                resetImageView.visibility = View.VISIBLE
            }
            RefreshState.Refreshing->{
                mHeaderText.text = context.getString(R.string.xm_refreshing)
            }
            RefreshState.ReleaseToRefresh->{
                mHeaderText.text = context.getString(R.string.xm_release)
            }
            else -> {}
        }

        Timber.d(newState.toString())
    }

}