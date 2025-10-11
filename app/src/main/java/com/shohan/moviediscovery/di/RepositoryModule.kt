package com.shohan.moviediscovery.di
import com.shohan.moviediscovery.feature.movie_details.data.repoImpl.MovieDetailsRepoImpl
import com.shohan.moviediscovery.feature.movie_details.domain.repository.MovieDetailsRepo
import com.shohan.moviediscovery.feature.movie_discovery.data.repositoryImpl.MovieDiscoveryRepoImpl
import com.shohan.moviediscovery.feature.movie_discovery.domain.repository.MovieDiscoveryRepo
import com.shohan.moviediscovery.feature.movie_fav.data.repositoryImpl.FavMovieRepoImpl
import com.shohan.moviediscovery.feature.movie_fav.domain.repository.FavMovieRepo
import com.shohan.moviediscovery.feature.movie_search.data.repoImpl.SearchMovieRepoImpl
import com.shohan.moviediscovery.feature.movie_search.domain.repository.SearchMovieRepo
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
    @Binds
    abstract fun bindSearchMovieRepository(impl: SearchMovieRepoImpl): SearchMovieRepo
    @Binds
    abstract fun bindFavMovieRepository(impl: FavMovieRepoImpl): FavMovieRepo

}