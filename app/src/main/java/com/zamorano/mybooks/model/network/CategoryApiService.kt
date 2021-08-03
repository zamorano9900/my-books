package com.zamorano.mybooks.model.network

import com.google.gson.JsonObject
import com.zamorano.mybooks.model.api.Category
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CategoryApiService {
    //region GET
    @GET("/categories")
    fun getCategories(): Call<List<Category>>

    @GET("/list/{typeCategory}")
    fun getCategoryDetail(@Path("typeCategory") typeCategory: String): Call<List<JsonObject>>
    //endregion
}