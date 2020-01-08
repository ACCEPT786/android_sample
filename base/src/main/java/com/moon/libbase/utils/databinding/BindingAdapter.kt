package com.moon.libbase.utils.databinding

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.moon.libbase.utils.glide.BlurTransformation
import com.moon.libbase.utils.glide.BlurTransformation.Companion.DEFAULT_DOWN_SAMPLING
import com.moon.libbase.utils.glide.BlurTransformation.Companion.MAX_RADIUS
import com.moon.libbase.utils.ui.dp
import timber.log.Timber

/**
 * @author ry
 * @date 2019-05-21
 */

/**
 * 加载图片
 */
@BindingAdapter("loadUrl")
fun loadUrl(view: ImageView, url: String?) {
    if (url != null) {
        Glide.with(view).load(url).into(view)
    }
}

@BindingAdapter(value = ["loadurlhasdefault", "defaultid"])
fun loadUrlhasdefault(view: ImageView, url: String?, drawable: Drawable?) {
    if (url != null) {
        if (drawable != null) {
            Glide.with(view).load(url).error(drawable).into(view)
        } else {
            Glide.with(view).load(url).into(view)
        }
    }else{
        if (drawable!=null) {
            Glide.with(view).load(drawable).into(view)
        }
    }
}

@BindingAdapter(value = ["loadCircleCrop", "defaultdrawable"])
fun loadCircleBitmap(view: ImageView, url: String?, drawable: Drawable? = null) {
    val options: RequestOptions = RequestOptions.circleCropTransform()
    options.override(Target.SIZE_ORIGINAL)
    if (!url.isNullOrBlank()) {
        if (drawable != null) {
            Glide.with(view).load(url).apply(options).error(drawable).into(view)
        } else {
            Glide.with(view).load(url).apply(options).into(view)
        }
    }else{
        if (drawable != null) {
            Glide.with(view).load(drawable).apply(options).into(view)
        }
    }
}

/**
 * 加载矩形圆角图片
 * @param corners dp值
 */
@BindingAdapter(value = ["loadRoundUrl", "corners", "roundError"], requireAll = false)
fun loadRoundUrl(view: ImageView, url: String?, corners: Int?, roundError: Drawable?) {
    Timber.d("loadRoundUrl:corners=$corners,roundError=$roundError")
    val builder = Glide.with(view).load(url ?: "")
    corners?.let {
        if (it > 0) {
            val roundedCorners = RoundedCorners(it.dp)
            val options: RequestOptions = RequestOptions.bitmapTransform(roundedCorners)
            options.override(Target.SIZE_ORIGINAL)
            builder.apply(options)
        }
    }
    roundError?.let {
        builder.error(it)
    }
    builder.into(view)
}

/**
 * 加载矩形圆角图片
 * @param corners dp值
 */
@BindingAdapter(value = ["loadRoundUrl", "corners", "roundError"], requireAll = false)
fun loadRoundUrlerrInt(view: ImageView, url: String?, corners: Int?, roundError: Int?) {
    Timber.d("loadRoundUrl:corners=$corners,roundError=$roundError")
    val builder = Glide.with(view).load(url ?: "")
    corners?.let {
        if (it > 0) {
            val roundedCorners = RoundedCorners(it.dp)
            val options: RequestOptions = RequestOptions.bitmapTransform(roundedCorners)
            options.override(Target.SIZE_ORIGINAL)
            builder.apply(options)
        }
    }
    roundError?.let {
        builder.error(it)
    }
    builder.into(view)
}


/**
 * 加载模糊图片
 * @param radius 模糊半径
 * @param sampling 取样点比例
 */
@BindingAdapter(value = ["loadBlurUrl", "radius", "sampling"], requireAll = false)
fun loadBlurUrl(view: ImageView, url: String?, radius: Int?, sampling: Int?) {
    if (url == null) return
    val builder = Glide.with(view).load(url)
            .transition(DrawableTransitionOptions.withCrossFade(500))
    val trans = BlurTransformation(
        radius ?: MAX_RADIUS,
        sampling ?: DEFAULT_DOWN_SAMPLING
    )
    val options: RequestOptions = RequestOptions.bitmapTransform(trans)
    options.override(Target.SIZE_ORIGINAL)
    builder.apply(options).into(view)
}