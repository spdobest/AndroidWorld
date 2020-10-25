package spm.androidworld.all.mviArchitecture.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import spm.androidworld.all.mviArchitecture.util.DataState


/**
 * Created by Sibaprasad Mohanty on 13/08/20.
 * sp.dobest@gmail.com
 */

class LoginViewModel : ViewModel() {
    private val _stateEvent: MutableLiveData<LoginStateEvent> = MutableLiveData()
    private val _viewState: MutableLiveData<LoginResponse> = MutableLiveData()


    val viewState: LiveData<LoginResponse>
        get() = _viewState


    val dataState: LiveData<DataState<LoginResponse>> = Transformations
        .switchMap(_stateEvent) { stateEvent ->
            stateEvent?.let {
                handleStateEvent(stateEvent)
            }
        }

    private fun handleStateEvent(stateEvent: LoginStateEvent): LiveData<DataState<LoginResponse>> {
        println("DEBUG: New StateEvent detected: $stateEvent")
        return when (stateEvent) {

            is LoginStateEvent.Loading -> {
                LoginRepository.loading(true)
            }

            is LoginStateEvent.LoginUser -> {
                LoginRepository.loginUser(stateEvent.emailId, stateEvent.password)
            }

            is LoginStateEvent.None -> {

            }
        }
    }
}