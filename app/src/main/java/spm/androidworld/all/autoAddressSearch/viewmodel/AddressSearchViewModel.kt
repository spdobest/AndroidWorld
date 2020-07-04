package spm.androidworld.all.autoAddressSearch.viewmodel

import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import spm.androidworld.all.autoAddressSearch.model.*
import spm.androidworld.all.autoAddressSearch.model.Resource.Companion.loading

class AddressSearchViewModel(/*val repository: SearchRepository*/) : BaseAddressSearchViewModel() {

    var apiCount: Int = 0

    fun autoPopulateAddress1(tag: String) = liveData(Dispatchers.IO) {
        emit(loading(data = null))
        try {
            val list = ArrayList<String>()
            for (i in 1..5) {
                list.add("$tag Item $i")
            }
            val data = AutoPopulateAddressData(list, "Success")

            val res = AutoPopulateAddressResponse(data, "Success", "success", "success")
            emit(Resource.success(res))
            // emit(Resource.success(data = repository.autoCompleteAddress(tag)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun findAddressNew(tag: String) = liveData(Dispatchers.IO) {
        emit(loading(data = null))

        apiCount++

        try {
            val list = ArrayList<AddressOption>()

            if (apiCount >= 3) {
                list.add(AddressOption("$tag Item 0", 123, tag))
            } else {
                for (i in 1..5) {
                    list.add(AddressOption("$tag Item $i", 123, tag))
                }
            }

            val data = FindAddressData((apiCount >= 3), list, tag, "")

            val res = FindAddressResponse(data, "Success", "success", "success")
            emit(Resource.success(res))

//            emit(Resource.success(data = repository.findAddress(tag)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

}