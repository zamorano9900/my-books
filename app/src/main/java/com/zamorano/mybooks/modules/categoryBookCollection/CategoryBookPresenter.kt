package com.zamorano.mybooks.modules.categoryBookCollection

import java.lang.ref.WeakReference
import javax.inject.Inject

class CategoryBookPresenter @Inject constructor(override var interactor: CategoryBookContract.InteractorInput,
                                                override var router: CategoryBookContract.Wireframe) : CategoryBookContract.Presentation, CategoryBookContract.InteractorOutput {

    override var view: WeakReference<CategoryBookContract.View>? = null
    override fun getCategories() {
        interactor.getCategories()
    }

    init {
        interactor.output = WeakReference(this)
    }


    //endregion presentation

    //region Output

    override fun onShowLoading(show : Boolean) {
        view?.get()?.showLoading(show)
    }

    //endregion Output

}