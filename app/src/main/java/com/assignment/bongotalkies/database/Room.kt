package com.assignment.bongotalkies.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(movie: List<DatabaseMoviesListItem>)

    // single movie
    @Query("select * from DatabaseMoviesListItem WHERE id LIKE :movieId")
    fun getMovieDetails(movieId: Int): LiveData<DatabaseMoviesListItem>


}

@Database(entities = [DatabaseMoviesListItem::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract val moviesDao: MoviesDao
}