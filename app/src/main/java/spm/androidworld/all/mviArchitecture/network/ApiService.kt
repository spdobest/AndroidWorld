package spm.androidworld.all.mviArchitecture.network

import retrofit2.http.GET
import spm.androidworld.all.mviArchitecture.dto.User

interface ApiService {

   @GET("users")
   suspend fun getUsers(): List<User>
}