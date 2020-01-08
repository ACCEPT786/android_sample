package com.moon.teachassistant.ui.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class SplashGuideAdapter extends PagerAdapter {
    private List<ImageView> guideIds;

    public SplashGuideAdapter(List<ImageView> guideIds) {
        this.guideIds = guideIds;
    }

    @Override
    public int getCount() {
        return guideIds.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(guideIds.get(position));
        return guideIds.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

}
