package com.moon.teachassistant.ui;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;

import androidx.viewpager.widget.ViewPager;

import com.moon.libbase.utils.ui.WindowUtils;
import com.moon.libcommon.base.BaseCommonActivity;
import com.moon.libcommon.sp.GSPSharedPreferences;
import com.moon.teachassistant.BuildConfig;
import com.moon.teachassistant.R;
import com.moon.teachassistant.databinding.ActivitySplashGuideBinding;
import com.moon.teachassistant.ui.adapter.SplashGuideAdapter;

import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.circlenavigator.CircleNavigator;

import java.util.ArrayList;
import java.util.List;


public class SplashGuideActivity extends BaseCommonActivity<ActivitySplashGuideBinding> implements View.OnClickListener {
    SplashGuideAdapter adapter;
    private int[] guideIds = {R.drawable.guide1, R.drawable.guide2, R.drawable.guide3};
    private List<ImageView> allViews;

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash_guide;
    }

    @Override
    public void initView() {
        super.initView();
        initImageView();
        adapter = new SplashGuideAdapter(allViews);
        dataBinding.viewPager.setAdapter(adapter);
        initMagicIndicator();
        WindowUtils.setScreenFull(getWindow());
    }


    @Override
    public void initData() {
        super.initData();
        long versionCode = BuildConfig.VERSION_CODE;
        if (GSPSharedPreferences.getInstance().isFirstStart() != versionCode) {
            GSPSharedPreferences.getInstance().setFirstStart(versionCode);
        } else {
            startSplash();
        }
    }


    public void initImageView() {
        allViews = new ArrayList<>();
        for (int i = 0; i < guideIds.length; i++) {
            ImageView imgview = new ImageView(this);
            ViewPager.LayoutParams mParams = new ViewPager.LayoutParams();
            imgview.setLayoutParams(mParams);
            imgview.setImageResource(guideIds[i]);
            imgview.setScaleType(ImageView.ScaleType.CENTER_CROP);

            if (i == guideIds.length - 1) {
                imgview.setOnClickListener(this);
            }
            allViews.add(imgview);
        }
    }

    private void initMagicIndicator() {
        CircleNavigator circleNavigator = new CircleNavigator(this);
        circleNavigator.setCircleCount(guideIds.length);
        circleNavigator.setCircleColor(Color.RED);
        circleNavigator.setCircleClickListener(index -> dataBinding.viewPager.setCurrentItem(index));
        dataBinding.magicIndicator.setNavigator(circleNavigator);
        ViewPagerHelper.bind(dataBinding.magicIndicator, dataBinding.viewPager);
    }

    @Override
    public void onClick(View view) {
        startSplash();
    }

    private void startSplash() {
        Intent intent = new Intent();
        intent.setClass(this, SplashActivity.class);
        startActivity(intent);
        finish();
    }

}
