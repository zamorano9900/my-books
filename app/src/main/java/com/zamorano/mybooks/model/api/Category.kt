package com.zamorano.mybooks.model.api

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Category(
    @SerializedName("type")
    val type: String,
    @SerializedName("category_name")
    val category_name: String
): Parcelable {
    val categoryType: CategoryType
        get() {
            return when (type) {
                "0" -> CategoryType.BOOKS
                "1" -> CategoryType.HOUSE
                "2" -> CategoryType.CHARS
                else -> CategoryType.BOOKS
            }
        }
}

enum class CategoryType {
    BOOKS,
    HOUSE,
    CHARS
}


