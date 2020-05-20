package spm.androidworld.all.dataBinding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import spm.androidworld.all.R

/**
 * https://developer.android.com/topic/libraries/data-binding
 *
 * https://developer.android.com/topic/libraries/data-binding/start
 */
class OneWayDataBindingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_oneway_data_binding)
    }
}
