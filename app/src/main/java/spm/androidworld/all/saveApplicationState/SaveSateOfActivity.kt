package spm.androidworld.all.saveApplicationState

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_save_sate_of.*
import spm.androidworld.all.R

class SaveSateOfActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save_sate_of)
        showLog("onCreate()")

        // Check whether we're recreating a previously destroyed instance
        if (savedInstanceState != null) {
            with(savedInstanceState) {
                // Restore value of members from saved state
                val name = getString(KEY_NAME)
                val password = getString(KEY_PWD)
                etName.setText(name.toString())
                etPassword.setText(password.toString())
            }
        } else {
            // Probably initialize members with default values for a new instance
        }
    }

    override fun onStart() {
        super.onStart()
        showLog("onStart()")
    }

    override fun onResume() {
        super.onResume()
        showLog("onResume()")
    }

    override fun onPause() {
        super.onPause()
        showLog("onPause()")
    }

    override fun onStop() {
        super.onStop()
        showLog("onStop()")
    }

    override fun onRestart() {
        super.onRestart()
        showLog("onRestart()")
    }

    override fun onDestroy() {
        super.onDestroy()
        showLog("onDestroy()")
    }

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        super.onSaveInstanceState(outState, outPersistentState)
        showLog("onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?)")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        // Save the user's current game state
        outState.run {
            putString(KEY_NAME, etName.text.toString())
            putString(KEY_PWD, etPassword.text.toString())
        }
        super.onSaveInstanceState(outState)
        showLog("onSaveInstanceState(outState: Bundle)")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        // Always call the superclass so it can restore the view hierarchy
        super.onRestoreInstanceState(savedInstanceState)
        // Check whether we're recreating a previously destroyed instance
        if (savedInstanceState != null) {
            with(savedInstanceState) {
                // Restore value of members from saved state
                val name = getString(KEY_NAME)
                val password = getString(KEY_PWD)
                etName.setText(name.toString())
                etPassword.setText(password.toString())
            }
        } else {
            // Probably initialize members with default values for a new instance
        }
    }

    override fun onStateNotSaved() {
        super.onStateNotSaved()
        showLog("onStateNotSaved()")
    }

    companion object {
        val TAG = SaveSateOfActivity::class.java.simpleName
        val KEY_NAME = "Name"
        val KEY_PWD = "Password"
    }

    fun showLog(msg: String) {
        Log.i(TAG, msg)
    }

}
