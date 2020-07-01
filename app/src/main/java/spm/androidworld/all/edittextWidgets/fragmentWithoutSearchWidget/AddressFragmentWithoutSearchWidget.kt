package spm.androidworld.all.edittextWidgets.fragmentWithoutSearchWidget

import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
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
import kotlinx.android.synthetic.main.fragment_address_without_searchwidget.*
import spm.androidworld.all.R

class AddressFragmentWithoutSearchWidget : Fragment(), AddressSelectListener {

    var count = 0
    private var listItems = ArrayList<String>()
    private lateinit var addressSearchAdapter: AutoAddressSearchAdapter
    private var isTypedText: Boolean = true
    private val DELAY: Long = 1000
    private var finalAddressSelectListener: FinalAddressSelectListener? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun setListeber(finaladdrs: FinalAddressSelectListener) {
        this.finalAddressSelectListener = finaladdrs
    }

    companion object {
        fun getInstance(url: String): AddressFragmentWithoutSearchWidget {
            val bundle = Bundle()
            bundle.putString("url", url)
            var addressFragmentWithoutSearchWidget = AddressFragmentWithoutSearchWidget()
            addressFragmentWithoutSearchWidget.arguments = bundle
            return addressFragmentWithoutSearchWidget
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_address_without_searchwidget, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpAddressListAdapter()
        initializeSearchView()

    }

    fun setUpAddressListAdapter() {
        addressSearchAdapter =
            AutoAddressSearchAdapter(listItems, this)
        recyclerViewAddress.layoutManager =
            LinearLayoutManager(activity as FragmentActivity, LinearLayoutManager.VERTICAL, false)
        recyclerViewAddress.adapter = addressSearchAdapter
        addressSearchAdapter.setDevider(recyclerViewAddress)
    }

    fun initializeSearchView() {
        val handler = Handler()
        edittextSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                charSequesnce: CharSequence?,
                p1: Int,
                p2: Int,
                p3: Int
            ) {

            }

            override fun onTextChanged(
                charSequesnce: CharSequence?,
                start: Int,
                end: Int,
                count: Int
            ) {
            }

            override fun afterTextChanged(editable: Editable?) {
                if (edittextSearch.text?.length!! >= 2 && isTypedText) {
                    handler.removeCallbacks(null)
                    handler.postDelayed(Runnable {
                        editable?.let {
                            callAddressSearchApi(editable.toString())
                            setReadOnly(true)
                        }
                    }, DELAY)
                }
            }
        })
    }

    fun callAddressSearchApi(searchedKey: String) {
        if (searchedKey.length >= 2) {
            progressAddrs1.visibility = View.VISIBLE
            listItems.clear()
            for (i in 1..10) {
                listItems.add("$searchedKey Address $i")
            }

            Handler().postDelayed({
                progressAddrs1.visibility = View.GONE
                addressSearchAdapter.notifyDataSetChanged()
                setReadOnly(false)
            }, 3000)
        }
    }

    fun setReadOnly(value: Boolean, inputType: Int = InputType.TYPE_NULL) {
        edittextSearch.isFocusable = !value
        edittextSearch.isFocusableInTouchMode = !value
        edittextSearch.requestFocus()
        edittextSearch.inputType = inputType
        edittextSearch.text?.length?.let { edittextSearch.setSelection(it) }
    }

    override fun onAddressSelect(item: String) {

        if (listItems.size == 1) {
            finalAddressSelectListener?.let {
                it.onFinalAddressSelect(item)
            }
            Toast.makeText(activity as FragmentActivity, "Address Selected", Toast.LENGTH_SHORT)
                .show()
        } else {
            progressAddrs1.visibility = View.VISIBLE
            count++
            listItems.clear()
            var range = if (count <= 3) 5 else 1
            for (i in 1..range) {
                listItems.add("Count$count $item $i")
            }
            Handler().postDelayed({
                progressAddrs1.visibility = View.GONE
                addressSearchAdapter.notifyDataSetChanged()
            }, 3000)
        }
    }

    public interface FinalAddressSelectListener {
        fun onFinalAddressSelect(item: String)
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



