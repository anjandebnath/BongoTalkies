package com.assignment.bongotalkies.views.top_movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.assignment.bongotalkies.network.MovieListService
import com.assignment.bongotalkies.paging.MoviePagingSource
import com.assignment.bongotalkies.repository.MovieListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel
@Inject constructor(
    private val movieListRepository: MovieListRepository,
    private val movieListService: MovieListService
) : ViewModel() {

    val data = movieListRepository.movies

    val listData = Pager(PagingConfig(pageSize = 1)){
        MoviePagingSource(movieListService)

    }.flow.cachedIn(viewModelScope)

    /*init {
        viewModelScope.launch(Dispatchers.IO) {
            movieListRepository.refreshMovieList()
        }
    }*/

}