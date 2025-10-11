package com.shohan.moviediscovery.di

import android.content.Context
import androidx.room.Room
import com.shohan.moviediscovery.feature.movie_fav.data.local.FavMovieDatabase
import com.shohan.moviediscovery.feature.movie_fav.data.local.FavMovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideMovieDatabase(@ApplicationContext context: Context): FavMovieDatabase =
        Room.databaseBuilder(
            context,
            FavMovieDatabase::class.java,
            "movie_db"
        ).build()

    @Provides
    @Singleton
    fun provideMovieDao(database: FavMovieDatabase): FavMovieDao = database.movieDao()
}
