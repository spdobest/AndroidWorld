package spm.androidworld.all.application

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import spm.androidworld.all.autoAddressSearch.module.addressSearchModule

class AndroidWorldApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // Start Koin
        startKoin {
            androidLogger()
            androidContext(this@AndroidWorldApplication)
            modules(
                listOf(
                    addressSearchModule
                )
            )
        }
    }
}