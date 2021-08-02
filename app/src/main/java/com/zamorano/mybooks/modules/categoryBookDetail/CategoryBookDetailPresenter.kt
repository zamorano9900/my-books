package com.zamorano.mybooks.modules.categoryBookDetail

import com.zamorano.mybooks.model.api.CategoryType
import java.lang.ref.WeakReference
import javax.inject.Inject

class CategoryBookDetailPresenter @Inject constructor(override var interactor: CategoryBookDetailContract.InteractorInput,
                                                      override var router: CategoryBookDetailContract.Wireframe) : CategoryBookDetailContract.Presentation, CategoryBookDetailContract.InteractorOutput {

    override var view: WeakReference<CategoryBookDetailContract.View>? = null

    init {
        interactor.output = WeakReference(this)
    }

    override fun getCategoryTypeDetail(categoryType: CategoryType) {
        TODO("Not yet implemented")
    }

    //endregion presentation
}