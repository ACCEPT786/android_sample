package com.moon.teachassistant;

import com.moon.libbase.base.BaseApplication;
import com.moon.libbase.dl.module.BaseAppModule;
import com.moon.libbase.dl.module.HttpClientModule;
import com.moon.teachassistant.di.AppComponent;
import com.moon.teachassistant.di.DaggerAppComponent;

/**
 * @author ry
 * @date 2019-12-13
 */
public class AppApplication  extends BaseApplication {
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder()
                .baseAppModule(new BaseAppModule(this))
                .httpClientModule(new HttpClientModule())
                .build();
        appComponent.inject(this);
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
