package com.assignment.bongotalkies.repository


import com.assignment.bongotalkies.network.MovieListService
import javax.inject.Inject

class MovieDetailsRepository @Inject constructor(
    private val movieListService: MovieListService
) {

    suspend fun fetchMovieDetails(movieId: Int) = movieListService.getMovieDetails(movieId)

}