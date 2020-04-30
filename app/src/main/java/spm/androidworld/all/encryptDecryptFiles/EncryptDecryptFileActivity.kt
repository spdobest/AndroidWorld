package spm.androidworld.all.encryptDecryptFiles

import android.Manifest.permission.*
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.security.crypto.EncryptedFile
import androidx.security.crypto.MasterKeys
import com.android.assignment.utility.CommonUtils
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_e_ncrypt_decrypt_file.*
import okhttp3.*
import spm.androidworld.all.R
import java.io.*
import java.net.UnknownHostException
import java.security.GeneralSecurityException


class EncryptDecryptFileActivity : AppCompatActivity(), View.OnClickListener {

    /**
     * Permission list required
     */
    var permissionList: Array<String> = arrayOf<String>(
        "android.permission.READ_EXTERNAL_STORAGE",
        "android.permission.WRITE_EXTERNAL_STORAGE"
    )
    val PERMISSION_REQUEST_CODE = 112
    private var file: File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_e_ncrypt_decrypt_file)

        file = File(filesDir, FILE_NAME)
        file?.let {
            if (it.exists()) {
                it.delete()
                file = File(filesDir, FILE_NAME)
            }
        }
        btnDownload.setOnClickListener(this)
        btnView.setOnClickListener(this)

        askPermission()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnDownload -> {
                if (CommonUtils.isInternetAvailable(this)) {
                    downloadFile()
                }
            }
            R.id.btnView -> {
                readToEncryptedFile()
            }
        }
    }


    @Throws(IOException::class, GeneralSecurityException::class, UnknownHostException::class)
    private fun downloadFile() {


        writeToEncryptedFile(edittextContent.text.toString())
        readToEncryptedFile()


        Request.Builder().url(FILE_URL).build().also { request ->
            OkHttpClient.Builder().build().newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    throw e
                }

                override fun onResponse(call: Call, response: Response) {
                    if (response.isSuccessful) {

                    }
                }
            })
        }
    }

    @Throws(IOException::class, GeneralSecurityException::class)
    private fun writeToEncryptedFile(response: String) {
        var encryptedOutputStream: FileOutputStream? = null
        try {
            encryptedOutputStream = generateEncryptedFile()?.openFileOutput()
//            response.body()?.bytes()?.let { bytes ->
            response.toByteArray().let { bytes ->
                encryptedOutputStream?.write(bytes)
            }
        } catch (securityEx: GeneralSecurityException) {
            throw securityEx
        } catch (ioEx: IOException) {
            throw ioEx
        } finally {
            encryptedOutputStream?.close()
        }
    }

    @Throws(IOException::class, GeneralSecurityException::class)
    private fun readToEncryptedFile() {
        var encryptedInputStream: FileInputStream? = null
        try {
            encryptedInputStream = generateEncryptedFile()?.openFileInput()
            encryptedInputStream?.let { stream ->
                val reader = BufferedReader(InputStreamReader(stream))
                var text = ""
                reader.forEachLine { line ->
                    text += "$line \n"
                }
                runOnUiThread {
                    tvContent.text = text
                }
            }
        } catch (securityEx: GeneralSecurityException) {
            throw securityEx
        } catch (ioEx: IOException) {
            throw ioEx
        } finally {
            encryptedInputStream?.close()
        }
    }

    private fun generateEncryptedFile(): EncryptedFile? {
        // Creates or gets the key to encrypt and decrypt.
        val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

        // Creates the instance for the encrypted file.
        return file?.let {
            return EncryptedFile.Builder(
                it,
                this,
                masterKeyAlias,
                EncryptedFile.FileEncryptionScheme.AES256_GCM_HKDF_4KB
            ).build()

        }
    }

    companion object {
        const val FILE_URL = "https://github.com/spdobest/AndroidWorld/blob/master/README.md"
        const val FILE_NAME = "README.md"
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSION_REQUEST_CODE -> {
                if (grantResults.isNotEmpty()) {
                    val storagePermission = grantResults[0] == PackageManager.PERMISSION_GRANTED
                    if (storagePermission)
                        Snackbar.make(
                            rootView,
                            "Permission Granted, Now you can Storage Permission",
                            Snackbar.LENGTH_LONG
                        ).show()
                    else {

                        Snackbar.make(
                            rootView,
                            "Permission Denied, You cannot access location data and camera.",
                            Snackbar.LENGTH_LONG
                        ).show()

                        if (shouldShowRequestPermissionRationale(ACCESS_FINE_LOCATION)) {
                            showMessageOKCancel("You need to allow access to both the permissions",
                                DialogInterface.OnClickListener { dialog, which ->
                                    requestPermissions(
                                        arrayOf(
                                            READ_EXTERNAL_STORAGE
                                        ),
                                        PERMISSION_REQUEST_CODE
                                    )
                                })
                            return
                        }
                    }
                }
            }
        }
    }

    private fun askPermission() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {

            val result1 = ContextCompat.checkSelfPermission(
                applicationContext,
                WRITE_EXTERNAL_STORAGE
            )
            val result2 =
                ContextCompat.checkSelfPermission(applicationContext, READ_EXTERNAL_STORAGE)

            val combinedResult =
                result1 == PackageManager.PERMISSION_GRANTED && result2 == PackageManager.PERMISSION_GRANTED

            if (!combinedResult) {
                ActivityCompat.requestPermissions(this, permissionList, PERMISSION_REQUEST_CODE)
            } else {
                Snackbar.make(rootView, "Permission already granted.", Snackbar.LENGTH_LONG).show();
            }
        }
    }

    private fun showMessageOKCancel(
        message: String,
        okListener: DialogInterface.OnClickListener
    ) {
        AlertDialog.Builder(this)
            .setMessage(message)
            .setPositiveButton("OK", okListener)
            .setNegativeButton("Cancel", null)
            .create()
            .show()
    }
}
