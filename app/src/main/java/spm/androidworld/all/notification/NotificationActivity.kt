package spm.androidworld.all.notification

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_notification.*
import spm.androidworld.all.R

class NotificationActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        Log.i(TAG, "onCreate")

        btnLocalNotification.setOnClickListener(this)
        btnBadgeNotification.setOnClickListener(this)
        btnNotification1.setOnClickListener(this)
        btnNotification2.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnLocalNotification -> {

            }
            R.id.btnBadgeNotification -> {

            }
            R.id.btnNotification1 -> {

            }
            R.id.btnNotification2 -> {

            }
        }
    }

    companion object {
        val TAG = this.javaClass.simpleName
    }
}
