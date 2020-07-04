package spm.androidworld.all

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import spm.androidworld.all.autoAddressSearch.AddressSearchFragment

class SearchContainerFragment : Fragment(),
    AddressSearchFragment.OnFinalAddressSelectListener {
    private var fragentType: Int = 0

    private var FRAGMENTTYPE = "FragmentType"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            fragentType = it.getInt(FRAGMENTTYPE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_container, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: Int) =
            SearchContainerFragment().apply {
                arguments = Bundle().apply {
                    putInt(FRAGMENTTYPE, param1)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

////        val frag = AddressFragmentWithoutSearchWidget.getInstance("asdadas")
//        val frag = AddressSearchFragment<MyViewModel>()
//       // frag.setListeber(this)
//        childFragmentManager.beginTransaction()
//            .add(R.id.addressContainer, frag, "AddressFragmentWithoutSearchWidget")
//            .addToBackStack("AddressFragmentWithoutSearchWidget")
//            .commit()

        when (fragentType) {

        }

        val addressSearchFragment: AddressSearchFragment =
            childFragmentManager.findFragmentById(R.id.fragmentWithSearchWidget) as AddressSearchFragment
        addressSearchFragment.setOnFinalAddressSelectListener(this)
    }

    override fun onFinalAddressSelect(selectedAddress: String) {
        Toast.makeText(activity as FragmentActivity, selectedAddress, Toast.LENGTH_SHORT)
            .show()
    }
}