package com.zamorano.mybooks.modules.categoryBookCollection

import android.os.Bundle
import android.os.PersistableBundle
import com.zamorano.mybooks.R
import com.zamorano.mybooks.modules.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
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

    override fun showGenericServiceError(errorText: String) {
        TODO("Not yet implemented")
    }

    override fun showLoading(show: Boolean) {
        TODO("Not yet implemented")
    }
}