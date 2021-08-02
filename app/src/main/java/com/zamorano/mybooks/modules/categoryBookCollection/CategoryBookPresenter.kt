package com.zamorano.mybooks.modules.categoryBookCollection

import com.zamorano.mybooks.model.api.Category
import java.lang.ref.WeakReference
import javax.inject.Inject

class CategoryBookPresenter @Inject constructor(override var interactor: CategoryBookContract.InteractorInput,
                                                override var router: CategoryBookContract.Wireframe) : CategoryBookContract.Presentation, CategoryBookContract.InteractorOutput {

    override var view: WeakReference<CategoryBookContract.View>? = null

    init {
        interactor.output = WeakReference(this)
    }

    override fun getCategories() {
        interactor.getCategories()
    }

    override fun onChooseCategory(category: Category) {
        router.showBookDetail(category)
    }

    //endregion presentation

    //region Output

    override fun onShowCategories(categories: List<Category>) {
        view?.get()?.showCategories(categories)
    }

    override fun onShowError(error: String) {
        view?.get()?.showGenericServiceError(error)
    }

    //endregion Output

}