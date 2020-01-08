package com.moon.popup.dialog

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RectShape
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.lxj.xpopup.animator.PopupAnimator
import com.lxj.xpopup.impl.PartShadowPopupView
import com.moon.libbase.utils.ui.dp
import com.moon.popup.R
import kotlinx.android.synthetic.main.popup_list.view.*

/**
 * @author ry
 * @date 2019-07-11
 */
class ListPopupView(context: Context) : PartShadowPopupView(context) {
    var adapter: Adapter<out ViewHolder>? = null
    var layoutManager: RecyclerView.LayoutManager? = null

    constructor(context: Context, adapter: Adapter<out ViewHolder>, layout: RecyclerView.LayoutManager? = null) : this(
        context
    ) {
        this.adapter = adapter
        this.layoutManager = layout
    }

    override fun getImplLayoutId(): Int {
        return R.layout.popup_list
    }

    override fun onCreate() {
        if (adapter != null) {
            class_rv.adapter = adapter
        }
        if (layoutManager != null) {
            class_rv.layoutManager = layoutManager
        } else {
            class_rv.layoutManager = LinearLayoutManager(context)
        }
    }

    fun addItemDecoration(deco: RecyclerView.ItemDecoration){
        if (class_rv==null) return
        class_rv.addItemDecoration(deco)
    }

    fun addDividerItemDecoration(){
        val itemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        val drawable = ShapeDrawable(RectShape()).apply {
            intrinsicHeight = 1.dp
            paint.color = Color.parseColor("#ECEEEF")
        }
        itemDecoration.setDrawable(drawable)
        addItemDecoration(itemDecoration)
    }

    override fun getPopupAnimator(): PopupAnimator {
        return SlideDownAnimator(popupImplView)
    }



}

