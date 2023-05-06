package com.erkindilekci.gamebook.presentation.mainlistscreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erkindilekci.gamebook.data.remote.dto.toGame
import com.erkindilekci.gamebook.domain.model.Game
import com.erkindilekci.gamebook.domain.repository.GameRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainListViewModel @Inject constructor(
    private val repository: GameRepository
) : ViewModel() {
    private val _mainListState = MutableStateFlow(MainListState())
    val mainListState: StateFlow<MainListState> = _mainListState.asStateFlow()

    var searchTf by mutableStateOf("")

    val gameList: MutableList<Game> = mutableListOf()
    val pcList: MutableList<Game> = mutableListOf()
    val webList: MutableList<Game> = mutableListOf()

    var shooterGames: MutableList<Game> = mutableListOf()
    var mmoarpgGames: MutableList<Game> = mutableListOf()
    var arpgGames: MutableList<Game> = mutableListOf()
    var strategyGames: MutableList<Game> = mutableListOf()
    var mmorpgGames: MutableList<Game> = mutableListOf()
    var fightingGames: MutableList<Game> = mutableListOf()
    var actionArpgGames: MutableList<Game> = mutableListOf()
    var battleRoyaleGames: MutableList<Game> = mutableListOf()
    var mobaGames: MutableList<Game> = mutableListOf()
    var racingGames: MutableList<Game> = mutableListOf()
    var cardGameGames: MutableList<Game> = mutableListOf()
    var sportsGames: MutableList<Game> = mutableListOf()
    var mmoGames: MutableList<Game> = mutableListOf()
    var socialGames: MutableList<Game> = mutableListOf()
    var fantasyGames: MutableList<Game> = mutableListOf()

    var pcshooterGames: MutableList<Game> = mutableListOf()
    var pcmmoarpgGames: MutableList<Game> = mutableListOf()
    var pcarpgGames: MutableList<Game> = mutableListOf()
    var pcstrategyGames: MutableList<Game> = mutableListOf()
    var pcmmorpgGames: MutableList<Game> = mutableListOf()
    var pcfightingGames: MutableList<Game> = mutableListOf()
    var pcactionArpgGames: MutableList<Game> = mutableListOf()
    var pcbattleRoyaleGames: MutableList<Game> = mutableListOf()
    var pcmobaGames: MutableList<Game> = mutableListOf()
    var pcracingGames: MutableList<Game> = mutableListOf()
    var pccardGameGames: MutableList<Game> = mutableListOf()
    var pcsportsGames: MutableList<Game> = mutableListOf()
    var pcmmoGames: MutableList<Game> = mutableListOf()
    var pcsocialGames: MutableList<Game> = mutableListOf()
    var pcfantasyGames: MutableList<Game> = mutableListOf()

    var webshooterGames: MutableList<Game> = mutableListOf()
    var webmmoarpgGames: MutableList<Game> = mutableListOf()
    var webarpgGames: MutableList<Game> = mutableListOf()
    var webstrategyGames: MutableList<Game> = mutableListOf()
    var webmmorpgGames: MutableList<Game> = mutableListOf()
    var webfightingGames: MutableList<Game> = mutableListOf()
    var webactionArpgGames: MutableList<Game> = mutableListOf()
    var webbattleRoyaleGames: MutableList<Game> = mutableListOf()
    var webmobaGames: MutableList<Game> = mutableListOf()
    var webracingGames: MutableList<Game> = mutableListOf()
    var webcardGameGames: MutableList<Game> = mutableListOf()
    var websportsGames: MutableList<Game> = mutableListOf()
    var webmmoGames: MutableList<Game> = mutableListOf()
    var websocialGames: MutableList<Game> = mutableListOf()
    var webfantasyGames: MutableList<Game> = mutableListOf()


    init {
        getGames()
    }

    private fun getGames() {
        viewModelScope.launch(Dispatchers.Default) {
            val gameDtoList = repository.getAllGames()

            gameDtoList.forEach {
                val game = it.toGame()
                gameList.add(game)
            }

            gameList.forEach {
                if (it.platform == "PC (Windows)") {
                    pcList.add(it)
                    when (it.genre.trim().lowercase()) {
                        "shooter" -> pcshooterGames.add(it)
                        "mmoarpg" -> pcmmoarpgGames.add(it)
                        "arpg" -> pcarpgGames.add(it)
                        "strategy" -> pcstrategyGames.add(it)
                        "mmorpg" -> pcmmorpgGames.add(it)
                        "fighting" -> pcfightingGames.add(it)
                        "action arpg" -> pcactionArpgGames.add(it)
                        "battle royale" -> pcbattleRoyaleGames.add(it)
                        "moba" -> pcmobaGames.add(it)
                        "racing" -> pcracingGames.add(it)
                        "card game" -> pccardGameGames.add(it)
                        "sports" -> pcsportsGames.add(it)
                        "mmo" -> pcmmoGames.add(it)
                        "social" -> pcsocialGames.add(it)
                        "fantasy" -> pcfantasyGames.add(it)
                    }
                }
                if (it.platform == "Web Browser") {
                    webList.add(it)
                    when (it.genre.trim().lowercase()) {
                        "shooter" -> webshooterGames.add(it)
                        "mmoarpg" -> webmmoarpgGames.add(it)
                        "arpg" -> webarpgGames.add(it)
                        "strategy" -> webstrategyGames.add(it)
                        "mmorpg" -> webmmorpgGames.add(it)
                        "fighting" -> webfightingGames.add(it)
                        "action arpg" -> webactionArpgGames.add(it)
                        "battle royale" -> webbattleRoyaleGames.add(it)
                        "moba" -> webmobaGames.add(it)
                        "racing" -> webracingGames.add(it)
                        "card game" -> webcardGameGames.add(it)
                        "sports" -> websportsGames.add(it)
                        "mmo" -> webmmoGames.add(it)
                        "social" -> websocialGames.add(it)
                        "fantasy" -> webfantasyGames.add(it)
                    }
                }

                when (it.genre.trim().lowercase()) {
                    "shooter" -> shooterGames.add(it)
                    "mmoarpg" -> mmoarpgGames.add(it)
                    "arpg" -> arpgGames.add(it)
                    "strategy" -> strategyGames.add(it)
                    "mmorpg" -> mmorpgGames.add(it)
                    "fighting" -> fightingGames.add(it)
                    "action arpg" -> actionArpgGames.add(it)
                    "battle royale" -> battleRoyaleGames.add(it)
                    "moba" -> mobaGames.add(it)
                    "racing" -> racingGames.add(it)
                    "card game" -> cardGameGames.add(it)
                    "sports" -> sportsGames.add(it)
                    "mmo" -> mmoGames.add(it)
                    "social" -> socialGames.add(it)
                    "fantasy" -> fantasyGames.add(it)
                }
            }

            _mainListState.update {
                it.copy(
                    gameList = gameList,
                    pcGames = pcList,
                    webGames = webList,
                    shooterGames,
                    mmoarpgGames,
                    arpgGames,
                    strategyGames,
                    mmorpgGames,
                    fightingGames,
                    actionArpgGames,
                    battleRoyaleGames,
                    mobaGames,
                    racingGames,
                    cardGameGames,
                    sportsGames,
                    mmoGames,
                    socialGames,
                    fantasyGames,
                    pcshooterGames,
                    pcmmoarpgGames,
                    pcarpgGames,
                    pcstrategyGames,
                    pcmmorpgGames,
                    pcfightingGames,
                    pcactionArpgGames,
                    pcbattleRoyaleGames,
                    pcmobaGames,
                    pcracingGames,
                    pccardGameGames,
                    pcsportsGames,
                    pcmmoGames,
                    pcsocialGames,
                    pcfantasyGames,
                    webshooterGames,
                    webmmoarpgGames,
                    webarpgGames,
                    webstrategyGames,
                    webmmorpgGames,
                    webfightingGames,
                    webactionArpgGames,
                    webbattleRoyaleGames,
                    webmobaGames,
                    webracingGames,
                    webcardGameGames,
                    websportsGames,
                    webmmoGames,
                    websocialGames,
                    webfantasyGames
                )
            }
        }
    }

    fun updateString(newString: String) {
        searchTf = newString
    }
}