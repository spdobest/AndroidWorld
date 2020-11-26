package spm.androidworld.all.mviArchitecture.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import spm.androidworld.all.mviArchitecture.network.ApiHelper
import spm.androidworld.all.mviArchitecture.repository.MainRepository

class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(MainRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}