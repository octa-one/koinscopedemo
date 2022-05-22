@file:Suppress("UNCHECKED_CAST", "NOTHING_TO_INLINE", "NON_PUBLIC_PRIMARY_CONSTRUCTOR_OF_INLINE_CLASS")

package com.orcchg.direcall.util

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

inline class DataState<out T> @PublishedApi internal constructor(
    @PublishedApi internal val value: Any?
) {

    val isSuccess: Boolean get() = value !is Failure && value !is Loading
    val isFailure: Boolean get() = value is Failure
    val isLoading: Boolean get() = value is Loading

    inline fun getOrNull(): T? =
        when {
            isFailure -> null
            isLoading -> null
            else -> value as T
        }

    inline fun getOrThrow(): T =
        when (value) {
            is Loading -> throw IllegalStateException("Value is Loading")
            is Failure -> throw value.exception
            else -> value as T
        }

    override fun toString(): String =
        when (value) {
            is Loading -> value.toString()
            is Failure -> value.toString()
            else -> "Success($value)"
        }

    @PublishedApi internal class Failure(
        val exception: Throwable
    ) {
        override fun equals(other: Any?): Boolean = other is Failure && exception == other.exception
        override fun hashCode(): Int = exception.hashCode()
        override fun toString(): String = "Failure($exception)"
    }

    @PublishedApi internal object Loading {
        override fun toString(): String = "Loading"
    }

    companion object {
        inline fun <T> success(value: T): DataState<T> = DataState(value)
        inline fun <T> failure(exception: Throwable): DataState<T> = DataState(Failure(exception))
        inline fun <T> loading(): DataState<T> = DataState(Loading)
    }
}

@OptIn(ExperimentalContracts::class)
inline fun <T> DataState<T>.onFailure(action: (exception: Throwable) -> Unit): DataState<T> {
    contract { callsInPlace(action, InvocationKind.AT_MOST_ONCE) }

    if (value is DataState.Failure) action(value.exception)
    return this
}

@OptIn(ExperimentalContracts::class)
inline fun <T> DataState<T>.onLoading(action: () -> Unit): DataState<T> {
    contract { callsInPlace(action, InvocationKind.AT_MOST_ONCE) }

    if (value is DataState.Loading) action()
    return this
}

@OptIn(ExperimentalContracts::class)
inline fun <T> DataState<T>.onSuccess(action: (value: T) -> Unit): DataState<T> {
    contract { callsInPlace(action, InvocationKind.AT_MOST_ONCE) }

    if (isSuccess) action(value as T)
    return this
}
