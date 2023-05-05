package com.erkindilekci.gamebook.presentation.mainlistscreen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.erkindilekci.gamebook.R
import com.erkindilekci.gamebook.domain.model.Game
import com.erkindilekci.gamebook.presentation.mainlistscreen.components.ListItemCard
import com.erkindilekci.gamebook.presentation.mainlistscreen.components.MyAppBar
import com.erkindilekci.gamebook.presentation.theme.AppBar
import com.erkindilekci.gamebook.presentation.theme.BackGround
import com.erkindilekci.gamebook.util.Screen
import com.erkindilekci.gamebook.util.screens

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun MainListScreen(
    viewModel: MainListViewModel = hiltViewModel()
) {
    val mainListState = viewModel.mainListState.collectAsState()

    //var gameList = mainListState.value.gameList
    var gameList: List<Game> = emptyList()

    var selectedScreen by remember { mutableStateOf(screens.first()) }

    when (selectedScreen) {
        is Screen.PcGames -> {
            gameList = mainListState.value.pcGames
        }
        is Screen.WebGames -> {
            gameList = mainListState.value.webGames
        }
        else -> {
            gameList = mainListState.value.gameList
        }
    }

    Scaffold(
        topBar = {
            MyAppBar(
                onStringChanged = { viewModel.updateString(it) },
                searchTf = viewModel.searchTf,
                onCloseClicked = { viewModel.updateString("") }
            )
        },
        backgroundColor = BackGround,
        //contentColor = Color.White
        content = {
            LazyColumn(Modifier.padding(it)) {
                val newList: List<Game>

                if (viewModel.searchTf.trim().isNotEmpty()) {
                    newList = gameList.filter {
                        it.title.lowercase().contains(viewModel.searchTf.lowercase())
                    }
                    newList?.let {
                        items(newList) {
                            ListItemCard(game = it)
                        }
                    }
                } else {
                    items(gameList) {
                        ListItemCard(game = it)
                    }
                }
            }
        },
        bottomBar = {
            BottomNavigation(backgroundColor = AppBar, contentColor = Color.White) {
                screens.forEach { screen ->
                    BottomNavigationItem(
                        icon = {
                            Icon(
                                painter = painterResource(id = R.drawable.round_gamepad_24),
                                contentDescription = "gamepad"
                            )
                        },
                        label = { Text(screen.title) },
                        selected = (selectedScreen == screen),
                        onClick = {
                            selectedScreen = screen
                        }
                    )
                }
            }
        }
    )
}