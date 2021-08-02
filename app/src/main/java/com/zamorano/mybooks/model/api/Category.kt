package com.zamorano.mybooks.model.api

import com.google.gson.annotations.SerializedName
import java.util.*

data class Category(
    @SerializedName("category_name")
    val category_name: String,
    @SerializedName("type")
    val type: String
)


