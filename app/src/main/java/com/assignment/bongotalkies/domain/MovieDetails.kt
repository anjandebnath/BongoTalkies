package com.assignment.bongotalkies.domain


data class MovieDetails(
    val id: Int,
    val adult: String,
    val backdropPath: String,
    val title : String,
    val overview: String,
    val popularity: String,
)