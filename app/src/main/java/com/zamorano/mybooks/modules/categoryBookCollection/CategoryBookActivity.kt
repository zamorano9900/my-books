package com.zamorano.mybooks.modules.categoryBookCollection

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.recyclerview.widget.LinearLayoutManager
import com.zamorano.mybooks.R
import com.zamorano.mybooks.model.api.Category
import com.zamorano.mybooks.modules.base.BaseActivity
import com.zamorano.mybooks.modules.categoryBookCollection.subviews.CategoryBookAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.content_book_collection.*
import java.lang.ref.WeakReference
import javax.inject.Inject

@AndroidEntryPoint
class CategoryBookActivity : BaseActivity(), CategoryBookContract.View {
    @Inject
    lateinit var presenter: CategoryBookContract.Presentation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_collection)

        presenter.view = WeakReference(this)
        presenter.getCategories()
    }

    override fun showCategories(categories: List<Category>) {
        val categoriesAdapter = CategoryBookAdapter(this, presenter, categories)
        recyclerView.adapter = categoriesAdapter
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)
    }

    override fun showGenericServiceError(errorText: String) {
        recyclerView.visibility = GONE
        emptyrecyclerView.visibility = VISIBLE
        emptyrecyclerView.text = errorText
    }
}