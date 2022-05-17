package com.assignment.bongotalkies.network


import com.assignment.bongotalkies.network.model.NetworkMovieListItem
import retrofit2.Response
import retrofit2.http.GET

interface MovieListService {

    @GET("movie/top_rated?api_key=c37d3b40004717511adb2c1fbb15eda4&language=en-US&page=1")
    suspend fun getMovieList(): Response<NetworkMovieListItem>
}