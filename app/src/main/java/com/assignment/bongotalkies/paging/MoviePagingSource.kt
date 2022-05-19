package com.assignment.bongotalkies.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.assignment.bongotalkies.domain.ResultMovie
import com.assignment.bongotalkies.network.MovieListService

import java.lang.Exception

class MoviePagingSource(private val movieListService: MovieListService):
    PagingSource<Int, ResultMovie>() {

    override fun getRefreshKey(state: PagingState<Int, ResultMovie>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResultMovie> {
        return try {
            val currentPage = params.key ?: 1
            val response = movieListService.getMovieList(currentPage)

            val data = response.body()?.results ?: emptyList()
            val responseData = mutableListOf<ResultMovie>()
            responseData.addAll(data)

            LoadResult.Page(
                data= responseData,
                prevKey = if (currentPage == 1) null else -1,
                nextKey = currentPage.plus(1)
            )

        }catch (e:Exception){
            LoadResult.Error(e)
        }
    }
}