package com.zamorano.mybooks.model.api

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class House(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("region")
    val region: String,
    @SerializedName("title")
    val title: String
): Parcelable {
    val image: String
        get() {
            return when (region) {
                "The North" -> "https://bit.ly/2Gcq0r4"
                "The Vale" -> "https://bit.ly/34FAvws"
                "The Riverlands", "IronIslands" -> "https://bit.ly/3kJrIiP"
                "The Westerlands" -> "https://bit.ly/2TAzjnO"
                "TheReach" -> "https://bit.ly/2HSCW5N"
                "Dorne" -> "https://bit.ly/2HOcavT"
                "The Stormlands" -> "https://bit.ly/34F2sEC"
                else -> ""
            }
        }
}