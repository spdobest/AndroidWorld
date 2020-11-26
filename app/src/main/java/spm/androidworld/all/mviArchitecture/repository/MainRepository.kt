package spm.androidworld.all.mviArchitecture.repository

import spm.androidworld.all.mviArchitecture.network.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {

    suspend fun getUsers() = apiHelper.getUsers()

}