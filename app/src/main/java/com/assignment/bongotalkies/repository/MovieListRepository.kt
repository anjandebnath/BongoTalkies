package com.assignment.bongotalkies.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.assignment.bongotalkies.database.DatabaseMoviesListItem
import com.assignment.bongotalkies.database.MovieDatabase
import com.assignment.bongotalkies.database.asDomainModel
import com.assignment.bongotalkies.domain.MovieListItem
import com.assignment.bongotalkies.network.MovieListService
import com.assignment.bongotalkies.util.Constants
import timber.log.Timber
import java.util.ArrayList
import javax.inject.Inject

class MovieListRepository @Inject constructor(
    private val userListService: MovieListService,
    private val database: MovieDatabase
) {
    var itemsArray: ArrayList<DatabaseMoviesListItem> = ArrayList()

    val movies: LiveData<List<MovieListItem>> =
        Transformations.map(database.moviesDao.getDatabaseUsers()) {
            it.asDomainModel()
        }


    suspend fun refreshMovieList() {
        try {

            val users = userListService.getMovieList(1)
            if (users.isSuccessful) {
                val items = users.body()?.result
                if (items != null) {
                    for (i in 0 until items.count()) {
                        // ID
                        val id = items[i].id.toInt()
                        val adult = items[i].adult ?: "N/A"
                        val backdropPath = Constants.IMAGE_CDN+items[i].backdropPath ?: "N/A"

                        val originalTitle = items[i].originalTitle?: "N/A"
                        val overview = items[i].overview?: "N/A"
                        val popularity = items[i].popularity?: "N/A"

                        val posterPath = Constants.IMAGE_CDN+items[i].posterPath?: "N/A"
                        val releaseDate = items[i].releaseDate?: "N/A"
                        val title = items[i].title?: "N/A"

                        val voteAvg = items[i].voteAvg?: "N/A"
                        val voteCount = items[i].voteCount?: "N/A"


                        val model =
                            DatabaseMoviesListItem(
                                id,
                                adult,
                                backdropPath,
                                originalTitle,
                                overview,
                                popularity,
                                posterPath,
                                releaseDate,
                                title,
                                voteAvg,
                                voteCount
                            )

                        itemsArray.add(model)

                    }
                }
                database.moviesDao.insertAll(itemsArray)
            }
        } catch (e: Exception) {
            Timber.w(e)
        }
    }
}