package com.assignment.bongotalkies.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.assignment.bongotalkies.database.DatabaseMoviesListItem
import com.assignment.bongotalkies.database.MovieDatabase
import com.assignment.bongotalkies.domain.MovieDetails
import com.assignment.bongotalkies.network.MovieListService
import timber.log.Timber
import javax.inject.Inject

class MovieDetailsRepository @Inject constructor(
    private val movieListService: MovieListService,
    private val database: MovieDatabase
) {

    var itemsArray: ArrayList<DatabaseMoviesListItem> = ArrayList()


    /*fun getMovieDetails(movieId: Int): LiveData<List<MovieDetails>> {
        return Transformations.map(database.moviesDao.getMovieDetails(movieId)) {
            it?.asDomainModel()
        }
    }*/

    suspend fun fetchMovieDetails(movieId: Int) = movieListService.getMovieDetails(movieId)

   /* suspend fun fetchMovieDetails(movieId: Int) {
        try {

            val movie = movieListService.getMovieDetails(movieId)
            if (movie.isSuccessful) {

                val adult = movie.body()?.adult
                val overview = movie.body()?.overview
                val popularity = movie.body()?.popularity

                val model =
                    DatabaseMoviesListItem(
                                123,
                                "adult",
                                "backdropPath",
                                "title",
                                "overview",
                                "popularity"
                            )

                itemsArray.add(model)

            }
            database.moviesDao.insertAll(itemsArray)
        } catch (e: Exception) {
            Timber.w(e)
        }
    }*/


}