package spm.androidworld.all.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Response
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import spm.androidworld.all.autoAddressSearch.model.AutoPopulateAddressResponse
import spm.androidworld.all.autoAddressSearch.model.FindAddressResponse

/**
 * The interface which provides methods to get result of webservices
 */

interface ApiServiceInterface {

    /**
     * Get the list of the pots from the API
     */
    @GET("/autoPopulateAddress")
    suspend fun autoCoompleteAddress(@Query("searchtag") searchtag: String): Response<AutoPopulateAddressResponse>

    @GET("/findAddress")
    suspend fun findAddress(@Query("findAddress") address: String): Response<FindAddressResponse>

    @GET("forecast")
    fun getWeatherDetails(
        @Query("q") action: String,
        @Query("APPID") appid: String
    ): Call<DummyResponse>

    companion object Factory {
        fun create(baseUrl: String): ApiServiceInterface {

            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

            val retrofit = retrofit2.Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .build()

            return retrofit.create(ApiServiceInterface::class.java)
        }
    }
}