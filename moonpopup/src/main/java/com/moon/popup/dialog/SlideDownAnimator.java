package com.moon.popup.dialog;

import android.view.View;

import androidx.interpolator.view.animation.FastOutSlowInInterpolator;

import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.animator.PopupAnimator;

/**
 * 往下平移动画，不带渐变
 */
public class SlideDownAnimator extends PopupAnimator {
    //动画起始坐标
    private float startTranslationY;

    public SlideDownAnimator(View target) {
        super(target, null);
    }

    @Override
    public void initAnimator() {
        // 设置起始坐标为view的高度
        targetView.setTranslationY(-targetView.getBottom());
        startTranslationY = targetView.getTranslationY();
    }

    @Override
    public void animateShow() {
        targetView.animate().translationY(0)
                .setInterpolator(new FastOutSlowInInterpolator())
                .setDuration(XPopup.getAnimationDuration()).start();
    }

    @Override
    public void animateDismiss() {
        targetView.animate().translationY(startTranslationY)
                .setInterpolator(new FastOutSlowInInterpolator())
                .setDuration(XPopup.getAnimationDuration()).start();
    }
}
