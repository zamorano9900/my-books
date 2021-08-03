package com.zamorano.mybooks.modules.categoryBookDetail

import com.zamorano.mybooks.model.api.ApiResultEntity
import com.zamorano.mybooks.model.api.CategoryType
import java.lang.ref.WeakReference

interface CategoryBookDetailContract {
    interface Presentation {
        var interactor: InteractorInput
        var router: Wireframe
        var view: WeakReference<View>?

        fun getCategoryTypeDetail(categoryType: CategoryType)
    }

    interface InteractorInput {
        var output: WeakReference<InteractorOutput>?
        fun getCategoryTypeDetail(categoryType: CategoryType)
    }

    interface InteractorOutput {
        fun onShowError(error : String)
        fun onShowCategoryDetail(categories : ApiResultEntity)
    }

    interface Wireframe {
    }

    interface View {
        fun showCategories(categories: ApiResultEntity)
        fun showGenericServiceError(errorText: String)
    }
}

