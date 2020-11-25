package spm.androidworld.all.hiltDI

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


/**
 * Created by Sibaprasad Mohanty on 29/10/20.
 * Spm Limited
 * sp.dobest@gmail.com
 */

@HiltAndroidApp
class HiltApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}