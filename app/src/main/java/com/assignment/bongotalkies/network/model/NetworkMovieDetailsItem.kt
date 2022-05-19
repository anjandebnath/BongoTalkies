package com.assignment.bongotalkies.network.model

import com.google.gson.annotations.SerializedName

data class NetworkMovieDetailsItem(
    @SerializedName("adult")
    val adult : String,
    @SerializedName("backdrop_path")
    val backdropPath : String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("original_title")
    val originalTitle: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("popularity")
    val popularity: String
)