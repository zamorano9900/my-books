package com.zamorano.mybooks.model.api

import com.google.gson.JsonParser
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.zamorano.mybooks.AppConfig
import com.zamorano.mybooks.AppConfig.Companion.urlBase
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import com.google.gson.JsonObject




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

    fun <T> executeApiQuery(block: () -> Deferred<T>): ApiResultEntity<T> {
        val result: ApiResultEntity<T> = ApiResultEntity()
        runBlocking {
            try {
                result.resultData = block().await()
            } catch (e: HttpException) {
                when {
                    else -> {
                        val errorJsonString = e.response()?.errorBody()?.string()
                        if (!errorJsonString.isNullOrEmpty()) {
                            try {
                                val errorMessage = JsonParser().parse(errorJsonString).asJsonObject["code"].asString
                                result.errorData = Error(errorMessage)
                            } catch (e: java.lang.Exception) {

                            }
                        } else {
                            result.errorData = Error(e.code().toString())
                        }
                    }
                }
            } catch (e: Exception) {
                result.errorData = Error(e.message)
            }
        }
        return result
    }

}