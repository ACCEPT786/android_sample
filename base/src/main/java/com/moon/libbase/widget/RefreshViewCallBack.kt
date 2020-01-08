package com.moon.libbase.widget

/**
 * @author ry
 * @date 2019-12-24
 */
interface RefreshViewCallBack {
    fun showContent()
    fun showLoading()
    fun showRetry()
    fun showEmpty()
    fun finishRefresh()
    fun setEnableRefresh(enabled:Boolean)
}