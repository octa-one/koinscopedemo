package inv.di

import org.koin.core.scope.Scope

@Suppress("unused")
inline fun <reified R> Scope.resolve(
    constructor: () -> R,
): R = constructor()

inline fun <reified R, reified T1> Scope.resolve(
    constructor: (T1) -> R,
): R = constructor(get())

inline fun <reified R, reified T1, reified T2> Scope.resolve(
    constructor: (T1, T2) -> R,
): R = constructor(get(), get())

inline fun <reified R, reified T1, reified T2, reified T3> Scope.resolve(
    constructor: (T1, T2, T3) -> R,
): R = constructor(get(), get(), get())

inline fun <reified R, reified T1, reified T2, reified T3, reified T4> Scope.resolve(
    constructor: (T1, T2, T3, T4) -> R,
): R = constructor(get(), get(), get(), get())

inline fun <reified R, reified T1, reified T2, reified T3, reified T4, reified T5> Scope.resolve(
    constructor: (T1, T2, T3, T4, T5) -> R,
): R = constructor(get(), get(), get(), get(), get())

inline fun <reified R, reified T1, reified T2, reified T3, reified T4, reified T5, reified T6> Scope.resolve(
    constructor: (T1, T2, T3, T4, T5, T6) -> R,
): R = constructor(get(), get(), get(), get(), get(), get())

inline fun <reified R, reified T1, reified T2, reified T3, reified T4, reified T5, reified T6, reified T7> Scope.resolve(
    constructor: (T1, T2, T3, T4, T5, T6, T7) -> R,
): R = constructor(get(), get(), get(), get(), get(), get(), get())

inline fun <reified R, reified T1, reified T2, reified T3, reified T4, reified T5, reified T6, reified T7, reified T8> Scope.resolve(
    constructor: (T1, T2, T3, T4, T5, T6, T7, T8) -> R,
): R = constructor(get(), get(), get(), get(), get(), get(), get(), get())

inline fun <reified R, reified T1, reified T2, reified T3, reified T4, reified T5, reified T6, reified T7, reified T8, reified T9> Scope.resolve(
    constructor: (T1, T2, T3, T4, T5, T6, T7, T8, T9) -> R,
): R = constructor(get(), get(), get(), get(), get(), get(), get(), get(), get())

inline fun <reified R, reified T1, reified T2, reified T3, reified T4, reified T5, reified T6, reified T7, reified T8, reified T9, reified T10> Scope.resolve(
    constructor: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10) -> R,
): R = constructor(get(), get(), get(), get(), get(), get(), get(), get(), get(), get())

inline fun <reified R, reified T1, reified T2, reified T3, reified T4, reified T5, reified T6, reified T7, reified T8, reified T9, reified T10, reified T11> Scope.resolve(
    constructor: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11) -> R,
): R = constructor(get(), get(), get(), get(), get(), get(), get(), get(), get(), get(), get())

inline fun <reified R, reified T1, reified T2, reified T3, reified T4, reified T5, reified T6, reified T7, reified T8, reified T9, reified T10, reified T11, reified T12> Scope.resolve(
    constructor: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12) -> R,
): R = constructor(get(), get(), get(), get(), get(), get(), get(), get(), get(), get(), get(), get())

inline fun <reified R, reified T1, reified T2, reified T3, reified T4, reified T5, reified T6, reified T7, reified T8, reified T9, reified T10, reified T11, reified T12, reified T13> Scope.resolve(
    constructor: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13) -> R,
): R = constructor(get(), get(), get(), get(), get(), get(), get(), get(), get(), get(), get(), get(), get())

inline fun <reified R, reified T1, reified T2, reified T3, reified T4, reified T5, reified T6, reified T7, reified T8, reified T9, reified T10, reified T11, reified T12, reified T13, reified T14> Scope.resolve(
    constructor: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14) -> R,
): R = constructor(get(), get(), get(), get(), get(), get(), get(), get(), get(), get(), get(), get(), get(), get())

inline fun <reified R, reified T1, reified T2, reified T3, reified T4, reified T5, reified T6, reified T7, reified T8, reified T9, reified T10, reified T11, reified T12, reified T13, reified T14, reified T15> Scope.resolve(
    constructor: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15) -> R,
): R = constructor(get(), get(), get(), get(), get(), get(), get(), get(), get(), get(), get(), get(), get(), get(), get())

inline fun <reified R, reified T1, reified T2, reified T3, reified T4, reified T5, reified T6, reified T7, reified T8, reified T9, reified T10, reified T11, reified T12, reified T13, reified T14, reified T15, reified T16> Scope.resolve(
    constructor: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16) -> R,
): R = constructor(get(), get(), get(), get(), get(), get(), get(), get(), get(), get(), get(), get(), get(), get(), get(), get())

inline fun <reified R, reified T1, reified T2, reified T3, reified T4, reified T5, reified T6, reified T7, reified T8, reified T9, reified T10, reified T11, reified T12, reified T13, reified T14, reified T15, reified T16, reified T17> Scope.resolve(
    constructor: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17) -> R,
): R = constructor(get(), get(), get(), get(), get(), get(), get(), get(), get(), get(), get(), get(), get(), get(), get(), get(), get())

inline fun <reified R, reified T1, reified T2, reified T3, reified T4, reified T5, reified T6, reified T7, reified T8, reified T9, reified T10, reified T11, reified T12, reified T13, reified T14, reified T15, reified T16, reified T17, reified T18> Scope.resolve(
    constructor: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18) -> R,
): R = constructor(get(), get(), get(), get(), get(), get(), get(), get(), get(), get(), get(), get(), get(), get(), get(), get(), get(), get())

inline fun <reified R, reified T1, reified T2, reified T3, reified T4, reified T5, reified T6, reified T7, reified T8, reified T9, reified T10, reified T11, reified T12, reified T13, reified T14, reified T15, reified T16, reified T17, reified T18, reified T19> Scope.resolve(
    constructor: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19) -> R,
): R = constructor(get(), get(), get(), get(), get(), get(), get(), get(), get(), get(), get(), get(), get(), get(), get(), get(), get(), get(), get())

inline fun <reified R, reified T1, reified T2, reified T3, reified T4, reified T5, reified T6, reified T7, reified T8, reified T9, reified T10, reified T11, reified T12, reified T13, reified T14, reified T15, reified T16, reified T17, reified T18, reified T19, reified T20> Scope.resolve(
    constructor: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20) -> R,
): R = constructor(get(), get(), get(), get(), get(), get(), get(), get(), get(), get(), get(), get(), get(), get(), get(), get(), get(), get(), get(), get())