package com.assignment.bongotalkies.domain

data class MovieListItem(
    val id: Int,
    val adult: String,
    val backdropPath: String,
    val originalTitle: String,
    val overview: String,
    val popularity: String,
    val posterPath: String,
    val releaseDate: String,
    val title : String,
    val voteAvg : String,
    val voteCount : String
)