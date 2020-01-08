package com.moon.teachassistant.di

import com.moon.educational.di.EducationalModule
import com.moon.libaccount.di.AccountModule
import com.moon.libbase.base.BaseApplication
import com.moon.libbase.dl.module.BaseAppModule
import com.moon.libbase.dl.module.HttpClientModule
import com.moon.libbase.dl.module.VMModule
import com.moon.libcommon.di.CommonHttpModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * @author ry
 * @date 2019-05-14
 */
@Singleton
@Component(
    modules = [AndroidInjectionModule::class,
        AndroidSupportInjectionModule::class,
        BaseAppModule::class,
        VMModule::class,
        HttpClientModule::class,
        CommonHttpModule::class,
        AppModule::class,
        AccountModule::class,
        EducationalModule::class]
)
interface AppComponent {

    fun application(): BaseApplication

    fun retrofit(): Retrofit

    fun inject(application: BaseApplication)
}