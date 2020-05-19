package spm.androidworld.all.network

import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import spm.androidworld.all.Dto.BooksResponse


interface BookApiService {
    @GET("lists/current/hardcover-fiction.json?")
    fun getListOfBooks(@Query("api-key") api_key: String): Call<BooksResponse>?


    @GET("lists/current/hardcover-fiction.json?")
    fun getListOfBooksUsingRx(@Query("api-key") api_key: String): Observable<BooksResponse>

}