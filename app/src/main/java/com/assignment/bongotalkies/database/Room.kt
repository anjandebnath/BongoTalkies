package com.assignment.bongotalkies.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MoviesDao {

    // user List
    @Query("select * from DatabaseMoviesListItem")
    fun getDatabaseUsers(): LiveData<List<DatabaseMoviesListItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(users: List<DatabaseMoviesListItem>)

    // single user
    @Query("select * from DatabaseMoviesListItem WHERE id LIKE :userId")
    fun getUserDetails(userId: Int): LiveData<DatabaseMoviesListItem>


}

@Database(entities = [DatabaseMoviesListItem::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract val moviesDao: MoviesDao
}