package spm.androidworld.all.hashing

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_hashing_example.*
import spm.androidworld.all.R
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.security.NoSuchProviderException
import java.security.SecureRandom


class HashingExampleActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hashing_example)

        title = "Hashing Example"

        buttonMd5.setOnClickListener(this)
        buttonSha256.setOnClickListener(this)
        buttonSha1.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {

        val pwd = edittextPassword.text.toString();

        when (p0?.id) {
            R.id.buttonMd5 -> {
                tvMd5.text = getMd5Hashing(pwd, null)
            }

            R.id.buttonSha256 -> {
                tvSha256.text = getSHA256Hashing(pwd, null)
            }

            R.id.buttonSha1 -> {
                tvSha1.text = getSHA1Hashing(pwd, null)
            }
        }
    }

    private fun getMd5Hashing(password: String, salt: ByteArray? = null): String {
        try {
            var passwordInputBytes: ByteArray
            var passwordOutputByteArray: ByteArray
            if (!TextUtils.isEmpty(password)) {
                passwordInputBytes = password.toByteArray()
                val md5 = MessageDigest.getInstance("MD5")
                md5.reset()
                if (salt == null) {
                    md5.update(passwordInputBytes)  // without salt
                } else {
                    md5.update(salt)  // with salt
                }

                passwordOutputByteArray = md5.digest(passwordInputBytes)
                var sb = StringBuffer()
                for (element in passwordOutputByteArray) {
                    sb.append(Integer.toHexString(0xFF and element.toInt()))
                }
                return sb.toString()
            }
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace();
        }
        return ""
    }

    private fun getSHA256Hashing(password: String, salt: ByteArray? = null): String {
        try {
            var passwordInputBytes: ByteArray
            var passwordOutputByteArray: ByteArray
            if (!TextUtils.isEmpty(password)) {
                passwordInputBytes = password.toByteArray()
                val msgDigestSHA25 = MessageDigest.getInstance("SHA-256")
                msgDigestSHA25.reset()
                if (salt == null) {
                    msgDigestSHA25.update(passwordInputBytes)  // without salt
                } else {
                    msgDigestSHA25.update(salt)  // with salt
                }
                passwordOutputByteArray = msgDigestSHA25.digest(passwordInputBytes)
                var sb = StringBuffer()
                for (element in passwordOutputByteArray) {
                    sb.append(Integer.toHexString(0xFF and element.toInt()))
                }
                return sb.toString()
            }
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace();
        }
        return ""
    }

    private fun getSHA1Hashing(password: String, salt: ByteArray? = null): String {

        try {
            var passwordInputBytes: ByteArray
            var passwordOutputByteArray: ByteArray
            if (!TextUtils.isEmpty(password)) {
                passwordInputBytes = password.toByteArray()
                val msgDigestSHA1 = MessageDigest.getInstance("SHA-1")
                msgDigestSHA1.reset()
                if (salt == null) {
                    msgDigestSHA1.update(passwordInputBytes)  // without salt
                } else {
                    msgDigestSHA1.update(salt)  // with salt
                }
                passwordOutputByteArray = msgDigestSHA1.digest(passwordInputBytes)
                var sb = StringBuffer()
                for (element in passwordOutputByteArray) {
                    sb.append(Integer.toHexString(0xFF and element.toInt()))
                }
                return sb.toString()
            }
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace();
        }
        return ""
    }

    //Add salt
    @Throws(NoSuchAlgorithmException::class, NoSuchProviderException::class)
    private fun getSalt(): ByteArray? {
        //Always use a SecureRandom generator
        val sr: SecureRandom = SecureRandom.getInstance("SHA1PRNG", "SUN")
        //Create array for salt
        val salt = ByteArray(16)
        //Get a random salt
        sr.nextBytes(salt)
        //return salt
        return salt
    }

}
