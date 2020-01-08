package com.moon.teachassistant.di

import androidx.lifecycle.ViewModel
import com.moon.libbase.dl.scope.ViewModelKey
import com.moon.teachassistant.vm.MainViewModel
import com.moon.teachassistant.vm.SplashViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * @author ry
 * @date 2019-12-17
 */
@Suppress("unused")
@Module
abstract class AppViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    abstract fun bindSplashViewModel(splashViewModel: SplashViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel
}