package com.zamorano.mybooks.modules.categoryBookDetail

import com.zamorano.mybooks.model.api.ApiResultEntity
import com.zamorano.mybooks.model.api.CategoryType
import com.zamorano.mybooks.model.network.ApiErrorKey
import com.zamorano.mybooks.model.repositories.BookRepository
import com.zamorano.mybooks.model.repositories.CategoryRepositoryCallback
import java.lang.ref.WeakReference
import javax.inject.Inject

class CategoryBookDetailInteractor @Inject constructor(
    private val bookRepository: BookRepository
) :
    CategoryBookDetailContract.InteractorInput {
    override var output: WeakReference<CategoryBookDetailContract.InteractorOutput>? = null

    override fun getCategoryTypeDetail(categoryType: CategoryType) {
        bookRepository.getCategoryDetail(categoryType, object : CategoryRepositoryCallback {
            override fun onGetCategoryDetailSuccess(categories: ApiResultEntity) {
                super.onGetCategoryDetailSuccess(categories)
                output?.get()?.onShowCategoryDetail(categories)
            }

            override fun onGetCategoriesDidFail(errorKey: ApiErrorKey) {
                super.onGetCategoriesDidFail(errorKey)
                output?.get()?.onShowError(errorKey.name)
            }
        })
    }
}