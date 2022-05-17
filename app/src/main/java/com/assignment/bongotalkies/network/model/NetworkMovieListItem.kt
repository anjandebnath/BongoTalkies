package com.assignment.bongotalkies.network.model


import com.google.gson.annotations.SerializedName

data class NetworkMovieListItem(
    @SerializedName("results")
    val result: List<Result>
)


data class Result(

    @SerializedName("adult")
    val adult : String,
    @SerializedName("backdrop_path")
    val backdropPath : String,
    @SerializedName("id")
    val id: String,
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

data class GenreList(
    @SerializedName("genre_ids")
    val genre: List<GenreList>
)






