package com.shohan.moviediscovery.feature.movie_discovery.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class MovieDiscoveryViewModel @Inject constructor() : ViewModel() {
    private val _demoString = MutableStateFlow<String> ("")
    var demoString: StateFlow<String> = _demoString.asStateFlow()

    init {
        _demoString.value = "Hello World"
    }

}