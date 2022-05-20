package com.assignment.bongotalkies.views.top_movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.assignment.bongotalkies.network.MovieListService
import com.assignment.bongotalkies.paging.MoviePagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel
@Inject constructor( // Primary constructor
    private val movieListService: MovieListService
) : ViewModel() {

    val listData = Pager(PagingConfig(pageSize = 1)){
        MoviePagingSource(movieListService)

    }.flow.cachedIn(viewModelScope) // flow allows continous stream of data


}