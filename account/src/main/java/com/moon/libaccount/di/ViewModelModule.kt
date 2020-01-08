/*
 * Copyright (C) 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.moon.libaccount.di


import androidx.lifecycle.ViewModel
import com.moon.libaccount.viewmodel.*
import com.moon.libbase.dl.scope.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * ViewModel的注入
 */
@Suppress("unused")
@Module
abstract class ViewModelModule {

    // @Binds 类似于 @Provides，在使用接口声明时使用，区别是 @Binds 用于修饰抽象类中的抽象方法的
    // 这个方法必须返回接口或抽象类，比如 ViewModel，不能直接返回 LoginViewModel
    // 方法的参数就是这个方法返回的是注入的对象，类似@Provides修饰的方法返回的对象
    // 这里的 LoginViewModel 会通过上述声明的构造器注入自动构建
    @Binds
    @IntoMap
    //@MapKey的封装注解
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(loginViewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    //@MapKey的封装注解
    @ViewModelKey(CodeViewModel::class)
    abstract fun bindCodeViewModel(codeViewModel: CodeViewModel): ViewModel


}
