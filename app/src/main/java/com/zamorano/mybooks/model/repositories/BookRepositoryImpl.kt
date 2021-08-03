package com.zamorano.mybooks.model.repositories

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import com.zamorano.mybooks.AppConfig
import com.zamorano.mybooks.helpers.NetworkHelper
import com.zamorano.mybooks.model.api.*
import com.zamorano.mybooks.model.api.ApiProvider.getApiClient
import com.zamorano.mybooks.model.api.Char
import com.zamorano.mybooks.model.db.BookDbRepository
import com.zamorano.mybooks.model.db.CategoryDbRepository
import com.zamorano.mybooks.model.db.CharDbRepository
import com.zamorano.mybooks.model.db.schema.AppDatabase
import com.zamorano.mybooks.model.network.ApiErrorKey
import com.zamorano.mybooks.model.network.CategoryApiService
import com.zamorano.myhouses.model.db.HouseDbRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type
import javax.inject.Inject


interface CategoryRepositoryCallback {
    fun onGetCategoriesDidFail(errorKey: ApiErrorKey) {}
    fun onGetCategoriesSuccess(categories: List<Category>) {}
    fun onGetCategoryDetailSuccess(categories: ApiResultEntity) {}
}

class BookRepositoryImpl @Inject constructor(val context: Context) : BookRepository {

    private lateinit var mDatabase: AppDatabase

    override fun getCategories(callback: CategoryRepositoryCallback) {
        mDatabase = AppDatabase.getInstance(context)!!
        val categories = CategoryDbRepository.getAll(mDatabase)
        if (categories.isNotEmpty()) {
            callback.onGetCategoriesSuccess(categories)
        } else {
            if (NetworkHelper.isInternetAvailable(context)) {
                getCategoriesApi(callback)
            } else {
                callback.onGetCategoriesDidFail(ApiErrorKey.NOINTERNET)
            }
        }
    }

    override fun getCategoryDetail(
        categoryType: CategoryType,
        callback: CategoryRepositoryCallback
    ) {
        mDatabase = AppDatabase.getInstance(context)!!
        val resultApi = ApiResultEntity()

        when (categoryType) {
            CategoryType.BOOK -> resultApi.bookResponse = BookDbRepository.getAll(mDatabase)
            CategoryType.HOUSE -> resultApi.houseResponse = HouseDbRepository.getAll(mDatabase)
            CategoryType.CHAR -> resultApi.charResponse = CharDbRepository.getAll(mDatabase)
            else -> BookDbRepository.getAll(mDatabase)
        }

        if (!resultApi.bookResponse.isNullOrEmpty() || !resultApi.houseResponse.isNullOrEmpty() || !resultApi.charResponse.isNullOrEmpty()) {
            callback.onGetCategoryDetailSuccess(resultApi)
        } else {
            if (NetworkHelper.isInternetAvailable(context)) {
                getCategoryDetailApi(categoryType, callback)
            } else {
                callback.onGetCategoriesDidFail(ApiErrorKey.NOINTERNET)
            }
        }
    }

    private fun getCategoriesApi(callback: CategoryRepositoryCallback): CategoryRepositoryCallback {
        val categoryApiService =
            getApiClient(AppConfig.urlBase).create(CategoryApiService::class.java)
        val call = categoryApiService.getCategories()

        call.enqueue(object : Callback<List<Category>> {
            override fun onResponse(
                call: Call<List<Category>>,
                response: Response<List<Category>>
            ) {
                if (response.isSuccessful && response.body() != null && response.code() == 200) {
                    val categories = response.body() as List<Category>
                    CategoryDbRepository.saveAll(categories, mDatabase)
                    callback.onGetCategoriesSuccess(categories)
                }
            }

            override fun onFailure(call: Call<List<Category>>, t: Throwable) {
                callback.onGetCategoriesDidFail(ApiErrorKey.UNKNOWNERROR)
            }
        })
        return callback
    }

    private fun getCategoryDetailApi(
        categoryType: CategoryType,
        callback: CategoryRepositoryCallback
    ): CategoryRepositoryCallback {
        val categoryApiService =
            getApiClient(AppConfig.urlBase).create(CategoryApiService::class.java)
        val call = categoryApiService.getCategoryDetail(categoryType.ordinal.toString())

        call.enqueue(object : Callback<List<JsonObject>> {
            override fun onResponse(
                call: Call<List<JsonObject>>,
                response: Response<List<JsonObject>>
            ) {
                if (response.isSuccessful && response.body() != null && response.code() == 200) {
                    val resultApi = ApiResultEntity()
                    when (categoryType) {
                        CategoryType.BOOK -> {
                            val type = object : TypeToken<List<Book?>?>() {}.type
                            resultApi.bookResponse =
                                deserializeList(response.body().toString(), type)
                            BookDbRepository.saveAll(resultApi.bookResponse!!, mDatabase)
                        }
                        CategoryType.HOUSE -> {
                            val type = object : TypeToken<List<House?>?>() {}.type
                            resultApi.houseResponse =
                                deserializeList(response.body().toString(), type)
                            HouseDbRepository.saveAll(resultApi.houseResponse!!, mDatabase)
                        }
                        CategoryType.CHAR -> {
                            val type = object : TypeToken<List<Char?>?>() {}.type
                            resultApi.charResponse =
                                deserializeList(response.body().toString(), type)
                            CharDbRepository.saveAll(resultApi.charResponse!!, mDatabase)
                        }
                        else -> {
                            val type = object : TypeToken<List<Book?>?>() {}.type
                            resultApi.bookResponse =
                                deserializeList(response.body().toString(), type)
                        }
                    }
                    callback.onGetCategoryDetailSuccess(resultApi)
                }
            }

            override fun onFailure(call: Call<List<JsonObject>>, t: Throwable) {
                callback.onGetCategoriesDidFail(ApiErrorKey.UNKNOWNERROR)
            }
        })
        return callback
    }

    private fun <T> deserializeList(json: String?, type: Type?): List<T>? {
        val gson = Gson()
        return gson.fromJson(json, type)
    }
}