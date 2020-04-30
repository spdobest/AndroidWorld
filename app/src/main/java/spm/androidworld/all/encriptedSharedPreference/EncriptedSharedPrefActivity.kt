package spm.androidworld.all.encriptedSharedPreference

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import kotlinx.android.synthetic.main.activity_encripted_shared_pref.*


class EncriptedSharedPrefActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var sharedPreferences: SharedPreferences

    override fun onClick(v: View?) {

        val userId = editTextKey.text.toString()
        val pwd = editTextPassword.text.toString()

        when (v?.id) {

            spm.androidworld.all.R.id.btnEncript -> {
                encriptData(userId, pwd)
            }

            spm.androidworld.all.R.id.btnDencript -> {
                dencriptData()
            }

            spm.androidworld.all.R.id.btnReset -> {
                editTextKey.setText("")
                editTextPassword.setText("")
            }

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(spm.androidworld.all.R.layout.activity_encripted_shared_pref)

        btnEncript.setOnClickListener(this)
        btnDencript.setOnClickListener(this)
        btnReset.setOnClickListener(this)
    }

    private fun encriptData(userId: String, pwd: String) {
        if (userId.isNotEmpty() && pwd.isNotEmpty()) {
            putData("USERID", userId)
            putData("PWD", pwd)
        }
    }

    private fun dencriptData() {
        tvUserId.setText(getSecureSharedPreference().getString("USERID", ""))
        tvPwd.setText(getSecureSharedPreference().getString("PWD", ""))
    }


    private fun getSecureSharedPreference(): SharedPreferences {
        val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

        if (sharedPreferences == null) {
            sharedPreferences = EncryptedSharedPreferences.create(
                "secret_shared_prefs",
                masterKeyAlias,
                this,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            )
        }
        return sharedPreferences
    }


    fun putData(key: String, value: Any) {
        // use the shared preferences and editor as you normally would
        val editor = getSecureSharedPreference().edit()
        when (value) {
            is String -> {
                editor.putString(key, value)
            }
            is Int -> {
                editor.putInt(key, value)
            }
            is Float -> {
                editor.putFloat(key, value)
            }
            is Long -> {
                editor.putLong(key, value)
            }
            is Boolean -> {
                editor.putBoolean(key, value)
            }
        }
        editor.commit()
    }
}
