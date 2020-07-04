package spm.androidworld.all.autoAddressSearch.repository

import spm.androidworld.all.network.ApiServiceInterface

class SearchRepository(val apiserviceIntervace: ApiServiceInterface) {
    suspend fun autoCompleteAddress(tag: String) = apiserviceIntervace.autoCoompleteAddress(tag)
    suspend fun findAddress(tag: String) = apiserviceIntervace.autoCoompleteAddress(tag)

}