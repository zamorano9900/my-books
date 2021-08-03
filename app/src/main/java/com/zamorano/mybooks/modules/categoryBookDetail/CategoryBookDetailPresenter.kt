package com.zamorano.mybooks.modules.categoryBookDetail

import com.zamorano.mybooks.model.api.ApiResultEntity
import com.zamorano.mybooks.model.api.CategoryType
import java.lang.ref.WeakReference
import javax.inject.Inject

class CategoryBookDetailPresenter @Inject constructor(override var interactor: CategoryBookDetailContract.InteractorInput,
                                                      override var router: CategoryBookDetailContract.Wireframe) : CategoryBookDetailContract.Presentation, CategoryBookDetailContract.InteractorOutput {

    override var view: WeakReference<CategoryBookDetailContract.View>? = null

    init {
        interactor.output = WeakReference(this)
    }

    //region Presentation

    override fun getCategoryTypeDetail(categoryType: CategoryType) {
        interactor.getCategoryTypeDetail(categoryType)
    }

    //endregion presentation

    //region Output
    override fun onShowError(error: String) {
        view?.get()?.showGenericServiceError(error)
    }

    override fun onShowCategoryDetail(categories: ApiResultEntity) {
        view?.get()?.showCategories(categories)
    }

    //endregion Output

}