package spm.androidworld.all.edittextWIdgets

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import spm.androidworld.all.R

class AddressSearchAdapter(val listItems: List<String>) :
    RecyclerView.Adapter<AddressViewHolder>() {

    lateinit var layoutInflater: LayoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.itemview_search, parent, false)
        return AddressViewHolder(view)
    }

    override fun getItemCount(): Int = if (listItems != null) listItems.size else 0

    override fun onBindViewHolder(holder: AddressViewHolder, position: Int) {
        holder.tvSearchItem.text = listItems[position]
    }

    public fun setDevider(recyclerView: RecyclerView) {
        val mDividerItemDecoration = DividerItemDecoration(
            recyclerView.context, LinearLayoutManager.VERTICAL
        )
        recyclerView.addItemDecoration(mDividerItemDecoration)
    }
}


class AddressViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val tvSearchItem = view.findViewById<AppCompatTextView>(R.id.tvSearchItem)
}