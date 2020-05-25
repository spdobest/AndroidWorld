package spm.androidworld.all.network

import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import spm.androidworld.all.network.model.WeatherResponse

/**
 * The interface which provides methods to get result of webservices
 */
interface ApiServiceInterface {
    /**
     * Get the list of the pots from the API
     */
    @GET("/posts")
    fun getPosts(): Observable<List<WeatherResponse>>


/*    @GET("data/2.5/forecast")
    fun getWeatherDetails(
        @Query("q") action: String,
        @Query("appid") format: String
    ): Observable<WeatherModel>*/

    @GET("forecast")
    fun getWeatherDetails(@Query("q") action: String, @Query("APPID") appid: String): Call<WeatherResponse>

    companion object Factory {
        fun create(): ApiServiceInterface {

            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

            val retrofit = retrofit2.Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("BuildConfig.SERVER_URL")
                .build()

            return retrofit.create(ApiServiceInterface::class.java)
        }
    }
}