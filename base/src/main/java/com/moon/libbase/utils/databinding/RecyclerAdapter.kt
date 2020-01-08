package com.moon.libbase.utils.databinding

import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RectShape
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.moon.libbase.R

/**
 * @author ry
 * @date 2019-12-25
 */


@BindingAdapter(value = ["dividerOrientation", "dividerDrawable"], requireAll = false)
fun setRecyclerDivider(view: RecyclerView, orientation: Int?, drawable: Drawable?) {
    val itemDecoration =
        DividerItemDecoration(view.context, orientation ?: DividerItemDecoration.VERTICAL)
    if (drawable != null) {
        itemDecoration.setDrawable(drawable)
    } else {
        val shape = ShapeDrawable(RectShape())
        shape.paint.apply {
            color = ContextCompat.getColor(view.context, R.color.xm_divider)
            style = Paint.Style.FILL
        }
        shape.intrinsicHeight = view.context.resources.getDimensionPixelOffset(R.dimen.xm_divider_height)
        itemDecoration.setDrawable(shape)
    }
    view.addItemDecoration(itemDecoration)
}