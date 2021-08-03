package com.zamorano.mybooks.model.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.zamorano.mybooks.AppConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiProvider {

    fun getApiClient(urlBase: String): Retrofit {
        val client = OkHttpClient().newBuilder()
            .connectTimeout(AppConfig.timeOutApiConection, TimeUnit.SECONDS)
            .readTimeout(AppConfig.timeOutApiConection, TimeUnit.SECONDS)
            .writeTimeout(AppConfig.timeOutApiConection, TimeUnit.SECONDS)
        return Retrofit.Builder()
            .baseUrl(urlBase)
            .client(client.build())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}