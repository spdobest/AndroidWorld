package spm.androidworld.all.xmlParsing.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import spm.androidworld.all.R
import spm.androidworld.all.xmlParsing.model.ObjStationData


/**
 * Created by Sibaprasad Mohanty on 26/05/20.
 * Spm Limited
 * sp.dobest@gmail.com
 */

public class StationAdapter(val list: List<ObjStationData>) :
    RecyclerView.Adapter<StationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StationViewHolder {
        return StationViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.itemview_station, parent, false)
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: StationViewHolder, position: Int) {
        holder.setData(list[position])
    }

}