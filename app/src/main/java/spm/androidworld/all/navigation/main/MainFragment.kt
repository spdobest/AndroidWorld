package spm.androidworld.all.navigation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.nav_main_fragment.*
import spm.androidworld.all.R

class MainFragment : androidx.fragment.app.Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.nav_main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Sending data from one fragment to another fragment
        goToDetailFragment.setOnClickListener {

            val name = editTextName.text.toString()
            if (isTextEmpty(name)) {
                editTextName.error = "Please enter a name"
            } else {
                val action = MainFragmentDirections.actionListToDetail()
                action.myname = name
                findNavController().navigate(action)
            }
        }

        // Sending data from one fragment to an activity
        goToSecondActivity.setOnClickListener {

            val name = editTextName.text.toString()
            if (isTextEmpty(name)) {
                editTextName.error = "Please enter a name"
            } else {
                val action = MainFragmentDirections.actionMainToSecondActivity()
                action.myname = name
                findNavController().navigate(action)
            }
        }

        gotToAgeFragment.setOnClickListener {
            val name = editTextName.text.toString()
            if (isTextEmpty(name)) {
                editTextName.error = "Please enter a name"
            } else {
                val action = MainFragmentDirections.actionMainFragmentToAgeFragment()
                action.myname = name
                findNavController().navigate(action)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        editTextName.setText("")
    }

    companion object {
        fun isTextEmpty(text: String): Boolean {
            return text.isEmpty()
        }
    }
}