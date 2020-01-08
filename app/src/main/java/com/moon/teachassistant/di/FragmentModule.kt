package com.moon.teachassistant.di

import com.moon.educational.ui.HomeFragment
import com.moon.libaccount.ui.fragment.MineFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * @author ry
 * @date 2019-12-19
 */
@Module
abstract class MainFragmentModule{
    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun contributeMineFragment(): MineFragment
}