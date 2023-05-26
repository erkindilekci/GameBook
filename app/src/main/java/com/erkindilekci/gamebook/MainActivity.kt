package com.erkindilekci.gamebook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.erkindilekci.gamebook.presentation.mainlistscreen.MainListScreen
import com.erkindilekci.gamebook.presentation.theme.GameBookTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GameBookTheme {
                MainListScreen()
            }
        }
    }
}
