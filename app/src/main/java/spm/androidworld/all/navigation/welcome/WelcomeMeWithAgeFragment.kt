package spm.androidworld.all.navigation.welcome


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.nav_fragment_welcome_with_age.*
import spm.androidworld.all.R

/**
 * A simple [Fragment] subclass.
 *
 */
class WelcomeMeWithAgeFragment : androidx.fragment.app.Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.nav_fragment_welcome_with_age, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentArgs = WelcomeMeWithAgeFragmentArgs.fromBundle(arguments!!)
        val name = fragmentArgs.myname
        val age = fragmentArgs.myage

        welcomewithNameandAge.text = "Welcome $name! Your age is $age"
    }
}
