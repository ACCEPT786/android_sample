package com.moon.popup.dialog

import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager
import com.lxj.xpopup.core.CenterPopupView
import com.moon.popup.R
import com.moon.popup.adapter.ListItem
import com.moon.popup.adapter.ListPopAdapter
import kotlinx.android.synthetic.main.popup_center_list.view.*

/**
 * @author ry
 * @date 2019-09-03
 * 居中显示. 3列显示
 */
class CenterGridPopupView(context: Context) : CenterPopupView(context) {

    var adapter = ListPopAdapter()

    override fun getImplLayoutId(): Int {
        return R.layout.popup_center_list
    }

    override fun onCreate() {
        //这里要先设manager，否则adapter的onAttachedToRecyclerView不起作用
        class_rv.layoutManager = GridLayoutManager(context, 3)
        class_rv.adapter = adapter
    }

    companion object {
        fun createPupUpView(context: Context, list: List<ListItem>): CenterGridPopupView {
            val popup = CenterGridPopupView(context)
            popup.adapter.submitList(list)
            return popup
        }
    }
}