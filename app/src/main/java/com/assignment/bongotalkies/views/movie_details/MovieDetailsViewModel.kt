package com.assignment.bongotalkies.views.movie_details

import androidx.databinding.ObservableParcelable
import androidx.lifecycle.ViewModel
import com.assignment.bongotalkies.domain.MovieDetails
import com.assignment.bongotalkies.repository.MovieDetailsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val movieDetailsRepository: MovieDetailsRepository
) : ViewModel() {

    val rqstUserDetails = ObservableParcelable(MovieDetails())

    fun getMovieDetails(userId: Int) = movieDetailsRepository.getMovieDetails(userId)


}