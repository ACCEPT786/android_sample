package com.moon.libcommon.http

import android.os.Build
import com.moon.libbase.BuildConfig
import com.moon.libcommon.sp.GSPSharedPreferences
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author ry
 * @date 2019-05-10
 */
class RetrofitFactory constructor(okHttpClient: OkHttpClient) {

    private val headerInterceptor: Interceptor = Interceptor { chain ->
        val original = chain.request()
        val originalHttpUrl = original.url()
        val uId: String = GSPSharedPreferences.getInstance().uid
        val token = GSPSharedPreferences.getInstance().token
        //公共参数
        val url = originalHttpUrl.newBuilder()
            .addQueryParameter("token", token)
            .addQueryParameter("uid", uId)
            .addQueryParameter("deviceType", "Android")
            .addQueryParameter("ver", BuildConfig.VERSION_CODE.toString())
            .addQueryParameter("hsman", Build.MANUFACTURER)
            .addQueryParameter("hstype", Build.MODEL)
        val request = original.newBuilder().url(url.build())
        chain.proceed(request.build())
    }
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(okHttpClient)
        .build()
}