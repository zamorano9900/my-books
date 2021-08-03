package com.zamorano.mybooks.modules.categoryBookDetail.subviews

import com.zamorano.mybooks.R
import kotlinx.android.synthetic.main.row_category_layout.view.*
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.zamorano.mybooks.model.api.*
import com.zamorano.mybooks.model.api.Char
import kotlinx.android.synthetic.main.row_book_layout.view.*
import kotlinx.android.synthetic.main.row_category_layout.view.titleTextView

class CategoryBookDetailAdapter(
    private val context: Context,
    private val categories: ApiResultEntity,
    private val categoryType: CategoryType
) : RecyclerView.Adapter<CategoryBookDetailAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.row_book_layout, p0, false)
        val params = view.layoutParams
        view.layoutParams = params

        return MyViewHolder(view, context)
    }

    override fun getItemCount(): Int {
        return when (categoryType) {
            CategoryType.BOOK -> categories.bookResponse?.size!!
            CategoryType.HOUSE -> categories.houseResponse?.size!!
            CategoryType.CHAR -> categories.charResponse?.size!!
            else -> categories.bookResponse?.size!!
        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        when (categoryType) {
            CategoryType.BOOK -> holder.updateBook(categories.bookResponse?.get(position)!!)
            CategoryType.HOUSE -> holder.updateHouse(categories.houseResponse?.get(position)!!)
            CategoryType.CHAR -> holder.updateChar(categories.charResponse?.get(position)!!)
            else -> holder.updateBook(categories.bookResponse?.get(position)!!)
        }
    }

    class MyViewHolder(
        view: View,
        val context: Context
    ) : RecyclerView.ViewHolder(view) {
        private val titleTextView: TextView = view.titleTextView
        private val regionImageView: ImageView = view.regionImageView

        fun updateBook(book: Book) {
            this.titleTextView.text = serializeList(book)
        }

        fun updateHouse(house: House) {
            this.titleTextView.text = serializeList(house)
            this.regionImageView.visibility = VISIBLE

            Glide.with(context)
                .load(house.image)
                .error(android.R.drawable.stat_notify_error)
                .centerCrop()
                .apply(
                    RequestOptions()
                        .placeholder(R.color.colorPrimary)
                )
                .into(regionImageView)
        }

        fun updateChar(char: Char) {
            this.titleTextView.text = serializeList(char)
        }

        private fun <T> serializeList(model: T?): String? {
            val gson = Gson()
            return gson.toJson(model)
        }
    }
}

