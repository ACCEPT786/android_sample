package com.moon.libbase.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.moon.libbase.R
import org.jetbrains.anko.find

/**
 * @author ry
 * @date 2019-12-19
 */
class OptionItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val titleView: TextView
    private val imageView: ImageView
    private val divider: View

    init {
        val inflate = View.inflate(getContext(), R.layout.option_item_view, this)
        titleView = inflate.find(R.id.titleView)
        imageView = inflate.find(R.id.imageView)
        divider = inflate.find(R.id.divider)
        val typeArray = context.obtainStyledAttributes(
            attrs, R.styleable.OptionItemView, defStyleAttr, 0
        )
        val icon = typeArray.getDrawable(R.styleable.OptionItemView_oiv_icon)
        val title = typeArray.getString(R.styleable.OptionItemView_oiv_title)
        typeArray.recycle()

        imageView.setImageDrawable(icon)
        titleView.text = title
    }
}