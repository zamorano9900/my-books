package com.zamorano.mybooks.modules.categoryBookCollection

import android.content.Context
import com.zamorano.mybooks.model.api.Category
import com.zamorano.mybooks.model.network.ApiErrorKey
import com.zamorano.mybooks.model.repositories.BookRepository
import com.zamorano.mybooks.model.repositories.CategoryRepositoryCallback
import dagger.hilt.android.qualifiers.ActivityContext
import java.lang.ref.WeakReference
import javax.inject.Inject

class CategoryBookInteractor @Inject constructor(private val bookRepository: BookRepository, @ActivityContext private val context: Context) :
    CategoryBookContract.InteractorInput {
    override var output: WeakReference<CategoryBookContract.InteractorOutput>? = null
    override fun getCategories() {
        bookRepository.getCategories(object : CategoryRepositoryCallback{
            override fun onGetCategoriesSuccess(categories: List<Category>) {
                super.onGetCategoriesSuccess(categories)
            }

            override fun onGetCategoriesDidFail(errorKey: ApiErrorKey) {
                super.onGetCategoriesDidFail(errorKey)
            }
        })

        //output?.onShowCategories(bookRepository.getCategories())
    }
}