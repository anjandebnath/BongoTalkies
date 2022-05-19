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
    val title : String,
    val overview: String,
    val popularity: String
)


fun List<DatabaseMoviesListItem>.asDomainModel(): List<MovieDetails> {
    return map {
        MovieDetails(
            id = it.id,
            adult = it.adult,
            backdropPath = it.backdropPath,
            title = it.title,
            overview = it.overview,
            popularity = it.popularity,
            )
    }

}