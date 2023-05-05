package com.erkindilekci.gamebook.domain.repository

import com.erkindilekci.gamebook.data.remote.dto.GameDto

interface GameRepository {
    suspend fun getAllGames(): List<GameDto>
}