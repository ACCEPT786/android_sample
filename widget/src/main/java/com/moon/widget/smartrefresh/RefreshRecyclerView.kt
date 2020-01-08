package com.moon.widget.smartrefresh

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.recyclerview.widget.RecyclerView
import com.moon.libbase.widget.RefreshViewCallBack
import com.moon.widget.R
import com.moon.widget.stateview.XmStateView
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshListener

/**
 * @author ry
 * @date 2019-12-23
 * 封装smartRefreshLayout和StateRecyclerView
 */
class RefreshRecyclerView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) :
    FrameLayout(context, attrs), RefreshViewCallBack {

    val recyclerView: RecyclerView
    val refreshLayout: SmartRefreshLayout
    val stateView: XmStateView

    init {
        View.inflate(context, R.layout.xm_refresh_recycler, this)
        recyclerView = findViewById(R.id.xmRecyclerView)
        refreshLayout = findViewById(R.id.xmRefreshLayout)
        stateView = XmStateView.inject(this)
    }

    fun setAdapter(adapter: RecyclerView.Adapter<out RecyclerView.ViewHolder>) {
        recyclerView.adapter = adapter
    }

    override fun showContent() {
        stateView.showContent()
    }

    override fun showEmpty() {
        stateView.showEmpty()
    }

    override fun showLoading() {
        stateView.showLoading()
    }

    override fun showRetry() {
        stateView.showRetry()
    }

    fun setOnRefreshListener(listener: OnRefreshListener) {
        refreshLayout.setOnRefreshListener(listener)
    }

    override fun finishRefresh() {
        refreshLayout.finishRefresh()
    }

    override fun setEnableRefresh(enabled: Boolean) {
        refreshLayout.setEnableRefresh(enabled)
    }


}