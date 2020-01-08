package com.moon.teachassistant.di

import com.moon.libbase.dl.scope.ActivityScoped
import com.moon.teachassistant.MainActivity
import com.moon.teachassistant.ui.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * @author ry
 * @date 2019-12-17
 */
@Module(includes = [AppViewModelModule::class])
abstract class AppModule {
    @ActivityScoped
    @ContributesAndroidInjector
    abstract fun contributeSplashActivityInjector(): SplashActivity

    @ActivityScoped
    @ContributesAndroidInjector(modules = [MainFragmentModule::class])
    abstract fun contributeMainActivityInjector(): MainActivity
}