package com.zamorano.mybooks.modules.categoryBookCollection

import java.lang.ref.WeakReference

interface CategoryBookContract {
    interface Presentation {
        var interactor: InteractorInput
        var router: Wireframe
        var view: WeakReference<View>?

        fun getCategories()
        fun onShowLoading(show : Boolean)
    }

    interface InteractorInput {
        var output: WeakReference<InteractorOutput>?
        fun getCategories()
    }

    interface InteractorOutput {
        fun onShowLoading(show : Boolean)
    }

    interface Wireframe {
        fun showBookDetail(id: Int)
    }

    interface View {
        fun showGenericServiceError(errorText: String)
        fun showLoading(show : Boolean)
    }
}

