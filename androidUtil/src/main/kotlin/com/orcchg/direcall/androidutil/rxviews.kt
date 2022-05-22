package com.orcchg.direcall.androidutil

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

fun <T> Observable<T>.clickDebounce(timeout: Long = 200L): Observable<T> =
    throttleFirst(timeout, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())
