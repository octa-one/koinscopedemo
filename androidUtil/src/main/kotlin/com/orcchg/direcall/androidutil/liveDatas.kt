package com.orcchg.direcall.androidutil

import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.orcchg.direcall.util.DataState

/**
 * Inspired with Clean Architecture...Reloaded.
 *
 * @see https://fernandocejas.com/2018/05/07/architecting-android-reloaded/
 * @see https://github.com/android10/Android-CleanArchitecture-Kotlin/blob/master/app/src/main/kotlin/com/fernandocejas/sample/core/extension/Fragment.kt#L33
 */
fun LiveData<*>.isPresent() = value != null

fun <T> LiveData<T>.requireValue(): T = value ?: throw IllegalArgumentException("Required value was null.")

fun <T : Any, L : LiveData<T>> LifecycleOwner.observe(liveData: L, body: (T) -> Unit = {}) =
    liveData.observe(this, Observer(body))

fun <T : Any, L : LiveData<T?>> LifecycleOwner.observeNullable(liveData: L, body: (T?) -> Unit = {}) =
    liveData.observe(this, Observer(body))

fun <T : Any, L : LiveData<T>> Fragment.observe(liveData: L, body: (T) -> Unit = {}) =
    liveData.observe(viewLifecycleOwner, Observer(body))

fun <T : Any, L : LiveData<T?>> Fragment.observeNullable(liveData: L, body: (T?) -> Unit = {}) =
    liveData.observe(viewLifecycleOwner, Observer(body))

fun <T : Any, L : LiveData<DataState<T>>> Fragment.observeDataState(liveData: L, body: DataState<T>.() -> Unit = {}) =
    liveData.observe(viewLifecycleOwner, Observer(body))
