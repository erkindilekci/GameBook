package com.erkindilekci.gamebook.data.remote.api

import com.erkindilekci.gamebook.data.remote.dto.GameDto
import retrofit2.http.GET

interface Api {
    @GET("games")
    suspend fun getAllGames(): List<GameDto>
}
