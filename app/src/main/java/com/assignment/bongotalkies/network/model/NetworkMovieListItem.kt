package com.assignment.bongotalkies.network.model


import com.google.gson.annotations.SerializedName

data class NetworkMovieListItem(
    @SerializedName("results")
    val result: List<ResultMovie>
)







