package com.zamorano.mybooks.modules.categoryBookDetail

import android.os.Bundle
import android.os.PersistableBundle
import com.zamorano.mybooks.R
import com.zamorano.mybooks.model.api.Category
import com.zamorano.mybooks.modules.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import java.lang.ref.WeakReference
import javax.inject.Inject

@AndroidEntryPoint
class CategoryBookDetailActivity : BaseActivity(), CategoryBookDetailContract.View {
    @Inject
    lateinit var presenter: CategoryBookDetailContract.Presentation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)

        val category: Category? = intent.extras?.getParcelable(BundleIntents.Category)

        supportActionBar?.title = category?.category_name

        presenter.view = WeakReference(this)
    }

    override fun showGenericServiceError(errorText: String) {
        TODO("Not yet implemented")
    }

    class BundleIntents {
        companion object {
            const val Category: String = "Category"
        }
    }
}