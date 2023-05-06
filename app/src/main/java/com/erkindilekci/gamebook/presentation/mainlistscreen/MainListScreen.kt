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
import androidx.compose.runtime.saveable.rememberSaveable
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
    var selectedFilter by rememberSaveable { mutableStateOf("All") }

    when (selectedScreen) {
        is Screen.PcGames -> {
            gameList = mainListState.value.pcGames
            when (selectedFilter.trim().lowercase()) {
                "all" -> gameList = mainListState.value.pcGames
                "shooter" -> gameList = mainListState.value.pcshooterGames
                "mmoarpg" -> gameList = mainListState.value.pcmmoarpgGames
                "arpg" -> gameList = mainListState.value.pcarpgGames
                "strategy" -> gameList = mainListState.value.pcstrategyGames
                "mmorpg" -> gameList = mainListState.value.pcmmorpgGames
                "fighting" -> gameList = mainListState.value.pcfightingGames
                "action arpg" -> gameList = mainListState.value.pcactionArpgGames
                "battle royale" -> gameList = mainListState.value.pcbattleRoyaleGames
                "moba" -> gameList = mainListState.value.pcmobaGames
                "racing" -> gameList = mainListState.value.pcracingGames
                "card game" -> gameList = mainListState.value.pccardGameGames
                "sports" -> gameList = mainListState.value.pcsportsGames
                "mmo" -> gameList = mainListState.value.pcmmoGames
                "social" -> gameList = mainListState.value.pcsocialGames
                "fantasy" -> gameList = mainListState.value.pcfantasyGames
            }
        }
        is Screen.WebGames -> {
            gameList = mainListState.value.webGames
            when (selectedFilter.trim().lowercase()) {
                "all" -> gameList = mainListState.value.webGames
                "shooter" -> gameList = mainListState.value.webshooterGames
                "mmoarpg" -> gameList = mainListState.value.webmmoarpgGames
                "arpg" -> gameList = mainListState.value.webarpgGames
                "strategy" -> gameList = mainListState.value.webstrategyGames
                "mmorpg" -> gameList = mainListState.value.webmmorpgGames
                "fighting" -> gameList = mainListState.value.webfightingGames
                "action arpg" -> gameList = mainListState.value.webactionArpgGames
                "battle royale" -> gameList = mainListState.value.webbattleRoyaleGames
                "moba" -> gameList = mainListState.value.webmobaGames
                "racing" -> gameList = mainListState.value.webracingGames
                "card game" -> gameList = mainListState.value.webcardGameGames
                "sports" -> gameList = mainListState.value.websportsGames
                "mmo" -> gameList = mainListState.value.webmmoGames
                "social" -> gameList = mainListState.value.websocialGames
                "fantasy" -> gameList = mainListState.value.webfantasyGames
            }
        }
        else -> {
            gameList = mainListState.value.gameList
            when (selectedFilter.trim().lowercase()) {
                "all" -> gameList = mainListState.value.gameList
                "shooter" -> gameList = mainListState.value.shooterGames
                "mmoarpg" -> gameList = mainListState.value.mmoarpgGames
                "arpg" -> gameList = mainListState.value.arpgGames
                "strategy" -> gameList = mainListState.value.strategyGames
                "mmorpg" -> gameList = mainListState.value.mmorpgGames
                "fighting" -> gameList = mainListState.value.fightingGames
                "action arpg" -> gameList = mainListState.value.actionArpgGames
                "battle royale" -> gameList = mainListState.value.battleRoyaleGames
                "moba" -> gameList = mainListState.value.mobaGames
                "racing" -> gameList = mainListState.value.racingGames
                "card game" -> gameList = mainListState.value.cardGameGames
                "sports" -> gameList = mainListState.value.sportsGames
                "mmo" -> gameList = mainListState.value.mmoGames
                "social" -> gameList = mainListState.value.socialGames
                "fantasy" -> gameList = mainListState.value.fantasyGames
            }
        }
    }

    Scaffold(
        topBar = {
            MyAppBar(
                onStringChanged = { viewModel.updateString(it) },
                searchTf = viewModel.searchTf,
                onCloseClicked = { viewModel.updateString("") },
                onFilterClicked = { selectedFilter = it }
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