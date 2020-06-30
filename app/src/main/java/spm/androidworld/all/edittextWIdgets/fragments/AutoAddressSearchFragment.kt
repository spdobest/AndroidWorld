package spm.androidworld.all.edittextWIdgets.fragments

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_autoaddresssearch.*
import spm.androidworld.all.R
import spm.androidworld.all.edittextWIdgets.AddressSearchWithTypingDelayEdittext

class AutoAddressSearchFragment : Fragment(),
    AddressSearchWithTypingDelayEdittext.OnCharTypeDelayListener, AddressSelectListener {

    var count = 0
    private lateinit var exactAddressSelected: ExactAddressSelectListener
    private var listItems = ArrayList<String>()
    private lateinit var addressSearchAdapter: AutoAddressSearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_autoaddresssearch, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    fun init() {
        edittextSearchTimeDelay.setListener(this)
        addressSearchAdapter = AutoAddressSearchAdapter(listItems, this)
        recyclerViewAddress.layoutManager =
            LinearLayoutManager(activity as FragmentActivity, LinearLayoutManager.VERTICAL, false)
        recyclerViewAddress.adapter = addressSearchAdapter
        addressSearchAdapter.setDevider(recyclerViewAddress)
    }

    override fun onTypeDelayChar(chars: String) {
        if (chars.length >= 2) {
            progressAddress.visibility = View.VISIBLE
            listItems.clear()
            for (i in 1..10) {
                listItems.add("$chars Address $i")
            }

            Handler().postDelayed({
                progressAddress.visibility = View.GONE
                addressSearchAdapter.notifyDataSetChanged()
                edittextSearchTimeDelay.enableEdittext()
            }, 3000)
        }
    }

    override fun onAddressSelect(item: String) {

        if (listItems.size == 1) {
            Toast.makeText(activity as FragmentActivity, "Address Selected", Toast.LENGTH_SHORT)
                .show()
        } else {
            progressAddress.visibility = View.VISIBLE
            count++
            listItems.clear()
            var range = if (count <= 3) 5 else 1
            for (i in 1..range) {
                listItems.add("Count$count $item $i")
            }
            Handler().postDelayed({
                progressAddress.visibility = View.GONE
                addressSearchAdapter.notifyDataSetChanged()
            }, 3000)
        }
    }

    fun setListener(exactAddressSelected: ExactAddressSelectListener) {
        this.exactAddressSelected = exactAddressSelected
    }
}

private class AutoAddressSearchAdapter(
    val listItems: List<String>,
    val addressSelectListener: AddressSelectListener
) :
    RecyclerView.Adapter<AutoAddressSearchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AutoAddressSearchViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.itemview_search, parent, false)
        return AutoAddressSearchViewHolder(view)
    }

    override fun getItemCount(): Int = if (listItems != null) listItems.size else 0

    override fun onBindViewHolder(holder: AutoAddressSearchViewHolder, position: Int) {
        holder.tvSearchItem.text = listItems[position]
        holder.itemView.setOnClickListener {
            addressSelectListener.onAddressSelect(listItems[position])
        }
    }

    public fun setDevider(recyclerView: RecyclerView) {
        val mDividerItemDecoration = DividerItemDecoration(
            recyclerView.context, LinearLayoutManager.VERTICAL
        )
        recyclerView.addItemDecoration(mDividerItemDecoration)
    }
}


private class AutoAddressSearchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val tvSearchItem = view.findViewById<AppCompatTextView>(R.id.tvSearchItem)
}

private interface AddressSelectListener {
    fun onAddressSelect(item: String)
}

public interface ExactAddressSelectListener {
    fun onExactAddressSeleted(item: String)
}