package com.moon.libaccount.ui;

import android.webkit.WebView;

import androidx.appcompat.widget.Toolbar;
import androidx.databinding.ViewDataBinding;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.moon.libaccount.BuildConfig;
import com.moon.libaccount.R;
import com.moon.libbase.base.BaseActivity;
import com.moon.libcommon.utils.ARouteAddress;

/**
 * Created by Ben on 2015/5/11.
 * 用户协议
 */
@Route(path = ARouteAddress.ACCOUNT_USER_PROTOCOL)
public class UserProtocolActivity extends BaseActivity<ViewDataBinding> {

    private WebView webView;

    @Override
    public void initView() {
        super.initView();
        webView = (WebView) findViewById(R.id.user_terms);
        webView.loadUrl(BuildConfig.USER_DIRECTIONS_URL);
    }

    @Override
    public void onDestroy() {
        webView.destroy();
        super.onDestroy();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_user_protocol;
    }

    @Override
    public boolean hasBinding() {
        return false;
    }
}
