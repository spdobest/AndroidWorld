package spm.androidworld.all

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


/**
 * Created by Sibaprasad Mohanty on 25/11/20.
 * Spm Limited
 * sp.dobest@gmail.com
 */

@HiltAndroidApp
class AndroidWorldApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}