# 🎬 Movie Discovery App

**Movie Discovery** is a modern Android application built using **Jetpack Compose**, **Kotlin**, and **Clean Architecture** principles.  
It connects to **TMDb (The Movie Database)** API to explore trending movies, discover movies by genres, search movies in real-time, and manage favorite movies offline using Room.

---

## Overview

Movie Discovery brings together everything a movie lover needs in one place.  
It’s designed to provide a smooth, modern, and dynamic experience — all while following the **best Android development practices** and **scalable architecture**.

---

## App Features in Detail

### Movie Discovery Screen
- Displays **Trending Movies** fetched from `/trending/movie/day?api_key={API_KEY}` endpoint.
- Lists **Popular Movies by Genre** using `/movie/popular?api_key={API_KEY}`.
- Organized horizontally using `LazyRow` for a cinematic carousel feel.
- Uses **Coil** for high-performance asynchronous image loading with caching.
- Smooth transitions and animations using **Jetpack Compose**.

### Movie Search Screen
- Provides **real-time search** using `/search/movie?api_key={API_KEY}&query={QUERY}` endpoint.
- Debounced input using **Kotlin Flows** to avoid redundant API calls.
- Displays search suggestions dynamically as the user types.
- Suggestions are based on recent searches and partially matching movie names.
- Fully reactive UI — typing instantly updates the search results.

### Favorite Movies
- Save or remove movies from your favorite list with a single tap
- Uses **Room Database** for persistent local storage.
- View all saved favorites even offline.
- The favorite list persists even after app restarts.
- Integrated cleanly within the Movie Details screen.

### Movie Details Screen
- Displays movie **poster**, **backdrop**, **title**, **overview**, and **tagline**.
- Displays **Movie Details** fetched from `/movie/{movie_id}?api_key={API_KEY}` endpoint.
- Shows metadata like:
    - Genres
    - Release date
    - Runtime
    - Rating (vote average)
    - Production companies
    - Spoken languages
    - Homepage link
- Add/remove from favorites from this screen.
- Professional design optimized for Android mobile UI.

---

## Architecture Overview

This app is built using **Clean Architecture** layered as follows:

