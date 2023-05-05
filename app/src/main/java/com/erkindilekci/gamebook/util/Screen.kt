package com.erkindilekci.gamebook.util

sealed class Screen(val title: String) {
    object AllGames : Screen("All Games")
    object PcGames : Screen("PC Games")
    object WebGames : Screen("Web Games")
}

val screens = listOf(
    Screen.AllGames,
    Screen.PcGames,
    Screen.WebGames
)