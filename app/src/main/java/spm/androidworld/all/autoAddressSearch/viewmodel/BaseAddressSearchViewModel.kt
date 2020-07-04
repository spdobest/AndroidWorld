package spm.androidworld.all.autoAddressSearch.viewmodel

import android.os.Handler
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


abstract class BaseAddressSearchViewModel : ViewModel() {

    var count = 0

    private var observablePopulateAddress = MutableLiveData<List<String>>()

    fun getAutopopulateAddressResult() = observablePopulateAddress
    fun getFindAddressResult() = observableFindAddress

    fun publicAutopopulateAddressResult(data: List<String>) {
        observablePopulateAddress.value = data
    }

    private var observableFindAddress = MutableLiveData<List<String>>()

    fun publicFindAddressResult(list: List<String>) {
        observableFindAddress.value = list
    }

    open fun autoPopulateAddress(searchTag: String) {

        var resultList = ArrayList<String>()

        for (i in 1..5) {
            resultList.add("Count$count $searchTag $i")
        }
        Handler().postDelayed({
            publicAutopopulateAddressResult(resultList)
        }, 3000)
    }

    open fun findAddress(searchTag: String) {
        var resultList = ArrayList<String>()
        count++
        var range = if (count <= 3) 5 else 1
        for (i in 1..range) {
            resultList.add("Count$count $searchTag $i")
        }
        Handler().postDelayed({
            publicFindAddressResult(resultList)
        }, 3000)
    }
}