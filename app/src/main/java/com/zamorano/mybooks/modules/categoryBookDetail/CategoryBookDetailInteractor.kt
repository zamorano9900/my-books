package com.zamorano.mybooks.modules.categoryBookDetail

import android.content.Context
import com.zamorano.mybooks.model.api.Category
import com.zamorano.mybooks.model.api.CategoryType
import com.zamorano.mybooks.model.repositories.BookRepository
import dagger.hilt.android.qualifiers.ActivityContext
import java.lang.ref.WeakReference
import javax.inject.Inject

class CategoryBookDetailInteractor @Inject constructor(@ActivityContext private val context: Context) :
    CategoryBookDetailContract.InteractorInput {
    override var output: WeakReference<CategoryBookDetailContract.InteractorOutput>? = null

    override fun getCategoryTypeDetail(categoryType: CategoryType) {
        //TODO Create calls for category
    }
}