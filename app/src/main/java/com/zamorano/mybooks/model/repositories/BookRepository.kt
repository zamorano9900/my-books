package com.zamorano.mybooks.model.repositories

import android.content.Context
import com.zamorano.mybooks.model.api.Book
import com.zamorano.mybooks.model.network.ApiErrorKey

interface BookRepository {
    fun loadBooks(context: Context, callback: ApiCallback<(Boolean)>)
    fun getBooks(): List<Book>
}

interface ApiCallback<T> {
     fun onSucceed(result: T) {}
     fun onFailed(code: Int, errorKey: ApiErrorKey) {}
}