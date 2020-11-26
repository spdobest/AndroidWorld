package spm.androidworld.all.mviArchitecture.intents

import spm.androidworld.all.mviArchitecture.dto.User

sealed class MainState {

    object Idle : MainState()
    object Loading : MainState()
    data class Users(val user: List<User>) : MainState()
    data class Error(val error: String?) : MainState()

}