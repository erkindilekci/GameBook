package com.erkindilekci.gamebook.domain.model

data class Game(
    val developer: String,
    val genre: String,
    val id: Int,
    val platform: String,
    val publisher: String,
    val release_date: String,
    val shortDescription: String,
    val thumbnail: String,
    val title: String
)

fun Game.toBasicGame(): BasicGame {
    return BasicGame(id, title, genre)
}
