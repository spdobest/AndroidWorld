package spm.androidworld.all.location

import android.content.IntentSender
import android.location.Location
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.tasks.Task
import com.google.android.material.snackbar.Snackbar
import spm.androidworld.all.R

class CurrentLocationActivity : AppCompatActivity() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    val builder = LocationSettingsRequest.Builder()

    val client: SettingsClient = LocationServices.getSettingsClient(this)
    val task: Task<LocationSettingsResponse> = client.checkLocationSettings(builder.build())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_current_location)

        val root = findViewById<ConstraintLayout>(R.id.root)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                Log.i(TAG, location?.latitude.toString() + " " + location?.longitude.toString())
                val snackbar = Snackbar.make(
                    root, location?.latitude.toString() + " " + location?.longitude.toString(),
                    Snackbar.LENGTH_INDEFINITE
                )

                snackbar.show()

            }


        task.addOnSuccessListener { locationSettingsResponse ->
            // All location settings are satisfied. The client can initialize
            // location requests here.
            // ...
        }

        task.addOnFailureListener { exception ->
            if (exception is ResolvableApiException) {
                // Location settings are not satisfied, but this can be fixed
                // by showing the user a dialog.
                try {
                    // Show the dialog by calling startResolutionForResult(),
                    // and check the result in onActivityResult().
                    exception.startResolutionForResult(
                        this,
                        123
                    )
                } catch (sendEx: IntentSender.SendIntentException) {
                    // Ignore the error.
                }
            }
        }
    }

    companion object {
        val TAG = CurrentLocationActivity::class.java.simpleName
    }
}
