package spm.androidworld.all.backgroundServices

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_background.*
import spm.androidworld.all.R

class BackgroundActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_background)

        Log.i(TAG, "onCreate")

        btnNormalService.setOnClickListener(this)
        btnIntentService.setOnClickListener(this)
        btnBindedService.setOnClickListener(this)
        btnJobService.setOnClickListener(this)
        btnServicesForOreo.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnNormalService -> {

            }
            R.id.btnIntentService -> {

            }
            R.id.btnBindedService -> {

            }
            R.id.btnJobService -> {

            }
            R.id.btnServicesForOreo -> {

            }
        }
    }

    companion object {
        val TAG = this.javaClass.simpleName
    }

}
