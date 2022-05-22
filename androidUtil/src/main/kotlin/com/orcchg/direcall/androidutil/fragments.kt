package com.orcchg.direcall.androidutil

import androidx.fragment.app.Fragment

@Suppress("Unchecked_Cast")
fun <T : Any> Fragment.argument(key: String) =
    lazy { requireArguments().get(key) as T }
