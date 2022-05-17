package com.assignment.bongotalkies.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.assignment.bongotalkies.database.MovieDatabase
import com.assignment.bongotalkies.database.asDomainModel
import com.assignment.bongotalkies.domain.MovieDetails
import javax.inject.Inject

class MovieDetailsRepository @Inject constructor(
    private val database: MovieDatabase
) {

    fun getMovieDetails(userId: Int): LiveData<MovieDetails> {
        return Transformations.map(database.moviesDao.getUserDetails(userId)) {
            it?.asDomainModel()
        }
    }


}