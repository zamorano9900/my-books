package com.zamorano.mybooks.model.network

import com.zamorano.mybooks.model.api.Category
import retrofit2.Call
import retrofit2.http.GET

interface CategoryApiService {
    //region GET
    @GET("/categories")
    fun getCategories(): Call<List<Category>>
    //endregion
}