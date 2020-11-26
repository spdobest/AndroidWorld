package spm.androidworld.all.mviArchitecture.network

import spm.androidworld.all.mviArchitecture.dto.User

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {

    override suspend fun getUsers(): List<User> {
        return apiService.getUsers()
    }
}