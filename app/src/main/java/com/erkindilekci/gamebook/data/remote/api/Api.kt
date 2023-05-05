package com.erkindilekci.gamebook.data.remote.api

import com.erkindilekci.gamebook.data.remote.dto.GameDto
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {
    @GET("games")
    suspend fun getAllGames(): List<GameDto>
}

