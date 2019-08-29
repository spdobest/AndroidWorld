package spm.androidworld.all.testing

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_testing.*
import spm.androidworld.all.R

class TestingActivity : AppCompatActivity() {

    var isFragmentVIsible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_testing)

        buttonActivityTesting.setOnClickListener {
            isFragmentVIsible = false
        }

        buttonFragmentTesting.setOnClickListener {
            testingContainer.visibility = View.VISIBLE
            buttonActivityTesting.visibility = View.GONE
            isFragmentVIsible = true
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (isFragmentVIsible) {
            testingContainer.visibility = View.GONE
            buttonActivityTesting.visibility = View.VISIBLE
            buttonActivityTesting.visibility = View.VISIBLE
        }
    }

}
