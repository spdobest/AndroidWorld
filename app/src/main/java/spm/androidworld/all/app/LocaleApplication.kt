package spm.androidworld.all.app

import android.app.Application
import android.content.Context
import spm.androidworld.all.utility.LocaleHelper


/**
 * Created by Sibaprasad Mohanty on 18/04/20.
 * Spm Limited
 * sp.dobest@gmail.com
 */

class LocaleApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(LocaleHelper.onAttach(base, "en"));
    }
}