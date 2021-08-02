package com.zamorano.mybooks.model.repositories

import android.content.Context
import com.zamorano.mybooks.model.api.Book
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor() : BookRepository {

    private lateinit var mContext: Context
    override fun loadBooks(context: Context, callback: ApiCallback<Boolean>) {
        TODO("Not yet implemented")
    }

    override fun getBooks(): List<Book> {
        TODO("Not yet implemented")
    }
}