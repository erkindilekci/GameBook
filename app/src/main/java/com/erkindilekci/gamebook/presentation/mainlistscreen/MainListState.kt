package com.erkindilekci.gamebook.presentation.mainlistscreen

import com.erkindilekci.gamebook.domain.model.Game

data class MainListState(
    val gameList: List<Game> = emptyList(),
    val pcGames: List<Game> = emptyList(),
    val webGames: List<Game> = emptyList()
)
