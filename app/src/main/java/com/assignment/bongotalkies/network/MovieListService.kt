package com.assignment.bongotalkies.network


import com.assignment.bongotalkies.BuildConfig
import com.assignment.bongotalkies.network.model.NetworkMovieListItem
import retrofit2.Response
import retrofit2.http.GET

interface MovieListService {

    @GET("movie/top_rated?api_key="+BuildConfig.API_KEY+"&language=en-US&page=1")
    suspend fun getMovieList(): Response<NetworkMovieListItem>
}