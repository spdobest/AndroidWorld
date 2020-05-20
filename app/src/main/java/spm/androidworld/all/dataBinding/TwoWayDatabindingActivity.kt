package spm.androidworld.all.dataBinding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import spm.androidworld.all.R

/**
 * https://developer.android.com/topic/libraries/data-binding/two-way
 *
 *
 */
class TwoWayDatabindingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_two_way_databinding)
    }
}
