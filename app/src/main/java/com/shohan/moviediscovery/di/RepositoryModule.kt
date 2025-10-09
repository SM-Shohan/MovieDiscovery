package com.shohan.moviediscovery.di
import com.shohan.moviediscovery.feature.movie_details.data.repoImpl.MovieDetailsRepoImpl
import com.shohan.moviediscovery.feature.movie_details.domain.repository.MovieDetailsRepo
import com.shohan.moviediscovery.feature.movie_discovery.data.repositoryImpl.MovieDiscoveryRepoImpl
import com.shohan.moviediscovery.feature.movie_discovery.domain.repository.MovieDiscoveryRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindMovieDiscoveryRepository(impl: MovieDiscoveryRepoImpl): MovieDiscoveryRepo
    @Binds
    abstract fun bindMovieDetailsRepository(impl: MovieDetailsRepoImpl): MovieDetailsRepo
}