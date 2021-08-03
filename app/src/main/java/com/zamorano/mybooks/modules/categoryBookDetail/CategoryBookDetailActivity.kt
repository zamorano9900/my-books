package com.zamorano.mybooks.modules.categoryBookDetail

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.zamorano.mybooks.R
import com.zamorano.mybooks.model.api.ApiResultEntity
import com.zamorano.mybooks.model.api.Category
import com.zamorano.mybooks.modules.base.BaseActivity
import com.zamorano.mybooks.modules.categoryBookCollection.subviews.CategoryBookAdapter
import com.zamorano.mybooks.modules.categoryBookDetail.subviews.CategoryBookDetailAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.content_book_collection.*
import java.lang.ref.WeakReference
import javax.inject.Inject

@AndroidEntryPoint
class CategoryBookDetailActivity : BaseActivity(), CategoryBookDetailContract.View {
    @Inject
    lateinit var presenter: CategoryBookDetailContract.Presentation
    var category: Category? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_collection)

        category = intent.extras?.getParcelable(BundleIntents.Category)

        supportActionBar?.title = category?.category_name

        presenter.view = WeakReference(this)

        presenter.getCategoryTypeDetail(category?.categoryType!!)
    }

    override fun showCategories(categories: ApiResultEntity) {
        val categoriesAdapter = CategoryBookDetailAdapter(this, categories, category?.categoryType!!)
        recyclerView.adapter = categoriesAdapter
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)
    }

    override fun showGenericServiceError(errorText: String) {
        recyclerView.visibility = View.GONE
        emptyrecyclerView.visibility = View.VISIBLE
        emptyrecyclerView.text = errorText
    }

    class BundleIntents {
        companion object {
            const val Category: String = "Category"
        }
    }
}