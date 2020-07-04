package spm.androidworld.all.autoAddressSearchWithWidgets.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import spm.androidworld.all.R
import spm.androidworld.all.autoAddressSearchWithWidgets.koinmodule.AddressSearchModule
import spm.androidworld.all.autoAddressSearchWithWidgets.viewmodel.AddressSearchViewModel

class AddressSearchFragmentWithWidget : Fragment() {

    private val homeViewModel: AddressSearchViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadKoinModules(AddressSearchModule)
        arguments?.let {

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unloadKoinModules(AddressSearchModule)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_address_search, container, false)
    }

}