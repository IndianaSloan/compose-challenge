package com.fueled.composechallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.fueled.composechallenge.ui.screens.video.VideoScreen
import com.fueled.composechallenge.ui.screens.video.VideoViewModel
import com.fueled.composechallenge.ui.theme.ComposeChallengeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeChallengeTheme {
                val viewModel: VideoViewModel by viewModels()
                VideoScreen(viewModel = viewModel)
            }
        }
    }
}
