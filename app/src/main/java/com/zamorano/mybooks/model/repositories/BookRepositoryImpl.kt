package com.zamorano.mybooks.model.repositories

import android.content.Context
import android.media.session.MediaSession
import com.zamorano.mybooks.AppConfig
import com.zamorano.mybooks.helpers.NetworkHelper
import com.zamorano.mybooks.model.api.ApiProvider.executeApiQuery
import com.zamorano.mybooks.model.api.ApiProvider.getApiClient
import com.zamorano.mybooks.model.api.ApiResultEntity
import com.zamorano.mybooks.model.api.Book
import com.zamorano.mybooks.model.api.Category
import com.zamorano.mybooks.model.api.House
import com.zamorano.mybooks.model.db.CategoryDbRepository
import com.zamorano.mybooks.model.db.schema.AppDatabase
import com.zamorano.mybooks.model.network.ApiErrorKey
import com.zamorano.mybooks.model.network.CategoryApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

interface CategoryRepositoryCallback {
    fun onGetCategoriesDidFail(errorKey: ApiErrorKey) {}
    fun onGetCategoriesSuccess(categories: List<Category>) {}
}

class BookRepositoryImpl @Inject constructor(val context: Context) : BookRepository {

    private lateinit var mDatabase: AppDatabase
    override fun loadBooks(callback: ApiCallback<Boolean>) {
        TODO("Not yet implemented")
    }

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

    override fun getBooks(): List<Book> {
        TODO("Not yet implemented")
    }

    override fun getChars(): List<Char> {
        TODO("Not yet implemented")
    }

    override fun getHouses(): List<House> {
        TODO("Not yet implemented")
    }

    private fun getCategoriesApi(callback: CategoryRepositoryCallback): CategoryRepositoryCallback {
        val userApiService =
            getApiClient(AppConfig.urlBase).create(CategoryApiService::class.java)
        val call = userApiService.getCategories()

        call.enqueue(object : Callback<List<Category>> {
            override fun onResponse(
                call: Call<List<Category>>,
                response: Response<List<Category>>
            ) {
                if (response.isSuccessful && response.body() != null && response.code() == 200) {
                    val categories = response.body() as List<Category>
                    saveCategories(categories)
                    callback.onGetCategoriesSuccess(categories)
                }
            }

            override fun onFailure(call: Call<List<Category>>, t: Throwable) {
                callback.onGetCategoriesDidFail(ApiErrorKey.UNKNOWNERROR)
            }
        })
        return callback
    }

    private fun saveCategories(categories: List<Category>) {
        CategoryDbRepository.saveAll(categories, mDatabase)
    }

    private fun getCategoriesDb(callback: CategoryRepositoryCallback) {
        callback.onGetCategoriesSuccess(CategoryDbRepository.getAll(mDatabase))
    }
}