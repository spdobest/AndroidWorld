package spm.androidworld.all.xmlParsing.adapter

import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import spm.androidworld.all.R
import spm.androidworld.all.xmlParsing.model.ObjStationData


/**
 * Created by Sibaprasad Mohanty on 26/05/20.
 * Spm Limited
 * sp.dobest@gmail.com
 */

class StationViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {

    val tvStationName = itemview.findViewById<AppCompatTextView>(R.id.tvStationName)
    val tvStationDesc = itemview.findViewById<AppCompatTextView>(R.id.tvStationDesc)
    val tvStationLattitude = itemview.findViewById<AppCompatTextView>(R.id.tvStationLattitude)
    val tvStationLongitude = itemview.findViewById<AppCompatTextView>(R.id.tvStationLongitude)
    val tvStationCode = itemview.findViewById<AppCompatTextView>(R.id.tvStationCode)
    val tvStationId = itemview.findViewById<AppCompatTextView>(R.id.tvStationId)

    fun setData(stationData: ObjStationData) {
        tvStationName.text = "Station Name : ${stationData.stationCode}"
        tvStationDesc.text = "Station Desc : ${stationData.stationDesc}"
        tvStationLattitude.text = "Lattitude : ${stationData.stationLatitude}"
        tvStationLongitude.text = "Longitude : ${stationData.stationLongitude}"
        tvStationCode.text = "Station Code : ${stationData.stationCode}"
        tvStationId.text = "Station ID : ${stationData.stationId}"
    }

}