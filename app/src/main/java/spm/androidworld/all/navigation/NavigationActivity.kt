package spm.androidworld.all.navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import spm.androidworld.all.R


/**
 * https://github.com/prasannajeet/android-navigation-example?source=post_page-----48d4167ec9e5----------------------
 * https://medium.com/@prasannajeet/using-the-navigation-architecture-component-in-android-jetpack-kotlin-48d4167ec9e5
 */
class NavigationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)

        val host = NavHostFragment.create(R.navigation.welcome_nav)
        supportFragmentManager.beginTransaction().replace(R.id.container, host)
            .setPrimaryNavigationFragment(host).commit()

    }

    override fun onSupportNavigateUp(): Boolean =
        Navigation.findNavController(this, R.id.container).navigateUp()
}