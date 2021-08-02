package com.zamorano.mybooks.modules.categoryBookCollection.subviews

import com.zamorano.mybooks.R
import com.zamorano.mybooks.model.api.Category
import com.zamorano.mybooks.modules.categoryBookCollection.CategoryBookContract
import kotlinx.android.synthetic.main.row_category_layout.view.*
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CategoryBookAdapter(
    private val context: Context,
    private val presenter: CategoryBookContract.Presentation,
    private val categories: List<Category>
) : RecyclerView.Adapter<CategoryBookAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.row_category_layout, p0, false)
        val params = view.layoutParams
        view.layoutParams = params

        return MyViewHolder(view, presenter)
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.updateCategoryPiece(categories[position])
    }

    class MyViewHolder(
        view: View,
        val presenter: CategoryBookContract.Presentation
    ) : RecyclerView.ViewHolder(view) {
        private val titleTextView: TextView = view.titleTextView

        private var category: Category? = null

        fun updateCategoryPiece(category: Category) {
            this.category = category
            this.titleTextView.text = category.category_name
        }

        init {
            this.titleTextView.setOnClickListener { 
                //TODO presenter!!.onChooseCategroy(category!!)
            }
        }
    }
}

