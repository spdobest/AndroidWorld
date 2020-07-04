package spm.androidworld.all.autoAddressSearchWithWidgets.ui.adapter

import android.content.Context
import android.widget.ArrayAdapter
import spm.androidworld.all.R
import spm.androidworld.all.autoAddressSearchWithWidgets.viewmodel.AddressSearchViewModel

class AddressAutocompleteAdapter<T>(
    context: Context,
    val addressSearchViewModel: AddressSearchViewModel
) : ArrayAdapter<T>(context, R.layout.itemview_addresssearch_autocomplete, R.id.tvAddressItem) {

    private var dataItems: List<T> = emptyList()


    override fun getItem(position: Int): T? {
        return dataItems.getOrNull(position)
    }

    override fun getCount(): Int {
        return dataItems.size
    }

}