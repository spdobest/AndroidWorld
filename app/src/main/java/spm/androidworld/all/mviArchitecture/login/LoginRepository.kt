package spm.androidworld.all.mviArchitecture.login

import androidx.lifecycle.MutableLiveData
import spm.androidworld.all.mviArchitecture.util.DataState

object LoginRepository {


    fun loading(isLoading: Boolean): MutableLiveData<DataState<Boolean>> {
        val mutableLiveData = MutableLiveData<DataState<Boolean>>()
        mutableLiveData.value = DataState.loading<Boolean>(
            isLoading
        )
        return mutableLiveData
    }

    fun onError(error: String): MutableLiveData<DataState<String>> {
        val mutableLiveData = MutableLiveData<DataState<String>>()
        mutableLiveData.value = DataState.error(error)
        return mutableLiveData
    }

    fun loginUser(emailId: String, password: String): MutableLiveData<DataState<LoginResponse>> {

        val loginData = LoginData("12345", "Name")
        val loginResponse = LoginResponse(loginData = loginData)

        val dataState = DataState.data(
            null,
            LoginResponse(
                loginData = loginData
            )
        )
        val mutableLiveData = MutableLiveData<DataState<LoginResponse>>()
        mutableLiveData.value = dataState

        return mutableLiveData
    }
}








