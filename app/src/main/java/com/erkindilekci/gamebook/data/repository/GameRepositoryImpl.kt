package com.erkindilekci.gamebook.data.repository

import com.erkindilekci.gamebook.data.remote.api.Api
import com.erkindilekci.gamebook.data.remote.dto.GameDto
import com.erkindilekci.gamebook.domain.repository.GameRepository
import javax.inject.Inject

class GameRepositoryImpl @Inject constructor(
    private val api: Api
): GameRepository {
    override suspend fun getAllGames(): List<GameDto> {
        return api.getAllGames()
    }
}