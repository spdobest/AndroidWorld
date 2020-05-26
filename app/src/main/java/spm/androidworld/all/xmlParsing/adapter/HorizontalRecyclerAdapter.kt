package spm.androidworld.all.xmlParsing.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import spm.androidworld.all.R


/**
 * Created by Sibaprasad Mohanty on 26/05/20.
 * Spm Limited
 * sp.dobest@gmail.com
 */

class HorizontalRecyclerAdapter(val list: List<String>) :
    RecyclerView.Adapter<HorizontalViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalViewHolder {
        return HorizontalViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.itemview_horizontal_scroll, parent, false)
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: HorizontalViewHolder, position: Int) {
        holder.buttonXMlAPiName.text = list[position]
    }
}

class HorizontalViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
    val buttonXMlAPiName = itemview.findViewById<AppCompatButton>(R.id.buttonXMlAPiName)
}

interface OnHorizontalItemClick {
    fun onHorizontalButtonCLick(name: String)
}