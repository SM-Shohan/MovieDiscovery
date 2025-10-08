package com.shohan.moviediscovery.uiUtility.utilities

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import com.shohan.moviediscovery.R

@Composable
fun AnimatedLoading(isLoading: Boolean, modifier: Modifier = Modifier.Companion) {
    if (isLoading) {
        val frameList = listOf(
            R.drawable.loading_one,
            R.drawable.loading_two,
            R.drawable.loading_three,
            R.drawable.loading_four,
            R.drawable.loading_five,
            R.drawable.loading_six,
            R.drawable.loading_seven,
            R.drawable.loading_eight,
            R.drawable.loading_nine,
            R.drawable.loading_ten,
            R.drawable.loading_eleven,
            R.drawable.loading_twelve
        )

        // Animated image: Change images over time
        val currentFrame = remember { mutableIntStateOf(0) }
        val frameCount = frameList.size

        LaunchedEffect(Unit) {
            while (true) {
                delay(40) // Delay between frames
                currentFrame.intValue = (currentFrame.intValue + 1) % frameCount
            }
        }

        Image(
            modifier = modifier.size(80.dp),
            painter = painterResource(id = frameList[currentFrame.intValue]),
            contentDescription = "Loading Animation",
        )
    }
}