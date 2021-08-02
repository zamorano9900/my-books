package com.zamorano.mybooks.model.api

import com.google.gson.annotations.SerializedName
import java.util.*

data class Category(
    @SerializedName("type")
    val type: String,
    @SerializedName("category_name")
    val category_name: String
)


