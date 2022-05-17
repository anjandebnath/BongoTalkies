package com.assignment.bongotalkies.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.assignment.bongotalkies.domain.MovieDetails
import com.assignment.bongotalkies.domain.MovieListItem

@Entity
data class DatabaseMoviesListItem constructor(
    @PrimaryKey
    val id: Int,
    val adult: String,
    val backdropPath: String,
    val originalTitle: String,
    val overview: String,
    val popularity: String,
    val posterPath: String,
    val releaseDate: String,
    val title : String,
    val voteAvg : String,
    val voteCount : String


)

fun List<DatabaseMoviesListItem>.asDomainModel(): List<MovieListItem> {
    return map {
        MovieListItem(
            id = it.id,
            adult = it.adult,
            backdropPath = it.backdropPath,
            originalTitle = it.originalTitle,
            overview = it.overview,
            popularity = it.popularity,
            posterPath = it.posterPath,
            releaseDate = it.releaseDate,
            title = it.title,
            voteAvg = it.voteAvg,
            voteCount = it.voteCount
        )
    }
}

fun DatabaseMoviesListItem.asDomainModel(): MovieDetails {
    return MovieDetails(
        //id = id,
//        avatar = largeAvatar,
//        username = username,
//        city = city,
//        state = state,
//        country = country,
//        email = email,
//        cellPhone = cellPhone
    )
}