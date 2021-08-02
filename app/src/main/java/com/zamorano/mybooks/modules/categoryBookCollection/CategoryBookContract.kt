package com.zamorano.mybooks.modules.categoryBookCollection

import com.zamorano.mybooks.model.api.Category
import java.lang.ref.WeakReference

interface CategoryBookContract {
    interface Presentation {
        var interactor: InteractorInput
        var router: Wireframe
        var view: WeakReference<View>?

        fun getCategories()
        fun onChooseCategory(category: Category)
    }

    interface InteractorInput {
        var output: WeakReference<InteractorOutput>?
        fun getCategories()
    }

    interface InteractorOutput {
        fun onShowCategories(categories : List<Category>)
        fun onShowError(error : String)
    }

    interface Wireframe {
        fun showBookDetail(category: Category)
    }

    interface View {
        fun showCategories(categories: List<Category>)
        fun showGenericServiceError(errorText: String)
    }
}

