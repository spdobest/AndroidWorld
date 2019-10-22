package spm.androidworld.all.navigation.age


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.nav_fragment_age.*
import spm.androidworld.all.R
import spm.androidworld.all.navigation.main.MainFragment

/**
 * A simple [Fragment] subclass.
 *
 */
class AskMyAgeFragment : androidx.fragment.app.Fragment() {

    private lateinit var name: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.nav_fragment_age, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        name = AskMyAgeFragmentArgs.fromBundle(arguments!!).myname
        helloEnterAge.hint = "Hello $name! What's your age?"

        buttonWelcomeWithAge.setOnClickListener {
            val age: Int = helloEnterAge.text.toString().toInt()
            if (MainFragment.isTextEmpty(age.toString())) {
                helloEnterAge.error = "Please enter age"
            } else {
                val action = AskMyAgeFragmentDirections.actionAgeFragmentToWelcomeWithAgeFragment()
                action.myage = age
                action.myname = name
                findNavController().navigate(action)
            }
        }
    }
}