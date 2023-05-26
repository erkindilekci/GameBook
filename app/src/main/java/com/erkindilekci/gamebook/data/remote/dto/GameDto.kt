package com.erkindilekci.gamebook.data.remote.dto

import com.erkindilekci.gamebook.domain.model.Game
import com.google.gson.annotations.SerializedName

data class GameDto(
    val developer: String,
    @SerializedName("freetogame_profile_url")
    val freeToGameProfileUrl: String,
    @SerializedName("game_url")
    val gameUrl: String,
    val genre: String,
    val id: Int,
    val platform: String,
    val publisher: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("short_description")
    val shortDescription: String,
    val thumbnail: String,
    val title: String
)

fun GameDto.toGame(): Game {
    return Game(
        developer, genre, id, platform, publisher, releaseDate, shortDescription, thumbnail, title
    )
}
