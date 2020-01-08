package com.moon.popup.dialog

import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager
import com.lxj.xpopup.animator.PopupAnimator
import com.lxj.xpopup.impl.PartShadowPopupView
import com.moon.popup.R
import com.moon.popup.adapter.ListItem
import com.moon.popup.adapter.ListPopAdapter
import kotlinx.android.synthetic.main.popup_list.view.*


/**
 * @author ry
 * @date 2019-07-18
 * 局部阴影弹窗 3列显示
 */
class PartShadowGridPopupView(context: Context) : PartShadowPopupView(context) {

    var adapter = ListPopAdapter()

    override fun getImplLayoutId(): Int {
        return R.layout.popup_list
    }

    override fun onCreate() {
        //这里要先设manager，否则adapter的onAttachedToRecyclerView不起作用
        class_rv.layoutManager = GridLayoutManager(context, 4)
        class_rv.adapter = adapter
    }

    override fun getPopupAnimator(): PopupAnimator {
        return SlideDownAnimator(popupImplView)
    }

    companion object {
        fun createPupUpView(context: Context, list: List<ListItem>): PartShadowGridPopupView {
            val popup = PartShadowGridPopupView(context)
            popup.adapter.submitList(list)
            return popup
        }
    }
}