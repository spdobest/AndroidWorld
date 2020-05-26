package spm.androidworld.all.xmlParsing

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import spm.androidworld.all.R
import spm.androidworld.all.network.ApiServiceInterface


class XmlParseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_xml_parse)


        val url = "http://api.irishrail.ie/realtime/realtime.asmx/getAllStationsXML"

        getXmlData()
    }

    fun getXmlData() {
        val retrofit: Retrofit =
            Retrofit.Builder().baseUrl("http://api.irishrail.ie/realtime/realtime.asmx/")
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build()

        val rssapi: ApiServiceInterface = retrofit.create(ApiServiceInterface::class.java)

        val call: Call<StationObjectResponse?>? = rssapi.getXml()
        call?.enqueue(object : Callback<StationObjectResponse?> {
            override fun onFailure(call: Call<StationObjectResponse?>, t: Throwable) {
                Log.i("TAG", "Failed")
            }

            override fun onResponse(
                call: Call<StationObjectResponse?>,
                response: Response<StationObjectResponse?>
            ) {
                Log.i("TAG", response.message())
            }
        })
    }
}
