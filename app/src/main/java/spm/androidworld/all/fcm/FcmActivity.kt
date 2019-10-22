package spm.androidworld.all.fcm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import spm.androidworld.all.R

/**
 * https://medium.com/@cdmunoz/working-easily-with-fcm-push-notifications-in-android-e1804c80f74
 *
 */
class FcmActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fcm)
    }
}
