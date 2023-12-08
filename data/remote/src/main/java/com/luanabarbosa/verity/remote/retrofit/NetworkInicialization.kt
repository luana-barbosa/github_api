package com.luanabarbosa.verity.remote.retrofit

import com.luanabarbosa.verity.remote.config.ConfigApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class NetworkInitialization {

    private var okHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor())
        .readTimeout(60, TimeUnit.SECONDS)
        .connectTimeout(60, TimeUnit.SECONDS)
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }).build()

    private var retrofitBuilder = Retrofit.Builder()
        .baseUrl(ConfigApi.BASE_API_URL)
        .client(okHttpClient )
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T> createService(apiService: Class<T>) = retrofitBuilder.create(apiService)

    private fun interceptor() = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.HEADERS)

//    fun providesSupportInterceptor(): Interceptor {}
}