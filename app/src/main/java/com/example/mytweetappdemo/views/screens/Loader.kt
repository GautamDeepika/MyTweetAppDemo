package com.example.mytweetappdemo.views.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun Loader() {
    val degree = produceState(initialValue = 0) {
        while (true) {
            delay(16)
            value = (value + 30) % 360
        }
    }

    Box(contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize(1f)
            .background(Color.White),
        content = {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "Loading",
                    modifier = Modifier
                        .size(60.dp)
                        .rotate(degree.value.toFloat())
                )
                Text(text = "Loading")
            }
        })
}
