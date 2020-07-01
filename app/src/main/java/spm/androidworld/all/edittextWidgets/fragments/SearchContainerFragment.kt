package spm.androidworld.all.edittextWidgets.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import spm.androidworld.all.R
import spm.androidworld.all.edittextWidgets.fragmentWithoutSearchWidget.AddressFragmentWithoutSearchWidget

class SearchContainerFragment : Fragment(),
    AddressFragmentWithoutSearchWidget.FinalAddressSelectListener {
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

        val frag = AddressFragmentWithoutSearchWidget.getInstance("asdadas")
        frag.setListeber(this)
        childFragmentManager.beginTransaction()
            .add(R.id.addressContainer, frag, "AddressFragmentWithoutSearchWidget")
            .addToBackStack("AddressFragmentWithoutSearchWidget")
            .commit()

        when (fragentType) {

        }

    }

    override fun onFinalAddressSelect(item: String) {
        Toast.makeText(activity as FragmentActivity, "Address Selected", Toast.LENGTH_SHORT)
            .show()
    }

}