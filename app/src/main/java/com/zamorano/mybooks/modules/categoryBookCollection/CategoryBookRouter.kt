package com.zamorano.mybooks.modules.categoryBookCollection

import android.content.Context
import android.content.Intent
import com.zamorano.mybooks.model.api.Category
import com.zamorano.mybooks.modules.categoryBookDetail.CategoryBookDetailActivity
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

class CategoryBookRouter @Inject constructor(@ActivityContext private val context: Context):
    CategoryBookContract.Wireframe {

    override fun showBookDetail(category: Category) {
        val intent = Intent(context, CategoryBookDetailActivity::class.java)
        intent.putExtra(CategoryBookDetailActivity.BundleIntents.Category, category)
        context.startActivity(intent)
    }
}