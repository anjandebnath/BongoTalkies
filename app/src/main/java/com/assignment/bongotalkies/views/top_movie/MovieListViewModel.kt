package com.assignment.bongotalkies.views.top_movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assignment.bongotalkies.repository.MovieListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val movieListRepository: MovieListRepository
) : ViewModel() {

    val data = movieListRepository.users

    init {
        viewModelScope.launch(Dispatchers.IO) {
            movieListRepository.refreshUserList()
        }
    }

}