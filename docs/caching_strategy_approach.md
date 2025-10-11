# 🗄️ Caching Strategy Documentation

## Table of Contents
- [Overview](#overview)
- [Caching Architecture](#caching-architecture)
- [Implementation Details](#implementation-details)
- [Current State](#current-state)
- [Future Improvements](#future-improvements)

## Overview

The Movie App implements a **minimal caching strategy** that primarily focuses on:
- ⭐ Favorites storage
- 📱 Offline access for saved movies
- 🔄 Real-time API fetching for dynamic content

Other features like movie search, trending movies, and popular movies are fetched in real-time from the TMDb API.

## Caching Architecture

### Data Flow Diagram
```
┌──────────────┐     ┌──────────────┐     ┌──────────────┐
│   UI Layer   │     │Domain Layer  │     │  Data Layer  │
└──────────────┘     └──────────────┘     └──────────────┘
       ↑                    ↑                     ↑
       │                    │                     │
       │                    │                     │
┌──────────────┐     ┌──────────────┐     ┌──────────────┐
│   Compose    │     │  Use Cases   │     │ Repository   │
└──────────────┘     └──────────────┘     └──────────────┘
                                                 ↑
                                          ┌──────┴───────┐
                                          │              │
                                    ┌──────────┐   ┌──────────┐
                                    │  Room    │   │  TMDb    │
                                    │Database  │   │   API    │
                                    └──────────┘   └──────────┘
                                    (Favorites)    (Live Data)
```

## Implementation Details

### 1. Favorites Caching (Room)

#### Entity Definition
```kotlin
@Entity(tableName = "movie_table")
data class MovieEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val posterPath: String?,
    val overview: String,
    val rating: Double
)
```

#### DAO Implementation
```kotlin
@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: MovieEntity)

    @Delete
    suspend fun deleteMovie(movie: MovieEntity)

    @Query("SELECT * FROM movie_table")
    fun getAllMovies(): Flow<List<MovieEntity>>
}
```

### 2. Search Suggestions

#### In-Memory Cache
```kotlin
class SearchSuggestionsManager {
    private val suggestions = mutableListOf<String>()

    fun getSuggestions(query: String): List<String> {
        return suggestions
            .filter { it.contains(query, ignoreCase = true) }
            .take(5)
    }

    fun addSuggestion(movieTitle: String) {
        if (!suggestions.contains(movieTitle)) {
            suggestions.add(movieTitle)
        }
    }
}
```

## Current State

### Feature Caching Status

| Feature            | Cache Type      | Persistence | Notes                    |
|-------------------|----------------|-------------|--------------------------|
| Favorites         | Room Database  | ✅ Yes      | Full offline access      |
| Trending Movies   | None           | ❌ No       | Always fetched live      |
| Popular by Genre  | None           | ❌ No       | Always fetched live      |
| Movie Details     | None           | ❌ No       | Always fetched live      |
| Search Results    | None           | ❌ No       | Always fetched live      |
| Search History    | Memory         | ❌ No       | Session-only storage     |

### Implementation Benefits

1. **Simplicity**
    - Clear data flow
    - Minimal maintenance
    - Reduced complexity

2. **Reliability**
    - Fresh data for dynamic content
    - Consistent offline favorites
    - Predictable behavior

3. **Performance**
    - Low memory footprint
    - Efficient database queries
    - Quick response times

## Future Improvements

### 1. NetworkBoundResource Pattern
```kotlin
class NetworkBoundResource<ResultType, RequestType> {
    fun asFlow() = flow {
        // 1. Load from database
        emit(Resource.Loading(data = null))
        val dbValue = database.load().first()

        if (shouldFetch(dbValue)) {
            // 2. Load from network
            when (val apiResponse = api.fetch()) {
                is ApiSuccess -> {
                    // 3. Save to database
                    database.save(apiResponse.data)
                    // 4. Emit new data
                    emitAll(database.load().map { Resource.Success(it) })
                }
                is ApiError -> {
                    emit(Resource.Error(apiResponse.error))
                }
            }
        }
    }
}
```

### 2. Paging Implementation
```kotlin
@ExperimentalPagingApi
class MovieRemoteMediator(
    private val database: MovieDatabase,
    private val api: MovieApi
) : RemoteMediator<Int, MovieEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, MovieEntity>
    ): MediatorResult {
        // Implementation for paged data loading
    }
}
```