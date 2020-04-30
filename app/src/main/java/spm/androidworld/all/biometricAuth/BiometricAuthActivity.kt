package spm.androidworld.all.biometricAuth

import android.Manifest
import android.app.KeyguardManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricPrompt
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.fragment.app.FragmentActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_biometric_auth.*
import spm.androidworld.all.R
import java.util.concurrent.Executors

class BiometricAuthActivity : AppCompatActivity() {

    lateinit var constraintlayout: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_biometric_auth)

        supportActionBar?.setTitle("BIOMETRIC")

        constraintlayout = findViewById(R.id.constraintLayoutBiometric)


        val executor = Executors.newSingleThreadExecutor()
        val activity: FragmentActivity = this // reference to activity
        val biometricPrompt = BiometricPrompt(activity, executor, object : BiometricPrompt.AuthenticationCallback() {

            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                if (errorCode == BiometricPrompt.ERROR_NEGATIVE_BUTTON) {
                    // user clicked negative button
                } else {
                    //"Called when an unrecoverable error has been encountered and the operation is complete.")
                }
            }

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                /**
                 * "Called when a biometric is recognized."
                 */
                showSnackBarError("Biometric Matched")
            }


            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                //"Called when a biometric is valid but not recognized.")
            }
        })

        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Set the title to display.")
            .setSubtitle("Set the subtitle to display.")
            .setDescription("Set the description to display")
            .setNegativeButtonText("Negative Button")
            .build()

        imageViewFinger.setOnClickListener {
            if (isBiometricSupported()) {
                biometricPrompt.authenticate(promptInfo)
            }
        }


    }

    fun isBiometricSupported(): Boolean {

        val keyguardManager: KeyguardManager = getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager
        val packageManager: PackageManager = this.packageManager

        if (!keyguardManager.isDeviceSecure) {
            showSnackBarError("SCREEN LOOCK IS NOT SET")
            return false
        }

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.USE_BIOMETRIC
            ) !=
            PackageManager.PERMISSION_GRANTED
        ) {
            showSnackBarError("Fingerprint authentication permission not enabled")
            return false
        }

        if (packageManager.hasSystemFeature(PackageManager.FEATURE_FINGERPRINT)) {
            return true
        }
        return true
    }

    fun showSnackBarError(msg: String) {
        val snackbar: Snackbar = Snackbar.make(constraintlayout, msg, Snackbar.LENGTH_LONG)
        snackbar.show()
    }


}
