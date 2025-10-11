# 🌐 API Integration Documentation

## Table of Contents
- [Overview](#overview)
- [Retrofit Setup](#retrofit-setup)
- [API Endpoints](#api-endpoints)
- [Data Flow](#data-flow)
- [Response Handling](#response-handling)
- [Search Implementation](#search-implementation)
- [Best Practices](#best-practices)

## Overview

The Movie App integrates with **TMDb API** to provide comprehensive movie data including:
- Trending movies
- Popular movies by genre
- Movie search results
- Movie details

Following Clean Architecture principles, all API interactions are encapsulated within the **data layer**, ensuring proper separation of concerns.

## Retrofit Setup

### Network Module Configuration
```kotlin
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideMovieApi(retrofit: Retrofit): MovieApi {
        return retrofit.create(APIService::class.java)
    }
}
```

## API Endpoints

### Core Endpoints
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

## Data Flow

### Network Request Flow
```
┌──────────┐    ┌──────────┐    ┌──────────┐    ┌───────────┐
│   View   │ → │ViewModel │ → │ UseCase  │ → │Repository │
└──────────┘    └──────────┘    └──────────┘    └───────────┘
                                                      ↓
┌──────────┐    ┌──────────┐    ┌──────────┐    ┌───────────┐
│    UI    │ ← │  State   │ ← │  Domain  │ ← │ Remote API │
└──────────┘    └──────────┘    └──────────┘    └───────────┘
```

## Response Handling

### Repository Implementation
```kotlin
class MovieRepositoryImpl @Inject constructor(
    private val api: MovieApi
) : MovieRepository {

    suspend fun getTrendingMovies(): UiState<MovieResponse> {
        return try {
            val response = api.getTrendingMovies()
            UiState.Success(response.toDomain())
        } catch (e: Exception) {
            UiState.Error(e.localizedMessage ?: "Unknown error")
        }
    }
}
```

### State Management
```kotlin
sealed class UiState<out T> {
    object Loading : UiState<Nothing>()
    data class Success<T>(val data: T) : UiState<T>()
    data class Error(val message: String) : UiState<Nothing>()
}
```

## Search Implementation

### Real-time Search with Debounce
```kotlin
class SearchViewModel @Inject constructor(
    private val searchMovieUseCase: SearchMovieUseCase
) : ViewModel() {

    private val _searchState = MutableStateFlow<UiState<List<Movie>>>(UiState.Loading)
    val searchState: StateFlow<UiState<List<Movie>>> = _searchState

    fun searchMovies(query: Flow<String>) {
        viewModelScope.launch {
            query
                .debounce(400)
                .distinctUntilChanged()
                .flatMapLatest { searchTerm ->
                    searchMovieUseCase(searchTerm)
                }
                .collect { result ->
                    _searchState.value = result
                }
        }
    }
}
```

## Best Practices

### 1. Repository Pattern
- Implement single source of truth
- Abstract data sources behind interfaces
- Handle data transformations

### 2. Error Handling
- Graceful error recovery
- User-friendly error messages
- Network state monitoring

### 3. Clean Architecture
- Separation of concerns
- Domain-driven design
- Independent layers

### 4. Dependency Injection
- Use Hilt for DI
- Provide test doubles easily
- Manage singleton instances

### 5. Data Mapping
- DTO to Domain model mapping
- Clean domain models
- Immutable data classes

## Implementation Tips

1. **API Key Management**
    - Store API keys in BuildConfig
    - Use interceptors for common headers
    - Implement API key rotation if needed

2. **Caching Strategy**
    - Implement OkHttp caching
    - Use Room for offline support
    - Consider memory caching for frequently accessed data

3. **Error Handling Strategy**
   ```kotlin
   sealed class NetworkError {
       object NoNetwork : NetworkError()
       object ServerError : NetworkError()
       data class Unknown(val message: String) : NetworkError()
   }
   ```

4. **Testing Considerations**
    - Mock API responses
    - Test error scenarios
    - Verify data transformations

## Future Improvements

1. Implement retry mechanism for failed requests
2. Add offline support with Room
3. Enhanced security measures
4. API usage analytics
5. Response caching optimization