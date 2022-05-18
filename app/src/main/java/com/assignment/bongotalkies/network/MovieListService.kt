package com.assignment.bongotalkies.network


import com.assignment.bongotalkies.BuildConfig
import com.assignment.bongotalkies.network.model.NetworkMovieListItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieListService {

    @GET("movie/top_rated?api_key="+BuildConfig.API_KEY+"&language=en-US")
    suspend fun getMovieList(
        @Query("page") page:Int
    ): Response<NetworkMovieListItem>
}