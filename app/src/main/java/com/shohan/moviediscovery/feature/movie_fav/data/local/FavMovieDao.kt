package com.shohan.moviediscovery.feature.movie_fav.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavMovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: MovieEntity)

    @Delete
    suspend fun deleteMovie(movie: MovieEntity)

    @Query("SELECT * FROM favorite_movies")
    fun getAllMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM favorite_movies WHERE id = :movieId")
    suspend fun getMovieById(movieId: Int): MovieEntity?
}
