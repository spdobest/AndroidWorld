package spm.androidworld.all.mviArchitecture.network

import spm.androidworld.all.mviArchitecture.dto.User

interface ApiHelper {

    suspend fun getUsers(): List<User>

}