package com.moon.libaccount.di

import com.moon.libaccount.ui.*
import com.moon.libbase.dl.scope.ActivityScoped
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * @author ry
 * @date 2019-05-14
 */

@Module(includes = [ViewModelModule::class])
abstract class AccountModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = [LoginFragmentModule::class])
    abstract fun contributeLoginActivityInjector(): LoginActivity

    @ActivityScoped
    @ContributesAndroidInjector
    abstract fun contributeUserProtocolActivityInjector(): UserProtocolActivity

}