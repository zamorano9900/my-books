package com.zamorano.mybooks.model.api

import com.google.gson.annotations.SerializedName
import java.util.*

data class House(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("region")
    val region: String,
    @SerializedName("title")
    val title: String
)