package com.shohan.moviediscovery.feature.movie_fav.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
abstract class FavMovieDatabase : RoomDatabase() {
    abstract fun movieDao(): FavMovieDao
}
