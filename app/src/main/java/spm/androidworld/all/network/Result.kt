package spm.androidworld.all.network

import retrofit2.Call
import retrofit2.Response

sealed class Result<out T> {
    data class Success<T>(val call: Call<T>, val response: Response<T>) : Result<T>()
    data class Failure<T>(val call: Call<T>, val error: Throwable) : Result<T>()
}