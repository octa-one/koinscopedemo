package com.orcchg.direcall.network.di

import com.orcchg.direcall.network.api.di.NetworkLibraryApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import inv.di.LibraryImpl
import inv.di.dsl.ImplDSL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

object NetworkLibraryImpl : LibraryImpl(NetworkLibraryApi) {

    override fun ImplDSL.definitions() {

        factory {
            HttpLoggingInterceptor()
                .apply { level = HttpLoggingInterceptor.Level.BODY }
        }
        scoped {
            Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
        }
        scoped {
            OkHttpClient.Builder()
                .addNetworkInterceptor(get<HttpLoggingInterceptor>())
                .build()
        }

        scoped {
            Retrofit.Builder()
                .client(get())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create(get()))
                .baseUrl("https://api.github.com/")
                .build()
        }
    }
}