package spm.androidworld.all.autoAddressSearch

import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_address_with_searchwidget.*
import org.koin.core.context.unloadKoinModules
import spm.androidworld.all.R
import spm.androidworld.all.autoAddressSearch.model.Status
import spm.androidworld.all.autoAddressSearch.module.addressSearchModule
import spm.androidworld.all.autoAddressSearch.viewmodel.BaseAddressSearchViewModel

class AddressSearchFragment : Fragment(), AddressSelectListener {

    private lateinit var addressSearchViewModel: BaseAddressSearchViewModel

    private lateinit var onFinalAddressSelectListener: OnFinalAddressSelectListener
    private var isTypedText: Boolean = true
    private val DELAY: Long = 1000
    private lateinit var addressAdaoter: AddressAdapter
    private var listItems = ArrayList<String>()

    override fun onDestroy() {
        super.onDestroy()
        unloadKoinModules(addressSearchModule)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        loadKoinModules(addressSearchModule)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_address_with_searchwidget, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpAddressListAdapter()
        initializeSearchView()
    }

    fun setAddressSearchViewModel(addressSearchViewmodel: BaseAddressSearchViewModel) {
        this.addressSearchViewModel = addressSearchViewmodel
    }

    fun setOnFinalAddressSelectListener(onFinalAddressSelectListener: OnFinalAddressSelectListener) {
        this.onFinalAddressSelectListener = onFinalAddressSelectListener
    }

    fun autoPopulateAddress(tag: String) {
        addressSearchViewModel.getAutoPopulateAddressSuggestions(tag).observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        resource.data?.let {
                            setReadOnly(false)
                            progressAddrs1.visibility = View.GONE
                            listItems.clear()
                            listItems.addAll(it.data.addresses)
                            addressAdaoter.notifyDataSetChanged()

                        }
                    }
                    Status.ERROR -> {

                    }
                    Status.LOADING -> {

                    }
                }
            }
        })
    }

    fun findAddress(tag: String) {
        addressSearchViewModel.findAddressData(tag).observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        resource.data?.let {

                            progressAddrs1.visibility = View.GONE

                            if (it.data.addressFound) {
                                onFinalAddressSelectListener.onFinalAddressSelect("Address Found ${it.data.addressOptions[0]}")
                            } else {
                                listItems.clear()
                                for (item in it.data.addressOptions) {
                                    listItems.add(item.displayName)
                                }
                                addressAdaoter.notifyDataSetChanged()
                            }
                            setReadOnly(false)
                        }
                    }
                    Status.ERROR -> {

                    }
                    Status.LOADING -> {

                    }
                }
            }
        })
    }

    private fun setUpAddressListAdapter() {
        addressAdaoter =
            AddressAdapter(listItems, this)
        recyclerViewAddress.layoutManager =
            LinearLayoutManager(activity as FragmentActivity, LinearLayoutManager.VERTICAL, false)
        recyclerViewAddress.adapter = addressAdaoter
        setDevider(recyclerViewAddress)
    }

    private fun initializeSearchView() {
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
                            // API call
                            val searchTag = editable.toString()
                            if (searchTag.length > 2) {
                                progressAddrs1.visibility = View.VISIBLE
                                setReadOnly(true)
                                // showing progress for 1 second - in place of network call
                                Handler().postDelayed(Runnable {
                                    autoPopulateAddress(searchTag)
                                }, 2000)
                            }

                        }
                    }, DELAY)
                }
            }
        })
    }

    private fun setReadOnly(value: Boolean, inputType: Int = InputType.TYPE_NULL) {
        edittextSearch.isFocusable = !value
        edittextSearch.isFocusableInTouchMode = !value
        edittextSearch.requestFocus()
//        edittextSearch.inputType = inputType
        edittextSearch.text?.length?.let { edittextSearch.setSelection(it) }
    }

    override fun onAddressSelect(item: String) {
        progressAddrs1.visibility = View.VISIBLE
        setReadOnly(false)
        findAddress(item)
    }

    interface OnFinalAddressSelectListener {
        fun onFinalAddressSelect(item: String)
    }
}

private class AddressAdapter(
    val listItems: List<String>,
    val addressSelectListener: AddressSelectListener
) :
    RecyclerView.Adapter<AddressAdapterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressAdapterViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.itemview_search, parent, false)
        return AddressAdapterViewHolder(view)
    }

    override fun getItemCount(): Int = listItems.size

    override fun onBindViewHolder(holder: AddressAdapterViewHolder, position: Int) {
        holder.tvSearchItem.text = listItems[position]
        holder.itemView.setOnClickListener {
            addressSelectListener.onAddressSelect(listItems[position])
        }
    }
}

private fun setDevider(recyclerView: RecyclerView) {
    val mDividerItemDecoration = DividerItemDecoration(
        recyclerView.context, LinearLayoutManager.VERTICAL
    )
    recyclerView.addItemDecoration(mDividerItemDecoration)
}

private class AddressAdapterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val tvSearchItem = view.findViewById<AppCompatTextView>(R.id.tvSearchItem)
}

private interface AddressSelectListener {
    fun onAddressSelect(item: String)
}

