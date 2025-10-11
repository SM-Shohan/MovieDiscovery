# 📱 Android Movie App - Technical Documentation

## Table of Contents
- [Overview](#overview)
- [Architecture](#architecture)
    - [Clean Architecture](#clean-architecture)
    - [MVVM Pattern](#mvvm-pattern)
- [Layer Details](#layer-details)
    - [Presentation Layer](#presentation-layer)
    - [Domain Layer](#domain-layer)
    - [Data Layer](#data-layer)
- [Dependencies & Tools](#dependencies--tools)
- [Data Flow](#data-flow)
- [Future Improvements](#future-improvements)

## Overview

This technical documentation outlines the architectural decisions and implementation details of the Android Movie App. The application is built using modern Android development practices and follows Clean Architecture principles with MVVM pattern.

### Key Features
1. 🎬 Movie Discovery Screen
2. 🔍 Movie Search Screen
3. ⭐ Favorite Movie Screen
4. 📋 Movie Details Screen

## Architecture

### Clean Architecture

The project implements Clean Architecture to achieve:
- 🎯 Clear separation of concerns
- 📦 Independent layers
- 🔄 Dependency inversion
- 🧪 Improved testability

#### Layer Structure
```
app/
├── ui/             # UI + ViewModels
├── domain/         # Business Logic + Use Cases
└── data/           # Data Sources + Repositories
```

### MVVM Pattern

Model-View-ViewModel pattern is implemented using:
- 🎨 Jetpack Compose for UI
- 🔄 StateFlow for reactive state management
- 📱 ViewModels for UI logic

## Layer Details

### Presentation Layer

#### Components
1. **ViewModels**
```kotlin
class MovieDiscoveryViewModel @Inject constructor(
    private val getTrendingMoviesUseCase: GetTrendingMoviesUseCase
) : ViewModel() {
    private val _trendingMoviesState = MutableStateFlow<UiState<MovieResponse>>(UiState.Idle)
    val trendingMoviesState: StateFlow<UiState<MovieResponse>> = _trendingMoviesState

    fun fetchTrendingMovies() {
        viewModelScope.launch {
            _trendingMoviesState.value = UiState.Loading
            _trendingMoviesState.value = getTrendingMoviesUseCase()
        }
    }
}
```

2. **UI States**
```kotlin
sealed class UiState<out T> {
    object Idle : UiState<Nothing>()
    object Loading : UiState<Nothing>()
    data class Success<T>(val data: T) : UiState<T>()
    data class Error(val message: String) : UiState<Nothing>()
}
```

### Domain Layer

#### Use Cases
```kotlin
class GetTrendingMoviesUseCase @Inject constructor(
    private val repository: MovieDiscoveryRepo
) {
    suspend operator fun invoke(): UiState<MovieResponse> {
        return repository.getTrendingMovies()
    }
}
```

#### Repository Interfaces
```kotlin
interface MovieDiscoveryRepo {
    suspend fun getTrendingMovies(): UiState<MovieResponse>
    suspend fun searchMovies(query: String): UiState<MovieResponse>
    suspend fun getMovieDetails(movieId: Int): UiState<MovieDetails>
}
```

### Data Layer

#### Repository Implementation
```kotlin
class MovieDiscoveryRepoImpl @Inject constructor(
    private val api: MovieApi,
    private val dao: MovieDao
) : MovieDiscoveryRepo {
    override suspend fun getTrendingMovies(): UiState<MovieResponse> {
        return try {
            val response = api.getTrendingMovies()
            UiState.Success(response.toDomain())
        } catch (e: Exception) {
            UiState.Error(e.localizedMessage ?: "Unknown error")
        }
    }
}
```

#### Local Storage (Room)
```kotlin
@Entity(tableName = "movie_table")
data class MovieEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val posterPath: String?,
    val overview: String,
    val rating: Double
)

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: MovieEntity)

    @Delete
    suspend fun deleteMovie(movie: MovieEntity)
}
```

#### Network Layer (Retrofit)
```kotlin
interface APIService {

    @GET
    suspend fun getTrendingMovies(
        @Url url: String
    ): Response<TrendingMovieResponseDto>

    @GET
    suspend fun getSearchMovies(
        @Url url: String
    ): Response<SearchMovieResponseDto>

}
```

## Dependencies & Tools

### Core Dependencies
- **Kotlin Coroutines & Flow** - Asynchronous programming
- **Hilt** - Dependency injection
- **Jetpack Compose** - Modern UI toolkit
- **Room** - Local database
- **Retrofit** - Network calls
- **Coil** - Image loading

### Dependency Injection
```kotlin
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
```

## Data Flow

```
User Action
    ↓
Composable Screen
    ↓
ViewModel (StateFlow)
    ↓
Use Case
    ↓
Repository Interface
   / \
  /   \
API   Room
  \   /
   \ /
Domain Models
    ↓
UI Update
```

## Future Improvements

### Planned Enhancements
1. Implement Paging 3 for efficient list loading
2. Add WorkManager for background operations
3. Support offline mode with NetworkBoundResource
4. Integrate Navigation Compose
5. Comprehensive test coverage

### Performance Optimizations
- Implement memory caching
- Optimize image loading
- Add prefetching for movie lists

### Testing Strategy
- Unit tests for ViewModels and Use Cases
- Integration tests for Repository layer
- UI tests using Compose testing framework