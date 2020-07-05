package spm.androidworld.all.autoAddressSearch.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import spm.androidworld.all.autoAddressSearch.model.*


abstract class BaseAddressSearchViewModel : ViewModel() {

    var apiCount: Int = 0

    open fun getAutoPopulateAddressSuggestions(tag: String) = liveData(Dispatchers.IO) {

        apiCount = 0
        emit(Resource.loading(data = null))
        try {
            val list = ArrayList<String>()
            for (i in 1..5) {
                list.add("$tag Item $i")
            }
            val data = AutoPopulateAddressData(list, "Success")

            var res: AutoPopulateAddressResponse? = null


            res = AutoPopulateAddressResponse(data, "Success", "success", "success")


            emit(Resource.success(res))
            // emit(Resource.success(data = repository.autoCompleteAddress(tag)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    open fun findAddressData(tag: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))

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

            var res: FindAddressResponse? = null

            /*Handler().postDelayed(kotlinx.coroutines.Runnable {

            }, 1000)*/
            res = FindAddressResponse(data, "Success", "success", "success")

            emit(Resource.success(res))

//            emit(Resource.success(data = repository.findAddress(tag)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}