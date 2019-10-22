package spm.androidworld.all.navigation.welcome

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.nav_activity_second.*
import spm.androidworld.all.R

class WelcomeMeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.nav_activity_second)
        val name = WelcomeMeActivityArgs.fromBundle(intent.extras).myname
        showWelcome.text = "Welcome $name!"
    }
}