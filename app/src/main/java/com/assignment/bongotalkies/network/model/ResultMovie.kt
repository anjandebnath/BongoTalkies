package com.assignment.bongotalkies.network.model

import com.google.gson.annotations.SerializedName

data class ResultMovie(
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
    val popularity: String,
    @SerializedName("poster_path")
    val posterPath : String,
    @SerializedName("release_date")
    val releaseDate : String,
    @SerializedName("title")
    val title: String,
    @SerializedName("vote_average")
    val voteAvg : String,
    @SerializedName("vote_count")
    val voteCount: String
)