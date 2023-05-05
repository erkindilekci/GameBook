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
                if (it.platform == "PC (Windows)"){
                    pcList.add(it)
                }
                if (it.platform == "Web Browser"){
                    webList.add(it)
                }
            }

            _mainListState.update {
                it.copy(
                    gameList = gameList,
                    pcGames = pcList,
                    webGames = webList
                )
            }
        }
    }

    fun updateString(newString: String) {
        searchTf = newString
    }
}