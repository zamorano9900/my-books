package com.zamorano.mybooks.model.api

import com.google.gson.annotations.SerializedName
import java.util.*

data class Book(
    @SerializedName("isbn")
    val isbn: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("authors")
    val authors: MutableList<String>,
    @SerializedName("numberOfPages")
    val numberOfPages: Int,
    @SerializedName("publisher")
    val publisher: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("mediaType")
    val mediaType: String,
    @SerializedName("released")
    val released: String
)


