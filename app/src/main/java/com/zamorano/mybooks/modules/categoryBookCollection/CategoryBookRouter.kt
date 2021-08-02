package com.zamorano.mybooks.modules.categoryBookCollection

import android.content.Context
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

class CategoryBookRouter @Inject constructor(@ActivityContext private val context: Context):
    CategoryBookContract.Wireframe {

    override fun showBookDetail(id: Int) {

    }
}