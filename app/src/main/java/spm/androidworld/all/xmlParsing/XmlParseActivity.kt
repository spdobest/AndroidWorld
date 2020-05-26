package spm.androidworld.all.xmlParsing

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_xml_parse.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import spm.androidworld.all.R
import spm.androidworld.all.network.ApiServiceInterface
import spm.androidworld.all.xmlParsing.adapter.StationAdapter
import spm.androidworld.all.xmlParsing.model.ObjStationData
import spm.androidworld.all.xmlParsing.model.StationObjectResponse


class XmlParseActivity : AppCompatActivity() {

    val list = ArrayList<ObjStationData>()
    lateinit var adapter: StationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_xml_parse)

        title = "XML Parsing Using Retrofit"

        adapter = StationAdapter(list)
        recyclerviewStation.layoutManager = LinearLayoutManager(this)
        recyclerviewStation.adapter = adapter

        val url = "http://api.irishrail.ie/realtime/realtime.asmx/getAllStationsXML"

        getXmlData()
    }

    fun getXmlData() {

        val call: Call<StationObjectResponse?>? = ApiServiceInterface.createXmlRetrofit().getXml()
        call?.enqueue(object : Callback<StationObjectResponse?> {
            override fun onFailure(call: Call<StationObjectResponse?>, t: Throwable) {
                Log.i("TAG", "Failed")
            }

            override fun onResponse(
                call: Call<StationObjectResponse?>,
                response: Response<StationObjectResponse?>
            ) {
                Log.i("TAG", response.message())
                response?.let {
                    it.body()?.cardsSummaryList?.let {
                        setDataToRecyclerView(it)
                    }
                }
            }
        })
    }

    fun setDataToRecyclerView(listData: List<ObjStationData>) {
        list.addAll(listData)
        adapter.notifyDataSetChanged()
    }

}
