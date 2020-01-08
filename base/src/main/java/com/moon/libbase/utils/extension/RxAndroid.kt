package com.moon.libbase.utils.extension

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

/**
 * @author ry
 * @date 2019-05-16
 */
fun <T> Observable<T>.ioScheduler(): Observable<T> {
    return subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}

fun Completable.ioScheduler(): Completable {
    return subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}

fun Completable.defSubscribe(): Disposable {
    return subscribe({},{
        Timber.d(it)
    })
}