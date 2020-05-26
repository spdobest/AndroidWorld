package spm.androidworld.all.network

import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.simpleframework.xml.convert.AnnotationStrategy
import org.simpleframework.xml.core.Persister
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import spm.androidworld.all.xmlParsing.model.StationObjectResponse

/**
 * The interface which provides methods to get result of webservices
 */
interface ApiServiceInterface {


    @GET("/trending/all/day?api_key=b7eb389e9e6ad5e434077e4b6d83ad78")
    fun getTrendingMovies(): MovieResponse

    /**
     * XML parsing
     */
    @Headers(*arrayOf("Accept: application/json"))
    @GET("getAllStationsXML")
    open fun getXml(): Call<StationObjectResponse?>?


    companion object Factory {
        fun create(java: Class<ApiServiceInterface>): ApiServiceInterface {

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


        fun createXmlRetrofit(): ApiServiceInterface {
            val retrofit = Retrofit.Builder()
                .baseUrl("http://api.irishrail.ie/realtime/realtime.asmx/")
                .client(OkHttpClient()) //you can customize it
                .addConverterFactory(
                    SimpleXmlConverterFactory.createNonStrict(
                        Persister(
                            AnnotationStrategy() // important part!
                        )
                    )
                )
                .addCallAdapterFactory(
                    RxJava2CallAdapterFactory.createWithScheduler(
                        Schedulers.io()
                    )
                ) // rx stuff
                .build().create(ApiServiceInterface::class.java)

            return retrofit
        }
    }
}