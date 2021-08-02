package com.zamorano.mybooks.model.api

import com.google.gson.annotations.SerializedName
import java.util.*

data class Char(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("culture")
    val culture: String,
    @SerializedName("born")
    val born: String,
    @SerializedName("died")
    val died: String,
    @SerializedName("aliases")
    val aliases: List<String>,
    @SerializedName("father")
    val father: String,
    @SerializedName("mother")
    val mother: String,
    @SerializedName("spouse")
    val spouse: String,
    @SerializedName("allegiances")
    val allegiances: List<String>,
    @SerializedName("playedBy")
    val playedBy: List<String>
)