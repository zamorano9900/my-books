package com.zamorano.mybooks.model.repositories

import android.content.Context
import com.zamorano.mybooks.model.api.Book
import com.zamorano.mybooks.model.api.Category
import com.zamorano.mybooks.model.api.CategoryType
import com.zamorano.mybooks.model.api.House
import com.zamorano.mybooks.model.network.ApiErrorKey

interface BookRepository {
    fun getCategories(callback: CategoryRepositoryCallback)
    fun getCategoryDetail(categoryType: CategoryType, callback: CategoryRepositoryCallback)
}

interface ApiCallback<T> {
     fun onSucceed(result: T) {}
     fun onFailed(code: Int, errorKey: ApiErrorKey) {}
}