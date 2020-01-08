package com.moon.libaccount.di

import com.moon.libaccount.ui.fragment.VerifyCodeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * @author ry
 * @date 2019-12-18
 */
@Module
abstract class LoginFragmentModule{

    @ContributesAndroidInjector
    abstract fun contributeVerifyCodeFragment(): VerifyCodeFragment

}