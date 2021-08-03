package com.zamorano.mybooks.model.api

import androidx.annotation.Nullable
import com.google.gson.annotations.SerializedName

class ApiResultEntity {
    @SerializedName("book")
    @Nullable
    var bookResponse: List<Book>? = null

    @SerializedName("char")
    @Nullable
    var charResponse: List<Char>? = null

    @SerializedName("house")
    @Nullable
    var houseResponse: List<House>? = null
}
